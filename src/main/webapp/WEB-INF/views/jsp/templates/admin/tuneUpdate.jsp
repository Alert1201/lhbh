
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style type="text/css">
/* Adjust feedback icon position */
#author option {
	width: 50px;
}
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
													this.action = "${pageContext.request.contextPath}/tunes/cancel"
													$('#updateForm')
															.attr("action",
																	"${pageContext.request.contextPath}/tunes/cancel");
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
		method="post" commandName="tuneForm">
		<fieldset>

			<!-- Form Name -->
			<legend>Update Tune</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="meter">Tune Name</label>
				<div class="col-md-4">
					<form:input path="name" id="name" type="text"
						class="form-control input-md" />
				</div>
			</div>

			<!-- Select Meter-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="meter">Meter</label>
				<div class="col-md-6">
					<form:select path="meter.id" multiple="false"
						class="form-control input-md">
						<form:option value="0">&nbsp;</form:option>
						<form:options items="${meterList}" />
					</form:select>
				</div>
			</div>

			<!-- Select Author-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="author">Author</label>
				<div class="col-md-6">
					<form:select path="author.id" multiple="false"
						class="form-control input-md">
						<form:option value="0">&nbsp;</form:option>
						<form:options items="${authorList}" />
					</form:select>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="compositionYear">Year
					Composed</label>
				<div class="col-md-5">
					<form:input path="compositionYear" id="compositionYear" type="text"
						class="form-control input-md" />
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