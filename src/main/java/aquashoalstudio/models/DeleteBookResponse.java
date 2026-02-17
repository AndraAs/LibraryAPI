package aquashoalstudio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias; // Add this import
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBookResponse {

    @JsonProperty("msg") // Primary name
    @JsonAlias({"Msg", "msg"}) // Accepted list
    private String msg;
}