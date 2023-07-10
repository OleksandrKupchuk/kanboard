package json.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamsUpdateUser {
    private int id;
    private String role;
}