/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.capabilities.IPlayerWarp;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardTruth
/*    */   extends TheorycraftCard
/*    */ {
/* 15 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String getResearchCategory() { return "ELDRITCH"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public String getLocalizedName() { return (new TextComponentTranslation("card.truth.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public String getLocalizedText() { return (new TextComponentTranslation("card.truth.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 35 */     data.addTotal("ELDRITCH", MathHelper.func_76136_a(player.func_70681_au(), 10, 25));
/* 36 */     data.bonusDraws++;
/* 37 */     ThaumcraftApi.internalMethods.addWarpToPlayer(player, 3, IPlayerWarp.EnumWarpType.TEMPORARY);
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardTruth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */