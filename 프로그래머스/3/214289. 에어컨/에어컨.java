import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        
        t1 += 10;
        t2 += 10;
        temperature += 10;
        
        int N = onboard.length;
        int[][] dp = new int[N + 1][51];
        int low = Math.min(t1, temperature);
        int high = Math.max(t2, temperature);
        
        for (int i = 0 ;i <= N; i++) {
            Arrays.fill(dp[i], 1_000_000);
        }
        // Arrays.fill(dp[0], 0);
        dp[0][temperature] = 0;
        
        for(int i = 0; i < N; i++) {
            int start = 0;
            int end = 0;
            
            // i 시점에서 가능한 온도의 범위
            if (onboard[i] == 1){ // 승객 탑승
                start = t1;
                end = t2;
            } else { // 승객 미탑승
                start = low;
                end = high;
            }
            
            for(int j = start ; j <= end ; j++) {
                // System.out.println(dp[i + 1][j] + " " + (dp[i][j + 1] + a));
                if (j + 1 <= high) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j + 1] + a);
                }
                if (j - 1 >= low) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j - 1] + a);
                }
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + b);
                if (j+1 > temperature && j + 1 <= high) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
                if (j-1 < temperature && j - 1 >= low) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
                if (j == temperature){
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < 51;i++) {
            result = Math.min(result, dp[N][i]);
        }
        return result;
        // return Arrays.stream(dp[onboard.length]).min().getAsInt();
    }
}
