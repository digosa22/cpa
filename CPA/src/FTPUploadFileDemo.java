import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
 
/**
 * A program that demonstrates how to upload files from local computer
 * to a remote FTP server using Apache Commons Net API.
 * @author www.codejava.net
 */
public class FTPUploadFileDemo {
 
    public static void main(String[] args) {
    	
    	System.setProperty("java.net.preferIPv4Stack", "true");
    	
        String server = "ftp.salongaudi.esy.es";
        int port = 21;
        String user = "u982068658";
        String pass = "159*963";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("pdfs/validacion.pdf");
 
            String firstRemoteFile = "cpa/validacion.pdf";
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
           
            inputStream.close();
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
 
}