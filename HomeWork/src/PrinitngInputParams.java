/**
 * 
 * @author jagoslub
 * 
 * Skusobna trieda na pokusy, ukazat ako sa tlacia vstupne parametre.
 *
 */

public class PrinitngInputParams {
	public static void main(String args[]){
		System.out.println("Vstupne parametre:");
		System.out.println("------------------");
		for (String param: args) {
			System.out.println("---> " + param);
		}
	}
}
