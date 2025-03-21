import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer(){
        return (clientSocket) -> {
            try{ 
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                toClient.println("Hello, you are connected to the server");
                toClient.close();
                clientSocket.close();

            } catch(Exception ex){
                ex.printStackTrace();
            }
        };
    }
    public static void main(String[] args) {
        Server server = new Server();
        int port = 8010;
        try{
            ServerSocket serversocket =  new ServerSocket(port);
            serversocket.setSoTimeout(10000);
            System.out.println("Server is running on port " + port);
            while(true){
                Socket acceptedSocket = serversocket.accept();
                Thread thread = new Thread(()->server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch(Exception ex){
            ex.printStackTrace();
    }
}
}
