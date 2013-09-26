package hgm;

import hgm.asve.factor.XADDFactor;
import hgm.asve.factory.XADDFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Hadi M Afshar
 * Date: 11/09/13
 * Time: 1:29 AM
 */
public class XADDFactoryTest {
    @Test
    public void testXaddInstantiation() {
        Variable d = new Variable("d");
        Variable x1 = new Variable("x1");
        Variable x2 = new Variable("x2");

        XADDFactory factory = new XADDFactory();
        factory.putContinuousVariable(d, -10, 20);
        factory.putContinuousVariable(x1, -10, 20);
        factory.putContinuousVariable(x2, -10, 20);
        XADDFactor df = factory.putNewFactorWithContinuousVars("U(d,0,0,10)");
        XADDFactor x1f = factory.putNewFactorWithContinuousVars("0.05*U(x1,0,0,10) + 0.85*N(x1,d,2,2.5) + 0.1*T(x1,10,1,0)");
        XADDFactor x2f = factory.putNewFactorWithContinuousVars("0.05*U(x2,0,0,10) + 0.85*N(x2,d,2,2.5) + 0.1*T(x2,10,1,0)");
        System.out.println("df = " + df);
        System.out.println("x1f = " + x1f);
        System.out.println("x2f = " + x2f);
        System.out.println("df.getScopeVars() = " + df.getScopeVars());
        System.out.println("x1f.getScopeVars() = " + x1f.getScopeVars());
        System.out.println("x2f.getScopeVars() = " + x2f.getScopeVars());

        //**********  Test instantiation of XADDs
        System.out.println("***\n");

        System.out.println("x1f = " + x1f);
        System.out.println("x1f.getXADDNodeString() = " + x1f.getXADDNodeString());
        XADDFactor instantiatedX1f = x1f.instantiate(new HashSet<InstantiatedVariable>(Arrays.asList(
                new InstantiatedVariable(x1, "5"),
                new InstantiatedVariable(x2, "50")
        )));
        System.out.println("instantiatedX1f = " + instantiatedX1f);
        System.out.println("instantiatedX1f.getXADDNodeString() = " + instantiatedX1f.getXADDNodeString());

    }
}