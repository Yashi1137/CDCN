import java.util.*;

public class LeftRecursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter non-terminal: ");
        char A = sc.next().charAt(0);

        System.out.print("Enter productions (use | between alternatives): ");
        String input = sc.next().replace(" ", "");

        String[] productions = input.split("\\|");

        List<String> alpha = new ArrayList<>();
        List<String> beta = new ArrayList<>();

        for (String p : productions) {
            if (p.charAt(0) == A) {
                alpha.add(p.substring(1));
            } else {
                beta.add(p);
            }
        }

        if (alpha.isEmpty()) {
            System.out.println("No left recursion.");
        } else if (beta.isEmpty()) {
            System.out.println("Cannot apply the standard transformation: no non-left-recursive alternative.");
        } else {
            System.out.println(A + " -> " + String.join("", beta) + A + "'");

            System.out.print(A + "' -> ");
            List<String> result = new ArrayList<>();

            for (String a : alpha) {
                result.add(a + A + "'");
            }

            result.add("#");
            System.out.println(String.join(" | ", result));
        }

        sc.close();
    }
} {
    
}
