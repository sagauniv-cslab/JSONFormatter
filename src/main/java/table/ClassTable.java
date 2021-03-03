package table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import table.element.ClassInfo;

public class ClassTable extends AbstractTable<ClassInfo> {
	private List<ClassInfo> classList;

	public ClassTable() {
		classList = new ArrayList<>();
	}

	public boolean hasParent(String completeClassName) {
		for (ClassInfo classInfo : classList) {
			if (classInfo.thisObject(completeClassName))
				return classInfo.getParent().size() > 0;
		}
		return false;
	}

	@Override
	public Optional<List<ClassInfo>> getParent(String completeClassName) {
		List<ClassInfo> cList = new ArrayList<>();
		// completeClassNameのClassInfoオブジェクトを取得
		if (getClassInfo(completeClassName).isPresent()) {
			if (getClassInfo(completeClassName).get().getParent().size() == 0)
				// 親クラスがない場合
				return Optional.empty();

		}

		return Optional.empty();
	}

	@Override
	public void addElement(ClassInfo element) {
		classList.add(element);
	}

	private Optional<ClassInfo> getClassInfo(String completeClassName) {
		for (ClassInfo classInfo : classList) {
			if (classInfo.thisObject(completeClassName))
				return Optional.of(classInfo);
		}
		return Optional.empty();
	}

	private Optional<List<ClassInfo>> getSameNameClassInfo(String name) {
		List<ClassInfo> parentList = new ArrayList<>();
		for (ClassInfo classInfo : classList) {
		}
		return null;
	}
}
