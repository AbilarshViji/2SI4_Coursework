package hugeinteger;

import java.util.Random;

public class HugeInteger {

    public static void main(String[] args) {
        HugeInteger x = new HugeInteger("999");
        HugeInteger y = new HugeInteger("11");
        System.out.println(y.add(x).hugeInt);
    }

    private String hugeInt;
    private boolean neg = false;
    private int length;

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
        length = val.length();

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
        length = n;
    }

    public HugeInteger add(HugeInteger h) {
        StringBuilder sumSB = new StringBuilder();
        StringBuilder hSB = new StringBuilder(h.hugeInt);
        StringBuilder thisSB = new StringBuilder(this.hugeInt);
        hSB.reverse();
        thisSB.reverse();
        boolean carry = false;

        if (h.length > this.length) {
            if (!h.neg && !this.neg) {
                for (int i = this.length - 1; i >= 0; i--) {
                    //System.out.println((Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i)))) % 10);
                    if (carry == true) {
                        sumSB.append((Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i))) + 1) % 10);
                        carry = false;
                    } else {
                        sumSB.append((Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i)))) % 10);
                    }
                    if ((Integer) (Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i)))) / 10 != 0) {
                        carry = true;
                    }
                }
                if (carry == true) {
                    int lastCarry = (Integer.parseInt(Character.toString(hSB.charAt(this.length))) + 1);
                    if ((Integer) lastCarry/10 != 0){
                        sumSB.append(lastCarry%10);
                        sumSB.append("1");
                    }
                    else{
                        sumSB.append(lastCarry);
                    }
                    
                    sumSB.append(hSB.substring(this.length + 1));
                } else {
                    sumSB.append(hSB.substring(this.length));
                }
            }
        }
        sumSB.reverse();
        return new HugeInteger(sumSB.toString());
    }
/*
    public HugeInteger subtract(HugeInteger h) {
        StringBuilder subSB = new StringBuilder();
        StringBuilder hSB = new StringBuilder(h.hugeInt);
        StringBuilder thisSB = new StringBuilder(this.hugeInt);
        if (this.length > h.length) {
            if (!this.neg && !h.neg) {
                for (int i = this.length - 1; i >= 0; i--) {
                    
                }
            }
        }
    }
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
