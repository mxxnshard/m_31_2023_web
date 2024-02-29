package web.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegForm {
    String name;
    String secondName;
    @NotBlank(message = "Email is mandatory")
    @Email
    String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 2)
    String password;
}
