import java.util.*;
import java.io.*;

public class Solution {

    static class Bracket{
        char ch;
        public Bracket(char ch){
            this.ch = ch;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static ArrayDeque<Bracket> bc;

    static int bracketValid(String line){

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '(' || ch == '[' ||ch == '{' ||ch == '<') bc.push(new Bracket(ch));
            else{
                char ch1 = bc.pop().ch;
                if (ch1 == '(' && ch == ')') continue;
                else if (ch1 == '[' && ch == ']') continue;
                else if (ch1 == '{' && ch == '}') continue;
                else if (ch1 == '<' && ch == '>') continue;
                else return 0;

            }
        }
        if (bc.isEmpty()) return 1;
        else return 0;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result;

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();
            bc = new ArrayDeque<>();
            sb.append("#").append(tc).append(" ").append(bracketValid(br.readLine())).append("\n");
        }
        System.out.println(sb.toString());
    }
}
