<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- http://blog.paumard.org/cours/servlet/chap05-jsp-i18n.html -->
<title>Page JSP - Affichage Traduction</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<!-- http://www.favicon.pro/fr/ -->
<link rel="shortcut icon" type="image/x-icon" href="../favicon.ico">
</head>
<script type="text/javascript">
	function changeLangue(id) {
		if (id == 1) {
			document.getElementById("LI").submit();
		} else if (id == 2) {
			document.getElementById("LC").submit();
		}
	}
</script>
<body>
	<%
		String langue1 = request.getParameter("langueInit");
		if (langue1 == null) {
			langue1 = "message_fr";
		}
		String langue2 = request.getParameter("langueCible");
		if (langue2 == null) {
			langue2 = "message_en";
		}
		String val = request.getParameter("testParam");
		if (val == "") {
			val = "aucun texte saisi";
		}
	%>
	<div class="container-fluid">
		<div class="text-success">
			<h3>Affichage Traduction</h3>
		</div>
		<div>
			Bonjour <span class="text-primary"><%=val%></span>, pour connaître
			des mots dans des langues différentes, <br>utilisez cette
			application.
		</div>
		<br>
		<div>
			<table>
				<tr>
					<td>
						<FORM method="POST" id="LI" action="affTranslate.jsp">
							<SELECT name="langueInit" size="1" onchange="changeLangue(1);">
								<OPTION value="message_fr"
									<%if (langue1.equals("message_fr"))
				out.print("selected");%>>Français</OPTION>
								<OPTION value="message_en"
									<%if (langue1.equals("message_en"))
				out.print("selected");%>>Anglais</OPTION>
								<OPTION value="message_es"
									<%if (langue1.equals("message_es"))
				out.print("selected");%>>Espagnol</OPTION>
							</SELECT> <input type="hidden" name="testParam" value="<%=val%>">
							<input type="hidden" name="langueCible" value="<%=langue2%>">
						</FORM>
					</td>
					<td width="40px"></td>
					<td>
						<FORM method="POST" id="LC" action="affTranslate.jsp">
							<SELECT name="langueCible" size="1" onchange="changeLangue(2);">
								<OPTION value="message_fr"
									<%if (langue2.equals("message_fr"))
				out.print("selected");%>>Français</OPTION>
								<OPTION value="message_en"
									<%if (langue2.equals("message_en"))
				out.print("selected");%>>Anglais</OPTION>
								<OPTION value="message_es"
									<%if (langue2.equals("message_es"))
				out.print("selected");%>>Espagnol</OPTION>
							</SELECT> <input type="hidden" name="testParam" value="<%=val%>">
							<input type="hidden" name="langueInit" value="<%=langue1%>">
						</FORM>
					</td>
				<tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="p-3 mb-2 bg-info text-white">Langue initiale</td>
					<td>&nbsp;</td>
					<td class="p-3 mb-2 bg-info text-white">Langue cible</td>
				</tr>
				<tr>
					<td><fmt:bundle basename="<%=langue1%>">
							<fmt:message key="msg_1"></fmt:message>
						</fmt:bundle></td>
					<td>&nbsp;</td>
					<td><fmt:bundle basename="<%=langue2%>">
							<fmt:message key="msg_1"></fmt:message>
						</fmt:bundle></td>
				</tr>
				<tr>
					<td><fmt:bundle basename="<%=langue1%>">
							<fmt:message key="msg_2"></fmt:message>
						</fmt:bundle></td>
					<td>&nbsp;</td>
					<td><fmt:bundle basename="<%=langue2%>">
							<fmt:message key="msg_2"></fmt:message>
						</fmt:bundle></td>
				</tr>
				<tr>
					<td><fmt:bundle basename="<%=langue1%>">
							<fmt:message key="msg_3"></fmt:message>
						</fmt:bundle></td>
					<td>&nbsp;</td>
					<td><fmt:bundle basename="<%=langue2%>">
							<fmt:message key="msg_3"></fmt:message>
						</fmt:bundle></td>
				</tr>
				<tr>
					<td><fmt:bundle basename="<%=langue1%>">
							<fmt:message key="msg_4"></fmt:message>
						</fmt:bundle></td>
					<td>&nbsp;</td>
					<td><fmt:bundle basename="<%=langue2%>">
							<fmt:message key="msg_4"></fmt:message>
						</fmt:bundle></td>
				</tr>

			</table>
		</div>
		<br>
		<div>
			<a class="btn btn-primary" data-toggle="accueil" href="/Demo1"
				role="button" aria-expanded="false" aria-controls="accueil"
				title="Retour à l'accueil ?"> Retour Accueil </a>
		</div>
	</div>
	<!-- https://www.javatpoint.com/jsp-include-action -->
	<%@include file="piedDePage.jspf"%>
</body>
</html>