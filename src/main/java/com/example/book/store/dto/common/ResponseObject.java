package com.example.book.store.dto.common;

import com.example.book.store.constants.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ResponseObject <DataType>{

    private DataType data;
    private String code;
    private Map<String, String> errors;
    private boolean success;
    private String message;
    private HttpStatus status;

    public ResponseObject(DataType data,
                          String code,
                          boolean success,
                          String message,
                          HttpStatus httpStatus){
        this.data = data;
        this.code = code;
        this.success = success;
        this.message = message;
        this.status = httpStatus;
    }

    public static <DataType> ResponseObject<DataType> success(DataType data){
        return new ResponseObject<>(data, Constant.SUCCESS, true, Constant.SUCCESS, HttpStatus.OK);
    }

    public static <DataType> ResponseObject<DataType> failed(String message, HttpStatus status){
        return new ResponseObject<>(null, Constant.FAILED, false, Constant.FAILED, status);
    }


}
