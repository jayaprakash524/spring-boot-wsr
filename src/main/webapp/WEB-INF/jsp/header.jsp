<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" type="text/css"
	href="static/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="static/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css"
	href="static/css/bootstrap-datepicker3.standalone.min.css" />
<link rel="stylesheet" type="text/css" href="static/css/app.css" />

<script type="text/javascript" src="static/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="static/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="static/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="static/js/app.js"></script>
<title>Week Status Report - Form</title>
</head>
<div id="header">
	<span id="icon" class="glyphicon glyphicon-edit"></span>
	<a href="./"><span id="headerText">Weekly Status Report</span></a>
	
	<span class="pull-right" id="viewOrExport">
		<a href="./viewOrExportWSR" class="btn btn-info center-block">View/Export</a>
	</span>
</div>
