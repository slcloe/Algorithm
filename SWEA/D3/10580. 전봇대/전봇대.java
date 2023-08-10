import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N;

    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][2];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[i][0] = a;
                arr[i][1] = b;
            }

            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j && isContain(i, j))
                        result++;
                }
//                System.out.println(result);
            }
            sb.append("#").append(tc).append(" ").append(result / 2).append("\n");
        }
        System.out.println(sb.toString());
    }

    static boolean isContain(int x, int y){

        if (arr[x][0] < arr[x][1] && arr[y][0] < arr[y][1]){
            if (arr[x][0] < arr[y][0] && arr[x][1] < arr[y][1]) return false;
            if (arr[x][0] > arr[y][0] && arr[x][1] > arr[y][1]) return false;
        }

        if (arr[x][0] > arr[x][1] && arr[y][0] > arr[y][1]){
            if (arr[x][0] < arr[y][0] && arr[x][1] < arr[y][1]) return false;
            if (arr[x][0] > arr[y][0] && arr[x][1] > arr[y][1]) return false;
        }

        int x2 = (arr[x][0] < arr[x][1]) ? arr[x][1] : arr[x][0];
        int x1 = (arr[x][0] < arr[x][1]) ? arr[x][0] : arr[x][1];

        int y2 = (arr[y][0] < arr[y][1]) ? arr[y][1] : arr[y][0];
        int y1 = (arr[y][0] < arr[y][1]) ? arr[y][0] : arr[y][1];

        if (y1 < x1 && y2 < x1) return false;
        if (x2 < y1 && x2 < y2) return false;
        return true;
    }
}
/*
1
4
3 5
6 2
7 3
8 4

//3
 */