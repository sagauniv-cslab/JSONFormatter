package table;

import java.util.List;
import java.util.Optional;

import table.element.MemberInfo;
import table.util.ParentDetector;
import table.util.Searcher;
import table.util.TableElement;

public class MethodTable implements ParentDetector, Searcher {

	@Override
	public Optional<TableElement> getParent(String completeClassName) {
		return null;
	}

	@Override
	public List<MemberInfo> getMethods(String completeClassName) {
		return null;
	}

}
