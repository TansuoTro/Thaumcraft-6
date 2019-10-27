/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ 
/*    */ public class CardScripting
/*    */   extends TheorycraftCard
/*    */ {
/* 13 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public String getResearchCategory() { return "GOLEMANCY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public String getLocalizedName() { return (new TextComponentTranslation("card.scripting.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public String getLocalizedText() { return (new TextComponentTranslation("card.scripting.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 34 */     if (data.table != null && ((TileResearchTable)data.table).func_70301_a(false) != null && ((TileResearchTable)data.table)
/* 35 */       .func_70301_a(0).func_77952_i() < ((TileResearchTable)data.table).func_70301_a(0).func_77958_k() && ((TileResearchTable)data.table)
/* 36 */       .func_70301_a(true) != null) {
/* 37 */       ((TileResearchTable)data.table).consumeInkFromTable();
/* 38 */       ((TileResearchTable)data.table).consumepaperFromTable();
/* 39 */       data.addTotal(getResearchCategory(), 25);
/* 40 */       return true;
/*    */     } 
/* 42 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardScripting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */