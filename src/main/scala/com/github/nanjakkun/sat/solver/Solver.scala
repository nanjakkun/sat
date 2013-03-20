package com.github.nanjakkun.sat.solver

import _root_.com.github.nanjakkun.sat._
import _root_.com.github.nanjakkun.sat.CNF._

trait Solver {
  def solve(cnf: CNF): Option[Clause]
}
