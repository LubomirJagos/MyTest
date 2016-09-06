import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;



public class JacksonObjectMapperWriter {

	public static void main(String[] args) throws IOException {
		byte[] jsonData = Files.readAllBytes(Paths.get("employee.txt"));
	
		ObjectMapper objectMapper = new ObjectMapper();
	
		//create JsonNode
		JsonNode rootNode = objectMapper.readTree(jsonData);
	
		//update JSON data
		((ObjectNode) rootNode).put("id", 500);
		//add new key value
		((ObjectNode) rootNode).put("test", "test value");
		//remove existing key
		((ObjectNode) rootNode).remove("role");
		((ObjectNode) rootNode).remove("properties");
		objectMapper.writeValue(new File("updated_emp.txt"), rootNode);
		
		System.out.println("JacksonObjectMapperWriter finished.");
	}
		
}











