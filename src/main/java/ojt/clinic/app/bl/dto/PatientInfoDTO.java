package ojt.clinic.app.bl.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.clinic.app.persistence.entity.PatientInfo;

/**
 * <h2> PatientInfoDTO Class</h2>
 * <p>
 * Process for Displaying PatientInfoDTO
 * </p>
 * 
 * @author ZawLwinTun,Sai Zaw Myint
 *
 */
/**
 * <h2>PatientInfoDTO Class</h2>
 * <p>
 * Process for Displaying PatientInfoDTO
 * </p>
 * 
 * @author ZawLwinTun,Sai Zaw Myint
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientInfoDTO {
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
	 * <h2>patientAge</h2>
	 * <p>
	 * patientAge
	 * </p>
	 */
	private Integer patientAge;
	/**
	 * <h2>patientGender</h2>
	 * <p>
	 * patientGender
	 * </p>
	 */
	private String patientGender;
	/**
	 * <h2>patientAddress</h2>
	 * <p>
	 * patientAddress
	 * </p>
	 */
	private String patientAddress;
	/**
	 * <h2>patientPhone</h2>
	 * <p>
	 * patientPhone
	 * </p>
	 */
	private String patientPhone;
	/**
	 * <h2>today</h2>
	 * <p>
	 * today
	 * </p>
	 */
	private LocalDate today;

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
	private Integer doctorId;

	/**
	 * <h2>doctorName</h2>
	 * <p>
	 * doctorName
	 * </p>
	 */
	private String doctorName;

	/**
	 * <h2>Constructor for PatientInfoDTO</h2>
	 * <p>
	 * Constructor for PatientInfoDTO
	 * </p>
	 * 
	 * @param patientInfo PatientInfo
	 */
	public PatientInfoDTO(PatientInfo patientInfo) {
		super();
		this.patientId = patientInfo.getPatientId();
		this.patientName = patientInfo.getPatientName();
		this.patientAge = patientInfo.getPatientAge();
		this.patientGender = patientInfo.getPatientGender();
		this.patientAddress = patientInfo.getPatientAddress();
		this.patientPhone = patientInfo.getPatientPhone();
		this.today = patientInfo.getToday();
		this.status = patientInfo.getStatus();
		this.doctorId = patientInfo.getDoctor().getUserId();
		this.doctorName = patientInfo.getDoctor().getUserName();
	}
}