import java.util.*;
import java.io.*;

public class Main {

    static char[][] arr;
    static boolean[][] v;
    static int R, C;
    static int dx[] = {0 , 0, -1, 1, 1, 1, -1, -1};
    static int dy[] = {1, -1, 0, 0, 1, -1, 1, -1 };

    static void dfs(int x, int y){
        v[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;
            if (v[tx][ty]) continue;
            if (arr[tx][ty] == '0') continue;
            dfs(tx, ty);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        while(R != 0 && C != 0){
            arr = new char[R][C];
            v = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < C; j++) {
                    arr[i][j] = st.nextToken().charAt(0);
                }
            }
            int continent = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (!v[i][j] && arr[i][j] == '1'){
                        continent++;
                        dfs(i ,j);
                    }
                }
            }
            sb.append(continent).append("\n");
            st = new StringTokenizer(br.readLine(), " ");
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
//            System.out.println("size " + R + " " + C);
        }

        System.out.println(sb.toString());

    }


}