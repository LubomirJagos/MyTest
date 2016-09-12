import static org.junit.Assert.*;

import mockit.Expectations;
import mockit.NonStrictExpectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import org.junit.Test;

public class GarageTest {
  @Test
  public void testRepair() {
    Garage g = new CheapGarage();
    Engine expectedEngine = new GasolineEngine();
    Car expectedCar = new Car(expectedEngine);
    Car actualCar = g.repairEngine(expectedCar);
    assertSame(expectedCar, actualCar);
    assertNotSame(expectedCar.engine, expectedEngine);
  }

  /*	
   *	Method EngineFactory.instantiate(...) is going to be mocked so it has to be annotate in parameters!
   */
  @Test
  public void testRepairWithExpectationEngineFactory(@Mocked final EngineFactory mock) throws InstantiationException, IllegalAccessException {

	final Engine expectedEngine = new DieselEngine();
    final Engine actualEngine = new GasolineEngine();
    final Garage g = new CheapGarage();
    final Car expectedCar = new Car(actualEngine);
    
	// here it is still the Engine built into the car
    assertSame(expectedCar.engine, actualEngine);

    new Expectations(){
    	{
    		mock.instantiate(actualEngine);						//used method instantiate() to record that this method is mocked
        	
      		result = new DieselEngine();
      		//result = new GasolineEngine();						//overriding return value of mocked method
        }
      };

      //BEFORE repair
      System.out.println("Engine type BEFORE repair");
      System.out.println("-------------------------");
      System.out.println("Engine type, gasoline: " + (expectedCar.engine instanceof GasolineEngine));
	  System.out.println("Engine type, diesel: " + (expectedCar.engine instanceof DieselEngine));

    Car actualCar = g.repairEngine(expectedCar);
    assertSame(expectedCar, actualCar);

    // normally this will be a GasolineEngine, because that was built into the car
    // but because of the mocked EngineFactory, we get a DieselEngine instead
    
    //AFTER repair
    System.out.println();
    System.out.println("Engine type AFTER repair");
    System.out.println("-------------------------");
    System.out.println("Engine type, gasoline: " + (expectedCar.engine instanceof GasolineEngine));
    System.out.println("Engine type, diesel: " + (expectedCar.engine instanceof DieselEngine));
    
    assertTrue(expectedCar.engine instanceof DieselEngine);
    assertNotSame(expectedCar.engine, expectedEngine);
  }
}