<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="page">
	<br />
	<table id="example" class="cell-border" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Abbreviation</th>
				<th>Name</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>Abbreviation</th>
				<th>Name</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${data}" var="element">
				<tr>
					<td>${element.abbreviation}</td>
					<td>${element.name}</td>

					<td align="center"><a
						href="${pageContext.request.contextPath}/hymnals/update?id=${element.id}"
						data-toggle="tooltip" title="Change row"><span
							class='glyphicon glyphicon-pencil'></span></a></td>

					<td align="center"><a
						href="${pageContext.request.contextPath}/hymnals/delete?id=${element.id}"
						data-toggle="tooltip" title="Delete row"><span
							class='glyphicon glyphicon-trash'></span></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/hymnals/add"
		data-toggle="tooltip" title="Change row"><button type="button"
			class="btn btn-primary btn-space btn-xs">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add
		</button></a>
</div>
</div>