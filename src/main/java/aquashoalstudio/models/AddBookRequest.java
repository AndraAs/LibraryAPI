package aquashoalstudio.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddBookRequest {
    private String name;
    private String isbn;
    private String aisle;
    private String author;

}

