<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clinic Management System</title>
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/plugins/fontawesome/css/all.min.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/reset.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/common.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<body>
  <div class="wrapper home">
    <section class="sec-mainvisual home">
      <div class="mv">
        <h2>
          WE ARE HERE<br>FOR YOUR HEALTH
        </h2>
        <p>We're here when you need us. For everyday care or
          life-changing care, you can count on us to keep you and your
          loved ones safe and healthy. Good health is one of the life's
          greatest blessings. We provide best health care with low cost.</p>
        <a href="${pageContext.request.contextPath}/contact" class="btn">Find Out More</a>
      </div>
    </section>
    <div class="welcome-txt clearfix">
      <div class="container">
        <h2>Welcome Our Clinic</h2>
        <p>Welome to Our Clinic. Let us know what you need and what
          can we do for you. The best of our service is we make close up
          treatment for you. And also the doctors from us are all
          specialize ones. All the Lorem Ipsum generators on the
          Internet tend to repeat predefined chunks as necessary, making
          this the first true generator on the Internet.</p>
      </div>
    </div>
    <div class="healthcare">
      <div class="container clearfix">
        <div class="ft-right contents">
          <img
            src="${pageContext.request.contextPath}/resources/img/img_doc_patient.jpg"
            alt="Doctor_Patient">
        </div>
        <div class="ft-left contents">
          <h2>Healthy and Happy</h2>
          <p>Welome to Our Clinic. Let us know what you need and
            what can we do for you. The best of our service is we make
            close up treatment for you. And also the doctors from us are
            all specialize ones. All the Lorem Ipsum generators on the
            Internet tend to repeat predefined chunks as necessary,
            making this the first true generator on the Internet. It
            uses a dictionary of over 200 Latin words, combined with a
            handful of model.</p>
          <a href="${pageContext.request.contextPath}/about" class="btn">Read More</a>
        </div>
      </div>
    </div>
    <div class="doctor-list">
      <div class="container">
        <ul class="doc-ul clearfix">
          <c:forEach items="${doctorList }" var="doctor">
            <li class="doc-li">
              <div class="li-prof">
                <img
                  src="${pageContext.request.contextPath}/resources/img/profile/${doctor.doctorPhoto}"
                  alt="${doctor.userName }">
              </div>
              <div class="doc-detail">
                <div class="doc-hl">
                  <div class="dd-name">
                    <h3>${doctor.userName}</h3>
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
                </div>
                <div class="dd-f center">
                  <a
                    href="${pageContext.request.contextPath }/bookingInit/${doctor.userId}"
                    class="btn link">Appointment</a>
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
</body>
</html>