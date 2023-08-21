import java.util.*;
import java.io.*;
public class Main {
    static int R, C; // row and column
    static char[][] arr; // 도로저장 array
    static Pos M; // 시작점
    static Pos road; // 도로를 추가할 위치
    static boolean[][] v; // visited check 를 위한 배열
    static char roadIcon; // 추가할 road 모양

    static int[][] dx = { // 도로별 이동 가능 x방향
            {1, -1},
            {0, 0},
            {-1, 0, 1, 0},
            {1, 0},
            {-1, 0},
            {-1, 0},
            {1, 0}

    };

    static int[][] dy = { // 도로별 이동 가능 y방향
            {0, 0},
            {1, -1},
            {0, 1, 0, -1},
            {0, 1},
            {0, 1},
            {0, -1},
            {0, -1}
    };

    static class Pos{ // 위치를 저장할 클래스

        int x, y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    // road 를 따라 갈 수 있는 길을 탐색하는 함수
    static void dfs(int x, int y) {
        v[x][y] = true; // 방문 체크

        int idx = 0;
        switch(arr[x][y]) { // 어떤 모양의 길인지 저장
            case('|') :
                idx = 0; break;
            case('-') :
                idx = 1; break;
            case('+') :
                idx = 2; break;
            case('1') :
                idx = 3; break;
            case('2') :
                idx = 4; break;
            case('3') :
                idx = 5; break;
            case('4') :
                idx = 6; break;
        }

        for (int i = 0; i < dx[idx].length; i++) { // 해당 모양의 길에서 갈 수 있는 방향으로 탐색
            int tx = x + dx[idx][i]; // x position update
            int ty = y + dy[idx][i]; // y position update

            if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue; // range check
            if (v[tx][ty]) continue; // visited check
            if (arr[tx][ty] == 'M' || arr[tx][ty] == 'Z') continue;
            if (arr[tx][ty] != '.') // find road
                dfs(tx, ty);
            else // 뚫린길을 찾았을 때
            {
                searchBlock(tx, ty); // 연결할 수 있는 길의 모양을 찾아야함.
                return; // 함수 종료
            }

        }
    }

    // 뚫린 길의 모양을 찾는 함수
    static void searchBlock(int x, int y)
    {
        road = new Pos(x + 1, y + 1); // 우선 뚫린 길의 위치를 저장

        boolean[] check = new boolean[4]; // 4방향 중 어느 방향으로 갈 수 있는지 체크
        int cnt = 0;
        for (int i = 0; i < dx[2].length; i++) { // 4방탐색
            int tx = x + dx[2][i]; // x position update
            int ty = y + dy[2][i]; // y position update

            if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;// range check
            if (arr[tx][ty] != '.' && arr[tx][ty] != 'M'&& arr[tx][ty] != 'Z') {  // 갈 수 있을것 같은 길이 존재한다면
                /// 진짜 갈 수 있는 길인지 한번 더 체크해야함.
                int idx = 0;
                switch(arr[tx][ty]) { // 길의 idx 를 저장
                    case('|') :
                        idx = 0; break;
                    case('-') :
                        idx = 1; break;
                    case('+') :
                        idx = 2; break;
                    case('1') :
                        idx = 3; break;
                    case('2') :
                        idx = 4; break;
                    case('3') :
                        idx = 5; break;
                    case('4') :
                        idx = 6; break;
                }

                for (int j = 0; j < dx[idx].length; j++) { // 해당 길의 방향을 검사

                    if (dx[idx][j] == -dx[2][i] && dy[idx][j] == -dy[2][i]) // x, y 이동 가능 방향이 같다면 이동 가능한 위치라고 판단
                    {
                        cnt++; // 개수 체크
                        check[i] = true; // 방문가능 체크
                    }
                }
            }
        }
        if (cnt == 4 || cnt == 3) roadIcon = '+'; // 4방 탐색이 가능하다면
        else if (check[0] && check[2]) roadIcon = '|';
        else if (check[1] && check[3]) roadIcon = '-';
        else if (check[1] && check[2]) roadIcon = '1';
        else if (check[0] && check[1]) roadIcon = '2';
        else if (check[0] && check[3]) roadIcon = '3';
        else if (check[2] && check[3]) roadIcon = '4';

    }


    static void setStartPos() {

        for (int i = 0; i < dx[2].length; i++) { // 4방탐색
            int tx = M.x + dx[2][i];// x position update
            int ty = M.y + dy[2][i];// y position update

            if (tx < 0 || tx >= R || ty < 0 || ty >= C) continue;// range check
            if (arr[tx][ty] != '.' && arr[tx][ty] != 'M' && arr[tx][ty] != 'Z') {
                M.x = tx; // 시작위치x 갱신
                M.y = ty; // 시작위치y 갱신
                return;
            }
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(st.nextToken()); // row 입력
            C = Integer.parseInt(st.nextToken()); // column 입력

            arr = new char[R][C]; // map array 할당
            v = new boolean[R][C]; // visited array 할당

            // map 입력
            for (int i = 0; i < R; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < C; j++) {
                    arr[i][j] = tmp.charAt(j);
                    if (arr[i][j] == 'M') // 시작점이라면 위치 저장
                        M = new Pos(i, j);
                }
            }


            setStartPos();// start pos 를 갱신
            dfs(M.x, M.y);// dfs 를 사용해서 길찾기

            // 결과를 StringBuilder 에 저장
            sb.append(road.x).append(" ").append(road.y).append(" ")
                    .append(roadIcon);

        // 결과 출력
        System.out.println(sb.toString());
        br.close();
    }

}
