<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.prova.pizzastore.model.Ordine"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica ordine</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="/utente/navbarPizzaiolo.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
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
				        <h5>Modifica ordine</h5> 
				    </div>
				    <div class='card-body'>
		
		
							<form method="post" action="ExecuteUpdateOrdineServlet" class="row g-3" novalidate="novalidate">
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice </label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" value="${update_ordine_attr.codice }">
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${update_ordine_attr.data}' />
								<div class="col-md-6">
									<label for="data" class="form-label">Data </label>
	                        		<input class="form-control" id="data" type="date" placeholder="dd/MM/yy" 
	                        				title="formato : gg/mm/aaaa"  name="data" value="${parsedDate}" >
								</div>
							
								<div class="col-md-6"> 
									<label for="cliente.id">Cliente </label>
								    <select class="form-select" id="cliente.id" name="cliente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${cliente_list_attribute }" var="clienteItem">
								      		<option value="${clienteItem.id}" ${update_ordine_attr.cliente.id == clienteItem.id?'selected':''} >${clienteItem.nome } ${clienteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<div class="col-md-6"> 
									<label for="utente.id">Utente </label>
								    <select class="form-select" id="utente.id" name="utente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${utente_list_attribute }" var="utenteItem">
								      		<option value="${utenteItem.id}" ${update_ordine_attr.utente.id == utenteItem.id?'selected':''} >${utenteItem.nome } ${utenteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<div class="col-md-6">
									<label for="pizze.id">Pizze </label><br>
									<c:forEach items="${pizza_list_attribute}" var="pizzaItem">
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="checkbox" value="${pizzaItem.id}"
												 id="pizze.id" name="pizze.id">
										<label class="form-check-label" for="pizze.id"> ${pizzaItem.descrizione}</label>
										</div>
									</c:forEach>
								</div>
								
								<div class="col-md-6">
									<label for="closed" class="form-label">Closed</label>
								    <select class="form-select" id="closed" name="closed">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								    	<option value="true">true</option>
								    	<option value="false">false</option>
								    </select>
								</div>
								
								<input type="hidden" name="idOrdine" value="${update_ordine_attr.id}">
								
								
							<div class="col-12">
								<a href="ExecuteListOrdineServlet" class='btn btn-outline-secondary' style='width:80px'>
					           		<i class='fa fa-chevron-left'></i> Back
					        	</a>
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
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