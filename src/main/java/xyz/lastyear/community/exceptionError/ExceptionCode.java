package xyz.lastyear.community.exceptionError;

public enum ExceptionCode implements IExceptionCode{
    QUESTION_NOT_FOUND(9999,"问题不存在，别处去找找~~~" ),
    UNCHECKED(9998,"没有选中任何问题或评论"),
    NOT_LOGIN(9997,"未登录无法评论"),
    INSERT_SUCCESS(9996,"评论成功"),
    SYSTEM_ERROR(9995,"服务器冒烟了~~~"),
    INSERT_CODE_ERROR(9994,"评论类型错误或者不存在~~~"),
    INSERT_CODE_NULL(9993,"评论不存在，要不换个试试~~~");
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
