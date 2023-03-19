package telran.net.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import telran.util.Level;

public class ServerLogAppl {
	private static final int PORT = 4000;

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("server listening on port " + PORT);
		while (true) {
			Socket socket = serverSocket.accept();
			try {
				runServerClient(socket);
			} catch (IOException e) {
				System.out.println("abnormal closing connection");
			}
		}

	}

	private static void runServerClient(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream writer = new PrintStream(socket.getOutputStream());
		while (true) {
			String request = reader.readLine();
			if (request == null) {
				break;
			}
			String response = getResponse(request);
			writer.println(response);
		}
		System.out.println("client closed connection");

	}

	private static String getResponse(String request) {
		String res = "Wrong Request";
		String tokens[] = request.split("#");
		if (tokens.length == 2) {
			res = switch (tokens[0]) {
			case "log" -> saveLog(tokens[1]);
			case "counter" -> getCounter(tokens[1]);
			default -> "Wrong type " + tokens[0];
			};
		}
		return res;
	}
	
	private static Object getCounter(String string) {
		String result = "";
		try {
			Level level = Level.valueOf(string);
			result += counter.getOrDefault(level, 0);
		} catch (IllegalArgumentException exception) {
			result = "Wrong level " + string;
		}
		
		return result;
	}

	private static Object saveLog(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	static HashMap<Level, Integer> counter = new HashMap<>();
}
