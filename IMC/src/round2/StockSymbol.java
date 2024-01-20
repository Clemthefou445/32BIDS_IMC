package round2;

import java.util.Arrays;
import java.util.List;

public class StockSymbol {
	
	final static Character[] alphabet = new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k', 'l', 'm','n','o',
			'p','q','r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	 public boolean isValidSymbol(String word) {
		 	if(word.length()<=4 && word.length()>0 && latinAlphabet(word) && word==word.toUpperCase()) {
		 		return true;
		 	}
	       return false;
	    }
	 
	 private static boolean latinAlphabet(String word) {
		 List<Character> alpha = Arrays.asList(alphabet);
		 char[] chars = word.toLowerCase().toCharArray();
		 for(int i=0;i<chars.length;i++) {
			 if(i!=chars.length-1) {
				 if(alpha.contains(chars[i])) {
					 continue;
				 }else {
					 break;
				 }
			 }else {
				 return true;
			 }
		 }
		 return false;
	 }
	
	public static void main(String[] args) {
		StockSymbol AI_Tool = new StockSymbol();
		System.out.println(AI_Tool.isValidSymbol("IIII"));

	}

}
