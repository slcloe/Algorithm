import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static char[][] arr;
    static int section1, section2;
    static boolean[][] v;



    static void dfs(int x, int y, boolean flag){
        v[x][y] = true;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
            if (v[tx][ty]) continue;
            if (arr[tx][ty] == arr[x][y])
                dfs(tx, ty, flag);
            if (flag){
                if ((arr[tx][ty] =='R' &&  arr[x][y] == 'G')||(arr[tx][ty] =='G' &&  arr[x][y] == 'R'))
                    dfs(tx, ty, flag);
            }
        }
    }

    static void calSection(){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v[i][j]){
                    section1++;
                    dfs(i, j, false);
                }
            }
        }
        v = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v[i][j]){
                    section2++;
                    dfs(i, j, true);
                }
            }
        }
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        v = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = tmp.charAt(j);
            }
        }

        calSection();
        System.out.println(section1 + " " + section2);


        br.close();
    }
}