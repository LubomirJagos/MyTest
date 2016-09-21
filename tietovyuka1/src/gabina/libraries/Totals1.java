package gabina.libraries;

import java.util.ArrayList;
import java.util.List;

public class Totals1 {
 public List<Integer> mylist;
 public ICalculator calc;
/* implementation of interface ICalculation done in Totals1 constructor*/	
	public Totals1(){
		mylist=new ArrayList<>();
		calc=new Calculator();
	}

	public void addTotal(int a, int b) {
		Integer c = calc.sum(a, b);
		mylist.add(c);
	}
}
