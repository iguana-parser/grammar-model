package iguana.grammar.expression

/**
 * @author Anastasia Izmaylova
 */
trait StatementType extends AbstractASTType {
  
  type ASTVisitor[T] <: AbstractASTVisitor[T]
  
  trait Statement extends AbstractAST
  
  trait AbstractASTVisitor[T] extends super.AbstractASTVisitor[T] {
    
  }
  
}