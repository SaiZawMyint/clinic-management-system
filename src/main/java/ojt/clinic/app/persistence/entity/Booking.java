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
import ojt.clinic.app.web.form.BookingForm;

/**
 * <h2>Booking Class</h2>
 * <p>
 * Process for Displaying Booking
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
@Table(name = "Booking")
public class Booking {
    /**
     * <h2>bookingId</h2>
     * <p>
     * bookingId
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;
    /**
     * <h2>patientName</h2>
     * <p>
     * patientName
     * </p>
     */
    @Column(name = "patient_name")
    private String patientName;
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    @Column(name = "doctor_id")
    private Integer userId;
    /**
     * <h2>contactPhone</h2>
     * <p>
     * contactPhone
     * </p>
     */
    @Column(name = "contact_phone")
    private String contactPhone;
    /**
     * <h2>patientGender</h2>
     * <p>
     * patientGender
     * </p>
     */
    @Column(name = "patient_Gender")
    private String patientGender;
    /**
     * <h2>bookingDate</h2>
     * <p>
     * bookingDate
     * </p>
     */
    @Column(name = "booking_date", nullable = false, updatable = false)
    private LocalDate bookingDate;
    /**
     * <h2>bookingTime</h2>
     * <p>
     * bookingTime
     * </p>
     */
    @Column(name = "booking_time", nullable = false, updatable = false)
    private String bookingTime;
    /**
     * <h2>status</h2>
     * <p>
     * status
     * </p>
     */
    @Column(name = "pending_status")
    private String status;
    /**
     * <h2>rejectMsg</h2>
     * <p>
     * rejectMsg
     * </p>
     */
    @Column(name = "reject_msg")
    private String rejectMsg;
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
     * <h2>doctor</h2>
     * <p>
     * doctor
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false, insertable = false, updatable = false)
    private User doctor;

    /**
     * <h2>Constructor for Booking</h2>
     * <p>
     * Constructor for Booking
     * </p>
     * 
     * @param bookingForm BookingForm
     */
    public Booking(BookingForm bookingForm) {
        super();
        this.bookingId = bookingForm.getBookingId();
        this.patientName = bookingForm.getPatientName();
        this.userId = bookingForm.getUserId();
        this.contactPhone = bookingForm.getContactPhone();
        this.patientGender = bookingForm.getPatientGender();
        this.status = bookingForm.getStatus();
        this.rejectMsg = bookingForm.getRejectMsg();
    }
}