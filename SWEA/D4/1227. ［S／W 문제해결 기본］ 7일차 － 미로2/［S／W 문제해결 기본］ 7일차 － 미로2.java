import java.io.*;
import java.util.*;

public class Solution {

    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static boolean[][] visited;
    static int[][] map;
    static int start_x, start_y, end_x, end_y;
    static int result;
    static StringBuilder sb = new StringBuilder();

    static boolean isRange(int x, int y){
        if (0 <= x && x < 100 && 0 <= y && y < 100) return true;
        return false;
    }

    static int dfs(int x, int y){
        visited[x][y] = true;
        if (x == end_x && y == end_y){
            return 1;
        }
        int res = 0;
        for (int i = 0; i < 4; i++) {
            int tx = dx[i] + x;
            int ty = dy[i] + y;
            if (!isRange(tx, ty)) continue;
            if (visited[tx][ty]) continue;
            if (map[tx][ty] == 1) continue;
            if (dfs(tx, ty) == 1) res = 1;
        }
        return res;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int case1;

        for (int tc = 1; tc <= 10; tc++) {
            case1 = Integer.parseInt(br.readLine());
            map = new int[100][100];
            visited = new boolean[100][100];

            for (int i = 0; i < 100; i++) {
                String tmp  =br.readLine();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = tmp.charAt(j) - '0';
                    if (map[i][j] == 2)
                    {
                        start_x = i;
                        start_y = j;
                    }
                    if (map[i][j] == 3){
                        end_x = i;
                        end_y = j;
                    }
                }
            }
            result = dfs(start_x, start_y);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}