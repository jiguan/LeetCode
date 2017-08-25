package com.basic;

public class DataTypeMemory {
	public static void main(String[] args) {
		System.out.println("boolean : 1 bit");
		System.out.println("byte : 8 bit");
		System.out.println("char : 2 byte");
		System.out.println("int : 4 byte");
		System.out.println("long : 8 byte");
		System.out.println("float : 4 byte");
		System.out.println("double : 8 byte");
		System.out.println("string : 2 * len + 8 + 4 + 4");
	}

	/*
	 * Java uses two bytes per character *, so you would need to multiply the
	 * number of characters by two to get a rough approximation. In addition to
	 * the storage of the "payload", you would need to account for the [space]
	 * allocated to the reference to your string, which usually equals to the
	 * size of a pointer on your target architecture, the space for the [length]
	 * of the string, which is an int, and the space for the cached [hash code],
	 * which is another int.
	 * 
	 * Since, "Hello World" is 11 characters long, I would estimate its size as
	 * 2*11+4+4+4=34 bytes on computers with 32-bit pointers, or 2*11+8+4+4=38
	 * bytes on computers with 64-bit pointers.
	 */
}
