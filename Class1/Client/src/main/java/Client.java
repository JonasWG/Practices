package main.java;

import java.util.*;
import java.util.logging.Logger;
import java.io.*;
import java.net.*;

class Client {
	private Scanner reader;
	private PrintWriter writer;
	private Socket internalSocket;

	Logger logger = Logger.getLogger(this.getClass().getName());

	public Client(String hostname, int port) {
		try {
			internalSocket = new Socket(hostname, port);
			reader = new Scanner(internalSocket.getInputStream());
			writer = new PrintWriter(internalSocket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMsg(String message) {
		logger.info("Sending \"" + message + "\" to server");
		writer.println(message);
		writer.flush();
	}

	public String readMsg() {
		//Intentionally blocking request
		while (!reader.hasNext()) {
		}
		String message = reader.nextLine();
		logger.info("Received \"" + message + "\" from server");
		return message;
	}

	public void echoFunction(String message) {
		// Send msg and get log response
		sendMsg(message);
		readMsg();
	}

	public void sendMsg(int message) {
		logger.info("Sending \"" + message + "\" to server");
		writer.println(message);
		writer.flush();
	}

}
