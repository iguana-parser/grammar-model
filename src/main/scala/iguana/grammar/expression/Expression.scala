package iguana.grammar.expression

import scala.collection.JavaConversions._

/**
 * @author Anastasia Izmaylova
 */
trait ExpressionType extends AbstractASTType {
  
  type ASTVisitor[T] <: AbstractASTVisitor[T]
  
  trait Expression extends AbstractAST
  
  trait Boolean extends Expression
  
  case object TRUE extends Boolean { 
    def accept[T](v: ASTVisitor[T]) = v.visitBoolean(this)
    override def toString = "true"
  }
  
  case object FALSE extends Boolean { 
    def accept[T](v: ASTVisitor[T]) = v.visitBoolean(this)
    override def toString = "false"
  }
  
  case class Integer(val value: java.lang.Integer) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitInteger(this)
    override def toString = value.toString()
  }
  
  case class Real(val value: java.lang.Float) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitReal(this)
    override def toString = value.toString() 
  }
  
  case class String(val value: java.lang.String) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitString(this)
    override def toString = value
  }
  
  case class Tuple(val expressions: java.util.List[Expression]) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitTuple(this)
    override def toString = {
      if (expressions.isEmpty())
        ""
      else {
        val es: Iterable[Expression] = expressions
        "(" + es.tail.foldLeft(es.head.toString())((s, e) => s + "," + e.toString()) + ")"
      }
    }
  }
  
  case class Name(val name: java.lang.String, val i: java.lang.Integer) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitName(this)
    override def toString = if (i == -1) name else (name + ":" + i)
  }
  
  object Name { def apply(name: java.lang.String): Name = Name(name, -1) }
  
  abstract class Call extends Expression {
    def fun: java.lang.String
    def arguments: java.util.List[Expression]
    def accept[T](v: ASTVisitor[T]) = v.visitCall(this)
    override def toString = {
      val args = if (arguments.isEmpty())
                   ""
                 else {
                   val as: Iterable[Expression] = arguments
                   as.tail.foldLeft(as.head.toString())((s, e) => s + "," + e.toString())
                 }
      fun + "(" + args + ")" 
    }
  }
  
  trait AbstractASTVisitor[T] extends super.AbstractASTVisitor[T] {
    def visitBoolean(expression: Boolean): T
    def visitInteger(expression: Integer): T
    def visitReal(expression: Real): T
    def visitString(expression: String): T
    def visitTuple(expression: Tuple): T
    def visitName(expression: Name): T
    def visitCall(expression: Call): T
  }
  
}