package table.element;

import java.util.ArrayList;
import java.util.List;

import table.util.TableElement;

public class ClassInfo extends TableElement {
	private String packageName;
	private List<String> parent;

	public ClassInfo(String name, String packageName) {
		super(name);
		parent = new ArrayList<>();
		this.packageName = packageName;
	}

	/**
	 * 親クラスの名前を追加する
	 * @param parentName 親クラスの完全修飾名
	 */
	public void addParent(String parentName) {
		parent.add(parentName);
	}

	public List<String> getParent(){
		return parent;
	}

	/**
	 * @return このsクラスの完全修飾名
	 */
	public String completeName() {
		return String.join(".", packageName, name);
	}

	public boolean thisObject(String completeClassName) {
		return this.completeName().equals(completeClassName);
	}

	public String toJSON() {
		return "";
	}
}
