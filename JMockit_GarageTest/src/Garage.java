public interface Garage {
  public Car repairEngine(Car car);
}

abstract class AbstractGarage implements Garage {
  public abstract Car repairEngine(Car car);
}

class CheapGarage extends AbstractGarage {
  public Car repairEngine(Car car) {
    replaceEngineWithNewInstance(car);
    return car;
  }

  private void replaceEngineWithNewInstance(Car car) {
    try {
      Engine newEngine = EngineFactory.instantiate(car.engine);
      car.replaceEngine(newEngine);
    } catch ( InstantiationException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( IllegalAccessException e ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

class EngineFactory {
  public EngineFactory() {}

  public static Engine instantiate(Engine engine) throws InstantiationException, IllegalAccessException {
    Class<? extends Engine> engineClass = engine.getClass();
    Engine newEngine = engineClass.newInstance();
    return newEngine;
  }
}