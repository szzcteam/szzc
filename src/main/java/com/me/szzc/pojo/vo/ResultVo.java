package com.me.szzc.pojo.vo;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class ResultVo implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public ResultVo(Object data){
        this.data = data;
        this.msg = WebSuccess.SUCCESS.getMsg();
        this.code = WebSuccess.SUCCESS.getCode();
    }

    public ResultVo(int code, String msg){
        this.msg = msg;
        this.code = code;
    }

    public static ResultVo success(Object data){
        return new ResultVo(data);
    }

    public static ResultVo error(int code, String msg){
        return new ResultVo(code,msg);
    }

    enum WebError {
        SERVICE_ERROR(90777, "服务异常"),
        PARAM_ERROR(99991, "参数异常"),
        FAIL(99922, "操作失败");
        int code;
        String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        WebError(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    enum WebSuccess {
        SUCCESS(0, "操作成功");
        int code;
        String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        WebSuccess(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
