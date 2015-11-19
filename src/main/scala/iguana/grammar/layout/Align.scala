package iguana.grammar.layout

import iguana.grammar.symbol.AbstractSymbolType

/**
 * @author Anastasia Izmaylova
 */
trait AlignType extends AbstractSymbolType {
  
  type Visitor[T] <: SymbolVisitor[T]
  
  trait SymbolVisitor[T] extends super.SymbolVisitor[T] { def visitAlign(symbol: Align): T }
  
  case class Align(val symbol: Symbol) extends Symbol {
    
    def name = ""
    def preConditions = new java.util.HashSet()
    def postConditions = new java.util.HashSet()
    def label = ""
    
    type Builder = Align.Builder
    def copyBuilder = new Align.Builder(this)
    
    def accept[T](v: Visitor[T]) = v.visitAlign(this)
    
  }
  
  object Align {
    
    class Builder(val s: Align) extends AbstractBuilder(s.name, s.preConditions, s.postConditions, s.label) {
      
      type Sym = Align
      
      var symbol = s.symbol
      
      def setSymbol(symbol: Symbol): this.type = {
        this.symbol = symbol
        this
      }
      
      def build = Align(symbol)
      
    }
    
  }
  
}