package DeepLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        out.flush();
        String resp = in.readLine();
        System.out.println(resp);
        //out.println("okay");
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
	/*
	
	public void sendImagePath(String path) {
		while(true)
			this.writer.println(path);
	}
	
	public void listenToInferenceResults() throws IOException {
		String line;
		while(true) {
			while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
		}
	}*/
	
	
}



