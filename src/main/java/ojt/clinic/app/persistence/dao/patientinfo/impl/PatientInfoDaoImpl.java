package ojt.clinic.app.persistence.dao.patientinfo.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ojt.clinic.app.persistence.dao.patientinfo.PatientInfoDao;
import ojt.clinic.app.persistence.entity.PatientInfo;

/**
 * <h2>PatientInfoDaoImpl Class</h2>
 * <p>
 * Process for Displaying PatientInfoDaoImpl
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Repository
public class PatientInfoDaoImpl implements PatientInfoDao {
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * <h2>SELECT_PATIENTINFO_HQL</h2>
     * <p>
     * SELECT_PATIENTINFO_HQL
     * </p>
     */
    private static final String SELECT_PATIENTINFO_HQL = "SELECT p FROM PatientInfo p WHERE p.delFlag = 0 ";
    /**
     * <h2>SEARCH_PATIENTINFO_HQL</h2>
     * <p>
     * SEARCH_PATIENTINFO_HQL
     * </p>
     */
    private static final String SEARCH_PATIENTINFO_HQL = "SELECT p FROM PatientInfo p WHERE (p.patientName like :search AND p.delFlag = 0) OR (p.patientAge = :search AND p.delFlag = 0) OR (p.patientGender like :search AND p.delFlag = 0) OR (p.patientAddress like :search AND p.delFlag = 0) OR (p.patientPhone like :search AND p.delFlag = 0)";
    /**
     * <h2>DELETE_PATIENTINFO_HQL</h2>
     * <p>
     * DELETE_PATIENTINFO_HQL
     * </p>
     */
    private static final String DELETE_PATIENTINFO_HQL = "UPDATE PatientInfo p SET p.delFlag = 1  where p.patientId = :pid";
    /**
     * <h2>SELECT_TODAY_PATIENT_HQL</h2>
     * <p>
     * SELECT_TODAY_PATIENT_HQL
     * </p>
     */
    private static final String SELECT_TODAY_PATIENT_HQL = "SELECT p FROM PatientInfo p WHERE p.delFlag = 0 AND p.today = :today AND p.status = :status";

    /**
     * <h2>UPDATE_PATIENT_STATUS_HQL</h2>
     * <p>
     * UPDATE_PATIENT_STATUS_HQL
     * </p>
     */
    private static final String UPDATE_PATIENT_STATUS_HQL = "UPDATE PatientInfo p SET p.status = 'Finished' WHERE p.delFlag = 0 AND p.patientId = :pid";

    /**
     * <h2>dbAddPatientInfo</h2>
     * <p>
     * Add Patient Info
     * </p>
     * 
     * @param patientInfo PatientInfo
     */
    @Override
    public void dbAddPatientInfo(PatientInfo patientInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(patientInfo);
    }

    /**
     * <h2>dbAddTodayPatient</h2>
     * <p>
     * Add Into Today Patient List
     * </p>
     * 
     * @param today LocalDate
     * @param id    int
     */
    @Override
    public void dbAddTodayPatient(LocalDate today, PatientInfo p) {
    	Session session = sessionFactory.getCurrentSession();
        session.update(p);
    }

    /**
     * <h2>dbListPatientInfos</h2>
     * <p>
     * List Patient Info
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PatientInfo> dbListPatientInfos() {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_PATIENTINFO_HQL).list();
    }

    /**
     * <h2>dbSearchListPatientInfos</h2>
     * <p>
     * Search Patient Info
     * </p>
     * 
     * @param search String
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PatientInfo> dbSearchListPatientInfos(String search) {
        return this.sessionFactory.getCurrentSession().createQuery(SEARCH_PATIENTINFO_HQL)
                .setParameter("search", "%" + search + "%").list();
    }

    /**
     * <h2>dbTodayPatients</h2>
     * <p>
     * List Today Patient
     * </p>
     * 
     * @param today
     * @param status
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PatientInfo> dbTodayPatients(LocalDate today, String status) {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_TODAY_PATIENT_HQL)
                .setParameter("today", today).setParameter("status", status).list();
    }

    /**
     * <h2>dbGetPatientInfoById</h2>
     * <p>
     * Get Patient Info By ID
     * </p>
     * 
     * @param id int
     * @return
     */
    @SuppressWarnings("removal")
    @Override
    public PatientInfo dbGetPatientInfoById(int id) {
        Session session = sessionFactory.getCurrentSession();
        PatientInfo patientInfo = (PatientInfo) session.get(PatientInfo.class, new Integer(id));
        return patientInfo;
    }

    /**
     * <h2>dbChangeStatus</h2>
     * <p>
     * Change Status Of Patient
     * </p>
     * 
     * @param id int
     */
    @Override
    public void dbChangeStatus(int id) {
        sessionFactory.getCurrentSession().createQuery(UPDATE_PATIENT_STATUS_HQL).setParameter("pid", id)
                .executeUpdate();
    }

    /**
     * <h2>dbDeletePatientInfo</h2>
     * <p>
     * Delete Patient Info
     * </p>
     * 
     * @param id int
     */
    @Override
    public void dbDeletePatientInfo(int id) {
        sessionFactory.getCurrentSession().createQuery(DELETE_PATIENTINFO_HQL).setParameter("pid", id).executeUpdate();
    }
}