package class__;

//import pkg 시, *(wild card) 사용 자제
//static method import 시, method까지 적어야 함(어떤 pkg, class, method를 확인하기 위함이므로)
import static java.lang.Math.random;
import static java.lang.Math.pow;
import static java.lang.String.format;
import static java.lang.System.out;

public class ImportStatic {

	public static void main(String[] args) {
		out.println(random());
		out.println(pow(2, 5)); // 2^5
		out.println(format("%.2f", 45.5678));
	}
}
