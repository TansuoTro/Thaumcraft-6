/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ 
/*    */ 
/*    */ public class SlotGhost
/*    */   extends Slot
/*    */ {
/* 11 */   int limit = Integer.MAX_VALUE;
/*    */   
/*    */   public SlotGhost(IInventory par1iInventory, int par2, int par3, int par4, int par5) {
/* 14 */     super(par1iInventory, par2, par3, par4);
/* 15 */     this.limit = par5;
/*    */   }
/*    */ 
/*    */   
/* 19 */   public SlotGhost(IInventory par1iInventory, int par2, int par3, int par4) { super(par1iInventory, par2, par3, par4); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public int func_75219_a() { return this.limit; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public boolean func_82869_a(EntityPlayer par1EntityPlayer) { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotGhost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */