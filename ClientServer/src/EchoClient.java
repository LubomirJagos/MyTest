import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class EchoClient {

	public static void main(String args[]) {
		// String hostName = args[0];
		// int portNumber = Integer.parseInt(args[1]);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\"name\":\"Gabi\",\"myMessage\":\"Gabimessage\"}";
		System.out.println(jsonString);
		// map json to message

		try {
			Message msg = mapper.readValue(jsonString, Message.class);

			System.out.println(msg);

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(msg);

			System.out.println(jsonString);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// try (
		// Socket echoSocket = new Socket(hostName, portNumber);
		// PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),
		// true);
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(echoSocket.getInputStream()));
		// BufferedReader stdIn = new BufferedReader(new
		// InputStreamReader(System.in));
		// )
		// {String userInput;
		// System.out.println("echo: " + in.readLine());
		// while ((userInput = stdIn.readLine()) != null) {
		// out.println(userInput);
		// System.out.println("echo: " + in.readLine());}
		// }
		// catch (IOException e) {System.out.println("This is IO Exception");
		//
		// }
		//
	}
}
