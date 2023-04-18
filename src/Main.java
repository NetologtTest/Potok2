import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static int maxKey = 0;
    public static int maxValue = 0;
    public static final int ROUTES = 1000;

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static void main(String[] args) {

        for (int i = 0; i < ROUTES; i++) {

            new Thread(() -> {

                String s = generateRoute("RLRFR", 100);

                int count = 0;
                for (int j = 0; j < s.length(); j++) {
                    if ('R' == s.charAt(j)) {
                        count++;
                    }
                }
                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(count)) {
                        sizeToFreq.replace(count, sizeToFreq.get(count) + 1);

                        if (sizeToFreq.get(count) > maxValue) {
                            maxValue = sizeToFreq.get(count);
                            maxKey = count;
                        }
                    } else {
                        sizeToFreq.put(count, 1);
                    }

                }
            }).start();

        }
        System.out.println("Больше всего R " + maxKey + ", " + maxValue + " раз");
        System.out.println("Другие размеры:");
        sizeToFreq.forEach((key, value) -> System.out.println(key + "(" + value + " раз" + ")"));
    }
}
