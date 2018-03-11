package com.logpie.shopping.tool.model;

public enum WechatAutoReplyRule {

    /**
     * Regex explanation
     *
     * [\p{L}{1,10}]: \p{L} matches any language character. {1,20} means the length should be between 1 and 20.
     *
     * [\d{11}]: \d matches any digit [0-9]. {11} means the length should be 11.
     */

    help("帮助", new String[0]),
    register("用户绑定", new String[]{"\\p{L}{1,10}", "\\d{11}"}),
    checkOrders("订单查询", new String[0]),
    checkOrderById("订单查询", new String[]{"\\d{1,8}"});

    private String command;
    // Regex Pattern String
    private String[] parameterPatterns;

    private WechatAutoReplyRule(String command, String[] patterns) {
        this.command = command;
        this.parameterPatterns = patterns;
    }

    public boolean isCommandMatch(String cmd) {
        return cmd.equals(command);
    }

    public boolean isParametersMatch(String[] parameters) {
        if(parameters.length != getParameterSize()) return false;

        for(int i = 0; i < parameters.length; i++)
            if(!parameters[i].matches(parameterPatterns[i]))
                return false;

        return true;
    }

    public String getCommand() {
        return command;
    }

    public String[] getParameterPatterns() {
        return parameterPatterns;
    }

    public int getParameterSize() {
        return parameterPatterns.length;
    }
}
