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
/*    */ public class CardGlyphs
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
/* 31 */   public String getLocalizedName() { return (new TextComponentTranslation("card.glyph.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public String getLocalizedText() { return (new TextComponentTranslation("card.glyph.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 41 */     String[] s = (String[])ResearchCategories.researchCategories.keySet().toArray(new String[0]);
/* 42 */     data.addTotal(s[player.func_70681_au().nextInt(s.length)], MathHelper.func_76136_a(player.func_70681_au(), 10, 20));
/* 43 */     data.addTotal("ELDRITCH", MathHelper.func_76136_a(player.func_70681_au(), 10, 20));
/* 44 */     ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, IPlayerWarp.EnumWarpType.TEMPORARY);
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardGlyphs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */