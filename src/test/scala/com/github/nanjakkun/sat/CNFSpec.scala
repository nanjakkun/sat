package com.github.nanjakkun.sat

import org.scalatest._

import _root_.com.github.nanjakkun.sat._
import _root_.com.github.nanjakkun.sat.CNF._

class CNFSpec extends FlatSpec{
  "cnf" should "valid dimacs form string" in {
    
    val clauses: Seq[Clause]  = List(List(1, 2))
    val cnf = CNF(clauses, 2)
    
    val expected = 
"""p cnf 2 1
1 2 0"""
      
//    println(cnf.asDIMACS)
    assert(expected == cnf.asDIMACS)
  }
}