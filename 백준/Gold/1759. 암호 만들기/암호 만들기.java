import java.util.*;
import java.io.*;

public class Main {

    static int L, C;
    static char[] arr;
    static char[] b;
    static StringBuilder sb = new StringBuilder();

    static void comb(int cnt, int start, int vowel, int consonant){
        if (cnt == L){
            if (vowel == 0) return;
            if (consonant < 2) return;
            for(char ch : b){
                sb.append(ch);
            }
            sb.append("\n");

            return ;
        }

        for (int i = start; i < C; i++) {
            b[cnt] = arr[i];
            if (b[cnt] == 'a' || b[cnt] == 'i' || b[cnt] == 'u' || b[cnt] == 'e' || b[cnt] == 'o')
                comb(cnt + 1, i + 1,  vowel + 1, consonant);
            else
                comb(cnt + 1, i + 1, vowel, consonant + 1);
        }
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        b = new char[L];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        comb(0, 0, 0, 0);

        System.out.print(sb.toString());

    }
}