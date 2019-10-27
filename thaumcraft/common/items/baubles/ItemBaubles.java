/*    */ package thaumcraft.common.items.baubles;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.items.IVisDiscountGear;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemBaubles
/*    */   extends ItemTCBase
/*    */   implements IBauble, IVisDiscountGear {
/*    */   public ItemBaubles() {
/* 15 */     super("baubles", new String[] { "amulet_mundane", "ring_mundane", "girdle_mundane", "ring_apprentice", "amulet_fancy", "ring_fancy", "girdle_fancy" });
/*    */ 
/*    */     
/* 18 */     this.field_77777_bU = 1;
/* 19 */     func_77656_e(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack itemstack) {
/* 24 */     switch (itemstack.func_77952_i()) { case 1: case 3: case 5:
/* 25 */         return BaubleType.RING;
/* 26 */       case 2: case 6: return BaubleType.BELT; }
/*    */     
/* 28 */     return BaubleType.AMULET;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack stack) {
/* 33 */     if (stack.func_77952_i() >= 3) {
/* 34 */       return EnumRarity.UNCOMMON;
/*    */     }
/* 36 */     return super.func_77613_e(stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getVisDiscount(ItemStack stack, EntityPlayer player) {
/* 41 */     if (stack.func_77952_i() == 3) {
/* 42 */       return 5;
/*    */     }
/* 44 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\baubles\ItemBaubles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */