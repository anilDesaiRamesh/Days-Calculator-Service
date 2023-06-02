package com.qantas.insurance.exception;

import com.qantas.insurance.model.InsuranceErrorResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.qantas.insurance.constants.ErrorMessages.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<InsuranceErrorResponseModel> badRequest(MissingServletRequestParameterException exception) {

        log.error("Exception while validating input - {} " , exception);
        InsuranceErrorResponseModel.Error error = InsuranceErrorResponseModel.Error.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(MISSING_INPUT_FIELDS).build();

        return ResponseEntity.badRequest().body(InsuranceErrorResponseModel.builder()
                .error(error).build());
    }

    @ExceptionHandler(InvalidInputFormatException.class)
    public ResponseEntity<InsuranceErrorResponseModel> badInputFormat(InvalidInputFormatException exception) {
        log.error("Exception while validating input - {} " , exception);
        InsuranceErrorResponseModel.Error error = InsuranceErrorResponseModel.Error.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(INVALID_INPUT_FORMAT).build();

        return ResponseEntity.badRequest().body(InsuranceErrorResponseModel.builder()
                .error(error).build());
    }

    @ExceptionHandler(InvalidInputValException.class)
    public ResponseEntity<InsuranceErrorResponseModel> badInputValFormat(InvalidInputValException exception) {
        log.error("Exception while validating input - {} " , exception);
        InsuranceErrorResponseModel.Error error = InsuranceErrorResponseModel.Error.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(START_DATE_SHOULD_BE_LESSER).build();

        return ResponseEntity.badRequest().body(InsuranceErrorResponseModel.builder()
                .error(error).build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<InsuranceErrorResponseModel> genericException(RuntimeException exception) {
        log.error("Generic Exception - {} " , exception);
        InsuranceErrorResponseModel.Error error = InsuranceErrorResponseModel.Error.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(GENERIC_EXCEPTION).build();

        return ResponseEntity.badRequest().body(InsuranceErrorResponseModel.builder()
                .error(error).build());
    }
}
