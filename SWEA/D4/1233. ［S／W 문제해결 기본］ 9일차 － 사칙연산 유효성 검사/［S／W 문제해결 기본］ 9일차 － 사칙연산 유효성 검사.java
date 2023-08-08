import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {


    static char[] tree;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static boolean middleOrder(int idx){
        boolean left = true;
        boolean right = true;
        boolean left1 = true;
        boolean right1 = true;

        if (idx * 2 <= N){
            left = middleOrder(idx * 2);
            left1 = isNumber(idx * 2);
        }
        else return true;
        if (idx * 2 + 1 <= N)
        {
            right = middleOrder(idx * 2 + 1);
            right1 = isNumber(idx * 2 + 1);
        }

        if (left == false || right == false) {
            return false;
        }

        if (left1 == false || right1 == false) {
            return false;
        }

        tree[idx] = '0';
        return true;
    }

    static boolean isNumber(int idx){
        if ('0' <= tree[idx] && tree[idx] <= '9') return true;
        return false;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result = 0;

        for (int tc = 1; tc <= 10 ; tc++) {
            N = Integer.parseInt(br.readLine());
            tree = new char[N + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int idx = Integer.parseInt(st.nextToken());
                char ch = st.nextToken().charAt(0);
                tree[idx] = ch;
            }
            result = (middleOrder(1)) ? 1: 0;

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());



    }
}