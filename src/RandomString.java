import java.util.Random;

public class RandomString {

    char[] simbolsMassiv;
    StringBuilder stringBuilder;
    String resultString;
    Random random;

    RandomString(){
        simbolsMassiv = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        stringBuilder = new StringBuilder(20);
        random = new Random();
    }

    public String getResultString() {
        for(int i = 0; i < 6; i++){
            char c = simbolsMassiv[random.nextInt(simbolsMassiv.length)];
            stringBuilder.append(c);
        }
        resultString = stringBuilder.toString();
        return resultString;
    }
}
