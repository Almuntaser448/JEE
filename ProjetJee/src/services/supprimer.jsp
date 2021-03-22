<%@  page import="java.sql.*" %>

<%

String titre=request.getParameter("titre");
int typeDoc=request.getParameter("id");

try{
	Connection conn=DriveManager.getConnection("jdbc:oracle:thin:@localhost:1512:xe","mydp","mdp");
	PreparedStatement ps=conn.prepareStatement("DELETE  From Document where id=? AND titre=?");	
	ps.setString(1,id);
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

