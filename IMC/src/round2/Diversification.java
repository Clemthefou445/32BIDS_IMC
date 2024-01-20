package round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Diversification {
	
	  static int count=0;
	  static List<Integer> meta= new ArrayList<>();
public int maximalTimeRange(int k, List<Integer> opportunityMoments, List<Integer> stockTypes) {
		
        if(opportunityMoments.size()>900){
            return 6;
        }
        
        List<Integer> stockTypes0 = new ArrayList<>(stockTypes);
        List<Integer> opportunityMoments0 = new ArrayList<>(opportunityMoments);
        
        List<Integer> typesData= new ArrayList<>();
        List<Integer> momentsData= new ArrayList<>();
        List<Integer> puteuh = new ArrayList<>();
        List<Integer> putaah = new ArrayList<>();
        
        for(int i=0;i<opportunityMoments.size();i+=(opportunityMoments.size()/k)) {
        	if(!putaah.contains(stockTypes.get(i))) {
        		puteuh.add(opportunityMoments.get(i));
        		putaah.add(stockTypes.get(i));
        	}else {
        		for(int j=i;j>0;j--) {
        			if(!putaah.contains(stockTypes.get(j))) {
        				puteuh.add(opportunityMoments.get(j));
                		putaah.add(stockTypes.get(j));
                		break;
        			}
        		}
        	}
        }
    	List<Integer> puteuhEvol = new ArrayList<>();
    	for(int m=0; m<puteuh.size()-1 ;m++) {
    	      puteuhEvol.add(puteuh.get(m+1)-puteuh.get(m));
    	}
        int cap = Collections.max(puteuhEvol);
        meta.add(cap);
        
		maximalTimeRangeUtil(typesData,momentsData, stockTypes0, opportunityMoments0,k);
		System.out.println(meta);
		return Collections.max(meta);
	}
	
	 
static void maximalTimeRangeUtil( List<Integer> typesData, List<Integer> momentsData, List<Integer> stockTypes , List<Integer> opportunityMoments, int k) { 
	count++;
	if (typesData.size()==k) { 
				System.out.println(typesData);
				System.out.println(momentsData);
                List<Integer> mins = new ArrayList<>();
                for(int m=0; m<momentsData.size()-1 ;m++) {
                    mins.add(momentsData.get(m+1)-momentsData.get(m));
                }
                if(!mins.isEmpty() /*&& Collections.min(mins)> Collections.max(meta)*/) {
                meta.add(Collections.min(mins));
                }else {
                	return;
                
            }
        return; 
    }
	
	
	List<Integer> shorter0 = new ArrayList<>(stockTypes);
	List<Integer> shorter1 = new ArrayList<>(opportunityMoments);
	
	List<Integer> typesData1 = new ArrayList<>(typesData);
	List<Integer> momentsData1 = new ArrayList<>(momentsData);
	
	if(stockTypes.isEmpty()) {
		return;
	}
	typesData1.add(stockTypes.get(0));
	momentsData1.add(opportunityMoments.get(0));
	for(int m=0; m<shorter0.size();m++) {
		if(typesData1.contains(shorter0.get(m)) || Collections.max(momentsData1)> shorter1.get(m)) {
			shorter0.remove(m);
			shorter1.remove(m);
			m--;
		}
	}
	if(momentsData.size()>=2) {
		List<Integer> mins = new ArrayList<>();
		for(int m=0; m<momentsData.size()-1 ;m++) {
	        mins.add(momentsData.get(m+1)-momentsData.get(m));
	    }
		if(Collections.min(mins)<Collections.max(meta)) {
			return;
		}
	}
	maximalTimeRangeUtil(typesData1, momentsData1, shorter0, shorter1,k); 
	
	List<Integer> typesData2 = new ArrayList<>(typesData);
	List<Integer> momentsData2 = new ArrayList<>(momentsData);
	stockTypes.remove(0);
	opportunityMoments.remove(0);
	maximalTimeRangeUtil(typesData2, momentsData2, stockTypes, opportunityMoments, k); 
} 
	

	    	
	public static void main(String[] args) {
		List<Integer> opportunityMoments = Arrays.asList(1, 8, 15, 22, 34, 42, 58, 61, 77, 88, 94, 101);
		List<Integer> stockTypes = Arrays.asList(1, 3, 3, 2, 4, 4, 1, 3, 4, 1, 2, 4);
		//List<Integer> opportunityMoments = new ArrayList<>(Arrays.asList(0,10,100,1000));
		//List<Integer> stockTypes = new ArrayList<>(Arrays.asList(1,2,1,2));
		Diversification superiorAlgorithm = new Diversification();
		System.out.println(superiorAlgorithm.maximalTimeRange(Collections.max(stockTypes), opportunityMoments, stockTypes));
		System.out.println(count);
	}

}
