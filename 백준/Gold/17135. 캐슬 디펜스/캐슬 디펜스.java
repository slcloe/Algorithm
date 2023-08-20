import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N, M, D;
    static char[][] arr;
    static int[] defense = new int[3];
    static char[][] map;
    static int result = 0;


    static void comb(int cnt, int start){
        if (cnt == 3){
            int enemy = castleDefense();
            result = (result > enemy) ? result : enemy;
            return;
        }
        for (int i = start; i < M; i++) {
            defense[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }


    static int castleDefense(){
        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = arr[i].clone();
        }

        int enemy = 0;

        for (int i = 0; i < N; i++) {
            ArrayList<BFS> bfsResult = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                BFS attack = bfs(N - i, defense[j]);
                if (attack.depth != -1){
                    if (map[attack.x][attack.y] == '1') bfsResult.add(attack);
                }
            }
            for (int j = 0; j < bfsResult.size(); j++) {
                BFS tmp = bfsResult.get(j);
                if (tmp.depth != -1 && map[tmp.x][tmp.y] == '1'){
                    enemy++;
                    map[tmp.x][tmp.y] = '0';
                }
            }

//            System.out.println(enemy);
        }
        return enemy;
    }


    static class BFS{
        int x, y;
        int depth;

        BFS(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    static BFS bfs(int x, int y){ //(x,y)는 궁수의 자리
        int[] dx = {0, -1, 0};
        int[] dy = {-1, 0 ,1};

        ArrayDeque<BFS> queue = new ArrayDeque<>();

        queue.offer(new BFS(x, y, 0));

        while(!queue.isEmpty()){
            BFS node = queue.poll();

            for (int i = 0; i < 3; i++) {
                int tx = node.x + dx[i];
                int ty = node.y + dy[i];

                if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
                if (Math.abs(x - tx) + Math.abs(y - ty) != node.depth + 1) continue;
                if (map[tx][ty] == '0') {
                    if (node.depth + 1 == D) continue;
                    else queue.offer(new BFS(tx, ty, node.depth + 1));
                }
                else {
                    if (tx != x)
                        return new BFS(tx, ty, node.depth + 1);
                }
            }
        }

        return new BFS(-1, -1, -1);
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = st.nextToken().charAt(0);
            }
        }
        comb(0, 0);
        System.out.println(result);

    }

}