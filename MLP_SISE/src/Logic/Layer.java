package Logic;

import java.util.ArrayList;
import java.util.Random;

public class Layer {
    public ArrayList<ArrayList<Double>> neurons;
    public Layer(int numberOfNeurons,int numberOfInputs){
        Random rng = new Random();
        neurons = new ArrayList<>();
        for(int i=0;i<numberOfNeurons;i++){
            ArrayList<Double> temp = new ArrayList<>();
            for(int j=0;j<numberOfInputs+1;j++){
                temp.add(rng.nextDouble());
            }
            neurons.add(temp);
        }
        //System.out.println(neurons);
    }
    public ArrayList<Double> feedForward(ArrayList<Double> input){
        ArrayList<Double> output = new ArrayList<>();
        for(ArrayList<Double> neuron : neurons){
            double pom =0;
            for(int i=0;i < input.size();i++){
                pom += input.get(i)*neuron.get(i);
            }
            pom += 1*neuron.get(neuron.size()-1);
            pom = ActivationFunction(pom);
            output.add(pom);
        }
        return output;
    }
    private Double ActivationFunction(Double input){
        return ((1d)/(1d+Math.exp(-input)));
    }
    private Double DerivativeOfActivationFunction(Double input){
        return ((ActivationFunction(input))*(1d-ActivationFunction(input)));
    }
}
