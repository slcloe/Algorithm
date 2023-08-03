import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int[] team;
    static int[] team1;
    static int[] team2;
    static int min;

    static void perm(int n, int M, int i){
//        System.out.println(n + " " + M);
        if (n == M)
        {
            int idx = 0;
            int exp1 = calExp(team1);
            team2 = new int[N - M];
            for (int j = 0; j < N; j++) {
                if (!visited[j]){
                    team2[idx++] = j + 1;
                }
            }
            int exp2 = calExp(team2);
            if (min == -1) min = abs(exp1 - exp2);
            min = (min < abs(exp1 - exp2)) ? min : abs(exp1 - exp2);
//            System.out.println(Arrays.toString(team1));
//            System.out.println(Arrays.toString(team2));
//            System.out.println();
            return ;
        }

        for (; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            team1[n] = i + 1;
            perm(n + 1, M, i);
            visited[i] = false;
        }
    }

    static int calExp(int[] player){
        int result = 0;
        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < player.length; j++) {
                result += arr[player[i] - 1][player[j] - 1];
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = -1;
        for (int i = 1; i <= N / 2; i++) {
            team1 = new int[i];
            perm(0, i, 0);
            if (min == 0) break;
        }
        System.out.println(min);
    }
}