package day10;

import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.Countries;

public class LombokTest {

    @Test
    public void test1(){
        Category c1 = new Category("12","abc");
        System.out.println("c1 = " + c1);

        Countries c2 = new Countries("AR","Argantina",12);
        System.out.println("c2 = " + c2);
        

    }
}
