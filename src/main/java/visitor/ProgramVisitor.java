package visitor;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import table.ClassTable;
import table.MemberTable;
import table.element.ClassInfo;

public class ProgramVisitor extends VoidVisitorAdapter<String> {
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
		currentClass = new ClassInfo(className, currentPackageName);
		if(n.getExtendedTypes().size() > 0) {
			n.getExtendedTypes().forEach(e -> {
				currentClass.addParent(e.getNameAsString());
			});
		}
		if(n.getImplementedTypes().size() > 0) {
			n.getImplementedTypes().forEach(i -> {
				currentClass.addParent(i.getNameAsString());
			});
		}
		classTable.addElement(currentClass);
		if(n.getFields().size() > 0) {
			n.getFields().forEach(f -> f.accept(this, arg));
		}
	}
}
