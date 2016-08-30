<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="http://code.jquery.com/jquery.js"></script>
	<script type="text/javascript">
		function doSearch() {
			$.getJSON("looseSearch", {
				CHARS : $('#searchBox').val()
			},	
			function(books) {
				alert("Response Received");
			});
		 }
		
	</script>
	</head>
	<body>
		<input id="searchBox" type="text" onKeyUp="doSearch();" />
		<div id="results>Results will appeara here....</div>
	</body>
	</html>