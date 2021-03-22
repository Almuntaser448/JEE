<%@ page import="java.util.Date,java.sql.*" %>

<%String login=request.getParameter("login");
String password=request.getParameter("password");

try{
	Connection conn=DriveManager.getConnection("jdbc:oracle:thin:@localhost:1512:xe","login","password");
	PreparedStatement ps=conn.prepareStatement("SELECT *  From UTILISATEUR where nom=? AND password=?");	
	ps.setString(1,login);
	ps.setString(2,password);

	int x=ps.executeQuery();
	if(x!=0){
		<c:redirect url="/choix.html"/>
	}else {
	out.println("Votre login ou/et votre password est/sont incorect/s")}
}
catch(Exception e)
{
	out.println(e);
}
	

%>

