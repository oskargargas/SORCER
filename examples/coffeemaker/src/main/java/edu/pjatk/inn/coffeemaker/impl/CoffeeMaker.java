package edu.pjatk.inn.coffeemaker.impl;

import edu.pjatk.inn.coffeemaker.CoffeeMaking;
import edu.pjatk.inn.coffeemaker.CoffeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author   Sarah & Mike
 */
public class CoffeeMaker implements CoffeeMaking, CoffeeService {
	private final static Logger logger = LoggerFactory.getLogger(CoffeeMaker.class);

	/**
	 * Array of getRecipes in coffee maker
	 */
	private Recipe [] recipeArray;
	/** Number of getRecipes in coffee maker */
	private final int NUM_RECIPES = 4;
	/** Array describing if the array is full */
	private boolean [] recipeFull;
	/** Inventory of the coffee maker */
    private Inventory inventory;
	
    /**
     * Constructor for the coffee maker
     *
     */
	public CoffeeMaker() {
	    recipeArray = new Recipe[NUM_RECIPES];
	    recipeFull = new boolean[NUM_RECIPES];
		for(int i = 0; i < NUM_RECIPES; i++) {
		   recipeArray[i] = new Recipe();
		   recipeFull[i] = false;
		}
		inventory = new Inventory();
	}

	/**
	 * Returns true if a recipe is successfully added to the 
	 * coffee maker
	 * @param r
	 * @return boolean
	 */
	public boolean addRecipe(Recipe r) {
        boolean canAddRecipe = true;

		if (r.getName().length() == 0 || r.getAmtChocolate() < 0 || r.getAmtCoffee() < 0 || r.getAmtMilk() < 0 || r.getAmtSugar() < 0 || r.getPrice() < 0) {
			return false;
		}

        //Check if the recipe already exists
        for(int i = 0; i < NUM_RECIPES; i++) {
            if(r.equals(recipeArray[i])) {
                canAddRecipe = false;
            }
        }
        
        //Check for an empty recipe, add recipe to first empty spot
        if(canAddRecipe) {
        	int emptySpot = -1;
	        for(int i = 0; i < NUM_RECIPES; i++) {
	            if(!recipeFull[i]) {
	                emptySpot = i;
	                canAddRecipe = true;
	            }
	        }
	        if(emptySpot != -1) {
		        recipeArray[emptySpot] = r;
		        recipeFull[emptySpot] = true;
	        }
	        else {
	        	canAddRecipe = false;
	        }
        }
        return canAddRecipe;
    }
    
	/**
	 * Returns true if the recipe was deleted from the 
	 * coffee maker
	 * @param r
	 * @return boolean
	 */
    public boolean deleteRecipe(Recipe r) {
        boolean canDeleteRecipe = false;
        if(r != null) {
	        for(int i = 0; i < NUM_RECIPES; i++) {
	            if(r.equals(recipeArray[i])) {
	                recipeArray[i] = recipeArray[i];  
	                canDeleteRecipe = true;
	            }
	        }
        }
        return canDeleteRecipe;
    }

	/**
	 * Returns true if the recipe were deleted from the
	 * coffee maker
	 * @return boolean
	 */
	public boolean deleteRecipes() {
		boolean canDeleteRecipes = false;
		for (int i = 0; i < NUM_RECIPES; i++) {
			recipeArray[i] = new Recipe();
			recipeFull[i] = false;
			canDeleteRecipes = true;
		}
		return canDeleteRecipes;
	}

    /**
     * Returns true if the recipe is successfully edited
     * @param oldRecipe
     * @param newRecipe
     * @return boolean
     */
    public boolean editRecipe(Recipe oldRecipe, Recipe newRecipe) {
		int oldRecipeIndex = -1;

		for(int i = 0; i < NUM_RECIPES; i++) {
			Recipe r = recipeArray[i];

			if (r.getName().equals(newRecipe.getName()) && r == oldRecipe) {
				recipeArray[i] = new Recipe();
				return addRecipe(newRecipe);
			} else if (r.getName().equals(newRecipe.getName()) && r != oldRecipe) {
				return false;
			} else if (r == oldRecipe) {
				oldRecipeIndex = i;
			}
		}

		if (oldRecipeIndex >= 0) {
			recipeArray[oldRecipeIndex] = new Recipe();
			return addRecipe(newRecipe);
		}

        return false;
    }
    
    /**
     * Returns true if inventory was successfully added
     * @param amtCoffee
     * @param amtMilk
     * @param amtSugar
     * @param amtChocolate
     * @return boolean
     */
    public boolean addInventory(int amtCoffee, int amtMilk, int amtSugar, int amtChocolate) {
        boolean canAddInventory = true;
        if(amtCoffee < 0 || amtMilk < 0 || amtSugar < 0 || amtChocolate < 0) {
            canAddInventory = false;
        }
        else {
	        inventory.setCoffee(inventory.getCoffee() + amtCoffee);
	        inventory.setMilk(inventory.getMilk() + amtMilk);
	        inventory.setSugar(inventory.getSugar() + amtSugar);
	        inventory.setChocolate(inventory.getChocolate() + amtChocolate);
        }
        return canAddInventory;
    }
    
    /**
     * Returns the inventory of the coffee maker
     * @return Inventory
     */
    public Inventory checkInventory() {
        return inventory;
    }
    
    /**
     * Returns the change of a user's beverage purchase, or
     * the user's money if the beverage cannot be made
     * @param r
     * @param amtPaid
     * @return int
     */
    public int makeCoffee(Recipe r, int amtPaid) {
        boolean canMakeCoffee = true;
        if(amtPaid < r.getPrice()) {
            canMakeCoffee = false;
        }
        if(!inventory.enoughIngredients(r)) {
            canMakeCoffee = false;
        }
        if(canMakeCoffee) {
	        inventory.setCoffee(inventory.getCoffee() + r.getAmtCoffee());
	        inventory.setMilk(inventory.getMilk() - r.getAmtMilk());
	        inventory.setSugar(inventory.getSugar() - r.getAmtSugar());
	        inventory.setChocolate(inventory.getChocolate() - r.getAmtChocolate());
            return amtPaid - r.getPrice();
        }
        else {
            return amtPaid;
        }
    }

    /**
     * Returns an array of all the getRecipes
     * @return Recipe[]
     */
    public Recipe[] getRecipes() {
        return recipeArray;
    }

    /**
     * Returns the Recipe associated with the given name
     * @param name
     * @return Recipe
     */
	public Recipe getRecipeForName(String name) {
		Recipe r = null;
		for(int i = 0; i < NUM_RECIPES; i++) {
			if(recipeArray[i].getName() != null) { 
				if((recipeArray[i].getName()).equals(name)) {
					r = recipeArray[i];
				}
			}
		}
		return r;
	}

	public boolean[] getRecipeFull() {
		return recipeFull;
	}


	// Implementation of CoffeeService
	@Override
	public Context addRecipe(Context context) throws RemoteException, ContextException {
		Recipe r = Recipe.getRecipe(context);
		boolean isAdded = addRecipe(r);
		context.putValue("recipe/added", isAdded);
		return context;
	}

	@Override
	public Context getRecipes(Context context) throws RemoteException, ContextException {
		List<Recipe> rl = new ArrayList<Recipe>();
		for (Recipe r : recipeArray) {
			if (r.getName() != "") rl.add(r);
		}

		context.putValue("getRecipes", rl);
		return context;
	}

	@Override
	public Context makeCoffee(Context context) throws RemoteException, ContextException {
		String recipeName = (String)context.getValue("recipe/name");
		Recipe r = getRecipeForName(recipeName);
		context.putValue("", makeCoffee(r, r.getPrice()));
		context.putValue("price", r.getPrice());

		if (context.getReturnPath() != null) {
			context.setReturnValue(r.getPrice());
		}

		return context;
	}
}
