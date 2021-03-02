package table.util;

public abstract class TableElement {
	protected String name;

	public TableElement(String name) {
		this.name = name;
	}

	public abstract String toJSON();
	public abstract boolean thisObject(String name);
	public abstract String completeName();
}
