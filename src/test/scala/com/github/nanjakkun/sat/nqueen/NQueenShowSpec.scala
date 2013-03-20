package com.github.nanjakkun.sat.nqueen

import _root_.com.github.nanjakkun.sat.solver.Minisat
import org.scalatest._

class NQueenShowSpec extends FlatSpec {
  "N-Queen" should "be SAT" in {
    val numOfQueens = 50
    val nQueen = NQueen(numOfQueens)
    val minisatRes = Minisat.solve(nQueen.getCNF)
    
    assert(minisatRes.nonEmpty)
    assert(minisatRes.get.filter(_ > 0).length == numOfQueens)
    
    nQueen.printAnswer(minisatRes.get)
  }
  
}