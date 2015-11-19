package iguana.grammar.symbol

import iguana.grammar.expression.ExpressionType

/**
 * @author Anastasia Izmaylova
 */
trait ConditionalType extends AbstractSymbolType with ExpressionType {
  
  type Visitor[T] <: SymbolVisitor[T]
  
  trait SymbolVisitor[T] extends super.SymbolVisitor[T] { def visitConditional(symbol: Conditional): T }
  
  case class Conditional(val expression: Expression) extends Symbol {
    
    def name = ""
    def preConditions = new java.util.HashSet()
    def postConditions = new java.util.HashSet()
    def label = ""
    
    type Builder = Conditional.Builder
    def copyBuilder: Builder = new Conditional.Builder(this)
    
    def accept[T](v: Visitor[T]): T = v.visitConditional(this)
    
  }
  
  object Conditional {
    
    class Builder(val cond: Conditional) extends AbstractBuilder(cond.name, cond.preConditions, cond.postConditions, cond.label) {
      
      type Sym = Conditional
      
      var expression = cond.expression
      
      def setExpression(expression: Expression): this.type = {
        this.expression = expression
        this
      }
      
      def build = new Conditional(expression)
      
    }
    
  }
  
}