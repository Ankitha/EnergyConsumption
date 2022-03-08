package com.zenhomes.Consumption.exception;

import javax.ws.rs.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ConsumptionControllerAdvice {

    @ExceptionHandler({ConsumptionDataNotFound.class})
    public ResponseEntity<ErrorDetail> handleConsumptionDataNotFoundException(ConsumptionDataNotFound ex)
    {
        ErrorDetail errorDetail=new ErrorDetail();
        errorDetail.setErrorMessage(ex.getMessage());
        errorDetail.setErrorCode(Response.Status.NOT_FOUND.getStatusCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler({ConsumptionBadRequest.class})
    public ResponseEntity<ErrorDetail> handleConsumptionBadRequestException(ConsumptionBadRequest ex)
    {
        ErrorDetail errorDetail=new ErrorDetail();
        errorDetail.setErrorMessage(ex.getMessage());
        errorDetail.setErrorCode(Response.Status.BAD_REQUEST.getStatusCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
    }

    @ExceptionHandler({ConsumptionInternalServerError.class})
    public ResponseEntity<ErrorDetail> handleConsumptionInternalServerError(ConsumptionInternalServerError ex)
    {
        ErrorDetail errorDetail=new ErrorDetail();
        errorDetail.setErrorMessage(ex.getMessage());
        errorDetail.setErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
    }

}
