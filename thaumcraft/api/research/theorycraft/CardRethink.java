/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ 
/*    */ public class CardRethink
/*    */   extends TheorycraftCard
/*    */ {
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 11 */     int a = 0;
/* 12 */     for (String category : data.categoryTotals.keySet()) {
/* 13 */       a += data.getTotal(category);
/*    */     }
/* 15 */     return (a >= 10);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public int getInspirationCost() { return -1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public String getLocalizedName() { return (new TextComponentTranslation("card.rethink.name", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public String getLocalizedText() { return (new TextComponentTranslation("card.rethink.text", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 35 */     if (!initialize(player, data)) return false; 
/* 36 */     int a = 0;
/* 37 */     for (String category : data.categoryTotals.keySet()) {
/* 38 */       a += data.getTotal(category);
/*    */     }
/* 40 */     a = Math.min(a, 10);
/* 41 */     int tries = 0;
/* 42 */     while (a > 0 && tries < 1000) {
/* 43 */       tries++;
/* 44 */       for (String category : data.categoryTotals.keySet()) {
/* 45 */         data.addTotal(category, -1);
/* 46 */         a--;
/* 47 */         if (a <= 0 || !data.hasTotal(category))
/*    */           break; 
/*    */       } 
/* 50 */     }  data.bonusDraws++;
/* 51 */     data.addTotal("BASICS", MathHelper.func_76136_a(player.func_70681_au(), 1, 10));
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardRethink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */