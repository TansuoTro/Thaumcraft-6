/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResearchCategory
/*    */ {
/*    */   public int minDisplayColumn;
/*    */   public int minDisplayRow;
/*    */   public int maxDisplayColumn;
/*    */   public int maxDisplayRow;
/*    */   public ResourceLocation icon;
/*    */   public ResourceLocation background;
/*    */   public ResourceLocation background2;
/*    */   public String researchKey;
/*    */   public String key;
/*    */   public AspectList formula;
/*    */   public Map<String, ResearchEntry> research;
/*    */   
/*    */   public ResearchCategory(String key, String researchkey, AspectList formula, ResourceLocation icon, ResourceLocation background) {
/* 79 */     this.research = new HashMap(); this.key = key; this.researchKey = researchkey; this.icon = icon; this.background = background; this.background2 = null; this.formula = formula; } public ResearchCategory(String key, String researchKey, AspectList formula, ResourceLocation icon, ResourceLocation background, ResourceLocation background2) { this.research = new HashMap();
/*    */     this.key = key;
/*    */     this.researchKey = researchKey;
/*    */     this.icon = icon;
/*    */     this.background = background;
/*    */     this.background2 = background2;
/*    */     this.formula = formula; }
/*    */ 
/*    */   
/*    */   public int applyFormula(AspectList as) { return applyFormula(as, 1.0D); }
/*    */   
/*    */   public int applyFormula(AspectList as, double mod) {
/*    */     if (this.formula == null)
/*    */       return 0; 
/*    */     double total = 0.0D;
/*    */     for (Aspect aspect : this.formula.getAspects())
/*    */       total += mod * mod * as.getAmount(aspect) * this.formula.getAmount(aspect) / 10.0D; 
/*    */     if (total > 0.0D)
/*    */       total = Math.sqrt(total); 
/*    */     return MathHelper.func_76143_f(total);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ResearchCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */