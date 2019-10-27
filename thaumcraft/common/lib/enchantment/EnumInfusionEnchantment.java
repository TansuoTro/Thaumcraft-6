/*     */ package thaumcraft.common.lib.enchantment;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public static enum EnumInfusionEnchantment
/*     */ {
/*  21 */   COLLECTOR(ImmutableSet.of("axe", "pickaxe", "shovel", "weapon"), 1, "INFUSIONENCHANTMENT"),
/*  22 */   DESTRUCTIVE(ImmutableSet.of("axe", "pickaxe", "shovel"), 1, "INFUSIONENCHANTMENT"),
/*  23 */   BURROWING(ImmutableSet.of("axe", "pickaxe"), 1, "INFUSIONENCHANTMENT"),
/*  24 */   SOUNDING(ImmutableSet.of("pickaxe"), 4, "INFUSIONENCHANTMENT"),
/*  25 */   REFINING(ImmutableSet.of("pickaxe"), 4, "INFUSIONENCHANTMENT"),
/*  26 */   ARCING(ImmutableSet.of("weapon"), 4, "INFUSIONENCHANTMENT"),
/*  27 */   ESSENCE(ImmutableSet.of("weapon"), 5, "INFUSIONENCHANTMENT"),
/*  28 */   VISBATTERY(ImmutableSet.of("chargable"), 3, "?"),
/*  29 */   VISCHARGE(ImmutableSet.of("chargable"), 1, "?"),
/*  30 */   SWIFT(ImmutableSet.of("boots"), 4, "IEARMOR"),
/*  31 */   AGILE(ImmutableSet.of("legs"), 1, "IEARMOR"),
/*  32 */   INFESTED(ImmutableSet.of("chest"), 1, "IETAINT"),
/*  33 */   LAMPLIGHT(ImmutableSet.of("axe", "pickaxe", "shovel"), 1, "INFUSIONENCHANTMENT");
/*     */   
/*     */   public Set<String> toolClasses;
/*     */   public int maxLevel;
/*     */   public String research;
/*     */   
/*     */   EnumInfusionEnchantment(Set<String> toolClasses, int ml, String research) {
/*  40 */     this.toolClasses = toolClasses;
/*  41 */     this.maxLevel = ml;
/*  42 */     this.research = research;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static NBTTagList getInfusionEnchantmentTagList(ItemStack stack) { return (stack == null || stack.func_190926_b() || stack.func_77978_p() == null) ? null : stack.func_77978_p().func_150295_c("infench", 10); }
/*     */ 
/*     */   
/*     */   public static List<EnumInfusionEnchantment> getInfusionEnchantments(ItemStack stack) {
/*  51 */     NBTTagList nbttaglist = getInfusionEnchantmentTagList(stack);
/*  52 */     List<EnumInfusionEnchantment> list = new ArrayList<EnumInfusionEnchantment>();
/*  53 */     if (nbttaglist != null)
/*     */     {
/*  55 */       for (int j = 0; j < nbttaglist.func_74745_c(); j++) {
/*     */         
/*  57 */         int k = nbttaglist.func_150305_b(j).func_74765_d("id");
/*  58 */         int l = nbttaglist.func_150305_b(j).func_74765_d("lvl");
/*     */         
/*  60 */         if (k >= 0 && k < values().length)
/*     */         {
/*  62 */           list.add(values()[k]);
/*     */         }
/*     */       } 
/*     */     }
/*  66 */     return list;
/*     */   }
/*     */   
/*     */   public static int getInfusionEnchantmentLevel(ItemStack stack, EnumInfusionEnchantment enchantment) {
/*  70 */     NBTTagList nbttaglist = getInfusionEnchantmentTagList(stack);
/*  71 */     List<EnumInfusionEnchantment> list = new ArrayList<EnumInfusionEnchantment>();
/*  72 */     if (nbttaglist != null)
/*     */     {
/*  74 */       for (int j = 0; j < nbttaglist.func_74745_c(); j++) {
/*     */         
/*  76 */         int k = nbttaglist.func_150305_b(j).func_74765_d("id");
/*  77 */         int l = nbttaglist.func_150305_b(j).func_74765_d("lvl");
/*     */         
/*  79 */         if (k >= 0 && k < values().length && values()[k] == enchantment)
/*     */         {
/*  81 */           return l;
/*     */         }
/*     */       } 
/*     */     }
/*  85 */     return 0;
/*     */   }
/*     */   
/*     */   public static void addInfusionEnchantment(ItemStack stack, EnumInfusionEnchantment ie, int level) {
/*  89 */     if (stack == null || stack.func_190926_b() || level > ie.maxLevel)
/*  90 */       return;  NBTTagList nbttaglist = getInfusionEnchantmentTagList(stack);
/*  91 */     if (nbttaglist != null) {
/*     */       
/*  93 */       for (int j = 0; j < nbttaglist.func_74745_c(); j++) {
/*     */         
/*  95 */         int k = nbttaglist.func_150305_b(j).func_74765_d("id");
/*  96 */         int l = nbttaglist.func_150305_b(j).func_74765_d("lvl");
/*  97 */         if (k == ie.ordinal()) {
/*  98 */           if (level <= l) {
/*     */             return;
/*     */           }
/* 101 */           nbttaglist.func_150305_b(j).func_74777_a("lvl", (short)level);
/* 102 */           stack.func_77983_a("infench", nbttaglist);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 108 */       nbttaglist = new NBTTagList();
/*     */     } 
/* 110 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 111 */     nbttagcompound.func_74777_a("id", (short)ie.ordinal());
/* 112 */     nbttagcompound.func_74777_a("lvl", (short)level);
/* 113 */     nbttaglist.func_74742_a(nbttagcompound);
/*     */     
/* 115 */     if (nbttaglist.func_74745_c() > 0)
/*     */     {
/* 117 */       stack.func_77983_a("infench", nbttaglist);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\enchantment\EnumInfusionEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */