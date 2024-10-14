import java.io.*;
import java.util.*;

public class Main {

    static int T, N, M, K;
    static int[] arr;
    static int[] sum;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());


        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N];

            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            sum = new int[N + M];

            for (int j = 0; j < N + M - 1; j++) {
                sum[j + 1] = sum[j] + arr[j % N];
            }

            int cnt = 0;
            int size = N;
            if (N == M)
                size = 1;
            for (int j = 0; j < size; j++) {
                if (sum[j + M] - sum[j] < K) cnt++;
            }
            sb.append(cnt).append('\n');
        }

        System.out.print(sb.toString());
    }
}

/*
1
5 5 16
1 2 3 4 5

 */
