package ojt.clinic.app.config;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ojt.clinic.app.common.Constants;
import ojt.clinic.app.persistence.dao.user.UserDao;
import ojt.clinic.app.persistence.entity.User;

/**
 * <h2>DeploymentListener Class</h2>
 * <p>
 * Process for Displaying DeploymentListener
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Component
public class DeploymentListener {
    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>addInitialData</h2>
     * <p>
     * Add Initial Data
     * </p>
     *
     * @return void
     */
    @PostConstruct
    public void addInitialData() {
        Date date = new Date();
        if (this.userDao.dbListUsers(Constants.ADMIN).size() <= 0) {
            User admin = new User();
            admin.setCreatedAt(date);
            admin.setDelFlag(0);
            admin.setUpdatedAt(date);
            admin.setUserEmail("admin@gmail.com");
            admin.setUserName("Admin");
            admin.setUserPassword(passwordEncoder.encode("Sider123$"));
            admin.setUserPhone("09123456789");
            admin.setUserType(Constants.ADMIN);
            this.userDao.dbAddUser(admin);
        }
    }
}