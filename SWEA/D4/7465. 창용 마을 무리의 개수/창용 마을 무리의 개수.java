import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int MAX_VERTEX = 101;
    static int[][] map = new int[MAX_VERTEX][MAX_VERTEX];
    static boolean[] visit = new boolean[MAX_VERTEX];

    static void DFS(int n, int v) {
        int result = 0;

        visit[v] = true;
        for (int i = 1; i <= n; i++) {
            if (map[v][i] == 1 && !visit[i])
            {
                DFS(n, i);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            map = new int[MAX_VERTEX][MAX_VERTEX];
            visit = new boolean[MAX_VERTEX];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[a][b] = map[b][a] = 1;
            }

            int result = 0;
            for (int i= 1; i <= n; i++)
            {
                if (!visit[i])
                {
                    result++;
                    DFS(n, i);
                }
            }
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}