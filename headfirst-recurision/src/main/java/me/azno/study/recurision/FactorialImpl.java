package me.azno.study.recurision;

/**
 * Created by yulei.ma on 2017/7/9.
 */
public class FactorialImpl implements IFactorial {
    public static void main(String[] args) {
        FactorialImpl fac = new FactorialImpl();
        int result = fac.calculate(9);
        System.out.println(result);
    }

    @Override
    public int calculate(int num) {
        return calculate(1, num, 1);
    }

    private int calculate(int now, int end, int result) {
        if (now < 1 || end < 1 || result < 1)
            return -1;
        if (now > end)
            return result;
        result *= now;
        now++;
        System.out.println(now + "," + result);
        return calculate(now, end, result);
    }

}
