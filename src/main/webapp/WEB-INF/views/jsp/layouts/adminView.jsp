<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<htEnide - Eclipse bootstrap e4ml lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title><tiles:getAsString name="title" /></title>

<script src="https://code.jquery.com/jquery-1.12.3.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.jqueryui.min.js"></script>
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- ********************************************************************* -->


<link
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"
	rel="stylesheet" />
<link
	href="https://cdn.datatables.net/1.10.12/css/dataTables.jqueryui.min.css"
	rel="stylesheet" />

<!-- Custom CSS -->
<link href="${contextPath}/resources/css/styles.css" rel="stylesheet">
<link href="${contextPath}/resources/css/public.css" rel="stylesheet">
</script>
<!-- Include Modernizr in the head, before any other Javascript -->
<script
	src="${pageContext.request.contextPath}/resources/bootstrap/js/modernizr-2.6.2.min.js">
</script>

<tiles:insertAttribute name="table" />
<!-- Display messages -->
<script type="text/javascript">
$( document ).ready(function() {
	
	var message = "${message}"
	if(message != ""){
	}
	
	<c:remove var="message" scope="request" />
});
 

</script>
<style>
.btn-space {
    margin-top: 5px;
}
</style>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div id="main">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html