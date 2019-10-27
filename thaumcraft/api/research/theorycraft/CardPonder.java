/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ 
/*    */ 
/*    */ public class CardPonder
/*    */   extends TheorycraftCard
/*    */ {
/* 10 */   public int getInspirationCost() { return 2; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 15 */   public String getLocalizedName() { return (new TextComponentTranslation("card.ponder.name", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String getLocalizedText() { return (new TextComponentTranslation("card.ponder.text", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public boolean initialize(EntityPlayer player, ResearchTableData data) { return (data.categoriesBlocked.size() < data.categoryTotals.size()); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 30 */     int a = 25;
/* 31 */     int tries = 0;
/* 32 */     while (a > 0 && tries < 1000) {
/* 33 */       tries++;
/* 34 */       for (String category : data.categoryTotals.keySet()) {
/* 35 */         if (data.categoriesBlocked.contains(category)) {
/* 36 */           if (data.categoryTotals.size() <= 1) return false; 
/*    */           continue;
/*    */         } 
/* 39 */         data.addTotal(category, 1);
/* 40 */         a--;
/* 41 */         if (a <= 0)
/*    */           break; 
/*    */       } 
/* 44 */     }  data.addTotal("BASICS", 5);
/* 45 */     data.bonusDraws++;
/* 46 */     return (a != 20);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardPonder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */