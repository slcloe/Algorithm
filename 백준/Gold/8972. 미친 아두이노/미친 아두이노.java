import java.io.*;
import java.util.*;

public class Main {


    static char[][] map;
    static int N, M;
//    static ArrayList<int[]> crazy;
    static HashSet<int[]> mops;
    static int[] pos;
    static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};


//    class Pos{
//        int x, y;
//
//        Pos(int x, int y){
//
//        }
//    }

    static String getSolution(int[] cmds) {

        int n = 0;
        for (int i = 0; i < cmds.length; i++) {
            if (cmds[i] != 5) n++;

            // 1 종수 이동
            pos[0] = dx[--cmds[i]] + pos[0];
            pos[1] = dy[cmds[i]] + pos[1];


            // 2 충돌 검사
            if (map[pos[0]][pos[1]] == 'R')
                return "kraj " + (n);

            // 3 아두이노 이동 및 충돌 검사
            map = new char[N][M];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    map[j][k] = '.';
                }
            }

            if (!moveCrazy()) {
                return "kraj " + (i + 1);
            }
            // 4 아두이노 겹치면 삭제


        }

        return "";
    }

    static boolean moveCrazy() {
        HashMap<String, Integer> mopPos = new HashMap<>();

        for(int[] mop : mops) {
            int tx = mop[0], ty = mop[1];

            if (pos[0] < mop[0]) {
                tx--;
            } else if (pos[0] > mop[0]) {
                tx++;
            }

            if (pos[1] < mop[1]) {
                ty--;
            } else if (pos[1] > mop[1]) {
                ty++;
            }

            if (tx == pos[0] && ty == pos[1]) return false; // 충돌

            String key = tx + " " + ty;

            if (mopPos.containsKey(key)) {
//                System.out.println("dd");
                mopPos.put(key, mopPos.get(key) + 1);
            } else {
//                System.out.println("ddd " + key);
//                System.out.println("before Size : " + mopPos.keySet().size());
                mopPos.put(key , 1);
//                System.out.println("after Size : " + mopPos.keySet().size());
            }
        }

//        mopPos.put("1 1", 1);
//        System.out.println("Size : " + mopPos.keySet().size());
//        System.out.println("mopPos Size : " + mopPos.keySet().size());

        mops = new HashSet<>();
        for (String key : mopPos.keySet()) {
            if (mopPos.get(key) == 1) {
                int[] tmpPos = Arrays.stream(key.split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                map[tmpPos[0]][tmpPos[1]] = 'R';
                mops.add(new int[] {tmpPos[0], tmpPos[1]});
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        pos = new int[2];
//        crazy = new ArrayList<>();
        mops = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'I'){
                    pos[0] = i;
                    pos[1] = j;
                }
                if (map[i][j] == 'R') {
                    mops.add(new int[] {i, j});
                }
            }
        }

        String cmd = br.readLine();
        int[] cmds = Arrays.stream(cmd.split(""))
                .mapToInt(Integer::parseInt).toArray();


        String sol = getSolution(cmds);

        if (sol.equals("")) {
            map[pos[0]][pos[1]] = 'I';

            StringBuilder sb = new StringBuilder();
            for(char[] arrs : map) {
                for(char ch : arrs) {
                    sb.append(ch);
                }
                sb.append('\n');
            }
            System.out.println(sb.toString());
        } else {
            System.out.println(sol);
        }

    }
}


/*
4 5
.....
..I..
..R..
.....
5

4 5
I....
.....
.....
.R.R.
65


 */
