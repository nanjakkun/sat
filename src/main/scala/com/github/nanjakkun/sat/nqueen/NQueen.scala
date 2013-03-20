package com.github.nanjakkun.sat.nqueen

import _root_.com.github.nanjakkun.sat._
import _root_.com.github.nanjakkun.sat.CNF._

/**
 * @author nanjakkun
 */
case class NQueen(n: Int) {
  protected[nqueen] def eachRowHasOneQueen: Seq[Clause] = {
    (Seq[Clause]() /: (0 until n)) {(sum, i) => 
      val vars = (i * n + 1) to (i + 1) * n 
      sum ++ oneMustBeTrue(vars)
    }
  }

  protected[nqueen] def eachColumnHasOneQueen: Seq[Clause] = {
    (Seq[Clause]() /: (1 to n)) {(sum, i) => 
      val vars = i to (n * n) by n
      sum ++ oneMustBeTrue(vars)
    }
  }
  
  protected[nqueen] def eachDiagonalLineHasOneQueen: Seq[Clause] = {
    def fromLeftTop = {
      (Seq[Clause]() /: (1 to n * n).groupBy(x => (x - 1) % n - (x - 1) / n)) {(sum, i) => 
        sum ++ atMostOneMustBeTrue(i._2)
      }
    }
    
    def fromRightTop = {
      (Seq[Clause]() /: (1 to n * n).groupBy(x => (x - 1) % n + (x - 1) / n)) {(sum, i) => 
        sum ++ atMostOneMustBeTrue(i._2)
      }
    }
    
    fromLeftTop ++ fromRightTop
  }
  
  def getCNF(): CNF = {
    val clauses = eachRowHasOneQueen ++ 
                  eachColumnHasOneQueen ++ 
                  eachDiagonalLineHasOneQueen
                  
    CNF(clauses, n * n)
  }
  
  def printAnswer(lits: Seq[Literal]): Unit = {
    val queens = lits.filter(_ > 0)
    val positions: Seq[(Int, Int)] = queens.map(x => ((x - 1) % n, (x -1) / n))
    
    val board = Array.ofDim[Char](n, n)
    for(i <- 0 until n; j <- 0 until n){
      board(i)(j) = '-'
    }
    
    positions.foreach(x => {
      board(x._1)(x._2) = 'Q'
    })
    
    board.foreach(x => println(("" /: x){_ + _}))
  }
}