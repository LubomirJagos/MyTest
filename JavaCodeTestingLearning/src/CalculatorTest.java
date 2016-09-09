import static org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assume.*;
import org.hamcrest.core.*;
import static org.hamcrest.CoreMatchers.*;

import java.nio.file.FileSystemException;
import java.lang.*;

public class CalculatorTest {
  private Calculator calculator;
  
  public CalculatorTest(){
	  this.calculator = new Calculator();
  }
  
  @Test
  public void evaluatesExpression() throws Exception{
    int sum = this.calculator.evaluate("1+2+3");
    assertEquals(6, sum);
  }
  
  @Test
  public void testUsedSystem() throws Exception{
	  assumeThat(calculator.getUsedSystem(), is("decadic"));
	  assertThat(calculator.evaluate("1+1"), is(2));
  }
}