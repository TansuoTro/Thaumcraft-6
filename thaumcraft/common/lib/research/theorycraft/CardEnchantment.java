/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardEnchantment
/*    */   extends TheorycraftCard
/*    */ {
/* 13 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public boolean isAidOnly() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public String getLocalizedName() { return (new TextComponentTranslation("card.enchantment.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public String getLocalizedText() { return (new TextComponentTranslation("card.enchantment.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 33 */     if (player.field_71068_ca >= 5) {
/* 34 */       player.func_82242_a(-5);
/* 35 */       data.addTotal("INFUSION", MathHelper.func_76136_a(player.func_70681_au(), 15, 20));
/* 36 */       data.addTotal("AUROMANCY", MathHelper.func_76136_a(player.func_70681_au(), 15, 20));
/* 37 */       return true;
/*    */     } 
/* 39 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */