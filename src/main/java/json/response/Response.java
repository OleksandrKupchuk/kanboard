package json.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response<T> {
    @Builder.Default
    private String jsonrpc = "2.0";
    private int id;
    private T result;
}