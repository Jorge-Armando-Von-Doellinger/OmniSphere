package omnisphere.microsservices.User.core.entity;

import lombok.*;
import omnisphere.microsservices.User.core.enums.ErrorLevel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Document(indexName = "logs_microservice-v0.1-rebuild")
public class Log {
    public Log(Object data,
               ErrorLevel level,
               Throwable exception) {
        this.data = data;
        this.level = level;
        this.message = exception.getMessage();
        this.exceptionClass = exception.getClass().getSimpleName();
        this.stackTrace = getStackTraceAsString(exception);
        this.rootCause = getRootCauseMessage(exception);
    }
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private final Object data;
    private final ErrorLevel level;
    private final String message;
    private final String stackTrace;
    private final String exceptionClass;
    private final String rootCause;

    @Setter(AccessLevel.NONE)
    @CreatedDate
    @Field(type = FieldType.Date, name = "@timestamp")
    private Instant createdAt = Instant.now();

    private String getRootCauseMessage(Throwable ex) {
        Throwable cause = ex;
        while (cause.getCause() != null && cause.getCause() != cause) {
            cause = cause.getCause();
        }
        return cause.getMessage() != null ? cause.getMessage() : "N/A";
    }
    private String getStackTraceAsString(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
