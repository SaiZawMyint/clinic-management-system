package ojt.clinic.app.web.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>PatientMedicalRecordForm Class</h2>
 * <p>
 * Process for Displaying PatientMedicalRecordForm
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientMedicalRecordForm {
    /**
     * <h2>patientMedicalRecordId</h2>
     * <p>
     * patientMedicalRecordId
     * </p>
     */
    private Integer patientMedicalRecordId;
    /**
     * <h2>patientId</h2>
     * <p>
     * patientId
     * </p>
     */
    private Integer patientId;
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    private Integer userId;
    /**
     * <h2>medicalRecord</h2>
     * <p>
     * medicalRecord
     * </p>
     */
    @NotEmpty
    private String medicalRecord;
    /**
     * <h2>medicine</h2>
     * <p>
     * medicine
     * </p>
     */
    @NotEmpty
    private String medicine;
    /**
     * <h2>cost</h2>
     * <p>
     * cost
     * </p>
     */
    @NotNull
    @Min(value = 0)
    private int cost;
    /**
     * <h2>remark</h2>
     * <p>
     * remark
     * </p>
     */
    private String remark;
}