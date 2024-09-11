package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserData {

    private String id;
    private String name;
    private String surname;
    private String birthDate;

}
