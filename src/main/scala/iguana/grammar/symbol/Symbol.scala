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
      setPreConditions(preConditions)
      this
    }
    
    def addPostConditions(conditions: java.util.Set[Condition]): this.type = {
      val postConditions = new java.util.HashSet(this.postConditions)
      postConditions.addAll(conditions)
      setPostConditions(postConditions)
      this
    }
    
    def setLabel(label: String): this.type
  }
  
  trait Condition
  
}