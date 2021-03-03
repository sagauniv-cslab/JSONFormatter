package table.util;

import java.util.List;
import java.util.Optional;

public interface ParentDetector<T extends TableElement<T>> {
	public Optional<List<T>> getParent(String completeClassName);
}
