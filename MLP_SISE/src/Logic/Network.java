package Logic;

import java.util.ArrayList;

public class Network {
    ArrayList<Layer> layers;
    public Network(int numberOfInputs,int numberOfLayers,ArrayList<Integer> numberOfNeuronsInEachLayer){
        layers = new ArrayList<>();
        layers.add(new Layer(numberOfNeuronsInEachLayer.get(0),numberOfInputs));
        for(int i=1;i<numberOfLayers;i++){
            layers.add(new Layer(numberOfNeuronsInEachLayer.get(i),numberOfNeuronsInEachLayer.get(i-1)));
        }
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
