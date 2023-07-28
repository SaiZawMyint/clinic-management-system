<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core"
  prefix="c"%>
<body>
  <sec:authorize access="hasRole('ADMIN')">
    <div class="field">
      <h2 class="ttl">Staffs</h2>
      <ul class="blk-list">
        <li>
          <div class="img-f">
            <img
              src="${pageContext.request.contextPath}/resources/img/img_medicare_dot.png"
              alt="Doctor">
          </div>
          <div class="contents">
            <h2>Doctors</h2>
            <p>View all Doctor from clinic.</p>
            <a href="${pageContext.request.contextPath}/doctorView"
              class="link btn">View</a>
          </div>
        </li>
        <li>
          <div class="img-f">
            <img
              src="${pageContext.request.contextPath}/resources/img/img_recep.png"
              alt="Receptionist">
          </div>
          <div class="contents">
            <h2>Receptionists</h2>
            <p>View all Receptionists from clinic.</p>
            <a
              href="${pageContext.request.contextPath}/receptionistView"
              class="link btn">View</a>
          </div>
        </li>
      </ul>
    </div>
    <div class="field">
      <h2 class="ttl">Patients</h2>
      <ul class="blk-list small">
        <li>
          <div class="hover-stg">
            <a href="bookingView" class="link btn">Details</a>
          </div>
          <div class="img-f">
            <img
              src="${pageContext.request.contextPath}/resources/img/img_booking_bg.jpg"
              alt="Booking">
          </div>
          <div class="contents">
            <p>Bookings</p>
          </div>
        </li>
        <li>
          <div class="hover-stg">
            <a href="patientInfoView" class="link btn">Details</a>
          </div>
          <div class="img-f">
            <img
              src="${pageContext.request.contextPath}/resources/img/img_patient_info.jpg"
              alt="Patients" class="img-h">
          </div>
          <div class="contents">
            <p>Patients</p>
          </div>
        </li>
        <li>
          <div class="hover-stg">
            <a href="todayPatientView" class="link btn">Details</a>
          </div>
          <div class="img-f">
            <img
              src="${pageContext.request.contextPath}/resources/img/img_today_patient_list_bg.jpeg"
              alt="Today Patient Lists" class="img-h">
          </div>
          <div class="contents">
            <p>Today Pateints list</p>
          </div>
        </li>
        <li>
          <div class="hover-stg">
            <a href="medicalRecordView" class="link btn">Details</a>
          </div>
          <div class="img-f">
            <img
              src="${pageContext.request.contextPath}/resources/img/img_medical_record.jpg"
              alt="Today Patient Lists" class="img-h">
          </div>
          <div class="contents">
            <p>Medical Records</p>
          </div>
        </li>
      </ul>
    </div>
  </sec:authorize>

  <sec:authorize access="hasRole('DOCTOR')">
    <div class="dashboard">
      <jsp:include page="../patientInfo/todayPatientList.jsp" />
    </div>
  </sec:authorize>

  <sec:authorize access="hasRole('RECEPTIONIST')">
    <div class="dashboard">
      <jsp:include page="../booking/bookingView.jsp" />
    </div>
  </sec:authorize>
</body>