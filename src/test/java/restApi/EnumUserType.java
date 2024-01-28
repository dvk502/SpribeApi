package restApi;

import lombok.Getter;

@Getter
public enum EnumUserType {
    ADMIN("admin"),
    SUPERVISOR("supervisor");

    private final String name;

    EnumUserType(String name) {
        this.name = name;
    }

}
