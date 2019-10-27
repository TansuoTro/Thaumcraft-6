/*     */ package thaumcraft.api;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.registries.GameData;
/*     */ import thaumcraft.api.aspects.AspectEventProxy;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.api.crafting.IThaumcraftRecipe;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.crafting.Part;
/*     */ import thaumcraft.api.internal.CommonInternals;
/*     */ import thaumcraft.api.internal.DummyInternalMethodHandler;
/*     */ import thaumcraft.api.internal.IInternalMethodHandler;
/*     */ import thaumcraft.api.internal.WeightedRandomLoot;
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
/*     */ public class ThaumcraftApi
/*     */ {
/*  38 */   public static IInternalMethodHandler internalMethods = new DummyInternalMethodHandler();
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
/*     */   public static void registerResearchLocation(ResourceLocation loc) {
/*  50 */     if (!CommonInternals.jsonLocs.containsKey(loc.toString())) {
/*  51 */       CommonInternals.jsonLocs.put(loc.toString(), loc);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SmeltBonus
/*     */   {
/*     */     public Object in;
/*     */     public ItemStack out;
/*     */     public float chance;
/*     */     
/*     */     public SmeltBonus(Object in, ItemStack out, float chance) {
/*  62 */       this.in = in;
/*  63 */       this.out = out;
/*  64 */       this.chance = chance;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addSmeltingBonus(Object in, ItemStack out, float chance) {
/*  75 */     if (in instanceof ItemStack || in instanceof String)
/*  76 */       CommonInternals.smeltingBonus.add(new SmeltBonus(in, out, chance)); 
/*     */   }
/*     */   
/*     */   public static void addSmeltingBonus(Object in, ItemStack out) {
/*  80 */     if (in instanceof ItemStack || in instanceof String) {
/*  81 */       CommonInternals.smeltingBonus.add(new SmeltBonus(in, out, 0.33F));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  86 */   public static HashMap<ResourceLocation, IThaumcraftRecipe> getCraftingRecipes() { return CommonInternals.craftingRecipeCatalog; }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static HashMap<ResourceLocation, Object> getCraftingRecipesFake() { return CommonInternals.craftingRecipeCatalogFake; }
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
/* 101 */   public static void addFakeCraftingRecipe(ResourceLocation registry, Object recipe) { getCraftingRecipesFake().put(registry, recipe); }
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
/* 112 */   public static void addMultiblockRecipeToCatalog(ResourceLocation registry, BluePrint recipe) { getCraftingRecipes().put(registry, recipe); }
/*     */   
/*     */   public static class BluePrint implements IThaumcraftRecipe {
/*     */     Part[][][] parts;
/*     */     String research;
/*     */     ItemStack displayStack;
/*     */     ItemStack[] ingredientList;
/*     */     private String group;
/*     */     
/*     */     public BluePrint(String research, Part[][][] parts, ItemStack... ingredientList) {
/* 122 */       this.parts = parts;
/* 123 */       this.research = research;
/* 124 */       this.ingredientList = ingredientList;
/*     */     }
/*     */     
/*     */     public BluePrint(String research, ItemStack display, Part[][][] parts, ItemStack... ingredientList) {
/* 128 */       this.parts = parts;
/* 129 */       this.research = research;
/* 130 */       this.displayStack = display;
/* 131 */       this.ingredientList = ingredientList;
/*     */     }
/*     */ 
/*     */     
/* 135 */     public Part[][][] getParts() { return this.parts; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     public String getResearch() { return this.research; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     public ItemStack[] getIngredientList() { return this.ingredientList; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     public ItemStack getDisplayStack() { return this.displayStack; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     public String getGroup() { return this.group; }
/*     */ 
/*     */     
/*     */     public BluePrint setGroup(ResourceLocation loc) {
/* 167 */       this.group = loc.toString();
/* 168 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addArcaneCraftingRecipe(ResourceLocation registry, IArcaneRecipe recipe) {
/* 180 */     recipe.setRegistryName(registry);
/* 181 */     GameData.register_impl(recipe);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public static void addInfusionCraftingRecipe(ResourceLocation registry, InfusionRecipe recipe) { getCraftingRecipes().put(registry, recipe); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InfusionRecipe getInfusionRecipe(ItemStack res) {
/* 202 */     for (Object r : getCraftingRecipes().values()) {
/* 203 */       if (r instanceof InfusionRecipe && (
/* 204 */         (InfusionRecipe)r).getRecipeOutput() instanceof ItemStack && (
/* 205 */         (ItemStack)((InfusionRecipe)r).getRecipeOutput()).func_77969_a(res)) {
/* 206 */         return (InfusionRecipe)r;
/*     */       }
/*     */     } 
/*     */     
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public static void addCrucibleRecipe(ResourceLocation registry, CrucibleRecipe recipe) { getCraftingRecipes().put(registry, recipe); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CrucibleRecipe getCrucibleRecipe(ItemStack stack) {
/* 228 */     for (Object r : getCraftingRecipes().values()) {
/* 229 */       if (r instanceof CrucibleRecipe && (
/* 230 */         (CrucibleRecipe)r).getRecipeOutput().func_77969_a(stack)) {
/* 231 */         return (CrucibleRecipe)r;
/*     */       }
/*     */     } 
/* 234 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CrucibleRecipe getCrucibleRecipeFromHash(int hash) {
/* 242 */     for (Object recipe : getCraftingRecipes().values()) {
/* 243 */       if (recipe instanceof CrucibleRecipe && ((CrucibleRecipe)recipe).hash == hash)
/* 244 */         return (CrucibleRecipe)recipe; 
/*     */     } 
/* 246 */     return null;
/*     */   }
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
/*     */   public static boolean exists(ItemStack item) {
/* 260 */     ItemStack stack = item.func_77946_l();
/* 261 */     stack.func_190920_e(1);
/* 262 */     AspectList tmp = (AspectList)CommonInternals.objectTags.get(stack.serializeNBT().toString());
/* 263 */     if (tmp == null) {
/*     */       try {
/* 265 */         stack.func_77964_b(32767);
/* 266 */         tmp = (AspectList)CommonInternals.objectTags.get(stack.serializeNBT().toString());
/* 267 */         if (item.func_77952_i() == 32767 && tmp == null) {
/* 268 */           int index = 0;
/*     */           do {
/* 270 */             stack.func_77964_b(index);
/* 271 */             tmp = (AspectList)CommonInternals.objectTags.get(stack.serializeNBT().toString());
/* 272 */             ++index;
/* 273 */           } while (index < 16 && tmp == null);
/*     */         } 
/* 275 */         if (tmp == null) return false; 
/* 276 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 292 */   public static void registerObjectTag(ItemStack item, AspectList aspects) { (new AspectEventProxy()).registerObjectTag(item, aspects); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void registerObjectTag(ItemStack item, int[] meta, AspectList aspects) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 312 */   public static void registerObjectTag(String oreDict, AspectList aspects) { (new AspectEventProxy()).registerObjectTag(oreDict, aspects); }
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
/*     */   @Deprecated
/* 327 */   public static void registerComplexObjectTag(ItemStack item, AspectList aspects) { (new AspectEventProxy()).registerComplexObjectTag(item, aspects); }
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
/*     */   @Deprecated
/* 340 */   public static void registerComplexObjectTag(String oreDict, AspectList aspects) { (new AspectEventProxy()).registerComplexObjectTag(oreDict, aspects); }
/*     */   
/*     */   public static class EntityTagsNBT { public String name;
/*     */     
/*     */     public EntityTagsNBT(String name, Object value) {
/* 345 */       this.name = name;
/* 346 */       this.value = value;
/*     */     }
/*     */     public Object value; }
/*     */   public static class EntityTags { public String entityName;
/*     */     public ThaumcraftApi.EntityTagsNBT[] nbts;
/*     */     public AspectList aspects;
/*     */     
/*     */     public EntityTags(String entityName, AspectList aspects, EntityTagsNBT... nbts) {
/* 354 */       this.entityName = entityName;
/* 355 */       this.nbts = nbts;
/* 356 */       this.aspects = aspects;
/*     */     } }
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
/*     */   @Deprecated
/* 377 */   public static void registerEntityTag(String entityName, AspectList aspects, EntityTagsNBT... nbt) { CommonInternals.scanEntities.add(new EntityTags(entityName, aspects, nbt)); }
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
/* 389 */   public static void addWarpToItem(ItemStack craftresult, int amount) { CommonInternals.warpMap.put(Arrays.asList(new Object[] { craftresult.func_77973_b(), Integer.valueOf(craftresult.func_77952_i()) }, ), Integer.valueOf(amount)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getWarp(ItemStack in) {
/* 398 */     if (in == null) return 0; 
/* 399 */     if (in instanceof ItemStack && CommonInternals.warpMap.containsKey(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77952_i()) }))) {
/* 400 */       return ((Integer)CommonInternals.warpMap.get(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77952_i()) }))).intValue();
/*     */     }
/* 402 */     return 0;
/*     */   }
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
/*     */   public static void addLootBagItem(ItemStack item, int weight, int... bagTypes) {
/* 418 */     if (bagTypes == null || bagTypes.length == 0) {
/* 419 */       WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
/*     */     } else {
/* 421 */       for (int rarity : bagTypes) {
/* 422 */         switch (rarity) { case 0:
/* 423 */             WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight)); break;
/* 424 */           case 1: WeightedRandomLoot.lootBagUncommon.add(new WeightedRandomLoot(item, weight)); break;
/* 425 */           case 2: WeightedRandomLoot.lootBagRare.add(new WeightedRandomLoot(item, weight));
/*     */             break; }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
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
/* 443 */   public static void registerSeed(Block block, ItemStack seed) { CommonInternals.seedList.put(block.func_149739_a(), seed); }
/*     */ 
/*     */ 
/*     */   
/* 447 */   public static ItemStack getSeed(Block block) { return (ItemStack)CommonInternals.seedList.get(block.func_149739_a()); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\ThaumcraftApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */