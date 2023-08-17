import java.io.*;
import java.util.*;
public class Solution {
    static char[][] map;
    static int T;
    static int H, W, N;
    static int side;
    static int x, y;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    static void runCmd(char ch){
        if (ch == 'U') {
            side = 0;
            if (isRange(x - 1, y)){
                x = x - 1;
                y = y;
            }

        }else if (ch == 'R'){
            side = 1;
            if (isRange(x, y + 1)){
                x = x;
                y = y + 1;
            }
        }else if (ch == 'D'){
            side = 2;
            if (isRange(x + 1, y)){
                x = x + 1;
                y = y;
            }
        }else if (ch == 'L'){
            side = 3;
            if (isRange(x, y - 1)){
                x = x;
                y = y - 1;
            }
        }else if (ch == 'S'){
            fire();
        }
    }

    static boolean isRange(int x, int y){
        if (x < 0 || x >= H || y < 0 || y >= W) return false;
        if (map[x][y] == '.') return true;
        else return false;
    }

    static void fire(){
//        int i = 2;
        int tx = dx[side] + x;
        int ty = dy[side] + y;
        while (tx >= 0 && tx < H && ty >= 0 && ty < W){
            if (map[tx][ty] == '*'){
                map[tx][ty] = '.';
                return;
            }
            if (map[tx][ty] == '#'){
                return;
            }
            tx += dx[side];
            ty += dy[side];
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        String tmp;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                tmp = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = tmp.charAt(j);
                    if (map[i][j] == '^'){
                        x = i; y = j; side = 0; map[i][j] = '.';
                    }else if (map[i][j] == '>'){
                        x = i; y = j; side = 1; map[i][j] = '.';
                    }else if (map[i][j] == 'v'){
                        x = i; y = j; side = 2; map[i][j] = '.';
                    }else if (map[i][j] == '<'){
                        x = i; y = j; side = 3; map[i][j] = '.';
                    }
                }
            }

            N = Integer.parseInt(br.readLine());
            tmp = br.readLine();
            for (int i = 0; i < N; i++) {
                char cmd = tmp.charAt(i);
                runCmd(cmd);
            }

            if (side == 0){
                map[x][y] = '^';
            }else if (side == 1){
                map[x][y] = '>';
            }else if (side == 2){
                map[x][y] = 'v';
            }else if (side == 3){
                map[x][y] = '<';
            }

            sb.append("#").append(tc).append(" ");
            for(char[] map1 : map){
                for(char map2 : map1)
                    sb.append(map2);
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}