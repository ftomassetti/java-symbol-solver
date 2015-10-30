package me.tomassetti.symbolsolver.resolution.javaparser.declarators;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import me.tomassetti.symbolsolver.model.declarations.MethodDeclaration;
import me.tomassetti.symbolsolver.model.declarations.ValueDeclaration;
import me.tomassetti.symbolsolver.resolution.TypeSolver;
import me.tomassetti.symbolsolver.resolution.javaparser.declarations.JavaParserSymbolDeclaration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by federico on 28/07/15.
 */
public class FieldSymbolDeclarator extends AbstractSymbolDeclarator<FieldDeclaration> {


  public FieldSymbolDeclarator(FieldDeclaration wrappedNode, TypeSolver typeSolver) {
    super(wrappedNode, typeSolver);
  }

  @Override
  public List<ValueDeclaration> getSymbolDeclarations() {
    List<ValueDeclaration> symbols = new LinkedList<>();
    for (VariableDeclarator v : wrappedNode.getVariables()) {
      symbols.add(JavaParserSymbolDeclaration.field(v, typeSolver));
    }
    return symbols;
  }

  @Override
  public List<MethodDeclaration> getMethodDeclarations() {
    return Collections.emptyList();
  }
}
