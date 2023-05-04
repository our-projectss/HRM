package hrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @Size(min=8, message="")
    private String password;

    @NotNull
    @Size(min=5, message="")
    private String name;

    private String type; // admin, user

    private Long salary;

    private String avatar;   
}
