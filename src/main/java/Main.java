import it.unibo.tuprolog.argumentation.core.model.Graph;
import org.example.SolveStandardKt;
import org.example.TheoryKt;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ABSTRACT EVALUATION (GRAPH OUTPUT)
        final Graph graph = SolveStandardKt.solve(TheoryKt.getTestTheory());
        graph.getLabellings().forEach(a ->
                System.out.println("Argument with conclusion " + a.getArgument().getConclusion() + " is " + a.getLabel()));

        // LIST OF QUERIES EVALUATION (True if the argument exists and is verified, False otherwise)
        final List<String> queries = Arrays.asList(
                "violation(viol(epc_1))",
                "violation(viol(epr_1))",
                "violation(viol(ca(epr_1, x_1, r_1)))",
                "o(-publish(epc_1))",
                "o(-publish(epr_1))",
                "o(remove(ca(epr_1, x_1, r_1)))"
        );
        SolveStandardKt.solve(queries, TheoryKt.getTestTheory())
                .forEach(s -> System.out.println(s.component1() + ":" + s.component2()));
    }
}
