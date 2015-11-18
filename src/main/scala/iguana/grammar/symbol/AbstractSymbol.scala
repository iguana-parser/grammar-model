package iguana.grammar.symbol

/**
 * @author Anastasia Izmaylova
 */
trait AbstractSymbolType extends SymbolType {
  
  abstract class AbstractBuilder(val name: String,
                                 var preConditions: java.util.Set[Condition] = new java.util.HashSet(),
                                 var postConditions: java.util.Set[Condition] = new java.util.HashSet(),
                                 var label: String = "") extends SymbolBuilder {
    
    def setPreConditions(conditions: java.util.Set[Condition]) = {
      preConditions = conditions
      this
    }
      
    def setPostConditions(conditions: java.util.Set[Condition]) = {
      postConditions = conditions
      this
    }
      
    def setLabel(label: String) = {
      this.label = label
      this
    }
    
  }
  
}