package com.logpie.shopping.tool.service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.WechatAutoReply;
import com.logpie.shopping.tool.model.WechatAutoReplyRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WechatAutoReplyCompiler {

    /*
     * Each auto reply rule must matches the following format:
     *
     * [ ] => command name. Maximum length is 10.
     *
     * < > => parameter. Maximum length is 20.
     *
     * Two formats:
     *
     * 1. Multiple parameters. Allow 1 to 5 different parameters.
     *
     * Examples:
     *
     * [用户绑定] $realName$ $phoneNumber$
     *
     * [订单查询] $id$
     *
     * [尺码助手] $brandName$
     *
     * 2. No parameters.
     *
     * Example:
     *
     * [订单查询]
     *
     */

    private LogpieLogger LOG = LogpieLoggerFactory.getLogger(this.getClass());

    public String compileMessage(final String msg)
    {
        for(WechatAutoReplyRule rule : WechatAutoReplyRule.values())
        {
            if(match(rule, msg))
            {
                // TODO 增加实际功能
                switch(rule)
                {
                    case help:
                    {
                        return "帮助文档";
                    }
                    case checkOrders:
                    {
                        return "订单列表";
                    }
                    case checkOrderById:
                    {
                        return "订单 " + getParameters(rule, msg)[0];
                    }
                    case register:
                    {
                        LOG.info("To register...");
                        String[] parameters = getParameters(WechatAutoReplyRule.register, msg);
                        return parameters[0] + "已绑定成功";
                    }
                    default:;
                }
            }
        }
        return "";
    }

    private boolean match(final WechatAutoReplyRule rule, final String msg)
    {
        if(msg == null) return false;

        String[] components = msg.split(" ");
        if(components.length == 0) return false;

        String command = components[0];
        String[] parameters = getParameters(rule, msg);

        return rule.isCommandMatch(command) && rule.isParametersMatch(parameters);
    }

    private String[] getParameters(final WechatAutoReplyRule rule, final String msg)
    {
        String[] components = msg.split(" ");

        String[] parameters = new String[components.length - 1];
        for(int i = 1; i < components.length; i++)
            parameters[i - 1] = components[i];

        return parameters;
    }
}
