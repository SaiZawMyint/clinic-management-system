<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/plugins/css/responsive.dataTables.min.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/plugins/css/jquery.dataTables.min.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/plugins/fontawesome/css/all.min.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/reset.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/common.css" />
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
  <div class="wrapper">
    <tiles:insertAttribute name="header" />
    <section class="admin-dashboard das clearfix">
      <div class="left-navigator lf">
        <tiles:insertAttribute name="menu" />
        <div class="dash btn-gnavi fa-usage ad-ico"></div>
      </div>
      <div class="rg-view rg">
        <tiles:insertAttribute name="dashboard" />
      </div>
    </section>
    <footer id="footer">
      <tiles:insertAttribute name="footer" />
    </footer>
  </div>
  <script
    src="<%=request.getContextPath()%>/resources/plugins/js/jquery-3.6.0.js"></script>
  <script
    src="<%=request.getContextPath()%>/resources/plugins/js/datatables.js"></script>
  <script
    src="<%=request.getContextPath()%>/resources/plugins/js/jquery.heightLine.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/common.js"></script>
</body>
</html>