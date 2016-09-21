package httpServerClient.app;

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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JacksonObjectMapperToList {
	 List<Message> myList;
	
	public void PrintList() {
		if (myList != null) {
			for (Message msg : myList) {
				System.out.println(msg);
			}
		}
	}

	public void jacksonToList(String jsonString) {
		// converting jsonString to List

		// byte[] jsonString =
		// Files.readAllBytes(Paths.get("/Users/kolargab/git/Mytest/HomeWork/src/messages.txt"));
		// for testing purposes

		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			this.myList = objectMapper.readValue(jsonString, new TypeReference<List<Message>>() {
			});
			
		} catch (JsonMappingException e) {
			System.err.println("Unable to map Json string to List " + e);
		} catch (OutOfMemoryError e) {
			System.err.println("System out of memory " + e);
		} catch (IOException e) {
			System.err.println("Unable to read input " + e);
		}
		
	}
	}
