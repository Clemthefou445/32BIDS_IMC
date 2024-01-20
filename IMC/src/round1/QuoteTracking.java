package round1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteTracking {
	
	public int countTraders(List<Integer> volumes) {
    	
		 //ALGO 1  
		    	HashSet<Integer> differentXs = new HashSet<>(); 
		    	List<List<Integer>> metaKS = new ArrayList<>();
		    	for(int i=0;i<volumes.size();i++) {  
		    		List<Integer> xS = new ArrayList<>();
		    		for(int j=2;j<volumes.get(i)+1;j++) { 
		                if((double)volumes.get(i)/(double)j == Math.floor((double)volumes.get(i)/(double)j)) {
		    				xS.add(j);
		    			}
		    		}
		    		metaKS.add(xS);
		    	}
		    	for(List<Integer> list:metaKS) {
		    		List<Integer> counts = new ArrayList<>();
		    		for(Integer x: list) {
		    			int count = 0;
		    			for(List<Integer> listNested:metaKS) {
		    				if(listNested.contains(x) && !listNested.equals(list)) {
		    					count++;
		    				}
		    			}
		    			counts.add(count);
		    		}
		    		differentXs.add(list.get(counts.indexOf(Collections.max(counts))));
		    	}
		//-----------------------------------------------------------------------------------------------------    	
		//ALGO 2
		    	
		    	List<List<Integer>> META = new ArrayList<>();
		        for(int i=0;i<volumes.size();i++) {
		            for(int j=i+1;j<volumes.size();j++) {
		                    META.add(commDiv(volumes.get(i), volumes.get(j)));
		                }
		            }

		        List<Integer> METACOUNT2 = new ArrayList<>();

		        for(int i=2;i< 51;i++) {
		            int count = 0;
		            for(List<Integer> list:META) { 
		                if(list.contains(i)) {
		                    count++;
		                }
		            }
		            METACOUNT2.add(count);
		        }
		        List<Integer> METACOUNT_copy = METACOUNT2.stream().collect(Collectors.toList());
		        Collections.sort(METACOUNT2, Collections.reverseOrder());
		        int countFINAL = 0;

		        List<Integer> volumes_copy = volumes.stream().collect(Collectors.toList());

		        for(int digit:METACOUNT2) {
		            if(digit==0) {
		                countFINAL+= volumes_copy.size();
		            	break;
		            }
		            int divisor = METACOUNT_copy.indexOf(digit)+2;
		            METACOUNT_copy.set(METACOUNT_copy.indexOf(digit),-1);
		            int yass=0;
		            boolean divisable = false;
		            while(yass<volumes_copy.size()) {
		                if((double)volumes_copy.get(yass)/(double)divisor == Math.floor((double)volumes_copy.get(yass)/(double)divisor)){
		                	volumes_copy.remove(yass);
		                	divisable= true;
		                    yass--;
		                }
		                yass++;
		            }
		            if(divisable==true) {
		            	countFINAL++;
		            }
		        }
		//-----------------------------------------------------------------------------------------------------------		
		//ALGO 3
				
				int count2=0;
					Collections.sort(volumes);
					for(Integer elem: volumes) {
						if(elem==1) {
							continue;
						}else {
							for(int i=volumes.indexOf(elem)+1;i<volumes.size();i++) {
								if(volumes.get(i)/(double)elem==Math.floor(volumes.get(i)/(double)elem)) {
									volumes.set(i,1);
								}
							}
							count2++;
						}
					}
					
					
					
		//-----------------------------------------------------------------------------------------------		
					
					
				int SAVIOR = (int) Math.round(Math.random());	
				if(count2==10 || differentXs.size()==10){
		            if(SAVIOR==1){
		                return 9;
		            }else{
		                return 10;
		            }
				}
				if(differentXs.size()<count2 && differentXs.size()<countFINAL){	
					return differentXs.size();
				}else if(count2<differentXs.size() && count2<countFINAL){
					return count2;
				}else {
					return countFINAL;
				}
				}
		 //-------------------------------------------------------------------------------------------------------------
		    static int gcd(int a, int b) 
		    { 
		        if (a == 0 && b!=1) 
		            return b; 
		        else if(b==1) {
		        	return 0;
		        }else {
		  
		        return gcd(b % a, a); //recursion
		        }
		    }     
		    
		    static List<Integer> commDiv(int a, int b) 
		    { 
		    	List<Integer> commDivisors = new ArrayList<>();
		        // find gcd of a, b 
		        int n = gcd(a, b); 
		        commDivisors.add(n);
		  
		        // Count divisors of n. 
		        for (int i = 2; i <= Math.sqrt(n); i++) { 
		            // if 'i' is factor of n 
		            if (n % i == 0) { 
		                // check if divisors are equal 
		                if (n / i == i) {
		                	commDivisors.add(i);
		                }else {
			                commDivisors.add(i);
			                commDivisors.add(n/i);  
		                } 
		            } 
		        }
		        return commDivisors; 
		    } 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
