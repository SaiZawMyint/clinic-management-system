package ojt.clinic.app.bl.service.patientmedicalrecord.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ojt.clinic.app.bl.dto.PatientMedicalRecordDTO;
import ojt.clinic.app.bl.dto.UserDTO;
import ojt.clinic.app.bl.service.doctor.DoctorService;
import ojt.clinic.app.bl.service.patientmedicalrecord.PatientMedicalRecordService;
import ojt.clinic.app.common.Constants;
import ojt.clinic.app.persistence.dao.patientinfo.PatientInfoDao;
import ojt.clinic.app.persistence.dao.patientmedicalrecord.PatientMedicalRecordDao;
import ojt.clinic.app.persistence.entity.PatientInfo;
import ojt.clinic.app.persistence.entity.PatientMedicalRecord;
import ojt.clinic.app.web.form.PatientMedicalRecordForm;

/**
 * <h2>PatientMedicalRecordServiceImpl Class</h2>
 * <p>
 * Process for Displaying PatientMedicalRecordServiceImpl
 * </p>
 * 
 * @author ZawLwinTun, Sai Zaw Myint
 *
 */
@Service
@Transactional
public class PatientMedicalRecordServiceImpl implements PatientMedicalRecordService {

	/**
	 * <h2>patientMedicalRecordDao</h2>
	 * <p>
	 * patientMedicalRecordDao
	 * </p>
	 */
	@Autowired
	private PatientMedicalRecordDao patientMedicalRecordDao;
	/**
	 * <h2>patientInfoDao</h2>
	 * <p>
	 * patientInfoDao
	 * </p>
	 */
	@Autowired
	private PatientInfoDao patientInfoDao;

	/**
	 * <h2>doctorService</h2>
	 * <p>
	 * doctorService
	 * </p>
	 */
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
	 * <h2>doAddPatientMedicalRecord</h2>
	 * <p>
	 * Add PatientMedicalRecord
	 * </p>
	 * 
	 * @param patientMedicalRecordForm PatientMedicalRecordForm
	 */
	@Override
	public void doAddPatientMedicalRecord(PatientMedicalRecordForm patientMedicalRecordForm) {
		PatientMedicalRecord patientMedicalRecord = new PatientMedicalRecord(patientMedicalRecordForm);
		Date a = new Date();
		int userId = (int) session.getAttribute("loggedInId");
		patientMedicalRecord.setDelFlag(0);
		patientMedicalRecord.setCreatedUserId(userId);
		patientMedicalRecord.setCreatedAt(a);
		patientMedicalRecord.setRecordDateTime(a);
		patientMedicalRecordDao.dbAddPatientMedicalRecord(patientMedicalRecord);
	}

	/**
	 * <h2>doListPatientMedicalRecords</h2>
	 * <p>
	 * List All PatientMedicalRecord
	 * </p>
	 * 
	 * @return List<PatientMedicalRecordDTO>
	 */
	@Override
	public List<PatientMedicalRecordDTO> doListPatientMedicalRecords() {
		List<PatientMedicalRecordDTO> patientMedicalRecordList = new ArrayList<PatientMedicalRecordDTO>();
		for (PatientMedicalRecord patientMedicalRecord : this.patientMedicalRecordDao.dbListPatientMedicalRecords()) {
			PatientMedicalRecordDTO patientMedicalRecordDto = new PatientMedicalRecordDTO(patientMedicalRecord);
			patientMedicalRecordList.add(patientMedicalRecordDto);
		}
		return patientMedicalRecordList;
	}

	/**
	 * <h2>doListPatientMedicalRecordsByPid</h2>
	 * <p>
	 * List PatientMedicalRecord By Patient ID
	 * </p>
	 * 
	 * @param id int
	 * @return List<PatientMedicalRecordDTO>
	 */
	@Override
	public List<PatientMedicalRecordDTO> doListPatientMedicalRecordsByPid(int id) {
		List<PatientMedicalRecordDTO> patientMedicalRecordList = new ArrayList<PatientMedicalRecordDTO>();
		for (PatientMedicalRecord patientMedicalRecord : this.patientMedicalRecordDao
				.dbListPatientMedicalRecordsByPid(id)) {
			PatientMedicalRecordDTO patientMedicalRecordDto = new PatientMedicalRecordDTO(patientMedicalRecord);
			patientMedicalRecordList.add(patientMedicalRecordDto);
		}
		return patientMedicalRecordList;
	}

	/**
	 * <h2>doSearchListPatientMedicalRecords</h2>
	 * <p>
	 * Search PatientMedicalRecord With Patient ID
	 * </p>
	 * 
	 * @param id     int
	 * @param search String
	 * @return List<PatientMedicalRecordDTO>
	 */
	@Override
	public List<PatientMedicalRecordDTO> doSearchListPatientMedicalRecords(int id, String search) {
		List<PatientMedicalRecordDTO> patientMedicalRecordList = new ArrayList<PatientMedicalRecordDTO>();
		for (PatientMedicalRecord patientMedicalRecord : this.patientMedicalRecordDao
				.dbSearchListPatientMedicalRecords(id, search)) {
			PatientMedicalRecordDTO patientMedicalRecordDto = new PatientMedicalRecordDTO(patientMedicalRecord);
			patientMedicalRecordList.add(patientMedicalRecordDto);
		}
		return patientMedicalRecordList;
	}

	/**
	 * <h2>doSearchAllListPatientMedicalRecords</h2>
	 * <p>
	 * Search All PatientMedicalRecord
	 * </p>
	 * 
	 * @param search String
	 * @return List<PatientMedicalRecordDTO>
	 */
	@Override
	public List<PatientMedicalRecordDTO> doSearchAllListPatientMedicalRecords(String search) {
		List<PatientMedicalRecordDTO> patientMedicalRecordList = new ArrayList<PatientMedicalRecordDTO>();
		for (PatientMedicalRecord patientMedicalRecord : this.patientMedicalRecordDao
				.dbSearchAllListPatientMedicalRecords(search)) {
			PatientMedicalRecordDTO patientMedicalRecordDto = new PatientMedicalRecordDTO(patientMedicalRecord);
			patientMedicalRecordList.add(patientMedicalRecordDto);
		}
		return patientMedicalRecordList;
	}

	/**
	 * <h2>doGetPatientMedicalRecordById</h2>
	 * <p>
	 * Get PatientMedicalRecord By ID
	 * </p>
	 * 
	 * @param id int
	 * @return PatientMedicalRecordDTO
	 */
	@Override
	public PatientMedicalRecordDTO doGetPatientMedicalRecordById(int id) {
		PatientMedicalRecord patientMedicalRecord = this.patientMedicalRecordDao.dbGetPatientMedicalRecordById(id);
		PatientMedicalRecordDTO patientMedicalRecordForm = patientMedicalRecord != null
				? new PatientMedicalRecordDTO(patientMedicalRecord)
				: null;
		return patientMedicalRecordForm;
	}

	/**
	 * <h2>doUpdatePatientMedicalRecord</h2>
	 * <p>
	 * Update PatientMedicalRecord
	 * </p>
	 * 
	 * @param patientMedicalRecordForm PatientMedicalRecordForm
	 */
	@Override
	public void doUpdatePatientMedicalRecord(PatientMedicalRecordForm patientMedicalRecordForm) {
		PatientMedicalRecord patientMedicalRecord = new PatientMedicalRecord(patientMedicalRecordForm);
		Date a = new Date();
		int userId = (int) session.getAttribute("loggedInId");
		patientMedicalRecord.setDelFlag(0);
		patientMedicalRecord.setUpdatedUserId(userId);
		patientMedicalRecord.setUpdatedAt(a);
		patientMedicalRecordDao.dbUpdatePatientMedicalRecord(patientMedicalRecord);
	}

	/**
	 * <h2>doDeletePatientMedicalRecord</h2>
	 * <p>
	 * Delete PatientMedicalRecord
	 * </p>
	 * 
	 * @param id int
	 */
	@Override
	public void doDeletePatientMedicalRecord(int id) {
		Date a = new Date();
		int userId = (int) session.getAttribute("loggedInId");
		patientMedicalRecordDao.dbGetPatientMedicalRecordById(id).setDeletedUserId(userId);
		patientMedicalRecordDao.dbGetPatientMedicalRecordById(id).setDeletedAt(a);
		patientMedicalRecordDao.dbDeletePatientMedicalRecord(id);
	}

	/**
	 * <h2>doDownloadMedicalRecord</h2>
	 * <p>
	 * Download PatientMedicalRecord
	 * </p>
	 * 
	 * @param response
	 * @throws IOException
	 * @throws NullPointerException
	 */
	@Override
	public void doDownloadMedicalRecord(HttpServletResponse response) throws IOException, NullPointerException {
		String fileName = Constants.MEDICALRECORD;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Patient Medical Record");
		CreationHelper creationHelper = workbook.getCreationHelper();
		Row row = sheet.createRow(0);
		for (int i = 0; i < Constants.EXCEL_DOWNLOAD_MEDICALRECORD.size(); i++) {
			row.createCell(i).setCellValue(Constants.EXCEL_DOWNLOAD_MEDICALRECORD.get(i));
		}
		Row datarow;
		int rowCount = 1;
		List<PatientMedicalRecord> medicalRecrodList = patientMedicalRecordDao.dbListPatientMedicalRecords();
		for (PatientMedicalRecord mdr : medicalRecrodList) {
			CellStyle style = workbook.createCellStyle();
			style.setDataFormat(creationHelper.createDataFormat().getFormat(Constants.DATE_FORMAT));
			datarow = sheet.createRow(rowCount++);
			try {
				datarow.createCell(0).setCellValue(mdr.getPatientMedicalRecordId());
				datarow.createCell(1).setCellValue(mdr.getPatientId());
				datarow.createCell(2).setCellValue(mdr.getUserId());
				Cell cell3 = datarow.createCell(3);
				cell3.setCellValue(mdr.getRecordDateTime());
				cell3.setCellStyle(style);
				datarow.createCell(4).setCellValue(mdr.getMedicalRecord());
				datarow.createCell(5).setCellValue(mdr.getMedicine());
				datarow.createCell(6).setCellValue(mdr.getCost());
				datarow.createCell(7).setCellValue(mdr.getRemark());
				datarow.createCell(8).setCellValue(mdr.getDelFlag());
				datarow.createCell(9).setCellValue(mdr.getCreatedUserId());
				Cell cell10 = datarow.createCell(10);
				cell10.setCellValue(mdr.getCreatedAt());
				cell10.setCellStyle(style);
				datarow.createCell(11).setCellValue(mdr.getUpdatedUserId());
				Cell cell12 = datarow.createCell(12);
				cell12.setCellValue(mdr.getUpdatedAt());
				cell12.setCellStyle(style);
				datarow.createCell(13).setCellValue(mdr.getDeletedUserId());
				Cell cell14 = datarow.createCell(14);
				cell14.setCellValue(mdr.getDeletedAt());
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
	 * <h2>doUploadPatientMedicalRecord</h2>
	 * <p>
	 * Upload PatientMedicalRecord
	 * </p>
	 * 
	 * @param file MultipartFile
	 * @return String
	 * @throws IOException
	 */
	@SuppressWarnings({ "resource" })
	@Override
	public String doUploadPatientMedicalRecord(MultipartFile file) throws IOException {
		String check = this.validExcelFile(file);
		if (check != null && check != "") {
			return check;
		}
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			PatientMedicalRecord patientMedicalRecord = new PatientMedicalRecord();
			Row row = sheet.getRow(i);
			Cell cellPatientId = row.getCell(1);
			patientMedicalRecord.setPatientId((int) cellPatientId.getNumericCellValue());
			Cell cellDoctorId = row.getCell(2);
			patientMedicalRecord.setUserId((int) cellDoctorId.getNumericCellValue());
			Cell cellRecordDate = row.getCell(3);
			patientMedicalRecord.setRecordDateTime(cellRecordDate.getDateCellValue());
			Cell cellMedicalRecord = row.getCell(4);
			patientMedicalRecord.setMedicalRecord(cellMedicalRecord.getStringCellValue());
			Cell cellMedicine = row.getCell(5);
			patientMedicalRecord.setMedicine(cellMedicine.getStringCellValue());
			Cell cellCost = row.getCell(6);
			patientMedicalRecord.setCost((int) cellCost.getNumericCellValue());
			Cell cellRemark = row.getCell(7);
			patientMedicalRecord.setRemark(cellRemark.getStringCellValue());
			patientMedicalRecord.setDelFlag(0);
			Date a = new Date();
			patientMedicalRecord.setCreatedUserId((int) session.getAttribute("loggedInId"));
			patientMedicalRecord.setCreatedAt(a);
			patientMedicalRecord.setUpdatedAt(a);
			patientMedicalRecordDao.dbAddPatientMedicalRecord(patientMedicalRecord);
		}
		return "File Upload Successful";
	}

	/**
	 * <h2>validExcelFile</h2>
	 * <p>
	 * PatientMedicalRecord Upload File Validation
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
				// check null
				if (cell == null)
					return "There is Null in the file";
			}
		}
		// check file Type
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			// Patient ID
			Cell cellPatientId = row.getCell(1);
			if (cellPatientId.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 1
						+ "<br>Patient ID has wrong data type in this file. <br>";
			}
			PatientInfo patient = patientInfoDao.dbGetPatientInfoById((int) cellPatientId.getNumericCellValue());
			if (patient == null) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 1 + "<br>No Patient found in this clinic. <br>";
			}
			// Doctor ID
			Cell cellDoctorId = row.getCell(2);
			if (cellDoctorId.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 2
						+ "<br>Doctor ID has wrong data type in this file. <br>";
			}
			UserDTO doctor = doctorService.doGetDoctorById((int) cellDoctorId.getNumericCellValue());
			if (doctor == null) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 2 + "<br>No Doctor found in this clinic. <br>";
			}
			// Record Date
			Cell cellRecordDate = row.getCell(3);
			if (!DateUtil.isCellDateFormatted(cellRecordDate)) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 3
						+ "<br>Record Date has wrong data type in this file. <br>";
			}

			// Medical Record
			Cell cellMedicalRecord = row.getCell(4);
			if (cellMedicalRecord.getCellType() != Cell.CELL_TYPE_STRING) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 4
						+ "<br>Medical Record has wrong data type in this file. <br>";
			}
			// Medicine
			Cell cellMedicine = row.getCell(5);
			if (cellMedicine.getCellType() != Cell.CELL_TYPE_STRING) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 5 + "<br>Medicine has type in this file. <br>";
			}
			// Cost
			Cell cellCost = row.getCell(6);
			if (cellCost.getCellType() != Cell.CELL_TYPE_NUMERIC) {
				err += "<br>Error : Row " + (i+1) + ", Column " + 6 + "<br>Cost has worng data type in this file. <br>";
			}

		}
		// check file has no data
		if (sheet.getFirstRowNum() == sheet.getLastRowNum()) {
			return "No Data in the File";
		}
		return err;
	}
}