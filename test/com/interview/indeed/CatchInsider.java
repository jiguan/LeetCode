package com.interview.indeed;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;

public class CatchInsider {
	 Map<Integer,Double> priceMap  = new HashMap<>();
	SortedMap<Integer,List<Transaction>> transactionMap = new TreeMap<>();

	public String[] catchInsiders(String[] input) {
		initialData(input);
		List<Transaction> result = new ArrayList<>();
		for (Integer key : transactionMap.keySet()) {
			List<Transaction> currentSet = transactionMap.get(key);
			for (Transaction tr : currentSet) {
				double curValue = tr.amount * priceMap.get(key);
				for (int i = 1; i <= 3; i++) {
					if (priceMap.get(key + i) != null) {
						double delta = tr.amount * priceMap.get(key + i) - curValue;
						if ((tr.buy && delta >= 5000000) || (!tr.buy && delta <= -5000000)) {
							result.add(tr);
						}
					}
				}
			}
		}
		
		if(result == null || result.size() == 0) {
			return  new String[0];
		}
		String[] arr = new String[result.size()];
		int index = 0;
		for(Transaction cur:result){
			arr[index++] = cur.day +"|" + cur.name;
		}
		return arr;

	}
	private void initialData(String[] input) {
		for(String st : input){
			String[] stArr = st.split("\\|");
			if(stArr.length == 2) {
				priceMap.put(Integer.parseInt(stArr[0]),Double.parseDouble(stArr[1]));
			} else if(stArr.length == 4){
				Transaction trans = new Transaction(Integer.parseInt(stArr[0]),stArr[2].equals("BUY"),
						stArr[1],Integer.parseInt(stArr[3]));
				int key = Integer.parseInt(stArr[0]);
				if(!transactionMap.containsKey(key)){
					transactionMap.put(key,  new ArrayList<Transaction>());
				}
				transactionMap.get(key).add(trans);
			}
		}
	}
	public static void main(String[] args) {
		String[] data = new String[]{"1|700", "2|10000","3|50","4|700", "5|10","6|50000","1|Bob|BUY|30000", "1|Alan|BUY|20000"
				,"2|tob|SELL|30000", "3|Ann|BUY|20000","5|Bb|BUY|30000", "4|Aggn|BUY|20000"};
		CatchInsider cs = new CatchInsider();
		String[] result = cs.catchInsiders(data);
		System.out.println(result.toString());
	}
	
	class Transaction implements Comparable<Transaction> {
		int day;
		boolean buy;
		String name;
		int amount;
		public Transaction(int day, boolean buy, String name, int amount) {
			this.day = day;
			this.buy = buy;
			this.name = name;
			this.amount = amount;
		}
		
		@Override
		public int compareTo(Transaction t2) {
			if(this.day == t2.day){
				return  this.name.compareTo(t2.name);
			} else {
				return this.day -t2.day;
			}
			 
		}
		 
		
	}
}
