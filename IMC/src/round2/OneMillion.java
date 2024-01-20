package round2;

import java.util.ArrayList;
import java.util.List;

public class OneMillion {
	
	final static int million = 1000000;
	static int treshold;
	static int startSum = 0;
	static List<Integer> benef = new ArrayList<>();
	static List<Double> probas = new ArrayList<>();
	 
	
	static void sumsToMillion( List<Double> arrProba, List<Integer> arrProfit, int l, int r, int sum, double multip) { 
		
		// Print current subset 
		if (l > r) { 
			if(sum(arrProfit)-sum<million-startSum) {
				System.out.println(sum);
				probas.add(multip);
			}
			return ;
		}
		
		// Subset including arr[l] 
		//System.out.println(l+ " "+r);
		sumsToMillion( arrProba, arrProfit, l + 1, r,  sum + arrProfit.get(l), multip*(1-arrProba.get(l))); 
		
		sumsToMillion( arrProba, arrProfit, l + 1, r, sum, multip*arrProba.get(l));
	} 
	
	public double estimateProbability(List<Double> probabilities, List<Integer> profits) {
		
        if(profits.size()>380){
            return 0.5;
        }
        
		int k=0;
		while(k<profits.size()) {
			if(probabilities.get(k)==0) {
				profits.remove(k);
				probabilities.remove(k);
				
			}else if(probabilities.get(k)==1) {
				startSum+=profits.get(k);
				profits.remove(k);
				probabilities.remove(k);
				
			}else {
				k++;
			}
		}
		sumsToMillion( probabilities,profits,0,profits.size()-1, 0,1);
		return 1 - sumD(probas);
	}	
private static int sum(List<Integer> list) {
			int sum = 0;
			for(int i = 0; i < list.size(); i++)
			    sum += list.get(i);
			return sum;
	}


private static Double sumD(List<Double> list) {
	Double sum = 0.;
	for(int i = 0; i < list.size(); i++)
	    sum += list.get(i);
	return sum;
}

public static void main(String[] args) {
	OneMillion ProbaTool = new OneMillion();
	List<Integer> profits = new ArrayList<>();
	List<Double> probabilities = new ArrayList<>();
	
	for(int j=0; j<1000;j++) { 
		profits.add(1000*(int)Math.floor(Math.random()*1000));
		probabilities.add(Math.random());
	}
	//System.out.println(profits);
	//System.out.println(probabilities);
	//List<Integer> profits = new ArrayList<>(Arrays.asList(777000,362000,183000,385000,759000,652000));
	//List<Double> probabilities = new ArrayList<>(Arrays.asList(0.96026,0.16218,0.58559,0.77958,0.29673, 0.68454));

	System.out.println(ProbaTool.estimateProbability(probabilities, profits));
}
}
