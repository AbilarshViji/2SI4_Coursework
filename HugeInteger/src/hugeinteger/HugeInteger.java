package hugeinteger;

import java.util.Random;

public class HugeInteger {

    public static void main(String[] args) {
        HugeInteger x = new HugeInteger("999999999999");
        HugeInteger y = new HugeInteger("99999");
        x.toString();
        y.toString();
        System.out.println(x.fastMultiply(y).toString());
    }

    private String hugeInt; //Store the number
    private boolean neg = false; //Store if its positive or negative
    private int length; //Store length of number, excluding negative sign

    public HugeInteger(String val) throws NumberFormatException {
        StringBuilder hugeIntSB = new StringBuilder();
        //Check if there is something in the string input
        if (val.length() == 0 || val.charAt(0) == '-' && val.length() == 1) {
            throw new NumberFormatException("Bad Input, not big enough");
        }
        length = val.length(); //set length

        int i = 0; //set for loop value

        //check if the input is negative
        if (val.charAt(0) == '-') {
            neg = true; //set HugeInt as negative
            i = 1; //skip negative sign in for loop
            length -= 1; //adjust length
        }
        //trimming the extra zeros
        int j = i;
        if (!neg) {
            while (val.charAt(j) == '0' && length > 1) {
                val = val.substring(1);
                length -= 1;
            }
        } else {
            while (val.charAt(j) == '0' && length > 1) {
                val = val.substring(0, j) + val.substring(j + 1);
                length -= 1;
            }
        }
        //check if the values are ints and add to the string
        try {
            for (; i < val.length(); i++) {
                Integer.parseInt(Character.toString(val.charAt(i)));
                hugeIntSB.append(val.charAt(i));
            }
            //if there is a non-int in the input string, catch error
        } catch (NumberFormatException e) {
            System.out.println("There was a non-int in your input (0-9)");
            throw e;
            //should not happen, here in case something goes wrong
        } catch (Exception e) {
            System.out.println("Figure out how this happened");
            throw e;
        }
        hugeInt = hugeIntSB.toString(); //save string

    }

    public HugeInteger(int n) {
        //check if int is creater then 1, throws exception if not
        if (n < 1) {
            throw new NumberFormatException("Int must be greater then 0");
        }
        //init stringbuilder and random
        StringBuilder hugeIntSB = new StringBuilder();
        Random rand = new Random();
        //generate first rand num from 1-9
        hugeIntSB.append(Integer.toString(rand.nextInt(9) + 1));
        //generate n random numbers from 0-9
        for (int i = 1; i < n; i++) {
            hugeIntSB.append(Integer.toString(rand.nextInt(10)));
        }
        //set instance variables
        neg = (rand.nextInt(2) == 1);
        hugeInt = hugeIntSB.toString();
        length = n;
    }

    public HugeInteger add(HugeInteger h) {
        //init SB for sum, and 2 HugeInts being added
        StringBuilder sumSB = new StringBuilder();
        StringBuilder hSB = new StringBuilder(h.hugeInt);
        StringBuilder thisSB = new StringBuilder(this.hugeInt);

        //adds the numbers
        if (h.length >= this.length) {
            //reverse the numbers since thats how you add on paper
            hSB.reverse();
            thisSB.reverse();
            boolean carry = false;
            //if both numbers are positive
            if (!h.neg && !this.neg) {
                //add numbers up to the length of the shorter number
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
                //go through remaining carry addition if it exists
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
                //(-a)+b = b-a, use subtract function
            } else if (h.neg && !this.neg) {
                HugeInteger num1 = new HugeInteger(h.hugeInt);
                return new HugeInteger("-" + num1.subtract(this).toString());
                //a +(-b)= a-b, use subtract function
            } else if (!h.neg && this.neg) {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                return h.subtract(num1);
                //if both numbers are negative
            } else {
                //same as adding above
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
                sumSB.append("-"); //added a negative sign to the number
            }
            sumSB.reverse();
            return new HugeInteger(sumSB.toString());
            //if this is bigger then h
        } else {
            //the same as above
            hSB.reverse();
            thisSB.reverse();
            boolean carry = false;
            if (!h.neg && !this.neg) {

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
                int count = h.length;
                while (carry == true) {
                    if (carry && count == this.length) {
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
                HugeInteger num1 = new HugeInteger(h.hugeInt);
                return this.subtract(num1);
            } else if (!h.neg && this.neg) {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                return new HugeInteger("-" + num1.subtract(h).toString());
            } else {
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
                int count = h.length;
                while (carry == true) {
                    if (carry && count == this.length) {
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

    //subtracts hugeInts (this - h)
    public HugeInteger subtract(HugeInteger h) {
        //similar setup as add
        StringBuilder subSB = new StringBuilder();
        StringBuilder hSB = new StringBuilder(h.hugeInt);
        StringBuilder thisSB = new StringBuilder(this.hugeInt);
        hSB.reverse();
        thisSB.reverse();
        boolean carry = false;
        if (this.length >= h.length) {
            if (!this.neg && !h.neg) {
                //weird case were lengths are equal and h is bigger then this
                if (h.length == this.length && this.compareTo(h) == -1) {
                    HugeInteger sub;
                    sub = h.subtract(this);
                    subSB.append("-");
                    subSB.append(sub.hugeInt);
                    return new HugeInteger(subSB.toString());
                }
                //typical case of this-h
                boolean multiCarry = false;
                int j;
                for (int i = 0; i < h.length; i++) {

                    if (Integer.parseInt(Character.toString(thisSB.charAt(i))) - Integer.parseInt(Character.toString(hSB.charAt(i))) >= 0) {
                        //System.out.println(Integer.parseInt(Character.toString(thisSB.charAt(i))) - Integer.parseInt(Character.toString(hSB.charAt(i))));
                        subSB.append(Integer.parseInt(Character.toString(thisSB.charAt(i))) - Integer.parseInt(Character.toString(hSB.charAt(i))));
                    } else {
                        carry = true;

                        if (Integer.parseInt(Character.toString(thisSB.charAt(i + 1))) == 0) {
                            j = i;
                            multiCarry = true;
                            while (multiCarry) {
                                if (Integer.parseInt(Character.toString(thisSB.charAt(j + 2))) == 0) {
                                    j++;
                                } else {
                                    multiCarry = false;
                                }
                            }
                            subSB.append(Integer.parseInt(Character.toString(thisSB.charAt(i))) - Integer.parseInt(Character.toString(hSB.charAt(i))) + 10);
                            thisSB.replace(j + 2, j + 3, Integer.toString(Character.getNumericValue(thisSB.charAt(j + 2)) - 1));
                            for (int k = i + 1; k < j + 2; k++) {
                                thisSB.replace(k, k + 1, "9");
                            }
                            carry = false;
                        } else {
                            carry = false;
                            thisSB.replace(i + 1, i + 2, Integer.toString(Integer.parseInt(Character.toString(thisSB.charAt(i + 1))) - 1));
                            subSB.append(Integer.parseInt(Character.toString(thisSB.charAt(i))) - Integer.parseInt(Character.toString(hSB.charAt(i))) + 10);
                        }
                    }
                    if (carry && Integer.parseInt(Character.toString(thisSB.charAt(i + 1))) != 0) {
                        thisSB.replace(i, i, Integer.toString(Integer.parseInt(Character.toString(thisSB.charAt(i + 1))) - 1));
                        carry = false;
                    }
                }
                subSB.append(thisSB.substring(h.length));
                subSB.reverse();
                return new HugeInteger(subSB.toString());
                // -this-h = -(this+h)
            } else if (this.neg && !h.neg) {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                HugeInteger num2 = new HugeInteger(h.hugeInt);
                HugeInteger sum;
                sum = num1.add(num2);
                subSB.append("-");
                subSB.append(sum.hugeInt);
                return new HugeInteger(subSB.toString());
                // this-(-h) = this+h
            } else if (!this.neg && h.neg) {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                HugeInteger num2 = new HugeInteger(h.hugeInt);
                HugeInteger sum;
                sum = num1.add(num2);
                return sum;
                //-this--h = h-this
            } else {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                HugeInteger num2 = new HugeInteger(h.hugeInt);
                HugeInteger sum;
                sum = num1.subtract(num2);
                if (this.compareTo(h) == -1) {
                    subSB.append("-");
                }
                subSB.append(sum.hugeInt);
                return new HugeInteger(subSB.toString());
            }
        } else {
            if (!this.neg && !h.neg) {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                HugeInteger num2 = new HugeInteger(h.hugeInt);
                HugeInteger sum;
                sum = num2.subtract(num1);
                subSB.append("-");
                subSB.append(sum.hugeInt);
                return new HugeInteger(subSB.toString());
            } else if (this.neg && !h.neg) {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                HugeInteger num2 = new HugeInteger(h.hugeInt);
                HugeInteger sum;
                sum = num1.add(num2);
                subSB.append("-");
                subSB.append(sum.hugeInt);
                return new HugeInteger(subSB.toString());
            } else if (!this.neg && h.neg) {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                HugeInteger num2 = new HugeInteger(h.hugeInt);
                HugeInteger sum;
                sum = num1.add(num2);
                return sum;
            } else {
                HugeInteger num1 = new HugeInteger(this.hugeInt);
                HugeInteger num2 = new HugeInteger(h.hugeInt);
                HugeInteger sum;
                sum = num1.subtract(num2);
                subSB.append("-");
                subSB.append(sum.hugeInt);
                return new HugeInteger(subSB.toString());
            }
        }
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
        if (neg) {
            return ("-" + this.hugeInt);
        }
        return this.hugeInt;
    }

    public HugeInteger multiply(HugeInteger h) {
        HugeInteger multiply = new HugeInteger("0");
        HugeInteger count = new HugeInteger("0");
        HugeInteger one = new HugeInteger("1");
        boolean flag = false;
        while (!(count.hugeInt.equals(h.hugeInt))) {
            multiply = multiply.add(this);
            count = count.add(one);
            flag = true;
        }
        if (flag == false) {
            while (!(count.hugeInt.equals(h.hugeInt))) {
                multiply = multiply.add(this);
                count = count.subtract(one);
                flag = true;
            }
        }
        return multiply;
    }

    public HugeInteger fastMultiply(HugeInteger h) {
        HugeInteger localThis = this;
        HugeInteger ten = new HugeInteger("10");
        boolean negative = false;
        int count = 0;
        int addZero = 0;
        if (h.hugeInt.equals("0") || this.hugeInt.equals("0")) {
            return new HugeInteger("0");
        }
        if (h.neg && this.neg) {
            h.neg = false;
            this.neg = false;
        } else if (h.neg || this.neg) {
            negative = true;
            h.neg = false;
            this.neg = false;
        }
        if (localThis.hugeInt.length() <= 4 && h.hugeInt.length() <= 4) {
            String product = Integer.toString(Integer.parseInt(localThis.hugeInt) * Integer.parseInt(h.hugeInt));
            return new HugeInteger(product);
        }
        if (this.length > h.length) {
            addZero = this.length - h.length;
            for (int i = 0; i < addZero; i++) {
                h = h.multiply(ten);
                count++;
            }
        } else if (h.length > this.length) {
            addZero = h.length - this.length;
            for (int i = 0; i < addZero; i++) {
                localThis = localThis.multiply(ten);
                count++;
            }
        }
        int mid = localThis.hugeInt.length() / 2;
        HugeInteger A = new HugeInteger(localThis.hugeInt.substring(0, mid));
        HugeInteger B = new HugeInteger(localThis.hugeInt.substring(mid));
        mid = h.hugeInt.length() / 2;
        HugeInteger C = new HugeInteger(h.hugeInt.substring(0, mid));
        HugeInteger D = new HugeInteger(h.hugeInt.substring(mid));

        HugeInteger AC = A.fM(C);
        HugeInteger BD = B.fastMultiply(D);
        HugeInteger ABCD = A.add(B).fastMultiply(C.add(D)).subtract(AC).subtract(BD);
        for (int i = 0; i < h.hugeInt.length(); i++) {
            AC = AC.multiply(ten);
        }
        for (int i = 0; i < mid; i++) {
            ABCD = ABCD.multiply(ten);
        }
        HugeInteger divideNum = AC.add(ABCD).add(BD);
        for (int i = 0; i < count; i++) {
            divideNum = divideNum.divide(ten);
        }
        return AC.add(ABCD).add(BD);
    }
    public HugeInteger fM(HugeInteger h){
        return this.fastMultiply(h);
    }
    public HugeInteger divide(HugeInteger h) {
        HugeInteger divide = new HugeInteger("0");
        HugeInteger count = new HugeInteger("0");
        HugeInteger one = new HugeInteger("1");
        if (h.hugeInt.equals("0")) {
            throw new NumberFormatException("Number cannot be 0");
        }
        HugeInteger num1, num2, ans;
        if (this.neg && h.neg) {
            num1 = new HugeInteger(this.hugeInt);
            num2 = new HugeInteger(h.hugeInt);
            return num1.divide(num2);
        } else if (this.neg || h.neg) {
            num1 = new HugeInteger(this.hugeInt);
            num2 = new HugeInteger(h.hugeInt);
            ans = num1.divide(num2);
            ans.neg = true;
            return ans;
        } else {
            while (this.compareTo(divide) == -1) {
                count = count.add(one);
                divide = divide.add(h);
            }
            count = count.subtract(one);
            return count;
        }
    }
}
