/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardDragonEgg
/*    */   extends TheorycraftCard
/*    */ {
/* 14 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public boolean isAidOnly() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public String getLocalizedName() { return (new TextComponentTranslation("card.dragonegg.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public String getLocalizedText() { return (new TextComponentTranslation("card.dragonegg.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 34 */     String[] s = (String[])ResearchCategories.researchCategories.keySet().toArray(new String[0]);
/* 35 */     for (int a = 0; a < 10; a++) {
/* 36 */       String cat = s[player.func_70681_au().nextInt(s.length)];
/* 37 */       data.addTotal(cat, MathHelper.func_76136_a(player.func_70681_au(), 2, 5));
/*    */     } 
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardDragonEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */