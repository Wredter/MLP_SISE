package Logic;

import Functions.Linear;
import Functions.Sigma;

import java.util.ArrayList;
import java.util.Collections;

public class Network {
    ArrayList<Layer> layers;
    Double learningRate = 0.001;
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
    public Double Error(ArrayList<Double> actualValues,ArrayList<Double> expectedValues){
        if(actualValues.size()!= expectedValues.size()){
            System.out.println("Ty kurwo spierdoliłeś obliczanie błędu tablice nie są jednakoych rozmiarów");
            return null;
        }
        Double resoult = 0d;
        for(int i = 0;i<actualValues.size();i++){
                resoult += Math.pow(actualValues.get(i) - expectedValues.get(i),2);
        }
        return resoult/2d;
    }
    public void Learn(ArrayList<Double> acualValues,ArrayList<Double> expectedValues){
        ArrayList<ArrayList<Double>> errorsInLayers = new ArrayList<>();
        //error for output layer
        ArrayList<Double> errorsL = new ArrayList<>();
        for(int i=0; i < layers.get(layers.size()-1).neurons.size();i++){
            errorsL.add((acualValues.get(i)-expectedValues.get(i)));//*layers.get(layers.size()-1).activationFunction.Derivative(layers.get(layers.size()-1).outputsOfNeurons.get(i)));
        }
        errorsInLayers.add(errorsL);
        for(int i = layers.size()-2;i >=0;i--){
            ArrayList<Double> errors = new ArrayList<>();
            for(int j = 0;j < layers.get(i).neurons.size();j++){
                Double sumB = 0d;
                for(int x = 0;x < layers.get(i+1).neurons.size();x++){
                    Double pomA = errorsInLayers.get(errorsInLayers.size()-1).get(x);
                    Double pomB = layers.get(i+1).neurons.get(x).get(j);
                    sumB += (pomA*pomB);
                }
                Double pomC = layers.get(i).activationFunction.Derivative(layers.get(i).outputsOfNeurons.get(j));
                Double pomD = sumB*pomC;
                errors.add(pomD);
            }
            errorsInLayers.add(errors);
        }
        Collections.reverse(errorsInLayers);
        //Mamy policzone błedy dla poszczególnych neuronów
        for(int i = 0;i < layers.size();i++){
            //Dla każdej warstwy
            for(int j =0;j < layers.get(i).neurons.size();j++){
                //Dla każdego neuronu
                for(int k = 0;k < layers.get(i).neurons.get(j).size();k++){
                    //Dla każdej wagi
                    Double newWeight;
                    Double x;
                    if(k!=layers.get(i).neurons.get(j).size()-1) {
                        newWeight = (-learningRate) * (layers.get(i).inputOfNeurons.get(k) * errorsInLayers.get(i).get(j));
                    }else{
                        newWeight = (-learningRate) * (errorsInLayers.get(i).get(j));
                    }
                    x = layers.get(i).neurons.get(j).get(k) + newWeight;
                    /*System.out.println(newWeight);
                    System.out.println(layers.get(i).neurons.get(j).get(k));
                    System.out.println(x);*/
                    layers.get(i).neurons.get(j).set(k,x);
                }
            }
        }

    }
}
/*
for(int i=layers.size()-2;i >=0;i--){
        //Dla każdej warstwy poza ostatnią
        ArrayList<Double> errors = new ArrayList<>();
        Double sumOfErrors = 0d;
        for(int j=0;j < layers.get(i+1).neurons.size();j++){
        //Dla każdego neuronu w (i+1)-tej warstwie
        for(int k=0;k < layers.get(i+1).neurons.get(j).size();k++){
        //Dla każdej wagi "k" j-tego neuronu w (i+1)-tej warstwie
        sumOfErrors += ((errorsInLayers.get(errorsInLayers.size()-1).get(j))*(layers.get(i+1).neurons.get(j).get(k)));
        }
        }
        for (int j=0;j < layers.get(i).neurons.size();j++){
        //Sumabłędu warstwy następnej pomnożona przez to co potrzeba
        errors.add(sumOfErrors*layers.get(i).activationFunction.Derivative(layers.get(i).outputsOfNeurons.get(j)));
        }
        errorsInLayers.add(errors);
        }*/
