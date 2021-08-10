
import java.io.*;
import java.sql.*;
import java.util.LinkedList;

public class PruebaDB {

public static void main(String[] args) {
  InputStreamReader isr = new InputStreamReader (System.in);
  BufferedReader br = new BufferedReader (isr);
  LinkedList<Cls_set_get> lista;
  Cls_set_get empleado;
  String cedula, nombre, telefono;
  float salario;
  int valor = 0;
  boolean error;
  char confirma;
  Conexion obj1 = new Conexion("C:\\Users\\felic\\Desktop\\Milagros\\EmpleadosNueva.accdb",
                               "","");
  Operaciones obj2= new Operaciones();
  Connection cnn;
  while (valor != 6 )
  { System.out.println ("\n\n\t\t BIENVENIDO AL SISTEMA DE BASES DE DATOS ");
	System.out.println ("\t Facultad de Ingenieria de Sistemas Computacionales  \n\n");
    System.out.println ("\t  >>>>>  OPERACIONES  >>>>>\n\n");
    System.out.println ("\t  1. Consulta de todos los registros");
    System.out.println ("\t  2. Insertar un Registro");
    System.out.println ("\t  3. Eliminar un Registro");    
    System.out.println ("\t  4. Consulta de un registro");
    System.out.println ("\t  5. Modificar teléfono del empleado");
    System.out.println ("\t  6. Salir \n\n");
    
    do{
    	error= false;   
        try{
          System.out.println ("\n\t Introduzca la Opción Deseada.....");	
          valor = Integer.parseInt(br.readLine());
           }
        catch (IOException e){
        System.out.println("Error...a ocurrido un problema en la entrada de datos");
        error=true;
        }
        catch (NumberFormatException e){
        System.out.println("Error...a ocurrido un problema conversión de datos");
        error=true;
        }
        
    }while (error);
    switch(valor){
	case 1: //1. Consulta de todos los registros
	      try{		    		 
		     lista = obj2.consulta_todos_registros(obj1);
		     System.out.print ("\nNombre"+"\t\t"+"Cedula"+"\t\t"+"Telefono"+"\t\t"+"Salario");
		     for (int i=0; i<lista.size();i++)
		     System.out.print ("\n"+lista.get(i).getnombre()+"\t\t"+lista.get(i).getcedula()+
		     	               "\t\t\t"+lista.get(i).gettelefono()+"\t\t\t\t"+lista.get(i).getsalario());
		     }
		 catch(Exception e){
		   	System.out.print (e);  }
		 break;
	 case 2: //2. Insertar un Registro
		  try {
             System.out.println ("\t Introduzca los datos \n");
             System.out.println ("\n\t Cedula....."); cedula = br.readLine();
             if (!(obj2.verificaE(cedula, obj1))){
                 System.out.println ("\t Nombre.....");     nombre = br.readLine();		    
	             System.out.println ("\n\t telefono....."); telefono = br.readLine();
		         System.out.println ("\n\t Salario.....");  salario = Float.parseFloat (br.readLine());
		         obj2.asignar (nombre,cedula,telefono,salario);
		         obj2.adicionar(obj1);}
	 		 else
		         System.out.print("\nEl registro que intenta adicionar existe");
	         }
	       catch(IOException e){
	       	   System.out.println("\nError...en la entrada de datos");	}
	       catch(NumberFormatException e){
	       	   System.out.println("\nError...en conversión de datos");	}	   
	       catch(Exception e){
		   	   System.out.print (e);  }
	        break;
     /* case 3: 
    	  try{		   
		    System.out.println (" Cedula que desea eliminar"); cedula = br.readLine();
	        empleado=obj2.consulta_de_un_registro(cedula, obj1); 
	        if (cedula.equals(empleado.getcedula())){	        
	        	System.out.print("\nEstá seguro que el registro a continuación es el que desea eliminar s o n");
	            System.out.print ("\nNombre"+"\t"+"Cedula"+"\t"+"Telefono"+"\t"+"Salario");
	            System.out.println ("\n"+empleado.getnombre()+"\t"+empleado.getcedula()+"\t"+empleado.gettelefono()+"\t\t"+empleado.getsalario());
	            confirma= (char) br.read();
	            br.skip(1);
	            if (confirma == 's' | confirma == 'S')
	            	obj2.eliminar(cedula,obj1);}
	         else 
	         	 System.out.println("\nLa cédula del registro que intenta eliminar no existe");
	         	 
	        }
	        catch (IOException e){
	        	 System.out.println("Error...de entrada de datos en el programa principal"); }	   
	 		catch (Exception e)	{
	 			 System.out.println (e); }
	        break;  */
  	case 3: try {
           	 
           	 System.out.println (" Cedula que desea eliminar"); cedula = br.readLine();
	         if (!(obj2.verificaE(cedula, obj1))) 
	  		    System.out.println ("\t\t No se encontro el registro");
	 		 else
	 		    {  confirma = '*';
	 		       while (confirma != 'S' & confirma != 'N')
	 		       {System.out.println ("\t\t Esta seguro que desea eliminar este registro  S/N");
		            confirma = (char)br.read();
		            br.skip(1);}
		            if (confirma == 's' || confirma =='S')
	 	   		        obj2.eliminar(cedula, obj1); 
	 	   	    }
	 		 }
	 	   catch (IOException e)
	 	   {System.out.println ("Error en el Sistema.....!("+e+")");}
           catch (Exception e)
	 	   { System.out.println (e); }
 	       break;
		      
	case 4:
		  try{		   
		    System.out.println (" Cedula que desea consultar"); cedula = br.readLine();
	        empleado=obj2.consulta_de_un_registro(cedula, obj1); 
	        System.out.print ("\nNombre"+"\t"+"Cedula"+"\t"+"Telefono"+"\t"+"Salario");
	        System.out.print ("\n"+empleado.getnombre()+"\t"+empleado.getcedula()+"\t"+empleado.gettelefono()+"\t\t"+empleado.getsalario());
	        }
	        catch (IOException e){
	        	 System.out.println("Error...de entrada de datos en el programa principal");
	        }	   
	 		catch (Exception e){
	 			 System.out.println (e); }
	        break; 
	 case 5:
	 	    try {           	 
           	 System.out.println ("Ingrese la cédula del empleado que desea cambiar su número de teléfono");
           	 cedula = br.readLine(); 
	         if (!(obj2.verificaE(cedula, obj1))) 
	  		    System.out.println ("\t\t No se encontro el registro");
	 		 else
	 		    {   	 		    	
	 		     System.out.println ("\t\t Ingrese el nuevo número de telefono");
		         telefono = br.readLine();            
	 	   		 obj2.modificar_num_telefono(cedula, telefono,obj1); 
	 	   		 	
	 	   	    }
	 		 }
	 	catch (IOException e){
	 	    System.out.println ("Error en el Sistema.....!("+e+")");}
         catch (Exception e){
            System.out.println (e); }
 	      break;
	 
		     }
	}
	}
}


 