package ge.ecomerce.newecomerce.anotation.ListValidation;

import ge.ecomerce.newecomerce.anotation.ListValidation.EachElementValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EachElementValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListOfStringAnnotation {
    String message() default "Invalid element in the list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
