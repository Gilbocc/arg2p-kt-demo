package org.gradle.sample

import it.unibo.tuprolog.argumentation.core.Arg2pSolver
import it.unibo.tuprolog.argumentation.core.libs.basic.FlagsBuilder
import it.unibo.tuprolog.argumentation.core.dsl.arg2pScope
import it.unibo.tuprolog.argumentation.core.model.Graph
import it.unibo.tuprolog.argumentation.core.model.LabelledArgument

import it.unibo.tuprolog.solve.classic.ClassicSolverFactory
import it.unibo.tuprolog.solve.flags.FlagStore
import it.unibo.tuprolog.solve.flags.TrackVariables
import it.unibo.tuprolog.solve.flags.Unknown
import it.unibo.tuprolog.theory.Theory
import it.unibo.tuprolog.theory.parsing.parse
import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.parsing.parse
import it.unibo.tuprolog.argumentation.core.mining.graph

fun solveDemo(kb: String): Graph =
    arg2pScope {
        ClassicSolverFactory.mutableSolverWithDefaultBuiltins(
            otherLibraries = Arg2pSolver.default(staticLibs = listOf(FlagsBuilder().create())).to2pLibraries(),
            staticKb = Theory.parse(kb, Arg2pSolver.default().operators()),
            flags = flags.set(Unknown, Unknown.FAIL).set(TrackVariables, TrackVariables.ON),
        ).let { solver ->
            solver.solve(Struct.parse("buildLabelSetsSilent") and "context_active"(X))
                .filter { it.isYes }
                .map { it.substitution[X]!!.toString().toInt() }
                .map { context -> solver.graph(context) }
                .firstOrNull() ?: Graph(emptyList(), emptyList(), emptyList())
        }
    }

fun main() {
    solveDemo("""
        r1 : a => d.
        r2 : b => -d.
        r3 : c => undercut(r1).
        
        p1 :=> a.
        p2 :=> b.
        p3 :=> c.
    """.trimIndent()).labellings.forEach {
        println("${it.label} : ${it.argument.conclusion}")
    }
}