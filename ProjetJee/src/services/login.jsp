<%@ page import="java.util.Date,java.sql.*" %>

<%String login=request.getParameter("login");
String mdp=request.getParameter("mdp");

try{
	Connection conn=DriveManager.getConnection("jdbc:oracle:thin:@localhost:1512:xe","mydp","mdp");
	PreparedStatement ps=conn.prepareStatement("SELECT *  From Bibliothecaires where nom=? AND mdp=?");	
	ps.setString(1,login);
	ps.setString(2,mdp);

	int x=ps.executeQuery();
	if(x!=0){
		<c:redirect url="/choix.html"/>
	}else {
	out.println("Votre login ou/et votre mdp est/sont incorect/s")}
}
catch(Exception e)
{
	out.println(e);
}
	

%>

