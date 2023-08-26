import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
 
    Point() {}
}
 
public class Solution {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
    static ArrayDeque<Point> queue = new ArrayDeque<>();
    static int T;
    static StringBuilder sb = new StringBuilder();
    static int N;
 
 
    static void makeMine(int x, int y){
        for (int i = 0; i < 8; i++) {
            int tx = dx[i] + x;
            int ty = dy[i] + y;
            if (!isRange(tx, ty ,N)) continue;
            if (map[tx][ty] == -1) continue;
            map[tx][ty]++;
        }
        map[x][y] = -1;
    }
 
    static boolean isRange(int pt1, int pt2, int n){
        if (0 <= pt1 && pt1 < n && 0 <= pt2 && pt2 < n)
            return true;
        else return false;
    }
 
    static int getResult(){
        int result = 0;
 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 0 && !visited[i][j]){
                    bfs(i ,j);
                    result++;
                }
//                else if (map[i][j] > 0 && !visited[i][j]){
//                    result++;
//                    visited[i][j] = true;
//                }
            }
        }
        return result;
    }
 
    static Point isZero(int x, int y){
        if (map[x][y] == 0) return new Point(x, y);
 
        for (int i = 0; i < 8; i++) {
            int tx = dx[i] + x;
            int ty = dy[i] + y;
 
            if (!isRange(tx, ty ,N)) continue;
            if (visited[tx][ty]) continue;
            if (map[tx][ty] == 0) return new Point(tx, ty);
        }
        return new Point(x, y);
    }
 
 
    static void bfs(int x, int y){
        queue = new ArrayDeque<>();
 
        Point tmp = isZero(x, y);
        queue.offer(tmp);
        visited[tmp.x][tmp.y] = true;
 
        if (map[tmp.x][tmp.y] > 0) return;
 
 
        while (!queue.isEmpty()){
            Point pt = queue.poll();
 
            for (int i = 0; i < 8; i++) {
                int tx = dx[i] + pt.x;
                int ty = dy[i] + pt.y;
 
                if (!isRange(tx, ty , N)) continue;
                if (visited[tx][ty]) continue;
                if (map[tx][ty] == -1) continue;
 
                visited[tx][ty] = true;
                if (map[tx][ty] == 0)
                    queue.offer(new Point(tx, ty));
            }
        }
    }
 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T ; tc++) {
 
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < N; j++) {
                    if (tmp.charAt(j) == '*')
                        makeMine(i, j);
                }
            }
 
//            for(int[] arr1 : map) System.out.println(Arrays.toString(arr1));
//            int result = 0;
 
            //result = getResult();
 
            sb.append("#").append(tc).append(" ").append(getResult()).append("\n");
        }
        System.out.println(sb.toString());
    }
}