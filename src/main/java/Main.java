import it.unibo.tuprolog.argumentation.core.model.Graph;
import org.example.SolveStandardKt;
import org.example.TheoryKt;

import java.util.Arrays;
import java.util.List;

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
        graph.getLabellings().forEach(a ->
                System.out.println("Argument with conclusion " + a.getArgument().getConclusion() + " is " + a.getLabel()));

        // QUERY EVALUATION
        final String query = "-flies(tweety)";
        final boolean result = SolveStandardKt.solve(query, theory);
        System.out.println(query + " is " + result);

        // LIST OF QUERIES EVALUATION
        final List<String> queries = Arrays.asList(
                "violation(viol(epc))",
                "violation(viol(epr))",
                "violation(viol(ca(epr, x, r)))",
                "o(-publish(epc))",
                "o(-publish(epr))",
                "o(remove(ca(epr, x, r)))"
        );
        System.out.println(SolveStandardKt.solve(queries, TheoryKt.getTestTheory()));
    }
}
