package com.me.szzc.pojo.vo;

import com.me.szzc.constant.Constant;

import java.io.Serializable;

/**
 * 公共响应
 */
public class ResultVO<T> implements Serializable {

    //操作是否成功
    private boolean success = false;

    //条数
    private int total;

    //数据
    private T data;

   //错误时的报错信息
    protected String message;

    //错误时的错误码
    protected String code;

    public ResultVO() {
    }

    public ResultVO(boolean success) {
        this.success = success;
    }

    public ResultVO(boolean success, int total) {
        this.success = success;
        this.total = total;
    }

    public ResultVO(String code, String message) {
        this.success = false;
        this.code = code;
        this.message = message;
    }

    public ResultVO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultVO(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static ResultVO fail() {
        ResultVO resultDTO = new ResultVO();
        resultDTO.setSuccess(false);
        resultDTO.setTotal(0);
        return resultDTO;
    }

    public static ResultVO error(Integer code, String message) {
        ResultVO vo = new ResultVO();
        vo.setCode(code.toString());
        vo.setMessage(message);
        vo.setSuccess(false);
        vo.setTotal(0);
        return vo;
    }

    public static ResultVO success(String message) {
        ResultVO vo = new ResultVO();
        vo.setMessage(message);
        vo.setCode(Constant.SUCCESS_CODE.toString());
        vo.setSuccess(true);
        vo.setTotal(0);
        return vo;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
