package xyz.lastyear.community.exceptionError;

public enum ExceptionCode implements IExceptionCode{
    QUESTION_NOT_FOUND(9999,"问题不存在，别处去找找~~~" ),
    UNCHECKED(9998,"没有选中任何问题或评论"),
    NOT_LOGIN(9997,"未登录无法评论"),
    INSERT_SUCCESS(9996,"评论成功");



    private String message;
    private Integer code;
@Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    ExceptionCode(Integer code, String message) {
        this.message = message;
        this.code=code;
    }
}
