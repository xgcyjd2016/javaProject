package com.xgcyjd.po;

import java.util.HashMap;

public class Result {
    private int stateCode;
    private String message;

    private HashMap<String,Object> data = new HashMap<>();

//    public Result() {
//        super();
//    }

    public Result(int stateCode, String message) {
        super();
        this.stateCode = stateCode;
        this.message = message;
    }

    public int getStateCode() {
        return stateCode;
    }

//    public void setStateCode(int stateCode) {
//        this.stateCode = stateCode;
//    }

    public String getMessage() {
        return message;
    }

//    public void setMessage(String message) {
//        this.message = message;
//    }

    public static Result getInstance(int stateCode,String message){
        return new Result(stateCode,message);
    }

//    public Result setStateCode(int stateCode){
//        this.stateCode = stateCode;
//        return this;
//    }
//
//    public Result setMessage(String message){
//        this.message = message;
//        return this;
//    }

    public Result setData(String name,Object object){
        this.data.put(name,object);
        return this;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "stateCode=" + stateCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
