/*     */ package thaumcraft.common.lib.research.theorycraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*     */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*     */ 
/*     */ public class CardCelestial
/*     */   extends TheorycraftCard
/*     */ {
/*     */   int md1;
/*     */   int md2;
/*  22 */   String cat = "BASICS";
/*     */ 
/*     */   
/*     */   public NBTTagCompound serialize() {
/*  26 */     NBTTagCompound nbt = super.serialize();
/*  27 */     nbt.func_74768_a("md1", this.md1);
/*  28 */     nbt.func_74768_a("md2", this.md2);
/*  29 */     nbt.func_74778_a("cat", this.cat);
/*  30 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void deserialize(NBTTagCompound nbt) {
/*  35 */     super.deserialize(nbt);
/*  36 */     this.md1 = nbt.func_74762_e("md1");
/*  37 */     this.md2 = nbt.func_74762_e("md2");
/*  38 */     this.cat = nbt.func_74779_i("cat");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public String getResearchCategory() { return this.cat; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/*  48 */     if (data.categoryTotals.isEmpty() || 
/*  49 */       !ThaumcraftCapabilities.knowsResearch(player, new String[] { "CELESTIALSCANNING" }))
/*  50 */       return false; 
/*  51 */     Random r = new Random(getSeed());
/*  52 */     this.md1 = MathHelper.func_76136_a(r, 0, 12);
/*  53 */     this.md2 = this.md1;
/*  54 */     for (; this.md1 == this.md2; this.md2 = MathHelper.func_76136_a(r, 0, 12));
/*     */     
/*  56 */     int hVal = 0;
/*  57 */     String hKey = "";
/*  58 */     for (String category : data.categoryTotals.keySet()) {
/*  59 */       int q = data.getTotal(category);
/*  60 */       if (q > hVal) {
/*  61 */         hVal = q;
/*  62 */         hKey = category;
/*     */       } 
/*     */     } 
/*  65 */     this.cat = hKey;
/*     */     
/*  67 */     return (this.cat != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  72 */   public int getInspirationCost() { return 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public String getLocalizedName() { return (new TextComponentTranslation("card.celestial.name", new Object[0])).func_150254_d(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public String getLocalizedText() { return (new TextComponentTranslation("card.celestial.text", new Object[] { TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat, new Object[0]))
/*  83 */           .func_150254_d() + TextFormatting.RESET
/*  84 */         })).func_150260_c(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public ItemStack[] getRequiredItems() { return new ItemStack[] { new ItemStack(ItemsTC.celestialNotes, 1, this.md1), new ItemStack(ItemsTC.celestialNotes, 1, this.md2) }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public boolean[] getRequiredItemsConsumed() { return new boolean[] { true, true }; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 102 */     data.addTotal(getResearchCategory(), MathHelper.func_76136_a(player.func_70681_au(), 25, 50));
/* 103 */     boolean sun = (this.md1 == 0 || this.md2 == 0);
/* 104 */     boolean moon = (this.md1 > 4 || this.md2 > 4);
/* 105 */     boolean stars = ((this.md1 > 0 && this.md1 < 5) || (this.md2 > 0 && this.md2 < 5));
/* 106 */     if (stars) {
/* 107 */       int amt = MathHelper.func_76136_a(player.func_70681_au(), 0, 5);
/* 108 */       data.addTotal("ELDRITCH", amt * 2);
/* 109 */       ThaumcraftApi.internalMethods.addWarpToPlayer(player, amt, IPlayerWarp.EnumWarpType.TEMPORARY);
/*     */     } 
/* 111 */     if (sun) data.penaltyStart++; 
/* 112 */     if (moon) data.bonusDraws++; 
/* 113 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardCelestial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */