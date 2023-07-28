package ojt.clinic.app.bl.service.user.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ojt.clinic.app.bl.dto.UserAuthorityDetail;
import ojt.clinic.app.bl.service.user.UserService;
import ojt.clinic.app.persistence.dao.user.UserDao;
import ojt.clinic.app.persistence.entity.User;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>doGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     * 
     * @param email String
     */
    @Override
    public User doGetUserByEmail(String email) {
        return this.userDao.dbGetUserByEmail(email);
    }

    /**
     * <h2>doCheckEmail</h2>
     * <p>
     * Check If Email Is Existed
     * </p>
     * 
     * @param email String
     * @param id    int
     */
    @Override
    public boolean doCheckEmail(String email, int id) {
        boolean isExist = false;
        if (id == 0) {
            for (int i = 0; i < userDao.dbListUsers("ROLE_%").size(); i++) {
                if (userDao.dbListUsers("ROLE_%").get(i).getUserEmail().equals(email)) {
                    isExist = true;
                }
            }
        } else {
            long count = (int) userDao.dbGetUserCountByEmail(id, email);
            if (count == 1) {
                isExist = false;
            } else if (count > 1 || count == 0) {
                isExist = true;
            }
        }
        return isExist;
    }

    /**
     * <h2>loadUserByUsername</h2>
     * <p>
     * Load user By UserName(Email)
     * </p>
     * 
     * @param email String
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userInfo = this.userDao.dbGetUserByEmail(email);
        if (userInfo == null) {
            throw new UsernameNotFoundException("Invalid Username or Password!");
        }
        UserDetails user = new UserAuthorityDetail(userInfo.getUserId(), userInfo.getUserEmail(),
                userInfo.getUserPassword(), userInfo.getUserType());
        return user;
    }
}