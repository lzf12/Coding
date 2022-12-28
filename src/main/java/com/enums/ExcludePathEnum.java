package com.enums;

import com.google.common.collect.Lists;

import java.util.List;

public enum ExcludePathEnum {

    EMAIL("/email-manager/v1/login", "邮件发送接口");


    ExcludePathEnum(String path, String desc) {
        this.path = path;
        this.desc = desc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 接口路径
     */
    private String path;

    /**
     * 描述
     */
    private String desc;

    public static List<String> getExcludePathList() {
        List<String> result = Lists.newArrayList();
        for (ExcludePathEnum excludePathEnum : values()) {
            result.add(excludePathEnum.getPath());
        }
        return result;
    }

}
