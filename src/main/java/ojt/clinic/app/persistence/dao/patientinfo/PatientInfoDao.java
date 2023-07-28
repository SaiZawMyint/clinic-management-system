package ojt.clinic.app.persistence.dao.patientinfo;

import java.time.LocalDate;
import java.util.List;

import ojt.clinic.app.persistence.entity.PatientInfo;

/**
 * <h2>PatientInfoDao Interface</h2>
 * <p>
 * Process for Displaying PatientInfoDao
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface PatientInfoDao {
    /**
     * <h2>dbAddPatientInfo</h2>
     * <p>
     * Add Patient Info
     * </p>
     *
     * @param patientInfo PatientInfo
     * @return void
     */
    public void dbAddPatientInfo(PatientInfo patientInfo);

    /**
     * <h2>dbAddTodayPatient</h2>
     * <p>
     * Add Into Today Patient List
     * </p>
     *
     * @param today LocalDate
     * @param id    int
     * @return void
     */
    public void dbAddTodayPatient(LocalDate today, PatientInfo p);

    /**
     * <h2>dbListPatientInfos</h2>
     * <p>
     * List Patient Info
     * </p>
     *
     * @return List<PatientInfo>
     */
    public List<PatientInfo> dbListPatientInfos();

    /**
     * <h2>dbSearchListPatientInfos</h2>
     * <p>
     * Search Patient Info
     * </p>
     *
     * @param search String
     * @return List<PatientInfo>
     */
    public List<PatientInfo> dbSearchListPatientInfos(String search);

    /**
     * <h2>dbTodayPatients</h2>
     * <p>
     * List Today Patient
     * </p>
     *
     * @param today  LocalDate
     * @param status String
     * @return List<PatientInfo>
     */
    public List<PatientInfo> dbTodayPatients(LocalDate today, String status);

    /**
     * <h2>dbGetPatientInfoById</h2>
     * <p>
     * Update Patient Info
     * </p>
     *
     * @param id int
     * @return PatientInfo
     */
    public PatientInfo dbGetPatientInfoById(int id);

    /**
     * <h2>dbChangeStatus</h2>
     * <p>
     * Change Status Of Patient
     * </p>
     *
     * @param id int
     * @return void
     */
    public void dbChangeStatus(int id);

    /**
     * <h2>dbDeletePatientInfo</h2>
     * <p>
     * Delete Patient Info
     * </p>
     *
     * @param id int
     * @return void
     */
    public void dbDeletePatientInfo(int id);
}