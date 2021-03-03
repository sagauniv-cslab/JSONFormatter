package table.util;

import com.github.javaparser.ast.type.Type;

public interface Matcher {
	public boolean thisObject(String completeName);

	public boolean thisType(Type type);
}
