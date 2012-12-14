import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	Timer timer = new Timer();
	int size = 10000000;
	Random rnd = new Random(13223L);

	void solve() throws IOException {
		System.err.println("Sorting " + size + " elements...");
		testByte();
		testChar();
		testShort();
		testInt();
		testLong();
		testDouble();
		testFloat();
	}

	void testByte() {
		byte[] arr = new byte[size];
		rnd.nextBytes(arr);
		timer.start();
		Arrays.sort(arr);
		System.err.println("Sorting byte array = " + timer.get());
	}

	void testChar() {
		char[] arr = new char[size];
		for (int i = 0; i < size; ++i)
			arr[i] = (char) rnd.nextInt();
		timer.start();
		Arrays.sort(arr);
		System.err.println("Sorting char array = " + timer.get());
	}

	void testShort() {
		short[] arr = new short[size];
		for (int i = 0; i < size; ++i)
			arr[i] = (short) rnd.nextInt();
		timer.start();
		Arrays.sort(arr);
		System.err.println("Sorting short array = " + timer.get());
	}

	void testInt() {
		int[] arr = new int[size];
		for (int i = 0; i < size; ++i)
			arr[i] = rnd.nextInt();
		timer.start();
		Arrays.sort(arr);
		System.err.println("Sorting int array = " + timer.get());
	}

	void testLong() {
		long[] arr = new long[size];
		for (int i = 0; i < size; ++i)
			arr[i] = rnd.nextLong();
		timer.start();
		Arrays.sort(arr);
		System.err.println("Sorting long array = " + timer.get());
	}

	void testDouble() {
		double[] arr = new double[size];
		for (int i = 0; i < size; ++i)
			arr[i] = rnd.nextDouble();
		timer.start();
		Arrays.sort(arr);
		System.err.println("Sorting double array = " + timer.get());
	}

	void testFloat() {
		float[] arr = new float[size];
		for (int i = 0; i < size; ++i)
			arr[i] = rnd.nextFloat();
		timer.start();
		Arrays.sort(arr);
		System.err.println("Sorting float array = " + timer.get());
	}

	static class Timer {
		long time;

		void start() {
			time = System.currentTimeMillis();
		}

		long get() {
			return System.currentTimeMillis() - time;
		}
	}

	public Solution() throws IOException {
		solve();
	}

	public static void main(String[] args) throws IOException {
		new Solution();
	}
}
