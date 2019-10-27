/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardSpellbinding
/*    */   extends TheorycraftCard
/*    */ {
/* 12 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public String getResearchCategory() { return "AUROMANCY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public String getLocalizedName() { return (new TextComponentTranslation("card.spellbinding.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public String getLocalizedText() { return (new TextComponentTranslation("card.spellbinding.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public boolean initialize(EntityPlayer player, ResearchTableData data) { return (player.field_71068_ca > 0); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 38 */     if (player.field_71068_ca <= 0) return false; 
/* 39 */     int l = Math.min(5, player.field_71068_ca);
/* 40 */     data.addTotal(getResearchCategory(), l * 5);
/* 41 */     player.func_82242_a(-l);
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardSpellbinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */