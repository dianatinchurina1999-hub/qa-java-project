package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Test
    public void setBuns_shouldSetBunReference() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredient_shouldAddToList() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient_shouldRemoveByIndex() {
        Burger burger = new Burger();
        Ingredient i1 = mock(Ingredient.class);
        Ingredient i2 = mock(Ingredient.class);
        burger.ingredients.add(i1);
        burger.ingredients.add(i2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(i2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredient_shouldMoveFromIndexToNewIndex() {
        Burger burger = new Burger();
        Ingredient i1 = mock(Ingredient.class);
        Ingredient i2 = mock(Ingredient.class);
        Ingredient i3 = mock(Ingredient.class);
        burger.ingredients.add(i1);
        burger.ingredients.add(i2);
        burger.ingredients.add(i3);

        burger.moveIngredient(0, 2);

        assertSame(i2, burger.ingredients.get(0));
        assertSame(i3, burger.ingredients.get(1));
        assertSame(i1, burger.ingredients.get(2));
    }

    @Test
    public void getReceipt_shouldContainBunTwiceIngredientsAndPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(100f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(100f);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= sauce hot sauce ="));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("Price:"));


        int first = receipt.indexOf("(==== black bun ====)");
        int second = receipt.indexOf("(==== black bun ====)", first + 1);
        assertTrue(second > first);
    }
}

