package table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import table.element.ClassInfo;

public class ClassTable extends AbstractTable<ClassInfo> {
	private List<ClassInfo> classList;
	private Map<ClassInfo, List<String>> remainRelationship;

	public ClassTable() {
		classList = new ArrayList<>();
		remainRelationship = new HashMap<>();
	}

	public boolean hasParent(String completeClassName) {
		for (ClassInfo classInfo : classList) {
			if (classInfo.thisObject(completeClassName))
				return classInfo.getParentNames().size() > 0;
		}
		return false;
	}

	/**
	 * 完全修飾名が{@code completeClassName}クラスの親クラスのリストを返す
	 * @return 親クラスの{@code ClassInfo}オブジェクトのリスト<br>
	 * {@code completeClassName}で指定されるクラスがない，{@code completeClassName}の親クラスがない場合は，{@code Optional.empty()}
	 */
	@Override
	public Optional<List<ClassInfo>> getParent(String completeClassName) {
		List<ClassInfo> cList = new ArrayList<>();
		Optional<ClassInfo> optionalClassInfo = getClassInfo(completeClassName);
		ClassInfo targetClass;
		if (optionalClassInfo.isPresent() && existAllParent(completeClassName)) {
			// 完全修飾名が completeClassName のオブジェクトがあり，そのオブジェクトの親クラスがすべて存在している場合
			targetClass = optionalClassInfo.get();
			for (String parentName : targetClass.getParentNames())
				cList.add(getSameNameClassInfo(parentName).get());
			return Optional.of(cList);
		}
		return Optional.empty();
	}

	private boolean existAllParent(String completeClassName) {
		// classinfo オブジェクトがない場合はfalse
		Optional<ClassInfo> classInfo = getClassInfo(completeClassName);
		if (classInfo.isPresent() == false)
			return false;

		// classinfo が保持している親クラスの名前を一つずつ参照して，自分がすべて持っているかを確認する
		// 一つでもない場合はfalse
		for (String parentName : classInfo.get().getParentNames()) {
			if (exist(parentName) == false)
				return false;
		}
		return true;
	}

	private boolean exist(String name) {
		for (ClassInfo object : classList) {
			if (object.sameName(name))
				return true;
		}
		return false;
	}

	/**
	 * あとでサブクラスが継承した親クラスの完全修飾子を解決するためにいったん，親クラス名と，サブクラスのオブジェクトを保持する
	 * @apiNote 親クラスがまだ解析していない時に，親クラス名と，サブクラスの{@code ClassInfo}オブジェクトを渡す<br>
	 * すべてのJavaプログラムの解析が終わったら{@code resolveParentRelationship}メソッドをコールする
	 * @param parentName まだ解析していない親クラス名
	 * @param targetClass サブクラスのオブジェクト
	 */
	public void remindParentRelationship(ClassInfo targetClass, String parentName) {
		if (remainRelationship.get(targetClass) == null) {
			List<String> remaining = new ArrayList<>();
			remaining.add(parentName);
			remainRelationship.put(targetClass, remaining);
		} else {
			remainRelationship.get(targetClass).add(parentName);
		}
	}

	/**
	 * クラス間の親子関係を解決する
	 */
	public void resolveParentRelationship() {
		for (ClassInfo hasParentClass : remainRelationship.keySet()) {
			remainRelationship.get(hasParentClass).forEach(parentName -> {
				hasParentClass.addParentObject(getSameNameClassInfo(parentName).get());
			});
		}
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

	/**
	 * @param name
	 * @return 引数と同じ名前の{@code ClassInfo} オブジェクト<br>
	 * ない場合は，{@code Optional.empty()}
	 */
	public Optional<ClassInfo> getSameNameClassInfo(String name) {
		for (ClassInfo classInfo : classList) {
			if (classInfo.sameName(name))
				return Optional.of(classInfo);
		}
		return Optional.empty();
	}
}
