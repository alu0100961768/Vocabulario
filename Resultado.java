import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resultado {

	static float aciertos;
	static float fallos;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		FileReader calificacion = new FileReader("calificacion.txt");
		BufferedReader brcalificacion = new BufferedReader(calificacion);
		FileReader resultados = new FileReader("resultadosreales.txt");
		BufferedReader brresultados = new BufferedReader(resultados);
		
		
		String calificaciontxt;
		String resultadotxt;
		String word = null;
		String[] arrayWords;
		ArrayList aListWords = new ArrayList();

		Pattern p = Pattern.compile("[a-zA-Z]");
		
		int j = 0;
		while(((calificaciontxt = brcalificacion.readLine()) != null) && ((resultadotxt = brresultados.readLine()) != null)) {
			Scanner scan1 = new Scanner(calificaciontxt);
			Scanner scan2 = new Scanner(resultadotxt);
			
			while(scan1.hasNext() && scan2.hasNext()) {
			calificaciontxt = scan1.next().toLowerCase();
			resultadotxt = scan2.next().toLowerCase();
				
			if(calificaciontxt.equals(resultadotxt)) {
				aciertos++;
			}	
			else {
				fallos++;
			}
			}
		}
		
		
		System.out.print((aciertos/(aciertos+fallos))*100 +"\n");
		
	}

}
