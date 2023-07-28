package ojt.clinic.app.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * <h2> MultiplePhoneNumber Class</h2>
 * <p>
 * Process for Displaying MultiplePhoneNumber
 * </p>
 * 
 * @author SaiZawMyint
 *
 */
@Documented
@Constraint(validatedBy = {PhoneNumberValidator.class})
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiplePhoneNumber {
	/**
	 * <h2> message</h2>
	 * <p>
	 * Message for validation.
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	String message() default "Invalid Phone Number!";
	/**
	 * <h2> groups</h2>
	 * <p>
	 * Group of called class.
	 * </p>
	 *
	 * @return
	 * @return Class<?>[]
	 */
	Class<?>[] groups() default{};
	/**
	 * <h2> payload</h2>
	 * <p>
	 * Represented the payload.
	 * </p>
	 *
	 * @return
	 * @return Class<? extends Payload>[]
	 */
	Class<? extends Payload>[] payload() default{};
}
