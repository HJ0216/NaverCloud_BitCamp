package interface_;

public class ComputeMain {

	public static void main(String[] args) {
		ComputeService computeService = new ComputeService();
		computeService.menu();
		System.out.println("Program Terminated");
	}
}
