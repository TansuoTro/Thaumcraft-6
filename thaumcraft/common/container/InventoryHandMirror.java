/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.IInventoryChangedListener;
/*    */ import net.minecraft.inventory.InventoryBasic;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class InventoryHandMirror
/*    */   extends InventoryBasic
/*    */ {
/*    */   Container container;
/*    */   
/*    */   public InventoryHandMirror(IInventoryChangedListener listener) {
/* 15 */     super("container.handmirror", false, 1);
/* 16 */     func_110134_a(listener);
/* 17 */     this.container = (Container)listener;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70299_a(int index, ItemStack stack) {
/* 23 */     super.func_70299_a(index, stack);
/* 24 */     if (!stack.func_190926_b()) this.container.func_75130_a(this); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\InventoryHandMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */