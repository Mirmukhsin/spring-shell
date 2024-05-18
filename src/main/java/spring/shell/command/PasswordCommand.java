package spring.shell.command;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import spring.shell.other.CurrentUser;
import spring.shell.service.PasswordService;
import spring.shell.service.UserService;

import java.util.Set;
import java.util.StringJoiner;

@ShellComponent
@RequiredArgsConstructor
public class PasswordCommand {
    private final PasswordService passwordService;
    private final UserService userService;
    private final CurrentUser currentUser = new CurrentUser();

    @ShellMethod(value = "Add password method", key = "add")
    public String addPassword(
            @ShellOption(value = "--ps", help = "[PASSWORD] This can not be empty")@Size(min = 4) String password,
            @ShellOption(value = "--sN", help = "[SERVICE NAME] This argument can not be empty") String service) {

        return passwordService.addPassword(currentUser.getUsername(), password, service);
    }

    @ShellMethod(value = "Get password method", key = "get")
    public String getPassword(
            @ShellOption(value = "--sN", help = "[SERVICE NAME] This argument can not be empty") String service) {

        return passwordService.getPassword(currentUser.getUsername(), service);
    }

    @ShellMethod(value = "Create password with length method", key = "create")
    public String createPasswordWithLength(
            @ShellOption(value = "--ps", help = "[PASSWORD] This argument can not be empty")@Size(min = 8) String password,
            @ShellOption(value = "--sN", help = "[SERVICE NAME] This argument can not be empty") String service) {

        return passwordService.createPasswordWithLength(currentUser.getUsername(), password, service);
    }

    @ShellMethod(value = "Create strong password method", key = "create-strong")
    public String createStrongPassword(
            @ShellOption(value = "--ps", help = "[PASSWORD] This argument can not be empty")@Size(min = 8) String password,
            @ShellOption(value = "--sN", help = "[SERVICE NAME] This argument can not be empty") String service) {
        return passwordService.createStrongPassword(currentUser.getUsername(), password, service);
    }

    @ShellMethod(value = "Register method")
    public String register(
            @ShellOption(value = "--u", help = "[USERNAME] This argument can not be empty") String username,
            @ShellOption(value = "--ps", help = "[PASSWORD] This argument can not be empty")@Size(min = 4) String password) {
        return userService.register(username, password);
    }

    @ShellMethod(value = "Login method")
    public String login(
            @ShellOption(value = "--u", help = "[USERNAME] This argument can not be empty") String username,
            @ShellOption(value = "--ps", help = "[PASSWORD] This argument can not be empty")@Size(min = 4) String password) {

        boolean login = userService.login(username, password);
        if (login) {
            currentUser.setLoggedIn(true);
            currentUser.setUsername(username);
            return "Successfully signed in!";
        }
        return "Bad Credentials!";
    }

    @ShellMethod(value = "Logout method")
    public String logout() {
        currentUser.setLoggedIn(false);
        return "Successfully signed out";
    }

    @ShellMethodAvailability({"add", "get", "create", "createPsd", "logout"})
    public Availability availabilityForLogin() {
        return currentUser.isLoggedIn() ? Availability.available() : Availability.unavailable("=> Login or Register First");
    }

    @ShellMethodAvailability({"login", "register"})
    public Availability availabilityForLogout() {
        return currentUser.isLoggedIn() ? Availability.unavailable(" => Logout First") : Availability.available();
    }

    @ExceptionResolver({ParameterValidationException.class})
    CommandHandlingResult errorHandler(ParameterValidationException e) {
        Set<ConstraintViolation<Object>> constraintViolations = e.getConstraintViolations();
        StringJoiner joiner = new StringJoiner("\n", "", "\n");
        for (ConstraintViolation<Object> violation : constraintViolations) {
            String arg = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            joiner.add(arg + " : " + message);
        }
        return CommandHandlingResult.of(joiner.toString(), 400);
    }
}
