package interface_;

public interface InterC extends InterA, InterB {
	// interface간 상속 가능
	// class는 다중상속이 불가능하지만, interface는 다중상속 가능
	// 단, 상속의 경우 class extends class, interface extends interface 만 가능(Mix X)
}
