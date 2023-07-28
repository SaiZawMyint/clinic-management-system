<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/reset.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<body>
  <div class="wrapper">
    <div class="form-login">
      <h2 class="tit-hdr">Login Page</h2>
      <br><br>
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message }">
          <p class="login invoke-err center-f">
            Incorrect Email or Password!
          </p>
        </c:if>
      <form action="<%=request.getContextPath()%>/login" method="POST">
        <div class="login">
          <fieldset class="field-set">
            <legend>Email</legend>
            <input type="text" name="userEmail" class="form-inp"
              placeholder="Email Address" />
          </fieldset>
          <br>
          <fieldset class="field-set">
            <legend>Password</legend>
            <input type="password" name="userPassword" class="form-inp"
              placeholder="Password" />
          </fieldset>
        </div>
        <div class="center-f">
          <input type="submit" class="btn sub" value="Login">
        </div>
        <input type="hidden" name="${_csrf.parameterName}"
          value="${_csrf.token}" />
      </form>
    </div>
  </div>
</body>
</html>
