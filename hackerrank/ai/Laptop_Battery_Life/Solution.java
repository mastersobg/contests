import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

    Read file, console;
    PrintWriter out;

    final double eps = 1e-9;

    boolean eq(double a, double b) {
        return abs(a - b) < eps;
    }

	void solve() throws IOException {
        double[][] arr = loadData();
        double maxBatTime = 0.0;
        for (double[] a : arr) {
            maxBatTime = max(maxBatTime, a[1]);
        }
        double minChargeTime = 1e+20;
        for (double[] a : arr) {
            if (eq(a[1], maxBatTime)) {
                minChargeTime = min(minChargeTime, a[0]);
            }
        }
        System.err.println(maxBatTime + " " + minChargeTime);
        double frac = maxBatTime / minChargeTime;
        String line = null;
        while((line = console.in.readLine()) != null) {
            double time = Double.valueOf(line);
            double ret = frac * time;
            out.printf("%.2f\n", ret);
            out.flush();
        }
	}

    double[][] loadData() throws IOException {
        List<double[]> values = new ArrayList<double[]>();
        String line = null;
        while((line = file.in.readLine()) != null) {
            String []arr = line.split(",");
            double []pair = new double[2];
            pair[0] = Double.valueOf(arr[0]);
            pair[1] = Double.valueOf(arr[1]);
            values.add(pair);
        }
        double [][]ret = new double[values.size()][];
        int idx = 0;
        for (double []a : values) {
            ret[idx++] = a;
        }
        return ret;
    }


	public void run() throws IOException {
		Locale.setDefault(Locale.US);
        file = new Read(new FileReader("trainingdata.txt"));
        console = new Read(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
        file.close();
        console.close();
		out.close();
	}


    class Read {
    
        BufferedReader in;
        StringTokenizer st;

        Read(Reader in) {
            this.in = new BufferedReader(in);
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

        void close() throws IOException {
            in.close();
        }
    }
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}
}
