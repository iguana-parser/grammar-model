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
  
  case class Assignment(val id: java.lang.String, val i: java.lang.Integer, val expression: Expression) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitAssignment(this)
    override def toString = id + (if (i == -1) "" else ":" + i) + "=" + expression.toString()
  }
  
  object Assignment { def apply(id: java.lang.String, expression: Expression): Assignment = Assignment(id, -1, expression)}
  
  case class Or(val lhs: Expression, val rhs: Expression) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitOr(this)
    override def toString = lhs.toString() + "||" + rhs.toString()
  }
  
  case class And(val lhs: Expression, val rhs: Expression) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitAnd(this)
    override def toString = lhs.toString() + "&&" + rhs.toString()
  }
  
  case class Less(val lhs: Expression, val rhs: Expression) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitLess(this)
    override def toString = lhs.toString() + "<" + rhs.toString()
  }
  
  case class LessThanEqual(val lhs: Expression, val rhs: Expression) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitLessThanEqual(this)
    override def toString = lhs.toString() + "<=" + rhs.toString()
  }
  
  case class Greater(val lhs: Expression, val rhs: Expression) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitGreater(this)
    override def toString = lhs.toString() + ">" + rhs.toString()
  }
  
  case class GreaterThanEqual(val lhs: Expression, val rhs: Expression) extends Expression {
    def accept[T](v: ASTVisitor[T]) = v.visitGreaterThanEqual(this)
    override def toString = lhs.toString() + ">=" + rhs.toString()
  }
  
  trait AbstractASTVisitor[T] extends super.AbstractASTVisitor[T] {
    def visitBoolean(expression: Boolean): T
    def visitInteger(expression: Integer): T
    def visitReal(expression: Real): T
    def visitString(expression: String): T
    def visitTuple(expression: Tuple): T
    def visitName(expression: Name): T
    def visitCall(expression: Call): T
    def visitAssignment(expression: Assignment): T
    def visitOr(expression: Or): T
    def visitAnd(expression: And): T
    def visitLess(expression: Less): T
    def visitLessThanEqual(expression: LessThanEqual): T
    def visitGreater(expression: Greater): T
    def visitGreaterThanEqual(expression: GreaterThanEqual): T
  }
  
}