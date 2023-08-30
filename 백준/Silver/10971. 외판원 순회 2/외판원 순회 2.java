import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] arr;
    static int[][] dp;
    static int INF = 16 * 1_000_000;
    static int go(int now, int visit){
//        System.out.println(now + "  " + visit);
//        for(int[] arr1 : dp){
//            System.out.println(Arrays.toString(arr1));
//        }
//        System.out.println();

        if(visit ==(1<<N)-1){ //visit = 11111111 즉, 모든 곳을 방문했을 때에 상황

            if(arr[now][0]!=0) return arr[now][0];
                // 현재 now에 있고 0번을 다녀왔을 때 비용이 0이 아니라면
            else return INF; //예외처리
        }

        if(dp[now][visit]!=INF){ //방문한 적이 있을 때!
            return dp[now][visit];
        }

        for(int i =0; i<N; i++){
            int next = visit |(1<<i); //i번으로 이동한 것까지 추가!

            if(arr[now][i] !=0 && (visit & (1<<i)) ==0){ //길이 없거나 방문한 경우가 아닐때
                dp[now][visit] = Math.min(dp[now][visit], go(i,next) + arr[now][i]);
            }// 최저비용 = 최저치판단(최저비용, i번으로 이동 + 이동비용);
        }
        return dp[now][visit];
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], INF);
        }

        System.out.println(go(0,1));

    }
}