package praktikum.tests;

import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {

    final String name = "булка";
    final float price = 100;
    final Bun bun = new Bun(name, price);

    @Test
    public void getNameShouldReturnName(){
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceShouldReturnPrice(){
        Assert.assertEquals(price, bun.getPrice(), 0);
    }
}
