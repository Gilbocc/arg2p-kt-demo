package it.unibo.tuprolog.argumentation.demo

import it.unibo.tuprolog.argumentation.core.Arg2pSolverFactory
import it.unibo.tuprolog.argumentation.core.libs.basic.FlagsBuilder

fun main() {
    val graph = Arg2pSolverFactory.evaluate("""
        r1 : a => d.
        r2 : b => -d.
        r3 : c => undercut(r1).
        
        p1 :=> a.
        p2 :=> b.
        p3 :=> c.
    """.trimIndent(), FlagsBuilder()).first()

    graph.labellings.forEach {
        println("${it.label} : ${it.argument.conclusion}")
    }
}