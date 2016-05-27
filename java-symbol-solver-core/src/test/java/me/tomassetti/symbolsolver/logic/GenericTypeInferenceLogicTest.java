package me.tomassetti.symbolsolver.logic;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javaslang.Tuple2;
import me.tomassetti.symbolsolver.model.resolution.TypeParameter;
import me.tomassetti.symbolsolver.model.resolution.TypeSolver;
import me.tomassetti.symbolsolver.model.typesystem.ReferenceTypeUsage;
import me.tomassetti.symbolsolver.model.typesystem.ReferenceTypeUsageImpl;
import me.tomassetti.symbolsolver.model.typesystem.TypeParameterUsage;
import me.tomassetti.symbolsolver.model.typesystem.TypeUsage;
import me.tomassetti.symbolsolver.reflectionmodel.ReflectionClassDeclaration;
import me.tomassetti.symbolsolver.resolution.typesolvers.JreTypeSolver;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class GenericTypeInferenceLogicTest {

    @Test
    public void inferGenericTypesTestEmptyCase() {
        assertEquals(Collections.emptyMap(), GenericTypeInferenceLogic.inferGenericTypes(Collections.emptyList()));
    }

    @Test
    public void inferGenericTypesTestSimpleCase() {
        TypeSolver typeSolver = new JreTypeSolver();
        ReferenceTypeUsage string = new ReferenceTypeUsageImpl(new ReflectionClassDeclaration(String.class, typeSolver), typeSolver);
        TypeParameter a = EasyMock.createMock(TypeParameter.class);
        EasyMock.expect(a.getName()).andReturn("A").anyTimes();
        EasyMock.replay(a);
        TypeParameterUsage aUsage = new TypeParameterUsage(a);
        assertEquals(ImmutableMap.of("A", string), GenericTypeInferenceLogic.inferGenericTypes(
                ImmutableList.of(new Tuple2<>(aUsage, string))));
    }

    @Test
    public void inferGenericTypesTestSimpleCaseWithTwoSubstitutions() {
        TypeSolver typeSolver = new JreTypeSolver();
        ReferenceTypeUsage string = new ReferenceTypeUsageImpl(new ReflectionClassDeclaration(String.class, typeSolver), typeSolver);
        ReferenceTypeUsage object = new ReferenceTypeUsageImpl(new ReflectionClassDeclaration(Object.class, typeSolver), typeSolver);
        TypeParameter a = EasyMock.createMock(TypeParameter.class);
        TypeParameter b = EasyMock.createMock(TypeParameter.class);
        TypeParameter c = EasyMock.createMock(TypeParameter.class);
        EasyMock.expect(a.getName()).andReturn("A").anyTimes();
        EasyMock.expect(b.getName()).andReturn("B").anyTimes();
        EasyMock.expect(c.getName()).andReturn("C").anyTimes();
        EasyMock.replay(a);
        EasyMock.replay(b);
        EasyMock.replay(c);
        TypeParameterUsage aUsage = new TypeParameterUsage(a);
        TypeParameterUsage bUsage = new TypeParameterUsage(b);
        TypeParameterUsage cUsage = new TypeParameterUsage(c);
        assertEquals(ImmutableMap.of("A", string, "B", object, "C", string), GenericTypeInferenceLogic.inferGenericTypes(
                ImmutableList.of(new Tuple2<>(aUsage, string),
                        new Tuple2<>(bUsage, object),
                        new Tuple2<>(cUsage, string))));
    }

    @Test
    public void inferGenericTypesTestSimpleCaseNoSubstitutions() {
        TypeSolver typeSolver = new JreTypeSolver();
        ReferenceTypeUsage string = new ReferenceTypeUsageImpl(new ReflectionClassDeclaration(String.class, typeSolver), typeSolver);
        ReferenceTypeUsage object = new ReferenceTypeUsageImpl(new ReflectionClassDeclaration(Object.class, typeSolver), typeSolver);
        assertEquals(Collections.emptyMap(), GenericTypeInferenceLogic.inferGenericTypes(
                ImmutableList.of(new Tuple2<>(object, string),
                        new Tuple2<>(object, object),
                        new Tuple2<>(object, string))));
    }
}
