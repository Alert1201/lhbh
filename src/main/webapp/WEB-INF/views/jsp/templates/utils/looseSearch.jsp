<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	function doSearch() {
		$.ajax({
			dataType : "json",
			url : "looseSearch",
			success : function(data) {
				confirm(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.status);
				alert(textStatus);
			}
		});
	}
</script>
</head>
<body>
	<input id="searchBox" type="text" onKeyUp="doSearch();" />
	<div id="results"></div>
</body>
</html>