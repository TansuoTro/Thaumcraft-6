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
/*    */ public class CardAwareness
/*    */   extends TheorycraftCard
/*    */ {
/* 15 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String getResearchCategory() { return "AUROMANCY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public String getLocalizedName() { return (new TextComponentTranslation("card.awareness.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public String getLocalizedText() { return (new TextComponentTranslation("card.awareness.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 35 */     data.addTotal(getResearchCategory(), 20);
/* 36 */     if (player.func_70681_au().nextFloat() < 0.33D) {
/* 37 */       data.addTotal("ELDRITCH", MathHelper.func_76136_a(player.func_70681_au(), 1, 5));
/* 38 */       ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, IPlayerWarp.EnumWarpType.NORMAL);
/*    */     } 
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardAwareness.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */