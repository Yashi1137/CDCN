import java.util.*;

public class FirstFollow {

    static Map<Character, List<String>> g = new LinkedHashMap<>();
    static Map<Character, Set<Character>> first = new LinkedHashMap<>();
    static Map<Character, Set<Character>> follow = new LinkedHashMap<>();

    static boolean isNT(char c) {
        return g.containsKey(c);
    }

    // FIRST of a string
    static Set<Character> firstOf(String s) {
        Set<Character> result = new LinkedHashSet<>();

        if (s.equals("#")) {
            result.add('#');
            return result;
        }

        boolean allEpsilon = true;

        for (char c : s.toCharArray()) {

            // Terminal
            if (!isNT(c)) {
                result.add(c);
                allEpsilon = false;
                break;
            }

            // Non-terminal
            result.addAll(first.get(c));
            result.remove('#');

            if (!first.get(c).contains('#')) {
                allEpsilon = false;
                break;
            }
        }

        if (allEpsilon)
            result.add('#');

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of productions: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Read grammar
        for (int i = 0; i < n; i++) {

            String line = sc.nextLine().replace(" ", "");

            char lhs = line.charAt(0);
            String rhs = line.substring(3);

            g.putIfAbsent(lhs, new ArrayList<>());
            first.putIfAbsent(lhs, new LinkedHashSet<>());
            follow.putIfAbsent(lhs, new LinkedHashSet<>());

            for (String p : rhs.split("\\|")) {
                g.get(lhs).add(p);
            }
        }

        // ---------------- FIRST ----------------
        boolean changed;

        do {
            changed = false;

            for (char nt : g.keySet()) {

                for (String p : g.get(nt)) {

                    Set<Character> f = firstOf(p);

                    if (first.get(nt).addAll(f))
                        changed = true;
                }
            }

        } while (changed);


        // ---------------- FOLLOW ----------------

        char start = g.keySet().iterator().next();
        follow.get(start).add('$');

        do {
            changed = false;

            for (char nt : g.keySet()) {

                for (String p : g.get(nt)) {

                    for (int i = 0; i < p.length(); i++) {

                        char B = p.charAt(i);

                        if (!isNT(B))
                            continue;

                        // Everything after B
                        String beta = p.substring(i + 1);

                        // B is at the end
                        if (beta.isEmpty()) {

                            if (follow.get(B).addAll(follow.get(nt)))
                                changed = true;
                        }

                        else {

                            Set<Character> f = firstOf(beta);

                            // FIRST(beta) - epsilon
                            f.remove('#');

                            if (follow.get(B).addAll(f))
                                changed = true;

                            // If beta can produce epsilon
                            if (firstOf(beta).contains('#')) {

                                if (follow.get(B).addAll(follow.get(nt)))
                                    changed = true;
                            }
                        }
                    }
                }
            }

        } while (changed);


        // Print FIRST
        System.out.println("\nFIRST:");

        for (char nt : g.keySet())
            System.out.println("FIRST(" + nt + ") = " + first.get(nt));


        // Print FOLLOW
        System.out.println("\nFOLLOW:");

        for (char nt : g.keySet())
            System.out.println("FOLLOW(" + nt + ") = " + follow.get(nt));

        sc.close();
    }
}