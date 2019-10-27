/*    */ package thaumcraft.common.lib.research;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.potion.PotionUtils;
/*    */ import thaumcraft.api.research.IScanThing;
/*    */ import thaumcraft.api.research.ScanningManager;
/*    */ 
/*    */ public class ScanPotion
/*    */   implements IScanThing
/*    */ {
/*    */   Potion potion;
/*    */   
/* 17 */   public ScanPotion(Potion potion) { this.potion = potion; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public boolean checkThing(EntityPlayer player, Object obj) { return (getPotionEffect(player, obj) != null); }
/*    */ 
/*    */   
/*    */   private PotionEffect getPotionEffect(EntityPlayer player, Object obj) {
/* 26 */     if (obj == null) return null; 
/* 27 */     if (obj instanceof EntityLivingBase) {
/* 28 */       EntityLivingBase e = (EntityLivingBase)obj;
/* 29 */       for (PotionEffect potioneffect : e.func_70651_bq()) {
/* 30 */         if (potioneffect.func_188419_a() == this.potion) {
/* 31 */           return potioneffect;
/*    */         }
/*    */       } 
/*    */     } else {
/* 35 */       ItemStack is = ScanningManager.getItemFromParms(player, obj);
/* 36 */       if (is != null && !is.func_190926_b()) {
/* 37 */         for (PotionEffect potioneffect : PotionUtils.func_185189_a(is)) {
/*    */           
/* 39 */           if (potioneffect.func_188419_a() == this.potion) {
/* 40 */             return potioneffect;
/*    */           }
/*    */         } 
/*    */       }
/*    */     } 
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public String getResearchKey(EntityPlayer player, Object obj) { return "!" + this.potion.func_76393_a(); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\ScanPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */