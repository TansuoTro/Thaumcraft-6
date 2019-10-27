/*    */ package thaumcraft.common.lib.crafting;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class RecipeTripleMeatTreat
/*    */   extends IForgeRegistryEntry.Impl<IRecipe>
/*    */   implements IRecipe {
/*    */   public boolean func_77569_a(InventoryCrafting inv, World worldIn) {
/* 16 */     boolean sugar = false;
/* 17 */     ArrayList<Integer> meats = new ArrayList<Integer>();
/* 18 */     for (int a = 0; a < 3; a++) {
/* 19 */       for (int b = 0; b < 3; b++) {
/* 20 */         if (inv.func_70463_b(a, b) != null && !inv.func_70463_b(a, b).func_190926_b()) {
/* 21 */           ItemStack stack = inv.func_70463_b(a, b).func_77946_l();
/* 22 */           if (stack.func_77973_b() == Items.field_151102_aT && sugar) return false; 
/* 23 */           if (stack.func_77973_b() == Items.field_151102_aT && !sugar)
/* 24 */           { sugar = true;
/*    */              }
/*    */           
/* 27 */           else if (stack.func_77973_b() == ItemsTC.chunks)
/* 28 */           { if (meats.contains(Integer.valueOf(stack.func_77952_i())) || meats.size() >= 3) return false; 
/* 29 */             meats.add(Integer.valueOf(stack.func_77952_i())); }
/*    */           else
/*    */           
/* 32 */           { return false; } 
/*    */         } 
/*    */       } 
/* 35 */     }  return (sugar && meats.size() == 3);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public ItemStack func_77572_b(InventoryCrafting inv) { return new ItemStack(ItemsTC.tripleMeatTreat); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public ItemStack func_77571_b() { return new ItemStack(ItemsTC.tripleMeatTreat); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public boolean func_194133_a(int width, int height) { return (width * height >= 4); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\RecipeTripleMeatTreat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */