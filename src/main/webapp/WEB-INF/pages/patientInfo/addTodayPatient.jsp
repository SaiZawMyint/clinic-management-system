<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<body>
  <div class="form-registration">
    <form:form action="${pageContext.request.contextPath}/addTodayPatient" method="POST" id="form"
      modelAttribute="patientInfoForm">
      <form:hidden path="patientId" />
      <div class="inp">
        <fieldset class="field-set">
          <legend>Choose Doctor</legend>
          <c:set var="id" value="" />
          <c:set var="value" value="Choose Doctor" />
          <c:set var="check" value="false" />
          <c:if test="${not empty patientInfoForm.doctorId}">
            <c:forEach items="${doctorList}" var="doctor">
              <c:if test="${doctor.userId == patientInfoForm.doctorId }">
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
          </select>
        </fieldset>
      </div>
      <br>
        <br>
        <div class="center-f">
          <input type="submit" class="btn sub" value="Confirm">
          <input type="button" class="btn sub reset-btn"
            value="Reset" data-clear=1> <input
            type="submit" name="back" class="btn sub back-btn"
            value="Back">
        </div>
    </form:form>
  </div>
</body>