/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotLimitedByItemstack
/*    */   extends Slot {
/*  9 */   ItemStack limitItem = null;
/*    */   
/*    */   public SlotLimitedByItemstack(ItemStack item, IInventory par2IInventory, int par3, int par4, int par5) {
/* 12 */     super(par2IInventory, par3, par4, par5);
/* 13 */     this.limitItem = item;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public boolean func_75214_a(ItemStack stack1) { return stack1.func_77969_a(this.limitItem); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotLimitedByItemstack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */