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
        <c:if test="${empty all}">
          <form action="${pageContext.request.contextPath}/recordSearch"
            method="POST" class="lf">
            <input type="text" class="search-inp" name="search"
              placeholder="Search Medical Record" required="required"><input
              type="submit" value="Search" class="btn link" /> <input
              type="hidden" name="${_csrf.parameterName}"
              value="${_csrf.token}" />
               <sec:authorize access="hasAnyRole('DOCTOR','RECEPTIONIST')">
              <a
                href="${pageContext.request.contextPath}/addPatientMedicalRecord/${pid}"
                class="btn link mrg-lft">Add More</a>
            </sec:authorize>
          </form>
        </c:if>
        <c:if test="${not empty all}">
          <form action="recordSearchAll" method="POST" class="lf">
            <input type="text" class="search-inp" name="search"
              placeholder="Search Medical Record" required="required"><input
              type="submit" value="Search" class="link btn" />
            <sec:authorize access="hasRole('RECEPTIONIST')">
              <a
                href="${pageContext.request.contextPath}/uploadMedicalRecordConfirm"
                class="btn link mrg-lft">Upload</a>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('DOCTOR','RECEPTIONIST')">
              <a
                href="${pageContext.request.contextPath}/addPatientMedicalRecord/${pid}"
                class="btn link mrg-lft">Add More</a>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ADMIN','RECEPTIONIST')">
              <c:if test="${not empty patientMedicalRecordList}">
                <a
                  href="${pageContext.request.contextPath}/downloadMedicalRecord"
                  class="btn link mrg-lft">Download</a>
              </c:if>
            </sec:authorize>
            <input type="hidden" name="${_csrf.parameterName}"
              value="${_csrf.token}" />
          </form>
        </c:if>
      </div>
    </div>
    <c:if test="${not empty msg}">
      <div class="message-field">
        <p>${msg}</p>
        <c:remove var="msg" />
      </div>
    </c:if>
    <c:if test="${empty patientMedicalRecordList}">
      <br>
      <br>
      <h2 align="center">No Medical Record Data Found</h2>
      <c:if test="${empty all }">
        <sec:authorize access="hasAnyRole('DOCTOR','RECEPTIONIST')">
          <div class="center-f">
            <br> <br>
            <h4>Add Medical Record for this patient</h4>
            <a
              href="${pageContext.request.contextPath}/addPatientMedicalRecord/${pid}"
              class="btn link mrg-lft">Add Medical Record</a>
          </div>
        </sec:authorize>
      </c:if>
    </c:if>
    <!-- check if list is emply or not -->
    <c:if test="${not empty patientMedicalRecordList}">
      <h2>Patient Medical Record List</h2>
      <br>
      <c:if test="${empty all}">
        <h4>Patient Name&emsp;&emsp;: ${name}</h4>
        <h4>Patient Age&emsp;&emsp;&emsp;: ${age}</h4>
        <h4>Patient Gender&emsp;&nbsp;: ${gender}</h4>
        <br>
      </c:if>
      <!-- table for showing data -->
      <table class="cell-border hover responsive nowrap"
        style="width: 100%;font-size: .8em;">
        <thead>
          <tr>
            <th>No.</th>
            <c:if test="${not empty all}">
              <th>Patient Name</th>
            </c:if>
            <th>Doctor Name</th>
            <th>Date</th>
            <th>Record</th>
            <th>medicine</th>
            <sec:authorize access="hasAnyRole('ADMIN','RECEPTIONIST')">
              <th>cost</th>
            </sec:authorize>
            <th>remark</th>
            <sec:authorize access="hasAnyRole('DOCTOR','RECEPTIONIST')">
              <c:if test="${empty all}">
                <th>Edit</th>
              </c:if>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('RECEPTIONIST')">
              <c:if test="${empty all}">
                <th>Delete</th>
              </c:if>
            </sec:authorize>
          </tr>
        </thead>
        <tbody>
          <!-- looping and print out all the data -->
          <c:forEach items="${patientMedicalRecordList}"
            var="patientMedicalRecord" varStatus="loopCount">
            <c:set var="pid" value="${patientMedicalRecord.patientId}" />
            <tr>
              <td align="center"><c:out value="${loopCount.count}" /></td>
              <c:if test="${not empty all}">
                <td align="left">${patientMedicalRecord.patientName}</td>
              </c:if>
              <td align="left">${patientMedicalRecord.userName}</td>
              <td align="left">${patientMedicalRecord.recordDateTime}</td>
              <td align="left">${patientMedicalRecord.medicalRecord}</td>
              <td align="left">${patientMedicalRecord.medicine}</td>
              <sec:authorize access="hasAnyRole('ADMIN','RECEPTIONIST')">
                <td align="right">${patientMedicalRecord.cost}</td>
              </sec:authorize>
              <td align="left">${patientMedicalRecord.remark}</td>
              <sec:authorize
                access="hasAnyRole('DOCTOR','RECEPTIONIST')">
                <c:if test="${empty all}">
                  <td align="center"><a
                    href="${pageContext.request.contextPath}/editPatientMedicalRecord/${patientMedicalRecord.patientMedicalRecordId}"
                    class="btn link edit">Edit</a></td>
                </c:if>
              </sec:authorize>
              <sec:authorize access="hasAnyRole('RECEPTIONIST')">
                <c:if test="${empty all}">
                  <td align="center"><a
                    href="${pageContext.request.contextPath}/deletePatientMedicalRecord/${patientMedicalRecord.patientMedicalRecordId}"
                    class="btn link delete">Delete</a></td>
                </c:if>
              </sec:authorize>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
  </section>
</body>