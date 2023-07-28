package ojt.clinic.app.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.clinic.app.web.form.DoctorForm;
import ojt.clinic.app.web.form.ReceptionistForm;

/**
 * <h2>User Class</h2>
 * <p>
 * Process for Displaying User
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    /**
     * <h2>doctorPhoto</h2>
     * <p>
     * doctorPhoto
     * </p>
     */
    @Column(name = "doctor_photo")
    private String doctorPhoto;
    /**
     * <h2>userName</h2>
     * <p>
     * userName
     * </p>
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * <h2>userEmail</h2>
     * <p>
     * userEmail
     * </p>
     */
    @Column(name = "user_email")
    private String userEmail;
    /**
     * <h2>userPassword</h2>
     * <p>
     * userPassword
     * </p>
     */
    @Column(name = "user_password")
    private String userPassword;
    /**
     * <h2>userType</h2>
     * <p>
     * userType
     * </p>
     */
    @Column(name = "user_type")
    private String userType;
    /**
     * <h2>doctorDegree</h2>
     * <p>
     * doctorDegree
     * </p>
     */
    @Column(name = "doctor_degree")
    private String doctorDegree;
    /**
     * <h2>doctorSpecialization</h2>
     * <p>
     * doctorSpecialization
     * </p>
     */
    @Column(name = "doctor_specialization")
    private String doctorSpecialization;
    /**
     * <h2>doctorDutyDay</h2>
     * <p>
     * doctorDutyDay
     * </p>
     */
    @Column(name = "doctor_duty_day")
    private String doctorDutyDay;
    /**
     * <h2>doctorDutyTime</h2>
     * <p>
     * doctorDutyTime
     * </p>
     */
    @Column(name = "doctor_duty_time")
    private String doctorDutyTime;
    /**
     * <h2>userPhone</h2>
     * <p>
     * userPhone
     * </p>
     */
    @Column(name = "user_phone")
    private String userPhone;
    /**
     * <h2>delFlag</h2>
     * <p>
     * delFlag
     * </p>
     */
    @Column(name = "del_flag")
    private Integer delFlag;
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
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "updated_at")
    private Date updatedAt;
    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    private Date deletedAt;
    @OneToMany(mappedBy="doctor")
    private List<PatientInfo> patient;
    /**
     * <h2>Constructor for User</h2>
     * <p>
     * Constructor for User
     * </p>
     * 
     * @param doctorForm DoctorForm
     */
    public User(DoctorForm doctorForm) {
        super();
        this.userId = doctorForm.getUserId();
        this.doctorPhoto = doctorForm.getDoctorPhoto();
        this.userName = doctorForm.getUserName();
        this.userEmail = doctorForm.getUserEmail();
        this.userPassword = doctorForm.getUserPassword();
        this.doctorDegree = doctorForm.getDoctorDegree();
        this.doctorSpecialization = doctorForm.getDoctorSpecialization();
        this.doctorDutyDay = doctorForm.getDoctorDutyDay();
        this.doctorDutyTime = doctorForm.getDoctorDutyTime();
        this.userPhone = doctorForm.getUserPhone();
    }

    /**
     * <h2>Constructor for User</h2>
     * <p>
     * Constructor for User
     * </p>
     * 
     * @param receptionistForm ReceptionistForm
     */
    public User(ReceptionistForm receptionistForm) {
        super();
        this.userId = receptionistForm.getUserId();
        this.userName = receptionistForm.getUserName();
        this.userEmail = receptionistForm.getUserEmail();
        this.userPassword = receptionistForm.getUserPassword();
        this.userPhone = receptionistForm.getUserPhone();
    }
}