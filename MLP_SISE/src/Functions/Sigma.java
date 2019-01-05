package Functions;

public class Sigma implements ActivationFunctions {
    public Double ActivationFunction(Double input){
        return ((1d)/(1d+Math.exp(-input)));
    }
    public Double Derivative(Double input){
        return ((this.ActivationFunction(input))*(1d-this.ActivationFunction(input)));
    }
}
