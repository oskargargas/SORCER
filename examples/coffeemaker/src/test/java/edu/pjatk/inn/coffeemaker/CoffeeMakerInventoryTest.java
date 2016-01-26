package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;

import static org.junit.Assert.*;

@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerInventoryTest {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerInventoryTest.class);

    private CoffeeMaker coffeeMaker;
    private Inventory inventory;

    /**
     * Inventory may be added to the machine at any time. The types of inventory in the Coffee Maker are coffee,
     * milk, sugar, and chocolate. The inventory is measured in integer units. No inventory may be taken away from
     * the CoffeeMaker except by purchasing a beverage. Upon completion, a status message is printed and the Coffee
     * Maker is returned to the waiting state.
     */

    @Before
    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();
        inventory = coffeeMaker.checkInventory();
    }

    @Test
    public void testAddInventory1() throws ContextException {
        int coffeeAmount = inventory.getCoffee();
        int milkAmount = inventory.getMilk();
        int sugarAmount = inventory.getSugar();
        int chocolateAmount = inventory.getChocolate();

        coffeeMaker.addInventory(5, 3, 7, 2);

        assertEquals(coffeeAmount + 5, inventory.getCoffee());
        assertEquals(milkAmount + 3, inventory.getMilk());
        assertEquals(sugarAmount + 7, inventory.getSugar());
        assertEquals(chocolateAmount + 2, inventory.getChocolate());
    }

    @Test
    public void testAddInventory2() throws ContextException {
        int coffeeAmount = inventory.getCoffee();

        coffeeMaker.addInventory(-1, 0, 0, 0);

        assertEquals(coffeeAmount, inventory.getCoffee());
    }

    @Test
    public void testAddInventory3() throws ContextException {
        int coffeeAmount = inventory.getCoffee();
        int milkAmount = inventory.getMilk();

        coffeeMaker.addInventory(5, -1, 0, 0);

        assertEquals(coffeeAmount, inventory.getCoffee());
        assertEquals(milkAmount, inventory.getMilk());
    }

    @Test
    public void testAddInventory4() throws ContextException {
        int coffeeAmount = inventory.getCoffee();
        int milkAmount = inventory.getMilk();
        int sugarAmount = inventory.getSugar();

        coffeeMaker.addInventory(5, 3, -1, 0);

        assertEquals(coffeeAmount, inventory.getCoffee());
        assertEquals(milkAmount, inventory.getMilk());
        assertEquals(sugarAmount, inventory.getSugar());
    }

    @Test
    public void testAddInventory5() throws ContextException {
        int coffeeAmount = inventory.getCoffee();
        int milkAmount = inventory.getMilk();
        int sugarAmount = inventory.getSugar();
        int chocolateAmount = inventory.getChocolate();

        coffeeMaker.addInventory(5, 3, 7, -1);

        assertEquals(coffeeAmount, inventory.getCoffee());
        assertEquals(milkAmount, inventory.getMilk());
        assertEquals(sugarAmount, inventory.getSugar());
        assertEquals(chocolateAmount, inventory.getChocolate());
    }

    @Test @Ignore
    public void testAddInventory6() throws ContextException {
        // Don't know how to implement using JUnit.
        // Using ScalaTest it would look like: "coffeeMaker.addInventory("a", 0, 0, 0);" shouldNot compile
        // http://www.scalatest.org/user_guide/using_matchers#checkingThatCodeDoesNotCompile
    }

    @Test @Ignore
    public void testAddInventory7() throws ContextException {
        // Don't know how to implement using JUnit.
        // Using ScalaTest it would look like: "coffeeMaker.addInventory(0, "a", 0, 0);" shouldNot compile
        // http://www.scalatest.org/user_guide/using_matchers#checkingThatCodeDoesNotCompile
    }

    @Test @Ignore
    public void testAddInventory8() throws ContextException {
        // Don't know how to implement using JUnit.
        // Using ScalaTest it would look like: "coffeeMaker.addInventory(0, 0, "a", 0);" shouldNot compile
        // http://www.scalatest.org/user_guide/using_matchers#checkingThatCodeDoesNotCompile
    }

    @Test @Ignore
    public void testAddInventory9() throws ContextException {
        // Don't know how to implement using JUnit.
        // Using ScalaTest it would look like: "coffeeMaker.addInventory(0, 0, 0, "a");" shouldNot compile
        // http://www.scalatest.org/user_guide/using_matchers#checkingThatCodeDoesNotCompile
    }

}
