import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DataService {
    ArrayList<ArrayList<Double>> testData = new ArrayList<>();
    public DataService(boolean isRandomized, int range){
        if(isRandomized) {
            for(int i = 0;i<range;i+= 2 ){
                ArrayList<Double> inputs = new ArrayList<>();
                Random rng = new Random();
                inputs.add((double) i + rng.nextDouble());
                testData.add(inputs);
            }
            Collections.shuffle(testData);
        }
        for(int i = 1;i<=range;i++ ){
            ArrayList<Double> inputs = new ArrayList<>();
            Random rng = new Random();
            inputs.add((double) i);
            testData.add(inputs);
        }
    }
    public static void DeleteFile(String path)
    {
        try{

            File file = new File(path);

            if(file.delete()){
                System.out.println(file.getName() + " zostal skasowany!");
            }else{
                System.out.println("Operacja kasowania sie nie powiodla.");
            }

        }catch(Exception e){

            e.printStackTrace();

        }
    }

    public ArrayList<ArrayList<Double>> getTestData() {
        return testData;
    }
}
