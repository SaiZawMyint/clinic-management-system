package ojt.clinic.app.persistence.dao.patientmedicalrecord.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ojt.clinic.app.persistence.dao.patientmedicalrecord.PatientMedicalRecordDao;
import ojt.clinic.app.persistence.entity.PatientMedicalRecord;

/**
 * <h2>PatientMedicalRecordDaoImpl Class</h2>
 * <p>
 * Process for Displaying PatientMedicalRecordDaoImpl
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Repository
public class PatientMedicalRecordDaoImpl implements PatientMedicalRecordDao {
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * <h2>SELECT_PATIENTMEDICALRECORD_HQL</h2>
     * <p>
     * SELECT_PATIENTMEDICALRECORD_HQL
     * </p>
     */
    private static final String SELECT_PATIENTMEDICALRECORD_HQL = "SELECT p FROM PatientMedicalRecord p WHERE p.delFlag = 0 ";
    /**
     * <h2>SELECT_PATIENTMEDICALRECORD_BYPID_HQL</h2>
     * <p>
     * SELECT_PATIENTMEDICALRECORD_BYPID_HQL
     * </p>
     */
    private static final String SELECT_PATIENTMEDICALRECORD_BYPID_HQL = "SELECT p FROM PatientMedicalRecord p WHERE p.delFlag = 0 AND p.patientId= :pid";
    /**
     * <h2>SEARCH_PATIENTMEDICALRECORD_HQL</h2>
     * <p>
     * SEARCH_PATIENTMEDICALRECORD_HQL
     * </p>
     */
    private static final String SEARCH_PATIENTMEDICALRECORD_HQL = "SELECT p FROM PatientMedicalRecord p WHERE (p.patientId= :pid AND p.medicalRecord like :search AND p.delFlag = 0) OR (p.patientId= :pid AND p.medicine like :search AND p.delFlag = 0) OR (p.patientId= :pid AND p.cost like :search AND p.delFlag = 0) OR (p.patientId= :pid AND p.remark like :search AND p.delFlag = 0) OR (p.patientId= :pid AND p.recordDateTime like :search AND p.delFlag = 0)";
    /**
     * <h2>SEARCH_ALL_PATIENTMEDICALRECORD_HQL</h2>
     * <p>
     * SEARCH_ALL_PATIENTMEDICALRECORD_HQL
     * </p>
     */
    private static final String SEARCH_ALL_PATIENTMEDICALRECORD_HQL = "SELECT p FROM PatientMedicalRecord p INNER JOIN p.patientInfo pi WHERE (p.medicalRecord like :search AND p.delFlag = 0) OR (p.medicine like :search AND p.delFlag = 0) OR (p.cost like :search AND p.delFlag = 0) OR (p.remark like :search AND p.delFlag = 0) OR (p.recordDateTime like :search AND p.delFlag = 0) OR (pi.patientName like :search AND p.delFlag = 0)";
    /**
     * <h2>DELETE_PATIENTMEDICALRECORD_HQL</h2>
     * <p>
     * DELETE_PATIENTMEDICALRECORD_HQL
     * </p>
     */
    private static final String DELETE_PATIENTMEDICALRECORD_HQL = "UPDATE PatientMedicalRecord p set p.delFlag = 1  WHERE p.patientMedicalRecordId = :pid";

    /**
     * <h2>dbAddPatientMedicalRecord</h2>
     * <p>
     * Add PatientMedicalRecord
     * </p>
     * 
     * @param patientMedicalRecord PatientMedicalRecord
     */
    @Override
    public void dbAddPatientMedicalRecord(PatientMedicalRecord patientMedicalRecord) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(patientMedicalRecord);
    }

    /**
     * <h2>dbListPatientMedicalRecords</h2>
     * <p>
     * List PatientMedicalRecord
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PatientMedicalRecord> dbListPatientMedicalRecords() {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_PATIENTMEDICALRECORD_HQL).list();
    }

    /**
     * <h2>dbListPatientMedicalRecordsByPid</h2>
     * <p>
     * List PatientMedicalRecord With Patient ID
     * </p>
     * 
     * @param id int
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PatientMedicalRecord> dbListPatientMedicalRecordsByPid(int id) {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_PATIENTMEDICALRECORD_BYPID_HQL)
                .setParameter("pid", id).list();
    }

    /**
     * <h2>dbSearchListPatientMedicalRecords</h2>
     * <p>
     * Search PatientMedicalRecord By Patient ID
     * </p>
     * 
     * @param id     int
     * @param search String
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PatientMedicalRecord> dbSearchListPatientMedicalRecords(int id, String search) {
        return this.sessionFactory.getCurrentSession().createQuery(SEARCH_PATIENTMEDICALRECORD_HQL)
                .setParameter("pid", id).setParameter("search", "%" + search + "%").list();
    }

    /**
     * <h2>dbSearchAllListPatientMedicalRecords</h2>
     * <p>
     * Search All PatientMedicalRecord
     * </p>
     * 
     * @param search String
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PatientMedicalRecord> dbSearchAllListPatientMedicalRecords(String search) {
        return this.sessionFactory.getCurrentSession().createQuery(SEARCH_ALL_PATIENTMEDICALRECORD_HQL)
                .setParameter("search", "%" + search + "%").list();
    }

    /**
     * <h2>dbGetPatientMedicalRecordById</h2>
     * <p>
     * Get PatientMedicalRecord By ID
     * </p>
     * 
     * @param id int
     * @return
     */
    @SuppressWarnings("removal")
    @Override
    public PatientMedicalRecord dbGetPatientMedicalRecordById(int id) {
        Session session = sessionFactory.getCurrentSession();
        PatientMedicalRecord patientMedicalRecord = (PatientMedicalRecord) session.get(PatientMedicalRecord.class,
                new Integer(id));
        return patientMedicalRecord;
    }

    /**
     * <h2>dbUpdatePatientMedicalRecord</h2>
     * <p>
     * Update PatientMedicalRecord
     * </p>
     * 
     * @param patientMedicalRecord PatientMedicalRecord
     */
    @Override
    public void dbUpdatePatientMedicalRecord(PatientMedicalRecord patientMedicalRecord) {
        Session session = sessionFactory.getCurrentSession();
        session.update(patientMedicalRecord);
    }

    /**
     * <h2>dbDeletePatientMedicalRecord</h2>
     * <p>
     * Delete PatientMedicalRecord
     * </p>
     * 
     * @param id int
     */
    @Override
    public void dbDeletePatientMedicalRecord(int id) {
        sessionFactory.getCurrentSession().createQuery(DELETE_PATIENTMEDICALRECORD_HQL).setParameter("pid", id)
                .executeUpdate();
    }
}