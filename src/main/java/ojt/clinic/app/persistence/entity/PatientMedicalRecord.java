package ojt.clinic.app.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.clinic.app.web.form.PatientMedicalRecordForm;

/**
 * <h2>PatientMedicalRecord Class</h2>
 * <p>
 * Process for Displaying PatientMedicalRecord
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patient_Medical_Record")
public class PatientMedicalRecord {
    /**
     * <h2>patientMedicalRecordId</h2>
     * <p>
     * patientMedicalRecordId
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_medical_record_id")
    private Integer patientMedicalRecordId;
    /**
     * <h2>patientId</h2>
     * <p>
     * patientId
     * </p>
     */
    @Column(name = "patient_id")
    private Integer patientId;
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    @Column(name = "doctor_id")
    private Integer userId;
    /**
     * <h2>recordDateTime</h2>
     * <p>
     * recordDateTime
     * </p>
     */
    @Column(name = "record_date_time", nullable = false, updatable = false)
    private Date recordDateTime;
    /**
     * <h2>medicalRecord</h2>
     * <p>
     * medicalRecord
     * </p>
     */
    @Column(name = "medical_record")
    private String medicalRecord;
    /**
     * <h2>medicine</h2>
     * <p>
     * medicine
     * </p>
     */
    @Column(name = "medicine")
    private String medicine;
    /**
     * <h2>cost</h2>
     * <p>
     * cost
     * </p>
     */
    @Column(name = "cost")
    private Integer cost;
    /**
     * <h2>remark</h2>
     * <p>
     * remark
     * </p>
     */
    @Column(name = "remark")
    private String remark;
    /**
     * <h2>delFlag</h2>
     * <p>
     * delFlag
     * </p>
     */
    @Column(name = "del_flag")
    private Integer delFlag;
    /**
     * <h2>createdUserId</h2>
     * <p>
     * createdUserId
     * </p>
     */
    @Column(name = "created_user_id", nullable = false, updatable = false)
    private Integer createdUserId;
    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    /**
     * <h2>updatedUserId</h2>
     * <p>
     * updatedUserId
     * </p>
     */
    @Column(name = "updated_user_id")
    private Integer updatedUserId;
    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "updated_at")
    private Date updatedAt;
    /**
     * <h2>deletedUserId</h2>
     * <p>
     * deletedUserId
     * </p>
     */
    @Column(name = "deleted_user_id")
    private Integer deletedUserId;
    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    private Date deletedAt;
    /**
     * <h2>patientInfo</h2>
     * <p>
     * patientInfo
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false, insertable = false, updatable = false)
    private PatientInfo patientInfo;
    /**
     * <h2>doctor</h2>
     * <p>
     * doctor
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false, insertable = false, updatable = false)
    private User doctor;

    /**
     * <h2>Constructor for PatientMedicalRecord</h2>
     * <p>
     * Constructor for PatientMedicalRecord
     * </p>
     * 
     * @param patientMedicalRecordForm PatientMedicalRecordForm
     */
    public PatientMedicalRecord(PatientMedicalRecordForm patientMedicalRecordForm) {
        super();
        this.patientMedicalRecordId = patientMedicalRecordForm.getPatientMedicalRecordId();
        this.patientId = patientMedicalRecordForm.getPatientId();
        this.userId = patientMedicalRecordForm.getUserId();
        this.medicalRecord = patientMedicalRecordForm.getMedicalRecord();
        this.medicine = patientMedicalRecordForm.getMedicine();
        this.cost = patientMedicalRecordForm.getCost();
        this.remark = patientMedicalRecordForm.getRemark();
    }
}