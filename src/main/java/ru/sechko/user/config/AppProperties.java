package ru.sechko.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties (prefix = "app")
public class AppProperties {

    private Topic topic = new Topic();

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public static class Topic {
        private String userCreate;
        public String getUserCreate() {
            return userCreate;
        }
        public void setUserCreate(String userCreate) {
            this.userCreate = userCreate;
        }
    }
}
