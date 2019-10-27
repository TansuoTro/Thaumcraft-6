/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import thaumcraft.api.ThaumcraftInvHelper;
/*    */ 
/*    */ public class IngredientNBTTC
/*    */   extends Ingredient
/*    */ {
/*    */   private final ItemStack stack;
/*    */   
/*    */   public IngredientNBTTC(ItemStack stack) {
/* 14 */     super(new ItemStack[] { stack });
/* 15 */     this.stack = stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean apply(@Nullable ItemStack input) {
/* 21 */     if (input == null)
/* 22 */       return false; 
/* 23 */     return (this.stack.func_77973_b() == input.func_77973_b() && this.stack
/* 24 */       .func_77952_i() == input.func_77952_i() && 
/* 25 */       ThaumcraftInvHelper.areItemStackTagsEqualRelaxed(this.stack, input));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public boolean isSimple() { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\IngredientNBTTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */