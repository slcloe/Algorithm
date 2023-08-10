import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] arr;

    static void upSideDown(){
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M; j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[N - i - 1][j];
                arr[N - i - 1][j] = tmp;
            }
        }
    }

    static void leftRight(){
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < M / 2; j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[i][M - j - 1];
                arr[i][M - j - 1] = tmp;
            }
        }
    }

    static void rightAngle()
    {
        int tmp[][] = new int[M][N];

        for (int i=0;i<M;i++)
        {
            for (int j=0;j<N;j++)
                tmp[i][j] = arr[N - j - 1][i];

        }
        int temp = N;
        N = M;
        M = temp;
        arr = tmp;
    }

    static void rightAngle_()
    {
        int tmp[][] = new int[M][N];

        for (int i=0;i<M;i++)
        {
            for (int j=0;j<N;j++)
                tmp[i][j] = arr[j][M - i - 1];
        }
        int temp = N;
        N = M;
        M = temp;
        arr = tmp;
    }

    static void quarter5(){
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[N/2 + i][j];
                arr[N/2 + i][j] = arr[N/2 + i][M/2 + j];
                arr[N/2 + i][M/2 + j] = arr[i][M/2 + j];
                arr[i][M/2 + j] = tmp;
            }
        }
    }
    static void quarter6(){
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[i][M/2 + j];
                arr[i][M/2 + j] = arr[N/2 + i][M/2 + j];
                arr[N/2 + i][M/2 + j] = arr[N/2 + i][j];
                arr[N/2 + i][j] = tmp;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        int[] cmd = new int[R];
        for (int i = 0; i < R; i++) {
            cmd[i] = Integer.parseInt(st.nextToken());
        }

        for (int c : cmd){
            if (c == 1) upSideDown();
            else if (c == 2) leftRight();
            else if (c == 3) rightAngle();
            else if (c == 4) rightAngle_();
            else if (c == 5) quarter5();
            else if (c == 6) quarter6();
        }

        for(int[] arr1 : arr){
            for(int arr2 : arr1){
                sb.append(arr2).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }


}