package json.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserResponse {
    private String jsonrpc = "2.0";
    private int id;
    private int result;
}