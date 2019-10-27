/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import thaumcraft.api.crafting.IArcaneWorkbench;
/*    */ 
/*    */ public class InventoryArcaneWorkbench
/*    */   extends InventoryCrafting implements IArcaneWorkbench {
/*    */   TileEntity workbench;
/*    */   
/*    */   public InventoryArcaneWorkbench(TileEntity tileEntity, Container container) {
/* 13 */     super(container, 5, 3);
/* 14 */     this.workbench = tileEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String func_70005_c_() { return "container.arcaneworkbench"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70296_d() {
/* 25 */     super.func_70296_d();
/* 26 */     this.workbench.func_70296_d();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\InventoryArcaneWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */