package table.util;

import table.ClassTable;
import table.MemberTable;

public class JSON implements JSONTransformer {

	private MemberTable methodTable;
	private MemberTable variableTable;
	private ClassTable classTable;

	public JSON(ClassTable classTable, MemberTable methodTable, MemberTable variableTable) {
		this.classTable = classTable;
		this.methodTable = methodTable;
		this.variableTable = variableTable;
	}

	@Override
	public String toJSON() {
		return null;
	}

}
