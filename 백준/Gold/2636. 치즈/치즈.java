import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] arr;
    static int before;
    static int after;
    static int[] dx = {0, 0, 1 ,-1};
    static int[] dy = {1, -1, 0, 0};

    static int countDay(){
        int day = 0;

        int total = calCheeze();
        while (total != 0){
            before = deleteCheeze();
            total -= before;
            day++;
        }

        return day;
    }
    static int deleteCheeze(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean v[][] = new boolean[N][M];
        int cnt = 0;

        v[0][0] = true;
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int tx = dx[i] + cur[0];
                int ty = dy[i] + cur[1];

                if (tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
                if (v[tx][ty]) continue;

                v[tx][ty] = true;
                if (arr[tx][ty] == '1') {
                    arr[tx][ty] = '0';
                    cnt++;
                }
                else{
                    queue.offer(new int[] {tx, ty});
                }
            }
        }
        return cnt;
    }

    static int calCheeze(){
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == '1')
                    cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = st.nextToken().charAt(0);
            }
        }

        System.out.println(countDay());
        System.out.println(before);
    }
}