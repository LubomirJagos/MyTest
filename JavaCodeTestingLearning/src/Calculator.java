import java.nio.file.FileSystemException;

public class Calculator {
	private String usedSystem = "decadic";
	
	public Calculator(){
		this.usedSystem = "octal";
	}
	
	public String getUsedSystem(){
		return this.usedSystem;
	}
	
	public int evaluate(String expression) throws FileSystemException{
		int sum = 0;
		for (String summand: expression.split("\\+"))
			sum += Integer.valueOf(summand);
		return sum;
	}
}