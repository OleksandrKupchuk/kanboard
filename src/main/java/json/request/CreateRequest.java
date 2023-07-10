package json.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRequest<T> {
    @Builder.Default
    private String jsonrpc = "2.0";
    private String method;
    @Builder.Default
    private String id = "1518863034";
    private T params;
}