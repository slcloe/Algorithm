import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] vips = new int[M + 1];
        for (int i = 0; i < M; i++) {
            vips[i] = Integer.parseInt(br.readLine());
        }

        int seat = 1, vip = 0;
        int result = 1;
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            if (vips[vip] == i){
                dp[i] = dp[i - 1];
                seat = 1;
//                System.out.println(vip);
                if (vip == 0 || vip >= 1 && vips[vip - 1] != i - 1)
                    result *= dp[i - 1];
                else dp[i] = 1;
                vip++;
                continue;
            }
            if (seat == 1) dp[i] = 1;
            else if (seat == 2) dp[i] = 2;
            else dp[i] = dp[i - 1] + dp[i - 2];
            seat++;
        }

        if (M == 0 || vips[M - 1] != N) result *= dp[N];
//        System.out.println(Arrays.toString(dp));
        System.out.println(result);
    }
}