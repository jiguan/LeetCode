package practice.first.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class BullsAndCows {
	public String getHint1(String secret, String guess) {
		int n = secret.length();
		int bull = 0, cow = 0;
		if(n==0) return "0A0B";
		List<Character> missedA = new ArrayList<Character>();
		List<Character> missedB = new ArrayList<Character>();
		for(int i=0;i<n;i++) {
			char a = secret.charAt(i);
			char b = guess.charAt(i);
			if(a==b) {
				bull++;
			} else {
				missedA.add(a);
				missedB.add(b);
			}
		}
		for(int i=0;i<missedA.size();i++) {
			char a = missedA.get(i);
			int index = missedB.indexOf(a);
			if(index!=-1) {
				cow++;
				missedB.remove(index);
			}
		}
		return bull+"A"+cow+"B";
	}
	
	public String getHint2(String secret, String guess) {
		int n = secret.length();
		int bull = 0, cow = 0;
		if(n==0) return "0A0B";
		Map<Character, Integer> map = new HashMap<>();
		for(int i=0;i<n;i++) {
			char a = secret.charAt(i);
			char b = guess.charAt(i);
			if(a==b) {
				bull++;
			} else {
				int count = map.containsKey(b) ? map.get(b) : 0;
				map.put(b, ++count);
			}
		}
		for(int i=0;i<n;i++) {
			char a = secret.charAt(i);
			char b = guess.charAt(i);
			if(a!=b && map.containsKey(a) && map.get(a)>0) {
				map.put(a, map.get(a)-1);
				cow++;
			}
		}
		return bull+"A"+cow+"B";
	}
	
	public String getHint(String secret, String guess) {
		int n = secret.length();
		int bull = 0, cow = 0;
		if(n==0) return "0A0B";
		Map<Character, Integer> map = new HashMap<>();
		for(int i=0;i<n;i++) {
			char a = secret.charAt(i);
			char b = guess.charAt(i);
			if(a==b) {
				bull++;
			} else {
				if(map.containsKey(a)) {
					int timeA = map.get(a);
					if(timeA<0) { //negative means it shows up in guess
						cow++;
					}
					map.put(a, timeA+1);
				} else {
					map.put(a, 1);
				}
				if(map.containsKey(b)) {
					int timeB = map.get(b);
					if(timeB>0) {
						cow++;
					}
					map.put(b, timeB-1);
				} else {
					map.put(b, -1);
				}
			}
		}
		return bull+"A"+cow+"B";
	}
	
	@Test
	public void defaultTest() {
		assertEquals("1A3B", getHint("1807", "7810"));
		assertEquals("1A1B", getHint("1123", "0111"));
		assertEquals("1A0B", getHint("11", "10"));
		assertEquals("1A0B", getHint("11", "01"));
		assertEquals("0A4B", getHint("1122", "2211"));
	}
}
