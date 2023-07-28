package ojt.clinic.app.persistence.dao.user;

import java.util.List;

import ojt.clinic.app.persistence.entity.User;

/**
 * <h2>UserDao Interface</h2>
 * <p>
 * Process for Displaying UserDao
 * </p>
 * 
 * @author ZawLwinTun,Sai Zaw Myint
 *
 */
public interface UserDao {
    /**
     * <h2>dbAddUser</h2>
     * <p>
     * Add User
     * </p>
     *
     * @param user User
     * @return void
     */
    public void dbAddUser(User user);

    /**
     * <h2>dbListUsers</h2>
     * <p>
     * List User
     * </p>
     *
     * @param type String
     * @return List<User>
     */
    public List<User> dbListUsers(String type);

    /**
     * <h2>dbSearchListUsers</h2>
     * <p>
     * Search User
     * </p>
     *
     * @param search String
     * @param type   String
     * @return List<User>
     */
    public List<User> dbSearchListUsers(String search, String type);

    /**
     * <h2>dbGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     *
     * @param id int
     * @return User
     */
    public User dbGetUserById(int id);

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     *
     * @param email String
     * @return User
     */
    public User dbGetUserByEmail(String email);

    /**
     * <h2>dbGetUserCountByEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param id
     * @param email
     * @return int
     */
    public long dbGetUserCountByEmail(int id, String email);

    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     *
     * @param user User
     * @return void
     */
    public void dbUpdateUser(User user);

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     *
     * @param id int
     * @return void
     */
    public void dbDeleteUser(int id);
}