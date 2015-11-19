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
  
  trait AbstractASTVisitor[T] extends super.AbstractASTVisitor[T] {
    def visitBoolean(expression: Boolean): T
    def visitInteger(expression: Integer): T
    def visitReal(expression: Real): T
    def visitString(expression: String): T
    def visitTuple(expression: Tuple): T
  }
  
}