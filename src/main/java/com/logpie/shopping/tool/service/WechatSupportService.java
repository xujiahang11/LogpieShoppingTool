package com.logpie.shopping.tool.service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.WechatMessage;
import com.logpie.shopping.tool.model.WechatAutoReply;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

@Service
public class WechatSupportService {

    private static final String WECHAT_INTEGRATION_TOKEN = "LogpieToken";
    private static final String MESSAGE_TYPE_TEXT = "text";

    @Autowired
    private WechatAutoReplyCompiler compiler;

    private LogpieLogger LOG = LogpieLoggerFactory.getLogger(this.getClass());

    public boolean checkSignature(final String timestamp, final String nonce, final String signature) {
        final String[] components = new String[3];
        components[0] = timestamp;
        components[1] = nonce;
        components[2] = WECHAT_INTEGRATION_TOKEN;

        Arrays.sort(components);

        final StringBuilder builder = new StringBuilder();
        for (String component : components)
            builder.append(component);

        final String targetString = builder.toString();
        final String logpieComputeSignature = DigestUtils.sha1Hex(targetString);
        LOG.info("Computed signature - " + logpieComputeSignature);

        if(logpieComputeSignature.equals(signature)) {
            LOG.info("WeChat validation is successful.");
            return true;
        }

        return false;
    }

    public String processMessage(final HttpServletRequest request) throws IOException {
        InputStream input = request.getInputStream();

        XStream inXStream = new XStream();
        inXStream.alias("xml", WechatMessage.class);
        WechatMessage message = (WechatMessage) inXStream.fromXML(input);

        WechatAutoReply autoReply = generateAutoReply(message);

        XStream outXStream = new XStream();
        outXStream.alias("xml", WechatAutoReply.class);
        return outXStream.toXML(autoReply);
    }

    private WechatAutoReply generateAutoReply(final WechatMessage message) {
        // 公众账号
        final String wechatOfficialAccount = message.getToUserName();
        // 发送方账号（open id）
        final String userOpenId = message.getFromUserName();
        // 消息类型
        final String msgType = message.getMsgType();

        LOG.info("Received a WeChat message from user " + userOpenId);

        WechatAutoReply autoReply = new WechatAutoReply();
        autoReply.setToUserName(userOpenId);
        autoReply.setFromUserName(wechatOfficialAccount);
        autoReply.setCreateTime(new Date(System.currentTimeMillis()).toString());
        autoReply.setMsgType(MESSAGE_TYPE_TEXT);

        if(msgType.equals(MESSAGE_TYPE_TEXT)) {
            // 接收用户发送的文本消息
            String msg = message.getContent();
            LOG.info("The message content - " + msg);
            String response = getResponseString(msg);
            autoReply.setContent(response);
        }else {
            LOG.info("Cannot support the message type - " + msgType);
            autoReply.setContent(getFormatErrorString());
        }

        return autoReply;
    }

    private String getResponseString(String msg) {
        String response = compiler.compileMessage(msg);

        if(response.isEmpty()) {
            return getNoSupportString();
        }
        return response;
    }

    private String getNoSupportString() {
        return "抱歉，您输入的文本格式有误。请回复\"帮助\"查看说明";
    }

    private String getFormatErrorString() {
        return "抱歉，您输入的消息类型有误。请输入文本消息";
    }
}
