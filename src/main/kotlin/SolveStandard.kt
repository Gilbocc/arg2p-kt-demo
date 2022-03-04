package org.example

import it.unibo.tuprolog.argumentation.core.Arg2pSolver
import it.unibo.tuprolog.argumentation.core.dsl.arg2pScope
import it.unibo.tuprolog.argumentation.core.libs.basic.FlagsBuilder
import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.parsing.parse
import it.unibo.tuprolog.solve.channel.OutputChannel
import it.unibo.tuprolog.solve.classic.ClassicSolverFactory
import it.unibo.tuprolog.theory.Theory
import it.unibo.tuprolog.theory.parsing.parse
import it.unibo.tuprolog.argumentation.core.mining.graph
import it.unibo.tuprolog.argumentation.core.model.Graph
import it.unibo.tuprolog.solve.SolveOptions
import it.unibo.tuprolog.solve.TimeDuration


fun solve(theory: String) : Graph =
    arg2pScope {
        Arg2pSolver.default().let { arg2pSolver ->
            ClassicSolverFactory.mutableSolverWithDefaultBuiltins(
                otherLibraries = arg2pSolver.to2pLibraries().plus(FlagsBuilder(argumentLabellingMode = "grounded_hash").create().content()),
                staticKb = Theory.parse(theory, arg2pSolver.operators()),
                stdOut = OutputChannel.of {}
            ).let { solver ->
                solver.solve(Struct.parse("buildLabelSetsSilent") and "context_active"(X), SolveOptions.allLazilyWithTimeout(TimeDuration.MAX_VALUE))
                    .map { it.substitution[X]!!.asNumeric()!!.intValue.toInt() }
                    .first()
                    .let { solver.graph(it) }
            }
        }
    }


fun solve(theory: String, consumer : (String) -> Unit) =
    Arg2pSolver.default().let { arg2pSolver ->
            ClassicSolverFactory.mutableSolverWithDefaultBuiltins(
            otherLibraries = arg2pSolver.to2pLibraries().plus(FlagsBuilder(argumentLabellingMode = "grounded_hash").create().content()),
            staticKb = Theory.parse(theory, arg2pSolver.operators()),
            stdOut = OutputChannel.of(consumer)
        ).solve(Struct.parse("buildLabelSets"), SolveOptions.allLazilyWithTimeout(TimeDuration.MAX_VALUE)).first()
    }

fun solve(query: String, theory: String) : String =
    arg2pScope {
        Arg2pSolver.default().let { arg2pSolver ->
            ClassicSolverFactory.mutableSolverWithDefaultBuiltins(
                otherLibraries = arg2pSolver.to2pLibraries().plus(FlagsBuilder().create().content()),
                staticKb = Theory.parse(theory, arg2pSolver.operators())
            ).solve("answerQuery"(Struct.parse(query, arg2pSolver.operators()), X, Y, Z), SolveOptions.allLazilyWithTimeout(TimeDuration.MAX_VALUE))
                .filter { it.isYes }
                .first()
                .let {
                    it.substitution[X]!!.toTerm().toString()
                }
        }
    }


fun solve(queryList: List<String>, theory: String) : List<Pair<String, Boolean>> =
    arg2pScope {
        Arg2pSolver.default().let { arg2pSolver ->
            ClassicSolverFactory.mutableSolverWithDefaultBuiltins(
                otherLibraries = arg2pSolver.to2pLibraries().plus(FlagsBuilder().create().content()),
                staticKb = Theory.parse(theory, arg2pSolver.operators())
            ).let { solver ->
                solver.solve("parser" call "convertAllRules"(`_`), SolveOptions.allLazilyWithTimeout(TimeDuration.MAX_VALUE)).first()
                queryList.map { query ->
                    solver.solve("structured" call "computeStatementAcceptance"(
                        Struct.parse(query, arg2pSolver.operators()), X, Y, Z), SolveOptions.allLazilyWithTimeout(TimeDuration.MAX_VALUE)).first().let {
                            Pair(query, !it.substitution[X]!!.isEmptyList)
                        }
                }
            }
        }
    }
