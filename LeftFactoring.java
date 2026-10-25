import java.util.*;

public class LeftFactoring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter non-terminal: ");
        char A = sc.next().charAt(0);

        System.out.print("Enter first production: ");
        String p1 = sc.next();

        System.out.print("Enter second production: ");
        String p2 = sc.next();

        int i = 0;

        while (i < p1.length() &&
               i < p2.length() &&
               p1.charAt(i) == p2.charAt(i)) {
            i++;
        }

        if (i == 0) {
            System.out.println("No common prefix. Left factoring is not required.");
        } else {
            String prefix = p1.substring(0, i);
            String suffix1 = p1.substring(i);
            String suffix2 = p2.substring(i);

            System.out.println(A + " -> " + prefix + A + "'");
            System.out.println(A + "' -> " + suffix1 + " | " + suffix2);
        }

        sc.close();
    }
}
