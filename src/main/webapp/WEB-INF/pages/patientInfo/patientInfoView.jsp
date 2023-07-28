<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
<%@ page session="true"%>
<body>
  <section class="content-sec">
    <div class="hdr clearfix">
      <div class="action-f rg clearfix">
        <form action="patientInfoSearch" method="POST" class="lf">
          <input type="text" class="search-inp" name="search"
            placeholder="Search Patient" required="required"><input
            type="submit" value="Search" class="btn link" />
          <sec:authorize access="hasRole('RECEPTIONIST')">
            <a href="${pageContext.request.contextPath}/patientInit"
              class="btn link mrg-lft">Add</a>
            <a
              href="${pageContext.request.contextPath}/uploadPatientInfoConfirm"
              class="btn link mrg-lft">Upload</a>
          </sec:authorize>

          <sec:authorize access="hasAnyRole('ADMIN','RECEPTIONIST')">
            <c:if test="${not empty patientInfoList}">
              <a
                href="${pageContext.request.contextPath}/downloadPatientInfo"
                class="btn link mrg-lft">Download</a>
            </c:if>
          </sec:authorize>

          <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}" />
        </form>
      </div>
    </div>
    <c:if test="${not empty msg}">
      <div class="message-field">
        <p>${msg}</p>
        <c:remove var="msg" />
      </div>
    </c:if>
    <c:if test="${empty patientInfoList}">
      <br>
      <br>
      <h2 align="center">No Patient Data Found</h2>
    </c:if>
    <!-- check if list is emply or not -->
    <c:if test="${not empty patientInfoList}">
      <h2>PatientInfo List</h2>
      <br>
      <!-- table for showing data -->
      <table class="cell-border hover responsive nowrap"
        style="width: 100%">
        <thead>
          <tr>
            <th>No.</th>
            <th>Name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Phone</th>
            <sec:authorize access="hasRole('RECEPTIONIST')">
              <th>Today</th>
              <th>Edit</th>
              <th>Delete</th>
            </sec:authorize>
            <th>Medical Record</th>
          </tr>
        </thead>
        <tbody>
          <!-- looping and print out all the data -->
          <c:forEach items="${patientInfoList}" var="patientInfo"
            varStatus="loopCount">
            <tr>
              <td align="center"><c:out value="${loopCount.count}" /></td>
              <td align="left">${patientInfo.patientName}</td>
              <td align="center">${patientInfo.patientAge}</td>
              <td align="left">${patientInfo.patientGender}</td>
              <td align="left">${patientInfo.patientAddress}</td>
              <td align="left">${patientInfo.patientPhone}</td>
              <sec:authorize access="hasRole('RECEPTIONIST')">
                <td align="center"><a
                  href="${pageContext.request.contextPath}/addTodayPatient/${patientInfo.patientId}"
                  class="btn link edit">Add Today</a></td>
                <td align="center"><a
                  href="${pageContext.request.contextPath}/editPatientInfo/${patientInfo.patientId}"
                  class="btn link edit">Edit</a></td>
                <td align="center"><a
                  href="${pageContext.request.contextPath}/deletePatientInfo/${patientInfo.patientId}"
                  class="btn link delete">Delete</a></td>
              </sec:authorize>
              <td align="center"><a
                href="${pageContext.request.contextPath}/medicalRecordView/${patientInfo.patientId}"
                class="btn link med">Medical Record</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
  </section>
</body>