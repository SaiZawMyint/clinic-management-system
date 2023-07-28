package ojt.clinic.app.persistence.dao.patientmedicalrecord;

import java.util.List;

import ojt.clinic.app.persistence.entity.PatientMedicalRecord;

/**
 * <h2>PatientMedicalRecordDao Interface</h2>
 * <p>
 * Process for Displaying PatientMedicalRecordDao
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface PatientMedicalRecordDao {
    /**
     * <h2>dbAddPatientMedicalRecord</h2>
     * <p>
     * Add PatientMedicalRecord
     * </p>
     *
     * @param patientMedicalRecord PatientMedicalRecord
     * @return void
     */
    public void dbAddPatientMedicalRecord(PatientMedicalRecord patientMedicalRecord);

    /**
     * <h2>dbListPatientMedicalRecords</h2>
     * <p>
     * List PatientMedicalRecord
     * </p>
     *
     * @return List<PatientMedicalRecord>
     */
    public List<PatientMedicalRecord> dbListPatientMedicalRecords();

    /**
     * <h2>dbListPatientMedicalRecordsByPid</h2>
     * <p>
     * List PatientMedicalRecord By Patient ID
     * </p>
     *
     * @param id int
     * @return List<PatientMedicalRecord>
     */
    public List<PatientMedicalRecord> dbListPatientMedicalRecordsByPid(int id);

    /**
     * <h2>dbSearchListPatientMedicalRecords</h2>
     * <p>
     * Search PatientMedicalRecord With Patient ID
     * </p>
     *
     * @param id     int
     * @param record String
     * @return List<PatientMedicalRecord>
     */
    public List<PatientMedicalRecord> dbSearchListPatientMedicalRecords(int id, String record);

    /**
     * <h2>dbSearchAllListPatientMedicalRecords</h2>
     * <p>
     * Search All PatientMedicalRecord
     * </p>
     *
     * @param record String
     * @return List<PatientMedicalRecord>
     */
    public List<PatientMedicalRecord> dbSearchAllListPatientMedicalRecords(String record);

    /**
     * <h2>dbGetPatientMedicalRecordById</h2>
     * <p>
     * Get PatientMedicalRecord By ID
     * </p>
     *
     * @param idint
     * @return PatientMedicalRecord
     */
    public PatientMedicalRecord dbGetPatientMedicalRecordById(int id);

    /**
     * <h2>dbUpdatePatientMedicalRecord</h2>
     * <p>
     * Update PatientMedicalRecord
     * </p>
     *
     * @param patientMedicalRecord PatientMedicalRecord
     * @return void
     */
    public void dbUpdatePatientMedicalRecord(PatientMedicalRecord patientMedicalRecord);

    /**
     * <h2>dbDeletePatientMedicalRecord</h2>
     * <p>
     * Delete PatientMedicalRecord
     * </p>
     *
     * @param id int
     * @return void
     */
    public void dbDeletePatientMedicalRecord(int id);
}