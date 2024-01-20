package round1;

import java.util.ArrayList;
import java.util.List;

public class TwoStocks {
	
	public int calculateProfit(List<Integer> firstStockPrices, List<Integer> secondStockPrices) {
        int maxProfit = 0;
        List<Integer> firstYields = new ArrayList<Integer>();
        List<Integer> secondYields = new ArrayList<Integer>();
		for(int i=0;i<(firstStockPrices.size()-1);i++){
            int yield = firstStockPrices.get(i+1)-firstStockPrices.get(i);
            firstYields.add(yield);
        }
        for(int i=0;i<secondStockPrices.size()-1;i++){
            int yield = secondStockPrices.get(i+1)-secondStockPrices.get(i);
            secondYields.add(yield);
        }
        
        for(int i=0;i<firstStockPrices.size()-1;i++){
            if(firstYields.get(i)<=0 && secondYields.get(i)<=0){
                continue;
            }else if(firstYields.get(i)>secondYields.get(i)){
                maxProfit+=firstYields.get(i);
            }else{
                maxProfit+=secondYields.get(i);
            }
        }
        return maxProfit;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
