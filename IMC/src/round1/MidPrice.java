package round1;

public class MidPrice {
	
	public int calculatePrice(int midPrice, int bidPrice) {        
        int askPrice = midPrice*2-bidPrice;
        return askPrice;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
