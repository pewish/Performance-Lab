import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main input");
            return;
        }

        final File fileNums = new File(args[0]);
        try (final Scanner input = new Scanner(fileNums)) {
            int[] nums = new int[100];
            int count = 0;
            while (input.hasNextInt()) {
                nums[count++] = input.nextInt();
            }

            int minMoves = minMoves(nums, count);
            System.out.println(minMoves);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + args[0]);
        }
    }

    public static int minMoves(int[] nums, int count) {
        Arrays.sort(nums, 0, count);
        int median = nums[count / 2];
        int moves = 0;
        for (int i = 0; i < count; i++) {
            moves += Math.abs(nums[i] - median);
        }
        return moves;
    }
}
