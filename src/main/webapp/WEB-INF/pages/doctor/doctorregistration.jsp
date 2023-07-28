<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<body>
  <c:if test="${not empty msg}">
    <div class="message-field">
      <p>${msg}</p>
      <c:remove var="msg" />
    </div>
  </c:if>
  <div class="wrapper">
    <div class="form-registration">
      <form:form
        action="${pageContext.request.contextPath}/addDoctorConfirm?${_csrf.parameterName}=${_csrf.token}"
        modelAttribute="doctorForm" id="form"
        enctype="multipart/form-data" class="doctor-registration">
        <c:if test="${empty doctorForm.userId}">
          <h2 class="tit-hdr">Doctor Registration</h2>
        </c:if>
        <c:if test="${not empty doctorForm.userId}">
          <h2 class="tit-hdr">Edit Doctor Info</h2>
          <form:hidden path="userId" />
        </c:if>
        <br>
        <div class="inp">
          <c:set var="filecode" value="base64" />
          <c:choose>
            <c:when
              test="${fn:contains(doctorForm.doctorPhoto, filecode)}">
              <c:set var="filename" value="${doctorForm.doctorPhoto }" />
              <c:set var="doctorPhotoData" value="${filename}"/>
            </c:when>
            <c:otherwise>
              <c:choose>
                <c:when test="${not empty doctorForm.doctorPhoto}">
                  <c:set var="filename"
                    value="${pageContext.request.contextPath}/resources/img/profile/${doctorForm.doctorPhoto}" />
                  <c:set var="doctorPhotoData" value="${doctorForm.doctorPhoto }"/>
                </c:when>
                <c:otherwise>
                  <c:set var="filename"
                    value="${pageContext.request.contextPath}/resources/img/profile/img_doctor.svg" />
                  <c:set var="doctorPhotoData" value="img_doctor.svg"/>
                </c:otherwise>
              </c:choose>
            </c:otherwise>
          </c:choose>
          <fieldset class="field-set">
            <div class="choose-photo choose" title="Change Photo">
              <img alt="doctor profile" id="dis-profile"
                src="${filename}">
            </div>
            <form:input path="doctorPhoto" value="${doctorPhotoData}"
              hidden="hidden" />
            <input name="profile" type="file" id="choose"
              accept="image/*" hidden="hidden" />
            <div class="center-f">
              <button class="btn choose-img choose" title="Choose Photo">Choose
                Profile</button>
            </div>
            <br> <i class="err-txt"><form:errors
                path="doctorPhoto" cssClass="error" /></i>
          </fieldset>
        </div>
        <div class="inp clearfix">
          <fieldset class="field-set lf">
            <legend>Doctor Name</legend>
            <form:input type="text" path="userName" class="form-inp"
              placeholder="Doctor Name" />
            <br> <i class="err-txt"><form:errors
                path="userName" cssClass="error" /></i>
          </fieldset>
          <fieldset class="field-set rg">
            <legend>Email Address</legend>
            <form:input type="text" path="userEmail" id=""
              class="form-inp" placeholder="Email Address" />
            <br> <i class="err-txt"><form:errors
                path="userEmail" cssClass="error" /></i>
          </fieldset>
        </div>
        <c:if test="${empty doctorForm.userId}">
          <div class="inp clearfix">
            <fieldset class="field-set lf">
              <legend>Phone Number</legend>
              <form:input type="text" path="userPhone" id=""
                class="form-inp" placeholder="Doctor's Phone Number" />
              <br> <i class="err-txt"><form:errors
                  path="userPhone" cssClass="error" /></i>
            </fieldset>
            <fieldset class="field-set rg">
              <legend>Doctor Password</legend>
              <form:input type="password" path="userPassword" id="password"
                class="form-inp" placeholder="Password" />
              <br> <i class="err-txt"><form:errors
                  path="userPassword" cssClass="error" /></i>
            </fieldset>
          </div>
        </c:if>
        <c:if test="${not empty doctorForm.userId}">
          <div class="inp clearfix">
            <form:hidden path="userPassword" />
            <fieldset class="field-set lf">
              <legend>Phone Number</legend>
              <form:input type="text" path="userPhone" id=""
                class="form-inp" placeholder="Doctor's Phone Number" />
              <br> <i class="err-txt"><form:errors
                  path="userPhone" cssClass="error" /></i>
            </fieldset>
          </div>
        </c:if>
        <div class="inp clearfix">
          <fieldset class="field-set rg">
            <legend>Degree</legend>
            <form:input type="text" path="doctorDegree" id=""
              class="form-inp" placeholder="Doctor's Degree" />
            <br> <i class="err-txt"><form:errors
                path="doctorDegree" cssClass="error" /></i>
          </fieldset>
          <fieldset class="field-set lf">
            <legend>Specialization</legend>
            <form:input type="text" path="doctorSpecialization" id=""
              class="form-inp" placeholder="Doctor's Specialization" />
            <br> <i class="err-txt"><form:errors
                path="doctorSpecialization" cssClass="error" /></i>
          </fieldset>
        </div>
        <div class="inp">
          <fieldset class="field-set">
            <legend>Duty Time</legend>
            <form:input type="text" path="doctorDutyTime"
              class="form-input cache" data-cache="0" id="dutytime"
              hidden="hidden" />
            <div class="d-time lf">
              <input type="time" class="form-inp time" data-time="start">
            </div>
            <div class="d-time lf">
              <input type="time" class="form-inp time" data-time="end">
            </div>
            <br> <br> <br> <br> <i class="err-txt">
              <form:errors path="doctorDutyTime" cssClass="error" />
            </i>
          </fieldset>
        </div>
        <div class="inp">
          <fieldset class="field-set rel">
            <legend>Duty Day</legend>
            <form:input path="doctorDutyDay" class="form-input cache"
              data-cache="1" hidden="hidden" id="selector-inp" />
            <div class="form-inp select-field clearfix">
              <div class="selection lf clearfix"></div>
              <span class="select-ico rg fa-usage"></span>
            </div>
            <nav class="selectors" tabindex="-1">
              <ul class="day-selector clearfix">
                <li class="days lf" data-day="Sunday">Sunday</li>
                <li class="days lf" data-day="Monday">Monday</li>
                <li class="days lf" data-day="Tuesday">Tuesday</li>
                <li class="days lf" data-day="Wednesday">Wednesday</li>
                <li class="days lf" data-day="Thursday">Thursday</li>
                <li class="days lf" data-day="Friday">Friday</li>
                <li class="days lf" data-day="Saturday">Saturday</li>
              </ul>
            </nav>
            <br> <i class="err-txt"><form:errors
                path="doctorDutyDay" cssClass="error" /></i>
          </fieldset>
        </div>
        <div class="center-f">
          <input type="submit" class="btn sub mrg-top" value="Confirm">
          <input type="button" class="btn sub ${btnJs}-btn mrg-top"
            data-reset=1 value="${btnName}"> <input
            type="submit" name="back" class="btn sub back-btn mrg-top" value="Back">
        </div>
      </form:form>
    </div>
  </div>
</body>