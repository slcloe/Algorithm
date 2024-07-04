import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*

 */


public class Main {

    static int N;
    static long B;
    static int[][] arr;

    static int[][] pow(int[][] arr, long b){
        if (b == 1L) return arr;

        int[][] res = pow(arr, b / 2);
        res = mul(res, res);

        if (b % 2 == 1L) {
            res = mul(res, arr);
        }

        return res;
    }

    static int[][] mul(int[][] arr1, int[][] arr2) {
        int res[][] = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    res[i][j] += arr1[i][k] * arr2[k][j];
                    res[i][j] %= 1000;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = pow(arr, B);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}