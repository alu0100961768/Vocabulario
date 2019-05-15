import java.io.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Vocabulario {

	public static void main(String[] args) {

	try{

			FileReader fr = new FileReader("corpustodo.txt");
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter writer = new BufferedWriter(new FileWriter("vocabulario.txt"));

			String s;
			String word = null;
			String[] arrayWords;
			ArrayList aListWords = new ArrayList();

			Pattern p = Pattern.compile("[a-zA-Z]");
			
			int j = 0;
			while((s = br.readLine()) != null) {
				Scanner scan = new Scanner(s);
				while (scan.hasNext()) {
					word = scan.next().toLowerCase();
					
					word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
					Matcher m = p.matcher(word);
					
					if(m.find() && word.length()>2) {
						aListWords.add(word);
						j++;
					}	
				}
			}

			br.close();
			fr.close();
			removeDuplicates(aListWords);
			Collections.sort(aListWords);
			int size = aListWords.size();
			//System.out.println(j);
			writer.write("Número de palabras:"+ size +"\n");
			for(int i = 0; i < size ; i++){
				//System.out.println( aListWords.get( i ) );
				writer.write("Palabra:" + aListWords.get( i ).toString() + "\n");
			}
			writer.close();
			
	} catch (Exception e){//Catch exception if any
		System.err.println("Error: " + e.getMessage());
	}
	}

	public static void removeDuplicates(ArrayList aList) {
		HashSet h = new HashSet(aList);
		aList.clear();
		aList.addAll(h);
	}

}
