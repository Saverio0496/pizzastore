<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Pizza</title>
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
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modificare la pizza</h5> 
				    </div>
				    <div class='card-body'>		
		
							<form method="post" action="ExecuteUpdatePizzaServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione</label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" value="${update_pizza_attr.descrizione }">
								</div>
								
								<div class="col-md-6">
									<label for="ingredienti" class="form-label">Ingredienti</label>
									<input type="text" name="ingredienti" id="ingredienti" class="form-control" placeholder="Inserire gli ingredienti" value="${update_pizza_attr.ingredienti }">
								</div>
							
								<div class="col-md-6">
									<label for="prezzobase" class="form-label">Prezzo Base</label>
									<input type="text" name="prezzobase" id="prezzobase" class="form-control" placeholder="Inserire il prezzo base" value="${update_pizza_attr.prezzoBase }">
								</div>
								
								<div class="col-md-6">
									<label for="attivo" class="form-label">Attivo(true o false)</label>
									<input type="text" name="attivo" id="attivo" class="form-control" placeholder="Attivo/Disattivo" value="${update_pizza_attr.attivo }">
								</div>
								
								<div class="col-12">
									<button type="submit" name="insertSubmit" value="insertSubmit" id="insertSubmit" class="btn btn-primary">Conferma</button>
								</div>
								
								<input type="hidden" name="idPizza" value="${update_pizza_attr.id}">
		
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