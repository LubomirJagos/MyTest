/************* "DepartmentTest.java" *********/
import static org.junit.Assert.*;
import org.junit.Test;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.mocki

public class DepartmentTest {
 @Test
 public void testGetNameCalls(){
  Department dept  = new Department();
   
  new MockUp<Department>(){
   @Mock //A directive to JMockit to redefine the method. Remember!! 
   public String getPersonName(){
    return null;
   }
       
  };
  String name = dept.getPersonName();//this call returns a null because of redefinition
  assertNull(name);
 }
}
//</department>
