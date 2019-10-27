/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventoryChangedListener;
/*    */ import net.minecraft.inventory.InventoryBasic;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.entities.monster.EntityPech;
/*    */ 
/*    */ 
/*    */ public class InventoryPech
/*    */   extends InventoryBasic
/*    */ {
/*    */   private final EntityPech theMerchant;
/*    */   private final EntityPlayer thePlayer;
/*    */   
/*    */   public InventoryPech(IInventoryChangedListener listener, EntityPlayer par1EntityPlayer, EntityPech par2IMerchant) {
/* 17 */     super("container.pech", false, 5);
/* 18 */     func_110134_a(listener);
/* 19 */     this.thePlayer = par1EntityPlayer;
/* 20 */     this.theMerchant = par2IMerchant;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public boolean func_70300_a(EntityPlayer player) { return this.theMerchant.isTamed(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public boolean func_94041_b(int index, ItemStack stack) { return (index == 0); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\InventoryPech.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */