<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<body>
  <div class="wrapper">
    <div class="form-registration">
      <form:form
        action="${pageContext.request.contextPath}/addBookingConfirm"
        modelAttribute="bookingForm" id="form">
        <c:if test="${empty bookingForm.bookingId}">
          <h2 class="tit-hdr">Book An Appointment</h2>
          <form:hidden path="userId" value="${userId}" />
          <br>
          <div class="inp clearfix">
            <fieldset class="field-set lf">
              <legend>Name</legend>
              <form:input type="text" path="patientName"
                class="form-inp" placeholder="Patient Name" />
              <br> <i class="err-txt"><form:errors
                  path="patientName" class="error" /></i>
            </fieldset>
            <fieldset class="field-set rg">
              <legend>Phone</legend>
              <form:input type="text" path="contactPhone"
                class="form-inp" placeholder=" Contact Phone" />
              <br> <i class="err-txt"><form:errors
                  path="contactPhone" class="error" /></i>
            </fieldset>
          </div>
          <div class="center-f">
            <div class="mrg">
              Gender :
              <form:radiobutton id="male" path="patientGender" value="Male" />
              <label for="male">Male</label>
              <form:radiobutton id="female" path="patientGender" value="Female" />
              <label for="female">Female</label> <br> <i class="err-txt"><form:errors
                  path="patientGender" class="error" /></i>
            </div>
            <input type="submit" class="btn sub" value="Confirm">
            <input type="button" class="btn sub ${btnJs}-btn"
              value="${btnName}"> <input type="submit" name="back"
              class="btn sub back-btn" value="Back">
          </div>
        </c:if>
        <c:if test="${not empty bookingForm.bookingId}">
          <h2 class="tit-hdr">Edit Booking Info</h2>
          <form:hidden path="bookingId" />
          <form:hidden path="patientName" />
          <form:hidden path="contactPhone" />
          <form:hidden path="patientGender" />
          <form:hidden path="userId" />
          <br>
          <div class="inp clearfix">
            <div class="lf">
              Status :
              <form:select path="status" class="select-status">
                <form:option value="Pending" label="Pending" />
                <form:option value="Accepted" label="Accepted" />
                <form:option value="Rejected" label="Rejected" />
              </form:select>
            </div>
            <c:choose>
              <c:when test="${bookingForm.status == 'Rejected' }">
                <c:set var="hos" value="show"></c:set>
              </c:when>
              <c:otherwise>
                <c:set var="hos" value="hide"></c:set>
              </c:otherwise>
            </c:choose>
            <fieldset class="field-set ${hos} reject-msg rg">
              <legend>Reject Message</legend>
              <form:textarea path="rejectMsg" id="rj-msg"
                class="form-inp" placeholder="Reject Message" rows="3"
                cols="20" />
              <br> <i class="err-txt"><form:errors
                  path="rejectMsg" class="error" /></i>
            </fieldset>
            <br>
          </div>
          <div class="center-f">
            <input type="submit" class="btn sub" value="Confirm">
            <input type="button" class="btn sub ${btnJs}-btn"
              data-reset=2 value="${btnName}"> <input
              type="submit" name="back" class="btn sub back-btn" value="Back">
          </div>
        </c:if>
      </form:form>
    </div>
  </div>
</body>