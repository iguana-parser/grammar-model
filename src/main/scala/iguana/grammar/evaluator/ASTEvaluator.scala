package iguana.grammar.evaluator

import iguana.grammar.ast.AST

/**
 * @author Anastasia Izmaylova
 */
trait ASTEvaluator extends ExpressionEvaluatorType 
                      with StatementEvaluatorType 
                      with DeclarationEvaluatorType
                      with AST {
  
  trait Evaluator extends super[ExpressionEvaluatorType].Evaluator
                     with super[StatementEvaluatorType].Evaluator
                     with super[DeclarationEvaluatorType].Evaluator
                     with super[AST].AbstractASTVisitor[java.lang.Object] {
    self: super[AST].ASTVisitor[java.lang.Object] =>
      
  }
  
}