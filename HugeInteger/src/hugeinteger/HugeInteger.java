package hugeinteger;

import java.util.Random;

public class HugeInteger {

    public static void main(String[] args) {
        HugeInteger x = new HugeInteger("5");
        HugeInteger y = new HugeInteger("9");
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
        length = val.length();

        int i = 0;
        if (val.charAt(0) == '-') {
            neg = true;
            i = 1;
            length -= 1;
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

        if (h.length >= this.length) {
            hSB.reverse();
            thisSB.reverse();
            boolean carry = false;
            if (!h.neg && !this.neg) {

                //TODO CHECK IF THIS SHOULD BE HERE OR ABOVE
                for (int i = 0; i < this.length; i++) {
                    if (carry == true) {
                        sumSB.append((Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i))) + 1) % 10);
                    } else {
                        sumSB.append((Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i)))) % 10);
                    }
                    if ((Integer) (Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i))) + (carry ? 1 : 0)) / 10 != 0) {
                        carry = true;
                    } else {
                        carry = false;
                    }
                }
                int count = this.length;
                while (carry == true) {
                    if (carry && count == h.length) {
                        sumSB.append("1");
                        carry = false;
                        break;
                    }
                    if (Integer.parseInt(Character.toString(hSB.charAt(count))) + 1 >= 10) {

                        sumSB.append((Integer.parseInt(Character.toString(hSB.charAt(count))) + 1) % 10);
                        count += 1;

                    } else {
                        sumSB.append(Integer.parseInt(Character.toString(hSB.charAt(count))) + 1);
                        count += 1;
                        carry = false;
                    }
                }
                sumSB.append(hSB.substring(count));
            } else if (h.neg && !this.neg) {
                return this.subtract(h);
            } else if (!h.neg && this.neg) {
                return h.subtract(this);
            } else {
                for (int i = 0; i < this.length; i++) {
                    if (carry == true) {
                        sumSB.append((Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i))) + 1) % 10);
                    } else {
                        sumSB.append((Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i)))) % 10);
                    }
                    if ((Integer) (Integer.parseInt(Character.toString(hSB.charAt(i))) + Integer.parseInt(Character.toString(thisSB.charAt(i))) + (carry ? 1 : 0)) / 10 != 0) {
                        carry = true;
                    } else {
                        carry = false;
                    }
                }
                int count = this.length;
                while (carry == true) {
                    if (carry && count == h.length) {
                        sumSB.append("1");
                        carry = false;
                        break;
                    }
                    if (Integer.parseInt(Character.toString(hSB.charAt(count))) + 1 >= 10) {

                        sumSB.append((Integer.parseInt(Character.toString(hSB.charAt(count))) + 1) % 10);
                        count += 1;

                    } else {
                        sumSB.append(Integer.parseInt(Character.toString(hSB.charAt(count))) + 1);
                        count += 1;
                        carry = false;
                    }
                }
                sumSB.append(hSB.substring(count));
                sumSB.append("-");
            }
            sumSB.reverse();
            return new HugeInteger(sumSB.toString());
        } else {
            hSB.reverse();
            thisSB.reverse();
            boolean carry = false;
            if (!h.neg && !this.neg) {

                //TODO CHECK IF THIS SHOULD BE HERE OR ABOVE
                for (int i = 0; i < h.length; i++) {
                    if (carry == true) {
                        sumSB.append((Integer.parseInt(Character.toString(thisSB.charAt(i))) + Integer.parseInt(Character.toString(hSB.charAt(i))) + 1) % 10);
                    } else {
                        sumSB.append((Integer.parseInt(Character.toString(thisSB.charAt(i))) + Integer.parseInt(Character.toString(hSB.charAt(i)))) % 10);
                    }
                    if ((Integer) (Integer.parseInt(Character.toString(thisSB.charAt(i))) + Integer.parseInt(Character.toString(hSB.charAt(i))) + (carry ? 1 : 0)) / 10 != 0) {
                        carry = true;
                    } else {
                        carry = false;
                    }
                }
                int count = this.length;
                while (carry == true) {
                    if (carry && count == h.length) {
                        sumSB.append("1");
                        carry = false;
                        break;
                    }
                    if (Integer.parseInt(Character.toString(thisSB.charAt(count))) + 1 >= 10) {

                        sumSB.append((Integer.parseInt(Character.toString(thisSB.charAt(count))) + 1) % 10);
                        count += 1;

                    } else {
                        sumSB.append(Integer.parseInt(Character.toString(thisSB.charAt(count))) + 1);
                        count += 1;
                        carry = false;
                    }
                }
                sumSB.append(thisSB.substring(count));
            } else if (h.neg && !this.neg) {
                return this.subtract(h);
            } else if (!h.neg && this.neg) {
                return h.subtract(this);
            } else {
                for (int i = 0; i < this.length; i++) {
                    if (carry == true) {
                        sumSB.append((Integer.parseInt(Character.toString(thisSB.charAt(i))) + Integer.parseInt(Character.toString(hSB.charAt(i))) + 1) % 10);
                    } else {
                        sumSB.append((Integer.parseInt(Character.toString(thisSB.charAt(i))) + Integer.parseInt(Character.toString(hSB.charAt(i)))) % 10);
                    }
                    if ((Integer) (Integer.parseInt(Character.toString(thisSB.charAt(i))) + Integer.parseInt(Character.toString(hSB.charAt(i))) + (carry ? 1 : 0)) / 10 != 0) {
                        carry = true;
                    } else {
                        carry = false;
                    }
                }
                int count = this.length;
                while (carry == true) {
                    if (carry && count == h.length) {
                        sumSB.append("1");
                        carry = false;
                        break;
                    }
                    if (Integer.parseInt(Character.toString(thisSB.charAt(count))) + 1 >= 10) {

                        sumSB.append((Integer.parseInt(Character.toString(thisSB.charAt(count))) + 1) % 10);
                        count += 1;

                    } else {
                        sumSB.append(Integer.parseInt(Character.toString(thisSB.charAt(count))) + 1);
                        count += 1;
                        carry = false;
                    }
                }
                sumSB.append(thisSB.substring(count));
                sumSB.append("-");
            }
            sumSB.reverse();
            return new HugeInteger(sumSB.toString());
        }
    }

    public HugeInteger subtract(HugeInteger h) {
        StringBuilder subSB = new StringBuilder();
        StringBuilder hSB = new StringBuilder(h.hugeInt);
        StringBuilder thisSB = new StringBuilder(this.hugeInt);
        hSB.reverse();
        thisSB.reverse();
        boolean carry = false;
        if (this.length >= h.length) {
            if (!this.neg && !h.neg) {
                for (int i = this.length - 1; i >= 0; i--) {
                    if (Integer.parseInt(Character.toString(thisSB.charAt(i))) - Integer.parseInt(Character.toString(hSB.charAt(i))) > 0) {
                        subSB.append(Integer.parseInt(Character.toString(thisSB.charAt(i))) - Integer.parseInt(Character.toString(hSB.charAt(i))));
                    } else {
                        carry = true;
                    }
                }
            }
        }
        subSB.reverse();
        return new HugeInteger(subSB.toString());
    }

    public int compareTo(HugeInteger h) {
        if (!h.neg && !this.neg) {
            if (this.length > h.length) {
                return 1;
            } else if (this.length < h.length) {
                return -1;
            } else {
                for (int i = 0; i < this.length; i++) {
                    if (this.hugeInt.charAt(i) > h.hugeInt.charAt(i)) {
                        return 1;
                    } else if (this.hugeInt.charAt(i) < h.hugeInt.charAt(i)) {
                        return -1;
                    }
                }
                return 0;
            }
        } else if (h.neg && !this.neg) {
            return 1;
        } else if (!h.neg && this.neg) {
            return -1;
        } else {
            if (this.length > h.length) {
                return -1;
            } else if (this.length < h.length) {
                return 1;
            } else {
                for (int i = 0; i < this.length; i++) {
                    if (this.hugeInt.charAt(i) > h.hugeInt.charAt(i)) {
                        return -1;
                    } else if (this.hugeInt.charAt(i) < h.hugeInt.charAt(i)) {
                        return 1;
                    }
                }
                return 0;
            }
        }
    }

    public String toString() {
        return this.hugeInt;
    }

    /*
public HugeInteger multiply(HugeInteger h) {

    }

    public HugeInteger divide(HugeInteger h) {

    }





     */
}
