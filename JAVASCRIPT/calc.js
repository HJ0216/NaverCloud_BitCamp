// 빈 객체 선언
// 자바와 달리 자료 타입 필요 X
var calc = {}

// 멤버변수 추가
calc.x = 0;
calc.y = 0;
/* java 표현
class calc{
	int x;
	int y
}
*/

// 멤버함수 추가
calc.setValue = function(p1, p2) {
	this.x = p1;
	this.y = p2;
}

/* java 표현
class calc{
public void setValue(int x, int y){
	public void setValue(int x, int y){
		this.x = x;
		this.y = y;
	}
}
}
*/


calc.plus = function() {
	return this.x + this.y;
}

/* java 표현
class calc{
public void setValue(int x, int y){
	public int plus(){
		return this.x + this.y;
	}
}
}
*/


calc.minus = function() {
	return this.x - this.y;
}

calc.result = function() {
	var value1 = this.plus();
	var value2 = this.minus();
	
	document.write("<p>덧셈: " + value1 + "</p>");
	document.write("<p>뺄셈: " + value2 + "</p>");
}
