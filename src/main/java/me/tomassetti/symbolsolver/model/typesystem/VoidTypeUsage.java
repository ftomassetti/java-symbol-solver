package me.tomassetti.symbolsolver.model.typesystem;


import me.tomassetti.symbolsolver.resolution.TypeSolver;


import java.util.Collections;
import java.util.List;

public class VoidTypeUsage implements TypeUsage {
    public static final TypeUsage INSTANCE = new VoidTypeUsage();

    private VoidTypeUsage() {
    }

    @Override
    public String describe() {
        return "void";
    }

    @Override
    public List<TypeUsage> parameters() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAssignableBy(TypeUsage other, TypeSolver typeSolver) {
        throw new UnsupportedOperationException();
    }

}
