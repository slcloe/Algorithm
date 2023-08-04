import java.io.*;
import java.util.*;

import static java.lang.Math.sqrt;

public class Main {
    static int N;
    static int[] a = {1,3,5,7,9};
    static StringBuilder sb = new StringBuilder();

    static void pperm(int cnt, int number){
        if (cnt == N){
            sb.append(number);
            sb.append("\n");
        }
        for (int i = 0; i < 5; i++) {
            int tmp = number * 10 + a[i];
            if (isPrime(tmp))
                pperm(cnt + 1, tmp);
        }
    }

    static boolean isPrime(int n){
        if (n == 2) return true;
        else if (n % 2 == 0) return false;
        for (int i = 3; i <= sqrt(n); i+=2) {
            if (n % i  == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());

        pperm(1, 2);
        for (int i = 1; i < 4; i++) {
            pperm(1, a[i]);
        }

        System.out.println(sb.toString());

    }
}