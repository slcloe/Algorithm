import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static boolean[][] v;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        v = new boolean[100][100];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    v[a + j][b + k] = true;
                }
            }
        }

        int result = 0;

        for (boolean[] arr1 : v){
            for(boolean arr : arr1)
                if (arr) result++;
        }
        
        System.out.println(result);
    }
}
/*
  if (pt1.x + 10 <= pt2.x) return false;
        if (pt1.y <= pt2.y){
            if (pt2.y <= pt1.y + 10) return false;
        }
        else {
            if (pt1.y > pt2.y + 10) return false;
        }


 */