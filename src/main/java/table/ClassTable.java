package table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import table.element.ClassInfo;

public class ClassTable extends AbstractTable<ClassInfo>{
	private List<ClassInfo> classList;

	public ClassTable() {
		classList = new ArrayList<>();
	}

	public boolean hasParent(String completeClassName) {
		for(ClassInfo classInfo : classList) {
			if(classInfo.thisObject(completeClassName))
				return classInfo.getParent().size() > 0;
		}
		return false;
	}

	@Override
	public Optional<List<ClassInfo>> getParent(String completeClassName) {
		return null;
	}

	@Override
	public void addElement(ClassInfo element) {
		classList.add(element);
	}

}
