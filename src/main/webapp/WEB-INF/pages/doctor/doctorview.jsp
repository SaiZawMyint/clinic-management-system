<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<body>
  <section class="content-sec">
    <div class="hdr clearfix">
      <div class="action-f rg clearfix">
        <form action="doctorSearch" method="POST" class="lf">
          <input type="text" class="search-inp" name="search"
            placeholder="Search Doctor" required="required"><input
            type="submit" value="Search" class="link btn" /> <a
            href="${pageContext.request.contextPath}/doctorInit"
            class="btn link mrg-lft">Add More</a> <input type="hidden"
            name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
      </div>
    </div>
    <c:if test="${not empty msg}">
      <div class="message-field">
        <p>${msg}</p>
        <c:remove var="msg" />
      </div>
    </c:if>
    <c:if test="${empty doctorList }">
      <br>
      <br>
      <h2 align="center">No Doctor Data Found</h2>
    </c:if>
    <!-- check if list is emply or not -->
    <c:if test="${not empty doctorList}">
      <h2 class="center-f">Doctor List</h2>
      <br>
      <c:forEach items="${doctorList }" var="doctor">
        <div class="doc-detail-field">
          <div class="box clearfix">
            <div class="doc-prof lf">
              <img
                src="${pageContext.request.contextPath}/resources/img/profile/${doctor.doctorPhoto}"
                alt="doctor profile">
            </div>
            <div class="doc-detail rg">
              <div class="dd-f">
                <b>Name</b> : <span>${doctor.userName}</span>
              </div>
              <div class="dd-f">
                <b>Phone Number</b> : <span>${doctor.userPhone }</span>
              </div>
              <div class="dd-f">
                <b>Email Address</b> : <span>${doctor.userEmail }</span>
              </div>
              <div class="dd-f">
                <b>Degree</b> : <span>${doctor.doctorDegree }</span>
              </div>
              <div class="dd-f">
                <b>Specialization</b> : <span>${doctor.doctorSpecialization }</span>
              </div>
              <div class="dd-f">
                <b>Duty Day</b> : <span>${doctor.doctorDutyDay }</span>
              </div>
              <div class="dd-f">
                <b>Duty Time</b> : <span class="doc-time">${doctor.doctorDutyTime }</span>
              </div>
              <div class="dd-f">
                <a
                  href="${pageContext.request.contextPath }/editDoctor/${doctor.userId}"
                  class="btn link edit">Edit</a> <a
                  href="${pageContext.request.contextPath }/deleteDoctor/${doctor.userId}"
                  class="link btn delete">Delete</a>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </c:if>
  </section>
</body>