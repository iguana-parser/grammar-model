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
    
    def accept[T](v: Visitor[T]) = v.visitIgnore(this)
    
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