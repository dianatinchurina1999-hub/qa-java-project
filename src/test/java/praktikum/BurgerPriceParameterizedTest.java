package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    @Parameterized.Parameter(0)
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float ing1Price;

    @Parameterized.Parameter(2)
    public float ing2Price;

    @Parameterized.Parameter(3)
    public float expected;

    @Parameterized.Parameters(name = "bun={0}, ing1={1}, ing2={2} -> {3}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 0f, 0f, 200f},
                {50f, 10f, 40f, 150f},
                {1.5f, 2.5f, 3.0f, 8.5f}
        });
    }

    @Test
    public void getPrice_shouldReturnBunDoublePlusIngredientsSum() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getPrice()).thenReturn(ing1Price);

        Ingredient i2 = mock(Ingredient.class);
        when(i2.getPrice()).thenReturn(ing2Price);

        burger.addIngredient(i1);
        burger.addIngredient(i2);

        assertEquals(expected, burger.getPrice(), 0.0001f);
    }
}
