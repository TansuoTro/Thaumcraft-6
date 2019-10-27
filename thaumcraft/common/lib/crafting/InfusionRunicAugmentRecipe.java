/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.RecipeMatcher;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.lib.events.PlayerEvents;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InfusionRunicAugmentRecipe
/*     */   extends InfusionRecipe
/*     */ {
/*  27 */   public InfusionRunicAugmentRecipe() { super("RUNICSHIELDING", null, 0, null, Ingredient.field_193370_a, new Object[] { "gemAmber", ItemsTC.salisMundus }); }
/*     */ 
/*     */ 
/*     */   
/*     */   public InfusionRunicAugmentRecipe(ItemStack in) {
/*  32 */     super("RUNICSHIELDING", null, 0, null, in, new Object[] { new ItemStack(ItemsTC.salisMundus), "gemAmber" });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  37 */     int fc = PlayerEvents.getRunicCharge(in);
/*  38 */     if (fc > 0) {
/*  39 */       this.components.clear();
/*  40 */       ArrayList<ItemStack> com = new ArrayList<ItemStack>();
/*  41 */       this.components.add(Ingredient.func_193367_a(ItemsTC.salisMundus));
/*  42 */       this.components.add(ThaumcraftApiHelper.getIngredient("gemAmber"));
/*  43 */       int c = 0;
/*  44 */       while (c < fc) {
/*  45 */         c++;
/*  46 */         this.components.add(ThaumcraftApiHelper.getIngredient("gemAmber"));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
/*  57 */     if (getRecipeInput() == null) return false; 
/*  58 */     if (!ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(this.research)) {
/*  59 */       return false;
/*     */     }
/*  61 */     if (!(central.func_77973_b() instanceof net.minecraft.item.ItemArmor) && !(central.func_77973_b() instanceof baubles.api.IBauble)) {
/*  62 */       return false;
/*     */     }
/*  64 */     return ((getRecipeInput() == Ingredient.field_193370_a || getRecipeInput().apply(central)) && RecipeMatcher.findMatches(input, getComponents(central)) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getRecipeOutput(EntityPlayer player, ItemStack input, List<ItemStack> comps) {
/*  69 */     if (input == null) return null; 
/*  70 */     ItemStack out = input.func_77946_l();
/*  71 */     int base = PlayerEvents.getRunicCharge(input) + 1;
/*  72 */     out.func_77983_a("TC.RUNIC", new NBTTagByte((byte)base));
/*  73 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public AspectList getAspects(EntityPlayer player, ItemStack input, List<ItemStack> comps) {
/*  78 */     AspectList out = new AspectList();
/*  79 */     int vis = 20 + (int)(20.0D * Math.pow(2.0D, PlayerEvents.getRunicCharge(input)));
/*  80 */     if (vis > 0) {
/*  81 */       out.add(Aspect.PROTECT, vis);
/*  82 */       out.add(Aspect.CRYSTAL, vis / 2);
/*  83 */       out.add(Aspect.ENERGY, vis / 2);
/*     */     } 
/*  85 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public int getInstability(EntityPlayer player, ItemStack input, List<ItemStack> comps) { return 5 + PlayerEvents.getRunicCharge(input) / 2; }
/*     */ 
/*     */ 
/*     */   
/*     */   public NonNullList<Ingredient> getComponents(ItemStack input) {
/*  95 */     NonNullList<Ingredient> com = NonNullList.func_191196_a();
/*  96 */     com.add(Ingredient.func_193367_a(ItemsTC.salisMundus));
/*  97 */     com.add(ThaumcraftApiHelper.getIngredient("gemAmber"));
/*  98 */     int fc = PlayerEvents.getRunicCharge(input);
/*  99 */     if (fc > 0) {
/* 100 */       for (int c = 0; c < fc; c++) {
/* 101 */         com.add(ThaumcraftApiHelper.getIngredient("gemAmber"));
/*     */       }
/*     */     }
/* 104 */     return com;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\InfusionRunicAugmentRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */