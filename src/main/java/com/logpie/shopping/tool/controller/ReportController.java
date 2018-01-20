package com.logpie.shopping.tool.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Controller
@RequestMapping("/{shopPath}/reports")
public class ReportController {

    @Autowired
    private ShopService shopService;
    private LogpieLogger LOG = LogpieLoggerFactory.getLogger(this.getClass());

    private static final String IMAGE_URL_ZONE_CN = "logpie.oss-cn-qingdao.aliyuncs.com";
    private static final String IMAGE_URL_ZONE_DEFAULT = "s3-us-west-2.amazonaws.com/logpiestorage-images";
    private HashMap<String, Long> metricMap = new HashMap<>();

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getDefaultPage(@PathVariable final String shopPath, final Model model) {
        Shop shop = shopService.getShopByPath(shopPath);
        model.addAttribute("shop", shop);

        String requestId = RequestContextHolder
                .getRequestAttributes()
                .getAttribute("requestId", RequestAttributes.SCOPE_REQUEST).toString();
        model.addAttribute("requestId", requestId);

        String ip = RequestContextHolder
                .getRequestAttributes()
                .getAttribute("ip", RequestAttributes.SCOPE_REQUEST).toString();
        String countryCode = getCountryCodeByCallingIpApiService(ip);
        String imageURL = getImageURLByCountryCode(countryCode);
        model.addAttribute("imageUrl", imageURL);

        long startTime = (long) RequestContextHolder
                .getRequestAttributes()
                .getAttribute("requestStartTime", RequestAttributes.SCOPE_REQUEST);
        addRequestStartTime(requestId, startTime);

        return "report/page";
    }

    @RequestMapping(path = "/metric", method = RequestMethod.POST)
    public @ResponseBody String handleLatencyRequest(@RequestBody final String requestId) {
        getLatency(requestId);
        return "success";
    }

    private String getImageURLByCountryCode(String countryCode) {
        if(countryCode.equals("CN")) {
            LOG.info("This request comes from Zone - CN");
            return IMAGE_URL_ZONE_CN;
        }
        LOG.info("This request comes from Zone - US(DEFAULT)");
        return IMAGE_URL_ZONE_DEFAULT;
    }

    private String getCountryCodeByCallingIpApiService(String ip) {
        if(ip == null || ip.isEmpty()) return "";

        String content = "";
        try {
            content = fileRequestToIpApiService(ip);
        } catch (IOException e) {
            LOG.error("IOException happened when file a request to IP-API service");
            e.printStackTrace();
        }
        if(content.isEmpty()) {
            LOG.error("Response from IP-API service is empty");
            return "";
        }

        try {
            JsonNode json = new ObjectMapper().readTree(content);
            JsonNode countryCode = json.get("countryCode");
            if(countryCode == null) {
                LOG.error("Failed to retrieve country code");
                return "";
            }
            if(countryCode.textValue().equals("CN")) return "CN";
            return "US";
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.error("IOException happened when parse IP-API service response to JSON");
        return "";
    }

    private String fileRequestToIpApiService(String ip) throws IOException {
        URL url = new URL("http://ip-api.com/json/" + ip);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();
        if(status == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            return content.toString();
        }
        LOG.error("HTTP Connection Error! Status Code " + status);
        return "";
    }

    private void addRequestStartTime(String requestId, long startTime) {
        if(metricMap.size() > 10000) {
            metricMap.clear();
        }
        metricMap.put(requestId, new Long(startTime));
    }

    private void getLatency(String requestId) {
        if(metricMap.containsKey(requestId)) {
            long latency = System.currentTimeMillis() - metricMap.get(requestId);
            LOG.info("TOTAL SERVICE LATENCY OF REQUEST " + requestId + ": " + latency + "ms");
            metricMap.remove(requestId);
        }
    }

}
