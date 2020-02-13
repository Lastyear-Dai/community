package xyz.lastyear.community.exceptionError;

public enum ExceptionCode implements IExceptionCode{
    QUESTION_NOT_FOUND("问题不存在，别处去找找~~~");
    private String message;
@Override
    public String getMessage() {
        return message;
    }

    ExceptionCode(String message) {
        this.message = message;
    }
}
