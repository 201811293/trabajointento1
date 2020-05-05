import java.net.*;
import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
 
class servidor{
 public static void main (String[] args){
 
ServerSocket server;
 Socket connection;
 
DataOutputStream output;
 BufferedInputStream bis;
 BufferedOutputStream bos;
 
byte[] receivedData;
 int in;
 String file;
 
try{
 //Servidor Socket en el puerto 5000
 server = new ServerSocket( 5000 );
 while ( true ) {
 //Aceptar conexiones
 connection = server.accept();
 //Buffer de 1024 bytes
 receivedData = new byte[1024];
 bis = new BufferedInputStream(connection.getInputStream());
     //Recibimos el nombre del fichero
     try (DataInputStream dis = new DataInputStream(connection.getInputStream())) {
         //Recibimos el nombre del fichero
         file = dis.readUTF();
         file = file.substring(file.indexOf('\\')+1,file.length());
         //Para guardar fichero recibido
         bos = new BufferedOutputStream(new FileOutputStream(file));
         while ((in = bis.read(receivedData)) != -1){
             bos.write(receivedData,0,in);
         }
         bos.close();
     }
     System.out.print(file);
 Path from= Paths.get("C:/Users/Usuario/Documents/NetBeansProjects/tareacliente/"+file);
 Path to= Paths.get("C:/Users/Usuario/Documents/prueba123/server/"+file);
 CopyOption[] options = new CopyOption[]{
   StandardCopyOption.REPLACE_EXISTING,
   StandardCopyOption.COPY_ATTRIBUTES
 };
 Files.copy(from, to, options);
 File fichero = new File("C:/Users/Usuario/Documents/NetBeansProjects/tareacliente/"+file);
 fichero.delete();
 }

 }catch (IOException e ) {
 System.err.println(e);
 }
 }
}