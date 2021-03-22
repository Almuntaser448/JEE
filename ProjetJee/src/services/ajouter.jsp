<%@  page import="java.sql.*" %>

<%

String titre=request.getParameter("titre");
String auteur=request.getParameter("auteur");
int typeDoc=request.getParameter("tdoc");
String emprunt=request.getParameter("Emprunt");
try{
	Connection conn=DriveManager.getConnection("jdbc:oracle:thin:@localhost:1512:xe","mydp","mdp");
	PreparedStatement ps=conn.prepareStatement("insert into Document values(?,?,?,?)");	
	ps.setString(1,titre);
	ps.setString(2,auteur);
	ps.setString(3,typeDoc);
	ps.setString(4,emprunt);
	int x=ps.executeUpdate();
	if(x!=0){
		out.println("la document est bien ajoute");
	}else {
	out.println("une erreur est passer")}
}
catch(Exception e)
{
	out.println(e);
}
%>

