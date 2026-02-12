package aquashoalstudio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookByIDResponse {
    @JsonProperty("book_name") // Contract says: "book_name": "Selenium..."
    private String bookName;

    @JsonProperty("isbn")      // Contract says: "isbn": "a23hd738"
    private String isbn;

    @JsonProperty("aisle")     // Contract says: "aisle": "1223"
    private String aisle;
}