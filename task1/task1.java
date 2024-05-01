import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CircularArrayPath {
    public static List<Integer> findCircularArrayPath(int n, int m) {
        List<Integer> path = new ArrayList<>();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        int currentIndex = 0;
        while (path.size() < n) {
            path.add(nums[currentIndex]);
            currentIndex = (currentIndex + m - 1) % n;
            if (currentIndex == 0) {
                break;
            }
        }
        return path;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.close();

        List<Integer> path = findCircularArrayPath(n, m);
        System.out.println(path);
    }
}
