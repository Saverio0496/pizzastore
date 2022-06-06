<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Rimuovi Cliente</title>

</head>
<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div class='card'>
				<div class='card-header'>
					<h5>Visualizza dettaglio</h5>
				</div>

				<dl class="row">
					<dt class="col-sm-3 text-right">Nome:</dt>
					<dd class="col-sm-9">${delete_cliente_attr.nome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Cognome:</dt>
					<dd class="col-sm-9">${delete_cliente_attr.cognome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Indirizzo:</dt>
					<dd class="col-sm-9">${delete_cliente_attr.indirizzo}</dd>
				</dl>

			</div>
			<!-- end card body -->

			<div class='card-footer'>
				<form action="ExecuteDeleteClienteServlet" method="post">
					<input type="hidden" name="idCliente"
						value="${delete_cliente_attr.id}">
					<button type="submit" name="submit" id="submit"
						class="btn btn-danger">Conferma</button>
					<a href="ExecuteListClienteServlet"
						class='btn btn-outline-secondary' style='width: 80px'> <i
						class='fa fa-chevron-left'></i> Back
					</a>
				</form>
			</div>
			<!-- end card -->
		</div>


		<!-- end container -->
		</div>

	</main>

	<!-- Footer -->
	<jsp:include page="../footer.jsp" />
</body>
</html>