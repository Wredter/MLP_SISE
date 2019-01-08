import Logic.Layer;
import Logic.Network;
import Plot.Plot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        ////////////////////////////////////////DATAPREP/////////////////////////////////////////////////////////////////////////////
        String projPath = "D:\\UnityProjects\\MLP_SISE\\MLP_SISE";
        int numberOfEpocs = 50;
        Double sumaBledzikow;
        ArrayList<Integer> neuronsOnLayers = new ArrayList<>();
        neuronsOnLayers.add(1);
        //neuronsOnLayers.add(15);
        //neuronsOnLayers.add(10);
        DataService dataService = new DataService(true);
        DataService xpom = new DataService(false);
        ArrayList<Double> bledzik = new ArrayList<>();
        ArrayList<Double> bledzikipom = new ArrayList<>();
        ArrayList<Double> x = new ArrayList<>();
        for(int i =1;i<=100;i++){
            x.add((double)i);
        }
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> x1 = new ArrayList<>();
        for(int i =1;i<=100;i++){
            x1.add((double)i);
        }
        ArrayList<Double> y1 = new ArrayList<>();
        for(int i =0;i<x1.size();i++){
            y1.add(Math.sqrt(x1.get(i)));
        }
        Network siec = new Network(dataService.getTestData().get(0).size(),1,neuronsOnLayers.size(),neuronsOnLayers);
        //////////////////////////////////////////LEARNING/////////////////////////////////////////////////////////////////////////
        for(int j = 0; j<=numberOfEpocs;j++) {
            sumaBledzikow = 0d;
            bledzikipom.add((double)j);
            for (int i = 0; i < dataService.getTestData().size(); i++) {
                ArrayList<Double> expectedResoult = new ArrayList<>();
                ArrayList<Double> acualValues;
                acualValues = siec.FeedForward(dataService.testData.get(i));
                Double pom = Math.sqrt(dataService.getTestData().get(i).get(0));
                expectedResoult.add(pom);
                siec.Learn(acualValues, expectedResoult);
                sumaBledzikow += siec.Error(acualValues,expectedResoult);
            }
            sumaBledzikow/=(double)dataService.getTestData().size();
            bledzik.add(sumaBledzikow);
            Collections.shuffle(dataService.testData);
        }
        /////////////////////////////////PLOTS//////////////////////////////////////
        DataService.DeleteFile(projPath+"\\błąd.png");
        DataService.DeleteFile(projPath+"\\PlotTest_1.png");
        for(int i = 0;i<x.size();i++){
            y.add(siec.FeedForward(xpom.testData.get(i)).get(0));
        }
        Plot plot = Plot.plot(null).series("Siec",Plot.data().xy(x,y),null).series("poprawne",Plot.data().xy(x1,y1),null);;
        try {
            plot.save("PlotTest_1","png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Plot plot1 = Plot.plot((null)).series("Błąd",Plot.data().xy(bledzikipom,bledzik),null);
        try {
            plot1.save("błąd","png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
