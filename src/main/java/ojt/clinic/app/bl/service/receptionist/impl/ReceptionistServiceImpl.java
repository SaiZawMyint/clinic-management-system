package ojt.clinic.app.bl.service.receptionist.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.bl.service.receptionist.ReceptionistService;
import ojt.clinic.app.common.Constants;
import ojt.clinic.app.persistence.dao.user.UserDao;
import ojt.clinic.app.persistence.entity.User;
import ojt.clinic.app.web.form.ReceptionistForm;

/**
 * <h2>ReceptionistServiceImpl Class</h2>
 * <p>
 * Process for Displaying ReceptionistServiceImpl
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Service
@Transactional
public class ReceptionistServiceImpl implements ReceptionistService {

    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;
    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * <h2>doAddReceptionist</h2>
     * <p>
     * Add Receptionist
     * </p>
     * 
     * @param receptionistForm ReceptionistForm
     */
    @Override
    public void doAddReceptionist(ReceptionistForm receptionistForm) {
        User receptionist = new User(receptionistForm);
        receptionist.setUserType(Constants.RECEPTIONIST);
        receptionist.setDelFlag(0);
        receptionist.setUserPassword(passwordEncoder.encode(receptionistForm.getUserPassword()));
        userDao.dbAddUser(receptionist);
    }

    /**
     * <h2>doListReceptionists</h2>
     * <p>
     * List Receptionist
     * </p>
     * 
     * @param type String
     * @return
     */
    @Override
    public List<UserDTO> doListReceptionists(String type) {
        List<UserDTO> receptionistList = new ArrayList<UserDTO>();
        for (User receptionist : this.userDao.dbListUsers(type)) {
            UserDTO receptionistDto = new UserDTO(receptionist);
            receptionistList.add(receptionistDto);
        }
        return receptionistList;
    }

    /**
     * <h2>doSearchListReceptionists</h2>
     * <p>
     * Search Receptionist
     * </p>
     * 
     * @param search String
     * @param Type   String
     * @return
     */
    @Override
    public List<UserDTO> doSearchListReceptionists(String search, String Type) {
        List<UserDTO> receptionistList = new ArrayList<UserDTO>();
        for (User receptionist : this.userDao.dbSearchListUsers(search, Constants.RECEPTIONIST)) {
            UserDTO receptionistDto = new UserDTO(receptionist);
            receptionistList.add(receptionistDto);
        }
        return receptionistList;
    }

    /**
     * <h2>doGetReceptionistById</h2>
     * <p>
     * Get Receptionist By ID
     * </p>
     * 
     * @param id int
     * @return
     */
    @Override
    public UserDTO doGetReceptionistById(int id) {
        User receptionist = this.userDao.dbGetUserById(id);
        UserDTO receptionistForm = null;
        if(receptionist != null && receptionist.getUserType().equals(Constants.RECEPTIONIST)
        		&& receptionist.getDelFlag() == 0) {
        	receptionistForm =  new UserDTO(receptionist);
		}
        return receptionistForm;
    }

    /**
     * <h2>doUpdateReceptionist</h2>
     * <p>
     * Update Receptionist
     * </p>
     * 
     * @param receptionistForm ReceptionistForm
     */
    @Override
    public void doUpdateReceptionist(ReceptionistForm receptionistForm) {
        User receptionist = new User(receptionistForm);
        Date a = new Date();
        receptionist.setUserType(Constants.RECEPTIONIST);
        receptionist.setDelFlag(0);
        receptionist.setUpdatedAt(a);
        userDao.dbUpdateUser(receptionist);
    }

    /**
     * <h2>doDeleteReceptionist</h2>
     * <p>
     * Delete Receptionist
     * </p>
     * 
     * @param id int
     */
    @Override
    public void doDeleteReceptionist(int id) {
        Date a = new Date();
        userDao.dbGetUserById(id).setDeletedAt(a);
        userDao.dbDeleteUser(id);
    }
}