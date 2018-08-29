package cn.kgc.itrip.exception;

/**
 * @Auther: L.Y.Huang
 * @Date: Creaeted in  11:44 2018/8/29
 */
public class ItripException extends Exception {
    private String errorCode;

    public ItripException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ItripException(String 用户不存在, String authUserNotFound) {
    }

    public String getErrorCode() {
        return errorCode;
    }
}
