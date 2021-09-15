import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// swea3124 최소스패닝트리
// 가중치 100만씩, 간선 갯수 20만개를 더할 수 있기 때문에 가중치를 전부 더하는 값을 long 타입으로 두어야함

public class swea3124 {
    // 부모 원소를 관리(트리처럼 사용
    static int[] parents;
    static int V,E;
    static Edge[] edgeList; 

    // 가중치 순으로 sort 하기 위함
    static class Edge implements Comparable<Edge>{      
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            super();
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);      // 간선의 부호가 다를 때
        }
    }

    private static void make(int n){
        parents = new int[n+1];
        // 모든 원소에서 자신을 대표자로 만듬
        // 1부터 시작
        for(int i = 1; i <= n; i++){
            parents[i] = i;
        }
    }

    // a가 속한 집합의 대표자 찾기
    private static int find(int a){
        if(a == parents[a]) return a;   // 자신이 대표자면 자신 return
        return parents[a] = find(parents[a]);   // 자신이 속한 집합의 대표자를 자신의 부모로 : path compression
    }

    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        // 둘의 부모가 같다면 이미 같은 집합이니 합치지 않는다.
        if(aRoot == bRoot) return false;
        else {
            parents[bRoot] = aRoot;
            return true;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            sb.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            edgeList = new Edge[E];      

            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(a, b, c);
            }

            Arrays.sort(edgeList);
            make(V);  
            
            int cnt = 0;
            long result = 0;

            for(Edge edge: edgeList){
                if(union(edge.start, edge.end)){
                    result += edge.weight;
                    if(++cnt == V-1) break;
                }
            }
            
            sb.append(result);
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
        br.close();
    }
}
