package gabina.libraries;

import java.util.ArrayList;
import java.util.List;

public class Totals {
	public List<Integer> mylist;
	public ICalculator calc;

	/*
	 * implementation of interface ICalculation done during run-time (class
	 * Run.java)
	 */
	public Totals() {
		mylist = new ArrayList<>();
		// calc=new Calculator();
	}

	public void addTotal(int a, int b) {
		Integer c = calc.sum(a, b);
		mylist.add(c);
	}
}
