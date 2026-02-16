package aquashoalstudio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookResponse {

    @JsonProperty("Msg")
    private String msg;

    @JsonProperty("ID")
    private String id;
}