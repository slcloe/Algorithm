import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int result[] = new int[T];
        long padovan[];
        int n = Integer.MIN_VALUE;


        for (int tc = 0; tc < T; tc++) {
            result[tc] = Integer.parseInt(br.readLine());
            n = Math.max(n, result[tc]);
        }

        padovan = new long[11 + n];

        padovan[1] = padovan[2] = padovan[3] = 1L;
        padovan[4] = padovan[5] = 2L;
        for (int i = 6; i <= n; i++) {
            padovan[i] = padovan[i - 1] + padovan[i - 5];
        }

        for(int a : result)
            sb.append(padovan[a]).append("\n");
        System.out.println(sb.toString());
    }
}