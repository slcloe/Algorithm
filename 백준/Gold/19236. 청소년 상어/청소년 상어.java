import java.util.*;
import java.io.*;

public class Main {


    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static ArrayList<int[]> fishInfo = new ArrayList<>();

    static int[][] arr = new int[4][4];
    static int[] sharkPos = {0, 0};
    static int max = 0;

    static void dfs(int[][] map, ArrayList<int[]> fishSet, int dinner, int depth){
//        System.out.println("depth : " + depth);
//        System.out.println(dinner);
//        System.out.println("before");
//        for(int[] arr1 : map){
//            System.out.println(Arrays.toString(arr1));
//        }
//        System.out.println();
//        for(int[] arr1 : fishSet){
//            System.out.println(Arrays.toString(arr1));
//        }
//        System.out.println();
        moveFish(map, fishSet);
//        System.out.println("after");
//        for(int[] arr1 : map){
//            System.out.println(Arrays.toString(arr1));
//        }
//        System.out.println();

        int x = fishSet.get(17)[1]; // shark position
        int y = fishSet.get(17)[2]; // shark position
        int d = fishSet.get(17)[3]; // shark direction

        int tx = x + dx[d];
        int ty = y + dy[d];
        boolean check = false;

        while (tx >= 0 && tx < 4 && ty >= 0 && ty < 4){
            if (map[tx][ty] == 0) {
                tx += dx[d];
                ty += dy[d];
                continue;
            }

            int[][] mapCpy = new int[4][];
            for (int i = 0; i < 4; i++) {
                mapCpy[i] = map[i].clone();
            }

            ArrayList<int[]> fishSetCpy = new ArrayList<>();
            for (int i = 0; i < fishSet.size(); i++) {
                fishSetCpy.add(fishSet.get(i).clone());
            }

            int fishIdx = mapCpy[tx][ty];
            int[] shark = fishSetCpy.get(17);
            mapCpy[tx][ty] = 17;
            mapCpy[shark[1]][shark[2]] = 0;
            shark[1] = tx;
            shark[2] = ty;
            shark[3] = fishSetCpy.get(fishIdx)[3];

            dfs(mapCpy, fishSetCpy, dinner + fishIdx,depth + 1);

            check = true;
            tx += dx[d];
            ty += dy[d];
        }

        if (!check) // 물고기 먹을 자리가 없다면 집으로 가야함
        {
//            System.out.println("return " + dinner);
            max = (max < dinner) ? dinner : max;
        }
    }

    static void moveFish(int[][] map, ArrayList<int[]> fishSet){
        for (int i = 1; i <= 16 ; i++) {
            int[] fish = fishSet.get(i);
            if (map[fish[1]][fish[2]] != i){
                continue;
            }
            for (int j = 0; j < 8; j++) {
                int d = (j + fish[3]) % 8;
                int tx = fish[1] + dx[d];
                int ty = fish[2] + dy[d];
                if (tx < 0 || tx >= 4 || ty < 0 || ty >= 4) continue;
                if (map[tx][ty] == 17) continue;

                int fishSwapIdx = map[tx][ty];

                int tmp = map[tx][ty];
                map[tx][ty] = map[fish[1]][fish[2]];
                map[fish[1]][fish[2]] = tmp;

                if (fishSwapIdx != 0) {
                    int[] fishSwap = fishSet.get(fishSwapIdx);
                    fishSwap[1] = fish[1];
                    fishSwap[2] = fish[2];
                }
                fish[1] = tx;
                fish[2] = ty;
                fish[3] = d;
                break;
            }
        }
    }


    public static void main(String[] args) throws Exception{
  //      System.setIn(new FileInputStream("src/SSA/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int fishNum = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                fishInfo.add(new int[]{fishNum, i,j,d});
                arr[i][j] = fishNum;
            }
        }
        fishInfo.add(new int[]{0,0,0,0});
        fishInfo.add(new int[]{17, 0,0,0}); // 상어
        fishInfo.sort((o1, o2)->Integer.compare(o1[0], o2[0]));

        int fishIdx = arr[0][0];
        arr[0][0] = 17;
        fishInfo.get(17)[1] = fishInfo.get(17)[2] = 0;
        fishInfo.get(17)[3] = fishInfo.get(fishIdx)[3];
        dfs(arr, fishInfo,  fishIdx, 0 );

        System.out.println(max);
    }

}
/*
7 6 2 3 15 6 9 8
3 1 1 8 14 7 10 1
6 1 13 6 4 3 11 4
16 1 8 7 5 2 12 2
 */