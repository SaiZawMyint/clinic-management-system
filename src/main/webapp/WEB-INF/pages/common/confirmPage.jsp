<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="wrapper">
    <div class="form-registration">
      <c:if test="${res == 'patientInfo'}">
        <form:form
          action="${pageContext.request.contextPath}/addPatientInfo"
          modelAttribute="patientInfoForm">
          <c:if test="${empty patientInfoForm.patientId}">
            <h2 class="tit-hdr">Patient Registration</h2>
            <c:set var="btn" scope="session" value="Register" />
          </c:if>
          <c:if test="${not empty patientInfoForm.patientId}">
            <h2 class="tit-hdr">Edit Patient Info</h2>
            <c:set var="btn" scope="session" value="Update" />
            <form:hidden path="patientId" />
          </c:if>
          <br>
          <form:hidden path="patientName" />
          <form:hidden path="patientAge" />
          <form:hidden path="patientGender" />
          <form:hidden path="patientAddress" />
          <form:hidden path="patientPhone" />
          <form:hidden path="doctorId"/>
          <div class="confirm-inp top clearfix">
            <p class="lf">Name</p>
            <p class="rg">${patientInfoForm.patientName}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Age</p>
            <p class="rg">${patientInfoForm.patientAge}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Gender</p>
            <p class="rg">${patientInfoForm.patientGender}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Phone</p>
            <p class="rg">${patientInfoForm.patientPhone}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Address</p>
            <p class="rg">${patientInfoForm.patientAddress}</p>
          </div>
          <div class="center-f">
            <input type="submit" class="btn sub" value="${btn}">
            <input type="submit" name="back" class="btn sub back-btn" value="Back">
          </div>
        </form:form>
      </c:if>

      <c:if test="${res == 'patientMedicalRecord'}">
        <form:form
          action="${pageContext.request.contextPath}/addPatientMedicalRecord"
          modelAttribute="patientMedicalRecordForm">
          <c:if
            test="${empty patientMedicalRecordForm.patientMedicalRecordId}">
            <h2 class="tit-hdr">Add New Patient Medical Record</h2>
            <c:set var="btn" scope="session" value="Add Record" />
          </c:if>
          <c:if
            test="${not empty patientMedicalRecordForm.patientMedicalRecordId}">
            <h2 class="tit-hdr">Edit Patient Medical Record</h2>
            <c:set var="btn" scope="session" value="Update Record" />
            <form:hidden path="patientMedicalRecordId" />
          </c:if>
          <br>
          <form:hidden path="patientId" />
          <form:hidden path="userId" />
          <form:hidden path="medicalRecord" />
          <form:hidden path="medicine" />
          <form:hidden path="cost" />
          <form:hidden path="remark" />
          <sec:authorize access="hasRole('RECEPTIONIST')">
            <div class="confirm-inp clearfix">
              <p class="lf">Patient Name</p>
              <p class="rg">${name}</p>

            </div>
            <div class="confirm-inp clearfix">
              <p class="lf">Medical Record</p>
              <p class="rg">${patientMedicalRecordForm.medicalRecord}</p>
            </div>
            <div class="confirm-inp clearfix">
              <p class="lf">Medicine</p>
              <p class="rg">${patientMedicalRecordForm.medicine}</p>
            </div>
            <div class="confirm-inp clearfix">
              <p class="lf">Remark</p>
              <p class="rg">${patientMedicalRecordForm.remark}</p>
            </div>
            <div class="confirm-inp clearfix">
              <p class="lf">Cost</p>
              <p class="rg">${patientMedicalRecordForm.cost}</p>
            </div>
          </sec:authorize>
          <sec:authorize access="hasRole('DOCTOR')">
            <div class="confirm-inp clearfix">
              <p class="lf">Patient Name</p>
              <p class="rg">${name}</p>
            </div>
            <div class="confirm-inp clearfix">
              <p class="lf">Medical Record</p>
              <p class="rg">${patientMedicalRecordForm.medicalRecord}</p>
            </div>
            <div class="confirm-inp clearfix">
              <p class="lf">Medicine</p>
              <p class="rg">${patientMedicalRecordForm.medicine}</p>
            </div>
            <div class="confirm-inp clearfix">
              <p class="lf">Remark</p>
              <p class="rg">${patientMedicalRecordForm.remark}</p>
            </div>
          </sec:authorize>
          <div class="center-f">
            <input type="submit" class="btn sub" value="${btn}">
            <input type="submit" name="back" class="btn sub back-btn" value="Back">
          </div>
        </form:form>
      </c:if>

      <c:if test="${res == 'receptionist'}">
        <form:form
          action="${pageContext.request.contextPath}/addReceptionist"
          modelAttribute="receptionistForm">
          <c:if test="${empty receptionistForm.userId}">
            <h2 class="tit-hdr">Receptionist Registration</h2>
            <c:set var="btn" scope="session" value="Register" />
          </c:if>
          <c:if test="${not empty receptionistForm.userId}">
            <h2 class="tit-hdr">Edit Receptionist Info</h2>
            <c:set var="btn" scope="session" value="Update" />
            <form:hidden path="userId" />
          </c:if>
          <br>
          <form:hidden path="userName" />
          <form:hidden path="userPassword" />
          <form:hidden path="userEmail" />
          <form:hidden path="userPhone" />
          <div class="confirm-inp clearfix">
            <p class="lf">Name</p>
            <p class="rg">${receptionistForm.userName}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Email</p>
            <p class="rg">${receptionistForm.userEmail}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Phone</p>
            <p class="rg">${receptionistForm.userPhone}</p>
          </div>
          <div class="center-f">
            <input type="submit" class="btn sub" value="${btn}">
            <input type="submit" name="back" class="btn sub back-btn" value="Back">
          </div>
        </form:form>
      </c:if>

      <c:if test="${res == 'doctor'}">
        <form:form
          action="${pageContext.request.contextPath}/addDoctor?${_csrf.parameterName}=${_csrf.token}"
          modelAttribute="doctorForm" enctype="multipart/form-data">
          <c:if test="${empty doctorForm.userId}">
            <h2 class="tit-hdr">Doctor Registration</h2>
            <c:set var="btn" scope="session" value="Register" />
          </c:if>
          <c:if test="${not empty doctorForm.userId}">
            <h2 class="tit-hdr">Edit Doctor Info</h2>
            <c:set var="btn" scope="session" value="Update" />
            <form:hidden path="userId" />
          </c:if>
          <br>
          <form:hidden path="doctorPhoto" />
          <form:hidden path="userName" />
          <form:hidden path="userPassword" />
          <form:hidden path="userEmail" />
          <form:hidden path="userPhone" />
          <form:hidden path="doctorDegree" />
          <form:hidden path="doctorSpecialization" />
          <form:hidden path="doctorDutyDay" />
          <form:hidden path="doctorDutyTime" />
          <div class="inp">
            <fieldset class="field-set">
              <div class="choose-photo" title="Profile Photo">
                <c:set var="filecode" value="base64" />
                <c:choose>
                  <c:when test="${fn:contains(profileData, filecode) }">
                    <c:set var="disProfile" value="${profileData}" />
                  </c:when>
                  <c:otherwise>
                    <c:set var="disProfile"
                      value="${pageContext.request.contextPath}/resources/img/profile/${profileData}" />
                  </c:otherwise>
                </c:choose>
                <c:if test=""></c:if>
                <img alt="doctor profile" id="dis-profile"
                  src="${disProfile}">
              </div>
              <input type="text" name="profile" value="${profileData}"
                hidden="hidden">
            </fieldset>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Name</p>
            <p class="rg">${doctorForm.userName}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Email</p>
            <p class="rg">${doctorForm.userEmail}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Phone</p>
            <p class="rg">${doctorForm.userPhone}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Degree</p>
            <p class="rg">${doctorForm.doctorDegree}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Specialization</p>
            <p class="rg">${doctorForm.doctorSpecialization}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Duty Days</p>
            <p class="rg">${doctorForm.doctorDutyDay}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Duty Time</p>
            <p class="rg doc-time">${doctorForm.doctorDutyTime}</p>
          </div>
          <div class="center-f">
            <input type="submit" class="btn sub" value="${btn}">
            <input type="submit" name="back" class="btn sub back-btn" value="Back">
          </div>
        </form:form>
      </c:if>

      <c:if test="${res == 'booking'}">
        <form:form
          action="${pageContext.request.contextPath}/addBooking"
          modelAttribute="bookingForm">
          <c:if test="${empty bookingForm.bookingId}">
            <h2 class="tit-hdr">Book An Appointment</h2>
            <c:set var="btn" scope="session" value="Book" />
          </c:if>
          <c:if test="${not empty bookingForm.bookingId}">
            <h2 class="tit-hdr">Edit Booking Info</h2>
            <c:set var="btn" scope="session" value="Update" />
            <form:hidden path="bookingId" />
          </c:if>
          <br>
          <form:hidden path="userId" />
          <form:hidden path="patientName" />
          <form:hidden path="contactPhone" />
          <form:hidden path="patientGender" />
          <form:hidden path="status" />
          <form:hidden path="rejectMsg" />
          <div class="confirm-inp top clearfix">
            <p class="lf">Name</p>
            <p class="rg">${bookingForm.patientName}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Contact Phone</p>
            <p class="rg">${bookingForm.contactPhone}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Gender</p>
            <p class="rg">${bookingForm.patientGender}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Doctor name</p>
            <p class="rg">${name}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Doctor Duty Day</p>
            <p class="rg">${day}</p>
          </div>
          <div class="confirm-inp clearfix">
            <p class="lf">Doctor Duty Time</p>
            <p class="rg doc-time">${time}</p>
          </div>
          <c:if test="${not empty bookingForm.status}">
            <div class="confirm-inp clearfix">
              <p class="lf">Status</p>
              <p class="rg">${bookingForm.status}</p>
            </div>
          </c:if>
          <c:if test="${not empty bookingForm.rejectMsg}">
            <div class="confirm-inp clearfix">
              <p class="lf">Reject Message</p>
              <p class="rg">${bookingForm.rejectMsg}</p>
            </div>
          </c:if>
          <div class="center-f">
            <input type="submit" class="btn sub" value="${btn}">
            <input type="submit" name="back" class="btn sub back-btn" value="Back">
          </div>
        </form:form>
      </c:if>
      <!-- Uploads -->
      <c:if test="${res == 'uploadPatientInfo' }">
        <form
          action="${pageContext.request.contextPath}/uploadPatientInfo?${_csrf.parameterName}=${_csrf.token}"
          enctype="multipart/form-data" method="post">
          <h2 class="center-f">Upload Patient Info from file</h2>
          <br>
          <c:if test="${not empty invoke }">
            <p class="invoke-err">${invoke}</p>
            <c:remove var="invoke"/>
          </c:if>

          <div class="inp">
            <fieldset class="field-set center-f">
              <legend>Please Choose File</legend>
              <input type="file" name="file" class="form-inp"
                placeholder="Choose Excel File">
            </fieldset>
          </div>
          <br>
          <div class="center-f">
            <input type="submit" class="btn sub" value="Upload">
            <input type="submit" name="back" class="btn sub back-btn" value="Back">
          </div>
        </form>
      </c:if>

      <c:if test="${res == 'uploadPatientMedicalRecord' }">
        <form
          action="${pageContext.request.contextPath}/uploadMedicalRecord?${_csrf.parameterName}=${_csrf.token}"
          enctype="multipart/form-data" method="post">
          <h2 class="center-f">Upload Patient Medical Record from
            file</h2>
          <br>
           <c:if test="${not empty invoke }">
            <p class="invoke-err">${invoke}</p>
            <c:remove var="invoke"/>
          </c:if>

          <div class="inp">
            <fieldset class="field-set center-f">
              <legend>Please Choose File</legend>
              <input type="file" name="file" class="form-inp"
                placeholder="Choose Excel File">
            </fieldset>
          </div>
          <br>
          <div class="center-f">
            <input type="submit" class="btn sub" value="Upload">
            <input type="submit" name="back" class="btn sub back-btn" value="Back">
          </div>
        </form>
      </c:if>
    </div>
  </div>
</body>
</html>