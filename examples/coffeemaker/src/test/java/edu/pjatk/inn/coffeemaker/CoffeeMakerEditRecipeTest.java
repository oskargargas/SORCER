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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerEditRecipeTest {
	private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerEditRecipeTest.class);

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

		coffeeMaker.addRecipe(coffee);
	}

	@Test
	public void testEditRecipe1() {
		Recipe newRecipe = new Recipe();

		newRecipe.setName("Coffee");
		newRecipe.setPrice(50);
		newRecipe.setAmtCoffee(3);
		newRecipe.setAmtMilk(1);
		newRecipe.setAmtSugar(1);
		newRecipe.setAmtChocolate(0);

		assertTrue(coffeeMaker.editRecipe(coffee, newRecipe));
	}


	@Test
	public void testEditRecipe2() {
		Recipe newRecipe = new Recipe();

		newRecipe.setName("Coffee");
		newRecipe.setPrice(50);
		newRecipe.setAmtCoffee(3);
		newRecipe.setAmtMilk(1);
		newRecipe.setAmtSugar(1);
		newRecipe.setAmtChocolate(0);

		assertTrue(coffeeMaker.editRecipe(coffee, newRecipe));
	}


	@Test
	public void testEditRecipe3() {
		Recipe newRecipe = new Recipe();

		newRecipe.setName("Mocha");
		newRecipe.setPrice(-50);

		assertFalse(coffeeMaker.editRecipe(coffee, newRecipe));
	}

	@Test
	public void testEditRecipe4() {
		Recipe newRecipe = new Recipe();

		newRecipe.setName("Mocha");
		newRecipe.setPrice(60);
		newRecipe.setAmtCoffee(-3);

		assertFalse(coffeeMaker.editRecipe(coffee, newRecipe));
	}

	@Test
	public void testEditRecipe5() {
		Recipe newRecipe = new Recipe();

		newRecipe.setName("Mocha");
		newRecipe.setPrice(60);
		newRecipe.setAmtCoffee(3);
		newRecipe.setAmtMilk(-2);

		assertFalse(coffeeMaker.editRecipe(coffee, newRecipe));
	}

	@Test
	public void testEditRecipe6() {
		Recipe newRecipe = new Recipe();

		newRecipe.setName("Mocha");
		newRecipe.setPrice(60);
		newRecipe.setAmtCoffee(3);
		newRecipe.setAmtMilk(2);
		newRecipe.setAmtSugar(-2);

		assertFalse(coffeeMaker.editRecipe(coffee, newRecipe));
	}

	@Test
	public void testEditRecipe7() {
		Recipe newRecipe = new Recipe();

		newRecipe.setName("Mocha");
		newRecipe.setPrice(60);
		newRecipe.setAmtCoffee(3);
		newRecipe.setAmtMilk(2);
		newRecipe.setAmtSugar(3);
		newRecipe.setAmtChocolate(-3);

		assertFalse(coffeeMaker.editRecipe(coffee, newRecipe));
	}
}

