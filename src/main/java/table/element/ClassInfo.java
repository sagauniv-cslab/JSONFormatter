package table.element;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.type.Type;

import table.util.TableElement;

public class ClassInfo extends TableElement<ClassInfo> {
	private String packageName;
	private List<String> parentNames;
	private List<ClassInfo> parents;

	public ClassInfo(String name, Type type, String packageName) {
		super(name, type);
		this.packageName = packageName;
		parentNames = new ArrayList<>();
		parents = new ArrayList<>();
	}

	/**
	 * 親クラスの名前を追加する<br>
	 * @apiNote すべてのクラスが解析し終わっていないので，親クラス名だけを渡す
	 * @param parentName 親クラスの名前
	 */
	public void addParentName(String parentName) {
		parentNames.add(parentName);
	}

	public List<String> getParentNames() {
		return parentNames;
	}
	
	public void addParentObject(ClassInfo parent) {
		parents.add(parent);
	}
	
	public List<ClassInfo> getParents(){
		return parents;
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
