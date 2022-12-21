interface IFunction {
	double function(int n);
}

class test{
	public double sum(IFunction fun, int x) {
		double answer = 0;
		
		while(x > 0) {
			x += fun.function(x);
		}
		return answer;
	}
}

class Cos implements IFunction{

	@Override
	public double function(int x) {
		double answer = Math.sin(x);
		return answer;
	}
	
}

class Sin implements IFunction{

	@Override
	public double function(int x) {
		double answer = Math.cos(x);
		return answer;
	}
	
}