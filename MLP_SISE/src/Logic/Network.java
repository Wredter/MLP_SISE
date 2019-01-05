package Logic;

import Functions.Linear;
import Functions.Sigma;

import java.util.ArrayList;

public class Network {
    ArrayList<Layer> layers;
    public Network(int numberOfInputs,int numberOfOutputs,int numberOfHidenLayers,ArrayList<Integer> numberOfNeuronsInEachHidenLayer){
        layers = new ArrayList<>();
        layers.add(new Layer(numberOfNeuronsInEachHidenLayer.get(0),numberOfInputs,new Sigma()));
        for(int i=1;i<numberOfHidenLayers;i++){
            layers.add(new Layer(numberOfNeuronsInEachHidenLayer.get(i),numberOfNeuronsInEachHidenLayer.get(i-1),new Sigma()));
        }
        layers.add(new Layer(numberOfOutputs,numberOfNeuronsInEachHidenLayer.get(numberOfNeuronsInEachHidenLayer.size()-1),new Linear()));
    }
    public ArrayList<Double> FeedForward(ArrayList<Double> input){
        ArrayList<Double> resoultsOfLayer;
        resoultsOfLayer = layers.get(0).feedForward(input);
        for(int i = 1;i < layers.size();i++){
            resoultsOfLayer = layers.get(i).feedForward(resoultsOfLayer);
        }
        return resoultsOfLayer;
    }
}
