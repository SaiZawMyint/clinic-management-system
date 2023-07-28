package ojt.clinic.app.bl.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.clinic.app.persistence.entity.PatientInfo;
import ojt.clinic.app.persistence.entity.User;

/**
 * <h2>UserDTO Class</h2>
 * <p>
 * Process for Displaying UserDTO
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    private Integer userId;
    /**
     * <h2>doctorPhoto</h2>
     * <p>
     * doctorPhoto
     * </p>
     */
    private String doctorPhoto;
    /**
     * <h2>userName</h2>
     * <p>
     * userName
     * </p>
     */
    private String userName;
    /**
     * <h2>userPassword</h2>
     * <p>
     * userPassword
     * </p>
     */
    private String userPassword;
    /**
     * <h2>userEmail</h2>
     * <p>
     * userEmail
     * </p>
     */
    private String userEmail;
    /**
     * <h2>doctorDegree</h2>
     * <p>
     * doctorDegree
     * </p>
     */
    private String doctorDegree;
    /**
     * <h2>doctorSpecialization</h2>
     * <p>
     * doctorSpecialization
     * </p>
     */
    private String doctorSpecialization;
    /**
     * <h2>doctorDutyDay</h2>
     * <p>
     * doctorDutyDay
     * </p>
     */
    private String doctorDutyDay;
    /**
     * <h2>doctorDutyTime</h2>
     * <p>
     * doctorDutyTime
     * </p>
     */
    private String doctorDutyTime;
    /**
     * <h2>userPhone</h2>
     * <p>
     * userPhone
     * </p>
     */
    private String userPhone;
    
    private List<PatientInfo> patient;
    /**
     * <h2>Constructor for UserDTO</h2>
     * <p>
     * Constructor for UserDTO
     * </p>
     * 
     * @param user User
     */
    public UserDTO(User user) {
        super();
        this.userId = user.getUserId();
        this.doctorPhoto = user.getDoctorPhoto();
        this.userName = user.getUserName();
        this.userPassword = user.getUserPassword();
        this.userEmail = user.getUserEmail();
        this.doctorDegree = user.getDoctorDegree();
        this.doctorSpecialization = user.getDoctorSpecialization();
        this.doctorDutyDay = user.getDoctorDutyDay();
        this.doctorDutyTime = user.getDoctorDutyTime();
        this.userPhone = user.getUserPhone();
        this.patient = user.getPatient();
    }
}