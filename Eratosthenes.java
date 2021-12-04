import java.util.ArrayList;

public class Eratosthenes {

    public static ArrayList<Integer> sieveEratosthenes(int n) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(int i = 2; i <= n; i++) {
            ans.add(i);
        }

        int p = 2;
        while (p <= n) {
            for(int k = ans.size()-1; k >= 0; k--) {
                if(ans.get(k) > p && ans.get(k)%p == 0) {
                    ans.remove(k);
                }
            }
            p++;
        }
        return ans;
    }

    //precondition: int even is an even number
    public static void goldbach(int even) {
        int first = 0;
        int second = 0;
        ArrayList<Integer> gb = sieveEratosthenes(even);
        for(int i = 0; i < gb.size(); i++) {
            for (int k = 0; k < gb.size(); k++) {
                if (even - gb.get(k) == gb.get(i)) {
                    first = gb.get(k);
                    second = gb.get(i);
                }
            }
        }
        System.out.println(even + " = " + first + " + " + second);
    }

    public static ArrayList<Integer> add(ArrayList<Integer> big1, ArrayList<Integer> big2) {
        int size = 0;
        int pair = 0;
        int carry = 0;
        ArrayList<Integer> sum = new ArrayList<Integer>();
        //System.out.println(big1.size());
        //System.out.println(big2.size());
        int first = big1.size() - 1;
        int second = big2.size() - 1;
        //System.out.println(first);
        //System.out.println(second);
        while(first >= 0 && second >= 0) {
            if (carry > 0) {
                pair = big1.get(first) + big2.get(second) + carry;
                carry = 0;
            } else {
                pair = big1.get(first) + big2.get(second);
            }
            if (pair >= 10) {
                pair = pair%10;
                carry = 1;
            }
            sum.add(0, pair);
            first--;
            second--;
        }
        int extra = 0;
        if (big1.size() == big2.size() && carry > 0) {
            sum.add(0, carry);
        } else if (big1.size() > big2.size()) {
            size = big1.size() - big2.size();
            for(int k = size -1; k >= 0; k--) {
                extra = big1.get(k);
                if (carry > 0) {
                    extra += carry;
                    carry = 0;
                    if (extra >= 10) {
                        extra = extra%10;
                        carry = 1;
                    }
                    sum.add(0, extra);
                } else {
                    sum.add(0, big1.get(k));
                }
            }
        } else {
            size = big2.size() - big1.size();
            for(int n = size -1; n >= 0; n--) {
                extra = big2.get(n);
                if (carry > 0) {
                    extra += carry;
                    carry = 0;
                    if (extra >= 10) {
                        extra = extra%10;
                        carry = 1;
                    }
                    sum.add(0, extra);
                } else {
                    sum.add(0, big2.get(n));
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //test adding BigInts
        ArrayList<Integer> one = new ArrayList<Integer>();
        ArrayList<Integer> two = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            one.add( (int) (Math.random()*10) );
        }
        for (int k = 0; k < 10; k++) {
            two.add( (int) (Math.random()*10) );
        }
        System.out.println(one);
        System.out.println(two);
        System.out.println(add(one, two));
        
        //test sieve of Eratosthenes
        System.out.println(sieveEratosthenes(100));
    }
}

