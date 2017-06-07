package cn.smlcx.template.bean;

/**
 * Created by lcx on 2017/6/7 0007.
 */

public class HttpResult<T> {
    private int error_code;
    private String reason;
    private PageBean<T> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PageBean<T> getResult() {
        return result;
    }

    public void setResult(PageBean<T> result) {
        this.result = result;
    }
}
