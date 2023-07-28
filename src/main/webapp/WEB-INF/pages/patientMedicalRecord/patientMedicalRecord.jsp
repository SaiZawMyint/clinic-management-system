<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
<%@ page session="true"%>
<body>
  <div class="wrapper">
    <div class="form-registration">
      <form:form
        action="${pageContext.request.contextPath}/addPatientMedicalRecordConfirm"
        modelAttribute="patientMedicalRecordForm" id="form">
        <c:if
          test="${empty patientMedicalRecordForm.patientMedicalRecordId}">
          <h2 class="tit-hdr">Add New Patient Medical Record</h2>
        </c:if>
        <c:if
          test="${not empty patientMedicalRecordForm.patientMedicalRecordId}">
          <h2 class="tit-hdr">Edit Patient Medical Record</h2>
          <form:hidden path="patientMedicalRecordId" />
        </c:if>
        <form:hidden path="patientId" />
        <c:if test="${empty patientMedicalRecordForm.userId }">
          <form:hidden path="userId" value="${loggedInId}" />
        </c:if>
        <c:if test="${not empty  patientMedicalRecordForm.userId}">
          <form:hidden path="userId" />
        </c:if>
        <sec:authorize access="hasRole('DOCTOR')">
          <div class="inp">
            <fieldset class="field-set">
              <legend>Medicine Record</legend>
              <form:textarea path="medicalRecord" class="form-inp"
                placeholder="Medical Record" rows="5" cols="20" />
              <br> <i class="err-txt"><form:errors
                  path="medicalRecord" class="error" /></i>
            </fieldset>
          </div>
          <div class="inp">
            <fieldset class="field-set">
              <legend>Medicine</legend>
              <form:textarea path="medicine" class="form-inp"
                placeholder="Medicine" rows="5" cols="20" />
              <br> <i class="err-txt"><form:errors
                  path="medicine" class="error" /></i>
            </fieldset>
          </div>
          <div class="inp">
            <fieldset class="field-set">
              <legend>Remark</legend>
              <form:textarea path="remark" class="form-inp"
                placeholder="Remark" rows="5" cols="20" />
              <br> <i class="err-txt"><form:errors
                  path="remark" class="error" /></i>
            </fieldset>
            <form:hidden path="cost" />
          </div>
        </sec:authorize>
        <c:set var="check" value="true" />
        <sec:authorize access="hasRole('RECEPTIONIST')">
          <c:if
            test="${empty patientMedicalRecordForm.medicalRecord || empty patientMedicalRecordForm.medicine}">
            <c:set var="check" value="false" />
          </c:if>
          <form:hidden path="medicalRecord" />
          <form:hidden path="medicine" />
          <form:hidden path="remark" />
          <div class="inp">
            <c:if test="${check eq true }">
              <b>Medical Record</b> : <span>${patientMedicalRecordForm.medicalRecord }</span>
              <br>
              <b>Medicine</b> : <span>${patientMedicalRecordForm.medicine }</span>
              <br>
              <b>Remark</b> : <span>${patientMedicalRecordForm.remark }</span>
              <br>
              <br>
              <fieldset class="field-set">
                <legend>Cost</legend>
                <form:input type="Number" path="cost" class="form-inp"
                  placeholder="Cost" />
                <br> <i class="err-txt"><form:errors
                    path="cost" class="error" /></i>
              </fieldset>
            </c:if>
            <c:if test="${check eq false }">
              <p class="center-f">Please wait for doctor submission.
              </p>
            </c:if>
          </div>
        </sec:authorize>
        <div class="center-f">
          <c:if test="${check eq true }">
            <input type="submit" class="btn sub" value="Confirm">
            <input type="button" class="btn sub ${btnJs}-btn"
              value="${btnName}">
          </c:if>
          <input type="submit" name="back" class="btn sub back-btn"
            value="Back">
        </div>
      </form:form>
    </div>
  </div>
</body>