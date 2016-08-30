<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="page">
	<br />

	<table id="example" class="cell-border" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>First Name</th>
				<th>LastName</th>
				<th>DOB Mon</th>
				<th>DOB Yr</th>
				<th>DOB Circa</th>
				<th>DOD Mon</th>
				<th>DOD Yr</th>
				<th>DOD Circa</th>
				<th>Nationality</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>First Name</th>
				<th>LastName</th>
				<th>DOB Mon</th>
				<th>DOB Yr</th>
				<th>DOB Circa</th>
				<th>DOD Mon</th>
				<th>DOD Yr</th>
				<th>DOD Circa</th>
				<th>Nationality</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${data}" var="element">
				<tr>
					<td>${element.firstName}</td>
					<td>${element.lastName}</td>
					<td>${element.dobMonth}</td>
					<td>${element.dobYear}</td>
					<td>${element.dobCirca}</td>
					<td>${element.dodMonth}</td>
					<td>${element.dodYear}</td>
					<td>${element.dodCirca}</td>
					<td>${element.nationality}</td>
					<td align="center"><a
						href="${pageContext.request.contextPath}/authors/update?id=${element.id}"
						data-toggle="tooltip" title="Change row"><span
							class='glyphicon glyphicon-pencil'></span></a></td>

					<td align="center"><a
						href="${pageContext.request.contextPath}/authors/delete?id=${element.id}"
						data-toggle="tooltip" title="Delete row"><span
							class='glyphicon glyphicon-trash'></span></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a
		href="${pageContext.request.contextPath}/authors/add"
		data-toggle="tooltip" title="Change row"><button type="button" class="btn btn-primary btn-space btn-xs">
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add
</button></a>


</div>
</div>