import java.util.*;
import java.io.*;

public class Solution {

    static int N;
    static int T;
    static char[] arr;
    static StringBuilder sb = new StringBuilder();

    static void inOrder(int n){
        if (n > N) return;

        inOrder(n * 2);
        sb.append(arr[n]);
        //System.out.println(arr[n]);
        inOrder(n * 2 + 1);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = 10;

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append("#").append(tc).append(" ");
            arr = new char[N + 1];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                int idx = Integer.parseInt(st.nextToken());
                char ch = st.nextToken().charAt(0);

                arr[idx] = ch;
            }

            inOrder(1);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}