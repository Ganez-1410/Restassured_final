package models.reqres;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
