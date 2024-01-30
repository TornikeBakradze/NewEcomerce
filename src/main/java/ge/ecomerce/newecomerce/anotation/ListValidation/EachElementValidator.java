package ge.ecomerce.newecomerce.anotation.ListValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EachElementValidator implements ConstraintValidator<ListOfStringAnnotation, List<String>> {

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext context) {
        if (strings == null || strings.isEmpty()) {
            addCustomMessage(context, "List must not be null or empty");
            return false;
        }

        for (String value : strings) {
            if (value == null || value.isEmpty()) {
                addCustomMessage(context, "Element must not be null or empty");
                return false;
            }

            if (!isValidSize(value)) {
                addCustomMessage(context, "Element size must be between 2 and 50");
                return false;
            }

            if (!isValidCharacters(value)) {
                addCustomMessage(context, "Element must contain only letters number and");
                return false;
            }
        }
        return true;
    }

    private void addCustomMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }

    private boolean isValidSize(String value) {
        return value.length() >= 2 && value.length() <= 50;
    }

    private boolean isValidCharacters(String value) {
        return value.matches("^[a-zA-Z0-9, ]*$");
    }
}
