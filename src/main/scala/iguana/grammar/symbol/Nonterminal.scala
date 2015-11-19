package iguana.grammar.symbol

import java.util.HashSet

/**
 * @author Anastasia Izmaylova
 */
trait NonterminalType extends AbstractSymbolType {
  
  type Visitor[T] <: SymbolVisitor[T]
  
  trait SymbolVisitor[T] extends super.SymbolVisitor[T] { def visitNonterminal(symbol: Nonterminal): T }
  
  case class Nonterminal(val name: String,
                         val preConditions: java.util.Set[Condition] = new java.util.HashSet(),
                         val postConditions: java.util.Set[Condition] = new java.util.HashSet(),
                         val label: String = "",
                         val variable: String = "") extends Symbol {
    
    type Builder = Nonterminal.Builder
    
    def copyBuilder: Builder = new Nonterminal.Builder(this)
    
    def accept[T](v: Visitor[T]): T = v.visitNonterminal(this)
    
  }
  
  object Nonterminal {
    
    class Builder(val nt: Nonterminal) extends AbstractBuilder(nt.name, nt.preConditions, nt.postConditions, nt.label) {
      
      type Sym = Nonterminal
      
      var variable: String = nt.variable
      
      def setVariable(variable: String): this.type = {
        this.variable = variable
        this
      }
      
      def build = Nonterminal(name, preConditions, postConditions, label, variable)
      
    }
    
  }
  
}