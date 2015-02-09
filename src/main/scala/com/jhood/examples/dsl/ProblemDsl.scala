package com.jhood.examples.dsl

trait ProblemDsl {
  trait Conversion[A] {
    def convert(x: A): Problem
  }
  
  implicit def intConverter = new Conversion[Int] {
    def convert(x: Int): Problem = Value(x)
  }

  implicit def passThroughConverter = new Conversion[Problem] {
    def convert(x: Problem): Problem = x
  }

  implicit class ProblemLanguage[A : Conversion](val left: A) {
    def plus[B : Conversion](right: B): Problem = makeOper(_ + _, right)
    def minus[B : Conversion](right: B): Problem = makeOper(_ - _, right) 

    private[this] def makeOper[B : Conversion](oper: (Int,Int) => Int, right: B): Problem =
      Operation(
        oper, 
        implicitly[Conversion[A]].convert(left), 
        implicitly[Conversion[B]].convert(right)
      )
  }
}

