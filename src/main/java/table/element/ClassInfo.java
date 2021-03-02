package table.element;

import table.util.TableElement;

public class ClassInfo extends TableElement {
	private String packageName;
	private String parentName;

	public ClassInfo(String name, String parentName, String packageName) {
		super(name);
		this.parentName = parentName;
		this.packageName = packageName;
	}

	/**
	 * @return このsクラスの完全修飾名
	 */
	public String completeClassName() {
		return String.join(".", packageName, name);
	}

	public String toJSON() {
		return "";
	}
}
