package me.tomassetti.symbolsolver.model.declarations;

import me.tomassetti.symbolsolver.model.TypeSolver;
import me.tomassetti.symbolsolver.model.usages.TypeUsage;


/**
 * @author Federico Tomassetti
 */
public interface ValueDeclaration extends Declaration {

    TypeUsage getType(TypeSolver typeSolver);

}
