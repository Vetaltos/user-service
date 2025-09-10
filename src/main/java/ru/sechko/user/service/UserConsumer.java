package ru.sechko.user.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.sechko.user.model.User;
import ru.sechko.user.model.UserMessage;
import ru.sechko.user.repository.UserRepository;

@Service
public class UserConsumer {

    private final UserRepository userRepository;

    public UserConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "${app.topic.user-create}", groupId = "user-group")
    public void consume(UserMessage userMessage) {
        User user = new User(userMessage.firstName(), userMessage.lastName(), userMessage.email());
        userRepository.save(user);
        System.out.println("Пользователь сохранен в БД: " + user.getFirstName() + " " + user.getLastName()
                + " " + user.getEmail());
    }
}
