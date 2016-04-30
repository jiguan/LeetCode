package practice.first.bit;

public class BitwiseAndOfNumbersRange {
	public int rangeBitwiseAnd(int m, int n) {
		int i=0;
		for(;i<32;i++) {
			if(m>>i == n>>i) break;
		}
		return m>> i << i;
	}
	
	
}
