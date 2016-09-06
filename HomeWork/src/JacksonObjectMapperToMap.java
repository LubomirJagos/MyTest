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
		byte[] mapData = Files.readAllBytes(Paths.get("/Users/kolargab/EclipseJavaTry_1/HomeWork/src/data.txt"));
		List<Employee> myList = new ArrayList<Employee>();
	
		ObjectMapper objectMapper = new ObjectMapper();
		myList = objectMapper.readValue(mapData, ArrayList.class);
		System.out.println("List is: " + myList);
	
		//another way
		myList = objectMapper.readValue(mapData, new TypeReference<ArrayList<Employee>>() {});
		System.out.println("Map using TypeReference: " + myMap);
		
		//ukazka ako vyberat zo zoznamu
		System.out.println("---> " + myMap.get("city"));
	}		
}
