package ru.sechko.user.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sechko.user.config.AppProperties;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AppProperties appProperties;

    public UserController(KafkaTemplate<String, Object> kafkaTemplate, AppProperties appProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.appProperties = appProperties;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        var msg = new UserMessage(userCreateRequest.firstName(),
                                  userCreateRequest.lastName()
        );




        kafkaTemplate.send(appProperties.getTopic().getUserCreate(), msg)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        logger.error("Ошибка в отправке сообщения", ex);
                    } else {
                        logger.info("Сообщение отправлено, partition{}, offset{}",
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
