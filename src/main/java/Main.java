import it.unibo.tuprolog.argumentation.core.model.Graph;
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
        SolveStandardKt.solve(theory, (String elem) -> {
            System.out.print(elem);
            return null;
        } );

        // ABSTRACT EVALUATION (GRAPH OUTPUT)
        final Graph graph = SolveStandardKt.solve(theory);
        graph.getLabellings().forEach(a -> {
            System.out.println("Argument with conclusion " + a.getArgument().getConclusion() + " is " + a.getLabel());
        });

        // QUERY EVALUATION
        final String query = "-flies(tweety)";
        final boolean result = SolveStandardKt.solve(query, theory);
        System.out.println(query + " is " + result);
    }
}
