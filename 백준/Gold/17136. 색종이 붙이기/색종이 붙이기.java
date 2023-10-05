import java.io.*;
import java.util.*;
public class Main {
    static int result = 0;
    static char[][] arr = new char[10][10];

    static int remains = 0;
    static int min = 26;


    static int[] nextPt(int x, int y){
        for (int i = x; i < 10; i++) {
            int j = (i == x)? y + 1 : 0;
            for (; j < 10; j++) {
                if (arr[i][j] == '1')
                    return new int[] {i, j};
            }
        }
        return null;
    }

    static void searchCase(int x, int y, int[] papers, int cnt){
//        if (cnt == remains){
//            System.out.println(5 * 5 - Arrays.stream(papers).sum());
//            min = Math.min(min, 5 * 5 - Arrays.stream(papers).sum());
//        }

        // paper size 1 ~ 5 까지 돌려보기
        for (int i = 1; i < 6; i++) {
            // paper 붙일수 있는지 여부 확인
            if (!checkPaper(x, y, i)) return; // 만약 붙일 수 없다면 더 큰 크기도 못 붙이기 때문에 pass함

            // 색종이 붙이기 & papers 배열 수정
            if (papers[i] == 0) continue; // 색종이 붙인 후 만약에 사용할 색종이가 없었다면 더 큰 색종이 붙일 수 있는지 확인.
            pastePaper(x, y, i, '*');
            papers[i]--;

            // 재귀
            int[] pt = nextPt(x, y);
            if (pt == null) {
                if (cnt + i * i == remains){
                    min = Math.min(min, 5 * 5 - Arrays.stream(papers).sum());
                }
                papers[i]++;
                pastePaper(x, y, i, '1');
                return;
            }
            searchCase(pt[0], pt[1], papers, cnt + i * i);

            // papers 배열 복귀
            papers[i]++;
            pastePaper(x, y, i, '1');

        }
    }

    static boolean checkPaper(int x, int y, int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i + x >= 10 || j + y >= 10) return false;
                if (arr[i + x][j + y] != '1') return false;
            }
        }
        return true;
    }
    static void pastePaper(int x, int y, int size, char ch){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i + x][j + y] = ch;
            }
        }
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[] papers = {0, 5, 5, 5, 5, 5};

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                arr[i][j] = st.nextToken().charAt(0);
                if (arr[i][j] == '1') remains++;
            }
        }

        if (0 == remains){
            min = 0;
        }

        int[] pt = nextPt(0, -1);
        if (pt != null)
            searchCase(pt[0], pt[1], papers, 0);
        if (min == 26) System.out.println(-1);
        else System.out.println(min);
    }
}
/*
0 0 0 0 0 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1


 */