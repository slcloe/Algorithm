import java.util.*;
import java.io.*;

public class Solution {
    static int T;
    static int N;
    static int[][] arr;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    static ArrayList<int[]> maxiNosList ;

    static int maxCore, max;

    static void dfs(int nodeIdx, int nodeCnt, int coreCnt){
        if (maxCore < nodeCnt){
            max = coreCnt;
            maxCore = nodeCnt;
        } else if (maxCore == nodeCnt){
            max = (max > coreCnt) ? coreCnt : max;
        }
        if (nodeIdx == maxiNosList.size()) return;

        for (int i = 0; i < 4; i++) {
            int check = checkLinked(nodeIdx, i);
            if (check == -1) continue;
            dfs(nodeIdx + 1, nodeCnt + 1, coreCnt + check);
            returnBack(nodeIdx, i);
        }
        dfs(nodeIdx + 1, nodeCnt, coreCnt);
    }

    static int checkLinked(int nodeIdx, int direct){
        int result = 0;

        int x = maxiNosList.get(nodeIdx)[0] + dx[direct];
        int y = maxiNosList.get(nodeIdx)[1] + dy[direct];
        while (0 <= x && x < N && 0 <= y && y < N){
            if (arr[x][y] != 0) return -1;
            x += dx[direct];
            y += dy[direct];
        }
        x = maxiNosList.get(nodeIdx)[0] + dx[direct];
        y = maxiNosList.get(nodeIdx)[1] + dy[direct];

        while (0 <= x && x < N && 0 <= y && y < N){
            arr[x][y] = 2;
            x += dx[direct];
            y += dy[direct];
            result++;
        }

        return result;
    }

    static void returnBack(int nodeIdx, int direct){
        int x = maxiNosList.get(nodeIdx)[0] + dx[direct];
        int y = maxiNosList.get(nodeIdx)[1] + dy[direct];
        while (0 <= x && x < N && 0 <= y && y < N){
            arr[x][y] = 0;
            x += dx[direct];
            y += dy[direct];
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());


        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            maxiNosList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue;
                    if (arr[i][j] == 1) maxiNosList.add(new int[]{i, j});
                }
            }

            max = maxCore = 0;
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }

        System.out.println(sb.toString());
    }
}
/*
1
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0


 */