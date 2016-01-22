package edu.pjatk.inn.coffeemaker.impl;

import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.io.Serializable;

/**
 * This class works as container for recipe data.
 * It stores recipe name, amount of coffee, milk, sugar and chocolate are being stored.
 * Javadoc for this class is part of HA2 assignment.
 * @author   Sarah & Mike; Julia Osiak, Krzysztof Kąkol, Oskar Gargas
 */
public class Recipe implements Serializable {
    private String name;
    private int price;
    private int amtCoffee;
    private int amtMilk;
    private int amtSugar;
    private int amtChocolate;

    public Recipe() {
    	this.name = "";
    	this.price = 0;
    	this.amtCoffee = 0;
    	this.amtMilk = 0;
    	this.amtSugar = 0;
    	this.amtChocolate = 0;
    }

    /**
     * Returns the amount of chocolate used for making coffee.
	 * @return Returns the amount of chocolate used for making coffee.
	 */
    public int getAmtChocolate() {
		return amtChocolate;
	}

    /**
	 * Sets the amount of Chocolate in the Recipe.
	 * @param amtChocolate   The amtChocolate to set.
	 */
    public void setAmtChocolate(int amtChocolate) {
		if (amtChocolate >= 0) {
			this.amtChocolate = amtChocolate;
		}
	}

    /**
	 * Returns amount of Chocolate in the Recipe.
	 * @return   Returns the amtCoffee.
	 */
    public int getAmtCoffee() {
		return amtCoffee;
	}

    /**
	 * Changes the amount of Coffee in the Recipe.
	 * @param amtCoffee   The amtCoffee to set.
	 */
    public void setAmtCoffee(int amtCoffee) {
		if (amtCoffee >= 0) {
			this.amtCoffee = amtCoffee;
		}
	}

    /**
	 * Returns the amount of Milk in the Recipe.
	 * @return   Returns the amtMilk.
	 */
    public int getAmtMilk() {
		return amtMilk;
	}

    /**
	 * Sets the amount of Milk in the Recipe.
	 *  @param amtMilk   The amtMilk to set.
	 */
    public void setAmtMilk(int amtMilk) {
		if (amtMilk >= 0) {
			this.amtMilk = amtMilk;
		}
	}

    /**
	 * Returns the amount of Sugar in the Recipe.
	 * @return   Returns the amtSugar.
	 */
    public int getAmtSugar() {
		return amtSugar;
	}

    /**
	 * Sets the amount of Sugar in the Recipe.
	 * @param amtSugar   The amtSugar to set.
	 */
    public void setAmtSugar(int amtSugar) {
		if (amtSugar >= 0) {
			this.amtSugar = amtSugar;
		}
	}

    /**
	 * Returns the name of the recipe.
	 * @return   Returns the name.
	 */
    public String getName() {
		return name;
	}

    /**
	 * Sets name for the recipe.
	 * @param name   The name to set.
	 */
    public void setName(String name) {
    	if(name != null) {
    		this.name = name;
    	}
	}

    /**
	 * Returns price of the recipe.
	 * @return   Returns the price.
	 */
    public int getPrice() {
		return price;
	}

    /**
	 * Sets price of the recipe.
	 * @param price   The price to set.
	 */
    public void setPrice(int price) {
		if (price >= 0) {
			this.price = price;
		}
	}

	/**
	 * Checks if given Recipe is the same as Recipe the method is invoked on. (Check is done only by name.)
	 * @param r    Recipe to check equality against.
	 * @return boolean
     */
    public boolean equals(Recipe r) {
        if((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }

    /**
     * Converts Recipe to a String.
     * @return String
     */
    public String toString() {
    	return name;
    }

	/**
	 * Returns Recipe from the given context.
	 * @param context	Context to return the recipe from.
	 * @return Returns the Recipe.
	 * @throws ContextException might be thrown during context.getValue operation.
	 * @see ContextException
     */
	static public Recipe getRecipe(Context context) throws ContextException {
		Recipe r = new Recipe();
		r.name = (String)context.getValue("name");
		r.price = (int)context.getValue("price");
		r.amtCoffee = (int)context.getValue("amtCoffee");
		r.amtMilk = (int)context.getValue("amtMilk");
		r.amtSugar = (int)context.getValue("amtSugar");
		r.amtChocolate = (int)context.getValue("amtChocolate");
		return r;
	}

    /**
     * Creates Context from a given recipe.
     * @param recipe    Recipe to create Context from.
     * @return Context object created from a given recipe.
     * @throws ContextException might be thrown during context.putValue operation.
     * @see ContextException
     * @see Context
     */
    static public Context getContext(Recipe recipe) throws ContextException {
		Context cxt = new ServiceContext();
		cxt.putValue("name", recipe.getName());
		cxt.putValue("price", recipe.getPrice());
		cxt.putValue("amtCoffee", recipe.getAmtCoffee());
		cxt.putValue("amtMilk", recipe.getAmtMilk());
		cxt.putValue("amtSugar", recipe.getAmtSugar());
		cxt.putValue("amtChocolate", recipe.getAmtChocolate());
		return cxt;
	}

}
