import java.io.*;
import java.util.*;

public class Solution {

    static int N, R;
    static final int mod = 1234567891;

    static long comb(int N, int R){
        if (R == 0) return 1;

        long nn = 1, rr = 1;
        for (int i = 1; i <= N; i++) {
            nn *= i;
            nn %= mod;
        }
        for (int i = 1; i <= R; i++) {
            rr *= i;
            rr %= mod;
        }
        for (int i = 1; i <= N - R; i++) {
            rr *= i;
            rr %= mod;
        }
        long rr2 = pow(rr, mod - 2);
        long result = nn * rr2;
        result %= mod;

        return result;
    }

    static long pow(long a, long b){
        if (b == 0) return 1;
        if (b % 2 == 1) return (pow(a, b - 1) * a) % mod;
        long half = pow(a, b / 2) % mod;
        return (half * half) % mod;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            R = Math.min(R, N - R);

            sb.append("#").append(tc).append(" ").append(comb(N, R)).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
/*
1
1000000 500000
 */