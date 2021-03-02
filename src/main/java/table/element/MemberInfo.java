package table.element;

import java.util.ArrayList;
import java.util.List;

import table.util.TableElement;

public class MemberInfo extends TableElement {
	private String completeClassName;
	private List<MemberInfo> refined;

	public MemberInfo(String name, ClassInfo classInfo) {
		super(name);
		this.completeClassName = classInfo.completeClassName();
		refined = new ArrayList<>();
	}

	public void addRefined(MemberInfo member) {
		refined.add(member);
	}

	public boolean definedIn(String completeClassName) {
		return this.completeClassName.equals(completeClassName);
	}

	public String toJSON() {
		return "";
	}
}
