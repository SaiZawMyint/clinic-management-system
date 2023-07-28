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
        <c:if test="${not empty booking}">
          <form
            action="${pageContext.request.contextPath}/bookingSearch"
            method="POST" class="lf">
            <input type="text" class="search-inp" name="search"
              placeholder="Search Today Booking" required="required">
            <input type="submit" value="Search" class="btn link" />
            <sec:authorize access="hasRole('RECEPTIONIST')">
              <c:if test="${not empty bookingList}">
                <a
                  href="${pageContext.request.contextPath}/downloadBooking"
                  class="btn link mrg-lft">Download</a>
              </c:if>
            </sec:authorize>
            <a href="${pageContext.request.contextPath}/allBookingView"
              class="btn link mrg-lft">All Booking</a> <input
              type="hidden" name="${_csrf.parameterName}"
              value="${_csrf.token}" />
          </form>
        </c:if>
        <c:if test="${empty booking}">
          <form
            action="${pageContext.request.contextPath}/allBookingSearch"
            method="POST" class="lf">
            <input type="text" class="search-inp" name="search"
              placeholder="Search All Booking" required="required">
            <input type="submit" value="Search" class="link btn" />
            <sec:authorize access="hasRole('RECEPTIONIST')">
                <a
                  href="${pageContext.request.contextPath}/downloadBooking"
                  class="btn link mrg-lft">Download</a>
            </sec:authorize>
            <a href="${pageContext.request.contextPath}/bookingView"
              class="btn link mrg-lft">Today Booking</a> <input
              type="hidden" name="${_csrf.parameterName}"
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
    <c:if test="${empty bookingList}">
      <br>
      <br>
      <h2 align="center">No Booking Data Found</h2>
    </c:if>
    <!-- check if list is emply or not -->
    <c:if test="${not empty bookingList}">
      <c:if test="${empty booking}">
        <h2>All Booking List</h2>
      </c:if>
      <c:if test="${not empty booking}">
        <h2>Today Booking List</h2>
      </c:if>
      <br>
      <!-- table for showing data -->
      <table class="cell-border hover responsive nowrap"
        style="width: 100%;font-size: .8em;">
        <thead>
          <tr>
            <th width="30px">No.</th>
            <th>Name</th>
            <th>Doctor Name</th>
            <th>Phone</th>
            <th>Gender</th>
            <th>Date</th>
            <th>Time</th>
            <th>Status</th>
            <th>Message</th>
            <sec:authorize access="hasRole('RECEPTIONIST')">
              <th>Edit</th>
            </sec:authorize>
          </tr>
        </thead>
        <tbody>
          <!-- looping and print out all the data -->
          <c:forEach items="${bookingList}" var="booking"
            varStatus="loopCount">
            <tr>
              <td align="center"><c:out value="${loopCount.count}" /></td>
              <td align="left">${booking.patientName}</td>
              <td align="left">${booking.userName}</td>
              <td align="center">${booking.contactPhone}</td>
              <td align="left">${booking.patientGender}</td>
              <td align="left">${booking.bookingDate}</td>
              <td align="left">${booking.bookingTime}</td>
              <td align="left">${booking.status}</td>
              <td align="left">${booking.rejectMsg}</td>
              <sec:authorize access="hasRole('RECEPTIONIST')">
                <td align="center">
                <c:choose>
                  <c:when test="${booking.status eq 'Rejected' }">
                    <input type="button" class="btn link edit disabled" disabled="disabled" value="Rejected"/>
                  </c:when>
                  <c:otherwise>
                    <a
                  href="${pageContext.request.contextPath}/editBooking/${booking.bookingId}"
                  class="btn link edit">Edit</a>
                  </c:otherwise>
                </c:choose>  
                  </td>
              </sec:authorize>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
  </section>
</body>