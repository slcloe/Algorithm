import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int cmd;
    static ArrayList<String> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();

            st = new StringTokenizer(br.readLine(), " ");
            // 결과물은 10개만 출력하기 때문에 10개만 list에 추가
            for (int i = 0; i < 10; i++) {
                list.add(st.nextToken());
            }
            cmd = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            String insert;
            for (int i = 0; i < cmd; i++) {
                insert = st.nextToken();
                int pos = Integer.parseInt(st.nextToken());
                int posN = Integer.parseInt(st.nextToken());
                for (int j = 0; j < posN; j++) {
                    insert = st.nextToken();
                    // index 가 10 이상인 수는 결과값에 포함 되지 않기 때문에 연산 수행 X
                    if (pos + j < 10)
                        list.add(pos + j, insert);
                }
            }
            sb.append("#").append(tc);
            for (int i = 0; i < 10; i++) {
                sb.append(" ").append(list.get(i));
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}