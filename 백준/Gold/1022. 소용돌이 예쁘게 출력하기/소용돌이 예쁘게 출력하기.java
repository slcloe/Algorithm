import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int r1, c1, r2, c2;


    static int calNum(int r, int c){
        int level = Math.max(Math.abs(r), Math.abs(c));

        if (level == 0) return 1;

        int[] arr = new int[5];
        int levels = (2 * level - 1) * (2 * level - 1);
        int section = 2 * level;
        arr[0] = levels + 1;
        for (int i = 1; i <= 4; i++) {
            arr[i] = levels + section * i;
        }

        if (Math.abs(r) == Math.abs(c)){
            if (r > 0 && c > 0){ //++ 4
                return arr[4];
            }else if (r > 0 && c < 0){//+- 3
                return arr[3];
            }else if (r < 0 && c > 0){ // -+ 1
                return arr[1];
            }else{ //-- 2
                return arr[2];
            }
        }

//        System.out.println(level);
        if (Math.abs(r) == level && r < 0) { // 1
            return arr[1] + level - c;
        } else if (Math.abs(r) == level && r > 0){ // 2
            return arr[3] + level + c;
        } else if (Math.abs(c) == level && c < 0){
            return arr[3] - level + r;
        } else if (Math.abs(c) == level && c > 0){
            return arr[1] - level - r;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        int[][] arr = new int[r2 - r1 + 1][c2 - c1 + 1];
        int max = -1;

        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                arr[i - r1][j - c1] = calNum(i, j);
                max = Math.max(max, arr[i - r1][j - c1]);
            }
        }
        int cnt = 0, i = 1;

        while (i <= max){
            cnt++;
            i *= 10;
        }

        for(int[] a : arr){
            for(int b : a){
                int length = Integer.toString(b).length();
                for (int j = 0; j < cnt - length; j++) {
                    sb.append(' ');
                }
                sb.append(b).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
//        System.out.println(Integer.toString(1234).length());


    }


}