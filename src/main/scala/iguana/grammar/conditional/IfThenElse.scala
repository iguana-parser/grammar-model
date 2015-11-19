package iguana.grammar.conditional

import iguana.grammar.symbol.AbstractSymbolType
import iguana.grammar.expression.ExpressionType

/**
 * @author Anastasia Izmaylova
 */
trait IfThenElseType extends AbstractSymbolType with ExpressionType {
  
  type Visitor[T] <: SymbolVisitor[T]
  
  trait SymbolVisitor[T] extends super.SymbolVisitor[T] { def visitIfThenElse(symbol: IfThenElse): T }
  
  case class IfThenElse(val preConditions: java.util.Set[Condition] = new java.util.HashSet(),
                        val postConditions: java.util.Set[Condition] = new java.util.HashSet(),
                        val label: String = "",
                        val condition: Expression,
                        val thenPart: Symbol,
                        val elsePart: Symbol) extends Symbol {
    
    def name = ""
    
    type Builder = IfThenElse.Builder
    def copyBuilder: Builder = new IfThenElse.Builder(this)
    
    def accept[T](v: Visitor[T]): T = v.visitIfThenElse(this)
    
  }
  
  object IfThenElse {
    
    class Builder(val ifThenElse: IfThenElse) extends AbstractBuilder(ifThenElse.name, ifThenElse.preConditions, ifThenElse.postConditions, ifThenElse.label) {
      
      type Sym = IfThenElse
      
      var condition = ifThenElse.condition
      var thenPart = ifThenElse.thenPart
      var elsePart = ifThenElse.elsePart
      
      def setCondition(condition: Expression): this.type = {
        this.condition = condition
        this
      }
      
      def setThenPart(thenPart: Symbol): this.type = {
        this.thenPart = thenPart
        this
      }
      
      def setElsePart(elsePart: Symbol): this.type = {
        this.elsePart = elsePart
        this
      }
      
      def build = IfThenElse(preConditions, postConditions, label, condition, thenPart, elsePart)
      
    }
    
  }
  
}