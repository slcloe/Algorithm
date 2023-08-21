import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static ArrayList<Integer>[] incident;
    static boolean[] v;
    static boolean[] set;
    static int min = -1;

    static void subSet(int cnt){
        if (cnt == N){
            // link 확인
            v = new boolean[N];
            int set1 = 0, set2 = 0;
            for (int i = 0; i < N; i++) {
                if (set[i] && set1 == 0){
                    set1 = dfs(i, true);
                }
                if (!set[i] && set2 == 0){
                    set2 = dfs(i, false);
                }
                if (set1 != 0 && set2 != 0) break;
            }

            // 최소값 갱신
            if (set1 == N || set2 == N) return;
            if (set1 + set2 == N){
                set1 = set2 = 0;
                for (int i = 0; i < N; i++) {
                    if (set[i]){
                        set1 += arr[i];
                    }
                    else{
                        set2 += arr[i];
                    }
                }
                if (min == -1) min = Math.abs(set1 - set2);
                min = (min < Math.abs(set1 - set2)) ? min : Math.abs(set1 - set2);
            }
            return ;
        }
        set[cnt] = true;
        subSet(cnt + 1);
        set[cnt] = false;
        subSet(cnt + 1);
    }

    static int dfs(int n, boolean flag){
        v[n] = true;
        int result = 1;
        for (int i = 0; i < N; i++) {
            if (set[i] == flag && incident[n].indexOf(i) >= 0 && !v[i])
            {
                result += dfs(i, flag);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        arr = new int[N];
        v = new boolean[N];
        set = new boolean[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        incident = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());

            incident[i] = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                incident[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }
        subSet(0);
        System.out.println(min);

    }
}
/*
6
2 2 2 2 2 2
1 3
1 4
1 1
1 2
1 6
1 5
 */