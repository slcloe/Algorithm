import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean v[][][];

    static class Moon{
        int[] pos;
        int depth;
        int key;
        Moon(int x, int y, int depth, int key){
            pos = new int[2];
            pos[0] = x;
            pos[1] = y;
            this.depth = depth;
            this.key = key;
        }
    }


    static int bfs(int x, int y){
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int dx[] = {0, 0, -1 ,1};
        int dy[] = {1, -1, 0, 0};
//        long v[] = new long[N];
//        v[x] = 1 << y;

        queue.offer(new int[] {x, y, 0, 0}); // x, y, depth, keyStatus
        v[x][y][0] = true;
        while (!queue.isEmpty()){
            int[] cur =  queue.poll();
            int tx, ty;
            int curDepth = cur[2];
            int curKeyStatus = cur[3];

            for (int i = 0; i < 4; i++) {
                tx = dx[i] + cur[0];
                ty = dy[i] + cur[1];
                if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
                if (v[tx][ty][curKeyStatus]){
                    ;continue;
                } // 방문 체크
                if (map[tx][ty] == '#') continue; // 벽 체크
                if (map[tx][ty] == '1') return curDepth + 1; // depth 리턴
                // 기본 방문 처리
                v[tx][ty][curKeyStatus] = true;
                // 열쇠에 도달했을 때
                if ('a' <= map[tx][ty] && map[tx][ty] <= 'f'){
                    // 열쇠 추가 후 큐 add 해야함
                    int key = curKeyStatus | (1 << (map[tx][ty] - 'a'));
                    v[tx][ty][key] = true;
                    queue.offer(new int[] {tx, ty, curDepth + 1, key});
                    continue;
                }
                // 문에 도달 했을때
                if ('A' <= map[tx][ty] && map[tx][ty] <= 'F'){
                    // 키가 없는 경우 continue
                    if ((curKeyStatus & (1 << (map[tx][ty] - 'A'))) == 0) continue;
                    // 있는 경우 큐 add 해야함
                }
                // 그냥 빈칸에 도착했을 때

                // 큐 add 해야함.

                queue.offer(new int[] {tx, ty ,curDepth + 1, curKeyStatus});


//                if ('a' <= map[tx][ty] && map[tx][ty] <= 'f'){ // 열쇠에 도달했을때
//
//                    queue.offer(new int[]{tx, ty, cur[2] + 1, cur[3] | (1 << (map[tx][ty] - 'a'))});
//                    continue;
//                }
//
//                if ('A' <= map[tx][ty] && map[tx][ty] <= 'F'){ // 문에 도달했을때
//                    // 키가 없다면 continue
//                    if ((cur.key & (1 << (map[tx][ty] - 'A'))) == 0) continue;
//
//                    // 키가 있다면 ㄱㅊ
//                    // 키 상태 중복이라면 continue
//                    if (!map[tx][ty].keyValue.add(cur.key)) continue;
//                }
//                // 그냥 빈칸에 도착했을때
//                // 방문처리 후 input 함
//                v = new long[N];
//                for (int j = 0; j < N; j++) {
//                    v[j] = cur.v[j];
//                }
//                v[tx] = v[tx] | (1 << ty);
//                queue.offer(new Moon(tx, ty, v, cur.depth + 1, cur.key));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int x = 0, y = 0;
        v = new boolean[N][M][1 << 6 + 1];
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0'){
                    map[i][j] = '.';
                    x = i; y = j;
                }
            }
        }
//        System.out.println(1 << 6 + 1);
        System.out.println(bfs(x, y));
//        Runtime.getRuntime().gc();
//        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        System.out.print(usedMemory + " bytes");



        // 0 ~ 64
        // 111111

        // 000000 ~ 111111
    }
}

/*

3 6
###...
#0a.A1
###...

2 7
1F.f#.0
A...#.a

48 7
1FD....
BC....a
#.....#
#cd....
.BD....
AC.....
#.....#
f#...##
1##.###
##....a
#.....#
#......
..#b#..
...#...
#.....#
.......
#.....#
f#...##
1##.###
##....a
#.....#
#......
..#b#..
...#...
#.....#
.......
.BD....
AC.....
#.....#
f#...##
1##.###
##....a
#.....#
#......
..#b#..
...#...
#.....#
.......
#.....#
f#...##
1##.###
##....a
#.....#
#......
..#b#..
...#...
#.....#
......0
 */

/*

 */