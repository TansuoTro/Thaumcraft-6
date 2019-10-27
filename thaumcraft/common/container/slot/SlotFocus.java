/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class SlotFocus
/*    */   extends Slot
/*    */ {
/* 11 */   int limit = 64;
/*    */ 
/*    */ 
/*    */   
/* 15 */   public SlotFocus(IInventory par2IInventory, int par3, int par4, int par5) { super(par2IInventory, par3, par4, par5); }
/*    */ 
/*    */ 
/*    */   
/*    */   public SlotFocus(int limit, IInventory par2IInventory, int par3, int par4, int par5) {
/* 20 */     super(par2IInventory, par3, par4, par5);
/* 21 */     this.limit = limit;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public boolean func_75214_a(ItemStack stack) { return (stack != null && !stack.func_190926_b() && stack.func_77973_b() != null && stack.func_77973_b() instanceof thaumcraft.common.items.casters.ItemFocus); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public int func_75219_a() { return this.limit; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotFocus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */