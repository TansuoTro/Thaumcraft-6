/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.inventory.IInventoryChangedListener;
/*    */ import net.minecraft.inventory.InventoryBasic;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryFocusPouch
/*    */   extends InventoryBasic
/*    */ {
/*    */   public InventoryFocusPouch(IInventoryChangedListener listener) {
/* 13 */     super("container.focuspouch", false, 18);
/* 14 */     func_110134_a(listener);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public int func_70297_j_() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public boolean func_94041_b(int i, ItemStack itemstack) { return (!itemstack.func_190926_b() && itemstack.func_77973_b() instanceof thaumcraft.common.items.casters.ItemFocus); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\InventoryFocusPouch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */