import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] arr;
    static int[][][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        dp = new int[N][N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = st.nextToken().charAt(0);
            }
        }

        for (int i = 1; i < N && arr[0][i] == '0'; i++) {
            dp[0][i][0] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (arr[i][j] == '1') continue;
                if (arr[i - 1][j] == '0' && arr[i][j - 1] == '0'){
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
                dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
            }
        }

        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
        br.close();
    }
}