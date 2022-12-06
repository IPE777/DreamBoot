package com.wangyuhang.dream_boot.vo;

public enum  LoginType {
     CUSTOMER("Customer"), USER("User");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
    }
