/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.capabilities.IPlayerWarp;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardDarkWhispers
/*    */   extends TheorycraftCard
/*    */ {
/* 16 */   public boolean isAidOnly() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public String getResearchCategory() { return "ELDRITCH"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getLocalizedName() { return (new TextComponentTranslation("card.darkwhisper.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public String getLocalizedText() { return (new TextComponentTranslation("card.darkwhisper.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 42 */     int l = player.field_71068_ca;
/* 43 */     player.func_82242_a(-(10 + l));
/*    */     
/* 45 */     if (l > 0) {
/* 46 */       for (String k : ResearchCategories.researchCategories.keySet()) {
/* 47 */         if (player.func_70681_au().nextBoolean())
/* 48 */           continue;  data.addTotal(k, MathHelper.func_76136_a(player.func_70681_au(), 0, Math.max(1, (int)Math.sqrt(l))));
/*    */       } 
/*    */     }
/* 51 */     data.addTotal("ELDRITCH", MathHelper.func_76136_a(player.func_70681_au(), Math.max(1, l / 5), Math.max(5, l / 2)));
/*    */     
/* 53 */     ThaumcraftApi.internalMethods.addWarpToPlayer(player, Math.max(1, (int)Math.sqrt(l)), IPlayerWarp.EnumWarpType.NORMAL);
/*    */     
/* 55 */     if (player.func_70681_au().nextBoolean()) data.bonusDraws++;
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardDarkWhispers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */