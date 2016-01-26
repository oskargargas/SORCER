package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerAddRecipeTest {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerAddRecipeTest.class);

    private CoffeeMaker coffeeMaker;
    private Recipe coffee, latte, hotChocolate, mocha;

    @Before
    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();

        coffee = new Recipe();
        coffee.setName("Coffee");
        coffee.setPrice(50);
        coffee.setAmtCoffee(3);
        coffee.setAmtMilk(1);
        coffee.setAmtSugar(1);
        coffee.setAmtChocolate(0);

        latte = new Recipe();
        latte.setName("Latte");
        latte.setPrice(60);
        latte.setAmtCoffee(3);
        latte.setAmtMilk(3);
        latte.setAmtSugar(2);
        latte.setAmtChocolate(0);

        hotChocolate = new Recipe();
        hotChocolate.setName("Hot Chocolate");
        hotChocolate.setPrice(60);
        hotChocolate.setAmtCoffee(0);
        hotChocolate.setAmtMilk(2);
        hotChocolate.setAmtSugar(2);
        hotChocolate.setAmtChocolate(3);

        mocha = new Recipe();
        mocha.setName("Mocha");
        mocha.setPrice(60);
        mocha.setAmtCoffee(3);
        mocha.setAmtMilk(2);
        mocha.setAmtSugar(2);
        mocha.setAmtChocolate(3);
    }

    @Test
    public void addRecipe1() {
        coffeeMaker.addRecipe(coffee);

        assertEquals(coffeeMaker.getRecipeForName("Coffee").getName(), "Coffee");
    }

    @Test
    public void addRecipe2() {
        coffeeMaker.addRecipe(coffee);

        assertFalse(coffeeMaker.addRecipe(coffee));
    }

    @Test
    public void addRecipe3() {
        mocha = new Recipe();
        mocha.setName("Mocha");
        mocha.setPrice(-50);

        assertFalse(coffeeMaker.addRecipe(mocha));
    }

    @Test
    public void addRecipe4() {
        mocha = new Recipe();
        mocha.setName("Mocha");
        mocha.setPrice(60);
        mocha.setAmtCoffee(-3);

        assertFalse(coffeeMaker.addRecipe(mocha));
    }

    @Test
    public void addRecipe5() {
        mocha = new Recipe();
        mocha.setName("Mocha");
        mocha.setPrice(60);
        mocha.setAmtCoffee(3);
        mocha.setAmtMilk(-2);

        assertFalse(coffeeMaker.addRecipe(mocha));
    }

    @Test
    public void addRecipe6() {
        mocha = new Recipe();
        mocha.setName("Mocha");
        mocha.setPrice(60);
        mocha.setAmtCoffee(3);
        mocha.setAmtMilk(2);
        mocha.setAmtSugar(-2);

        assertFalse(coffeeMaker.addRecipe(mocha));
    }

    @Test
    public void addRecipe7() {
        mocha = new Recipe();
        mocha.setName("Mocha");
        mocha.setPrice(60);
        mocha.setAmtCoffee(3);
        mocha.setAmtMilk(2);
        mocha.setAmtSugar(2);
        mocha.setAmtChocolate(-3);

        assertFalse(coffeeMaker.addRecipe(mocha));
    }

    @Test
    public void addRecipe13() {
        assertTrue(coffeeMaker.addRecipe(mocha));
    }

    @Test
    public void addRecipe14() {
        assertTrue(coffeeMaker.addRecipe(mocha));
        assertTrue(coffeeMaker.addRecipe(latte));
    }

}
