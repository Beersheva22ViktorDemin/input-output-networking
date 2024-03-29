package telran.net.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import telran.util.Level;
import telran.util.LoggerRecord;

public class ServerLogAppl {
	private static final int PORT = 4001;

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
			System.out.println(response);
			writer.println(response);
		}
		System.out.println("client closed connection");

	}

	static String getResponse(String request) {
		String res = "Wrong Request";
		String tokens[] = request.split("#");
		if (tokens.length == 2) {
			res = switch (tokens[0]) {
			case "log" -> saveLog(tokens[1]);
			case "counter" -> getCounter(tokens[1].toUpperCase());
			default -> "Wrong type " + tokens[0];
			};
		}
		return res;
	}
	
	static String getCounter(String string) {
		String result = "";
		try {
			Level level = Level.valueOf(string);
			result += counter.getOrDefault(level, 0);
		} catch (IllegalArgumentException exception) {
			result = "Wrong level " + string;
		}
		
		return result;
	}

	static String saveLog(String string) {
		String result = "OK";
		try {
			LoggerRecord record = parseString(string);
			Integer count = counter.getOrDefault(record.level, 0);
			count++;
			counter.put(record.level, count);
		} catch (Exception e) {
			result = "Wrong log format";
		}

		return result;
	}

	private static LoggerRecord parseString(String string) throws ClassNotFoundException, IOException {
		return LoggerRecord.unserializeFromString(string);
	}

	static HashMap<Level, Integer> counter = new HashMap<>();
}
