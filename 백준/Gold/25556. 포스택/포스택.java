import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static ArrayDeque<Integer>[] s = new ArrayDeque[4];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < 4; i++) {
            s[i] = new ArrayDeque<>();
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean result = true;
        for(int n : arr){
            boolean v = false;
            for (int i = 0; i < 4; i++) {
                if (s[i].isEmpty() || s[i].peek() < n) {
                    v = true;
                    s[i].offerFirst(n);
                    break;
                }
            }
            if (!v) {
                result =  false;
                break;
            }
        }
        if (result)
            System.out.print("YES");
        else
            System.out.print("NO");

    }

}


