import java.sql.*;
import java.util.LinkedList;
class Operaciones{
    private String nombre, cedula,telefono, cadSQL;
    private float salario;  
	private Statement stmt; // objeto que permite la manipulaciòn de sentencias SQL
	private ResultSet recordset; // objeto que permite obtener resultados de la BD
	
	public void asignar (String n, String c, String t, float s)
   {	nombre = n;	  cedula = c; 	telefono = t;	salario = s; }

    // Consultar todos los registros
    public  LinkedList<Cls_set_get> consulta_todos_registros(Conexion obj1) throws Exception
    {  	Connection cnn=obj1.establecer_conexion();
        stmt = cnn.createStatement();
    	LinkedList<Cls_set_get> listaContactos = new LinkedList<Cls_set_get>();

    	try {
    		recordset = stmt.executeQuery("select * from tabgenerales order by cedu");
    		
    		while (recordset.next()) {

    		Cls_set_get contacto = new Cls_set_get();

    		contacto.setnombre(recordset.getString("nombre"));
    		contacto.setcedula(recordset.getString("cedu"));
    		contacto.settelefono(recordset.getString("telefono"));
    		contacto.setsalario(recordset.getFloat("salario"));
    		listaContactos.add(contacto);    		
    		}
    		cnn.close();
    		return listaContactos;
    		}
    	catch (SQLException e) {
    		cnn.close();
    		throw new Exception ("Error...en la consulta de todos los registros");
    		}
    	}
    //incluir un registro
   void adicionar (Conexion obj1) throws Exception
   {Connection cnn=null;
   	 try {
   	 	cnn=obj1.establecer_conexion();
   	 	stmt = cnn.createStatement();
   	 	cadSQL = "Insert Into tabgenerales (nombre,cedu,telefono,salario) Values ('"+nombre+"','"+cedula+"','"+telefono+"','"+salario +"')";
	    stmt.executeUpdate(cadSQL);	  
	    cnn.close();	   
     }
    catch (SQLException e) 
   { cnn.close();
     throw new Exception  ("ERROR  cuando inserta registros"+ e);
    }
  }	
    	
    //consulta de un registro	
    public  Cls_set_get consulta_de_un_registro(String ced, Conexion obj1) throws Exception
    {  Connection cnn=null;
       try {
    	 cnn=obj1.establecer_conexion();
         stmt = cnn.createStatement();	
         String cadSQL;
         Cls_set_get empleado= new Cls_set_get(); 
      
       	  cadSQL = "Select * from tabgenerales where cedu='"+ced+"'";	      
	      recordset = stmt.executeQuery(cadSQL);
	      boolean registros = recordset.next();
	      if (!(registros))
		      throw new Exception("Error...no hay registro seleccionado");
	      else
		      {empleado.setnombre(recordset.getString("nombre"));
		       empleado.setcedula(recordset.getString("cedu"));
		       empleado.settelefono(recordset.getString("telefono"));
		       empleado.setsalario(recordset.getFloat("salario"));
		       cnn.close();	
		       return empleado;
		      }				
	     } 
        catch (SQLException e) {
		      cnn.close();
		       throw new Exception("Error...consulta de un registro en la base de dato  " + e);
	    }
    }
    
  public boolean  verificaE (String ced, Conexion obj1) throws Exception
  { Connection cnn=null;
 	try {
 	   cnn=obj1.establecer_conexion();
       stmt = cnn.createStatement();      	   
  	   String cadSQL = "Select * from tabgenerales Where cedu = '"+ced+"'";
	   recordset= stmt.executeQuery (cadSQL);
	   boolean registros = recordset.next();
	   cnn.close();
	   return registros;
	   }  	
   catch (SQLException e)
   { cnn.close(); 
   	 throw new Exception("Error...en verificaE  " + e); } 
   
}
 public void eliminar (String ced, Conexion obj1 )throws Exception
 {Connection cnn=null;		  
  try {
   	 cnn=obj1.establecer_conexion();
     stmt = cnn.createStatement();      
     String cadSQL = "Delete from tabgenerales where cedu = '"+ced+"'";
	 stmt.executeUpdate(cadSQL);
	 cnn.close();
   	 }
    catch (SQLException e)  { 
      cnn.close();	
       throw new Exception("Error...al eliminar de un registro en la base de dato  " + e); 
    }
 }
public void modificar_num_telefono(String ced, String telefono, Conexion obj1) throws Exception  {
  Connection cnn=null;
  try {
   	 cnn=obj1.establecer_conexion();
     stmt = cnn.createStatement();     	
     String cadSQL = "Update tabgenerales set telefono = '"+telefono+"' where cedu = '"+ced+"'";
	 stmt.executeUpdate(cadSQL);
	 cnn.close();
   	 }
   catch (SQLException e)
    {  cnn.close();
       throw new Exception("Error...al moficar el número de teléfono de un registro en la base de dato  " + e);  }	
}   	
}





