package table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import table.element.MemberInfo;
import table.util.ParentDetector;
import table.util.Searcher;
import table.util.TableElement;

public class MemberTable implements ParentDetector, Searcher {

	private List<MemberInfo> memberList;

	public MemberTable() {
		memberList = new ArrayList<>();
	}

	@Override
	public Optional<TableElement> getParent(String completeClassName) {
		return null;
	}

	/**
	 * @return クラスの完全修飾名から，クラス中に定義しているメソッドのリスト
	 */
	@Override
	public List<MemberInfo> getMethods(String completeClassName) {
		List<MemberInfo> definedMethods = new ArrayList<>();
		for (MemberInfo member : memberList) {

		}
		return null;
	}

}
