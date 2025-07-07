package exercise.usersystem.validation;

import exercise.usersystem.annotations.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        boolean notNull = email != null;
        boolean notEmpty = !email.isBlank();

        String host = email.substring(email.indexOf("@") + 1);
        String[] hostParts =  host.split("\\.");

        boolean hostRepeats = hostParts[0].equals(hostParts[1]);

        return notNull && notEmpty && !hostRepeats && email.matches("^[a-zA-Z]+[0-9]*([._-][a-zA-Z0-9]+)*@[a-zA-Z]+[0-9]*(?:[._-][a-zA-Z0-9]+)+$");
    }
}
