package day08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// This annotation will use the alphabetical order or your DisplayName
// to order your test , since you have put any text in DisplayName
// you can play around with first character to change the order in any way you want
// JUNIT 5 ONLY FEATURE !!
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class MethodOrderByDisplayName {

    @DisplayName("A. Test This A")
    @Test
    public void test1(){

    }

    @DisplayName("Z. Test This Z")
    @Test
    public void test2(){

    }

    @DisplayName("M. Test This M")
    @Test
    public void test3(){

    }


}
