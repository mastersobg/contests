package main;



import com.ivangorbachev.io.Reader;
import java.io.PrintWriter;

public class G {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        String s = in.readLine();
        int cnt = 0;
        int idx = s.length() - 1;
        while (idx >= 0 && s.charAt(idx) == '!') {
            --idx;
            cnt++;
        }
        if (cnt == 0)
            out.println("Pfff");
        else {
            out.print("W");
            for (int i = 0; i < cnt; i++) {
                out.print('o');
            }
            out.println('w');
        }
    }
}
