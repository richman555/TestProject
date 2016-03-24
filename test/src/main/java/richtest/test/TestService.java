package richtest.test;

public interface TestService {
	
	double calculate(int a);
	
	default double sqrt(int a) {
		double y = Math.sqrt(a);
		System.out.println(y);
		return y;
	}

}
