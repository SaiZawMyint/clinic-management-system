package ojt.clinic.app.bl.service.doctor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import ojt.clinic.app.bl.dto.PatientInfoDTO;
import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.web.form.DoctorForm;

/**
 * <h2>DoctorService Interface</h2>
 * <p>
 * Process for Displaying DoctorService
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
public interface DoctorService {
    /**
     * <h2>doAddDoctor</h2>
     * <p>
     * Add Doctor
     * </p>
     *
     * @param doctorForm DoctorForm
     * @return void
     */
    public void doAddDoctor(DoctorForm doctorForm);

    /**
     * <h2>doListDoctors</h2>
     * <p>
     * List Doctor
     * </p>
     *
     * @param type String
     * @return List<UserDTO>
     */
    public List<UserDTO> doListDoctors(String type);

    /**
     * <h2>doGetDoctorSchedule</h2>
     * <p>
     * Get Soctor Schedule
     * </p>
     *
     * @param id int
     * @return Map<String,String>
     */
    public Map<Integer, Map<String, String>> doGetDoctorSchedule(Integer id);

    /**
     * <h2>doSearchListDoctors</h2>
     * <p>
     * Search Doctor
     * </p>
     *
     * @param search String
     * @param type   String
     * @return List<UserDTO>
     */
    public List<UserDTO> doSearchListDoctors(String search, String type);

    /**
     * <h2>doGetDoctorById</h2>
     * <p>
     * Get Doctor By ID
     * </p>
     *
     * @param id int
     * @return UserDTO
     */
    public UserDTO doGetDoctorById(int id);

    /**
     * <h2>doUpdateDoctor</h2>
     * <p>
     * Update Doctor
     * </p>
     *
     * @param doctorForm DoctorForm
     * @param id         int
     * @return void
     */
    public void doUpdateDoctor(DoctorForm doctorForm, int id);

    /**
     * <h2>doDeleteDoctor</h2>
     * <p>
     * Delete Doctor
     * </p>
     *
     * @param id int
     * @return void
     */
    public void doDeleteDoctor(int id);

    /**
     * <h2>doAnalyseImageData</h2>
     * <p>
     * Analyse Image Data
     * </p>
     *
     * @param profile    MultipartFile
     * @param path       String
     * @param doctorForm DoctorForm
     * @return HashMap<String,String>
     */
    public HashMap<String, String> doAnalyseImageData(MultipartFile profile, DoctorForm doctorForm);

    /**
     * <h2>doUploadProfile</h2>
     * <p>
     * Upload Profile Image
     * </p>
     *
     * @param profile String
     * @param dir     String
     * @throws IOException
     * @return void
     */
    public void doUploadProfile(String profile, String dir) throws IOException;
    /**
     * <h2>doGetRelatedPatientInfo</h2>
     * <p>
     * doGetRelatedPatientInfo
     * </p>
     * @param status String
     * @param Id Integer
     * @return
     */
    public List<PatientInfoDTO> doGetRelatedPatientInfo(String status,Integer Id);
}