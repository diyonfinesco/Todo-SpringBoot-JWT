package com.diyonfinesco.todo.util;

public class CustomResponseEntity {
    private int status;
    private boolean success;
    private Object result;

    public CustomResponseEntity() {

    }

    public CustomResponseEntity(int status, boolean success, Object result) {
        this.status = status;
        this.success = success;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
