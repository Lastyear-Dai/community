package xyz.lastyear.community.exceptionError;

public class myExceptionError extends RuntimeException{
    private String message;
    private Integer code;
    public myExceptionError(ExceptionCode Code){
        this.message=Code.getMessage();
        this.code=Code.getCode();
    }


    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
