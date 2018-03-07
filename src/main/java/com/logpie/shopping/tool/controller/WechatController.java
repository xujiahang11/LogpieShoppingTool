package com.logpie.shopping.tool.controller;


import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping(path = "{shopPath}/wechat")
public class WechatController {

    private static final String token = "LogpieToken";
    private LogpieLogger LOG = LogpieLoggerFactory.getLogger(this.getClass());

    @RequestMapping(path = "/validation", method = RequestMethod.GET)
    public String validate(@RequestParam("signature") final String signature,
                           @RequestParam("timestamp") final String timestamp,
                           @RequestParam("nonce") final String nonce,
                           @RequestParam("echostr") final String echostr) {
        return checkSignature(timestamp, nonce, signature) ? echostr : "";
    }

    private boolean checkSignature(final String timestamp, final String nonce, final String signature) {
        String tmpStr = sort(token) + sort(timestamp) + sort(nonce);
        LOG.info("tmpStr - " + tmpStr);
        String hashStr = DigestUtils.sha1Hex(tmpStr);
        LOG.info("hashStr - " + hashStr);
        LOG.info("check signature result - " + hashStr.equals(tmpStr));
        return hashStr.equals(tmpStr);
    }

    private String sort(final String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }
}
