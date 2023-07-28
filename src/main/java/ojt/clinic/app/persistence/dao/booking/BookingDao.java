package ojt.clinic.app.persistence.dao.booking;

import java.util.List;

import ojt.clinic.app.persistence.entity.Booking;

/**
 * <h2>BookingDao Interface</h2>
 * <p>
 * Process for Displaying BookingDao
 * </p>
 * 
 * @author ZawLwinTun, NweNi Aung
 *
 */
public interface BookingDao {
    /**
     * <h2>dbAddBooking</h2>
     * <p>
     * Add Booking
     * </p>
     *
     * @param booking Booking
     * @return void
     */
    public void dbAddBooking(Booking booking);

    /**
     * <h2>dbListBookings</h2>
     * <p>
     * List Today Booking
     * </p>
     *
     * @return List<Booking>
     */
    public List<Booking> dbListBookings();

    /**
     * <h2>dbListAllBookings</h2>
     * <p>
     * List All Booking
     * </p>
     *
     * @return List<Booking>
     */
    public List<Booking> dbListAllBookings();

    /**
     * <h2>dbSearchListBookings</h2>
     * <p>
     * Search Today Booking
     * </p>
     *
     * @param search String
     * @return List<Booking>
     */
    public List<Booking> dbSearchListBookings(String search);

    /**
     * <h2>dbSearchListAllBookings</h2>
     * <p>
     * Search All Booking
     * </p>
     *
     * @param search String
     * @return List<Booking>
     */
    public List<Booking> dbSearchListAllBookings(String search);

    /**
     * <h2>dbGetBookingById</h2>
     * <p>
     * Get Booking By ID
     * </p>
     *
     * @param id int
     * @return Booking
     */
    public Booking dbGetBookingById(int id);

    /**
     * <h2>dbUpdateBooking</h2>
     * <p>
     * Update Booking
     * </p>
     *
     * @param booking Booking
     * @return void
     */
    public void dbUpdateBooking(Booking booking);
}