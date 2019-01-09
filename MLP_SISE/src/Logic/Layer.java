package Logic;



import Functions.ActivationFunctions;

import java.util.ArrayList;
import java.util.Random;

public class Layer {
    public ArrayList<ArrayList<Double>> neurons;
    public ActivationFunctions activationFunction;
    public ArrayList<Double> outputsOfNeurons;
    public ArrayList<Double> inputOfNeurons;
    public Layer(int numberOfNeurons, int numberOfInputs, ActivationFunctions activationFunction){
        this.activationFunction = activationFunction;
        Random rng = new Random();
        outputsOfNeurons = new ArrayList<>();
        neurons = new ArrayList<>();
        for(int i=0;i<numberOfNeurons;i++){
            ArrayList<Double> temp = new ArrayList<>();
            for(int j=0;j<numberOfInputs;j++){
                temp.add((rng.nextDouble()-0.5)*2);
            }
            temp.add((rng.nextDouble()*0.5)*2);
            neurons.add(temp);
        }
        //System.out.println(neurons);
    }
    public ArrayList<Double> feedForward(ArrayList<Double> input){
        ArrayList<Double> output = new ArrayList<>();
        outputsOfNeurons.clear();
        inputOfNeurons = input;
        for(ArrayList<Double> neuron : neurons){
            double pom =0;
            for(int i=0;i < input.size();i++){
                //outputsOfNeurons.add(input.get(i)*neuron.get(i));
                pom += input.get(i)*neuron.get(i);
            }
            //outputsOfNeurons.add(1*neuron.get(neuron.size()-1));
            pom += neuron.get(neuron.size()-1);
            outputsOfNeurons.add(pom);
            pom = activationFunction.ActivationFunction(pom);
            output.add(pom);
        }
        return output;
    }


}
