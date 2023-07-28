package ojt.clinic.app.bl.service.patientmedicalrecord;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import ojt.clinic.app.bl.dto.PatientMedicalRecordDTO;
import ojt.clinic.app.web.form.PatientMedicalRecordForm;

/**
 * <h2>PatientMedicalRecordService Interface</h2>
 * <p>
 * Process for Displaying PatientMedicalRecordService
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface PatientMedicalRecordService {
    /**
     * <h2>doAddPatientMedicalRecord</h2>
     * <p>
     * Add PatientMedicalRecord
     * </p>
     *
     * @param patientMedicalRecordFrom PatientMedicalRecordForm
     * @return void
     */
    public void doAddPatientMedicalRecord(PatientMedicalRecordForm patientMedicalRecordFrom);

    /**
     * <h2>doListPatientMedicalRecords</h2>
     * <p>
     * List PatientMedicalRecord
     * </p>
     *
     * @return List<PatientMedicalRecordDTO>
     */
    public List<PatientMedicalRecordDTO> doListPatientMedicalRecords();

    /**
     * <h2>doListPatientMedicalRecordsByPid</h2>
     * <p>
     * List PatientMedicalRecord By Patient ID
     * </p>
     *
     * @param id int
     * @return List<PatientMedicalRecordDTO>
     */
    public List<PatientMedicalRecordDTO> doListPatientMedicalRecordsByPid(int id);

    /**
     * <h2>doSearchListPatientMedicalRecords</h2>
     * <p>
     * Search PatientMedicalRecord With Patient ID
     * </p>
     *
     * @param id     int
     * @param record String
     * @return List<PatientMedicalRecordDTO>
     */
    public List<PatientMedicalRecordDTO> doSearchListPatientMedicalRecords(int id, String record);

    /**
     * <h2>doSearchAllListPatientMedicalRecords</h2>
     * <p>
     * Search All PatientMedicalRecord
     * </p>
     *
     * @param record String
     * @return List<PatientMedicalRecordDTO>
     */
    public List<PatientMedicalRecordDTO> doSearchAllListPatientMedicalRecords(String record);

    /**
     * <h2>doGetPatientMedicalRecordById</h2>
     * <p>
     * Get PatientMedicalRecord By ID
     * </p>
     *
     * @param id int
     * @return PatientMedicalRecordDTO
     */
    public PatientMedicalRecordDTO doGetPatientMedicalRecordById(int id);

    /**
     * <h2>doUpdatePatientMedicalRecord</h2>
     * <p>
     * Update PatientMedicalRecord
     * </p>
     *
     * @param patientMedicalRecordForm PatientMedicalRecordForm
     * @return void
     */
    public void doUpdatePatientMedicalRecord(PatientMedicalRecordForm patientMedicalRecordForm);

    /**
     * <h2>doDeletePatientMedicalRecord</h2>
     * <p>
     * Delete MedicalRecord
     * </p>
     *
     * @param id int
     * @return void
     */
    public void doDeletePatientMedicalRecord(int id);

    /**
     * <h2>doDownloadMedicalRecord</h2>
     * <p>
     * Download MedicalRecord
     * </p>
     *
     * @param response HttpServletResponse
     * @throws IOException
     * @throws NullPointerException
     * @return void
     */
    public void doDownloadMedicalRecord(HttpServletResponse response) throws IOException, NullPointerException;

    /**
     * <h2>doUploadPatientMedicalRecord</h2>
     * <p>
     * Upload PatientMedicalRecord
     * </p>
     *
     * @param file MultipartFile
     * @throws IOException
     * @return String
     */
    public String doUploadPatientMedicalRecord(MultipartFile file) throws IOException;
}