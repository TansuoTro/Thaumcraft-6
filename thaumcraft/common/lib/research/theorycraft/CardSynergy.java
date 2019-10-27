/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardSynergy
/*    */   extends TheorycraftCard
/*    */ {
/* 12 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public String getResearchCategory() { return "GOLEMANCY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public String getLocalizedName() { return (new TextComponentTranslation("card.synergy.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public String getLocalizedText() { return (new TextComponentTranslation("card.synergy.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 32 */     int tot = 0;
/* 33 */     tot += data.getTotal("ARTIFICE");
/* 34 */     tot += data.getTotal("ALCHEMY");
/* 35 */     tot += data.getTotal("INFUSION");
/* 36 */     return (tot >= 15);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 41 */     int tot = 0;
/* 42 */     tot += data.getTotal("ARTIFICE");
/* 43 */     tot += data.getTotal("ALCHEMY");
/* 44 */     tot += data.getTotal("INFUSION");
/* 45 */     if (tot >= 15) {
/* 46 */       tot = 15;
/* 47 */       String[] cats = { "ARTIFICE", "ALCHEMY", "INFUSION" };
/* 48 */       int tries = 0;
/* 49 */       while (tot > 0 && tries < 1000) {
/* 50 */         tries++;
/* 51 */         for (String category : cats) {
/* 52 */           if (data.getTotal(category) > 0)
/*    */           
/*    */           { 
/* 55 */             data.addTotal(category, -1);
/* 56 */             tot--;
/* 57 */             if (tot <= 0)
/*    */               break;  } 
/*    */         } 
/* 60 */       }  data.addTotal("GOLEMANCY", 30);
/* 61 */       data.penaltyStart++;
/* 62 */       return true;
/*    */     } 
/* 64 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardSynergy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */