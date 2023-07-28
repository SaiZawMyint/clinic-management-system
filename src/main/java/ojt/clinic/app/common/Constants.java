package ojt.clinic.app.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Constants Class</h2>
 * <p>
 * Process for Displaying Constants
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public class Constants {
    /**
     * <h2>ADMIN</h2>
     * <p>
     * ADMIN
     * </p>
     */
    public static final String ADMIN = "ROLE_ADMIN";
    /**
     * <h2>DOCTOR</h2>
     * <p>
     * DOCTOR
     * </p>
     */
    public static final String DOCTOR = "ROLE_DOCTOR";
    /**
     * <h2>RECEPTIONIST</h2>
     * <p>
     * RECEPTIONIST
     * </p>
     */
    public static final String RECEPTIONIST = "ROLE_RECEPTIONIST";
    /**
     * <h2>EXCEL_DOWNLOAD_BOOKING</h2>
     * <p>
     * EXCEL_DOWNLOAD_BOOKING
     * </p>
     */
    public static final Map<Integer, String> EXCEL_DOWNLOAD_BOOKING;
    static {
        EXCEL_DOWNLOAD_BOOKING = new HashMap<Integer, String>();
        EXCEL_DOWNLOAD_BOOKING.put(0, "Booking ID");
        EXCEL_DOWNLOAD_BOOKING.put(1, "Patient Name");
        EXCEL_DOWNLOAD_BOOKING.put(2, "Contact Phone");
        EXCEL_DOWNLOAD_BOOKING.put(3, "Patient Gender");
        EXCEL_DOWNLOAD_BOOKING.put(4, "Booking Date");
        EXCEL_DOWNLOAD_BOOKING.put(5, "Booking Time");
        EXCEL_DOWNLOAD_BOOKING.put(6, "Doctor ID");
        EXCEL_DOWNLOAD_BOOKING.put(7, "Status");
        EXCEL_DOWNLOAD_BOOKING.put(8, "Reject Message");
        EXCEL_DOWNLOAD_BOOKING.put(9, "Created At");
        EXCEL_DOWNLOAD_BOOKING.put(10, "Updated User ID");
        EXCEL_DOWNLOAD_BOOKING.put(11, "Updated At");
    }
    /**
     * <h2>EXCEL_DOWNLOAD_PATIENTINFO</h2>
     * <p>
     * EXCEL_DOWNLOAD_PATIENTINFO
     * </p>
     */
    public static final Map<Integer, String> EXCEL_DOWNLOAD_PATIENTINFO;
    static {
        EXCEL_DOWNLOAD_PATIENTINFO = new HashMap<Integer, String>();
        EXCEL_DOWNLOAD_PATIENTINFO.put(0, "Patient ID");
        EXCEL_DOWNLOAD_PATIENTINFO.put(1, "Name");
        EXCEL_DOWNLOAD_PATIENTINFO.put(2, "Age");
        EXCEL_DOWNLOAD_PATIENTINFO.put(3, "Gender");
        EXCEL_DOWNLOAD_PATIENTINFO.put(4, "Address");
        EXCEL_DOWNLOAD_PATIENTINFO.put(5, "Phone");
        EXCEL_DOWNLOAD_PATIENTINFO.put(6, "Date");
        EXCEL_DOWNLOAD_PATIENTINFO.put(7, "Doctor ID");
        EXCEL_DOWNLOAD_PATIENTINFO.put(8, "Status");
        EXCEL_DOWNLOAD_PATIENTINFO.put(9, "Del_Flag");
        EXCEL_DOWNLOAD_PATIENTINFO.put(10, "Created User ID");
        EXCEL_DOWNLOAD_PATIENTINFO.put(11, "Created At");
        EXCEL_DOWNLOAD_PATIENTINFO.put(12, "Updated User ID");
        EXCEL_DOWNLOAD_PATIENTINFO.put(13, "Updated At");
        EXCEL_DOWNLOAD_PATIENTINFO.put(14, "Deleted User ID");
        EXCEL_DOWNLOAD_PATIENTINFO.put(15, "Deleted At");
    }
    /**
     * <h2>EXCEL_DOWNLOAD_MEDICALRECORD</h2>
     * <p>
     * EXCEL_DOWNLOAD_MEDICALRECORD
     * </p>
     */
    public static final Map<Integer, String> EXCEL_DOWNLOAD_MEDICALRECORD;
    static {
        EXCEL_DOWNLOAD_MEDICALRECORD = new HashMap<Integer, String>();
        EXCEL_DOWNLOAD_MEDICALRECORD.put(0, "Medical Record ID");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(1, "Patient ID");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(2, "Doctor ID");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(3, "Record Date");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(4, "Medical Record");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(5, "Medicine");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(6, "Cost");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(7, "Remark");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(8, "Del_Flag");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(9, "Created User ID");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(10, "Created At");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(11, "Updated User ID");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(12, "Updated At");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(13, "Deleted User ID");
        EXCEL_DOWNLOAD_MEDICALRECORD.put(14, "Deleted At");
    }
    /**
     * <h2>DATE_FORMAT</h2>
     * <p>
     * DATE_FORMAT
     * </p>
     */
    public static final String DATE_FORMAT = "mm/dd/yyyy hh:mm:ss";
    /**
     * <h2>BOOKING</h2>
     * <p>
     * BOOKING
     * </p>
     */
    public static final String BOOKING = "booking.xlsx";
    /**
     * <h2>PATIENT</h2>
     * <p>
     * PATIENT
     * </p>
     */
    public static final String PATIENT = "patient.xlsx";
    /**
     * <h2>MEDICALRECORD</h2>
     * <p>
     * MEDICALRECORD
     * </p>
     */
    public static final String MEDICALRECORD = "medical record.xlsx";
}
