package com.jhood.examples.dsl

trait ProblemDsl {
  implicit def intToProblem(x: Int): Problem = Value(x)

  abstract class ProblemDsl[A <% Problem] { 
    def left: A
    def plus(right: Problem): Problem = Operation(_ + _, left, right)
    def minus(right: Problem): Problem = Operation(_ - _, left, right) 
  }

  implicit class PimpedProblem(val left: Problem) extends ProblemDsl[Problem]
  implicit class PimpedInt(val left: Int) extends ProblemDsl[Int]
}

