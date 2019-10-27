/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotGhostFull
/*    */   extends Slot {
/* 10 */   int limit = Integer.MAX_VALUE;
/*    */   
/*    */   public SlotGhostFull(IInventory par1iInventory, int par2, int par3, int par4, int par5) {
/* 13 */     super(par1iInventory, par2, par3, par4);
/* 14 */     this.limit = par5;
/*    */   }
/*    */ 
/*    */   
/* 18 */   public SlotGhostFull(IInventory par1iInventory, int par2, int par3, int par4) { super(par1iInventory, par2, par3, par4); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public int func_75219_a() { return this.limit; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public boolean func_82869_a(EntityPlayer par1EntityPlayer) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public boolean func_75214_a(ItemStack stack) { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotGhostFull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */