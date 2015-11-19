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