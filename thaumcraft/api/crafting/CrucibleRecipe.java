/*     */ package thaumcraft.api.crafting;
/*     */ 
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrucibleRecipe
/*     */   implements IThaumcraftRecipe
/*     */ {
/*     */   private ItemStack recipeOutput;
/*     */   private Ingredient catalyst;
/*     */   private AspectList aspects;
/*     */   private String research;
/*     */   private String name;
/*     */   public int hash;
/*     */   private String group;
/*     */   
/*     */   private void generateHash() {
/*     */     String hc = this.research;
/*     */     hc = hc + this.recipeOutput.toString();
/*     */     if (this.recipeOutput.func_77942_o())
/*     */       hc = hc + this.recipeOutput.func_77978_p().toString(); 
/*     */     for (ItemStack is : getCatalyst().func_193365_a()) {
/*     */       hc = hc + is.toString();
/*     */       if (is.func_77942_o())
/*     */         hc = hc + is.func_77978_p().toString(); 
/*     */     } 
/*     */     this.hash = hc.hashCode();
/*     */   }
/*     */   
/*     */   public boolean matches(AspectList itags, ItemStack cat) {
/*     */     if (!getCatalyst().apply(cat))
/*     */       return false; 
/*     */     if (itags == null)
/*     */       return false; 
/*     */     for (Aspect tag : getAspects().getAspects()) {
/*     */       if (itags.getAmount(tag) < getAspects().getAmount(tag))
/*     */         return false; 
/*     */     } 
/*     */     return true;
/*     */   }
/*     */   
/*     */   public boolean catalystMatches(ItemStack cat) { return getCatalyst().apply(cat); }
/*     */   
/*     */   public AspectList removeMatching(AspectList itags) {
/*     */     AspectList temptags = new AspectList();
/*     */     temptags.aspects.putAll(itags.aspects);
/*     */     for (Aspect tag : getAspects().getAspects())
/*     */       temptags.remove(tag, getAspects().getAmount(tag)); 
/*     */     return temptags;
/*     */   }
/*     */   
/*     */   public ItemStack getRecipeOutput() { return this.recipeOutput; }
/*     */   
/*     */   public CrucibleRecipe(String researchKey, ItemStack result, Object catalyst, AspectList tags) {
/*  98 */     this.group = ""; this.recipeOutput = result; this.name = ""; setAspects(tags); this.research = researchKey;
/*     */     setCatalyst(ThaumcraftApiHelper.getIngredient(catalyst));
/*     */     if (getCatalyst() == null)
/*     */       throw new RuntimeException("Invalid crucible recipe catalyst: " + catalyst); 
/* 102 */     generateHash(); } public String getGroup() { return this.group; }
/*     */   public String getResearch() { return this.research; }
/*     */   public Ingredient getCatalyst() { return this.catalyst; }
/*     */   public CrucibleRecipe setGroup(ResourceLocation s) {
/* 106 */     this.group = s.toString();
/* 107 */     return this;
/*     */   }
/*     */   
/*     */   public void setCatalyst(Ingredient catalyst) { this.catalyst = catalyst; }
/*     */   
/*     */   public AspectList getAspects() { return this.aspects; }
/*     */   
/*     */   public void setAspects(AspectList aspects) { this.aspects = aspects; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\CrucibleRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */