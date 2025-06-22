import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<Integer> studentScores = new ArrayList<>();

        System.out.println("---- Student Grade Tracker ----");
        System.out.print("Enter the number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            sc.nextLine(); // consume leftover newline
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = sc.nextLine();
            System.out.print("Enter score of " + name + ": ");
            int score = sc.nextInt();

            studentNames.add(name);
            studentScores.add(score);
        }

        // Calculate statistics
        int total = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
        for (int score : studentScores) {
            total += score;
            if (score > highest) highest = score;
            if (score < lowest) lowest = score;
        }

        double average = (double) total / n;

        // Display report
        System.out.println("\n---- Summary Report ----");
        System.out.println("Total Students: " + n);
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);
        System.out.println("\nIndividual Scores:");

        for (int i = 0; i < n; i++) {
            System.out.println(studentNames.get(i) + " : " + studentScores.get(i));
        }

        sc.close();
    }
}
