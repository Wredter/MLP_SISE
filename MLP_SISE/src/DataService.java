import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class DataService {
    ArrayList<ArrayList<Double>> testData = new ArrayList<>();
    public DataService(boolean isRandomized){
        for(int i = 1;i<=100;i++ ){
            ArrayList<Double> inputs = new ArrayList<>();
            inputs.add((double) i);
            testData.add(inputs);
        }
        if(isRandomized) {
            Collections.shuffle(testData);
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
