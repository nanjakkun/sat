package com.github.nanjakkun.sat.nqueen

import _root_.com.github.nanjakkun.sat.solver.Minisat
import org.scalatest._

class NQueenSpec extends FlatSpec {
  "3-Queen" should "be UNSAT" in {
    val minisatRes = Minisat.solve(NQueen(3).getCNF)
    
    assert(minisatRes.isEmpty)
  }

  "4-Queen" should "be SAT" in {
    val minisatRes = Minisat.solve(NQueen(4).getCNF)
    
    assert(minisatRes.nonEmpty)
    
    val queens = minisatRes.get.filter(_ > 0)
    
    assert(queens.length == 4)
  }
  
  "N-Queen" should "be SAT" in {
    val numOfQueens = 50
    val minisatRes = Minisat.solve(NQueen(numOfQueens).getCNF)
    
    assert(minisatRes.nonEmpty)
    
    val queens = minisatRes.get.filter(_ > 0)
    
    assert(queens.length == numOfQueens)
  }
  
}