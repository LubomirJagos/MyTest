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



public class JacksonObjectMapperToMap {
	public static void main(String[] args) throws IOException {
		//converting json to Map
		byte[] mapData = Files.readAllBytes(Paths.get("data.txt"));
		Map<String,String> myMap = new HashMap<String, String>();
	
		ObjectMapper objectMapper = new ObjectMapper();
		myMap = objectMapper.readValue(mapData, HashMap.class);
		System.out.println("Map is: " + myMap);
	
		//another way
		myMap = objectMapper.readValue(mapData, new TypeReference<HashMap<String,String>>() {});
		System.out.println("Map using TypeReference: " + myMap);
		
		//ukazka ako vyberat zo zoznamu
		System.out.println("---> " + myMap.get("city"));
	}		
}
