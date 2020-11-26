package cn.app.source.util;

/**
 *  @author: haodongdong
 *  @Date: 2020/9/4 11:20
 *  @Description: 自定义异常
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 1L;

    private ResultCode resultCode = ResultCode.FAIL;

    public AppException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public AppException(String msg) {
        super(msg);
        resultCode.setMessage(msg);
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
