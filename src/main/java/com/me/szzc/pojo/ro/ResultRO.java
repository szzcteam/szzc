package com.me.szzc.pojo.ro;


import lombok.Data;

import java.io.Serializable;

/**
 * 公共响应
 **/
@Data
public class ResultRO<T> implements Serializable {

    /**
     * 是否成功
     **/
    private boolean success = false;

    /**
     * 条数
     **/
    private int total;

    private T data;

    /**
     * 错误时的报错信息
     **/
    protected String message;

    /**
     * 错误时的错误码
     **/
    protected String code;

    public ResultRO() {
    }

    public ResultRO(boolean success) {
        this.success = success;
    }

    public ResultRO(boolean success, int total) {
        this.success = success;
        this.total = total;
    }

    public ResultRO(String code, String message) {
        this.success = false;
        this.code = code;
        this.message = message;
    }

    public ResultRO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultRO(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ResultRO fail() {
        ResultRO resultDTO = new ResultRO();
        resultDTO.setSuccess(false);
        resultDTO.setTotal(0);
        return resultDTO;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}