package telran.util;

import java.io.*;
import java.net.Socket;

public class TcpClientHandler implements Handler {

	Socket socket;
	PrintStream output;
	BufferedReader input;
	
	public TcpClientHandler(Socket socket, PrintStream output, BufferedReader input) {
		super();
		this.socket = socket;
		this.output = output;
		this.input = input;
	}

	@Override
	public void publish(LoggerRecord loggerRecord) {
		output.println(loggerRecord.toString());
		String request;
		try {
			request = input.readLine();
			if (request != "OK") {
				//raise error
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() {
		//TODO check order, is it important or not?
		output.close();
		try {
			input.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
