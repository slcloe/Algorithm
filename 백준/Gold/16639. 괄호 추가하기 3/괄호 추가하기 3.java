import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static Integer num[];
    static Character oper[];
    static int[] b;
    static boolean[] v;
    static int N;
    static int max = Integer.MIN_VALUE;

    static void comb(int cnt){
        if (cnt == N / 2){
//            System.out.println(Arrays.toString(b));
            max = Math.max(max, cal());
            return;
        }

        for (int i = 0; i < N / 2; i++) {
            if (v[i]) continue;

            b[cnt] = i;
            v[i] = true;
            comb(cnt + 1);
            v[i] = false;
        }

    }

    static int cal(){
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(num));
        ArrayList<Character> opers = new ArrayList<>(Arrays.asList(oper));
        int[] bCopy = new int[N/2];
        bCopy = Arrays.copyOf(b, N/2);

        for (int i = 0; i < N/2; i++) {
            calIndex(bCopy[i], nums, opers);
            for (int j = i + 1; j < N/2; j++) {
                if (bCopy[i] < bCopy[j]) bCopy[j]--;
            }
        }

        return nums.get(0);
    }

    static void calIndex(int i, ArrayList<Integer> nums, ArrayList<Character> opers){
        int tmp = 0;
        int t1 = nums.remove(i);
        int t2 = nums.remove(i);
        char operator = opers.remove(i);

        if (operator == '+') tmp = t1 + t2;
        else if (operator == '-') tmp = t1 - t2;
        else if (operator == '*') tmp = t1 * t2;
        nums.add(i, tmp);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        v = new boolean[N / 2];
        b = new int[N / 2];
        num = new Integer[N / 2 + 1];
        oper  = new Character[N / 2];

        int j = 0, k = 0;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) num[j++] = str.charAt(i) - '0';
            else oper[k++] = str.charAt(i);
        }

        comb(0);
        System.out.println(max);
    }
}