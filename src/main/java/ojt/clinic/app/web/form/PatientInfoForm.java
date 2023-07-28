package ojt.clinic.app.web.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.clinic.app.common.validator.MultiplePhoneNumber;

/**
 * <h2>PatientInfoForm Class</h2>
 * <p>
 * Process for Displaying PatientInfoForm
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientInfoForm {
    /**
     * <h2>patientId</h2>
     * <p>
     * patientId
     * </p>
     */
    private Integer patientId;
    /**
     * <h2>patientName</h2>
     * <p>
     * patientName
     * </p>
     */
    @NotEmpty
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-zA-Z_ ]*$")
    private String patientName;
    /**
     * <h2>patientAge</h2>
     * <p>
     * patientAge
     * </p>
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 110)
    private Integer patientAge;
    /**
     * <h2>patientGender</h2>
     * <p>
     * patientGender
     * </p>
     */
    @NotEmpty
    private String patientGender;
    /**
     * <h2>patientAddress</h2>
     * <p>
     * patientAddress
     * </p>
     */
    @NotEmpty
    @Size(max = 100)
    private String patientAddress;
    /**
     * <h2>patientPhone</h2>
     * <p>
     * patientPhone
     * </p>
     */
    @NotEmpty
    @MultiplePhoneNumber
    private String patientPhone;
    /**
     * <h2>status</h2>
     * <p>
     * status
     * </p>
     */
    private String status;
    /**
     * <h2>doctorId</h2>
     * <p>
     * doctorId
     * </p>
     */
    @NotNull
    private Integer doctorId;
}