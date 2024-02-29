package web.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginForm {
    @NotBlank(message = "Email is mandatory")
    String email;

    @NotBlank(message = "Password is mandatory")
    String password;
}
