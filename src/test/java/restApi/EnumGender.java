package restApi;

import lombok.Getter;

@Getter
public enum EnumGender {
    MALE("male"),
    FEMALE("female"),
    NO_GENDER("without gender");

    private final String gender;

    EnumGender(String gender) {
        this.gender = gender;
    }

}
