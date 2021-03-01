package table;

import java.util.Optional;

import table.util.ParentDetector;
import table.util.TableElement;

public class ClassTable implements ParentDetector {

	@Override
	public Optional<TableElement> getParent(String completeClassName) {
		return null;
	}

}
