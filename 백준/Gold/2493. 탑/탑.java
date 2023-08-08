import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static ArrayDeque<stacks> stack = new ArrayDeque<>();
    static int[] solution;
    static StringBuilder sb = new StringBuilder();
    static class stacks{
        int pos;
        int arr;
        stacks(int pos, int arr){
            this.pos = pos;
            this.arr = arr;
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        solution = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = N - 1; i >= 0; i--) {
            if (!stack.isEmpty())
//                System.out.println(stack.peek().arr + " " + arr[i]);
            while (!stack.isEmpty() && stack.peek().arr <= arr[i]){
//                System.out.println("dd");
                solution[stack.peek().pos] = i + 1;
                stack.pop();
            }
            stack.push(new stacks(i, arr[i]));
        }

        while (stack.isEmpty()){
            solution[stack.peek().pos] = 0;
            stack.pop();
        }

        for (int i = 0; i < N; i++) {
            sb.append(solution[i]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
/*

6 9 5 7 4
0 0 2 2 4


6 1 8 5 9 2 4 3 7 10
0 1 0 3 0 5 5 7 6 0

 */