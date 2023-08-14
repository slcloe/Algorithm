import java.util.*;
import java.io.*;

public class Main {

    static int R, C, T;
    static int[][] arr;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] dustPos = new int[2][2];
    static int[][] arr1;

    static int afterOneSec(){
        int result = 2;
        for (int i = 0; i < T; i++) {
            diffusion();
            clearRoom();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C;  j++) {
                result += arr[i][j];
            }
        }
        return result;
    }

    static void diffusion(){
        arr1 = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (arr[i][j] == -1) arr1[i][j] = -1;
                else if (arr[i][j] != 0){
                    int cnt = 0;
                    int spread = arr[i][j] / 5;
                    for (int k = 0; k < 4; k++) {
                        int tx = dx[k] + i;
                        int ty = dy[k] + j;
                        if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;
                        if (arr[tx][ty] == -1) continue;
                        cnt++;
                        arr1[tx][ty] += spread;
                    }
                    arr1[i][j] +=  arr[i][j] - spread * cnt;
                }
            }
        }

        arr = arr1;
//        for(int[] arr2 : arr){
//            System.out.println(Arrays.toString(arr2));
//        }
//        System.out.println();
    }

    static void clearRoom(){
        arr1 = new int[R][C];
        for (int i = 0; i < R; i++) {
            arr1[i] = arr[i].clone();
        }
        int posX = dustPos[0][0];
        int posY = dustPos[0][1] + 1;
        arr1[posX][posY] = 0;
        posY++;
        while (posY < C){
            arr1[posX][posY] = arr[posX][posY - 1];
            posY++;
        }
        posY--;
        posX--;
        while (posX >= 0){
            arr1[posX][posY] = arr[posX + 1][posY];
            posX--;
        }
        posX++;
        posY--;
        while (posY >= 0){
            arr1[posX][posY] = arr[posX][posY + 1];
            posY--;
        }
        posY++;
        posX++;
        while (arr[posX][posY] != -1) {
            arr1[posX][posY] = arr[posX - 1][posY];
            posX++;
        }

        posX = dustPos[1][0];
        posY = dustPos[1][1] + 1;
        arr1[posX][posY] = 0;

        posY++;
        while (posY < C){
            arr1[posX][posY] = arr[posX][posY - 1];
            posY++;
        }
        posY--;
        posX++;
        while (posX < R) {
            arr1[posX][posY] = arr[posX - 1][posY];
            posX++;
        }
        posX--;
        posY--;
        while (posY >= 0){
            arr1[posX][posY] = arr[posX][posY + 1];
            posY--;
        }
        posY++;
        posX--;
        while (arr[posX][posY] != -1){
            arr1[posX][posY] = arr[posX + 1][posY];
            posX--;
        }

        arr = arr1;
//        for(int[] arr2 : arr){
//            System.out.println(Arrays.toString(arr2));
//        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        int dustPosIdx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1){
                    dustPos[dustPosIdx][0] = i;
                    dustPos[dustPosIdx++][1] = j;
                }
            }
        }
        System.out.println(afterOneSec());




    }
}