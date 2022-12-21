
public interface IOp {
	double compute(double leftOperand, double rightOperand); 
}

class Plus implements IOp{

	@Override
	public double compute(double leftOperand, double rightOperand) {
		return leftOperand + rightOperand;
	}
	
}

class Minus implements IOp{

	@Override
	public double compute(double leftOperand, double rightOperand) {
		return leftOperand - rightOperand;
	}
	
}

class Multiply implements IOp{

	@Override
	public double compute(double leftOperand, double rightOperand) {
		return leftOperand * rightOperand;
	}
	
}

class Divide implements IOp{

	@Override
	public double compute(double leftOperand, double rightOperand) {
		return leftOperand / rightOperand;
	}
	
}