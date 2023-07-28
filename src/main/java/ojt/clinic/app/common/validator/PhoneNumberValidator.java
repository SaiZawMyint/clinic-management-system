package ojt.clinic.app.common.validator;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <h2>PhoneNumberValidator Class</h2>
 * <p>
 * Process for Displaying PhoneNumberValidator
 * </p>
 * 
 * @author SaiZawMyint
 *
 */
public class PhoneNumberValidator implements ConstraintValidator<MultiplePhoneNumber, String> {

    /**
     * <h2>isValid</h2>
     * <p>
     * is Valid
     * </p>
     * 
     * @param numbers ConstraintValidatorContext
     * @param context String
     */
    @Override
    public boolean isValid(String numbers, ConstraintValidatorContext context) {
        boolean check = true;
        if (numbers.contains(",")) {
            int count = 0;
            for (int i = 0; i < numbers.length(); i++) {
                if (numbers.charAt(i) == ',') {
                    count++;
                }
            }
            ArrayList<String> number = this.splitNumber(numbers);
            if (number.size() > count) {
                for (String s : number) {
                    if (!this.validate(s)) {
                        check = false;
                        break;
                    }
                }
            } else {
                check = false;
            }
        } else {
            check = this.validate(numbers);
        }
        return check;
    }

    /**
     * <h2>splitNumber</h2>
     * <p>
     * 
     * </p>
     *
     * @param numbers String
     * @return ArrayList<String>
     */
    private ArrayList<String> splitNumber(String numbers) {
        ArrayList<String> phone = new ArrayList<String>();
        StringTokenizer token = new StringTokenizer(numbers, ",");
        while (token.hasMoreTokens()) {
            phone.add(token.nextToken().trim());
        }
        return phone;
    }

    /**
     * <h2>validate</h2>
     * <p>
     * Validate
     * </p>
     *
     * @param phone String
     * @return boolean
     */
    private boolean validate(String phone) {
        return phone.matches("(^&|[0-9,]{9,})") && phone.length() > 10 && phone.length() < 12;
    }
}
