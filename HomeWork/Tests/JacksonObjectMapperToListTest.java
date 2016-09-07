
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JacksonObjectMapperToListTest {
 public JacksonObjectMapperToList myTestMapper;

	@Before
	public void setUp() throws Exception {
	myTestMapper=new JacksonObjectMapperToList();

	}

	@Test
	public void JsonToListTest() {
		String expected;
		String actual;
				myTestMapper.jacksonToList(
				"[{\"name\": \"David\",\"myMessage\": \"Hello\"},{\"name\": \"Lukas\",\"myMessage\": \"Hello world\"}]");
		expected="David";	
		actual=myTestMapper.myList.get(0).getName();
		assertEquals(expected,actual);
		assertEquals("David",myTestMapper.myList.get(0).getName());
		assertEquals("Lukas",myTestMapper.myList.get(1).getName());
		assertEquals("Hello",myTestMapper.myList.get(0).getMyMessage());
		assertEquals("Hello world",myTestMapper.myList.get(1).getMyMessage());
	}

}
