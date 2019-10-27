/*    */ package thaumcraft.api.items;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IRechargable
/*    */ {
/*    */   int getMaxCharge(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase);
/*    */   
/*    */   EnumChargeDisplay showInHud(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase);
/*    */   
/*    */   public enum EnumChargeDisplay
/*    */   {
/* 33 */     NEVER, NORMAL, PERIODIC;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\items\IRechargable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */