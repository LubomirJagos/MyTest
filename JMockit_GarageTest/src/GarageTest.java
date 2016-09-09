import static org.junit.Assert.*;

import mockit.Expectations;
import mockit.E

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

  @Test
  public void testRepairWithExpectationEngineFactory() throws InstantiationException, IllegalAccessException {
    final Engine expectedEngine = new DieselEngine();
    final Engine actualEngine = new GasolineEngine();
    final Garage g = new CheapGarage();
    final Car expectedCar = new Car(actualEngine);
    // here it is still the Engine built into the car
    assertSame(expectedCar.engine, actualEngine);		//engine == gasoline

    new Expectations() {
      EngineFactory mock;
      {
        mock.instantiate(actualEngine); returns(new DieselEngine());		//change engine diesel instead gasoline
      }
    };

    Car actualCar = g.repairEngine(expectedCar);
    assertSame(expectedCar, actualCar);
    // normally this will be a GasolineEngine, because that was built into the car
    // but because of the mocked EngineFactory, we get a DieselEngine instead
    assertTrue(expectedCar.engine instanceof DieselEngine);    
    assertNotSame(expectedCar.engine, expectedEngine);
  }
}