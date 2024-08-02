import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[문제풀이]

1. 4개의 스택을 생성한다.
2. 배열을 순회하면서 각 스택에 넣을 수 있는지 판단한다.
    a. 스택에 넣기 위해서는 스택의 첫번째 요소보다 클 때만 가능하다.
    만약 작은 원소가 들어갈 경우 순열을 "청소할 때" 내림차순으로 나오게 된다.

 */

public class Main {

    static int N;
    static int[][][] dp;
    static int[][] arr;

    static boolean isRange(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < N) return true;
        return false;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N][N][3];
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 0 :가로, 1 :세로, 2: 대각선
        dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (arr[i][j] == 1) continue;

                if (isRange(i + 1, j + 1)
                        && arr[i + 1][j] == 0 && arr[i][j + 1] == 0 && arr[i + 1][j + 1] == 0) {
                    dp[i + 1][j + 1][2] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
                }

                if (isRange(i , j + 1) && arr[i][j + 1] == 0) {
                    dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][2];
                }

                if (isRange(i + 1, j) && arr[i + 1][j] == 0) {
                    dp[i + 1][j][1] += dp[i][j][1] + dp[i][j][2];
                }
            }

//            for(int[][] a : dp){
//                for(int[] ar: a) {
//                    System.out.println(Arrays.toString(ar));
//                }
//                System.out.println();
//            }
        }

        N--;
        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);


    }

}


