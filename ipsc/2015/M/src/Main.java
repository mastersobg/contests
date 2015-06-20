import java.io.*;
import java.util.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static java.lang.Math.*;

public class Main {

    BufferedReader in;
    StringTokenizer st;
    PrintWriter out;

    int MAX = 5000;
    String []d = new String[MAX * 2];
    char []sign = new char[MAX * 2];

    void solve() throws IOException {
        
        
        d[MAX] = "+![]";
        d[MAX + 1] = "!+[]";
        sign[MAX] = '*';
        sign[MAX + 1] = '*';
        boolean updated = true;
        while (updated) {
            updated = false;
            for (int i = 0; i < d.length; ++i) {
                int a = i - MAX;
                if (d[i] == null || a < 0)
                    continue;
                for (int j = 0; j < d.length; ++j) {
                    int b = j - MAX;
                    if (d[j] == null || b < 0)
                        continue;

                    int c = a + b;
                    updated |= relax(d, d[i] + "+" + d[j], c + MAX, '+');
                    

                    c = a * b;
                    updated |= mult(i, j, c); //relax(d, "[" + d[i] + "]*[" + d[j] + "]", c + MAX, '*');
                    

                    c = a - b;
                    
                    boolean x = sub(i, j, c);//relax(d, d[i] + "-[" + d[j] + "]", c + MAX, '-');
                    updated |= x;
                    // if (x && c == 142) {
                    // 	dbg(d[i], "////////////", d[j]);                    	
                    // }
                    
                }
            }
        }
        d[MAX + 1] = "+!![]";
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");

        
        for (int i = MAX, j = 0; i < MAX * 2 && j <= 1000; ++i, ++j) {
            if (d[i].length() > 75) {
                dbg(j, d[i].length());
            }
            try {
            	if (!new Boolean(engine.eval("typeof(" + d[i] + ") == \"number\"").toString())) {
            		throw new IllegalArgumentException(d[i]);
            	}
				Double x = new Double(engine.eval(d[i]).toString());
				if (x.longValue() != j) {
					throw new IllegalArgumentException(x + " " + j);
				}
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
            out.println(d[i]);
        }
    }

    private boolean mult(int i, int j, int c) {
        if (sign[i] == '*' && sign[j] == '*') {
            return relax(d, d[i] + "*" + d[j], c + MAX, '*');
        }
        return relax(d, "[" + d[i] + "]*[" + d[j] + "]", c + MAX, '*');
    }

    private boolean sub(int i, int j, int c) {
        if (sign[j] == '*') {
            return relax(d, d[i] + "-" + d[j], c + MAX, '-');
        }
        return relax(d, d[i] + "-[" + d[j] + "]", c + MAX, '-');
    }

    boolean relax(String []d, String s, int pos, char sign) {
        if (pos < 0 || pos >= d.length)
            return false;
        if (d[pos] == null || d[pos].length() > s.length()) {
            this.sign[pos] = sign;
        	if (pos - MAX == 142) {
        		dbg(sign);
        	}
            // if (pos - MAX == 0 || pos - MAX == 1) {
            //     dbg(pos - MAX, d[pos], s);
            // }
            d[pos] = s;
            return true;
        }
        return false;
    }

    public void run() throws IOException {
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
        new Main().run();
    }

    class Timer {

        long time;

        void start() {
            time = System.currentTimeMillis();
        }
        long time() {
            return System.currentTimeMillis() - time;
        }
        void print() {
            print("Time spent = ");
        } 

        void print(String message) {
            dbg(message, time());
        }

    }

    static boolean DEBUG = true;

    void dbg(Object ... objs) {
        if (!DEBUG) {
            return ;
        }
        for (Object o : objs) {
            String printLine;
            if (o.getClass().isArray()) {
                printLine = arrayToString(o);
            } else {
                printLine = o.toString();
            }
            System.err.print(printLine + " ");
        }
        System.err.println();
    }

    String arrayToString(Object o) {
        if (o instanceof long[]) 
            return Arrays.toString((long[]) o);
        if (o instanceof int[])
            return Arrays.toString((int[]) o);
        if (o instanceof short[])
            return Arrays.toString((short[]) o);
        if (o instanceof char[])
            return Arrays.toString((char[]) o);
        if (o instanceof byte[])
            return Arrays.toString((byte[]) o);
        if (o instanceof double[])
            return Arrays.toString((double[]) o);
        if (o instanceof float[])
            return Arrays.toString((float[]) o);
        if (o instanceof boolean[])
            return Arrays.toString((boolean[]) o);
        if (o instanceof Object[])
            return Arrays.deepToString((Object[]) o);
        throw new IllegalStateException();
    }


}

