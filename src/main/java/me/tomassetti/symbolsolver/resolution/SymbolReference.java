package me.tomassetti.symbolsolver.resolution;

import me.tomassetti.symbolsolver.model.declarations.Declaration;
import me.tomassetti.symbolsolver.model.typesystem.TypeUsage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by federico on 28/07/15.
 */
public class SymbolReference<S extends Declaration> {

  private Optional<? extends S> correspondingDeclaration;
  private Map<String, TypeUsage> typeParametersByName = new HashMap<>();

  public static <S extends Declaration, S2 extends S> SymbolReference<S> solved(S2 symbolDeclaration) {
    return new SymbolReference(Optional.of(symbolDeclaration));
  }

  public static <S extends Declaration, S2 extends S> SymbolReference<S> unsolved(Class<S2> clazz) {
    return new SymbolReference(Optional.<S>empty());
  }

  @Override
  public String toString() {
    return "SymbolReference{" 
    + "correspondingDeclaration=" + correspondingDeclaration 
    + '}';
  }

  private SymbolReference(Optional<? extends S> correspondingDeclaration) {
    this.correspondingDeclaration = correspondingDeclaration;
  }

  public S getCorrespondingDeclaration() {
    if (!isSolved()) {
      throw new IllegalStateException();
    }
    return correspondingDeclaration.get();
  }

  public boolean isSolved() {
    return correspondingDeclaration.isPresent();
  }
}
