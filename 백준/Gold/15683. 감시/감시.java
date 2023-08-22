import java.util.*;
import java.io.*;
public class Main {


    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> cameraList = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    static int[][][] camera = {
        {{0}, {1}, {2}, {3}},
        {{0, 2}, {1, 3}},
        {{0, 1}, {1, 2}, {2, 3}, {0, 3}},
        {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {0, 3, 1}},
        {{0, 1, 2, 3}}
    };

    static void dfs(int camIdx, int[][] v){
        if (camIdx == cameraList.size()) {
            int over = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(v[i][j] == 0) over++;
                }
            }
            min = (min < over) ? min : over;
            return;
        }

        // 카메라 범위 계산
        int[] camPos = cameraList.get(camIdx);
        int camNum = map[camPos[0]][camPos[1]] - 1;

//        checkOversee(camPos[0], camPos[1], camNum, direct, v);
//
//
//        camPos = cameraList.get(camIdx);
//        camNum = map[camPos[0]][camPos[1]] - 1;
        for (int i = 0; i < camera[camNum].length; i++) {
            int[][] newV = new int[N][];
            for (int j = 0; j < N; j++) {
                newV[j] = v[j].clone();
            }
            checkOversee(camPos[0], camPos[1], camNum, i, newV);
            dfs(camIdx + 1, newV);
        }
    }

    static void checkOversee(int x, int y, int camNum, int direct, int[][] v){
        int result = 0;

        for (int i = 0; i < camera[camNum][direct].length; i++) {
            int tx = x + dx[camera[camNum][direct][i]];
            int ty = y + dy[camera[camNum][direct][i]];
            while (0 <= tx && tx < N && 0 <= ty && ty < M){
                if (v[tx][ty] == 6) break;
                if (v[tx][ty] == 0){
                    v[tx][ty] = -1;
                }
                tx += dx[camera[camNum][direct][i]];
                ty += dy[camera[camNum][direct][i]];
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        int blank = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6){
                    cameraList.add(new int[]{i, j});
                }
                if (map[i][j] == 0)
                    blank++;
            }
        }
        if (cameraList.size() > 0)
        {
            int[][] newV = new int[N][];
            for (int j = 0; j < N; j++) {
                newV[j] = map[j].clone();
            }
            dfs( 0, newV);
            System.out.println(min);
        }
        else
            System.out.println(blank);


    }
}
/*

4 6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 2 0 6 0
0 0 0 0 0 0

 */