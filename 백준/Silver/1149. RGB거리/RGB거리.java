import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][3];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = (dp[i - 1][1] < dp[i - 1][2]) ? arr[i][0] + dp[i - 1][1] : arr[i][0] + dp[i - 1][2];
            dp[i][1] = (dp[i - 1][0] < dp[i - 1][2]) ? arr[i][1] + dp[i - 1][0] : arr[i][1] + dp[i - 1][2];
            dp[i][2] = (dp[i - 1][0] < dp[i - 1][1]) ? arr[i][2] + dp[i - 1][0] : arr[i][2] + dp[i - 1][1];
        }

        int min = Math.min(dp[N - 1][0] , Math.min(dp[N - 1][1], dp[N - 1][2]));

        System.out.println(min);
    }

}