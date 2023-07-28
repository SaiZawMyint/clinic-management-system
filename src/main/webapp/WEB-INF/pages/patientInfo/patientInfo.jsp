<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<body>
  <div class="wrapper">
    <div class="form-registration">
      <form:form
        action="${pageContext.request.contextPath}/addPatientInfoConfirm"
        modelAttribute="patientInfoForm" id="form">
        <c:if test="${empty patientInfoForm.patientId}">
          <h2 class="tit-hdr">Patient Registration</h2>
        </c:if>
        <c:if test="${not empty patientInfoForm.patientId}">
          <h2 class="tit-hdr">Edit Patient Info</h2>
          <form:hidden path="patientId" />
        </c:if>
        <br>
        <div class="inp clearfix">
          <fieldset class="field-set lf">
            <legend>Name</legend>
            <form:input type="text" path="patientName" class="form-inp"
              placeholder="Patient Name" />
            <br> <i class="err-txt"><form:errors
                path="patientName" class="error" /></i>
          </fieldset>
          <fieldset class="field-set rg">
            <legend>Age</legend>
            <form:input type="number" path="patientAge" class="form-inp"
              placeholder=" Patient Age" />
            <br> <i class="err-txt"><form:errors
                path="patientAge" class="error" /></i>
          </fieldset>
        </div>
        <div class="inp clearfix">
          <fieldset class="field-set lf">
            <legend>Phone Number</legend>
            <form:input type="text" path="patientPhone" class="form-inp"
              placeholder="Patient's Phone Number" />
            <br> <i class="err-txt"><form:errors
                path="patientPhone" class="error" /></i>
          </fieldset>
          <fieldset class="field-set rg">
            <legend>Address</legend>
            <form:input type="text" path="patientAddress"
              class="form-inp" placeholder="Patient Address" />
            <br> <i class="err-txt"><form:errors
                path="patientAddress" class="error" /></i>
          </fieldset>
        </div>
        <div class="inp clearfix">
          <fieldset class="field-set lf">
            <legend>Choose Doctor</legend>
            <c:set var="id" value="" />
            <c:set var="value" value="Choose Doctor" />
            <c:set var="check" value="false" />
            <c:if test="${not empty patientInfoForm.doctorId}">
              <c:forEach items="${doctorList}" var="doctor">
                <c:if
                  test="${doctor.userId == patientInfoForm.doctorId }">
                  <c:set var="id" value="${doctor.userId }" />
                  <c:set var="value" value="${doctor.userName}" />
                  <c:set var="check" value="true" />
                </c:if>
              </c:forEach>
            </c:if>
            <form:input path="doctorId" hidden="hidden" id="doctor-id" />
            <select class="form-inp select-doc">
              <option value="${id}">${value}</option>
              <c:forEach items="${doctorList}" var="doctor">
                <c:if test="${check eq true }">
                  <c:if
                    test="${doctor.userId != patientInfoForm.doctorId }">
                    <option value="${doctor.userId }">${doctor.userName}</option>
                  </c:if>
                </c:if>
                <c:if test="${check eq false }">
                  <option value="${doctor.userId }"
                    data-id="${doctor.userId }">${doctor.userName}</option>
                </c:if>
              </c:forEach>
            </select> <i class="err-txt"><form:errors path="doctorId"
                class="error" /></i>
          </fieldset>
          <fieldset class="field-set rg">
            <legend>Gender</legend>
            <div class="mrg form-inp">
              <form:radiobutton path="patientGender" id="male"
                value="Male" />
              <label for="male">Male</label>
              <form:radiobutton path="patientGender" id="female"
                value="Female" />
              <label for="female">Female</label> <br>
            </div>
            <i class="err-txt"><form:errors path="patientGender"
                class="error" /></i>
          </fieldset>
        </div>
        <br>
        <br>
        <div class="center-f">
          <input type="submit" class="btn sub" value="Confirm">
          <input type="button" class="btn sub ${btnJs}-btn"
            value="${btnName}" data-clear=1> <input
            type="submit" name="back" class="btn sub back-btn"
            value="Back">
        </div>
      </form:form>
    </div>
  </div>
</body>