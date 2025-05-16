package praktikum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Database;

@RunWith(Parameterized.class)
public class BunTest {

    @Parameterized.Parameter
    public String name;

    @Parameterized.Parameter(1)
    public float price;

    @Parameterized.Parameters
    public static Object[][] values() {
        return new Object[][] {
                {"black bun", 100},
                {"white bun", 200},
                {"red bun", 300}
        };
    }

    @Test
    public void getNameShouldReturnName(){
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceShouldReturnPrice(){
        Bun bun = new Bun(name, price);
        Assert.assertEquals(price, bun.getPrice(), 0);
    }
}
