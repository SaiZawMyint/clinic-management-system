package ojt.clinic.app.bl.service.user;

import ojt.clinic.app.persistence.entity.User;

/**
 * <h2>UserService Class</h2>
 * <p>
 * Process for Displaying UserService
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface UserService {
    /**
     * <h2>doGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     *
     * @param email String
     * @return User
     */
    public User doGetUserByEmail(String email);

    /**
     * <h2>doCheckEmail</h2>
     * <p>
     * Check If Email Is Existed
     * </p>
     *
     * @param email String
     * @param id    int
     * @return boolean
     */
    public boolean doCheckEmail(String email, int id);
}