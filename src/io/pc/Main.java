package io.pc;

import java.util.logging.Logger;

//15.1
public class Main {
    private final static Logger logger = Logger.getLogger("io.pc");
    public static void main(String[] args) {
        int[] c = new int[11];
        c[1] = 1;
        c[2] = 5;
        c[3] = 8;
        c[4] = 9;
        c[5] = 10;
        c[6] = 17;
        c[7] = 17;
        c[8] = 20;
        c[9] = 24;
        c[10] = 30;

        int m = 11; //total length

        int p = 0;
        int sections = 0;
        for(int i = 1; i <= m; i++){
            int tmp = cut(i,m,c);
            if(tmp >= p) {
                logger.info(String.format("The biggest price of %d section(s) :  %d.",i,tmp));
                sections = i;// record sections
            }
            p = Math.max(tmp,p);
        }
        logger.info(String.format("The latest biggest price is %d when sections is %d.",p,sections));
    }

    /**
     *
     * @param a sections
     * @param b length of the section
     * @param c table of price
     * @return max price of specific section
     */
    public static int cut(int a, int b, int[] c){
        logger.info(String.format("current length of section is %d, need to split into %d elements",b,a));
        if(a > b || (a == 1 && b > 10)) {
            if(a > b){
                logger.warning(String.format("sections %d larger than length of sections %d.",a,b));
            }
            if(a == 1 && b > 10){
                logger.warning(String.format("can't give price because length %d greater that limit 10",b));
            }
            return 0;
        }
        else if (a == 1 && b <= 10) {
            logger.info(String.format("length is %d , and don't need to split, price is %d",b,c[b]));
            return c[b];
        }
        else {
            int p = 0;
            for(int i = 1; i <= b-1 && i <=10; i++){
                logger.info(String.format("job : current length %d is splitting into %d and %d.",b,i,b-i));

                int tmp = cut(a-1,b-i,c);
                int tmp2 = c[i] + tmp;
                if(tmp2 >= p){
                    logger.info(String.format("SUB: biggest price was consist of %d + %d = %d in length %d + %d, in %d sections",c[i],tmp,tmp2,i,b-i,a));
                }
                p = Math.max(tmp2 ,p);
            }
            return p;
        }
    }
}
