package ojt.clinic.app.bl.service.booking.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ojt.clinic.app.bl.dto.BookingDTO;
import ojt.clinic.app.bl.service.booking.BookingService;
import ojt.clinic.app.common.Constants;
import ojt.clinic.app.persistence.dao.booking.BookingDao;
import ojt.clinic.app.persistence.entity.Booking;
import ojt.clinic.app.web.form.BookingForm;

/**
 * <h2>BookingServiceImpl Class</h2>
 * <p>
 * Process for Displaying BookingServiceImpl
 * </p>
 * 
 * @author ZawLwinTun, NweNi Aung
 *
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    /**
     * <h2>bookingDao</h2>
     * <p>
     * bookingDao
     * </p>
     */
    @Autowired
    private BookingDao bookingDao;

    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2>doAddBooking</h2>
     * <p>
     * Add Booking
     * </p>
     * 
     * @param bookingForm BookingForm
     */
    @Override
    public void doAddBooking(BookingForm bookingForm) {
        Booking booking = new Booking(bookingForm);
        booking.setBookingDate(LocalDate.now());
        LocalTime time = LocalTime.now();
        DateTimeFormatter localTime = new DateTimeFormatterBuilder().appendPattern("K:mm a").toFormatter(Locale.US);
        booking.setBookingTime(time.format(localTime));
        booking.setStatus("Pending");
        bookingDao.dbAddBooking(booking);
    }

    /**
     * <h2>doListBookings</h2>
     * <p>
     * List Today Booking
     * </p>
     * 
     * @return List<BookingDTO>
     */
    @Override
    public List<BookingDTO> doListBookings() {
        List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
        for (Booking booking : this.bookingDao.dbListBookings()) {
            BookingDTO bookingDto = new BookingDTO(booking);
            bookingList.add(bookingDto);
        }
        return bookingList;
    }

    /**
     * <h2>doListAllBookings</h2>
     * <p>
     * List All Booking
     * </p>
     * 
     * @return List<BookingDTO>
     */
    @Override
    public List<BookingDTO> doListAllBookings() {
        List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
        for (Booking booking : this.bookingDao.dbListAllBookings()) {
            BookingDTO bookingDto = new BookingDTO(booking);
            bookingList.add(bookingDto);
        }
        return bookingList;
    }

    /**
     * <h2>doSearchListBookings</h2>
     * <p>
     * Search Today Booking
     * </p>
     * 
     * @param search String
     * @return List<BookingDTO>
     */
    @Override
    public List<BookingDTO> doSearchListBookings(String search) {
        List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
        for (Booking booking : this.bookingDao.dbSearchListBookings(search)) {
            BookingDTO bookingDto = new BookingDTO(booking);
            bookingList.add(bookingDto);
        }
        return bookingList;
    }

    /**
     * <h2>doSearchListAllBookings</h2>
     * <p>
     * Search All Booking
     * </p>
     * 
     * @param search String
     * @return List<BookingDTO>
     */
    @Override
    public List<BookingDTO> doSearchListAllBookings(String search) {
        List<BookingDTO> bookingList = new ArrayList<BookingDTO>();
        for (Booking booking : this.bookingDao.dbSearchListAllBookings(search)) {
            BookingDTO bookingDto = new BookingDTO(booking);
            bookingList.add(bookingDto);
        }
        return bookingList;
    }

    /**
     * <h2>doGetBookingById</h2>
     * <p>
     * Get Booking By ID
     * </p>
     * 
     * @param id int
     * @return List<BookingDTO>
     */
    @Override
    public BookingDTO doGetBookingById(int id) {
        Booking booking = this.bookingDao.dbGetBookingById(id);
        BookingDTO bookingForm = booking != null ? new BookingDTO(booking) : null;
        return bookingForm;
    }

    /**
     * <h2>doUpdateBooking</h2>
     * <p>
     * Update Booking
     * </p>
     * 
     * @param bookingForm BookingForm
     */
    @Override
    public void doUpdateBooking(BookingForm bookingForm) {
        Booking booking = new Booking(bookingForm);
        int userId = (int) session.getAttribute("loggedInId");
        Date a = new Date();
        booking.setUpdatedUserId(userId);
        booking.setUpdatedAt(a);
        bookingDao.dbUpdateBooking(booking);
    }

    /**
     * <h2>doDownlaodBooking</h2>
     * <p>
     * Download All Booking
     * </p>
     * 
     * @param response HttpServletResponse
     * @throws IOException
     */
    @Override
    public void doDownlaodBooking(HttpServletResponse response) throws IOException {
        String filename = Constants.BOOKING;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Booking");
        CreationHelper creationHelper = workbook.getCreationHelper();
        Row row = sheet.createRow(0);
        for (int i = 0; i < Constants.EXCEL_DOWNLOAD_BOOKING.size(); i++) {
            row.createCell(i).setCellValue(Constants.EXCEL_DOWNLOAD_BOOKING.get(i));
        }
        Row datarow;
        int rowCount = 1;
        List<Booking> bookinglist = bookingDao.dbListAllBookings();
        for (Booking b : bookinglist) {
            CellStyle style = workbook.createCellStyle();
            style.setDataFormat(creationHelper.createDataFormat().getFormat(Constants.DATE_FORMAT));
            datarow = sheet.createRow(rowCount++);
            try {
                datarow.createCell(0).setCellValue(b.getBookingId());
                datarow.createCell(1).setCellValue(b.getPatientName());
                datarow.createCell(2).setCellValue(b.getContactPhone());
                datarow.createCell(3).setCellValue(b.getPatientGender());
                datarow.createCell(4).setCellValue(b.getBookingDate().toString());
                datarow.createCell(5).setCellValue(b.getBookingTime());
                datarow.createCell(6).setCellValue(b.getUserId());
                datarow.createCell(7).setCellValue(b.getStatus());
                datarow.createCell(8).setCellValue(b.getRejectMsg());
                Cell cell9 = datarow.createCell(9);
                cell9.setCellValue(b.getCreatedAt());
                cell9.setCellStyle(style);
                datarow.createCell(10).setCellValue(b.getUpdatedUserId());
                Cell cell11 = datarow.createCell(11);
                cell11.setCellValue(b.getUpdatedAt());
                cell11.setCellStyle(style);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException thrown!");
            }
        }
        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workbook.close();
            IOUtils.closeQuietly(response.getOutputStream());
        }
    }
}