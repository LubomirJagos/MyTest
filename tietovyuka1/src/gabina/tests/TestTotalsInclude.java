package gabina.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import gabina.libraries.ICalculator;
import gabina.libraries.Totals;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class TestTotalsInclude {
	
	@Tested
	private Totals totals;
	/*using injection of ICalculator instance - dependency injection used in tested class Totals*/
	@Injectable
	ICalculator mockedICalculator;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddTotals() {
		new Expectations() {
			{
				mockedICalculator.sum(anyInt, anyInt);
				result = 1000;
				times=2;
				
			}
		};
					
		totals.addTotal(3, 5);
		totals.addTotal(2, 4);
		for(int x: totals.mylist) {
			System.out.println(x);
		assertTrue(x == 1000);
		}
	}

}
