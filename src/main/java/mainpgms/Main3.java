/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpgms;

import com.pwolfgang.albebraiccalculus.types.PolyNumber;
import java.io.PrintStream;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Main3 {
    public static void main(String... args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        var p = new PolyNumber(2, 1, -3, 0, 1);
        var pOfXp1 = p.eval(new PolyNumber(1,1));
        System.out.println(p);
        System.out.println(pOfXp1);
        
    }
}
