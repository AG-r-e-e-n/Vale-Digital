import java.sql.*;
class Conexion {	
	private String nombre_bd;
	private String usuario_bd;
    private String password_bd;
    
    private Connection cnn; //objeto que permite la conexión a la BD
    
    public Conexion(String nombre_bd, String usuario_bd, String password_bd){
       this.nombre_bd=nombre_bd;
       this.usuario_bd= usuario_bd;
       this.password_bd=password_bd; }
       
	// Método de conexion
    public Connection establecer_conexion() throws Exception {
    	try {
    		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		cnn=DriverManager.getConnection("jdbc:ucanaccess://"+nombre_bd,usuario_bd,password_bd);    	
    		return cnn;
    	}
    	catch (ClassNotFoundException e){ 
    		throw new Exception ("\nPara el programador: "+e+
    			                 "\n\nPara el usuario: Error...No se pudo cargar el driver puente Jdbc_Odbc");
        }
    	catch (SQLException e){ 
    		throw new Exception ("\nPara el programador: "+e+
    			                 "\n\nPara el usuario: Error... No se pudo establecer la conexion");
        }	
    }    
 } 