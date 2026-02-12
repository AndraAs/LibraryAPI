package aquashoalstudio.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteBookRequest {
    private String ID;
}
