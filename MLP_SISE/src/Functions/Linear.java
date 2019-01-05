package Functions;

public class Linear implements ActivationFunctions {
    @Override
    public Double ActivationFunction(Double input) {
        return input;
    }

    @Override
    public Double Derivative(Double input) {
        return 1d;
    }
}
