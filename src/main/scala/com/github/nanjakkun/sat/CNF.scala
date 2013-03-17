package com.github.nanjakkun.sat

import language.postfixOps

case class CNF(clauses: Seq[Clause], numOfVariables: Int) {
  
  override def toString = {
    val numOfClauses = clauses.size
    s"variables: $numOfVariables \n" +
     "clauses:   $numOfClauses"
  }
  
  def asDIMACS = {
    val numOfClauses = clauses.size
    s"p cnf $numOfVariables $numOfClauses\n" +
    (clauses map (x => {
       x.mkString(" ") + " 0" 
    }) mkString("\n"))
  }
}

object CNF {
  def atLeastOneMustBeTrue(lits: Seq[Literal]): Seq[Clause] = {
    Seq(lits)
  }
  
  def atMostOneMustBeTrue(lits: Seq[Literal]): Seq[Clause] = {
    lits map(_ * -1) combinations(2) toSeq
  }
  
  def oneMustBeTrue(lits: Seq[Literal]) =
    atLeastOneMustBeTrue(lits) ++ atMostOneMustBeTrue(lits)
}
