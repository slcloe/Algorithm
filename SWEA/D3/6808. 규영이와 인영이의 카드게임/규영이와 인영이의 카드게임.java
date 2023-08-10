import java.io.*;
import java.util.*;

public class Solution {

    static int T;
    static boolean[] card;
    static int[] card1, card2;
    static int lose, win;
    static StringBuilder sb = new StringBuilder();

    static int[] b = new int[9];
    static boolean[] v;


    static void getResult(){
        int p1 = 0, p2 = 0;

        for (int i = 0; i < 9; i++) {
            if (card1[i] > b[i]) p1 += (card1[i] + b[i]);
            else p2 += (card1[i] + b[i]);
        }
        if (p1 > p2) lose++;
        else if (p1 < p2) win++;
    }

    static void perm(int cnt){
        if (cnt == 9){
            getResult();
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (v[i]) continue;
            v[i] = true;
            b[cnt] = card2[i];
            perm(cnt + 1);
            v[i] = false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            card = new boolean[18];
            card1 = new int[9];
            card2 = new int[9];
            v = new boolean[9];

            st = new StringTokenizer(br.readLine(), " ");
            int idx1 = 0, idx2 = 0;
            for (int i = 0; i < 9; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                card[tmp - 1] = true;
                card1[idx1++] = tmp;
            }

            for (int i = 0; i < 18; i++) {
                if (!card[i]) card2[idx2++] = i + 1;
            }
            lose = win = 0;
            perm(0);
            sb.append("#").append(tc).append(" ").append(lose).append(" ").append(win).append("\n");

        }

        System.out.println(sb.toString());

    }

}