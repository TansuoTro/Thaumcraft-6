/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.ThaumcraftInvHelper;
/*    */ 
/*    */ public class ScanItem
/*    */   implements IScanThing {
/*    */   String research;
/*    */   ItemStack stack;
/*    */   
/*    */   public ScanItem(String research, ItemStack stack) {
/* 14 */     this.research = research;
/* 15 */     this.stack = stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj) {
/* 20 */     if (obj == null) return false;
/*    */     
/* 22 */     ItemStack is = null;
/*    */     
/* 24 */     if (obj instanceof ItemStack)
/* 25 */       is = (ItemStack)obj; 
/* 26 */     if (obj instanceof EntityItem && ((EntityItem)obj).func_92059_d() != null) {
/* 27 */       is = ((EntityItem)obj).func_92059_d();
/*    */     }
/* 29 */     return (is != null && !is.func_190926_b() && ThaumcraftInvHelper.areItemStacksEqualForCrafting(is, this.stack));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public String getResearchKey(EntityPlayer player, Object object) { return this.research; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ScanItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */