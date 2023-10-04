import java.io.*;
import java.util.*;
public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int arr[][] = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int seq = 0;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                seq = 1;
                int j;
                for (j = 1; j < N; j++) {
                    if (Math.abs(arr[i][j - 1] - arr[i][j]) > 1) break;
                    // 증가하는 경우
                    if (arr[i][j - 1] < arr[i][j]) {
                        if (seq >= L) seq = 1;
                        else break;
                    }
                    // 감소하는 경우
                    else if (arr[i][j - 1] > arr[i][j]) {
                        seq = 1;
                        while (j + 1 < N && arr[i][j + 1] == arr[i][j]) {
                            seq++;
                            j++;
                        }
                        if (seq >= L) seq -= L;
                        else break;
                    }
                    // 같은 경우
                    else seq++;
                }
                if (j == N) cnt++;
            }

            for (int i = 0; i < N; i++) {
                seq = 1;
                int j;
                for (j = 1; j < N; j++) {
                    if (Math.abs(arr[j - 1][i] - arr[j][i]) > 1) break;
                    // 증가하는 경우
                    if (arr[j - 1][i] < arr[j][i]) {
                        if (seq >= L) seq = 1;
                        else break;
                    }
                    // 감소하는 경우
                    else if (arr[j - 1][i] > arr[j][i]) {
                        seq = 1;
                        while (j + 1 < N && arr[j + 1][i] == arr[j][i]) {
                            seq++;
                            j++;
                        }
                        if (seq >= L) seq -= L;
                        else break;
                    }
                    // 같은 경우
                    else seq++;
                }
                if (j == N) cnt++;
            }
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb.toString());
    }
}