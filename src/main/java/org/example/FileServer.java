package org.example;
import java.io.*;
import java.net.*;


public class FileServer{
    public static void main(String[] args) {
    int port = 5000;
    String filePath = "test.txt";

    try(ServerSocket serverSocket = new ServerSocket(port)) {
        System.out.println("Server venter på forbindelse... ");

        Socket socket = serverSocket.accept();
        System.out.println("Klient forbundet!");

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        OutputStream out = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while((bytesRead = bis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        System.out.println("Fil sendt: " + filePath);

        bis.close();
        socket.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

}