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
 * <h2>BookingForm Class</h2>
 * <p>
 * Process for Displaying BookingForm
 * </p>
 * 
 * @author ZawLwinTun, NweNi Aung
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingForm {
    /**
     * <h2>bookingId</h2>
     * <p>
     * bookingId
     * </p>
     */
    private Integer bookingId;
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    private Integer userId;
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
     * <h2>contactPhone</h2>
     * <p>
     * contactPhone
     * </p>
     */
    @NotEmpty
    @MultiplePhoneNumber
    private String contactPhone;
    /**
     * <h2>patientGender</h2>
     * <p>
     * patientGender
     * </p>
     */
    @NotEmpty
    private String patientGender;
    /**
     * <h2>status</h2>
     * <p>
     * status
     * </p>
     */
    private String status;
    /**
     * <h2>rejectMsg</h2>
     * <p>
     * rejectMsg
     * </p>
     */
    private String rejectMsg;
}