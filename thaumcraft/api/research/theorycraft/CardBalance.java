/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ 
/*    */ 
/*    */ public class CardBalance
/*    */   extends TheorycraftCard
/*    */ {
/* 10 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 15 */   public String getLocalizedName() { return (new TextComponentTranslation("card.balance.name", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public String getLocalizedText() { return (new TextComponentTranslation("card.balance.text", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 25 */     int total = 0;
/* 26 */     int size = 0;
/* 27 */     for (String c : data.categoryTotals.keySet()) {
/* 28 */       if (data.categoriesBlocked.contains(c))
/* 29 */         continue;  total += ((Integer)data.categoryTotals.get(c)).intValue();
/* 30 */       size++;
/*    */     } 
/* 32 */     return (data.categoriesBlocked.size() < data.categoryTotals.size() - 1 && total >= size);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 37 */     int total = 0;
/* 38 */     int size = 0;
/* 39 */     for (String c : data.categoryTotals.keySet()) {
/* 40 */       if (data.categoriesBlocked.contains(c))
/* 41 */         continue;  total += ((Integer)data.categoryTotals.get(c)).intValue();
/* 42 */       size++;
/*    */     } 
/* 44 */     if (data.categoriesBlocked.size() >= data.categoryTotals.size() - 1 || total < size) return false; 
/* 45 */     for (String category : data.categoryTotals.keySet()) {
/* 46 */       if (data.categoriesBlocked.contains(category))
/* 47 */         continue;  data.categoryTotals.put(category, Integer.valueOf(total / size));
/*    */     } 
/* 49 */     data.addTotal("BASICS", 5);
/* 50 */     data.penaltyStart++;
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardBalance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */