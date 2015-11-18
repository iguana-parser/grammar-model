package iguana.grammar.symbol

import iguana.grammar.expression.StatementType

/**
 * @author Anastasia Izmaylova
 */
trait CodeType extends AbstractSymbolType with StatementType {
  
  type Visitor[T] <: SymbolVisitor[T]
  
  trait SymbolVisitor[T] extends super.SymbolVisitor[T] { }
  
  case class Code(val statements: java.util.List[Statement]) extends Symbol {
    
    def name = ""
    def preConditions = new java.util.HashSet()
    def postConditions = new java.util.HashSet()
    def label = ""
    
    type Builder <: SymbolBuilder
    def copyBuilder: Builder = ???
    
    def accept[T](v: Visitor[T]): T = ???
  }
  
  object Code {
    
    class Builder(val code: Code) extends AbstractBuilder(code.name, code.preConditions, code.postConditions, code.label) {
      
      type Sym = Code
      
      var statements = code.statements
      
      def setStatements(statements: java.util.List[Statement]): this.type = {
        this.statements = statements
        this
      }
      
      def addStatements(statements: java.util.List[Statement]): this.type = {
        this.statements = new java.util.ArrayList(this.statements)
        this.statements.addAll(statements)
        this
      }
      
      def addStatement(statement: Statement): this.type = {
        this.statements = new java.util.ArrayList(this.statements)
        this.statements.add(statement)
        this
      }
      
      def build = Code(statements)
      
    }
    
  }
  
}