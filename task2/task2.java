import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("Unknown args");
        }
        final File file1 = new File(args[0]);
        double x = 0;
        double y = 0;
        double r = 0;
        try (final Scanner input1 = new Scanner(file1)) {
            x = input1.nextDouble();
            y = input1.nextDouble();
            r = Math.pow(input1.nextDouble(), 2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File1 is not exist");
        }
        final File file2 = new File(args[1]);
        try (final Scanner input2 = new Scanner(file2)) {
            while (input2.hasNextDouble()) {
                final double xPoint = input2.nextDouble();
                final double yPoint = input2.nextDouble();
                final double distance = Math.pow(xPoint-x, 2) + Math.pow(yPoint-y, 2);
                if (distance < r) {
                    System.out.println(1);
                } else if (distance == r) {
                    System.out.println(0);
                } else {
                    System.out.println(2);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File2 is not exist");
        }
    }
}
