package com.blog.helper;

import com.blog.helper.enums.ResponseStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class ServiceApiResponse<T> implements Serializable {
    private List<T> dataList;
    private ResponseStatus status;
    private boolean hasError;
    private String message;
    private long totalCount;

    public ServiceApiResponse(List<T> dataList, ResponseStatus status) {
        this.dataList = dataList;
        this.status = status;
        this.message = "";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 0;
    }
    public ServiceApiResponse(List<T> dataList,long totalCount, ResponseStatus status) {
        this.dataList = dataList;
        this.status = status;
        this.message = "";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = totalCount;
    }
    public ServiceApiResponse(T data, ResponseStatus status) {
        this.dataList = new ArrayList<T>();
        this.dataList .add(data);
        this.status = status;
        this.message = "ok";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 1;
    }
    public ServiceApiResponse(String message, ResponseStatus status) {
        this.dataList = new ArrayList<T>();
        this.status = status;
        this.message = message;
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 0;
    }
    public ServiceApiResponse(Exception ex) {
        this.dataList = new ArrayList<T>();
        this.status = ResponseStatus.EXCEPTION;
        this.message = ex.getMessage();
        this.hasError = true;
        this.totalCount = 0;
    }

}
