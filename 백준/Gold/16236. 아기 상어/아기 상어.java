import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[][] arr;
    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int r = Integer.compare(o1[2], o2[2]); // 거리
                if (r == 0) r = Integer.compare(o1[0], o2[0]); // 높이
                if (r == 0) r = Integer.compare(o1[1], o2[1]); // 좌우
                return r;
            }
        });

        N = Integer.parseInt(br.readLine());
        v = new boolean[N][N];
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9){
                    arr[i][j] = 0;
                    pq.offer(new int[] {i, j, 0});
                    v[i][j] = true;
                }
            }
        }

        int distance = 0;
        int exp = 0;
        int size = 2;

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        while (!pq.isEmpty()){
            int[] now = pq.poll();
            int x = now[0];
            int y = now[1];
            int depth = now[2];

            if (arr[x][y] != 0 && arr[x][y] < size) // 물고기를 먹을 수 있다면
            {
                arr[x][y] = 0;
                exp++;
                if (exp == size) {
                    size++;
                    exp = 0;
                }
                pq.clear();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        v[i][j] = false;
                    }
                }
                distance += depth;
//                System.out.println(distance);
                pq.offer(new int[] {x, y, 0});
            }
            else { // 물고기를 먹을 수 없다면
                for (int i = 0; i < 4; i++) {
                    int tx = dx[i] + x;
                    int ty = dy[i] + y;
                    if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
                    if (v[tx][ty]) continue;
                    if (arr[tx][ty] <= size)
                    {
                        v[tx][ty] = true;
                        pq.offer(new int[]{tx, ty, depth + 1});
                    }
                }
            }
        }
        System.out.println(distance);
    }
}

/*

우선순위큐 거리짧은순 높이순 좌우순
 -> 물고기를 먹으면 큐 clear
 -> 안먹으면 사방탐색

 */