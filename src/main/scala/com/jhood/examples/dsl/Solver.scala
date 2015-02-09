package com.jhood.examples.dsl

object Solver extends ProblemDsl {
  def manual_problem = Operation(_ - _, 
                        Operation(_ + _, Value(1), Value(2)),
                        Operation(_ + _, Value(4), Value(2))
                       )

  def dsl_problem = (1 plus 2) minus (4 plus 2)

  def main(args: Array[String]) = {
      println(manual_problem.solve())
      println(dsl_problem.solve())
  }
}

