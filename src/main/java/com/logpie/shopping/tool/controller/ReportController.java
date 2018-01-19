package com.logpie.shopping.tool.controller;

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

import java.util.HashMap;

@Controller
@RequestMapping("/{shopPath}/reports")
public class ReportController {

    @Autowired
    private ShopService shopService;
    private LogpieLogger LOG = LogpieLoggerFactory.getLogger(this.getClass());

    private HashMap<String, Long> metricMap = new HashMap<>();

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getDefaultPage(@PathVariable final String shopPath, final Model model) {
        Shop shop = shopService.getShopByPath(shopPath);
        model.addAttribute("shop", shop);

        String requestId = RequestContextHolder
                .getRequestAttributes()
                .getAttribute("requestId", RequestAttributes.SCOPE_REQUEST).toString();
        model.addAttribute("requestId", requestId);

        long startTime = (long) RequestContextHolder
                .getRequestAttributes()
                .getAttribute("requestStartTime", RequestAttributes.SCOPE_REQUEST);
        addRequestStartTime(requestId, startTime);

        return "report/page";
    }

    @RequestMapping(path = "/metric", method = RequestMethod.POST)
    public @ResponseBody String getByAJAX(@RequestBody final String requestId) {
        getLatency(requestId);
        return "success";
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
