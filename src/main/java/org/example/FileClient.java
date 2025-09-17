package org.example;
import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        String serverAdress = "localhost";
        int port = 5000;
        String savePath = "modtaget.txt";

        try(Socket socket = new Socket(serverAdress, port)) {
            System.out.println("Forbundet til serveren...");

            InputStream in = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(savePath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = in.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            System.out.println("Fil modtaget og gemt: " + savePath);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
