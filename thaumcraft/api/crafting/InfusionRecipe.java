/*     */ package thaumcraft.api.crafting;
/*     */ public class InfusionRecipe implements IThaumcraftRecipe { public AspectList aspects;
/*     */   public String research;
/*     */   private String name;
/*     */   protected NonNullList<Ingredient> components;
/*     */   public Ingredient sourceInput;
/*     */   public Object recipeOutput;
/*     */   public int instability;
/*     */   private String group;
/*     */   
/*     */   public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
/*     */     if (getRecipeInput() == null)
/*     */       return false; 
/*     */     if (!ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(this.research))
/*     */       return false; 
/*     */     return ((getRecipeInput() == Ingredient.field_193370_a || getRecipeInput().apply(central)) && RecipeMatcher.findMatches(input, getComponents()) != null);
/*     */   }
/*     */   
/*     */   public String getResearch() { return this.research; }
/*     */   
/*  21 */   public InfusionRecipe(String research, Object outputResult, int inst, AspectList aspects2, Object centralItem, Object... recipe) { this.components = NonNullList.func_191196_a();
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
/*  99 */     this.group = ""; this.name = ""; this.research = research; this.recipeOutput = outputResult; this.aspects = aspects2; this.instability = inst; this.sourceInput = ThaumcraftApiHelper.getIngredient(centralItem); if (this.sourceInput == null) { String ret = "Invalid infusion central item: " + centralItem; throw new RuntimeException(ret); }  for (Object in : recipe) { Ingredient ing = ThaumcraftApiHelper.getIngredient(in); if (ing != null) { this.components.add(ing); }
/*     */       else { String ret = "Invalid infusion recipe: "; for (Object tmp : recipe)
/*     */           ret = ret + tmp + ", ";  ret = ret + outputResult; throw new RuntimeException(ret); }
/*     */        }
/* 103 */      } public Ingredient getRecipeInput() { return this.sourceInput; } public NonNullList<Ingredient> getComponents() { return this.components; } public String getGroup() { return this.group; } public Object getRecipeOutput() { return this.recipeOutput; } public AspectList getAspects() { return this.aspects; } public Object getRecipeOutput(EntityPlayer player, ItemStack input, List<ItemStack> comps) { return this.recipeOutput; }
/*     */   public AspectList getAspects(EntityPlayer player, ItemStack input, List<ItemStack> comps) { return this.aspects; }
/*     */   public int getInstability(EntityPlayer player, ItemStack input, List<ItemStack> comps) { return this.instability; }
/*     */   public InfusionRecipe setGroup(ResourceLocation s) {
/* 107 */     this.group = s.toString();
/* 108 */     return this;
/*     */   } }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\InfusionRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */