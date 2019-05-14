import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clasificacion {

	public float aciertos;
	public float fallos;
	
	static Vector<Palabra> palabrasTroll= new Vector<Palabra>(); 
	static Vector<Palabra> palabrasNoTroll= new Vector<Palabra>(); 
	static int nPalabrasTroll;
	static int nPalabrasNoTroll;
	static Pattern p = Pattern.compile("[a-zA-Z]");
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		FileReader corpus = new FileReader("CTR_TRAIN.txt");
		BufferedReader brcorpus = new BufferedReader(corpus);
		
		FileReader aprendizajeT = new FileReader("aprendizajeT.txt");
		BufferedReader brAprendizajeT = new BufferedReader(aprendizajeT);
		
		FileReader aprendizajenT = new FileReader("aprendizajenT.txt");
		BufferedReader brAprendizajenT = new BufferedReader(aprendizajenT);
		
		BufferedWriter calificacion = new BufferedWriter(new FileWriter("calificacion.txt"));
		
		//Pasar todo aprendizaje a un string.
		String palabra;
		String palabraaux;
		String npalabras;
		String[] datos;
		String[] dcadena;
		String[] dfrecuencia;
		String[] dprobabilidad;
		String[] dnpalabras;
		
		brAprendizajeT.readLine();
		npalabras= brAprendizajeT.readLine();
		dnpalabras= npalabras.split(":");
		nPalabrasTroll= Integer.parseInt(dnpalabras[1]);
		
		while((palabra = brAprendizajeT.readLine()) != null) {
			Scanner scan = new Scanner(palabra);
			while (scan.hasNext()) {
				palabraaux = scan.next().toLowerCase();
				dcadena= palabraaux.split(":");
				palabraaux = scan.next();
				dfrecuencia= palabraaux.split(":");
				palabraaux = scan.next();
				dprobabilidad= palabraaux.split(":");
				//public Palabra(String cadena, int frecuencia, float probabilidad)
				palabrasTroll.add(new Palabra(dcadena[1], Integer.parseInt(dfrecuencia[1]), Float.parseFloat(dprobabilidad[1])));
			}
			scan.close();
		}
		brAprendizajeT.close();
		
		brAprendizajenT.readLine();
		npalabras= brAprendizajenT.readLine();
		dnpalabras= npalabras.split(":");
		nPalabrasNoTroll= Integer.parseInt(dnpalabras[1]);
		
		while((palabra = brAprendizajenT.readLine()) != null) {
			Scanner scan = new Scanner(palabra);
			while (scan.hasNext()) {
				palabraaux = scan.next().toLowerCase();
				dcadena= palabraaux.split(":");
				palabraaux = scan.next();
				dfrecuencia= palabraaux.split(":");
				palabraaux = scan.next();
				dprobabilidad= palabraaux.split(":");
				//public Palabra(String cadena, int frecuencia, float probabilidad)
				palabrasNoTroll.add(new Palabra(dcadena[1], Integer.parseInt(dfrecuencia[1]), Float.parseFloat(dprobabilidad[1])));
			}
			scan.close();
		}
		brAprendizajenT.close();
		
		float probTroll= 0;
		float probNoTroll= 0;
		
		while((palabra = brcorpus.readLine()) != null) {
			Scanner scan = new Scanner(palabra);
			probTroll= 0;
			probNoTroll= 0;
			while (scan.hasNext()) {
				palabraaux = scan.next().toLowerCase();
				palabraaux = palabraaux.replaceAll("[^a-zA-Z ]", "").toLowerCase();
				Matcher m = p.matcher(palabraaux);
				if(m.find()) {
					probTroll= probTroll+ probTroll(palabraaux);
					probNoTroll= probNoTroll+ probNoTroll(palabraaux);
				}
			}
			
			if(probTroll> probNoTroll) {
				calificacion.write("troll\n");
			}
			else {
				calificacion.write("not_troll\n");
			}
			scan.close();
		}
		brcorpus.close();
		calificacion.close();
		
	}

	public static float probTroll(String word) {
		for(Palabra palabra: palabrasTroll) {
			if(palabra.getCadena().equals(word)) {
				return palabra.getProbabilidad();
			}
		}
		return (float)Math.log((1)/nPalabrasTroll+1)*100000;
	}
	
	public static float probNoTroll(String word) {
		for(Palabra palabra: palabrasNoTroll) {
			if(palabra.getCadena().equals(word)) {
				return palabra.getProbabilidad();
			}
		}
		return (float)Math.log((1)/nPalabrasNoTroll+1)*100000;
	}
	
}
