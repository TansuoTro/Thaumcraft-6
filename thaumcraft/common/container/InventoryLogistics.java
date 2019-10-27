/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.inventory.IInventoryChangedListener;
/*    */ import net.minecraft.inventory.InventoryBasic;
/*    */ 
/*    */ 
/*    */ public class InventoryLogistics
/*    */   extends InventoryBasic
/*    */ {
/*    */   public InventoryLogistics(IInventoryChangedListener listener) {
/* 11 */     super("container.logistics", false, 81);
/* 12 */     func_110134_a(listener);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public int func_70297_j_() { return Integer.MAX_VALUE; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\InventoryLogistics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */