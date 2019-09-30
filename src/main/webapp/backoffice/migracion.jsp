<%@include file="../includes/header.jsp"%>
<%@include file="../includes/navbar.jsp"%>
<%@include file="../includes/mensaje.jsp"%>


<h1>Resultado de la migración</h1>

	<p>Líneas Leidas: <b>${ lineasLeidas }</b></p>
	<p>Líneas Insertadas: <b>${ lineasInsertadas }</b></p>
	<p>Líneas Erroneas: <b>${ lineasErroneas }</b></p>
	<p>Tiempo: <b>${ tiempo }</b> ms</p>
	<p>Tiempo: <b>${ tiempo/1000 }</b> s</p>
	<p>Tiempo: <b>${ tiempo/60000 }</b> min</p>
    

<%@include file="../includes/footer.jsp"%>