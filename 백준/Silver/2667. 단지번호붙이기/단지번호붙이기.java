import java.util.*;
import java.io.*;

public class Main {


    static int N;
    static boolean[][] v;
    static char[][] map;

    static ArrayList<Integer> list = new ArrayList<>();
    static int dx[] = {0, 0, 1 ,-1};
    static int dy[] = {1, -1, 0, 0};

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
            if (map[tx][ty] == '0') continue;
            result += dfs(tx, ty);
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        v = new boolean[N][N];
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '1' && !v[i][j])
                    list.add(dfs(i, j));
            }
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for(int tmp : list){
            sb.append(tmp).append("\n");
        }
        System.out.println(sb.toString());

    }
}