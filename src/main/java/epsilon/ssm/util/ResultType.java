package epsilon.ssm.util;

public enum ResultType {

    SUCCESS(200,"成功");
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
