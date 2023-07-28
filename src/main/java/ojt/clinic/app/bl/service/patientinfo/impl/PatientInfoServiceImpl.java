package ojt.clinic.app.bl.service.patientinfo.impl;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ojt.clinic.app.bl.dto.PatientInfoDTO;
import ojt.clinic.app.bl.service.doctor.DoctorService;
import ojt.clinic.app.bl.service.patientinfo.PatientInfoService;
import ojt.clinic.app.common.Constants;
import ojt.clinic.app.persistence.dao.patientinfo.PatientInfoDao;
import ojt.clinic.app.persistence.dao.user.UserDao;
import ojt.clinic.app.persistence.entity.PatientInfo;
import ojt.clinic.app.web.form.PatientInfoForm;

/**
 * <h2>PatientInfoServiceImpl Class</h2>
 * <p>
 * Process for Displaying PatientInfoServiceImpl
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Service
@Transactional
public class PatientInfoServiceImpl implements PatientInfoService {

	/**
	 * <h2>patientInfoDao</h2>
	 * <p>
	 * patientInfoDao
	 * </p>
	 */
	@Autowired
	private PatientInfoDao patientInfoDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DoctorService doctorService;

	/**
	 * <h2>session</h2>
	 * <p>
	 * session
	 * </p>
	 */
	@Autowired
	private HttpSession session;

	/**
	 * <h2>doAddPatientInfo</h2>
	 * <p>
	 * Add Patient Info
	 * </p>
	 * 
	 * @param patientInfoForm PatientInfoForm
	 */
	@Override
	public void doAddPatientInfo(PatientInfoForm patientInfoForm) {
		PatientInfo patientInfo = new PatientInfo(patientInfoForm);
		int userId = (int) session.getAttribute("loggedInId");
		Date a = new Date();
		patientInfo.setDelFlag(0);
		patientInfo.setCreatedUserId(userId);
		patientInfo.setCreatedAt(a);
		patientInfo.setToday(LocalDate.now());
		patientInfo.setStatus("Treating");
		patientInfo.setDoctor(userDao.dbGetUserById(patientInfoForm.getDoctorId()));
		patientInfoDao.dbAddPatientInfo(patientInfo);
	}

	/**
	 * <h2>doAddTodayPatient</h2>
	 * <p>
	 * Add Into Today Patient List
	 * </p>
	 * 
	 * @param today LocalDate
	 * @param id    int
	 */
	@Override
	public void doAddTodayPatient(LocalDate today, PatientInfoForm p) {
		PatientInfo patientInfo = patientInfoDao.dbGetPatientInfoById(p.getPatientId());
		patientInfo.setToday(today);
		patientInfo.setDoctor(userDao.dbGetUserById(p.getDoctorId()));
		patientInfoDao.dbAddTodayPatient(today, patientInfo);
	}

	/**
	 * <h2>doListPatientInfos</h2>
	 * <p>
	 * List Patient Info
	 * </p>
	 * 
	 * @return List<PatientInfoDTO>
	 */
	@Override
	public List<PatientInfoDTO> doListPatientInfos() {
		List<PatientInfoDTO> patientInfoList = new ArrayList<PatientInfoDTO>();
		for (PatientInfo patientInfo : this.patientInfoDao.dbListPatientInfos()) {
			PatientInfoDTO patientInfoDto = new PatientInfoDTO(patientInfo);
			patientInfoList.add(patientInfoDto);
		}
		return patientInfoList;
	}

	/**
	 * <h2>doSearchListPatientInfos</h2>
	 * <p>
	 * Search Patient Info
	 * </p>
	 * 
	 * @param search String
	 * @return List<PatientInfoDTO>
	 */
	@Override
	public List<PatientInfoDTO> doSearchListPatientInfos(String search) {
		List<PatientInfoDTO> patientInfoList = new ArrayList<PatientInfoDTO>();
		for (PatientInfo patientInfo : this.patientInfoDao.dbSearchListPatientInfos(search)) {
			PatientInfoDTO patientInfoDto = new PatientInfoDTO(patientInfo);
			patientInfoList.add(patientInfoDto);
		}
		return patientInfoList;
	}

	/**
	 * <h2>doTodayPatients</h2>
	 * <p>
	 * List Today Patient
	 * </p>
	 * 
	 * @param today  LocalDate
	 * @param status String
	 * @return List<PatientInfoDTO>
	 */
	@Override
	public List<PatientInfoDTO> doTodayPatients(LocalDate today, String status) {
		List<PatientInfoDTO> patientInfoList = new ArrayList<PatientInfoDTO>();
		for (PatientInfo patientInfo : this.patientInfoDao.dbTodayPatients(today, status)) {
			PatientInfoDTO patientInfoDto = new PatientInfoDTO(patientInfo);
			patientInfoList.add(patientInfoDto);
		}
		return patientInfoList;
	}

	/**
	 * <h2>doGetPatientInfoById</h2>
	 * <p>
	 * Get Patient Info By ID
	 * </p>
	 * 
	 * @param id int
	 * @return PatientInfoDTO
	 */
	@Override
	public PatientInfoDTO doGetPatientInfoById(int id) {
		PatientInfo patientInfo = this.patientInfoDao.dbGetPatientInfoById(id);
		PatientInfoDTO patientInfoForm = patientInfo != null ? new PatientInfoDTO(patientInfo) : null;
		return patientInfoForm;
	}

	/**
	 * <h2>doUpdatePatientInfo</h2>
	 * <p>
	 * Update Patient Info
	 * </p>
	 * 
	 * @param id              int
	 * @param patientInfoForm PatientInfoForm
	 */
	@Override
	public void doUpdatePatientInfo(int id, PatientInfoForm patientInfoForm) {
		int userId = (int) session.getAttribute("loggedInId");
		Date a = new Date();
		PatientInfo patientInfo = patientInfoDao.dbGetPatientInfoById(id);
		patientInfo.setUpdatedUserId(userId);
		patientInfo.setUpdatedAt(a);
		patientInfo.setPatientName(patientInfoForm.getPatientName());
		patientInfo.setPatientAge(patientInfoForm.getPatientAge());
		patientInfo.setPatientGender(patientInfoForm.getPatientGender());
		patientInfo.setPatientAddress(patientInfoForm.getPatientAddress());
		patientInfo.setPatientPhone(patientInfoForm.getPatientPhone());
	}

	/**
	 * <h2>doChangeStatus</h2>
	 * <p>
	 * Change Status Of Patient
	 * </p>
	 * 
	 * @param id int
	 */
	@Override
	public void doChangeStatus(int id) {
		patientInfoDao.dbChangeStatus(id);
	}

	/**
	 * <h2>doDeletePatientInfo</h2>
	 * <p>
	 * Delete Patient Info
	 * </p>
	 * 
	 * @param id int
	 */
	@Override
	public void doDeletePatientInfo(int id) {
		Date a = new Date();
		int userId = (int) session.getAttribute("loggedInId");
		patientInfoDao.dbGetPatientInfoById(id).setDeletedUserId(userId);
		patientInfoDao.dbGetPatientInfoById(id).setDeletedAt(a);
		patientInfoDao.dbDeletePatientInfo(id);
	}

	/**
	 * <h2>doDownloadPatientInfo</h2>
	 * <p>
	 * Download Patient Info
	 * </p>
	 * 
	 * @param response HttpServletResponse
	 * @throws IOException
	 * @throws NullPointerException
	 */
	@Override
	public void doDownloadPatientInfo(HttpServletResponse response) throws IOException, NullPointerException {
		String fileName = Constants.PATIENT;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Patient Information");
		CreationHelper creationHelper = workbook.getCreationHelper();
		Row row = sheet.createRow(0);
		for (int i = 0; i < Constants.EXCEL_DOWNLOAD_PATIENTINFO.size(); i++) {
			row.createCell(i).setCellValue(Constants.EXCEL_DOWNLOAD_PATIENTINFO.get(i));
		}
		Row datarow;
		int rowCount = 1;
		List<PatientInfo> patientList = patientInfoDao.dbListPatientInfos();
		for (PatientInfo p : patientList) {
			CellStyle style = workbook.createCellStyle();
			style.setDataFormat(creationHelper.createDataFormat().getFormat(Constants.DATE_FORMAT));
			datarow = sheet.createRow(rowCount++);
			try {
				datarow.createCell(0).setCellValue(p.getPatientId());
				datarow.createCell(1).setCellValue(p.getPatientName());
				datarow.createCell(2).setCellValue(p.getPatientAge());
				datarow.createCell(3).setCellValue(p.getPatientGender());
				datarow.createCell(4).setCellValue(p.getPatientAddress());
				datarow.createCell(5).setCellValue(p.getPatientPhone());
				datarow.createCell(6).setCellValue(p.getToday().toString());
				datarow.createCell(7).setCellValue(p.getDoctor().getUserId());
				datarow.createCell(8).setCellValue(p.getStatus());
				datarow.createCell(9).setCellValue(p.getDelFlag());
				datarow.createCell(10).setCellValue(p.getCreatedUserId());
				Cell cell10 = datarow.createCell(11);
				cell10.setCellValue(p.getCreatedAt());
				cell10.setCellStyle(style);
				datarow.createCell(12).setCellValue(p.getUpdatedUserId());
				Cell cell12 = datarow.createCell(13);
				cell12.setCellValue(p.getUpdatedAt());
				cell12.setCellStyle(style);
				datarow.createCell(14).setCellValue(p.getDeletedUserId());
				Cell cell14 = datarow.createCell(15);
				cell14.setCellValue(p.getDeletedAt());
				cell14.setCellStyle(style);
			} catch (NullPointerException e) {
				System.out.println("NullPointerException thrown!");
			}
		}
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
			IOUtils.closeQuietly(response.getOutputStream());
		}
	}

	/**
	 * <h2>doUploadPateintInfo</h2>
	 * <p>
	 * Upload Patient Info
	 * </p>
	 * 
	 * @param file MultipartFile
	 * @return String
	 * @throws IOException
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	@Override
	public String doUploadPateintInfo(MultipartFile file) throws IOException {
		String check = this.validExcelFile(file);
		if (check != null && check != "") {
			return check;
		}
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			PatientInfo patientInfo = new PatientInfo();
			Row row = sheet.getRow(i);
			Cell cellPatientName = row.getCell(1);
			patientInfo.setPatientName(cellPatientName.getStringCellValue());
			Cell cellPatientAge = row.getCell(2);
			patientInfo.setPatientAge((int) cellPatientAge.getNumericCellValue());
			Cell cellPatientGender = row.getCell(3);
			patientInfo.setPatientGender(cellPatientGender.getStringCellValue());
			Cell cellPatientAddress = row.getCell(4);
			patientInfo.setPatientAddress(cellPatientAddress.getStringCellValue());
			Cell cellPatientPhone = row.getCell(5);
			cellPatientPhone.setCellType(Cell.CELL_TYPE_STRING);
			patientInfo.setPatientPhone(cellPatientPhone.getStringCellValue());
			Cell cellDate = row.getCell(6);
			patientInfo.setToday(Instant.ofEpochMilli(cellDate.getDateCellValue().getTime())
					.atZone(ZoneId.systemDefault()).toLocalDate());
			Cell celldoctorId = row.getCell(7);
			patientInfo.setDoctor(userDao.dbGetUserById((int) celldoctorId.getNumericCellValue()));
			Cell cellStatus = row.getCell(8);
			patientInfo.setStatus(cellStatus.getStringCellValue());
			patientInfo.setDelFlag(0);
			Date a = new Date();
			patientInfo.setCreatedUserId((int) session.getAttribute("loggedInId"));
			patientInfo.setCreatedAt(a);
			patientInfo.setUpdatedAt(a);
			patientInfoDao.dbAddPatientInfo(patientInfo);
		}
		return "File Upload Successful";
	}

	/**
	 * <h2>validExcelFile</h2>
	 * <p>
	 * PatientInfo File Upload Validation
	 * </p>
	 *
	 * @param file MultipartFile
	 * @return String
	 * @throws IOException
	 * @return String
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	private String validExcelFile(MultipartFile file) throws IOException {
		// check if file is null
		String err = "";
		if (file.isEmpty()) {
			return "No File Is Selected";
		}
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		// check extension
		if (!extension.equals("xlsx") && !extension.equals("xls")) {
			return "File Extension Wrong!";
		}
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		// check null
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
				Cell cell = row.getCell(k);
				if (cell == null) {
					return "There is Null in the file " + i + 1 + ", " + k + 1;
				}
			}
		}
		// check file Type
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			// Patient Name
			Cell cellName = row.getCell(1);
			if (cellName.getCellType() != Cell.CELL_TYPE_STRING) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 1
						+ "<br>Patient name has wrong data type in this file. <br>";
			}
			if (cellName.getStringCellValue().length() < 3) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 1 + "<br>Patient name must greater than 3. <br>";
			}
			if (cellName.getStringCellValue().length() > 100) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 1 + "<br>Patient name must less than 100. <br>";
			}
			// Patient Age
			Cell cellPatientAge = row.getCell(2);
			if (cellPatientAge.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 2
						+ "<br>Patient Age has wrong data type in this file. <br>";
			}else
			if(cellPatientAge.getNumericCellValue() < 0 || cellPatientAge.getNumericCellValue() > 100) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 2
						+ "<br>Patient Age must greater than 0 and less than 100. <br>";
			}
			// Patient Gender
			Cell cellPatientGender = row.getCell(3);
			if (cellPatientGender.getCellType() != Cell.CELL_TYPE_STRING) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 3
						+ "<br>Patient Gender has wrong data type in this file. <br>";
			}
			// Patient Address
			Cell cellPatientAddress = row.getCell(4);
			if (cellPatientAddress.getCellType() != Cell.CELL_TYPE_STRING) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 4 + "<br>Patient Address has type in this file. <br>";
			}
			// Patient Phone Number
			Cell cellPatientPhone = row.getCell(5);
			if (cellPatientPhone.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 5
						+ "<br>Patient Phone Number has worng data type in this file. <br>";
			}
			cellPatientPhone.setCellType(Cell.CELL_TYPE_STRING);
			if (!(cellPatientPhone.getStringCellValue()).matches("(^&|[0-9,]{9,})")) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 5 + "<br>Invalid Patient Phone Number "
						+ cellPatientPhone.getStringCellValue() + ". <br>";
			}
			// doctorId
			Cell cellDocId = row.getCell(7);
			if (cellDocId.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 7
						+ "<br>Doctor Id has worng data type in this file. <br>";
			}
			if (doctorService.doGetDoctorById((int) cellDocId.getNumericCellValue()) == null) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 7 + "<br>No Doctor matched by the given id. <br>";
			}
			// Status
			Cell cellStatus = row.getCell(8);
			if (cellStatus.getCellType() != Cell.CELL_TYPE_STRING) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 8
						+ "<br>Patient Status has wrong data type in this file. <br>";
			}
			if (cellStatus.getStringCellValue().equals("Treating")
					&& cellStatus.getStringCellValue().equals("Finished")) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 8
						+ "<br>Patient Status has wrong data value in this file. <br>";
			}
		}
		// check file has no data
		if (sheet.getFirstRowNum() == sheet.getLastRowNum()) {
			return "No Data in the File";
		}
		return err;
	}
}