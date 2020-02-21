package xyz.lastyear.community.dto;

import lombok.Data;
import xyz.lastyear.community.exceptionError.ExceptionCode;

@Data
public class ResultDTO {
private Integer code;
private String message;
public  static ResultDTO error(Integer code,String message){
    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setCode(code);
    resultDTO.setMessage(message);
    return resultDTO;
}

    public static ResultDTO error(ExceptionCode notLogin) {
    return error(notLogin.getCode(),notLogin.getMessage());
    }
}
