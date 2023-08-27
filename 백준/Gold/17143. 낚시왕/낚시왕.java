import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Shark{
        int r, c, s, d, z, idx;
        boolean alive;
        public Shark(int r, int c, int s, int d, int z, int idx) {
            this.r = r; this.c = c; this.s = s; this.d = d; this.z = z; this.alive = true; this.idx = idx;
        }
    }
    static ArrayList<Shark> sharkList = new ArrayList<>();

    static int result;

    static void fishing(int idx){
        for (int i = 0; i < R; i++) {
            if (arr[i][idx] != 0){
                result += sharkList.get(arr[i][idx]).z;
                sharkList.get(arr[i][idx]).alive = false;
                arr[i][idx] = 0;
                break;
            }
        }
    }

    static void moveShark(){
        int[][] arrCpy = new int[R][C];

        for (int i = 1; i < sharkList.size(); i++) {
            Shark shark = sharkList.get(i);
            if (!shark.alive) continue;
            int move = shark.s;

//            for (int[] arr1 : arrCpy){
//                System.out.println(Arrays.toString(arr1));
//            }
//            System.out.println();
//            System.out.println(move);
            while (move-- > 0){
                shark.r += dx[shark.d];
                shark.c += dy[shark.d];
//                System.out.println(shark.r + " " + shark.c);
                if (shark.r < 0 || shark.r >= R || shark.c < 0 || shark.c >= C){
                    if (shark.d == 0) shark.d = 1;
                    else if (shark.d == 1) shark.d = 0;
                    else if (shark.d == 2) shark.d = 3;
                    else if (shark.d == 3) shark.d = 2;
                    move += 2;
                }
            }

            if (arrCpy[shark.r][shark.c] != 0){
                Shark compete = sharkList.get(arrCpy[shark.r][shark.c]);
                if (shark.z < compete.z){
                    shark.alive = false;
                }
                else{
                    arrCpy[shark.r][shark.c] = shark.idx;
                    compete.alive = false;
                }
            }
            else arrCpy[shark.r][shark.c] = shark.idx;

        }
        arr = arrCpy;
    }

//    static void collisionCheck(int[][] arrCpy, )

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        sharkList.add(new Shark(0,0,0,0,0,0));
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            sharkList.add(new Shark(r, c, s, d, z, i));
            arr[r][c] = i;
        }

        for (int i = 0; i < C; i++) {
            fishing(i);
            moveShark();
        }
        System.out.println(result);
    }

}