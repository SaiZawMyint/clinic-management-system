package ojt.clinic.app.bl.service.doctor.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ojt.clinic.app.bl.dto.PatientInfoDTO;
import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.bl.service.doctor.DoctorService;
import ojt.clinic.app.common.Constants;
import ojt.clinic.app.persistence.dao.user.UserDao;
import ojt.clinic.app.persistence.entity.PatientInfo;
import ojt.clinic.app.persistence.entity.User;
import ojt.clinic.app.web.form.DoctorForm;

/**
 * <h2>DoctorServiceImpl Class</h2>
 * <p>
 * Process for Displaying DoctorServiceImpl
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

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

    @Autowired
    ServletContext context;

    /**
     * <h2>doAddDoctor</h2>
     * <p>
     * Add Doctor
     * </p>
     * 
     * @param doctorForm DoctorForm
     */
    @Override
    public void doAddDoctor(DoctorForm doctorForm) {
        User doctor = new User(doctorForm);
        doctor.setUserType(Constants.DOCTOR);
        doctor.setDelFlag(0);
        doctor.setUserPassword(passwordEncoder.encode(doctorForm.getUserPassword()));
        userDao.dbAddUser(doctor);
    }

    /**
     * <h2>doListDoctors</h2>
     * <p>
     * List Doctor
     * </p>
     * 
     * @param type String
     * @return List<UserDTO>
     */
    @Override
    public List<UserDTO> doListDoctors(String type) {
        List<UserDTO> doctorList = new ArrayList<UserDTO>();
        for (User doctor : this.userDao.dbListUsers(type)) {
            UserDTO doctorDto = new UserDTO(doctor);
            doctorDto.setDoctorPhoto(this.getRealPhoto(doctorDto.getDoctorPhoto()));
            doctorList.add(doctorDto);
        }
        return doctorList;
    }

    /**
     * <h2>doGetDoctorSchedule</h2>
     * <p>
     * Get Doctor Schedule
     * </p>
     * 
     * @param id int
     * @return Map<Integer, Map<String, String>>
     */
    @Override
    public Map<Integer, Map<String, String>> doGetDoctorSchedule(Integer id) {
        User user = userDao.dbGetUserById(id);
        String secday = user.getDoctorDutyDay();
        StringTokenizer daytoken = new StringTokenizer(secday, ",");
        String sectime = user.getDoctorDutyTime();
        Map<Integer, Map<String, String>> schedule = new HashMap<Integer, Map<String, String>>();
        int count = 0;
        Map<String, String> val = null;
        while (daytoken.hasMoreTokens()) {
            val = new HashMap<String, String>();
            val.put("day", daytoken.nextToken());
            val.put("time", sectime);
            schedule.put(count, val);
            count++;
        }
        return schedule;
    }

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
    @Override
    public List<UserDTO> doSearchListDoctors(String search, String type) {
        List<UserDTO> doctorList = new ArrayList<UserDTO>();
        for (User doctor : this.userDao.dbSearchListUsers(search, type)) {
            UserDTO doctorDto = new UserDTO(doctor);
            doctorDto.setDoctorPhoto(this.getRealPhoto(doctorDto.getDoctorPhoto()));
            doctorList.add(doctorDto);
        }
        return doctorList;
    }

    /**
     * <h2>doGetRelatedPatientInfo</h2>
     * <p>
     * Get Related Patient Info
     * </p>
     * 
     * @param status String
     * @param Id     int
     * @return
     */
    @Override
    public List<PatientInfoDTO> doGetRelatedPatientInfo(String status, Integer Id) {
        UserDTO dto = this.doGetDoctorById(Id);
        List<PatientInfo> info = dto.getPatient();
        List<PatientInfoDTO> pdtoList = new ArrayList<PatientInfoDTO>();
        PatientInfoDTO pdto;
        for (PatientInfo p : info) {
            if (p.getToday().isEqual(LocalDate.now()) && p.getStatus().equals(status) && p.getDelFlag() == 0) {
                pdto = new PatientInfoDTO(p);
                pdtoList.add(pdto);
            }
        }
        return pdtoList;
    }

    /**
     * <h2>doGetDoctorById</h2>
     * <p>
     * Get Doctor By ID
     * </p>
     * 
     * @param id int
     * @return UserDTO
     */
    @Override
    public UserDTO doGetDoctorById(int id) {
        User doctor = this.userDao.dbGetUserById(id);
        UserDTO doctorForm = null;
        if (doctor != null && doctor.getUserType().equals(Constants.DOCTOR) && doctor.getDelFlag() == 0) {
            doctorForm = new UserDTO(doctor);
            doctorForm.setDoctorPhoto(this.getRealPhoto(doctorForm.getDoctorPhoto()));
        }
        return doctorForm;
    }

    /**
     * <h2>doUpdateDoctor</h2>
     * <p>
     * Update Doctor
     * </p>
     * 
     * @param doctorForm DoctorForm
     * @param id         int
     */
    @Override
    public void doUpdateDoctor(DoctorForm doctorForm, int id) {
        User doctor = userDao.dbGetUserById(id);
        Date a = new Date();
        doctor.setUpdatedAt(a);
        doctor.setUserName(doctorForm.getUserName());
        doctor.setUserEmail(doctorForm.getUserEmail());
        doctor.setUserPhone(doctorForm.getUserPhone());
        doctor.setDoctorDegree(doctorForm.getDoctorDegree());
        doctor.setDoctorSpecialization(doctorForm.getDoctorSpecialization());
        doctor.setDoctorDutyTime(doctorForm.getDoctorDutyTime());
        doctor.setDoctorDutyDay(doctorForm.getDoctorDutyDay());
    }

    /**
     * <h2>doDeleteDoctor</h2>
     * <p>
     * Delete Doctor
     * </p>
     * 
     * @param id int
     */
    @Override
    public void doDeleteDoctor(int id) {
        Date a = new Date();
        userDao.dbGetUserById(id).setDeletedAt(a);
        userDao.dbDeleteUser(id);
    }

    /**
     * <h2>doAnalyseImageData</h2>
     * <p>
     * Analuse Image Data
     * </p>
     * 
     * @param profile    MultipartFile
     * @param path       String
     * @param doctorForm String
     * @return HashMap<String, String>
     */
    @Override
    public HashMap<String, String> doAnalyseImageData(MultipartFile profile, DoctorForm doctorForm) {
        HashMap<String, String> data = new HashMap<>();
        StringBuffer strbuilder = new StringBuffer();
        String filetype = "";
        boolean checkProfile = true;
        if (!profile.isEmpty()) {
            filetype = FilenameUtils.getExtension(profile.getOriginalFilename());
            strbuilder.append("data:image/" + filetype + ";base64,");
            try {
                byte[] imageByte = profile.getBytes();
                strbuilder.append(StringUtils.newStringUtf8(Base64.encodeBase64(imageByte)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            strbuilder.append(doctorForm.getDoctorPhoto());
            checkProfile = false;
        }
        data.put("base64", strbuilder.toString());
        if (checkProfile) {
            if (doctorForm.getUserId() == null || doctorForm.getUserId() == 0) {
                data.put("filename", this.generateId(doctorForm.getUserName(), filetype));
            } else {
                UserDTO prof = this.doGetDoctorById(doctorForm.getUserId());
                if (prof.getDoctorPhoto().equals(doctorForm.getDoctorPhoto())) {
                    data.put("filename", doctorForm.getDoctorPhoto());
                } else {
                    data.put("filename", this.generateId(doctorForm.getUserName(), filetype));
                }
            }
        } else {
            if (doctorForm.getDoctorPhoto().contains("base64")) {
                String pfdata = doctorForm.getDoctorPhoto();
                filetype = pfdata.substring(pfdata.indexOf('/') + 1, pfdata.indexOf(';'));
                data.put("filename", this.generateId(doctorForm.getUserName(), filetype));
            } else {
                data.put("filename", doctorForm.getDoctorPhoto());
            }

        }
        return data;
    }

    /**
     * <h2>doUploadProfile</h2>
     * <p>
     * Upload Profile
     * </p>
     * 
     * @param profile StrinString
     * @throws IOException
     */
    @Override
    public void doUploadProfile(String profile, String dir) throws IOException {
        if (profile.contains("base64")) {
            String[] base64 = profile.split(",");
            byte[] bytes = Base64.decodeBase64(base64[1]);
            File file = new File(dir);
            if (!file.exists())
                file.createNewFile();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            BufferedImage imge = ImageIO.read(bis);
            bis.close();
            String filetype = base64[0].substring(base64[0].indexOf('/') + 1, base64[0].indexOf(';'));
            ImageIO.write(imge, filetype, file);
        }
    }

    /**
     * <h2>generateId</h2>
     * <p>
     * Generate Serial ID
     * </p>
     *
     * @param name String
     * @param type String
     * @return String
     */
    private String generateId(String name, String type) {
        String id = "";
        name = name.replaceAll("\\s", "_");
        GregorianCalendar cal = new GregorianCalendar();
        id = name + "_" + cal.getTimeInMillis() + "." + type;
        return id;
    }

    /**
     * <h2>getRealPhoto</h2>
     * <p>
     * Get Real Photo
     * </p>
     *
     * @param curphoto String
     * @return String
     */
    private String getRealPhoto(String curphoto) {
        String realphoto = "";
        String path = context.getRealPath("/resources/img/profile");
        File file = new File(path + File.separator + curphoto);
        if (!file.exists()) {
            realphoto = "img_doctor.svg";
        } else {
            realphoto = curphoto;
        }
        return realphoto;
    }
}