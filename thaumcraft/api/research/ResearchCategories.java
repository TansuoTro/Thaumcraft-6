/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedHashMap;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResearchCategories
/*    */ {
/* 15 */   public static LinkedHashMap<String, ResearchCategory> researchCategories = new LinkedHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public static ResearchCategory getResearchCategory(String key) { return (ResearchCategory)researchCategories.get(key); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public static String getCategoryName(String key) { return I18n.func_74838_a("tc.research_category." + key); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ResearchEntry getResearch(String key) {
/* 39 */     Collection rc = researchCategories.values();
/* 40 */     for (Object cat : rc) {
/* 41 */       Collection rl = ((ResearchCategory)cat).research.values();
/* 42 */       for (Object ri : rl) {
/* 43 */         if (((ResearchEntry)ri).key.equals(key)) return (ResearchEntry)ri; 
/*    */       } 
/*    */     } 
/* 46 */     return null;
/*    */   }
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
/*    */   public static ResearchCategory registerCategory(String key, String researchkey, AspectList formula, ResourceLocation icon, ResourceLocation background) {
/* 59 */     if (getResearchCategory(key) == null) {
/* 60 */       ResearchCategory rl = new ResearchCategory(key, researchkey, formula, icon, background);
/* 61 */       researchCategories.put(key, rl);
/* 62 */       return rl;
/*    */     } 
/* 64 */     return null;
/*    */   }
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
/*    */   public static ResearchCategory registerCategory(String key, String researchkey, AspectList formula, ResourceLocation icon, ResourceLocation background, ResourceLocation background2) {
/* 77 */     if (getResearchCategory(key) == null) {
/* 78 */       ResearchCategory rl = new ResearchCategory(key, researchkey, formula, icon, background, background2);
/* 79 */       researchCategories.put(key, rl);
/* 80 */       return rl;
/*    */     } 
/* 82 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ResearchCategories.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */