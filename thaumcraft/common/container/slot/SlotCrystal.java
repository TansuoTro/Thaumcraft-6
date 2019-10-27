/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.common.items.resources.ItemCrystalEssence;
/*    */ 
/*    */ 
/*    */ public class SlotCrystal
/*    */   extends Slot
/*    */ {
/*    */   private Aspect aspect;
/*    */   
/*    */   public SlotCrystal(Aspect aspect, IInventory par2IInventory, int par3, int par4, int par5) {
/* 16 */     super(par2IInventory, par3, par4, par5);
/* 17 */     this.aspect = aspect;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public boolean func_75214_a(ItemStack stack) { return isValidCrystal(stack, this.aspect); }
/*    */ 
/*    */   
/*    */   public static boolean isValidCrystal(ItemStack stack, Aspect aspect) {
/* 27 */     return (stack != null && !stack.func_190926_b() && stack.func_77973_b() != null && stack
/* 28 */       .func_77973_b() instanceof ItemCrystalEssence && ((ItemCrystalEssence)stack
/* 29 */       .func_77973_b()).getAspects(stack).getAspects()[false] == aspect);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */