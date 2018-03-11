package com.logpie.shopping.tool.model;

public class WechatMessage {

    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;

    public WechatMessage() {}

    public WechatMessage(final String ToUserName, final String FromUserName, final String CreateTime,
                         final String MsgType, final String Content, final String MsgId) {
        this.ToUserName = ToUserName;
        this.FromUserName = FromUserName;
        this.CreateTime = CreateTime;
        this.MsgType = MsgType;
        this.Content = Content;
        this.MsgId = MsgId;
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

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String MsgId) {
        this.MsgId = MsgId;
    }
}
