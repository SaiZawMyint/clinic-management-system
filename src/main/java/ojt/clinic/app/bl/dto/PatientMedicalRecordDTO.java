package ojt.clinic.app.bl.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.clinic.app.persistence.entity.PatientMedicalRecord;

/**
 * <h2>PatientMedicalRecordDTO Class</h2>
 * <p>
 * Process for Displaying PatientMedicalRecordDTO
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientMedicalRecordDTO {
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
     * <h2>patientName</h2>
     * <p>
     * patientName
     * </p>
     */
    private String patientName;
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    private Integer userId;
    /**
     * <h2>userName</h2>
     * <p>
     * userName
     * </p>
     */
    private String userName;
    /**
     * <h2>recordDateTime</h2>
     * <p>
     * recordDateTime
     * </p>
     */
    private Date recordDateTime;
    /**
     * <h2>medicalRecord</h2>
     * <p>
     * medicalRecord
     * </p>
     */
    private String medicalRecord;
    /**
     * <h2>medicine</h2>
     * <p>
     * medicine
     * </p>
     */
    private String medicine;
    /**
     * <h2>cost</h2>
     * <p>
     * cost
     * </p>
     */
    private Integer cost;
    /**
     * <h2>remark</h2>
     * <p>
     * remark
     * </p>
     */
    private String remark;

    /**
     * <h2>Constructor for PatientMedicalRecordDTO</h2>
     * <p>
     * Constructor for PatientMedicalRecordDTO
     * </p>
     * 
     * @param patientMedicalRecord PatientMedicalRecord
     */
    public PatientMedicalRecordDTO(PatientMedicalRecord patientMedicalRecord) {
        super();
        this.patientMedicalRecordId = patientMedicalRecord.getPatientMedicalRecordId();
        this.patientId = patientMedicalRecord.getPatientId();
        this.patientName = patientMedicalRecord.getPatientInfo().getPatientName();
        this.userId = patientMedicalRecord.getUserId();
        this.userName = patientMedicalRecord.getDoctor().getUserName();
        this.recordDateTime = patientMedicalRecord.getRecordDateTime();
        this.medicalRecord = patientMedicalRecord.getMedicalRecord();
        this.medicine = patientMedicalRecord.getMedicine();
        this.cost = patientMedicalRecord.getCost();
        this.remark = patientMedicalRecord.getRemark();
    }
}