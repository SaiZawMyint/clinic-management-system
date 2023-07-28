<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<body>
  <section class="content-sec">
    <div class="hdr clearfix">
      <div class="action-f rg clearfix">
        <form action="receptionistSearch" method="POST" class="lf">
          <input type="text" class="search-inp" name="search"
            placeholder="Search Receptionist" required="required"><input
            type="submit" value="Search" class="btn link" /> <a
            href="${pageContext.request.contextPath}/receptionistInit"
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
    <c:if test="${empty receptionistList}">
      <br>
      <br>
      <h2 align="center">No Receptionist Data Found</h2>
    </c:if>
    <!-- check if list is emply or not -->
    <c:if test="${not empty receptionistList}">
      <h2>Receptionist List</h2>
      <br>
      <!-- table for showing data -->
      <table class="cell-border hover responsive nowrap"
        style="width: 100%">
        <thead>
          <tr>
            <th>No.</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <!-- looping and print out all the data -->
          <c:forEach items="${receptionistList}" var="receptionist"
            varStatus="loopCount">
            <tr>
              <td align="center"><c:out value="${loopCount.count}" /></td>
              <td align="left">${receptionist.userName}</td>
              <td align="center">${receptionist.userPhone}</td>
              <td align="left">${receptionist.userEmail}</td>
              <td align="center"><a
                href="${pageContext.request.contextPath}/editReceptionist/${receptionist.userId}"
                class="btn link edit">Edit</a></td>
              <td align="center"><a
                href="${pageContext.request.contextPath}/deleteReceptionist/${receptionist.userId}"
                class="btn link delete">Delete</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
  </section>