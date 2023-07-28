package ojt.clinic.app.bl.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.clinic.app.persistence.entity.Booking;

/**
 * <h2> BookingDTO Class</h2>
 * <p>
 * Process for Displaying BookingDTO
 * </p>
 * 
 * @author ZawLwinTun, NweNi Aung
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    /**
     * <h2> bookingId</h2>
     * <p>
     * bookingId
     * </p>
     */
    private Integer bookingId;
    /**
     * <h2> patientName</h2>
     * <p>
     * patientName
     * </p>
     */
    private String patientName;
    /**
     * <h2> userId</h2>
     * <p>
     * userId
     * </p>
     */
    private Integer userId;
    /**
     * <h2> userName</h2>
     * <p>
     * userName
     * </p>
     */
    private String userName;
    /**
     * <h2> contactPhone</h2>
     * <p>
     * contactPhone
     * </p>
     */
    private String contactPhone;
    /**
     * <h2> patientGender</h2>
     * <p>
     * patientGender
     * </p>
     */
    private String patientGender;
    /**
     * <h2> bookingDate</h2>
     * <p>
     * bookingDate
     * </p>
     */
    private LocalDate bookingDate;
    /**
     * <h2> bookingTime</h2>
     * <p>
     * bookingTime
     * </p>
     */
    private String bookingTime;
    /**
     * <h2> status</h2>
     * <p>
     * status
     * </p>
     */
    private String status;
    /**
     * <h2> rejectMsg</h2>
     * <p>
     * rejectMsg
     * </p>
     */
    private String rejectMsg;

    /**
     * <h2> Constructor for BookingDTO </h2>
     * <p>
     * Constructor for BookingDTO
     * </p>
     * @param booking Booking
     */
    public BookingDTO(Booking booking) {
        super();
        this.bookingId = booking.getBookingId();
        this.patientName = booking.getPatientName();
        this.userId = booking.getUserId();
        this.userName = booking.getDoctor().getUserName();
        this.contactPhone = booking.getContactPhone();
        this.patientGender = booking.getPatientGender();
        this.bookingDate = booking.getBookingDate();
        this.bookingTime = booking.getBookingTime();
        this.status = booking.getStatus();
        this.rejectMsg = booking.getRejectMsg();
    }
}