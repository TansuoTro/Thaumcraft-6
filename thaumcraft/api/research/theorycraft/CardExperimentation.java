/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ 
/*    */ 
/*    */ public class CardExperimentation
/*    */   extends TheorycraftCard
/*    */ {
/* 12 */   public int getInspirationCost() { return 2; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public String getLocalizedName() { return (new TextComponentTranslation("card.experimentation.name", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public String getLocalizedText() { return (new TextComponentTranslation("card.experimentation.text", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/*    */     try {
/* 28 */       String[] s = (String[])ResearchCategories.researchCategories.keySet().toArray(new String[0]);
/* 29 */       String cat = s[player.func_70681_au().nextInt(s.length)];
/* 30 */       data.addTotal(cat, MathHelper.func_76136_a(player.func_70681_au(), 15, 30));
/* 31 */       data.addTotal("BASICS", MathHelper.func_76136_a(player.func_70681_au(), 1, 10));
/* 32 */     } catch (Exception e) {
/* 33 */       return false;
/*    */     } 
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardExperimentation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */