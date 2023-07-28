package ojt.clinic.app.bl.service.patientinfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import ojt.clinic.app.bl.dto.PatientInfoDTO;
import ojt.clinic.app.web.form.PatientInfoForm;

/**
 * <h2>PatientInfoService Interface</h2>
 * <p>
 * Process for Displaying PatientInfoService
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface PatientInfoService {
    /**
     * <h2>doAddPatientInfo</h2>
     * <p>
     * Add Patient info
     * </p>
     *
     * @param patientInfoFrom PatientInfoForm
     * @return void
     */
    public void doAddPatientInfo(PatientInfoForm patientInfoFrom);

    /**
     * <h2>doAddTodayPatient</h2>
     * <p>
     * Add To Today Patient List
     * </p>
     *
     * @param today LocalDate
     * @param id    int
     * @return void
     */
    public void doAddTodayPatient(LocalDate today, PatientInfoForm patient );

    /**
     * <h2>doListPatientInfos</h2>
     * <p>
     * List Patient Info
     * </p>
     *
     * @return List<PatientInfoDTO>
     */
    public List<PatientInfoDTO> doListPatientInfos();

    /**
     * <h2>doSearchListPatientInfos</h2>
     * <p>
     * Search Patient Info
     * </p>
     *
     * @param search String
     * @return List<PatientInfoDTO>
     */
    public List<PatientInfoDTO> doSearchListPatientInfos(String search);

    /**
     * <h2>doTodayPatients</h2>
     * <p>
     * List Today Patient
     * </p>
     *
     * @param today  LocalDate
     * @param status String
     * @return List<PatientInfoDTO>
     */
    public List<PatientInfoDTO> doTodayPatients(LocalDate today, String status);

    /**
     * <h2>doGetPatientInfoById</h2>
     * <p>
     * Get Patient Info By ID
     * </p>
     *
     * @param id int
     * @return PatientInfoDTO
     */
    public PatientInfoDTO doGetPatientInfoById(int id);

    /**
     * <h2>doUpdatePatientInfo</h2>
     * <p>
     * Update Patient Info
     * </p>
     *
     * @param id              int
     * @param patientInfoForm PatientInfoForm
     * @return void
     */
    public void doUpdatePatientInfo(int id, PatientInfoForm patientInfoForm);

    /**
     * <h2>doChangeStatus</h2>
     * <p>
     * Change Status Of Patient
     * </p>
     *
     * @param id int
     * @return void
     */
    public void doChangeStatus(int id);

    /**
     * <h2>doDeletePatientInfo</h2>
     * <p>
     * Delete Patient Info
     * </p>
     *
     * @param id int
     * @return void
     */
    public void doDeletePatientInfo(int id);

    /**
     * <h2>doDownloadPatientInfo</h2>
     * <p>
     * Download Patient Info
     * </p>
     *
     * @param response HttpServletResponse
     * @throws IOException
     * @throws NullPointerException
     * @return void
     */
    public void doDownloadPatientInfo(HttpServletResponse response) throws IOException, NullPointerException;

    /**
     * <h2>doUploadPateintInfo</h2>
     * <p>
     * Upload Patient Info
     * </p>
     *
     * @param file MultipartFile
     * @throws IOException
     * @throws NullPointerException
     * @return String
     */
    public String doUploadPateintInfo(MultipartFile file) throws IOException, NullPointerException;
}