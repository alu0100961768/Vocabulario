import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;

public class Aprendizaje {

	public static Vector<Pareja> vectorVocabulario= new Vector<Pareja>();
	public static int unknown;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub:
		System.out.print("test");
		unknown=1;
		FileReader corpus = new FileReader("corpusnT.txt");
		BufferedReader brcorpus = new BufferedReader(corpus);
		FileReader vocabulario = new FileReader("vocabulario.txt");
		BufferedReader brvocabulario = new BufferedReader(vocabulario);
		
		int nElementosCorpus=0;
		
		String s;
		String[] ps;
		String word = null;
		String[] palabrasCorpus= null;
		ArrayList listPalabrasCorpus = new ArrayList();

		Pattern p = Pattern.compile("[a-zA-Z]");
		
		//brcorpus.readLine();
		
		while((s = brcorpus.readLine()) != null) {
			Scanner scan = new Scanner(s);
			while (scan.hasNext()) {
				word = scan.next().toLowerCase();
				word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
				Matcher m = p.matcher(word);
				if(m.find()) {
					listPalabrasCorpus.add(word);
					nElementosCorpus++;
				}
			}
			scan.close();
		}
		brcorpus.close();
		corpus.close();
		
		String ppv= brvocabulario.readLine();
		ps= ppv.split(":");
		int numeropalabrasvocabulario= Integer.parseInt(ps[1]);
		
		while((s = brvocabulario.readLine()) != null) {
			Scanner scan = new Scanner(s);
			while (scan.hasNext()) {
				word = scan.next().toLowerCase();
				
				ps= word.split(":");
				word= ps[1];
				
				word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
				
				Matcher m = p.matcher(word);
				
				if(m.find()) {
					//Pareja pareja= new Pareja(word);
					vectorVocabulario.add(new Pareja(word));
				}	
			}
			scan.close();
		}
		brvocabulario.close();
		vocabulario.close();
		
		int size = listPalabrasCorpus.size();
		for(int i = 0; i < size ; i++){
			if(!doesContains(listPalabrasCorpus.get(i).toString())) {
				unknown++;
			}
		}
		//int numeropalabrasvocabulario= Integer.parseInt(ppv);
		BufferedWriter aprendizaje = new BufferedWriter(new FileWriter("aprendizajenT.txt"));
		//int size= vectorVocabulario.size();
		
		aprendizaje.write("Número de documentos del corpus:"+ listPalabrasCorpus.size() +"\n");
		aprendizaje.write("Número de palabras del corpus:"+ numeropalabrasvocabulario +"\n");
		
		for(Pareja pareja: vectorVocabulario) {
			aprendizaje.write("Palabra:"+pareja.getPalabra()+ " Frec:" + pareja.getValor() + " LogProb:" + Math.log(((float)pareja.getValor())/(numeropalabrasvocabulario+listPalabrasCorpus.size()+1))*100000 + "\n");
		}
		aprendizaje.write("Palabra:<desconocido> " + "Frec:" + unknown + " LogProb:" + Math.log(((float)unknown)/(numeropalabrasvocabulario+listPalabrasCorpus.size()+1))*100000 + "\n");
//		for(int i = 0; i < size ; i++){
//			//System.out.println( aListWords.get( i ) );
//			aprendizaje.write(listPalabrasCorpus.get( i ).toString() + "\n");
//		}
		aprendizaje.close();
	}

	
	public static boolean doesContains(String word) {
		
		for(Pareja pair: vectorVocabulario) {
			
			if(pair.getPalabra().equals(word)) {
				pair.setValor(pair.getValor()+1);
				return true;
			}
		}
		//System.out.println("Chivato2");
		return false;
	}
	
	


}
