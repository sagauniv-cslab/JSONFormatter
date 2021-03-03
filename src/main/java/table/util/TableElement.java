package table.util;

import com.github.javaparser.ast.type.Type;

public abstract class TableElement<T extends TableElement<T>> implements Matcher, JSONTransformer {
	protected String name;
	private Type type;

	public TableElement(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public boolean thisType(Type type) {
		return this.type.equals(type);
	}

	public Type getType() {
		return type;
	}

	public boolean sameName(String name) {
		return this.name.equals(name);
	}

	public abstract String completeName();
}
