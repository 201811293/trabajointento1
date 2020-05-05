import java.net.*;
import java.io.*;
 
class cliente{
public static void main (String[] args){
 DataInputStream input;
 BufferedInputStream bis;
 BufferedOutputStream bos;
 int in;
 byte[] byteArray;
 //Fichero a transferir
 final String filename = ("C:\\Users\\Usuario\\Documents\\prueba123\\choca.txt");
 
try{
 final File localFile = new File( filename );
 Socket client = new Socket("localhost", 5000);
 bis = new BufferedInputStream(new FileInputStream(localFile));
 bos = new BufferedOutputStream(client.getOutputStream());
 //Enviamos el nombre del fichero
 DataOutputStream dos=new DataOutputStream(client.getOutputStream());
 dos.writeUTF(localFile.getName());
 //Enviamos el fichero
 byteArray = new byte[8192];
 while ((in = bis.read(byteArray)) != -1){
 bos.write(byteArray,0,in);
 }
 
bis.close();
 bos.close();
 
}catch ( IOException e ) {
 System.err.println(e);
 }
 }
}