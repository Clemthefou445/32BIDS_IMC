package round1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WorldIndex {
	
	private static String str; 

	private int maxOverlapOfPairs(String str1, String str2)  { 
		int max = -1; 
	    int len1 = str1.length(); 
	    int len2 = str2.length(); 
	
	    for (int i = 1; i <= Math.min(len1, len2); i++) { 
	        if (str1.substring(len1 - i).compareTo(str2.substring(0, i)) == 0) { 
	            if (max < i) {  
	                max = i; 
	                str = str1 + str2.substring(i); 
	            } 
	        } 
	    } 
	    for (int i = 1; i <= Math.min(len1, len2); i++) { 
	        if (str1.substring(0, i).compareTo(str2.substring(len2 - i)) == 0){ 
	            if (max < i) { 
	                max = i; 
	                str = str2 + str1.substring(i); 
	            } 
	        } 
	    }
	    
	    if(max==-1) {
	    	for(int i=0; i<Math.max(len1, len2);i++) {
	    		if(len1 > len2 && len1-i>=len2) {
		    		if(str1.substring(i, i+len2).compareTo(str2) == 0) {
		    			if (max < len2) { 
			                max = len2; 
			                str = str1; 
			            }
		    		}
	    		}else if(len1-i>=len2){
	    			if(str2.substring(i, i+len1).compareTo(str1) == 0) {
		    			if (max < len1) { 
			                max = len1; 
			                str = str2; 
			            } 
	    			}
	    		}
	    	}
	    }
	    
	    return max; 
	} 

	static String makeName(List<String> arr) { 
		//PREPROCESSING
		//---------------------------------------------------------------------------------		
		List<String> letters_5 = new ArrayList<>();
	    List<String> letters_4 = new ArrayList<>();
	    List<String> letters_3 = new ArrayList<>();
	    List<String> letters_2 = new ArrayList<>();
	    for(int i=0; i<arr.size();i++) {
	    	switch(arr.get(i).length()) {
	    		case 5:
	    			letters_5.add(arr.get(i));
	    			break;
	    		case 4:
	    			letters_4.add(arr.get(i));
	    			break;
	    		case 3:
	    			letters_3.add(arr.get(i));
	    			break;
	    		case 2:
	    			letters_2.add(arr.get(i));
	    			break;
	    	}
	    }
	    
	    for(String word5 : letters_5) {
	    		letters_4.removeIf(i -> word5.contains(i)); //TRES BELLE DECOUVERTE !!! EVITER L'ENORME FOR LOOP NESTED 4 levels.
	    		letters_3.removeIf(i -> word5.contains(i));
	    		letters_2.removeIf(i -> word5.contains(i));
	    }
	    
	    for(String word5 : letters_5) {
	    letters_5.removeIf(i -> word5.contentEquals(i) && !word5.equals(i));
	    }
	    for(String word4 : letters_4) {
		    letters_4.removeIf(i -> word4.contentEquals(i) && !word4.equals(i));
		}
	    for(String word3 : letters_3) {
		    letters_3.removeIf(i -> word3.contentEquals(i) && !word3.equals(i));
		}
	    for(String word2 : letters_2) {
		    letters_2.removeIf(i -> word2.contentEquals(i) && !word2.equals(i));
	    }
	    
	    List<List<String>> resultHomemade = new ArrayList<>();
	    resultHomemade.add(letters_5);
	    resultHomemade.add(letters_4);
	    resultHomemade.add(letters_3);
	    resultHomemade.add(letters_2);
	    
	    List<String> arrPRE_forward= resultHomemade.stream()
	            .flatMap(List::stream)
	            .collect(Collectors.toList());
	    List<String> arrPRE_shuffled1= resultHomemade.stream()
	    	            .flatMap(List::stream)
	    	            .collect(Collectors.toList());
	    List<String> arrPRE_shuffled2= resultHomemade.stream()
	            .flatMap(List::stream)
	            .collect(Collectors.toList());
	    Collections.shuffle(arrPRE_shuffled1);
	    Collections.shuffle(arrPRE_shuffled2);
	    System.out.println(arrPRE_forward);
//--------------------------------------------------------------------------------------		
	     int len1 = arrPRE_forward.size();
	     int len2 = arrPRE_shuffled1.size();
         int len3 = arrPRE_shuffled2.size();
	     while (len1 != 1) { 
	        int max = -1;
	        int l = 0, r = 0; 
	        String name = "";
	        for (int i = 0; i < len1; i++) { 
	            for (int j = i + 1; j < len1; j++) {
	            	WorldIndex res0 = new WorldIndex();
	                int res = res0.maxOverlapOfPairs(arrPRE_forward.get(i), arrPRE_forward.get(j));
	                if (max < res) { 
	                    max = res; 
	                    name = str; 
	                    l = i; 
	                    r = j; 
	                }
	                //System.out.println(res);
	            } 
	        } 
	        
	        for (int i = len1-1; i>=0; i--) { 
	            for (int j = i + 1; j < len1; j++) {
	            	WorldIndex res0 = new WorldIndex();
	                int res = res0.maxOverlapOfPairs(arrPRE_forward.get(i), arrPRE_forward.get(j));
	                if (max < res) { 
	                    max = res; 
	                    name = str; 
	                    l = i; 
	                    r = j; 
	                }
	                //System.out.println(res);
	            } 
	        } 
	
	        len1--;
	        if (max == -1) {
	            arrPRE_forward.set(0, arrPRE_forward.get(0) + arrPRE_forward.get(len1)); 
	            //System.out.println(arr.get(0));
            }else { 
	            arrPRE_forward.set(l, name);
	            arrPRE_forward.set(r, arrPRE_forward.get(len1)); 
	            //System.out.println(arr.get(l));
	            //System.out.println(arr.get(r));
	        } 
	    }
	     while (len2 != 1) { 
		        int max = -1;
		        int l = 0, r = 0; 
		        String name = "";
		        for (int i = 0; i < len2; i++) { 
		            for (int j = i + 1; j < len2; j++) {
		            	WorldIndex res0 = new WorldIndex();
		                int res = res0.maxOverlapOfPairs(arrPRE_shuffled1.get(i), arrPRE_shuffled1.get(j));
		                if (max < res) { 
		                    max = res; 
		                    name = str; 
		                    l = i; 
		                    r = j; 
		                }
		            } 
		        } 
		        
		        for (int i = len2-1; i>=0; i--) { 
		            for (int j = i + 1; j < len2; j++) {
		            	WorldIndex res0 = new WorldIndex();
		                int res = res0.maxOverlapOfPairs(arrPRE_shuffled1.get(i), arrPRE_shuffled1.get(j));
		                if (max < res) { 
		                    max = res; 
		                    name = str; 
		                    l = i; 
		                    r = j; 
		                }
		            } 
		        } 
		
		        len2--;
		        if (max == -1) {
		        	arrPRE_shuffled1.set(0, arrPRE_shuffled1.get(0) + arrPRE_shuffled1.get(len2)); 
		            //System.out.println(arr.get(0));
	            }else { 
	            	arrPRE_shuffled1.set(l, name);
	            	arrPRE_shuffled1.set(r, arrPRE_shuffled1.get(len2)); 
		            System.out.println(arrPRE_shuffled1);
		            //System.out.println(arr.get(r));
		        } 
		    }
        while (len3 != 1) { 
	        int max = -1;
	        int l = 0, r = 0; 
	        String name = "";
	        for (int i = 0; i < len3; i++) { 
	            for (int j = i + 1; j < len3; j++) {
	            	WorldIndex res0 = new WorldIndex();
	                int res = res0.maxOverlapOfPairs(arrPRE_shuffled2.get(i), arrPRE_forward.get(j));
	                if (max < res) { 
	                    max = res; 
	                    name = str; 
	                    l = i; 
	                    r = j; 
	                }
	                //System.out.println(res);
	            } 
	        } 
	        
	        for (int i = len3-1; i>=0; i--) { 
	            for (int j = i + 1; j < len3; j++) {
	            	WorldIndex res0 = new WorldIndex();
	                int res = res0.maxOverlapOfPairs(arrPRE_shuffled2.get(i), arrPRE_shuffled2.get(j));
	                if (max < res) { 
	                    max = res; 
	                    name = str; 
	                    l = i; 
	                    r = j; 
	                }
	                //System.out.println(res);
	            } 
	        } 
	
	        len3--;
	        if (max == -1) {
	            arrPRE_shuffled2.set(0, arrPRE_shuffled2.get(0) + arrPRE_shuffled2.get(len3)); 
	            //System.out.println(arr.get(0));
            }else { 
	            arrPRE_shuffled2.set(l, name);
	            arrPRE_shuffled2.set(r, arrPRE_shuffled2.get(len3)); 
	            //System.out.println(arr.get(l));
	            //System.out.println(arr.get(r));
	        } 
	    }
	    if(arrPRE_forward.get(0).length()<arrPRE_shuffled1.get(0).length()) {
	    	return arrPRE_forward.get(0); 
	    }else {
	    	return arrPRE_shuffled1.get(0);
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
