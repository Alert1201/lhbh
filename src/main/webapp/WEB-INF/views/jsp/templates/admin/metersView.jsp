
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="page">
	<br />



	<c:choose>
		<c:when test="${messageType=='error'}">
			<div id="errorMessage">
		</c:when>
		<c:when test="${messageType=='warning'}">
			<div id="warningMessage">
		</c:when>
		<c:when test="${messageType=='info'}">
			<div id="infoMessage">${message}
		</c:when>
	</c:choose>
	<c:if test="${not empty message}">
		</div>
    	<span id="messageButtonShow"><i id="hide"
			class="fa fa-toggle-on fa-sm" title="Hide message"></i> </span>
		<p style="margin-bottom: 1px">&nbsp;</p>
	</c:if>

	<table id="example" class="cell-border" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Meter</th>
				<th>Description</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>Meter</th>
				<th>Description</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${data}" var="element">
				<tr>
					<td>${element.meter}</td>
					<td>${element.description}</td>

					<td align="center"><a
						href="${pageContext.request.contextPath}/meters/update?id=${element.id}"
						data-toggle="tooltip" title="Change row"><span
							class='glyphicon glyphicon-pencil'></span></a></td>

					<td align="center"><a id="delRow"
						href="${pageContext.request.contextPath}/meters/delete?id=${element.id}"
						data-toggle="tooltip" title="Delete row"><span
							class='glyphicon glyphicon-trash'></span></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/meters/add"
		data-toggle="tooltip" title="Change row"><button type="button"
			class="btn btn-primary btn-space btn-xs">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add
		</button></a>


</div>
</div>