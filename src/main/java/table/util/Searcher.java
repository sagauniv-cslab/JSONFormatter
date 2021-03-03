package table.util;

import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.type.Type;

import table.element.MemberInfo;

public interface Searcher {
	public List<MemberInfo> getMethods(String completeClassName);

	public Optional<Type> getType(String completeName);
}
