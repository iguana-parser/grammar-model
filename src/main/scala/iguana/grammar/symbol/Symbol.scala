package iguana.grammar.symbol

import org.iguana.util.CollectionsUtil

/**
 * @author Anastasia Izmaylova
 */
trait SymbolType {
  
  trait Symbol {
    
    def name: String
    def preConditions: java.util.Set[Condition]
    def postConditions: java.util.Set[Condition]
    def label: String
    
    def hasConditions = !conditions.isEmpty()
    def conditions = CollectionsUtil.union(preConditions, postConditions)
    def hasLabel = !label.isEmpty()
    
    type Builder <: SymbolBuilder
    def copyBuilder: Builder
    
    def accept[T](v: Visitor[T]): T
    
    def constructorCode: String
    
  }
  
  trait SymbolVisitor[T] { def visitSymbol(symbol: Symbol): T }
  
  type Visitor[T] <: SymbolVisitor[T]
  
  trait SymbolBuilder {
    type Sym <: Symbol
    
    def build: Sym
    
    def name: String
    def preConditions: java.util.Set[Condition]
    def postConditions: java.util.Set[Condition]
    def label: String
    
    def setPreConditions(conditions: java.util.Set[Condition]): this.type
    def setPostConditions(conditions: java.util.Set[Condition]): this.type
    
    def addPreConditions(conditions: java.util.Set[Condition]): this.type = {
      val preConditions = new java.util.HashSet(this.preConditions)
      preConditions.addAll(conditions)
      this
    }
    
    def addPostConditions(conditions: java.util.Set[Condition]): this.type = {
      val postConditions = new java.util.HashSet(this.postConditions)
      postConditions.addAll(conditions)
      this
    }
    
    def setLabel(label: String): this.type
  }
  
  trait Condition
  
}