package me.tomassetti.symbolsolver.model.typesystem;

import com.github.javaparser.ast.type.WildcardType;

import me.tomassetti.symbolsolver.resolution.TypeSolver;


import java.util.Collections;
import java.util.List;

/**
 * Created by federico on 23/08/15.
 */
public class WildcardUsage implements TypeUsage {

    private WildcardType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WildcardUsage that = (WildcardUsage) o;

        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    public WildcardUsage(WildcardType type) {
        if (type == null) {
            throw new NullPointerException();
        }
        this.type = type;
    }

    @Override
    public String describe() {
        return type.toStringWithoutComments();
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
