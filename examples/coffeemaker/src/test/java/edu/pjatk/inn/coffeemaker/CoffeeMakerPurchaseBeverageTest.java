package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
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
public class CoffeeMakerPurchaseBeverageTest {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerPurchaseBeverageTest.class);

    private CoffeeMaker coffeeMaker;
    private Inventory inventory;
    private Recipe coffee;

    @Before
    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();
        inventory = coffeeMaker.checkInventory();

        coffee = new Recipe();
        coffee.setName("Coffee");
        coffee.setPrice(50);
        coffee.setAmtCoffee(3);
        coffee.setAmtMilk(1);
        coffee.setAmtSugar(1);
        coffee.setAmtChocolate(0);

        coffeeMaker.addRecipe(coffee);
    }

    @Test
    public void testPurchaseBeverage1() throws ContextException {
        int coffeeAmount = inventory.getCoffee();
        int milkAmount = inventory.getMilk();
        int sugarAmount = inventory.getSugar();
        int chocolateAmount = inventory.getChocolate();

        int amountEntered = 60;
        int change = coffeeMaker.makeCoffee(coffee, amountEntered);

        assertEquals(change, amountEntered - coffee.getPrice());
        assertEquals(coffeeAmount - coffee.getAmtCoffee(), inventory.getCoffee());
        assertEquals(milkAmount - coffee.getAmtMilk(), inventory.getMilk());
        assertEquals(sugarAmount - coffee.getAmtSugar(), inventory.getSugar());
        assertEquals(chocolateAmount - coffee.getAmtChocolate(), inventory.getChocolate());
    }

    @Test
    public void testPurchaseBeverage2() throws ContextException {
        int coffeeAmount = inventory.getCoffee();
        int milkAmount = inventory.getMilk();
        int sugarAmount = inventory.getSugar();
        int chocolateAmount = inventory.getChocolate();

        int amountEntered = 40;
        int change = coffeeMaker.makeCoffee(coffee, amountEntered);

        assertEquals(change, amountEntered);
        assertEquals(coffeeAmount, inventory.getCoffee());
        assertEquals(milkAmount, inventory.getMilk());
        assertEquals(sugarAmount, inventory.getSugar());
        assertEquals(chocolateAmount, inventory.getChocolate());
    }

    @Test @Ignore
    public void testPurchaseBeverage3() throws ContextException {
        // From what I understand described test case is identical with `testPurchaseBeverage1`.
        // Expected result should be the same and the one provided on the website is incorrect.
    }

}
