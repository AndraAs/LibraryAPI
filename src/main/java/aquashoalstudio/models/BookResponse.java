package aquashoalstudio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    @JsonProperty("book_name")
    @JsonAlias({"Name", "book_name"}) // Handles both endpoints
    private String bookName;

    @JsonProperty("isbn")
    @JsonAlias({"Isbn", "isbn"})
    private String isbn;

    @JsonProperty("aisle")
    @JsonAlias({"Aisle", "aisle"})
    private String aisle;

    @JsonProperty("author")
    private String author;
}