import java.util.*;
import java.io.*;


public class Solution {
    static int T, M, N;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    static int max;

    static int flies(int x, int y){
        int result = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                result += arr[i + x][j + y];
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T ; tc++){
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = 0;
            for (int i = 0; i < N - M + 1; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    int tmp = flies(i, j);
                    max = (max < tmp) ? tmp : max;
                }
            }

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
    }
}