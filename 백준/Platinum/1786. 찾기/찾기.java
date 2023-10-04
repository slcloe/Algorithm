import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] txt = br.readLine().toCharArray();
        char[] ptn = br.readLine().toCharArray();

        int tlen = txt.length, plen = ptn.length;

        int[] pi = new int[plen];
        for (int i = 1, j = 0; i < plen; i++) {
            while (j > 0 && ptn[i] != ptn[j]) j = pi[j - 1];

            if (ptn[i] == ptn[j]) pi[i] = ++j;
            else pi[i] = 0;
        }

        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < tlen; i++) {
            while (j > 0 && txt[i] != ptn[j]) j = pi[j - 1];

            if (txt[i] == ptn[j]){
                if (j == plen - 1) {
                    cnt++;
                    list.add(i - j + 1);
                    j = pi[j];
                }else{
                    j++;
                }
            }
        }
        System.out.println(cnt);
        for(int elem : list){
            System.out.print(elem + " ");
        }


    }
}