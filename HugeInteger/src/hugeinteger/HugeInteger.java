package hugeinteger;

import java.util.Random;

public class HugeInteger {

    public static void main(String[] args) {
        HugeInteger x = new HugeInteger("$");
    }
    public String hugeInt;
    public boolean neg = false;

    public HugeInteger(String val) throws NumberFormatException {
        StringBuilder hugeIntSB = new StringBuilder();

        //Check if there is something in the string input
        if (val.length() == 0 || val.charAt(0) == '-' && val.length() == 1) {
            throw new NumberFormatException("Bad Input, not big enough");
        }

        int i = 0;
        if (val.charAt(0) == '-') {
            neg = true;
            i = 1;
        }
        try {
            for (; i < val.length(); i++) {
                Integer.parseInt(Character.toString(val.charAt(i)));
                System.out.println(Character.getNumericValue(val.charAt(i)));
                hugeIntSB.append(val.charAt(i));
            }
        } catch (NumberFormatException e) {
            System.out.println("There was a non-int in your input (0-9)");
            throw e;
        } catch (Exception e) {
            System.out.println("Figure out how this happened");
            throw e;
        }
        hugeInt = hugeIntSB.toString();

    }

    public HugeInteger(int n) {
        if (n < 1) {
            throw new NumberFormatException("Int must be greater then 0");
        }
        StringBuilder hugeIntSB = new StringBuilder();
        Random rand = new Random();
        hugeIntSB.append(Integer.toString(rand.nextInt(9) + 1));
        for (int i = 1; i < n; i++) {
            hugeIntSB.append(Integer.toString(rand.nextInt(10)));
        }
        hugeInt = hugeIntSB.toString();
    }
    /*
    public HugeInteger add(HugeInteger h) {

    }

    public HugeInteger subtract(HugeInteger h) {

    }

    public HugeInteger multiply(HugeInteger h) {

    }

    public HugeInteger divide(HugeInteger h) {

    }

    public int compareTo(HugeInteger h) {

    }

    public String toString() {

    }
     */
}
