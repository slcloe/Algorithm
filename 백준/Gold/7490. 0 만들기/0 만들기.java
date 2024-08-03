import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

 */

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    static void calResult(int n, int idx, int num, int sum, int op, String str) {
        if (n == idx) {
            sum += num * op;
            if (sum == 0) {
                sb.append(str).append('\n');
            }
            return;
        }

        calResult(n, idx + 1, (num * 10) + (idx + 1), sum, op, str + ' ' + (idx + 1));
        calResult(n, idx + 1, idx + 1, sum + (op * num), 1, str + '+' + (idx + 1));
        calResult(n, idx + 1, idx + 1, sum + (op * num), -1, str + '-' + (idx + 1));


    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; t++) {
            int n = Integer.parseInt(br.readLine());
            char[] op = new char[n];
            op[0] = '+';
            calResult(n, 1, 1, 0, 1, "1");

            sb.append('\n');
        }

        System.out.print(sb.toString());



    }

}


