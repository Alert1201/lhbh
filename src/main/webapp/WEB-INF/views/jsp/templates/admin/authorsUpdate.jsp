
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style type="text/css">
/* Adjust feedback icon position */
</style>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$(function() {
							var buttonVal;
							$("button")
									.click(
											function() {
												if (this.id == "save") {
													return true;
												} else {
													this.action = "${pageContext.request.contextPath}/authors/cancel"
													$('#updateForm')
															.attr("action",
																	"${pageContext.request.contextPath}/authors/cancel");
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
	<form:form id="updateForm" class="form-horizontal" action="save"
		method="post" commandName="authorForm">
		<fieldset>

			<!-- Form Name -->
			<legend>Update Meter</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="meter">First Name</label>
				<div class="col-md-4">
					<form:input path="firstName" id="firstName" type="text"
						class="form-control input-md" />
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="description">Last
					Name</label>
				<div class="col-md-5">
					<form:input path="lastName" id="lastName" type="text"
						class="form-control input-md" size="50" />
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nationality">Nationality</label>
				<div class="col-md-5">
					<form:input path="nationality" id="nationality" type="text"
						class="form-control input-md" />
				</div>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="dobMonth">DoB
					Month</label>
				<div class="col-md-5">
					<form:select path="dobMonth" items="${monthList}" multiple="false" class="form-control input-md"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="dobYear">DoB
					Year</label>
				<div class="col-md-5">
					<form:input path="dobYear" id="dobYear" type="text"
						class="form-control input-md" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="dodMonth">DoD
					Month</label>
				<div class="col-md-5">
					<form:select path="dodMonth" items="${monthList}" multiple="false" class="form-control input-md" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="dodYear">DoD
					Year</label>
				<div class="col-md-5">
					<form:input path="dodYear" id="dodYear" type="text"
						class="form-control input-md" />
				</div>
			</div>			
			<form:hidden path="id" />
			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="save"></label>
				<div class="col-md-8">
					<form:button id="save" name="save" value="save"
						class="btn btn-success">Save</form:button>
					<form:button id="cancel" name="cancel" value="cancel"
						class="btn btn-success">Cancel</form:button>
				</div>
			</div>

		</fieldset>
	</form:form>
</div>
<!-- id=page -->
</div>