package study.myproject.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrivacyInfo {

    private String username;
    private int age;
    private String city;
    private String street;
    private String zipcode;

    public PrivacyInfo(String username, int age, String city, String street, String zipcode) {
        this.username = username;
        this.age = age;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
