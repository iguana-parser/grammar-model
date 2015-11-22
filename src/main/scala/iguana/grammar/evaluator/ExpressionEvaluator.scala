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
package iguana.grammar.evaluator

import iguana.grammar.ast.ExpressionType
import java.util.ArrayList
import scala.collection.JavaConversions._

/**
 * @author Anastasia Izmaylova
 */
trait ExpressionEvaluatorType extends EvaluatorType with ExpressionType {
  
  trait Evaluator extends super.Evaluator with AbstractASTVisitor[java.lang.Object] {
    self: ASTVisitor[java.lang.Object] =>
      
    def visitBoolean(expression: Boolean) 
      = if (expression == TRUE) new java.lang.Boolean(true) else new java.lang.Boolean(false)
    
    def visitInteger(expression: Integer) = expression.value
    
    def visitReal(expression: Real) = expression.value
    
    def visitString(expression: String) = expression.value
    
    def visitTuple(expression: Tuple) = {
        val es: Iterable[Expression] = expression.expressions
        new ArrayList(es.map { e => e.accept(this) })
    }
    
    def visitName(expression: Name) = {
      val value = evaluatorContext.lookupVariable(expression.name)
      if (value == null) throw new RuntimeException("Undeclared variable: " + expression.name)
      value
    }
    
    def visitCall(expression: Call) = ???
    
    def visitAssignment(expression: Assignment) = ???
    
    def visitOr(expression: Or) = ???
    
    def visitAnd(expression: And) = ???
    
    def visitLess(expression: Less) = ???
    
    def visitLessThanEqual(expression: LessThanEqual) = ???
    
    def visitGreater(expression: Greater) = ???
    
    def visitGreaterThanEqual(expression: GreaterThanEqual) = ???
  }
  
}