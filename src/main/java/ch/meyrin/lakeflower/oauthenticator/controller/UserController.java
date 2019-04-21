package ch.meyrin.lakeflower.oauthenticator.controller;

import ch.meyrin.lakeflower.oauthenticator.controller.model.RegistrationForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    private final transient UserDetailsManager userDetailsManager;

    public UserController(@Qualifier("OAuthUserDetailsService") UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }


    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@Valid @RequestBody final RegistrationForm registrationForm,
                             final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getAllErrors().stream()
                    .map(objectError -> (FieldError) objectError)
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return new ResponseEntity<Map>(errors, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @GetMapping("/checkUser/{username}")
    public boolean isUsernameAvailable(@PathVariable String username) {
        return !userDetailsManager.userExists(username);
    }
}
