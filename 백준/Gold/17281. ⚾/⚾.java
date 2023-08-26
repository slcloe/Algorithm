import java.util.*;
import java.io.*;
public class Main {

    static int[][] arr;
    static int N;
    static int[] b = new int[9];
    static boolean[] v = new boolean[9];

    static int max = 0;

    static void perm(int cnt){
        if (cnt == 3) cnt++;
        if (cnt == 9) {
            int tmp = calBaseBall();
            max = (max < tmp) ? tmp : max;
            return ;
        }
        for (int i = 1; i < 9; i++) {
            if (!v[i]){
                b[cnt] = i;
                v[i] = true;
                perm(cnt + 1);
                v[i] = false;
            }
        }
    }

    static int calBaseBall(){
        int score = 0;
        int[] basement;
        int player = 0;

//        System.out.println(Arrays.toString(b));
        for (int i = 0; i < N; i++) {
            int out = 0;
            basement = new int[3];

            while(true){
                int ability = arr[i][b[player]];
                if (ability == 0) out++;
                else {
                    for (int j = 2; j >= 0; j--) {
                        if (j + ability >= 3) {
                            if (basement[j] == 1) {
                                score++; basement[j] = 0;
                            }
                        }
                        else {
                            basement[j + ability] = basement[j];
                            basement[j] = 0;
                        }
                    }
                    if (ability == 4) score++;
                    else basement[ability - 1] = 1;
                }

                player = (player + 1) % 9;
                if (out == 3) break;
            }
//            System.out.println(score);
        }
        return score;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][9];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        b[3] = 0;
        v[0] = true;
        perm(0);
        System.out.println(max);
    }
}