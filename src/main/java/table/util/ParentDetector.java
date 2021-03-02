package table.util;

import java.util.Optional;

public interface ParentDetector {
	public Optional<TableElement> getParent(String completeClassName);
}
