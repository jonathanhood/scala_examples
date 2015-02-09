package com.jhood.examples.dsl

trait Problem {
  def solve(): Int = solve(this)

  def solve(prob: Problem): Int = prob match { 
    case Value(x) => x
    case Operation(oper,left,right) => oper(solve(left),solve(right))
  }
}

case class Operation(oper: (Int,Int) => Int, left: Problem, right: Problem) 
  extends Problem

case class Value(value: Int) 
  extends Problem

