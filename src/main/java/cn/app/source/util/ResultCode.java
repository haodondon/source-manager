package cn.app.source.util;

/**
 * 公共的返回码
 * @author kevinsu
 * @date 2020/04/29
 * 返回码 code：
 *  成功:10000
 *  失败:10001
 */
public enum ResultCode {

    /**
     * SUCCESS
     */
    SUCCESS(true,10000,"操作成功"),

    /**
     * FAIL
     */
    FAIL(false,10001,"操作失败"),

    AGAIN_LOGIN(false,417,"请重新登录"),

    REQUEST_TIME_OUT(false,500,"Request Time Out"),

    REQUEST_PERMISSION_DENIED(false,401,"You do not have permission to request an interface"),

    IS_PAIXIAN(false,444,"手机号已经排线"),
    IS_REGISTER(false,444,"手机号已经注册"),

    ERROR_INTERCEPT(false,403,"交易时间为14:00-21:00");

    //此处扩展业务状态码

    /**
     * 操作是否成功
     */
    public boolean success;
    /**
     * 操作代码
     */
    public Integer code;
    /**
     * 提示信息
     */
    public String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}