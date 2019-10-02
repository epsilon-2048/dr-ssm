package epsilon.ssm.util;

public enum ResultType {

    SUCCESS(200,"成功"),
    NO_PERMISSION(403,"没有权限"),
    UNAUTHORIZED(401,"未登录"),
    ERROR(500,"未知错误");
    private int code;
    private String msg;

    ResultType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
