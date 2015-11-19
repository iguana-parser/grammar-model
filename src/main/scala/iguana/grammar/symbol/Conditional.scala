/*
 * Copyright (c) 2015, Ali Afroozeh and Anastasia Izmaylova, Centrum Wiskunde & Informatica (CWI)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this 
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this 
 *    list of conditions and the following disclaimer in the documentation and/or 
 *    other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
 * OF SUCH DAMAGE.
 *
 */
package iguana.grammar.symbol

import iguana.grammar.ast.ExpressionType

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