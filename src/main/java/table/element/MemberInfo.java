package table.element;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.type.Type;

import table.util.TableElement;

public class MemberInfo extends TableElement<MemberInfo> {
	private String completeClassName;
	private List<MemberInfo> refined;

	public MemberInfo(String name, Type type, ClassInfo classInfo) {
		super(name, type);
		this.completeClassName = classInfo.completeName();
		refined = new ArrayList<>();
	}

	public void addRefined(MemberInfo member) {
		refined.add(member);
	}

	public String completeName() {
		return String.join(".", completeClassName, name);
	}

	public boolean definedIn(String completeClassName) {
		return this.completeClassName.equals(completeClassName);
	}

	public String toJSON() {
		return "";
	}

	@Override
	public boolean thisObject(String completeMethodName) {
		return this.completeName().equals(completeMethodName);
	}
}
