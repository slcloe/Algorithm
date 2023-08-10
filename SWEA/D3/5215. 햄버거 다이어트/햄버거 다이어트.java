import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N, L, result = 0;
    static int[][] diet;

    static StringBuilder sb = new StringBuilder();

    static int[] b;

    static void comb(int cnt, int start, int I, int calory){
        if (calory > L) return ;
        if (cnt == I){
            int tmp = 0;
            for (int i = 0; i < I; i++) {
                tmp += diet[b[i]][0];
            }
            result = (result > tmp ) ? result : tmp;
            return ;
        }
        for (int i = start; i < N; i++) {
            b[cnt] = i;
            comb(cnt + 1, i + 1, I, calory + diet[i][1]);
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            diet = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                diet[i][0] = Integer.parseInt(st.nextToken());
                diet[i][1] = Integer.parseInt(st.nextToken());
            }
            b = new int[N];
            result = 0;
            for (int i = 0; i < N; i++) {
                comb(0, 0, i + 1, 0);
//                System.out.println(result);
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());

    }

}