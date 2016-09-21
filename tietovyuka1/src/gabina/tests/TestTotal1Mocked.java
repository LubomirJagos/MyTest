package gabina.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import gabina.libraries.Calculator;
import gabina.libraries.Totals;
import gabina.libraries.Totals1;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;

public class TestTotal1Mocked {

	@Tested
	private Totals1 totals;

	@Mocked
	Calculator mockedICalculator;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddTotals() {
		new Expectations() {
			{
				mockedICalculator.sum(anyInt, anyInt);
				result = 1000;
				times = 2;

			}
		};

		totals.addTotal(3, 5);
		totals.addTotal(2, 4);
		for (int x : totals.mylist) {
			System.out.println(x);
			assertTrue(x == 1000);
		}
	}

}
