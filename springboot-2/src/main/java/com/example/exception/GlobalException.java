package com.example.exception;

public class GlobalException extends RuntimeException {
    private String code;
    private String info;

    public GlobalException(){}

    public GlobalException(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "GlobalException{" +
                "code='" + code + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
