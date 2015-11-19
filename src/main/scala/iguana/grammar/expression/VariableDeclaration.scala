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
package iguana.grammar.expression

/**
 * @author Anastasia Izmaylova
 */
trait VariableDeclarationType extends AbstractASTType with ExpressionType {
  
  type ASTVisitor[T] <: AbstractASTVisitor[T]
  
  trait VariableDeclaration extends AbstractAST
  
  case class VariableDeclarationInit(val id: java.lang.String, 
                                     val i: java.lang.Integer,
                                     val expression: Expression) extends VariableDeclaration {
    def accept[T](v: ASTVisitor[T]) = v.visitVariableDeclarationInit(this)
    override def toString = id + (if (i == -1) "" else ":" + i) + "=" + expression.toString()
  }
  
  object VariableDeclarationInit { def apply(id: java.lang.String, expression: Expression): VariableDeclarationInit = VariableDeclarationInit(id, -1, expression) }
  
  case class VariableDeclarationNoInit(val id: java.lang.String,
                                       val i: java.lang.Integer) extends VariableDeclaration {
    def accept[T](v: ASTVisitor[T]) = v.visitVariableDeclarationNoInit(this)
    override def toString = id + (if (i == -1) "" else ":" + i)
  }
  
  object VariableDeclarationNoInit { def apply(id: java.lang.String): VariableDeclarationNoInit = VariableDeclarationNoInit(id, -1) }
  
  trait AbstractASTVisitor[T] extends super.AbstractASTVisitor[T] {
    def visitVariableDeclarationInit(declaration: VariableDeclarationInit): T
    def visitVariableDeclarationNoInit(declaration: VariableDeclarationNoInit): T
  }
  
}