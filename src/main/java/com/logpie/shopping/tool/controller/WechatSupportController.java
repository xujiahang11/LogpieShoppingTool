package com.logpie.shopping.tool.controller;


import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.service.WechatSupportService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Controller
@RequestMapping(path = "/{shopPath}/wechat")
public class WechatSupportController {

    private static final String UTF_8 = "utf-8";
    private LogpieLogger LOG = LogpieLoggerFactory.getLogger(this.getClass());

    @Autowired
    private WechatSupportService service;


    // 微信公众平台验证
    @RequestMapping(path = "/validation", method = RequestMethod.GET)
    public @ResponseBody String validate(@RequestParam(value = "signature", required = true) final String signature,
                                         @RequestParam(value = "timestamp", required = true) final String timestamp,
                                         @RequestParam(value = "nonce", required = true) final String nonce,
                                         @RequestParam(value = "echostr", required = true) final String echostr) {
        LOG.trace("Validating the sender's identity...");
        return service.checkSignature(timestamp, nonce, signature) ? echostr : null;
    }

    // 接受普通消息
    @RequestMapping(path = "/validation", method = RequestMethod.POST)
    public @ResponseBody String receiveMessage(final HttpServletRequest request) {
        LOG.trace("Processing the WeChat message request...");

        try {
            request.setCharacterEncoding(UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
        }

        // 调用核心业务类接收消息、处理消息
        try {
            return service.processMessage(request);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        return "success";
    }
}
