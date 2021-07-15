
package com.tree.demo.util;

import java.io.Serializable;

public class ListResult<T> implements Serializable {
    private static final long serialVersionUID = -3755143195269176337L;
    private int status;
    private T data;
    private String message;

    public ListResult() {
    }

    public ListResult(int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
