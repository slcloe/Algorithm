import java.io.*;
import java.util.*;

public class Solution {

    static int T;
    static int N;
    static StringBuilder sb = new StringBuilder();
    static int[][] pos;
    static int[][] weight;
    static int result;
    static boolean[] v;
    static int min;
    static int cnt = 0;

    static void dfs(int vertex, int depth, int distance){
        if (depth == N){
//            System.out.println(distance);
            distance += weight[vertex][N + 1];
            min = (min < distance) ? min : distance;
            cnt++;
            return;
        }
        for (int i = 1; i <= N ; i++) {
            if (v[i]) continue;
            v[i] = true;
            dfs(i, depth + 1, distance + weight[vertex][i]);
            v[i] = false;
        }
    }
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/input_d5_1247.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            pos = new int[N + 2][2];
            weight = new int[N + 2][N + 2];
            v = new boolean[N + 2];
            pos[0][0] = Integer.parseInt(st.nextToken()); pos[0][1] = Integer.parseInt(st.nextToken());
            pos[N + 1][0] = Integer.parseInt(st.nextToken()); pos[N + 1][1] = Integer.parseInt(st.nextToken());

            for (int i = 1 ; i <= N; i++) {
                pos[i][0] = Integer.parseInt(st.nextToken()); pos[i][1] = Integer.parseInt(st.nextToken());
            }


            for (int i = 0; i < N + 1; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    int distance = Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]);
                    weight[i][j] = weight[j][i] = distance;
                }
            }

//            for(int[] arr1 : weight){
//                System.out.println(Arrays.toString(arr1));
//            }

            min = Integer.MAX_VALUE;
            dfs(0, 0, 0);


            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
    }
}