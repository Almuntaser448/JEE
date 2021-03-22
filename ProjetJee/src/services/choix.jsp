<%@ page import="java.util.Date,java.sql.*" %>

<%String choix=request.getParameter("choix");

if(choix=="Ajouter"){
			<c:redirect url="/ajoute.html"/>

}
else{
			<c:redirect url="/supprimer.html"/>

}

%>

