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
/*    */ public class CardRevelation
/*    */   extends TheorycraftCard
/*    */ {
/* 16 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public String getResearchCategory() { return "ELDRITCH"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public String getLocalizedName() { return (new TextComponentTranslation("card.revelation.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getLocalizedText() { return (new TextComponentTranslation("card.revelation.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 36 */     String[] s = (String[])ResearchCategories.researchCategories.keySet().toArray(new String[0]);
/* 37 */     data.addTotal(s[player.func_70681_au().nextInt(s.length)], MathHelper.func_76136_a(player.func_70681_au(), 5, 10));
/* 38 */     data.addTotal("ELDRITCH", 30);
/* 39 */     ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, IPlayerWarp.EnumWarpType.TEMPORARY);
/* 40 */     ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, IPlayerWarp.EnumWarpType.NORMAL);
/* 41 */     data.penaltyStart++;
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardRevelation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */