package academy.devdojo.springboot2.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetail {
    protected String title;
    protected int status;
    protected String detail;
    protected String developerMessage;
    protected LocalDateTime timestamp;
}
