<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
  prefix="c"%>
<sec:authorize access="hasRole('ADMIN')">
  <div class="top-nav">
    <div class="img-hdr">
      <div class="admin-prof">
        <img
          src="${pageContext.request.contextPath}/resources/img/img_admin.png"
          alt="Admin">
      </div>
      <h2 class="prof-name">Admin</h2>
    </div>
    <ul class="lf-list">
      <li class=""><a
        href="${pageContext.request.contextPath}/dashboard"
        class="link btn fa-usage home-ico <c:if test='${curPage == "home" }'>active</c:if>"><span>Home</span></a>
      </li>
      <li><a href="${pageContext.request.contextPath}/doctorView"
        class="link btn fa-usage doctor-ico <c:if test='${curPage == "doctorView" }'>active</c:if>">Doctor</a></li>
      <li><a
        href="${pageContext.request.contextPath}/receptionistView"
        class="link btn fa-usage receptionist-ico <c:if test='${curPage == "receptionistView" }'>active</c:if>">Receptionist</a></li>
    </ul>
  </div>
  <div class="buttom-nav">
    <ul class="lf-list">
      <li><form action="<%=request.getContextPath()%>/logout"
          method="POST">
          <button type="submit" class="link btn fa-usage logout-ico">LOGOUT</button>
          <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}" />
        </form></li>
    </ul>
  </div>
</sec:authorize>
<sec:authorize access="hasRole('DOCTOR')">
  <div class="top-nav">
    <div class="img-hdr">
      <div class="admin-prof">
        <img
          src="${pageContext.request.contextPath}/resources/img/profile/${doctorInfo.doctorPhoto}"
          alt="${doctorInfo.userName}">
      </div>
      <h2 class="prof-name">${doctorInfo.userName}</h2>
    </div>
    <ul class="lf-list">
      <li class=""><a
        href="${pageContext.request.contextPath}/dashboard"
        class="link btn fa-usage home-ico <c:if test='${curPage == "home" }'>active</c:if>"><span>Home</span></a>
      </li>
      <li><a
        href="${pageContext.request.contextPath}/doctorSchedule"
        class="link btn fa-usage doctor-sec-ico <c:if test='${curPage == "doctorSchedule" }'>active</c:if>">Schedule</a></li>
      <li><a
        href="${pageContext.request.contextPath}/patientInfoView"
        class="link btn fa-usage patient-info-ico <c:if test='${curPage == "patientInfoView" }'>active</c:if>">Patient
          Info</a></li>
      <li>
      <li><a
        href="${pageContext.request.contextPath}/medicalRecordView"
        class="link btn fa-usage medical-record-ico <c:if test='${curPage == "medicalRecordView" }'>active</c:if>">Medical
          Record</a></li>
    </ul>
  </div>
  <div class="buttom-nav">
    <ul class="lf-list">
      <li><form action="<%=request.getContextPath()%>/logout"
          method="POST">

          <button type="submit" class="link btn fa-usage logout-ico">LOGOUT</button>
          <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}" />
        </form></li>
    </ul>
  </div>
</sec:authorize>
<sec:authorize access="hasRole('RECEPTIONIST')">
  <div class="top-nav">
    <div class="img-hdr">
      <div class="admin-prof">
        <img
          src="${pageContext.request.contextPath}/resources/img/img_doctor_d.png"
          alt="Admin">
      </div>
      <h2 class="prof-name">Receptionist</h2>
    </div>
    <ul class="lf-list">
      <li class=""><a
        href="${pageContext.request.contextPath}/dashboard/"
        class="link btn fa-usage home-ico <c:if test='${curPage == "home" }'>active</c:if>"><span>Home</span></a>
      </li>
      <li><a
        href="${pageContext.request.contextPath}/patientInfoView"
        class="link btn fa-usage patient-info-ico <c:if test='${curPage == "patientInfoView" }'>active</c:if>">Patient
          Info</a></li>
      <li>
      <li><a
        href="${pageContext.request.contextPath}/todayPatientView"
        class="link btn fa-usage tdy-patient-ico <c:if test='${curPage == "todayPatientView" }'>active</c:if>">Today
          Patient List</a></li>
      <li><a
        href="${pageContext.request.contextPath}/medicalRecordView"
        class="link btn fa-usage medical-record-ico <c:if test='${curPage == "medicalRecordView" }'>active</c:if>">Medical
          Record</a></li>
    </ul>
  </div>
  <div class="buttom-nav">
    <ul class="lf-list">
      <li><form action="<%=request.getContextPath()%>/logout"
          method="POST">
          <button type="submit" class="link btn fa-usage logout-ico">LOGOUT</button>
          <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}" />
        </form></li>
    </ul>
  </div>
</sec:authorize>
