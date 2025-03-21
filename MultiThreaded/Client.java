import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable(){
        return new Runnable(){
            @Override
            public void run(){
                int port = 8010;
                try{
                    InetAddress address = InetAddress.getByName("localhost");
                    try(Socket socket = new Socket(address, port)){
                        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        toServer.println("Hello, I am a client");
                        String line = fromServer.readLine();
                        System.out.println("Server says: " + line);
                    } catch(IOException ex){
                        ex.printStackTrace();
                    }
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }

        };
    }
    public static void main(String[] args){
        Client client = new Client();
        for(int i = 0; i < 100; i++){
            try{
               Thread thread = new Thread(client.getRunnable());
                thread.start();
            }   catch(Exception ex){
                    ex.printStackTrace();
                }
        }
    }
}
