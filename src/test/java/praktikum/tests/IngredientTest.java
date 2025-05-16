package praktikum.tests;

import org.junit.Assert;
import org.junit.Test;

import praktikum.Ingredient;
import praktikum.IngredientType;


public class IngredientTest {

    final IngredientType ingredientType = IngredientType.SAUCE;
    final String name = "hot sauce";
    final float price = 100;
    final Ingredient ingredient = new Ingredient(ingredientType, name, price);

    @Test
    public void getPriceShouldReturnPrice(){
        Assert.assertEquals(price, ingredient.getPrice(), 0);
    }

    @Test
    public void getNameShouldReturnName(){
        Assert.assertEquals(name, ingredient.getName());
    }

    @Test
    public void getTypeShouldReturnType(){
        Assert.assertEquals(ingredientType, ingredient.getType());
    }
}
