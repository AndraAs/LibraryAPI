package aquashoalstudio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor  // Jackson to create the object
@AllArgsConstructor // Lombok's Builder to work with NoArgsConstructor
public class AddBookRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("aisle")
    private String aisle;

    @JsonProperty("author")
    private String author;
}