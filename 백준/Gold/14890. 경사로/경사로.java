import java.util.*;
import java.io.*;

public class Main {
    static int num;
    static int L;
    static int map[][];
    static int result = 0;

    static int abs(int a){
        return ((a > 0) ? a : -a);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        num = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[num][num];
        for (int i = 0; i < num; i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0;j < num; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < num; i++)
        {
            int check = 0;
            int sequence = 1;
            for (int j = 1; j < num; j++)
            {
                if (map[i][j] == map[i][j - 1])
                {
                    sequence++;
//                    if (check == 1)
//                    {
//                        if (sequence >= L) check = 0;
//                        else break;
//                    }
                }
                else
                {
//                    if (i == 0)
//                        System.out.println("abs " + abs(map[i][j-1]-map[i][j]) + " " + map[i][j-1] +" "+ map[i][j]);
                    if (abs(map[i][j-1]-map[i][j]) > 1)
                    {
                        check = 1;
                        break;
                    }
                    if (map[i][j - 1] < map[i][j])
                    {
                        if (sequence < L)
                        {
                            check = 1;
                            break;
                        }
                        sequence = 1;
                    }
                    else
                    {
                        check = 1;
                        sequence = 1;
                        for (j = j + 1;j < num && map[i][j] == map[i][j - 1]; j++)
                        {
                            if (sequence == L) break;
                            sequence++;
                        }
                        j--;
                        if (sequence == L) check = 0;
                        else break;
                        sequence = 0;
                    }
                }
            }
            if (check == 0)
            {
                result++;
                //System.out.println("1. " + i);
            }
        }
        //System.out.println(result);
        for(int i = 0; i < num; i++)
        {
            int check = 0;
            int sequence = 1;
            for (int j = 1; j < num; j++)
            {
                if (map[j][i] == map[j - 1][i])
                {
                    sequence++;
                }
                else
                {
                    if (abs(map[j - 1][i]-map[j][i]) > 1)
                    {
                        check = 1;
                        break;
                    }
                    if (map[j - 1][i] < map[j][i])
                    {
                        if (sequence < L)
                        {
                            check = 1;
                            break;
                        }
                        sequence = 1;
                    }
                    else
                    {
                        check = 1;
                        sequence = 1;
                        for (j = j + 1;j < num && map[j][i] == map[j - 1][i]; j++)
                        {
                            if (sequence == L) break;
                            sequence++;
                        }
                        j--;
                        if (sequence == L) check = 0;
                        else break;
                        sequence = 0;
                    }
                }
            }
            if (check == 0)
            {
                result++;
                //System.out.println(i);
            }
        }
        System.out.println(result);
    }
}