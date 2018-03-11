package com.logpie.shopping.tool.model;

public class WechatAutoReply {

    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String Content;

    public WechatAutoReply() {}

    public WechatAutoReply(final String ToUserName, final String FromUserName, final String CreateTime,
                           final String MsgType, final String Content) {
        this.ToUserName = ToUserName;
        this.FromUserName = FromUserName;
        this.CreateTime = CreateTime;
        this.MsgType = MsgType;
        this.Content = Content;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String ToUserName) {
        this.ToUserName = ToUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String FromUserName) {
        this.FromUserName = FromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String MsgType) {
        this.MsgType = MsgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}
