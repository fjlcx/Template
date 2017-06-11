package cn.smlcx.template.api;

/**
 * Created by lcx on 2017/6/7.
 */

public class ApiException extends RuntimeException{

	public static final int ERROR_KEY = 10001;//	错误的请求KEY
	public static final int UNABLE_KEY = 10002;//	该KEY无请求权限
	public static final int OVERDUE_KEY = 10003;//    KEY过期
	private static String message;

	public ApiException(int resultCode) {
		this(getApiExceptionMessage(resultCode));
	}

	public ApiException(String detailMessage) {
		super(detailMessage);
		message = detailMessage;
	}

	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
	 * 需要根据错误码对错误信息进行一个转换，在显示给用户
	 * @param code
	 * @return
	 */
	private static String getApiExceptionMessage(int code){
		switch (code) {
			case ERROR_KEY:
				message = "错误的请求KEY";
				break;
			case UNABLE_KEY:
				message = "该KEY无请求权限";
				break;
			case OVERDUE_KEY:
				message = "KEY过期";
				break;
			default:
				message = "未知错误";
		}
		return message;
	}
}
