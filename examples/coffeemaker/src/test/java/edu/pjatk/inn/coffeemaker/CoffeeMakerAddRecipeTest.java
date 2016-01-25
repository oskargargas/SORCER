package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;
import sorcer.service.Exertion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static sorcer.eo.operator.*;

/**
 * @author Krzysztof Kakol s9209
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerAddRecipeTest {
	private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerAddRecipeTest.class);

	private CoffeeMaker coffeeMaker;
	private Inventory inventory;
	private Recipe espresso, mocha, macchiato, americano, noNameRecipe, noPriceRecipe, noCoffeeRecipe, noMilkRecipe, noSugerRecipe, noChocolateRecipe;

	@Before
	public void setUp() throws ContextException {
		coffeeMaker = new CoffeeMaker();
		inventory = coffeeMaker.checkInventory();

		espresso = new Recipe();
		espresso.setName("espresso");
		espresso.setPrice(50);
		espresso.setAmtCoffee(6);
		espresso.setAmtMilk(1);
		espresso.setAmtSugar(1);
		espresso.setAmtChocolate(0);

		mocha = new Recipe();
		mocha.setName("mocha");
		mocha.setPrice(100);
		mocha.setAmtCoffee(8);
		mocha.setAmtMilk(1);
		mocha.setAmtSugar(1);
		mocha.setAmtChocolate(2);

		macchiato = new Recipe();
		macchiato.setName("macchiato");
		macchiato.setPrice(40);
		macchiato.setAmtCoffee(7);
		macchiato.setAmtMilk(1);
		macchiato.setAmtSugar(2);
		macchiato.setAmtChocolate(0);

		americano = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);

		noNameRecipe = new Recipe();
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);

		noPriceRecipe = new Recipe();
		americano.setName("americano");
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);

		noCoffeeRecipe = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);

		noMilkRecipe = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);

		noSugerRecipe = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtChocolate(0);

		noChocolateRecipe = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);


	}

	@Test
	public void testAddRecipe() {
		assertTrue(coffeeMaker.addRecipe(espresso));
	}

	@Test
	public void testAddIdenticalRecipies() {
		assertTrue(coffeeMaker.addRecipe(espresso));
		assertFalse(coffeeMaker.addRecipe(espresso));
	}

	@Test
	public void testAddMoreThanThreeRecipies() {
		assertTrue(coffeeMaker.addRecipe(espresso));
		assertTrue(coffeeMaker.addRecipe(mocha));
		assertTrue(coffeeMaker.addRecipe(macchiato));
		assertFalse(coffeeMaker.addRecipe(americano));
	}

	@Test
	public void testAddNoNameRecipe() {
		assertFalse(coffeeMaker.addRecipe(noNameRecipe));
	}

	@Test
	public void testAddNoPriceRecipe() {
		assertFalse(coffeeMaker.addRecipe(noPriceRecipe));
	}

	@Test
	public void testAddNoCoffeeRecipe() {
		assertFalse(coffeeMaker.addRecipe(noCoffeeRecipe));
	}

	@Test
	public void testAddNoMilkRecipe() {
		assertFalse(coffeeMaker.addRecipe(noMilkRecipe));
	}

	@Test
	public void testAddNoSugarRecipe() {
		assertFalse(coffeeMaker.addRecipe(noSugerRecipe));
	}

	@Test
	public void testAddNoChocolateRecipe() {
		assertFalse(coffeeMaker.addRecipe(noChocolateRecipe));
	}


	@Test
	public void addRecipes() throws Exception {
		coffeeMaker.addRecipe(mocha);
		coffeeMaker.addRecipe(macchiato);
		coffeeMaker.addRecipe(americano);

		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
		assertEquals(coffeeMaker.getRecipeForName("macchiato").getName(), "macchiato");
		assertEquals(coffeeMaker.getRecipeForName("americano").getName(), "americano");
	}

}

