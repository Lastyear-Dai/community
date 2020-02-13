package xyz.lastyear.community.exceptionError;

public class myExceptionError extends RuntimeException{
    private String message;
    public myExceptionError(ExceptionCode Code){
        this.message=Code.getMessage();
    }



    @Override
    public String getMessage() {
        return message;
    }
}
