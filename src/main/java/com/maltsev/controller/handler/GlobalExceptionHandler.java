package com.maltsev.controller.handler;

import com.maltsev.exception.NotFoundProductException;
import com.maltsev.model.ExceptionResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.Instant;
import java.util.HashMap;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String COMMON_ERROR_MESSAGE = "Exception has occurred: ";
    private static final String VALIDATION_ERROR_MESSAGE = "Invalid request: ";

    @ApiResponse(responseCode = "400", description = "Bad request. Запрос не удовлетворяет валидационным критериям",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponseDto.class)))
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        var errorMessage = VALIDATION_ERROR_MESSAGE + errors;

        log.error(errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(makeResponseBody(errorMessage));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseDto> handleValidationException(ConstraintViolationException ex) {
        var errorMessage = VALIDATION_ERROR_MESSAGE + ex.getMessage();
        log.error(errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(makeResponseBody(errorMessage));
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponseDto> handleValidationException(ValidationException ex) {
        var errorMessage = VALIDATION_ERROR_MESSAGE + ex.getMessage();
        log.error(errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(makeResponseBody(errorMessage));
    }

    @ExceptionHandler(NotFoundProductException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundException(NotFoundProductException ex) {
        var errorMessage = ex.getMessage();
        log.error(errorMessage);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(makeResponseBody(errorMessage));
    }


    @ApiResponse(responseCode = "500", description = "Internal server error. Неожиданная ошибка, возникшая при работе метода",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponseDto.class)))
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleCommonException(Exception ex) {
        log.error(COMMON_ERROR_MESSAGE, ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(makeResponseBody(ex.getMessage()));
    }

    private ExceptionResponseDto makeResponseBody(String errorMessage) {
        return ExceptionResponseDto.builder()
                .message(errorMessage)
                .timestamp(Instant.now())
                .build();
    }
}
