package me.tomassetti.symbolsolver.resolution.reflection;

import me.tomassetti.symbolsolver.resolution.Context;
import me.tomassetti.symbolsolver.resolution.SymbolReference;
import me.tomassetti.symbolsolver.resolution.TypeSolver;
import me.tomassetti.symbolsolver.model.declarations.MethodDeclaration;
import me.tomassetti.symbolsolver.model.declarations.TypeDeclaration;
import me.tomassetti.symbolsolver.model.declarations.ValueDeclaration;
import me.tomassetti.symbolsolver.model.typesystem.TypeUsage;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Created by federico on 28/07/15.
 */
public class ClassOrInterfaceDeclarationContext implements Context {

  private Class<?> wrapped;

  public ClassOrInterfaceDeclarationContext(Class<?> clazz) {
    this.wrapped = clazz;
  }


  @Override
  public SymbolReference<ValueDeclaration> solveSymbol(String name, TypeSolver typeSolver) {
    for (Field field : wrapped.getFields()) {
      if (Modifier.isStatic(field.getModifiers()) && field.getName().equals(name)) {
        return SymbolReference.solved(new ReflectionFieldDeclaration(field));
      }
    }
    return SymbolReference.unsolved(ValueDeclaration.class);
  }

  @Override
  public SymbolReference<TypeDeclaration> solveType(String name, TypeSolver typeSolver) {
    throw new UnsupportedOperationException();
  }

  @Override
  public SymbolReference<MethodDeclaration> solveMethod(String name, List<TypeUsage> parameterTypes, TypeSolver typeSolver) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Context getParent() {
    throw new UnsupportedOperationException();
  }

}
