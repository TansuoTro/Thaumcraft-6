/*    */ package thaumcraft.common.items.baubles;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemCharmUndying
/*    */   extends ItemTCBase
/*    */   implements IBauble {
/*    */   public ItemCharmUndying() {
/* 13 */     super("charm_undying", new String[0]);
/* 14 */     this.field_77777_bU = 1;
/* 15 */     this.canRepair = false;
/* 16 */     func_77656_e(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public BaubleType getBaubleType(ItemStack itemstack) { return BaubleType.CHARM; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\baubles\ItemCharmUndying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */