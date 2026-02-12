package aquashoalstudio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class BookByAuthorResponse {
    @JsonProperty("book_name")
    @JsonAlias({"Name", "book_name"})
    private String name;

    @JsonProperty("isbn")
    @JsonAlias({"Isbn", "isbn"})
    private String isbn;

    @JsonProperty("aisle")
    @JsonAlias({"Aisle", "aisle"})
    private String aisle;
}