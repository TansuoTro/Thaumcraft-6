/*    */ package thaumcraft.common.lib.research;
/*    */ 
/*    */ import java.util.Map;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.research.IScanThing;
/*    */ import thaumcraft.api.research.ScanningManager;
/*    */ 
/*    */ 
/*    */ public class ScanEnchantment
/*    */   implements IScanThing
/*    */ {
/*    */   Enchantment enchantment;
/*    */   
/* 17 */   public ScanEnchantment(Enchantment ench) { this.enchantment = ench; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public boolean checkThing(EntityPlayer player, Object obj) { return (getEnchantment(player, obj) != null); }
/*    */ 
/*    */   
/*    */   private Enchantment getEnchantment(EntityPlayer player, Object obj) {
/* 26 */     if (obj == null) return null; 
/* 27 */     ItemStack is = ScanningManager.getItemFromParms(player, obj);
/* 28 */     if (is != null && !is.func_190926_b()) {
/* 29 */       Map<Enchantment, Integer> e = EnchantmentHelper.func_82781_a(is);
/* 30 */       for (Enchantment ench : e.keySet()) {
/* 31 */         if (ench == this.enchantment) {
/* 32 */           return ench;
/*    */         }
/*    */       } 
/*    */     } 
/* 36 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public String getResearchKey(EntityPlayer player, Object obj) { return "!" + this.enchantment.func_77320_a(); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\ScanEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */