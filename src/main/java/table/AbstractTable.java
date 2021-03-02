package table;

import table.util.ParentDetector;
import table.util.TableElement;

public abstract class AbstractTable<T extends TableElement> implements ParentDetector<T>{

	public abstract void addElement(T element);

}
