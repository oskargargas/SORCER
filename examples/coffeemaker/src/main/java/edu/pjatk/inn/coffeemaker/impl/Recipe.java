package edu.pjatk.inn.coffeemaker.impl;

import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.io.Serializable;

/**
 * @author   Sarah & Mike
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
	 * @return   Returns the amtChocolate.
	 */
    public int getAmtChocolate() {
		return amtChocolate;
	}
    /**
	 * This method sets the amount of Chocolate in the Recipe.
	 * @param amtChocolate   The amtChocolate to set.
	 */
    public void setAmtChocolate(int amtChocolate) {
		if (amtChocolate >= 0) {
			this.amtChocolate = amtChocolate;
		} 
	}
    /**
	 * This method returns amount of Chocolate in the Recipe.
	 * @return   Returns the amtCoffee.
	 */
    public int getAmtCoffee() {
		return amtCoffee;
	}
    /**
	 * This method changes the amount of Coffee in the Recipe.
	 * @param amtCoffee   The amtCoffee to set.
	 */
    public void setAmtCoffee(int amtCoffee) {
		if (amtCoffee >= 0) {
			this.amtCoffee = amtCoffee;
		} 
	}
    /**
	 * This method shows the amount of Milk in the Recipe.
	 * @return   Returns the amtMilk.
	 */
    public int getAmtMilk() {
		return amtMilk;
	}
    /**
	 * This method sets the amount of Milk in the Recipe.
	 *  @param amtMilk   The amtMilk to set.
	 */
    public void setAmtMilk(int amtMilk) {
		if (amtMilk >= 0) {
			this.amtMilk = amtMilk;
		} 
	}
    /**
	 * This method returns the amount of Sugar in the Recipe.
	 * @return   Returns the amtSugar.
	 */
    public int getAmtSugar() {
		return amtSugar;
	}

    /**
	 * This method sets the amount of Sugar in the Recipe.
	 * @param amtSugar   The amtSugar to set.
	 */
    public void setAmtSugar(int amtSugar) {
		if (amtSugar >= 0) {
			this.amtSugar = amtSugar;
		} 
	}
    /**
	 * This method returns the name of the recipe
	 * @return   Returns the name.
	 */
    public String getName() {
		return name;
	}
    /**
	 * This method sets name for the recipe
	 * @param name   The name to set.
	 */
    public void setName(String name) {
    	if(name != null) {
    		this.name = name;
    	}
	}
    /**
	 * This method returns price of the recipe
	 * @return   Returns the price.
	 */
    public int getPrice() {
		return price;
	}
    /**
	 * This method sets price of the recipe
	 * @param price   The price to set.
	 */
    public void setPrice(int price) {
		if (price >= 0) {
			this.price = price;
		} 
	}

	/**
	 * This method checks if recipes have the same name
	 * @param r
	 * @return boolean
     */
    public boolean equals(Recipe r) {
        if((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }

    public String toString() {
    	return name;
    }


	/**
	 * This method returns Recipe from the given context
	 * @param context	Context to return the recipe from
	 * @return Returns the Recipe
	 * @throws ContextException Might be thrown during context.getValue operation
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
