package omnisphere.microsservices.User.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import omnisphere.microsservices.User.core.enums.ErrorLevel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Table(name = "tb_logs")
public class Log {
    public Log(ErrorLevel level,
               String message,
               String stackTrace,
               String service,
               Throwable exception) {
        this.level = level;
        this.message = message;
        this.stackTrace = stackTrace;
        this.service = service;
        this.exception = exception;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final ErrorLevel level;
    private final String message;
    private final String stackTrace;
    private final String service;
    private final Throwable exception;

    @Setter(AccessLevel.NONE)
    @CreatedDate
    private Date createdAt;
}
