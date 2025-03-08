import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = nextInt();
        int size = nextInt();
        List<Integer>[] list = new ArrayList[200];
        for (int i = 0; i < 200; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= num; i++) {
            list[1].add(i);
        }
        int index = 1;
        for (int i = 0; i < size; i++) {
            int n = nextInt();
            List<Integer> arr = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                int station = nextInt();//车站停靠的站台
                arr.add(station);
            }
            int start = arr.get(0),end = arr.get(arr.size() - 1);
            List<Integer> integerList = list[index];
            int flag= -1;
            out:
            for (Integer integer : integerList) {
                if(integer >= start && integer <= end){
                    if(!arr.contains(integer)){
                        flag = 1;
                        break out;
                    }
                }
            }
            if(flag == 1){
                index++;
                list[index].addAll(arr);
            }

        }
        System.out.println(index);
    }

}
