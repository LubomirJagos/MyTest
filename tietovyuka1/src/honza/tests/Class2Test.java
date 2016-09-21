package honza.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import honza.libraries.Calculator;
import honza.libraries.ForMock;
import honza.libraries.ICalculator;

import mockit.Injectable;
import mockit.Expectations;

public class Class2Test {

	//@Injectable
	//@Tested
	//private Calculator calc;
	
	final ICalculator calculator = new Calculator();
	
	@Injectable
	/*final */ForMock forMock;// = new Calculator();
	
	@Test
	public void Calculator_WhenSum4And5_ShouldReturn9(@Injectable Calculator mockedICalculator) {
		
		new Expectations() {
			{
				mockedICalculator.sum(anyInt, anyInt);
				result = 1000;
				times = 2;
			}
		};

		int a = 4;
		int b = 5;
		
		int c = calculator.sum(a, b);
		c = mockedICalculator.sum(b, a);
		c = mockedICalculator.sum(b, a);
		
		assertEquals(9, c);
	}

}
