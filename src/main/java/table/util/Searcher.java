package table.util;

import java.util.List;

import table.element.MemberInfo;

public interface Searcher {
	public List<MemberInfo> getMethods(String completeClassName);
}
