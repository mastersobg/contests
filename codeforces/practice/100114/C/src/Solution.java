import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	long[] precalc = { 1, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578, 48889, 122227, 155578, 48889,
			122227, 155578, 48889, 122227, 155578, 48889, 122227, 155578,
			48889, 122227, 155578, 48889, 122227, 155578, 48889, 122227,
			155578, 48889, 122227, 155578 };

	void solve() throws IOException {
		long n = nl() - 1;
		int pos = (int) (n / 1000000L);
		int shift = (int) (n % 1000000L);
		long value = precalc[pos];
		for (int i = 0; i < shift; ++i) {
			long next = value << 1L;
			next = sort(next);
			value = next;
		}
		out.println(value);
	}

	void pregenerate() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("test");
		out.println("1, ");
		long value = 1;
		for (long i = 1; i <= 2147483647L; ++i) {
			long next = value << 1L;
			next = sort(next);
			value = next;
			if (i % 1000000L == 0L) {
				out.println(value + ", ");
				out.flush();
			}
		}
		out.close();
	}

	int[] arr = new int[100];

	long sort(long a) {
		int size = toDigits(a, arr);
		Arrays.sort(arr, 0, size);
		long ret = 0;
		for (int i = 0; i < size; ++i)
			ret = ret * 10L + arr[i];
		return ret;
	}

	int toDigits(long a, int[] arr) {
		int ret = 0;
		while (a > 0) {
			arr[ret++] = (int) (a % 10L);
			a /= 10L;
		}
		return ret;
	}

	public Solution() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		solve();
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}

	long nl() throws IOException {
		return Long.valueOf(ns());
	}

	double nd() throws IOException {
		return Double.valueOf(ns());
	}

	public static void main(String[] args) throws IOException {
		new Solution();
	}
}
