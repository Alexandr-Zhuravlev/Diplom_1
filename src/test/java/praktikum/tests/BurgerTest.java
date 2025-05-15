package praktikum.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    final float bunPrice = 100.f;
    final float ingredient1Price = 200.f;
    final float ingredient2Price = 300.f;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;

    @Before
    public void setUp(){
    }

    @Test
    public void setBunShouldInitializeBun(){
        Burger burger = new Burger();
        burger.setBuns(bun);

        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddNewIngredientToList(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);

        Assert.assertTrue(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientToList(){
        Burger burger = new Burger();
        burger.ingredients.add(ingredient1);
        burger.removeIngredient(0);

        Assert.assertFalse(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToList(){
        int index = 0;
        int newIndex = 1;
        Burger burger = new Burger();
        burger.ingredients.add(ingredient1);
        burger.ingredients.add(ingredient2);
        burger.moveIngredient(index, newIndex);

        Assert.assertEquals(ingredient1, burger.ingredients.get(newIndex));
        Assert.assertEquals(ingredient2, burger.ingredients.get(index));
    }

    @Test
    public void getPriceShouldReturnPrice(){
        float expectedPrice = bunPrice * 2;
        Burger burger = new Burger();
        burger.bun = bun;
        burger.ingredients = List.of(ingredient1, ingredient2);

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient1.getPrice()).thenReturn(ingredient1Price);
        Mockito.when(ingredient2.getPrice()).thenReturn(ingredient2Price);

        for (Ingredient ingredient : burger.ingredients) {
            expectedPrice += ingredient.getPrice();
        }

        Assert.assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptShouldReturnReceipt(){
        final String bunName = "black bun";
        final String sauce = "hot sauce";
        final String filling = "cutlet";
        final float price = 100.f;
        Burger burgerSpy = Mockito.spy(Burger.class);
        burgerSpy.ingredients = List.of(ingredient1, ingredient2);
        burgerSpy.bun = bun;

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn(sauce);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getName()).thenReturn(filling);
        Mockito.doReturn(price).when(burgerSpy).getPrice();

        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", burgerSpy.bun.getName()));

        for (Ingredient ingredient : burgerSpy.ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", burgerSpy.bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burgerSpy.getPrice()));

        Assert.assertEquals(receipt.toString(), burgerSpy.getReceipt());
    }
}
