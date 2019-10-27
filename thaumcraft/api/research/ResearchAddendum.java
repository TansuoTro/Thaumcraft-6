/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResearchAddendum
/*    */ {
/*    */   String text;
/*    */   ResourceLocation[] recipes;
/*    */   String[] research;
/*    */   
/* 15 */   public String getText() { return this.text; }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public String getTextLocalized() { return I18n.func_74838_a(getText()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public void setText(String text) { this.text = text; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public ResourceLocation[] getRecipes() { return this.recipes; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void setRecipes(ResourceLocation[] recipes) { this.recipes = recipes; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String[] getResearch() { return this.research; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setResearch(String[] research) { this.research = research; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ResearchAddendum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */