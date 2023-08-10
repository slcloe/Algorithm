import java.util.*;
import java.io.*;

import static java.lang.Math.abs;

public class Solution {

    static int T;
    static int N;
    static int[][] arr ;
    static boolean[] food;
    static StringBuilder sb = new StringBuilder();
    static int[] plate1, plate2;
    static int[] b = new int[2];
    static int minRes = -1;

    static void comb(int cnt, int start){
        if (cnt == N / 2){
//            System.out.println(Arrays.toString(food));
            plate1 = new int[N / 2 + 1];
            plate2 = new int[N / 2 + 1];
            int idx1 = 0, idx2 = 0;
            for (int i = 0; i < N; i++) {
                if (food[i]) plate1[idx1++] = i;
                else plate2[idx2++] = i;
            }
//            System.out.println("plate1" + Arrays.toString(plate1));
//            System.out.println("plate2" + Arrays.toString(plate2));
            comb2(0, 0, plate1);
            comb2(0, 0, plate2);
//            System.out.println(plate1[N/2] - plate2[N/2]);

            int tmp = abs(plate1[N/2] - plate2[N/2]);
            if (minRes == -1) minRes = tmp;
            minRes = (minRes < tmp) ? minRes : tmp;
            return;
        }
        for (int i = start; i < N ; i++) {
            food[i] = true;
            comb(cnt + 1, i + 1);
            food[i] = false;

        }
    }



    static void comb2(int cnt, int start, int[] plate){
        if (cnt == 2){
//            System.out.println(Arrays.toString(b));
            plate[N / 2] += (arr[b[0]][b[1]] + arr[b[1]][b[0]]);
            return;
        }
        for (int i = start; i < plate.length -1; i++) {
            b[cnt] = plate[i];
            comb2(cnt + 1, i + 1, plate);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T ; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            food = new boolean[N];
            minRes = -1;
            comb(0, 0);

            sb.append("#").append(tc).append(" ").append(minRes).append("\n");
        }
        System.out.println(sb.toString());


    }
}