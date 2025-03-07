import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int n,m;
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] in = new int[30];//入度
        int[] in2 = new int[30];//出度
        List<Integer>[] graph = new ArrayList[30];

        List<Integer>[] graph2 = new ArrayList[30];
        for (int i = 0; i < 30; i++) {
            graph[i] = new ArrayList<>();

        }
        int index = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            String[] split = sc.next().split("<");
            int son = split[0].charAt(0)- 'A' + 1;
            int father = split[1].charAt(0) - 'A' + 1;
            graph[father].add(son);
            in[son]++;
            set.add(son);
            set.add(father);
            index++;
            if(son == father){
                System.out.println("Inconsistency found after "+ index +" relations.");
                break;
            }

            for (int j = 0; j < 30; j++) {
                graph2[j] = new ArrayList<>(graph[j]);
                in2[j] = in[j];
            }

            int judge = judge(graph2, in2, set, index);
            if(judge >=1){
                break;
            }else if(index == m){//输入完了还没有结果就
                System.out.println("Sorted sequence cannot be determined.");
                break;
            }


        }



    }

    private static int judge(List<Integer>[] graph, int[] in,Set<Integer> set ,int index){
        Queue<Integer> q = new LinkedList<>();
        //查出入度为0的开始遍历
        for (Integer integer : set) {
            if(in[integer] == 0){
                q.add(integer);
            }
        }

        if(q.isEmpty() ){
            //如果是空的入度为0的数没有  报错
            System.out.println("Inconsistency found after "+ index +" relations.");
            return 1;
        }
        int num = 1;//因为原本的已经放入
        StringBuilder sb = new StringBuilder("");
        int omt = 0;
        //入度不为空的话就依次删除入度为0的点
        while (!q.isEmpty()){
            //弹出一个入度为0的点，然后遍历它的所有子节点，子节点的入度减一，如果入度减一后为0，则加入队列
            Integer poll = q.poll();
            char temp = (char) (poll - 1 + 'A');
            sb.append(temp);
            List<Integer> list = graph[poll];//有且只有一个入度为0的数
            int flag = 0;
            for (int j = 0; j < list.size(); j++) {
                //入度减一
                in[list.get(j)]--;
                if(in[list.get(j)] == 0){
                    //如果入度等于0就加入
                    q.add(list.get(j));
                    num++;
                    flag++;
                }
            }
            if(flag > 1){
                //构不成
                omt = 1;
            }


        }
        StringBuilder reverse = sb.reverse();
        //如果num==n说明全部遍历完了，没有错误
        if(num == n){
            if(omt == 1)return -1;
            //
            System.out.println("Sorted sequence determined after "+index+" relations: "+reverse+".");
            return 2;
        }
        //如果还有入度大于0的就报错
        for (int i = 0; i < 30; i++) {
            if(in[i] > 0){
                System.out.println("Inconsistency found after "+index+" relations.");
                return 2;
            }
        }


        return -1;
    }
}
