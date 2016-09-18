<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="navigation">
	<h4>Admin Menu</h4>
	<ul>

		<c:forEach items="${menu}" var="menu">
			<li><p class="menuListActive">
					<c:choose>
						<c:when test="${menu.key.equals(active)}">
							<b><i>${menu.value}</i></b>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/${menu.key}/view" />${menu.value}</a>
						</c:otherwise>
					</c:choose>
				</p></li>
		</c:forEach>
</div>