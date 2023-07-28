package ojt.clinic.app.web.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.clinic.app.common.validator.MultiplePhoneNumber;

/**
 * <h2> DoctorForm Class</h2>
 * <p>
 * Process for Displaying DoctorForm
 * </p>
 * 
 * @author  Sai Zaw Myint
 *
 */
/**
 * <h2>DoctorForm Class</h2>
 * <p>
 * Process for Displaying DoctorForm
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorForm {
    private Integer userId;
    /**
     * <h2>doctorPhoto</h2>
     * <p>
     * doctorPhoto
     * </p>
     */
    @NotEmpty
    private String doctorPhoto;
    /**
     * <h2>userName</h2>
     * <p>
     * userName
     * </p>
     */
    @NotEmpty
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-zA-Z_ ]*$")
    private String userName;
    /**
     * <h2>userEmail</h2>
     * <p>
     * userEmail
     * </p>
     */
    @NotEmpty
    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String userEmail;
    /**
     * <h2>userPassword</h2>
     * <p>
     * userPassword
     * </p>
     */
    @NotEmpty
    private String userPassword;
    /**
     * <h2>userPhone</h2>
     * <p>
     * userPhone
     * </p>
     */
    @NotEmpty
    @MultiplePhoneNumber
    private String userPhone;
    /**
     * <h2>doctorDegree</h2>
     * <p>
     * doctorDegree
     * </p>
     */
    @NotEmpty
    private String doctorDegree;
    /**
     * <h2>doctorSpecialization</h2>
     * <p>
     * doctorSpecialization
     * </p>
     */
    @NotEmpty
    private String doctorSpecialization;
    /**
     * <h2>doctorDutyDay</h2>
     * <p>
     * doctorDutyDay
     * </p>
     */
    @NotEmpty
    private String doctorDutyDay;
    /**
     * <h2>doctorDutyTime</h2>
     * <p>
     * doctorDutyTime
     * </p>
     */
    @NotEmpty
    private String doctorDutyTime;
}