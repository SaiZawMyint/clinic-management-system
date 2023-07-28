package ojt.clinic.app.persistence.entity;

import java.time.LocalDate;
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
import ojt.clinic.app.web.form.PatientInfoForm;

/**
 * <h2>PatientInfo Class</h2>
 * <p>
 * Process for Displaying PatientInfo
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
@Table(name = "Patient_Info")
public class PatientInfo {
	/**
	 * <h2>patientId</h2>
	 * <p>
	 * patientId
	 * </p>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private Integer patientId;
	/**
	 * <h2>patientName</h2>
	 * <p>
	 * patientName
	 * </p>
	 */
	@Column(name = "patient_name")
	private String patientName;
	/**
	 * <h2>patientAge</h2>
	 * <p>
	 * patientAge
	 * </p>
	 */
	@Column(name = "patient_age")
	private Integer patientAge;
	/**
	 * <h2>patientGender</h2>
	 * <p>
	 * patientGender
	 * </p>
	 */
	@Column(name = "patient_gender")
	private String patientGender;
	/**
	 * <h2>patientAddress</h2>
	 * <p>
	 * patientAddress
	 * </p>
	 */
	@Column(name = "patient_address")
	private String patientAddress;
	/**
	 * <h2>patientPhone</h2>
	 * <p>
	 * patientPhone
	 * </p>
	 */
	@Column(name = "patient_phone")
	private String patientPhone;
	/**
	 * <h2>delFlag</h2>
	 * <p>
	 * delFlag
	 * </p>
	 */
	@Column(name = "del_flag")
	private Integer delFlag;
	/**
	 * <h2>today</h2>
	 * <p>
	 * today
	 * </p>
	 */
	@Column(name = "date")
	private LocalDate today;

	/**
	 * <h2>status</h2>
	 * <p>
	 * status
	 * </p>
	 */
	/**
	 * <h2>status</h2>
	 * <p>
	 * status
	 * </p>
	 */
	@Column(name = "status")
	private String status;
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
     * <h2>doctor</h2>
     * <p>
     * doctor
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;
	/**
	 * <h2>Constructor for PatientInfo</h2>
	 * <p>
	 * Constructor for PatientInfo
	 * </p>
	 * 
	 * @param patientInfoForm PatientInfoForm
	 */
	public PatientInfo(PatientInfoForm patientInfoForm) {
		super();
		this.patientId = patientInfoForm.getPatientId();
		this.patientName = patientInfoForm.getPatientName();
		this.patientAge = patientInfoForm.getPatientAge();
		this.patientGender = patientInfoForm.getPatientGender();
		this.patientAddress = patientInfoForm.getPatientAddress();
		this.patientPhone = patientInfoForm.getPatientPhone();
	}
}