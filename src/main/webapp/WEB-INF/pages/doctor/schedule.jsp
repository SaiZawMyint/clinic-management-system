<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
  <div class="field">
    <h3 class="center-f">Doctor's Schedule</h3>
    <ul class="blk-list small se">
      <c:if test="${empty doctorSchedule }">
        <li>
          <div class="img-f">
            <h2>No Schedule</h2>
          </div>
          <div class="contents">
            <p>No Schedule found.</p>
          </div>
        </li>
      </c:if>
      <c:forEach items="${doctorSchedule }" var="schedule">
        <li class="sec-activer schedule">
          <div class="img-f">
            <h2 class="dow">${schedule.value.day }</h2>
          </div>
          <div class="contents">
            <h3>Siting Time</h3>
            <p class="doc-time">${schedule.value.time }</p>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>
</body>