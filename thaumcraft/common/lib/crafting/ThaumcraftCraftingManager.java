/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Enchantments;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.PotionTypes;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.PotionHelper;
/*     */ import net.minecraft.potion.PotionType;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import net.minecraftforge.fml.relauncher.ReflectionHelper;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import net.minecraftforge.registries.IRegistryDelegate;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.internal.CommonInternals;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThaumcraftCraftingManager
/*     */ {
/*     */   static final int ASPECTCAP = 500;
/*     */   
/*     */   public static CrucibleRecipe findMatchingCrucibleRecipe(EntityPlayer player, AspectList aspects, ItemStack lastDrop) {
/*  54 */     int highest = 0;
/*  55 */     CrucibleRecipe out = null;
/*  56 */     for (Object re : ThaumcraftApi.getCraftingRecipes().values()) {
/*  57 */       if (re != null && re instanceof CrucibleRecipe) {
/*  58 */         CrucibleRecipe recipe = (CrucibleRecipe)re;
/*  59 */         ItemStack temp = lastDrop.func_77946_l();
/*  60 */         temp.func_190920_e(1);
/*  61 */         if (player != null && ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { recipe.getResearch() }) && recipe
/*  62 */           .matches(aspects, temp)) {
/*  63 */           int result = recipe.getAspects().visSize();
/*  64 */           if (result > highest) {
/*  65 */             highest = result;
/*  66 */             out = recipe;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  71 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IArcaneRecipe findMatchingArcaneRecipe(InventoryCrafting matrix, EntityPlayer player) {
/*  76 */     int var2 = 0;
/*  77 */     ItemStack var3 = null;
/*  78 */     ItemStack var4 = null;
/*     */     
/*  80 */     for (int var5 = 0; var5 < 15; var5++) {
/*     */       
/*  82 */       ItemStack var6 = matrix.func_70301_a(var5);
/*     */       
/*  84 */       if (!var6.func_190926_b()) {
/*     */         
/*  86 */         if (var2 == 0)
/*     */         {
/*  88 */           var3 = var6;
/*     */         }
/*     */         
/*  91 */         if (var2 == 1)
/*     */         {
/*  93 */           var4 = var6;
/*     */         }
/*     */         
/*  96 */         var2++;
/*     */       } 
/*     */     } 
/*     */     
/* 100 */     for (ResourceLocation key : CraftingManager.field_193380_a.func_148742_b()) {
/* 101 */       IRecipe recipe = (IRecipe)CraftingManager.field_193380_a.func_82594_a(key);
/* 102 */       if (recipe != null && recipe instanceof IArcaneRecipe && 
/* 103 */         recipe.func_77569_a(matrix, player.field_70170_p)) {
/* 104 */         return (IArcaneRecipe)recipe;
/*     */       }
/*     */     } 
/*     */     
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public static ItemStack findMatchingArcaneRecipeResult(InventoryCrafting awb, EntityPlayer player) {
/* 112 */     IArcaneRecipe var13 = findMatchingArcaneRecipe(awb, player);
/* 113 */     return (var13 == null) ? null : var13.func_77572_b(awb);
/*     */   }
/*     */   
/*     */   public static AspectList findMatchingArcaneRecipeCrystals(InventoryCrafting awb, EntityPlayer player) {
/* 117 */     IArcaneRecipe var13 = findMatchingArcaneRecipe(awb, player);
/* 118 */     return (var13 == null) ? null : var13.getCrystals();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int findMatchingArcaneRecipeVis(InventoryCrafting awb, EntityPlayer player) {
/* 123 */     IArcaneRecipe var13 = findMatchingArcaneRecipe(awb, player);
/* 124 */     return (var13 == null) ? 0 : ((var13.getVis() > 0) ? var13.getVis() : var13.getVis());
/*     */   }
/*     */ 
/*     */   
/*     */   public static InfusionRecipe findMatchingInfusionRecipe(ArrayList<ItemStack> items, ItemStack input, EntityPlayer player) {
/* 129 */     for (Object recipe : ThaumcraftApi.getCraftingRecipes().values()) {
/* 130 */       if (recipe != null && recipe instanceof InfusionRecipe && (
/* 131 */         (InfusionRecipe)recipe).matches(items, input, player.field_70170_p, player)) {
/* 132 */         return (InfusionRecipe)recipe;
/*     */       }
/*     */     } 
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static AspectList getObjectTags(ItemStack itemstack) { return getObjectTags(itemstack, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AspectList getObjectTags(ItemStack itemstack, ArrayList<String> history) {
/* 149 */     if (itemstack.func_190926_b()) return null; 
/* 150 */     int ss = CommonInternals.generateUniqueItemstackId(itemstack);
/* 151 */     AspectList tmp = (AspectList)CommonInternals.objectTags.get(Integer.valueOf(ss));
/* 152 */     if (tmp == null) {
/*     */       try {
/* 154 */         ItemStack sc = itemstack.func_77946_l();
/* 155 */         sc.func_77964_b(32767);
/* 156 */         ss = CommonInternals.generateUniqueItemstackId(sc);
/* 157 */         tmp = (AspectList)CommonInternals.objectTags.get(Integer.valueOf(ss));
/* 158 */         if (tmp == null) {
/* 159 */           if (itemstack.func_77952_i() == 32767) {
/* 160 */             int index = 0;
/*     */             do {
/* 162 */               sc.func_77964_b(index);
/* 163 */               ss = CommonInternals.generateUniqueItemstackId(sc);
/* 164 */               tmp = (AspectList)CommonInternals.objectTags.get(Integer.valueOf(ss));
/* 165 */               ++index;
/* 166 */             } while (index < 16 && tmp == null);
/*     */           } 
/* 168 */           if (tmp == null) {
/* 169 */             sc = itemstack.func_77946_l();
/* 170 */             ss = CommonInternals.generateUniqueItemstackIdStripped(sc);
/* 171 */             tmp = (AspectList)CommonInternals.objectTags.get(Integer.valueOf(ss));
/* 172 */             if (tmp == null) {
/* 173 */               sc = itemstack.func_77946_l();
/* 174 */               sc.func_77964_b(32767);
/* 175 */               ss = CommonInternals.generateUniqueItemstackIdStripped(sc);
/* 176 */               tmp = (AspectList)CommonInternals.objectTags.get(Integer.valueOf(ss));
/*     */             } 
/*     */           } 
/* 179 */           if (tmp == null) tmp = generateTags(itemstack, history); 
/*     */         } 
/* 181 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 184 */     return capAspects(getBonusTags(itemstack, tmp), 500);
/*     */   }
/*     */ 
/*     */   
/*     */   private static AspectList capAspects(AspectList sourcetags, int amount) {
/* 189 */     if (sourcetags == null) return sourcetags; 
/* 190 */     AspectList out = new AspectList();
/* 191 */     for (Aspect aspect : sourcetags.getAspects()) {
/* 192 */       if (aspect != null)
/* 193 */         out.merge(aspect, Math.min(amount, sourcetags.getAmount(aspect))); 
/*     */     } 
/* 195 */     return out;
/*     */   }
/*     */   
/*     */   private static AspectList getBonusTags(ItemStack itemstack, AspectList sourcetags) {
/* 199 */     AspectList tmp = new AspectList();
/* 200 */     if (itemstack.func_190926_b()) return tmp;
/*     */     
/* 202 */     Item item = itemstack.func_77973_b();
/* 203 */     if (item != null && item instanceof IEssentiaContainerItem && !((IEssentiaContainerItem)item).ignoreContainedAspects()) {
/* 204 */       if (sourcetags != null) sourcetags.aspects.clear(); 
/* 205 */       tmp = ((IEssentiaContainerItem)item).getAspects(itemstack);
/* 206 */       if (tmp != null && tmp.size() > 0)
/* 207 */         for (Aspect tag : tmp.copy().getAspects()) {
/* 208 */           if (tmp.getAmount(tag) <= 0) tmp.remove(tag);
/*     */         
/*     */         }  
/*     */     } 
/* 212 */     if (tmp == null) tmp = new AspectList();
/*     */     
/* 214 */     if (sourcetags != null) {
/* 215 */       for (Aspect tag : sourcetags.getAspects()) {
/* 216 */         if (tag != null) {
/* 217 */           tmp.add(tag, sourcetags.getAmount(tag));
/*     */         }
/*     */       } 
/*     */     }
/* 221 */     if (item != null && (
/* 222 */       tmp != null || item == Items.field_151068_bn)) {
/*     */       
/* 224 */       if (item instanceof ItemArmor) {
/* 225 */         tmp.merge(Aspect.PROTECT, ((ItemArmor)item).field_77879_b * 4);
/*     */       }
/* 227 */       else if (item instanceof ItemSword && ((ItemSword)item).func_150931_i() + 1.0F > 0.0F) {
/*     */         
/* 229 */         tmp.merge(Aspect.AVERSION, (int)(((ItemSword)item).func_150931_i() + 1.0F) * 4);
/*     */       
/*     */       }
/* 232 */       else if (item instanceof net.minecraft.item.ItemBow) {
/* 233 */         tmp.merge(Aspect.AVERSION, 10).merge(Aspect.FLIGHT, 5);
/*     */       
/*     */       }
/* 236 */       else if (item instanceof ItemTool) {
/* 237 */         String mat = ((ItemTool)item).func_77861_e();
/* 238 */         for (Item.ToolMaterial tm : Item.ToolMaterial.values()) {
/* 239 */           if (tm.toString().equals(mat)) {
/* 240 */             tmp.merge(Aspect.TOOL, (tm.func_77996_d() + 1) * 4);
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 245 */       else if (item instanceof net.minecraft.item.ItemShears || item instanceof net.minecraft.item.ItemHoe) {
/* 246 */         if (item.func_77612_l() <= Item.ToolMaterial.WOOD.func_77997_a()) {
/* 247 */           tmp.merge(Aspect.TOOL, 4);
/*     */         }
/* 249 */         else if (item.func_77612_l() <= Item.ToolMaterial.STONE.func_77997_a() || item
/* 250 */           .func_77612_l() <= Item.ToolMaterial.GOLD.func_77997_a()) {
/* 251 */           tmp.merge(Aspect.TOOL, 8);
/*     */         }
/* 253 */         else if (item.func_77612_l() <= Item.ToolMaterial.IRON.func_77997_a()) {
/* 254 */           tmp.merge(Aspect.TOOL, 12);
/*     */         } else {
/* 256 */           tmp.merge(Aspect.TOOL, 16);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 261 */       String[] dyes = { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };
/*     */       
/* 263 */       int[] ores = OreDictionary.getOreIDs(itemstack);
/* 264 */       if (ores != null && ores.length > 0) {
/* 265 */         Arrays.sort(dyes);
/* 266 */         for (int od : ores) {
/* 267 */           String s = OreDictionary.getOreName(od);
/* 268 */           if (s != null && Arrays.binarySearch(dyes, s) >= 0) {
/* 269 */             tmp.merge(Aspect.SENSES, 5);
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 278 */       NBTTagList ench = itemstack.func_77986_q();
/* 279 */       if (item instanceof ItemEnchantedBook) {
/* 280 */         ench = ((ItemEnchantedBook)item).func_92110_g(itemstack);
/*     */       }
/* 282 */       if (ench != null) {
/*     */         
/* 284 */         int var5 = 0;
/* 285 */         for (int var3 = 0; var3 < ench.func_74745_c(); var3++) {
/*     */           
/* 287 */           short eid = ench.func_150305_b(var3).func_74765_d("id");
/* 288 */           short lvl = (short)(ench.func_150305_b(var3).func_74765_d("lvl") * 3);
/*     */           
/* 290 */           Enchantment e = Enchantment.func_185262_c(eid);
/*     */           
/* 292 */           if (e != null) {
/* 293 */             if (e == Enchantments.field_185299_g) { tmp.merge(Aspect.WATER, lvl); }
/* 294 */             else if (e == Enchantments.field_180312_n) { tmp.merge(Aspect.BEAST, lvl / 2).merge(Aspect.AVERSION, lvl / 2); }
/* 295 */             else if (e == Enchantments.field_185297_d) { tmp.merge(Aspect.PROTECT, lvl / 2).merge(Aspect.ENTROPY, lvl / 2); }
/* 296 */             else if (e == Enchantments.field_185305_q) { tmp.merge(Aspect.TOOL, lvl); }
/* 297 */             else if (e == Enchantments.field_180309_e) { tmp.merge(Aspect.FLIGHT, lvl); }
/* 298 */             else if (e == Enchantments.field_77334_n) { tmp.merge(Aspect.FIRE, lvl / 2).merge(Aspect.AVERSION, lvl / 2); }
/* 299 */             else if (e == Enchantments.field_77329_d) { tmp.merge(Aspect.PROTECT, lvl / 2).merge(Aspect.FIRE, lvl / 2); }
/* 300 */             else if (e == Enchantments.field_185311_w) { tmp.merge(Aspect.FIRE, lvl); }
/* 301 */             else if (e == Enchantments.field_185308_t) { tmp.merge(Aspect.DESIRE, lvl); }
/* 302 */             else if (e == Enchantments.field_185312_x) { tmp.merge(Aspect.CRAFT, lvl); }
/* 303 */             else if (e == Enchantments.field_180313_o) { tmp.merge(Aspect.AIR, lvl); }
/* 304 */             else if (e == Enchantments.field_185304_p) { tmp.merge(Aspect.DESIRE, lvl); }
/* 305 */             else if (e == Enchantments.field_185309_u) { tmp.merge(Aspect.AVERSION, lvl); }
/* 306 */             else if (e == Enchantments.field_180308_g) { tmp.merge(Aspect.PROTECT, lvl); }
/* 307 */             else if (e == Enchantments.field_180310_c) { tmp.merge(Aspect.PROTECT, lvl); }
/* 308 */             else if (e == Enchantments.field_185310_v) { tmp.merge(Aspect.AIR, lvl); }
/* 309 */             else if (e == Enchantments.field_185298_f) { tmp.merge(Aspect.AIR, lvl); }
/* 310 */             else if (e == Enchantments.field_185302_k) { tmp.merge(Aspect.AVERSION, lvl); }
/* 311 */             else if (e == Enchantments.field_185306_r) { tmp.merge(Aspect.EXCHANGE, lvl); }
/* 312 */             else if (e == Enchantments.field_92091_k) { tmp.merge(Aspect.AVERSION, lvl); }
/* 313 */             else if (e == Enchantments.field_185303_l) { tmp.merge(Aspect.UNDEAD, lvl / 2).merge(Aspect.AVERSION, lvl / 2); }
/* 314 */             else if (e == Enchantments.field_185307_s) { tmp.merge(Aspect.EARTH, lvl); }
/* 315 */             else if (e == Enchantments.field_185300_i) { tmp.merge(Aspect.WATER, lvl); }
/* 316 */             else if (e == Enchantments.field_151370_z) { tmp.merge(Aspect.DESIRE, lvl); }
/* 317 */             else if (e == Enchantments.field_151369_A) { tmp.merge(Aspect.BEAST, lvl); }
/* 318 */             else if (e == Enchantments.field_185301_j) { tmp.merge(Aspect.COLD, lvl); }
/* 319 */             else if (e == Enchantments.field_185296_A) { tmp.merge(Aspect.CRAFT, lvl); }
/* 320 */              if (e.func_77324_c() == Enchantment.Rarity.UNCOMMON) var5 += 2; 
/* 321 */             if (e.func_77324_c() == Enchantment.Rarity.RARE) var5 += 4; 
/* 322 */             if (e.func_77324_c() == Enchantment.Rarity.VERY_RARE) var5 += 6;
/*     */           
/*     */           } 
/* 325 */           var5 += lvl;
/*     */         } 
/*     */         
/* 328 */         if (var5 > 0) tmp.merge(Aspect.MAGIC, var5);
/*     */       
/*     */       } 
/*     */     } 
/* 332 */     return AspectHelper.cullTags(tmp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void getPotionReagentsRecursive(PotionType potion, HashSet<ItemStack> hashSet) {
/*     */     try {
/* 339 */       String mixPredicateName = "net.minecraft.potion.PotionHelper$MixPredicate";
/* 340 */       Class<?> mixPredicateClass = Class.forName(mixPredicateName);
/* 341 */       Field output = ReflectionHelper.findField(mixPredicateClass, 
/* 342 */           ObfuscationReflectionHelper.remapFieldNames(mixPredicateName, new String[] { "field_185200_c" }));
/* 343 */       Field reagent = ReflectionHelper.findField(mixPredicateClass, 
/* 344 */           ObfuscationReflectionHelper.remapFieldNames(mixPredicateName, new String[] { "field_185199_b" }));
/* 345 */       Field input = ReflectionHelper.findField(mixPredicateClass, 
/* 346 */           ObfuscationReflectionHelper.remapFieldNames(mixPredicateName, new String[] { "field_185198_a" }));
/* 347 */       output.setAccessible(true);
/* 348 */       reagent.setAccessible(true);
/* 349 */       input.setAccessible(true);
/*     */       
/* 351 */       for (Object mixpre : PotionHelper.field_185213_a)
/*     */       {
/* 353 */         if (((IRegistryDelegate)output.get(mixpre)).get() instanceof PotionType && ((PotionType)((IRegistryDelegate)output
/* 354 */           .get(mixpre)).get()).getRegistryName() == potion.getRegistryName()) {
/*     */           
/*     */           try {
/* 357 */             hashSet.add(((Ingredient)reagent.get(mixpre)).func_193365_a()[0]);
/* 358 */             if (((IRegistryDelegate)input.get(mixpre)).get() != PotionTypes.field_185230_b && ((IRegistryDelegate)input
/* 359 */               .get(mixpre)).get() instanceof PotionType)
/* 360 */               getPotionReagentsRecursive((PotionType)((IRegistryDelegate)input.get(mixpre)).get(), hashSet); 
/*     */             break;
/* 362 */           } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 384 */     catch (Exception e) {
/* 385 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 391 */   public static AspectList generateTags(ItemStack is) { return generateTags(is, new ArrayList()); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static AspectList generateTags(ItemStack is, ArrayList<String> history) {
/*     */     AspectList ret;
/* 397 */     if (history == null) history = new ArrayList<String>();
/*     */ 
/*     */ 
/*     */     
/* 401 */     ItemStack stack = is.func_77946_l();
/* 402 */     stack.func_190920_e(1);
/*     */     
/*     */     try {
/* 405 */       if (stack.func_77973_b().func_77645_m() || !stack.func_77973_b().func_77614_k()) {
/* 406 */         stack.func_77964_b(32767);
/*     */       }
/* 408 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 411 */     if (ThaumcraftApi.exists(stack)) {
/* 412 */       return getObjectTags(stack, history);
/*     */     }
/* 414 */     String ss = stack.serializeNBT().toString();
/* 415 */     if (history.contains(ss)) {
/* 416 */       return null;
/*     */     }
/*     */     
/* 419 */     history.add(ss);
/*     */ 
/*     */ 
/*     */     
/* 423 */     if (history.size() < 100)
/* 424 */     { if (stack.func_77952_i() == 32767) stack.func_77964_b(0); 
/* 425 */       ret = generateTagsFromRecipes(stack, history); }
/* 426 */     else { return null; }
/*     */     
/* 428 */     AspectList ret = capAspects(ret, 500);
/*     */ 
/*     */     
/* 431 */     ThaumcraftApi.registerObjectTag(is, ret);
/*     */     
/* 433 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   private static AspectList generateTagsFromCrucibleRecipes(ItemStack stack, ArrayList<String> history) {
/* 438 */     CrucibleRecipe cr = ThaumcraftApi.getCrucibleRecipe(stack);
/*     */     
/* 440 */     if (cr != null) {
/* 441 */       AspectList ot = cr.getAspects().copy();
/* 442 */       int ss = cr.getRecipeOutput().func_190916_E();
/* 443 */       ItemStack cat = cr.getCatalyst().func_193365_a()[0];
/* 444 */       if (cat == null || cat.func_190926_b()) return null; 
/* 445 */       AspectList ot2 = getObjectTags(cat, history);
/* 446 */       AspectList out = new AspectList();
/* 447 */       if (ot2 != null && ot2.size() > 0)
/* 448 */         for (Aspect tt : ot2.getAspects()) {
/* 449 */           out.add(tt, ot2.getAmount(tt));
/*     */         } 
/* 451 */       for (Aspect tt : ot.getAspects()) {
/* 452 */         int amt = (int)(Math.sqrt(ot.getAmount(tt)) / ss);
/* 453 */         out.add(tt, amt);
/*     */       } 
/* 455 */       for (Aspect as : out.getAspects()) {
/* 456 */         if (out.getAmount(as) <= 0) out.remove(as); 
/*     */       } 
/* 458 */       return out;
/*     */     } 
/* 460 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static AspectList generateTagsFromInfusionRecipes(ItemStack stack, ArrayList<String> history) {
/* 465 */     InfusionRecipe cr = ThaumcraftApi.getInfusionRecipe(stack);
/*     */     
/* 467 */     if (cr != null) {
/* 468 */       AspectList ot = cr.getAspects().copy();
/* 469 */       NonNullList<Ingredient> ingredients = NonNullList.func_191196_a();
/* 470 */       ingredients.add(cr.getRecipeInput());
/* 471 */       ingredients.addAll(cr.getComponents());
/*     */       
/* 473 */       AspectList out = new AspectList();
/*     */       
/* 475 */       AspectList ot2 = getAspectsFromIngredients(ingredients, (ItemStack)cr.getRecipeOutput(), null, history);
/*     */       
/* 477 */       for (Aspect tt : ot2.getAspects()) {
/* 478 */         out.add(tt, ot2.getAmount(tt));
/*     */       }
/*     */       
/* 481 */       for (Aspect tt : ot.getAspects()) {
/* 482 */         int amt = (int)(Math.sqrt(ot.getAmount(tt)) / ((ItemStack)cr.getRecipeOutput()).func_190916_E());
/* 483 */         out.add(tt, amt);
/*     */       } 
/*     */       
/* 486 */       for (Aspect as : out.getAspects()) {
/* 487 */         if (out.getAmount(as) <= 0) out.remove(as);
/*     */       
/*     */       } 
/* 490 */       return out;
/*     */     } 
/* 492 */     return null;
/*     */   }
/*     */   
/*     */   private static AspectList generateTagsFromCraftingRecipes(ItemStack stack, ArrayList<String> history) {
/* 496 */     AspectList ret = null;
/* 497 */     int value = Integer.MAX_VALUE;
/*     */ 
/*     */     
/* 500 */     for (ResourceLocation key : CraftingManager.field_193380_a.func_148742_b()) {
/* 501 */       IRecipe recipe = (IRecipe)CraftingManager.field_193380_a.func_82594_a(key);
/*     */       
/* 503 */       if (recipe == null || recipe.func_77571_b() == null || Item.func_150891_b(recipe.func_77571_b().func_77973_b()) <= 0 || recipe.func_77571_b().func_77973_b() == null)
/*     */         continue; 
/* 505 */       int idR = (recipe.func_77571_b().func_77952_i() == 32767) ? 0 : recipe.func_77571_b().func_77952_i();
/* 506 */       int idS = (stack.func_77952_i() == 32767) ? 0 : stack.func_77952_i();
/*     */       
/* 508 */       if (recipe.func_77571_b().func_77973_b() == stack.func_77973_b() && idR == idS) {
/* 509 */         ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
/* 510 */         AspectList ph = new AspectList();
/* 511 */         int cval = 0;
/*     */         
/*     */         try {
/* 514 */           ph = getAspectsFromIngredients(recipe.func_192400_c(), recipe.func_77571_b(), recipe, history);
/*     */           
/* 516 */           if (recipe instanceof IArcaneRecipe) {
/* 517 */             IArcaneRecipe ar = (IArcaneRecipe)recipe;
/* 518 */             if (ar.getVis() > 0) ph.add(Aspect.MAGIC, (int)(Math.sqrt((1 + ar.getVis() / 2)) / recipe.func_77571_b().func_190916_E()));
/*     */           
/*     */           } 
/* 521 */           for (Aspect as : ph.copy().getAspects()) {
/* 522 */             if (ph.getAmount(as) <= 0) ph.remove(as); 
/*     */           } 
/* 524 */           if (ph.visSize() < value && ph.visSize() > 0) {
/* 525 */             ret = ph;
/* 526 */             value = ph.visSize();
/*     */           } 
/*     */         } catch (Exception e) {
/* 529 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 534 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   private static AspectList getAspectsFromIngredients(NonNullList<Ingredient> nonNullList, ItemStack recipeOut, IRecipe recipe, ArrayList<String> history) {
/* 539 */     AspectList out = new AspectList();
/* 540 */     AspectList mid = new AspectList();
/* 541 */     NonNullList<ItemStack> exlist = NonNullList.func_191196_a();
/* 542 */     if (recipe != null) {
/* 543 */       InventoryCrafting inv = new InventoryCrafting(new ContainerFake(), 3, 3);
/* 544 */       int index = 0;
/* 545 */       for (Ingredient is : nonNullList) {
/* 546 */         if (is.func_193365_a().length > 0)
/* 547 */           inv.func_70299_a(index, is.func_193365_a()[0]); 
/* 548 */         index++;
/*     */       } 
/* 550 */       exlist = recipe.func_179532_b(inv);
/*     */     } 
/*     */     
/* 553 */     int index = -1;
/* 554 */     for (Ingredient is : nonNullList) {
/* 555 */       index++;
/* 556 */       if (is.func_193365_a().length <= 0)
/*     */         continue; 
/* 558 */       AspectList obj = getObjectTags(is.func_193365_a()[0], history);
/* 559 */       if (obj != null) {
/* 560 */         for (Aspect as : obj.getAspects()) {
/* 561 */           if (as != null) {
/* 562 */             mid.add(as, obj.getAmount(as));
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 567 */     if (exlist != null) {
/* 568 */       for (ItemStack ri : exlist) {
/* 569 */         if (!ri.func_190926_b()) {
/* 570 */           AspectList obj = getObjectTags(ri, history);
/* 571 */           for (Aspect as : obj.getAspects()) {
/* 572 */             mid.reduce(as, obj.getAmount(as));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 577 */     for (Aspect as : mid.getAspects()) {
/* 578 */       if (as != null) {
/* 579 */         float v = mid.getAmount(as) * 0.75F / recipeOut.func_190916_E();
/* 580 */         if (v < 1.0F && v > 0.75D) v = 1.0F; 
/* 581 */         out.add(as, (int)v);
/*     */       } 
/*     */     } 
/* 584 */     for (Aspect as : out.getAspects()) {
/* 585 */       if (out.getAmount(as) <= 0) out.remove(as); 
/*     */     } 
/* 587 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   private static AspectList generateTagsFromRecipes(ItemStack stack, ArrayList<String> history) {
/* 592 */     ret = null;
/* 593 */     int value = 0;
/*     */ 
/*     */     
/* 596 */     ret = generateTagsFromCrucibleRecipes(stack, history);
/* 597 */     if (ret != null) return ret;
/*     */ 
/*     */     
/* 600 */     ret = generateTagsFromInfusionRecipes(stack, history);
/* 601 */     if (ret != null) return ret;
/*     */ 
/*     */     
/* 604 */     return generateTagsFromCraftingRecipes(stack, history);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\ThaumcraftCraftingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */