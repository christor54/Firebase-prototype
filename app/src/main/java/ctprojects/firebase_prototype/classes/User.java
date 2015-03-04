package ctprojects.firebase_prototype.classes;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by christophe on 3/3/2015.
 */
public class User {

    @Getter
    @Setter
    private int birthYear;

    @Getter @Setter
    private String fullName;

    public User() {}

    public User(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

}
