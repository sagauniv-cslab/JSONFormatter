package table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import table.element.MemberInfo;
import table.util.Searcher;

public class MemberTable extends AbstractTable<MemberInfo> implements  Searcher {

	private List<MemberInfo> memberList;

	public MemberTable() {
		memberList = new ArrayList<>();
	}

	@Override
	public Optional<List<MemberInfo>> getParent(String completeClassName) {

		return null;
	}

	/**
	 * @return クラスの完全修飾名から，クラス中に定義しているメソッドのリスト
	 */
	@Override
	public List<MemberInfo> getMethods(String completeClassName) {
		List<MemberInfo> definedMethods = new ArrayList<>();
		for (MemberInfo member : memberList) {
			if(member.definedIn(completeClassName))
				definedMethods.add(member);
		}
		return definedMethods;
	}

	@Override
	public void addElement(MemberInfo element) {
		memberList.add(element);
	}

}
