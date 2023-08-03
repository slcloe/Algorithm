import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[] idx = new int[4];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            arr[0][i] += arr[0][i - 1];
            arr[i][0] += arr[i - 1][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                arr[i][j] += arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1];
            }
        }

        for (int tc = 0; tc < M; tc++){
            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i < 4; i++) {
                idx[i] = Integer.parseInt(st.nextToken()) - 1;
            }
            int sum = arr[idx[2]][idx[3]];
            if (idx[0] - 1 >= 0)
                sum -= arr[idx[0] - 1][idx[3]];
            if (idx[1] - 1 >= 0)
                sum -= arr[idx[2]][idx[1] - 1];
            if (idx[0] - 1 >= 0 && idx[1] - 1 >= 0)
                sum += arr[idx[0] - 1][idx[1] - 1];

            sb.append(sum).append("\n");
        }
        System.out.println(sb.toString());
    }
}