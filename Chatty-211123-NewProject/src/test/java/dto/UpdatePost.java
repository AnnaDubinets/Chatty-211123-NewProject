package dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePost {
    private String title;
    private String description;
    private String body;
}
