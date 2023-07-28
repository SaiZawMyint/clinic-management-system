package ojt.clinic.app.bl.service.receptionist;

import java.util.List;

import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.web.form.ReceptionistForm;

/**
 * <h2>ReceptionistService Interface</h2>
 * <p>
 * Process for Displaying ReceptionistService
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
public interface ReceptionistService {
    /**
     * <h2>doAddReceptionist</h2>
     * <p>
     * Add Receptionist
     * </p>
     *
     * @param receptionistForm ReceptionistForm
     * @return void
     */
    public void doAddReceptionist(ReceptionistForm receptionistForm);

    /**
     * <h2>doListReceptionists</h2>
     * <p>
     * List Receptionist
     * </p>
     *
     * @param type String
     * @return List<UserDTO>
     */
    public List<UserDTO> doListReceptionists(String type);

    /**
     * <h2>doSearchListReceptionists</h2>
     * <p>
     * Search Receptionist
     * </p>
     *
     * @param search String
     * @param type   String
     * @return List<UserDTO>
     */
    public List<UserDTO> doSearchListReceptionists(String search, String type);

    /**
     * <h2>doGetReceptionistById</h2>
     * <p>
     * Get Receptionist By ID
     * </p>
     *
     * @param id int
     * @return UserDTO
     */
    public UserDTO doGetReceptionistById(int id);

    /**
     * <h2>doUpdateReceptionist</h2>
     * <p>
     * Update Receptionist
     * </p>
     *
     * @param receptionistForm ReceptionistForm
     * @return void
     */
    public void doUpdateReceptionist(ReceptionistForm receptionistForm);

    /**
     * <h2>doDeleteReceptionist</h2>
     * <p>
     * Delete Receptionist
     * </p>
     *
     * @param id int
     * @return void
     */
    public void doDeleteReceptionist(int id);
}