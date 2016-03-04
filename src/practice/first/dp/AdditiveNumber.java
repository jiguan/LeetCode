package practice.first.dp;

public class AdditiveNumber {
	public static boolean isAdditiveNumber1(String num) {
		int length = num.length();
		label: while (length > 0) {
			for (int i = length - 1; i >= 0; i--) {
				String sub_str = num.substring(i, length);
				if (sub_str.startsWith("0"))
					continue;
				for (int x = 1; x <= sub_str.length() && i >= x; x++) {
					int startPos1 = i - x;
					String a = num.substring(startPos1, i);
					if (a.startsWith("0") && a.length() != 1)
						continue;
					for (int y = 1; y <= sub_str.length() && startPos1 >= y; y++) {
						int startPos2 = startPos1 - y;
						String b = num.substring(startPos2, startPos1);
						if (b.startsWith("0") && b.length() != 1)
							continue;
						if (Double.valueOf(a) + Double.valueOf(b) == Double.valueOf(sub_str)) {
							length = i;
							if (startPos2 == 0)
								return true;
							continue label;
						}
					}
				}
			}
			return false;
		}
		return false;
	}

	public static boolean isAdditiveNumber(String num) {
		return check(0, num);
	}

	private static boolean check(int start, String num) {
		for (int i = start+1; i < num.length(); i++) {
			String a = num.substring(start, i);
			if(a.startsWith("0")&&a.length()!=1) continue;
			for (int j = i+1; j < num.length(); j++) {
				String b = num.substring(i, j);
				if(b.startsWith("0")&&b.length()!=1) continue;
				for(int k = j+1;k<=num.length();k++) {
					String c = num.substring(j, k);
					if(c.startsWith("0")&&c.length()!=1) continue;
					if(Double.valueOf(c)==Double.valueOf(a)+Double.valueOf(b)) {
						if(k==num.length()) return true;
						else {
							if(check(i, num)) return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String  num = "101";
		 System.out.println(isAdditiveNumber(num));
//		 num = "112358";
//		 System.out.println(isAdditiveNumber(num));
//		num = "1991000199299498797";
		// System.out.println(isAdditiveNumber(num));
		// num = "101";
		// System.out.println(isAdditiveNumber(num));
		num = "12012122436";
		System.out.println(isAdditiveNumber(num));
	}
}
