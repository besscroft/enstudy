package dev.heming.enstudy.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @Description 自定义业务异常
 * @Author Bess Croft
 * @Date 2023/8/25 12:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HeMingFileException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    /** 错误码 */
    private Integer code;

    /** 错误提示 */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public HeMingFileException() {
    }

    public HeMingFileException(String message) {
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

    public HeMingFileException(String message, Throwable cause) {
        super(message, cause);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

    public HeMingFileException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public HeMingFileException setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HeMingFileException setMessage(String message) {
        this.message = message;
        return this;
    }

}

