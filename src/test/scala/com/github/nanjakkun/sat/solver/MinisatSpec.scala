package com.github.nanjakkun.sat.solver

import org.scalatest._

import _root_.com.github.nanjakkun.sat._
import _root_.com.github.nanjakkun.sat.CNF._

class MinisatSpec extends FlatSpec{
  
  // p cnf 1 1
  // 1 0
  "one variable clause" should "be SAT" in {
    val clauses: Seq[Clause]  = List(List(1))
    val cnf = CNF(clauses, 1)
    
    val res = Minisat.solve(cnf)
    assert(res.nonEmpty)
    assert(res.get.contains(1))
  }  

  // p cnf 2 1
  // 1 0
  // -1 0
  "contradiction equation" should "give UNSAT" in {
    val clauses: Seq[Clause]  = List(List(1), List(-1))
    val cnf = CNF(clauses, 1)
    
    val res = Minisat.solve(cnf)
    
    assert(res.isEmpty)
  }
  
}