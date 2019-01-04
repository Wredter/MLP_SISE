import Logic.Layer;
import Logic.Network;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        ArrayList<Integer> neuronsOnLayers = new ArrayList<>();
        neuronsOnLayers.add(2);
        neuronsOnLayers.add(3);
        neuronsOnLayers.add(1);
        ArrayList<Double> input = new ArrayList<>();
        input.add(4d);

        Network siec = new Network(input.size(),neuronsOnLayers.size(),neuronsOnLayers);
        System.out.println(siec.FeedForward(input));
    }
}
