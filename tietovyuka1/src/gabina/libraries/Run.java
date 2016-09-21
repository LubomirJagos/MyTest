package gabina.libraries;

public class Run {
	public static void main(String[] args){
		Totals tot = new Totals();
		
		tot.calc=new Calculator();
		tot.addTotal(1,1);
		tot.addTotal(2,2);
		tot.addTotal(3,3);
		for(int x: tot.mylist) {
			System.out.println(x);}
		
		
	}

}
