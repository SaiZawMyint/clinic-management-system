<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty msg}">
  <div class="message-field">
    <p>${msg}</p>
    <c:remove var="msg" />
  </div>
</c:if>
<header class="header clearfix">
  <div class="btn-gnavi">
    <span></span> <span></span> <span></span>
  </div>
  <div class="container clearfix">
    <h1 class="ft-left logo">
      <a href="${pageContext.request.contextPath}/init" class="link"><img
        src="${pageContext.request.contextPath}/resources/img/img_logo.png"
        alt="Clinic"></a>
    </h1>
    <nav id="global-navi" class="rg">
      <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/init"
          class="link <c:if test="${hdrActiver eq 'home' }">active</c:if>">HOME</a></li>
        <li><a href="${pageContext.request.contextPath}/about"
          class="link <c:if test="${hdrActiver eq 'about' }">active</c:if>">ABOUT US</a></li>
        <li><a href="${pageContext.request.contextPath}/contact"
          class="link <c:if test="${hdrActiver eq 'contact' }">active</c:if>">CONTACT US</a></li>
        <li><c:if test="${empty loggedInId }">
            <a href="${pageContext.request.contextPath}/login"
              class="link <c:if test="${hdrActiver eq 'login' }">active</c:if>">LOGIN</a>
          </c:if> <c:if test="${not empty loggedInId }">
            <a href="${pageContext.request.contextPath}/dashboard"
              class="link <c:if test="${hdrActiver eq 'dashboard' }">active</c:if>">DASHBOARD</a>
          </c:if></li>
      </ul>
    </nav>
  </div>
</header>