package cn.smlcx.template.bean;

/**
 * Created by lcx on 2017/6/7.
 */

public class HttpResult<T> {
	private int error_code;
	private String reason;
	private T result;

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

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public HttpResult(int error_code) {
		this.error_code = error_code;
	}
}
