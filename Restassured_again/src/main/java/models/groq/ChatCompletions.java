package models.groq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatCompletions {
    private String model;

    @JsonProperty(value = "messages")
    private List<Messages> messagesList;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Messages {
        private String role;
        private String content;
    }
}
