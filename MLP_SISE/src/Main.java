import Logic.Layer;
import Logic.Network;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        int numberOfEpocs = 1000;
        ArrayList<Integer> neuronsOnLayers = new ArrayList<>();
        neuronsOnLayers.add(20);
        neuronsOnLayers.add(20);
        neuronsOnLayers.add(20);
        DataService dataService = new DataService();
        ArrayList<Double> test = new ArrayList<>();
        test.add(4d);

        Network siec = new Network(dataService.getTestData().get(0).size(),1,neuronsOnLayers.size(),neuronsOnLayers);
        for(int j = 0; j<=numberOfEpocs;j++) {
            for (int i = 0; i < dataService.getTestData().size(); i++) {
                ArrayList<Double> expectedResoult = new ArrayList<>();
                Double pom = Math.sqrt(dataService.getTestData().get(i).get(0));
                expectedResoult.add(pom);
                siec.Learn(dataService.getTestData().get(i), expectedResoult);
            }
            Collections.shuffle(dataService.testData);
        }
        System.out.println(siec.FeedForward(test));
    }
}
