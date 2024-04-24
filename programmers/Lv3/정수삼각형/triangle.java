package 정수삼각형;

public class triangle {
    public static void main(String[] args){
        solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
    }
    // 동적계획법 해설 참고
    // 학습
    public static int solution(int[][] triangle) {
        for(int i = triangle.length-1; i >0; i--){ // 층마다
            dynamic(triangle, i);
        }
        System.out.println(triangle[0][0]);
        return triangle[0][0];
    }

    public static void dynamic(int [][] triangle, int high){
        for( int i = 0; i < triangle[high].length-1; i++ ){ // 양 옆 두수 비교 후 큰값을 상위층에 적용.
            triangle[high-1][i] += Math.max(triangle[high][i], triangle[high][i+1]);
        }
    }
}