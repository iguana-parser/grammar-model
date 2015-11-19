package iguana.grammar.layout

import iguana.grammar.symbol.AbstractSymbolType

/**
 * @author Anastasia Izmaylova
 */
trait IgnoreType extends AbstractSymbolType {
  
  type Visitor[T] <: SymbolVisitor[T]
  
  trait SymbolVisitor[T] extends super.SymbolVisitor[T] { def visitIgnore(symbol: Ignore): T }
  
  case class Ignore(val symbol: Symbol) extends Symbol {
    
    def name = ""
    def preConditions = new java.util.HashSet()
    def postConditions = new java.util.HashSet()
    def label = ""
    
    type Builder = Ignore.Builder
    def copyBuilder = new Ignore.Builder(this)
    
    def accept[T](v: Visitor[T]): T = v.visitIgnore(this)
    
  }
  
  object Ignore {
    
    class Builder(val s: Ignore) extends AbstractBuilder(s.name, s.preConditions, s.postConditions, s.label) {
      
      type Sym = Ignore
      
      var symbol = s.symbol
      
      def setSymbol(symbol: Symbol): this.type = {
        this.symbol = symbol
        this
      }
      
      def build = Ignore(symbol)
      
    }
    
  }
  
}