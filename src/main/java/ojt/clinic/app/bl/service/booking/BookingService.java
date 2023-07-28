package ojt.clinic.app.bl.service.booking;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import ojt.clinic.app.bl.dto.BookingDTO;
import ojt.clinic.app.web.form.BookingForm;

/**
 * <h2>BookingService Interface</h2>
 * <p>
 * Process for Displaying BookingService
 * </p>
 * 
 * @author ZawLwinTun, NweNi Aung
 *
 */
public interface BookingService {
    /**
     * <h2>doAddBooking</h2>
     * <p>
     * Add Booking
     * </p>
     *
     * @param bookingFrom BookingForm
     * @return void
     */
    public void doAddBooking(BookingForm bookingFrom);

    /**
     * <h2>doListBookings</h2>
     * <p>
     * List Today Booking
     * </p>
     *
     * @return List<BookingDTO>
     */
    public List<BookingDTO> doListBookings();

    /**
     * <h2>doListAllBookings</h2>
     * <p>
     * List All Booking
     * </p>
     *
     * @return List<BookingDTO>
     */
    public List<BookingDTO> doListAllBookings();

    /**
     * <h2>doSearchListBookings</h2>
     * <p>
     * Search Today Booking
     * </p>
     *
     * @param search String
     * @return List<BookingDTO>
     */
    public List<BookingDTO> doSearchListBookings(String search);

    /**
     * <h2>doSearchListAllBookings</h2>
     * <p>
     * Search All Bookings
     * </p>
     *
     * @param search String
     * @return List<BookingDTO>
     */
    public List<BookingDTO> doSearchListAllBookings(String search);

    /**
     * <h2>doGetBookingById</h2>
     * <p>
     * Get Booking By ID
     * </p>
     *
     * @param id int
     * @return BookingDTO
     */
    public BookingDTO doGetBookingById(int id);

    /**
     * <h2>doUpdateBooking</h2>
     * <p>
     * Update Booking
     * </p>
     *
     * @param bookingForm BookingForm
     * @return void
     */
    public void doUpdateBooking(BookingForm bookingForm);

    /**
     * <h2>doDownlaodBooking</h2>
     * <p>
     * Download Booking
     * </p>
     *
     * @param response HttpServletResponse
     * @throws IOException
     * @return void
     */
    public void doDownlaodBooking(HttpServletResponse response) throws IOException;
}