import java.util.List;
import java.io.File;
import java.io.IOException;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * 
 * @author jagoslub
 * 
 * Ukazka ako citat spravy z pola a ulozit ich do zoznamu.
 */

public class JacksonObjectReadMultipleMessages {

	public static void main(String args[]) throws IOException{
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("messageList.txt"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		List<Message> msgList = objectMapper.readValue(jsonData,
				TypeFactory.defaultInstance().constructCollectionType(List.class,  
				   Message.class));
		
		System.out.println("Message List\n" + msgList);
	}
	
}
