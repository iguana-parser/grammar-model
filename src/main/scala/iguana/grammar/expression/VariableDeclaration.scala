package iguana.grammar.expression

/**
 * @author Anastasia Izmaylova
 */
trait VariableDeclaration extends AbstractASTType with ExpressionType {
  
  type ASTVisitor[T] <: AbstractASTVisitor[T]
  
  trait VariableDeclaration extends AbstractAST
  
  case class VariableDeclarationInit(val id: java.lang.String, 
                                     val i: java.lang.Integer,
                                     val expression: Expression) extends VariableDeclaration {
    def accept[T](v: ASTVisitor[T]) = v.visitVariableDeclarationInit(this)
    override def toString = id + (if (i == -1) "" else ":" + i) + "=" + expression.toString()
  }
  
  object VariableDeclarationInit { def apply(id: java.lang.String, expression: Expression): VariableDeclarationInit = VariableDeclarationInit(id, -1, expression) }
  
  case class VariableDeclarationNoInit(val id: java.lang.String,
                                       val i: java.lang.Integer) extends VariableDeclaration {
    def accept[T](v: ASTVisitor[T]) = v.visitVariableDeclarationNoInit(this)
    override def toString = id + (if (i == -1) "" else ":" + i)
  }
  
  object VariableDeclarationNoInit { def apply(id: java.lang.String): VariableDeclarationNoInit = VariableDeclarationNoInit(id, -1) }
  
  trait AbstractASTVisitor[T] extends super.AbstractASTVisitor[T] {
    def visitVariableDeclarationInit(declaration: VariableDeclarationInit): T
    def visitVariableDeclarationNoInit(declaration: VariableDeclarationNoInit): T
  }
  
}