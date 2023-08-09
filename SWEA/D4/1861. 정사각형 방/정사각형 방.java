import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

public class Solution {
    static int T;
    static int N;
    static int[][] arr;
    static boolean[][] v;
    static StringBuilder sb = new StringBuilder();

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static boolean isRange(int x, int y){
        if (0 <= x && x < N && 0 <= y && y < N) return true;
        return false;
    }

    static int dfs(int x, int y){
        int result = 1;
        v[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (!isRange(tx, ty)) continue;
            if (v[tx][ty]) continue;
            if (abs(arr[tx][ty] - arr[x][y]) != 1) continue;
            result += dfs(tx, ty);
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T ; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            v = new boolean[N][N];
            int max = dfs(0 ,0);
            int pos = arr[0][0];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    v = new boolean[N][N];
                    int tmp = dfs(i ,j);
//                    System.out.println("tmp : " + tmp + " arr[i][j] : " + arr[i][j]);
                    if (max < tmp){
//                        System.out.println("MAX ::::: tmp : " + tmp + " arr[i][j] : " + arr[i][j]);
                        max = tmp;
                        pos = arr[i][j];
                    }
                    if (max == tmp){
                        pos = (pos < arr[i][j]) ? pos : arr[i][j];
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(pos).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
    }
}
/*
1
7
23 45 6 25 22 13 9
30 40 12 20 33 16 28
4 46 42 48 21 41 11
17 37 36 15 29 3 10
47 5 43 27 26 1 19
34 14 24 49 44 31 2
38 18 39 32 35 8 7

 */