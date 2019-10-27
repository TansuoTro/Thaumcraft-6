/*     */ package thaumcraft.api.research;
/*     */ 
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResearchStage
/*     */ {
/*     */   String text;
/*     */   ResourceLocation[] recipes;
/*     */   Object[] obtain;
/*     */   Object[] craft;
/*     */   int[] craftReference;
/*     */   Knowledge[] know;
/*     */   String[] research;
/*     */   String[] researchIcon;
/*     */   int warp;
/*     */   
/*  23 */   public String getText() { return this.text; }
/*     */ 
/*     */ 
/*     */   
/*  27 */   public String getTextLocalized() { return I18n.func_74838_a(getText()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public void setText(String text) { this.text = text; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public ResourceLocation[] getRecipes() { return this.recipes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public void setRecipes(ResourceLocation[] recipes) { this.recipes = recipes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public Object[] getObtain() { return this.obtain; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void setObtain(Object[] obtain) { this.obtain = obtain; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public Object[] getCraft() { return this.craft; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public void setCraft(Object[] craft) { this.craft = craft; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public int[] getCraftReference() { return this.craftReference; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setCraftReference(int[] craftReference) { this.craftReference = craftReference; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public Knowledge[] getKnow() { return this.know; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void setKnow(Knowledge[] know) { this.know = know; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public String[] getResearch() { return this.research; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setResearch(String[] research) { this.research = research; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public String[] getResearchIcon() { return this.researchIcon; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setResearchIcon(String[] research) { this.researchIcon = research; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public int getWarp() { return this.warp; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public void setWarp(int warp) { this.warp = warp; }
/*     */   
/*     */   public static class Knowledge {
/*     */     public IPlayerKnowledge.EnumKnowledgeType type;
/*     */     
/*     */     public Knowledge(IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory category, int num) {
/* 138 */       this.amount = 0;
/*     */ 
/*     */ 
/*     */       
/* 142 */       this.type = type;
/* 143 */       this.category = category;
/* 144 */       this.amount = num;
/*     */     }
/*     */     public ResearchCategory category; public int amount;
/*     */     public static Knowledge parse(String text) {
/* 148 */       String[] s = text.split(";");
/* 149 */       if (s.length == 2) {
/* 150 */         int num = 0;
/*     */         try {
/* 152 */           num = Integer.parseInt(s[1]);
/* 153 */         } catch (Exception exception) {}
/* 154 */         IPlayerKnowledge.EnumKnowledgeType t = IPlayerKnowledge.EnumKnowledgeType.valueOf(s[0].toUpperCase());
/* 155 */         if (t != null && !t.hasFields() && num > 0) {
/* 156 */           return new Knowledge(t, null, num);
/*     */         }
/* 158 */       } else if (s.length == 3) {
/* 159 */         int num = 0;
/*     */         try {
/* 161 */           num = Integer.parseInt(s[2]);
/* 162 */         } catch (Exception exception) {}
/* 163 */         IPlayerKnowledge.EnumKnowledgeType t = IPlayerKnowledge.EnumKnowledgeType.valueOf(s[0].toUpperCase());
/* 164 */         ResearchCategory f = ResearchCategories.getResearchCategory(s[1].toUpperCase());
/* 165 */         if (t != null && f != null && num > 0) {
/* 166 */           return new Knowledge(t, f, num);
/*     */         }
/*     */       } 
/* 169 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ResearchStage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */