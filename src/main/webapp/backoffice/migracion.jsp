<%@include file="../includes/header.jsp"%>
<%@include file="../includes/navbar.jsp"%>
<%@include file="../includes/mensaje.jsp"%>


<h1>Resultado de la migraci�n</h1>

	<p>L�neas Leidas: <b>${ lineasLeidas }</b></p>
	<p>L�neas Insertadas: <b>${ lineasInsertadas }</b></p>
	<p>L�neas Erroneas: <b>${ lineasErroneas }</b></p>
	<p>Tiempo: <b>${ tiempo }</b> ms</p>
	<p>Tiempo: <b>${ tiempo/1000 }</b> s</p>
	<p>Tiempo: <b>${ tiempo/60000 }</b> min</p>
    

<%@include file="../includes/footer.jsp"%>