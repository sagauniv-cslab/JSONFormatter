package visitor;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import table.ClassTable;
import table.MemberTable;
import table.element.ClassInfo;
import table.element.MemberInfo;
import table.util.JSON;
import table.util.JSONTransformer;

public class ProgramVisitor extends VoidVisitorAdapter<String> implements JSONTransformer {
	private ClassTable classTable;
	private MemberTable variableTable;
	private MemberTable methodTable;
	private String currentPackageName;
	private ClassInfo currentClass;

	public ProgramVisitor() {
		classTable = new ClassTable();
		variableTable = new MemberTable();
		methodTable = new MemberTable();
	}

	@Override
	public void visit(PackageDeclaration n, String arg) {
		currentPackageName = n.getNameAsString();
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, String arg) {
		String className = n.getNameAsString();
		currentClass = new ClassInfo(className, null, currentPackageName);
		if (n.getExtendedTypes().size() > 0) {
			n.getExtendedTypes().forEach(e -> {
				currentClass.addParent(e.getNameAsString());
			});
		}
		if (n.getImplementedTypes().size() > 0) {
			n.getImplementedTypes().forEach(i -> {
				currentClass.addParent(i.getNameAsString());
			});
		}
		classTable.addElement(currentClass);
		if (n.getFields().size() > 0) {
			n.getFields().forEach(f -> f.accept(this, arg));
		}
		if (n.getMethods().size() > 0) {
			n.getMethods().forEach(m -> m.accept(this, arg));
		}
	}

	@Override
	public void visit(MethodDeclaration n, String arg) {
		String methodName = n.getNameAsString();
		MemberInfo method = new MemberInfo(methodName, n.getType(), currentClass);
		methodTable.addElement(method);
		n.getBody().ifPresent(body -> {
			body.getStatements().forEach(stmt -> {
				stmt.ifExpressionStmt(exp -> exp.accept(this, arg));
			});
		});
	}

	@Override
	public void visit(ExpressionStmt n, String arg) {
		n.getChildNodes().forEach(node -> node.accept(this, arg));
	}

	@Override
	public void visit(VariableDeclarationExpr n, String arg) {
		n.getVariables().forEach(v -> {
			String variableName = v.getNameAsString();
			MemberInfo variable = new MemberInfo(variableName, v.getType(), currentClass);
			variableTable.addElement(variable);
		});
	}

	@Override
	public void visit(MethodCallExpr n, String arg) {
		System.out.println(n.getNameAsExpression());
	}

	@Override
	public String toJSON() {
		return new JSON(classTable, methodTable, variableTable).toJSON();
	}
}
