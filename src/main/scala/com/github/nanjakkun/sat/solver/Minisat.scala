package com.github.nanjakkun.sat.solver

import org.apache.commons.lang3.RandomStringUtils
import java.io._
import scala.io.Source

import sys.process.Process

import _root_.com.github.nanjakkun.sat._
import _root_.com.github.nanjakkun.sat.CNF._


/**
 * @author @nanjakkun
 * Minisatを外部から扱う
 */
object Minisat extends Solver {
  def solve(cnf: CNF): Option[Clause] = {
    /**
     * @param res: minisatが返す文字列
     * @return
     */
    def parseResult(res: String): Option[Clause] = {
      if (res.startsWith("SAT")){
        val pattern = """-?\d+""".r
        Some(pattern.findAllIn(res).map(_.toInt).toList)
      } else {
        None
      }
    }
    
    import resource._
    
    val cnfFileName    = RandomStringUtils.randomAlphabetic(10) +  ".cnf"
    val resultFileName = RandomStringUtils.randomAlphabetic(10) +  ".cnf"
    
    val cnfFile = new File(cnfFileName)
    val resultFile = new File(resultFileName)
    
    try {
      for { writer <- managed(new PrintWriter(cnfFile)) } {
        writer.print(cnf.asDIMACS)
      }
    
      val cmd = s"minisat $cnfFileName $resultFileName"    
      val process = Process(cmd).run
      
      // 外部のminisatがresultFileを手放してくれるのを待つ
      Thread.sleep(1000)

      val source = Source.fromFile(resultFile)
      
      try {
        parseResult(source.getLines.mkString(" "))
      } finally {
        // あとでLoanパターンにするかも
        source.close()
      }
    } finally {
      resultFile.delete()
      cnfFile.delete()
    }
  }
  

}