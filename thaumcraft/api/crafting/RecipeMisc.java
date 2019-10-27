/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeMisc
/*    */ {
/*    */   MiscRecipeType type;
/*    */   ItemStack input;
/*    */   ItemStack output;
/*    */   
/*    */   public enum MiscRecipeType
/*    */   {
/* 16 */     SMELTING;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeMisc(ItemStack input, ItemStack output, MiscRecipeType type) {
/* 24 */     this.input = input;
/* 25 */     this.output = output;
/* 26 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public MiscRecipeType getType() { return this.type; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public void setType(MiscRecipeType type) { this.type = type; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   public ItemStack getInput() { return this.input; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setInput(ItemStack input) { this.input = input; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public ItemStack getOutput() { return this.output; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 68 */   public void setOutput(ItemStack output) { this.output = output; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\RecipeMisc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */