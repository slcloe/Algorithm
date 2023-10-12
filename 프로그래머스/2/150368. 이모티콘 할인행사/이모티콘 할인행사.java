import java.io.*;
import java.util.*;

class Solution {
    int[] b;
    int userNum, maxPrice;
    void perm(int cnt, int n, int[][] users, int[] emoticons){
        if (cnt == n){
            //cal
            cal(users, emoticons);
            return ;
        }
        for (int i = 1; i < 5; i++) {
            b[cnt] = i * 10;
            perm(cnt + 1, n, users, emoticons);
        }
    }

     void cal(int[][] users, int[] emoticons){
        int user = 0, price = 0;
        for (int i = 0; i < users.length; i++) {
            int totalP = 0;
            for (int j = 0; j < emoticons.length; j++) {
                if (users[i][0] > b[j]) continue;
                int p = emoticons[j] * (100 - b[j]) / 100;
                totalP += p;
            }
            if (totalP >= users[i][1]) user++;
            else price += (int) totalP;
        }
        if (user > userNum){
            userNum = user;
            maxPrice = price;
        }
        else if (user == userNum){
            maxPrice = Math.max(maxPrice, price);
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        b = new int[emoticons.length];

        perm(0, emoticons.length, users, emoticons);

        answer[0] = userNum;
        answer[1] = maxPrice;
        return answer;
    }
}