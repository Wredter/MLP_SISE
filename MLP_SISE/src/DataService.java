import java.util.ArrayList;
import java.util.Collections;

public class DataService {
    ArrayList<ArrayList<Double>> testData = new ArrayList<>();
    public DataService(){
        for(int i = 1;i<=100;i++ ){
            ArrayList<Double> inputs = new ArrayList<>();
            inputs.add((double) i);
            testData.add(inputs);
        }
        Collections.shuffle(testData);
    }
    public ArrayList<ArrayList<Double>> getTestData() {
        return testData;
    }
}
