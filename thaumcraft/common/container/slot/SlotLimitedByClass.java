/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotLimitedByClass
/*    */   extends Slot
/*    */ {
/* 10 */   Class clazz = Object.class;
/* 11 */   int limit = 64;
/*    */ 
/*    */   
/*    */   public SlotLimitedByClass(Class clazz, IInventory par2IInventory, int par3, int par4, int par5) {
/* 15 */     super(par2IInventory, par3, par4, par5);
/* 16 */     this.clazz = clazz;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotLimitedByClass(Class clazz, int limit, IInventory par2IInventory, int par3, int par4, int par5) {
/* 21 */     super(par2IInventory, par3, par4, par5);
/* 22 */     this.clazz = clazz;
/* 23 */     this.limit = limit;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public boolean func_75214_a(ItemStack stack) { return (!stack.func_190926_b() && this.clazz.isAssignableFrom(stack.func_77973_b().getClass())); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public int func_75219_a() { return this.limit; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotLimitedByClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */