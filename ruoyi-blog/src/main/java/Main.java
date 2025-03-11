import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String p = sc.next();
        int m = sc.nextInt();

        int temp = Integer.parseInt(p, n);
        String s = Integer.toString(temp, m);
        System.out.println(s.toUpperCase());
    }
}
