
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
$(document).ready(function() {
	$(function() {
		var buttonVal;
		$("button").click(function() {
		    if(this.id=="save"){
		    	return true;
		    } else  {
		    	this.action = "${pageContext.request.contextPath}/meters/cancel"
		    		$('#updateForm').attr("action", "${pageContext.request.contextPath}/meters/cancel");
		    		return true;
		    }
		});
		$('#updateForm').submit(function(event) {
			return true;
		});
	});
});

</script>
<div id="page">
	<br />
	<form:form id="updateForm" class="form-horizontal" action="save" method="post"
		commandName="meterForm">
		<fieldset>

			<!-- Form Name -->
			<legend>Update Meter</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="meter">Meter</label>
				<div class="col-md-4">
					<form:input path="meter" id="meter" type="text"
						class="form-control input-md" />
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="description">Description</label>
				<div class="col-md-5">
					<form:input path="description" id="description" type="text"
						class="form-control input-md" />
				</div>
			</div>
			<form:hidden path="id" />
			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="save"></label>
				<div class="col-md-8">
					<form:button id="save" name="save" value="save" class="btn btn-success">Save</form:button>
					<form:button id="cancel" name="cancel"  value="cancel"  class="btn btn-success" >Cancel</form:button>
				</div>
			</div>

		</fieldset>
	</form:form>
</div>
<!-- id=page -->
</div>