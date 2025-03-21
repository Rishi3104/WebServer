import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException{
        int port = 8010;
        try (ServerSocket socket = new ServerSocket(port)) {
            socket.setSoTimeout(10000);
            while(true){
                try {
                    System.out.println(" Server is running on port " + port);
                    Socket acceptedConnection = socket.accept();
                    System.out.println("Accepted connection from " + acceptedConnection.getInetAddress());
                    PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                    BufferedReader fromClient =  new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                    toClient.println("Hello, you are connected to the server");
                    toClient.close();
                    fromClient.close();
                    acceptedConnection.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    public static void main(String[] args){
        Server server = new Server();
        try{
            server.run();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}