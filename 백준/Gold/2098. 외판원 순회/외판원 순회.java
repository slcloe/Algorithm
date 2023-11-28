import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] arr;
    static int[][] dp;
    static int INF = 16_000_000;
    static int go(int now, int visit){
        if(visit == (1 << N) - 1){ // 모든 곳을 방문했을 때

            // cycle 존재할 경우
            if(arr[now][0] != 0) return arr[now][0];
            else { // cycle 존재하지 않을 경우
                dp[now][visit] = INF;
                return INF;
            }
        }

        if(dp[now][visit] != -1){ //방문한 적이 있을 때
            return dp[now][visit];
        }

        for(int i =0; i<N; i++){
            int next = visit |(1 << i); // visited[i] = true;

            if(arr[now][i] != 0 && (visit & (1 << i)) == 0){ //  now 와 연결된 간선일 경우 || visited[i] = false
                if (dp[now][visit] == -1) // 처음 방문한 정점일 경우
                    dp[now][visit] = go(i,next) + arr[now][i];
                else // 최저비용 = 최저치판단(최저비용, i번으로 이동 + 이동비용);
                    dp[now][visit] = Math.min(dp[now][visit], go(i,next) + arr[now][i]);
            }
        }

        if (dp[now][visit] == -1) return INF; // 방문할 수 있는 정점이 없었다면
        return dp[now][visit];
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][1 << N];

        // 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1); // dp 배열 초기화
        }
        System.out.println(go(0,1));

    }
}

/*

- 시작점에 따라 최소경로가 바뀌지는 않는다.

case1 과 case2 는 같은 경로이다.

case1 : 0 -> 1 -> 2 -> 3 -> 4 -> 0
case2 : 2 -> 3 -> 4 -> 0 -> 1 -> 2

- dp 에 어떤 것을 저장해야하는가?

case1 : 0 -> 1 -> 2 -> 4 -> | 3 -> 0 |
case2 : 0 -> 2 -> 1 -> 4 -> | 3 -> 0 |

중복되는 경로에 대해 저장해야한다.
만약 dfs 로 이 경우를 계산한다면 4 -> 3 -> 0 에 대한 계산을 중복으로 실행해야한다.

 */