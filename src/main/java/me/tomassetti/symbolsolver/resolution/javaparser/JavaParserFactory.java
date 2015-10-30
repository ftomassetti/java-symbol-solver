package me.tomassetti.symbolsolver.resolution.javaparser;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import me.tomassetti.symbolsolver.resolution.Context;
import me.tomassetti.symbolsolver.resolution.SymbolDeclarator;
import me.tomassetti.symbolsolver.resolution.TypeSolver;
import me.tomassetti.symbolsolver.resolution.javaparser.contexts.*;
import me.tomassetti.symbolsolver.resolution.javaparser.declarators.FieldSymbolDeclarator;
import me.tomassetti.symbolsolver.resolution.javaparser.declarators.NoSimboyDeclarator;
import me.tomassetti.symbolsolver.resolution.javaparser.declarators.ParameterSymbolDeclarator;
import me.tomassetti.symbolsolver.resolution.javaparser.declarators.VariableSymbolDeclarator;

/**
 * Created by federico on 28/07/15.
 */
public class JavaParserFactory {

  public static Context getContext(Node node) {
    if (node == null) {
      return null;
    } else if (node instanceof CompilationUnit) {
      return new CompilationUnitContext((CompilationUnit) node);
    } else if (node instanceof ForeachStmt) {
      return new ForechStatementContext((ForeachStmt) node);
    } else if (node instanceof ForStmt) {
      return new ForStatementContext((ForStmt) node);
    } else if (node instanceof LambdaExpr) {
      return new LambdaExprContext((LambdaExpr) node);
    } else if (node instanceof MethodDeclaration) {
      return new MethodContext((MethodDeclaration) node);
    } else if (node instanceof ConstructorDeclaration) {
      return new ConstructorContext((ConstructorDeclaration) node);
    } else if (node instanceof ClassOrInterfaceDeclaration) {
      return new ClassOrInterfaceDeclarationContext((ClassOrInterfaceDeclaration) node);
    } else if (node instanceof MethodCallExpr) {
      return new MethodCallExprContext((MethodCallExpr) node);
    } else if (node instanceof EnumDeclaration) {
      return new EnumDeclarationContext((EnumDeclaration) node);
    } else if (node instanceof FieldAccessExpr) {
      return new FieldAccessContext((FieldAccessExpr) node);
    } else if (node instanceof SwitchEntryStmt) {
      return new SwitchEntryContext((SwitchEntryStmt) node);
    } else if (node instanceof Statement) {
      return new StatementContext((Statement) node);
    } else {
      return getContext(node.getParentNode());
    }
  }

  public static SymbolDeclarator getSymbolDeclarator(Node node, TypeSolver typeSolver) {
    if (node instanceof FieldDeclaration) {
      return new FieldSymbolDeclarator((FieldDeclaration) node, typeSolver);
    } else if (node instanceof Parameter) {
      return new ParameterSymbolDeclarator((Parameter) node, typeSolver);
    } else if (node instanceof ExpressionStmt) {
      ExpressionStmt expressionStmt = (ExpressionStmt) node;
      if (expressionStmt.getExpression() instanceof VariableDeclarationExpr) {
        return new VariableSymbolDeclarator((VariableDeclarationExpr) (expressionStmt.getExpression()), typeSolver);
      } else {
        return new NoSimboyDeclarator(node, typeSolver);
      }
    } else if (node instanceof IfStmt) {
      return new NoSimboyDeclarator(node, typeSolver);
    } else if (node instanceof ForeachStmt) {
      ForeachStmt foreachStmt = (ForeachStmt) node;
      return new VariableSymbolDeclarator((VariableDeclarationExpr) (foreachStmt.getVariable()), typeSolver);
    } else {
      return new NoSimboyDeclarator(node, typeSolver);
    }
  }

}
