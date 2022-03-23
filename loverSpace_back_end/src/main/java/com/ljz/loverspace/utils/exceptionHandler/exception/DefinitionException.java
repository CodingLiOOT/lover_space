package com.ljz.loverspace.utils.exceptionHandler.exception;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefinitionException extends RuntimeException {
    @NonNull
    private Integer code;
    @NonNull
    private String message;

    public DefinitionException(ErrorEnum item) {
        this.code = item.getCode();
        this.message = item.getMessage();
    }
}
