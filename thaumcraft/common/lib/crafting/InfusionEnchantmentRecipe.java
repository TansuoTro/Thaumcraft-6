/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.RecipeMatcher;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InfusionEnchantmentRecipe
/*     */   extends InfusionRecipe
/*     */ {
/*     */   EnumInfusionEnchantment enchantment;
/*     */   
/*     */   public InfusionEnchantmentRecipe(EnumInfusionEnchantment ench, AspectList as, Object... components) {
/*  34 */     super(ench.research, null, 4, as, Ingredient.field_193370_a, components);
/*  35 */     this.enchantment = ench;
/*     */   }
/*     */ 
/*     */   
/*     */   public InfusionEnchantmentRecipe(InfusionEnchantmentRecipe recipe, ItemStack in) {
/*  40 */     super(recipe.enchantment.research, null, recipe.instability, recipe.aspects, in, recipe.components.toArray());
/*  41 */     this.enchantment = recipe.enchantment;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
/*  46 */     if (central == null || central.func_190926_b() || !ThaumcraftCapabilities.knowsResearch(player, new String[] { this.research })) {
/*  47 */       return false;
/*     */     }
/*     */     
/*  50 */     if (EnumInfusionEnchantment.getInfusionEnchantmentLevel(central, this.enchantment) >= this.enchantment.maxLevel) return false;
/*     */ 
/*     */     
/*  53 */     if (!this.enchantment.toolClasses.contains("all")) {
/*  54 */       Multimap<String, AttributeModifier> itemMods = central.func_111283_C(EntityEquipmentSlot.MAINHAND);
/*  55 */       boolean cool = false;
/*     */       
/*  57 */       if (itemMods != null && itemMods.containsKey(SharedMonsterAttributes.field_111264_e.func_111108_a()) && this.enchantment.toolClasses
/*  58 */         .contains("weapon")) {
/*  59 */         cool = true;
/*     */       }
/*     */       
/*  62 */       if (!cool && central.func_77973_b() instanceof ItemTool) {
/*  63 */         Set<String> tcs = ((ItemTool)central.func_77973_b()).getToolClasses(central);
/*  64 */         for (String tc : tcs) {
/*  65 */           if (this.enchantment.toolClasses.contains(tc)) {
/*  66 */             cool = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  73 */       if (!cool && central.func_77973_b() instanceof ItemArmor) {
/*  74 */         String at = "none";
/*  75 */         switch (((ItemArmor)central.func_77973_b()).field_77881_a) { case AMULET:
/*  76 */             at = "helm"; break;
/*  77 */           case BELT: at = "chest"; break;
/*  78 */           case RING: at = "legs"; break;
/*  79 */           case null: at = "boots";
/*     */             break; }
/*     */ 
/*     */ 
/*     */         
/*  84 */         if (this.enchantment.toolClasses.contains("armor") || this.enchantment.toolClasses.contains(at)) {
/*  85 */           cool = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  90 */       if (!cool && central.func_77973_b() instanceof IBauble) {
/*  91 */         String at = "none";
/*  92 */         switch (((IBauble)central.func_77973_b()).getBaubleType(central)) { case AMULET:
/*  93 */             at = "amulet"; break;
/*  94 */           case BELT: at = "belt"; break;
/*  95 */           case RING: at = "ring";
/*     */             break; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 102 */         if (this.enchantment.toolClasses.contains("bauble") || this.enchantment.toolClasses.contains(at)) {
/* 103 */           cool = true;
/*     */         }
/*     */       } 
/*     */       
/* 107 */       if (!cool && central.func_77973_b() instanceof thaumcraft.api.items.IRechargable && this.enchantment.toolClasses.contains("chargable")) {
/* 108 */         cool = true;
/*     */       }
/*     */       
/* 111 */       if (!cool) return false;
/*     */     
/*     */     } 
/*     */     
/* 115 */     return ((getRecipeInput() == Ingredient.field_193370_a || getRecipeInput().apply(central)) && RecipeMatcher.findMatches(input, getComponents()) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getRecipeOutput(EntityPlayer player, ItemStack input, List<ItemStack> comps) {
/* 121 */     if (input == null) return null;
/*     */     
/* 123 */     ItemStack out = input.func_77946_l();
/* 124 */     int cl = EnumInfusionEnchantment.getInfusionEnchantmentLevel(out, this.enchantment);
/*     */     
/* 126 */     if (cl >= this.enchantment.maxLevel) return null;
/*     */     
/* 128 */     List<EnumInfusionEnchantment> el = EnumInfusionEnchantment.getInfusionEnchantments(input);
/*     */     
/* 130 */     Random rand = new Random(System.nanoTime());
/* 131 */     if (rand.nextInt(10) < el.size()) {
/* 132 */       int base = 1;
/* 133 */       if (input.func_77942_o()) base += input.func_77978_p().func_74771_c("TC.WARP"); 
/* 134 */       out.func_77983_a("TC.WARP", new NBTTagByte((byte)base));
/*     */     } 
/*     */     
/* 137 */     EnumInfusionEnchantment.addInfusionEnchantment(out, this.enchantment, cl + 1);
/*     */     
/* 139 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public AspectList getAspects(EntityPlayer player, ItemStack input, List<ItemStack> comps) {
/* 144 */     AspectList out = new AspectList();
/*     */     
/* 146 */     if (input == null || input.func_190926_b()) return out;
/*     */     
/* 148 */     int cl = EnumInfusionEnchantment.getInfusionEnchantmentLevel(input, this.enchantment) + 1;
/*     */     
/* 150 */     if (cl > this.enchantment.maxLevel) return out;
/*     */     
/* 152 */     List<EnumInfusionEnchantment> el = EnumInfusionEnchantment.getInfusionEnchantments(input);
/* 153 */     int otherEnchantments = el.size();
/* 154 */     if (el.contains(this.enchantment)) otherEnchantments--;
/*     */     
/* 156 */     float modifier = cl + otherEnchantments * 0.33F;
/*     */     
/* 158 */     for (Aspect a : getAspects().getAspects()) {
/* 159 */       out.add(a, (int)(getAspects().getAmount(a) * modifier));
/*     */     }
/*     */     
/* 162 */     return out;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\InfusionEnchantmentRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */