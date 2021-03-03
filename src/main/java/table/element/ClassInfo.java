package table.element;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.type.Type;

import table.util.TableElement;

public class ClassInfo extends TableElement<ClassInfo> {
	private String packageName;
	private List<String> parent;

	public ClassInfo(String name, Type type, String packageName) {
		super(name, type);
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

	public List<String> getParent() {
		return parent;
	}

	public boolean thisObject(String completeClassName) {
		return this.completeName().equals(completeClassName);
	}

	/**
	 * @return このsクラスの完全修飾名
	 */
	@Override
	public String completeName() {
		return String.join(".", packageName, name);
	}

	public String toJSON() {
		return "";
	}

}
