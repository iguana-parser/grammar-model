package iguana.grammar.expression

/**
 * @author Anastasia Izmaylova
 */
trait AbstractASTType {
  
  type ASTVisitor[T] <: AbstractASTVisitor[T]
  
  trait AbstractASTVisitor[T]
  
  trait AbstractAST { def accept[T](v: ASTVisitor[T]): T }
  
}