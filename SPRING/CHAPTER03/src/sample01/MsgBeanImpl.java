package sample01;

import lombok.Setter;

public class MsgBeanImpl implements MsgBean {
	// msgBeanImpl: TargetClass
	// showPrintBefore(), viewPrintBefore(), display(): JoinPoint
	// showPrintBefore(), viewPrintBefore(): PointCut

	@Setter
	private String str;
	
//	public void setStr(String str) {
//		this.str = str;
//	}
	
	
	
	@Override
	public void showPrintBefore() {
		// System.out.println("Before Trace");
		System.out.println("showPrintBefore 메세지: " + str); // 핵심코드
	}

	@Override
	public void viewPrintBefore() {
		// System.out.println("Before Trace");
		 try {
			Thread.sleep(1000); // 1초동안 sleep
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			System.out.println("viewPrintBefore 메세지: " + str); // 핵심코드
	}

	@Override
	public void showPrintAfter() {
		System.out.println("showPrintAfter 메세지: " + str); // 핵심코드		
	}

	@Override
	public void viewPrintAfter() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // 1초동안 sleep
		System.out.println("viewPrintAfter 메세지: " + str); // 핵심코드
	}

	@Override
	public String showPrint() {
		System.out.println("showPrint 메세지: " + str); // 핵심코드		
		return "Spring";
	}

	@Override
	public void viewPrint() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrint 메세지: " + str); // 핵심코드
	}
	
	
	
	@Override
	public void display() {
		System.out.println("display 메세지: " + str); // 핵심코드
	}

}
