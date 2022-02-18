import org.example.SolveStandardKt;

public class Main {

    private static final String theory = String.join("\n",
            "d1 : bird(X) => flies(X).",
                "d2 : penguin(X) => bird(X).",
                "s1 : penguin(X) -> -flies(X).",
                "a1 :-> penguin(tweety)."
            );


    public static void main(String[] args) {
        // ABSTRACT EVALUATION
        // output to std out
        SolveStandardKt.solve(theory);

        // QUERY EVALUATION
        final String query = "-flies(tweety)";
        final boolean result = SolveStandardKt.solve(query, theory);
        System.out.println(query + " is " + result);
    }
}
