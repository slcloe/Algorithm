import java.io.*;
import java.util.*;

public class Solution {
    static int[] pts = new int[4];

    static int getMinMvm(){
        int mvm = 0;
        int[] points = new int[2];

        points[0] = Math.abs(pts[0] - pts[2]);
        points[1] = Math.abs(pts[1] - pts[3]);

        while(Math.abs(points[0] - points[1]) > 1){
            int line = (points[0] + points[1]) / 2;

            mvm += line * 2;
            points[0] = Math.abs(points[0] - line);
            points[1] = Math.abs(points[1] - line);
        }

        mvm += points[0] + points[1];
        return mvm;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 4; i++) {
                pts[i] = Integer.parseInt(st.nextToken());
            }
            sb.append("#").append(tc).append(" ").append(getMinMvm()).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
/*

3
0 0 1 0
1 7 0 0
0 0 0 2

 */