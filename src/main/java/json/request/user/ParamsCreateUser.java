package json.request.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsCreateUser {
    private String username;
    private String password;
}