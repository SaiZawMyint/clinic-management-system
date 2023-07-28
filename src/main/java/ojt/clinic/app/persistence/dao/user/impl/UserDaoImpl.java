package ojt.clinic.app.persistence.dao.user.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ojt.clinic.app.common.Constants;
import ojt.clinic.app.persistence.dao.user.UserDao;
import ojt.clinic.app.persistence.entity.User;

/**
 * <h2>UserDaoImpl Class</h2>
 * <p>
 * Process for Displaying UserDaoImpl
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>SELECT_USER_HQL</h2>
     * <p>
     * SELECT_USER_HQL
     * </p>
     */
    private static final String SELECT_USER_HQL = "SELECT u FROM User u WHERE u.delFlag = 0 AND u.userType like :uType order by u.userId desc";
    /**
     * <h2>COUNT_USER_EMAIL_HQL</h2>
     * <p>
     * COUNT_USER_EMAIL_HQL
     * </p>
     */
    private static final String COUNT_USER_EMAIL_HQL = "SELECT count(*) FROM User u WHERE u.delFlag = 0 AND u.userId= :uId  AND u.userEmail = : uEmail";
    /**
     * <h2>SEARCH_RECEPTIONIST_HQL</h2>
     * <p>
     * SEARCH_RECEPTIONIST_HQL
     * </p>
     */
    private static final String SEARCH_RECEPTIONIST_HQL = "SELECT u FROM User u WHERE "
            + "(u.userName like :search AND u.userType= :utype AND u.delFlag=0) "
            + "OR (u.userEmail like :search AND u.userType= :utype AND u.delFlag=0) "
            + "OR (u.userPhone like :search AND u.userType= :utype AND u.delFlag=0) ORDER BY u.userId desc";

    /**
     * <h2>SEARCH_DOCTOR_HQL</h2>
     * <p>
     * SEARCH_DOCTOR_HQL
     * </p>
     */
    private static final String SEARCH_DOCTOR_HQL = "SELECT u FROM User u WHERE "
            + "(u.userName like :search AND u.userType= :utype AND u.delFlag=0) "
            + "OR (u.userEmail like :search AND u.userType= :utype AND u.delFlag=0) "
            + "OR (u.userPhone like :search AND u.userType= :utype AND u.delFlag=0) "
            + "OR (u.doctorDegree like :search AND u.userType= :utype AND u.delFlag=0) "
            + "OR (u.doctorSpecialization like :search AND u.userType= :utype AND u.delFlag=0) "
            + "OR (u.doctorDutyDay like :search AND u.userType= :utype AND u.delFlag=0) "
            + "OR (u.doctorDutyTime like :search AND u.userType= :utype AND u.delFlag=0) ORDER BY u.userId DESC";

    /**
     * <h2>DELETE_USER_HQL</h2>
     * <p>
     * DELETE_USER_HQL
     * </p>
     */
    private static final String DELETE_USER_HQL = "UPDATE User u SET u.delFlag = 1  WHERE u.userId = :uid";

    /**
     * <h2>SELECT_USER_EMAIL_HQL</h2>
     * <p>
     * SELECT_USER_EMAIL_HQL
     * </p>
     */
    private static final String SELECT_USER_EMAIL_HQL = "SELECT u FROM User u WHERE u.delFlag = 0 AND u.userEmail = :uEmail";

    /**
     * <h2>dbAddUser</h2>
     * <p>
     * Add User
     * </p>
     * 
     * @param user User
     */
    @Override
    public void dbAddUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    /**
     * <h2>dbListUsers</h2>
     * <p>
     * List User
     * </p>
     * 
     * @param type String
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> dbListUsers(String type) {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_HQL).setParameter("uType", type).list();
    }

    /**
     * <h2>dbSearchListUsers</h2>
     * <p>
     * Search User
     * </p>
     * 
     * @param search String
     * @param type   String
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> dbSearchListUsers(String search, String type) {
        if (type.equals(Constants.RECEPTIONIST)) {
            return this.sessionFactory.getCurrentSession().createQuery(SEARCH_RECEPTIONIST_HQL)
                    .setParameter("search", "%" + search + "%").setParameter("utype", type).list();
        } else if (type.equals(Constants.DOCTOR)) {
            return this.sessionFactory.getCurrentSession().createQuery(SEARCH_DOCTOR_HQL)
                    .setParameter("search", "%" + search + "%").setParameter("utype", type).list();
        } else {
            return null;
        }
    }

    /**
     * <h2>dbGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     * 
     * @param id int
     * @return
     */
    @SuppressWarnings("removal")
    @Override
    public User dbGetUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, new Integer(id));
        return user;
    }

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     * 
     * @param email String
     * @return
     */
    @Override
    public User dbGetUserByEmail(String email) {
        return (User) this.sessionFactory.getCurrentSession().createQuery(SELECT_USER_EMAIL_HQL)
                .setParameter("uEmail", email).uniqueResult();
    }

    /**
     * <h2>dbGetUserCountByEmail</h2>
     * <p>
     * 
     * </p>
     * 
     * @param id    int
     * @param email Email
     * @return
     */
    @Override
    public long dbGetUserCountByEmail(int id, String email) {
        long count = (long) this.sessionFactory.getCurrentSession().createQuery(COUNT_USER_EMAIL_HQL)
                .setParameter("uId", id).setParameter("uEmail", email).getSingleResult();
        return count;
    }

    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     * 
     * @param user User
     */
    @Override
    public void dbUpdateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     * 
     * @param id int
     */
    @Override
    public void dbDeleteUser(int id) {
        sessionFactory.getCurrentSession().createQuery(DELETE_USER_HQL).setParameter("uid", id).executeUpdate();
    }
}