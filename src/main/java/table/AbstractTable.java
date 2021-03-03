package table;

import table.util.ParentDetector;
import table.util.TableElement;

public abstract class AbstractTable<T extends TableElement<T>> implements ParentDetector<T>{

	public abstract void addElement(T element);

}
