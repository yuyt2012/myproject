package study.myproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRegisterDTO {

    @NotNull
    private String loginId;
    @NotNull
    private String password;
    @NotNull
    private String username;
    private int age;
    private String city;
    private String street;
    private String zipcode;

    public MemberRegisterDTO(String loginId, String password, String username, int age, String city, String street,
                             String zipcode) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.age = age;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
