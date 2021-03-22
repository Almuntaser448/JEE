<%@  page import="java.sql.*" %>

<%

String titre=request.getParameter("titre");
int typeDoc=request.getParameter("typedocument");

try{
	Connection conn=DriveManager.getConnection("jdbc:oracle:thin:@localhost:1512:xe","mydp","mdp");
	PreparedStatement ps=conn.prepareStatement("DELETE  From Document where typedocument=? AND titre=?");	
	ps.setString(1,typedocument);
	ps.setString(2,titre);

	int x=ps.executeUpdate();
	if(x!=0){
		out.println("la document est bien supprime");
	}else {
	out.println("une erreur est passer")}
}
catch(Exception e)
{
	out.println(e);
}
%>

