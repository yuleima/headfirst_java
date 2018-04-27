package me.azno.study.tmp.test;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class TestArrayQuote {
    private static final Logger logger = LoggerFactory.getLogger("test");

    @Test
    public void test() {
        int[] array = {1, 2, 3};
        Obj obj = new Obj();
        obj.setArray(array);
        obj.setArrayByCopy(array);
        array[2] = 4;

        Assert.assertNotEquals(array[2], obj.getArray()[2]);
        // obj.sout();

    }

    class Obj {
        private int[] array;

        public Obj() {

        }

        public void setArray(int[] array) {
            this.array = array;
        }

        public int[] getArray() {
            return array;
        }

        public void setArrayByCopy(int[] array) {
            this.array = (array != null) ? Arrays.copyOf(array, array.length) : null;
        }

        private void sout() {
            for (int i : array) {
                logger.info("{}", i);
            }
        }
    }


}
