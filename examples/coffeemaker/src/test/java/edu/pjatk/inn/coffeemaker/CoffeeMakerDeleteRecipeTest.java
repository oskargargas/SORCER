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

import static org.junit.Assert.*;


@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerDeleteRecipeTest {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerDeleteRecipeTest.class);

    private CoffeeMaker coffeeMaker;
    private Recipe coffee;

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
    }

    @Test
    public void testDeleteRecipe1() {
        coffeeMaker.addRecipe(coffee);
        assertNotNull(coffeeMaker.getRecipeForName(coffee.getName()));

        boolean didDeleteRecipe = coffeeMaker.deleteRecipe(coffee);

        assertTrue(didDeleteRecipe);
        assertNull(coffeeMaker.getRecipeForName(coffee.getName()));
    }

    @Test
    public void testDeleteRecipe2() {
        boolean didDeleteRecipe = coffeeMaker.deleteRecipe(coffee);

        assertFalse(didDeleteRecipe);
    }
}

