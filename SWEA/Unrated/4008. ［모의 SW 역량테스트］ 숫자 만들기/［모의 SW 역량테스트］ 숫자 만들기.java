import java.io.*;
import java.util.*;
public class Solution {

    static int T, N;
    static int max = -100_000_000;
    static int min = 100_000_000;
    static int[] module, num;
    static StringBuilder sb = new StringBuilder();

    static void comb(int cnt, int sum){

        if (cnt == N - 1){
//            System.out.println(sum);
            max = (max > sum) ? max : sum;
            min = (min < sum) ? min : sum;
            return ;
        }
        if (module[0] > 0) {
            module[0]-- ; comb(cnt + 1, sum + num[cnt + 1]); module[0]++ ;
        }
        if (module[1] > 0) {
            module[1]-- ; comb(cnt + 1, sum - num[cnt + 1]);module[1]++ ;
        }
        if (module[2] > 0) {
            module[2]-- ; comb(cnt + 1, sum * num[cnt + 1]);module[2]++ ;
        }
        if (module[3] > 0) {
            module[3]-- ; comb(cnt + 1, sum / num[cnt + 1]);module[3]++ ;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            num = new int[N];
            module = new int[4];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 4; i++) {
                module[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            max = -100_000_000;
            min = 100_000_000;
            comb(0, num[0]);
            sb.append("#").append(tc).append(" ").append(max - min).append("\n");
        }

        System.out.println(sb.toString());

    }

    
}