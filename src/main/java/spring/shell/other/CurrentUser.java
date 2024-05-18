package spring.shell.other;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUser {
    private String username;
    private boolean loggedIn;
}
