package iguana.grammar.layout

import iguana.grammar.symbol.AbstractSymbolType

/**
 * @author Anastasia Izmaylova
 */
trait OffsideType extends AbstractSymbolType {
  
  type Visitor[T] <: SymbolVisitor[T]
  
  trait SymbolVisitor[T] extends super.SymbolVisitor[T] { def visitOffside(symbol: Offside): T }
  
  case class Offside(val symbol: Symbol) extends Symbol {
    
    def name = ""
    def preConditions = new java.util.HashSet()
    def postConditions = new java.util.HashSet()
    def label = ""
    
    type Builder = Offside.Builder
    def copyBuilder = new Offside.Builder(this)
    
    def accept[T](v: Visitor[T]) = v.visitOffside(this) 
    
  }
  
  object Offside {
    
    class Builder(val s: Offside) extends AbstractBuilder(s.name, s.preConditions, s.postConditions, s.label) {
      
      type Sym = Offside
      
      var symbol = s.symbol
      
      def setSymbol(symbol: Symbol): this.type = {
        this.symbol = symbol
        this
      }
      
      def build = Offside(symbol);
      
    }
    
  }
  
}