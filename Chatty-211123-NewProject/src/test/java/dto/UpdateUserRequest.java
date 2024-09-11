package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {

    private String name;
    private String surname;
    private String email;
    private String gender;
    private String tittle;
    private String phone;
    private String birthDate;
}
