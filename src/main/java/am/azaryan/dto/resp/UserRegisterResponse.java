package am.azaryan.dto.resp;

import am.azaryan.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterResponse {

    private String name;
    private String surname;
    private String email;
    private Role role;

}
