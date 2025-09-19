
public class Sumofeq {

    public static void main(String[] args) {
        int ans = 0;
        int maximum = 0;

        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (i + j <= 4 && i + j >= 0) {
                    ans = (3 * i) + (2 * j);
                    if (ans > maximum) 
                    {
                        maximum = ans;
                    }
                }
            }
        }
        System.out.println(maximum);
    }
}