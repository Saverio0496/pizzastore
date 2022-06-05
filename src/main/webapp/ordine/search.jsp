<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	
		<jsp:include page="../header.jsp"/>
		
		<title>Ricerca</title>
	
	</head>
	<body>
		<jsp:include page="/utente/navbarPizzaiolo.jsp"></jsp:include>
		
		<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Ricerca Ordine</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteSearchOrdineServlet" class="row g-3" novalidate="novalidate">
								
								<div class="col-md-6">
									<label for="cliente_id">Cliente</label>
								    <select class="form-select" id="cliente_id" name="cliente_id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${cliente_list_attribute }" var="clienteItem">
								      		<option value="${clienteItem.id}">${clienteItem.nome } ${clienteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<c:forEach items="${pizza_list_attribute}" var="pizzaItem">
									<div class="form-check form-check-inline">
											  <input class="form-check-input" type="checkbox" value="${pizzaItem.id}"
											  		 id="pizza_id" name="pizza_id">
											  <label class="form-check-label" for="pizza_id">${pizzaItem.descrizione}</label>
									</div>
								</c:forEach>
								
								<div class="col-md-6">
									<label for="data" class="form-label">Data</label>
	                        		<input class="form-control" id="data" type="date" placeholder="dd/MM/yy" 
	                        				title="formato : gg/mm/aaaa"  name="data" >
								</div>
								
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice</label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice">
								</div>
								
								<div class="col-md-6">
									<label for="utente_id">Utente</label>
								    <select class="form-select" id="utente_id" name="utente_id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${utente_list_attribute }" var="utenteItem">
								      		<option value="${utenteItem.id}">${utenteItem.nome } ${utenteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>