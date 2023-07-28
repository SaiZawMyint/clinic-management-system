package ojt.clinic.app.persistence.dao.booking.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ojt.clinic.app.persistence.dao.booking.BookingDao;
import ojt.clinic.app.persistence.entity.Booking;

/**
 * <h2>BookingDaoImpl Class</h2>
 * <p>
 * Process for Displaying BookingDaoImpl
 * </p>
 * 
 * @author ZawLwinTun, NweNi Aung
 *
 */
@Repository
public class BookingDaoImpl implements BookingDao {
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * <h2>SELECT_ALL_BOOKING_HQL</h2>
     * <p>
     * SELECT_ALL_BOOKING_HQL
     * </p>
     */
    private static final String SELECT_ALL_BOOKING_HQL = "SELECT b FROM Booking b";
    /**
     * <h2>SELECT_BOOKING_HQL</h2>
     * <p>
     * SELECT_BOOKING_HQL
     * </p>
     */
    private static final String SELECT_BOOKING_HQL = "SELECT b FROM Booking b WHERE b.bookingDate = :date";
    /**
     * <h2>SEARCH_BOOKING_HQL</h2>
     * <p>
     * SEARCH_BOOKING_HQL
     * </p>
     */
    private static final String SEARCH_BOOKING_HQL = "SELECT b FROM Booking b WHERE (b.patientName like :search AND b.bookingDate = :today) OR (b.bookingDate like :search AND b.bookingDate = :today) OR (b.status like :search AND b.bookingDate = :today) OR (b.rejectMsg like :search AND b.bookingDate = :today) OR (b.contactPhone like :search AND b.bookingDate = :today) OR (b.patientGender  like :search AND b.bookingDate = :today)";
    /**
     * <h2>SEARCH_ALL_BOOKING_HQL</h2>
     * <p>
     * SEARCH_ALL_BOOKING_HQL
     * </p>
     */
    private static final String SEARCH_ALL_BOOKING_HQL = "SELECT b FROM Booking b WHERE b.patientName like :search OR b.bookingDate like :search OR b.status like :search OR b.rejectMsg like :search OR b.contactPhone like :search OR b.patientGender  like :search";

    /**
     * <h2>dbAddBooking</h2>
     * <p>
     * Add Booking
     * </p>
     * 
     * @param booking Booking
     */
    @Override
    public void dbAddBooking(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(booking);
    }

    /**
     * <h2>dbListBookings</h2>
     * <p>
     * List Today Booking
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Booking> dbListBookings() {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_BOOKING_HQL)
                .setParameter("date", LocalDate.now()).list();
    }

    /**
     * <h2>dbListAllBookings</h2>
     * <p>
     * List All Booking
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Booking> dbListAllBookings() {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_ALL_BOOKING_HQL).list();
    }

    /**
     * <h2>dbSearchListBookings</h2>
     * <p>
     * Search Today Booking
     * </p>
     * 
     * @param search String
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Booking> dbSearchListBookings(String search) {
        return this.sessionFactory.getCurrentSession().createQuery(SEARCH_BOOKING_HQL)
                .setParameter("search", "%" + search + "%").setParameter("today", LocalDate.now()).list();
    }

    /**
     * <h2>dbSearchListAllBookings</h2>
     * <p>
     * Search All Booking
     * </p>
     * 
     * @param search String
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Booking> dbSearchListAllBookings(String search) {
        return this.sessionFactory.getCurrentSession().createQuery(SEARCH_ALL_BOOKING_HQL)
                .setParameter("search", "%" + search + "%").list();
    }

    /**
     * <h2>dbGetBookingById</h2>
     * <p>
     * Get Booking By ID
     * </p>
     * 
     * @param id int
     * @return
     */
    @SuppressWarnings("removal")
    @Override
    public Booking dbGetBookingById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Booking booking = (Booking) session.get(Booking.class, new Integer(id));
        return booking;
    }

    /**
     * <h2>dbUpdateBooking</h2>
     * <p>
     * Update Booking
     * </p>
     * 
     * @param booking Booking
     */
    @Override
    public void dbUpdateBooking(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        session.update(booking);
    }
}