/*     */ package thaumcraft.api.research.theorycraft;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TheorycraftCard
/*     */ {
/*  17 */   private long seed = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  24 */     if (this.seed < 0L) setSeed(System.nanoTime()); 
/*  25 */     return this.seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public boolean initialize(EntityPlayer player, ResearchTableData data) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public boolean isAidOnly() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getInspirationCost();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public String getResearchCategory() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getLocalizedName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getLocalizedText();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public ItemStack[] getRequiredItems() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[] getRequiredItemsConsumed() {
/*  90 */     if (getRequiredItems() != null) {
/*  91 */       boolean[] b = new boolean[getRequiredItems().length];
/*  92 */       Arrays.fill(b, false);
/*  93 */       return b;
/*     */     } 
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean activate(EntityPlayer paramEntityPlayer, ResearchTableData paramResearchTableData);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setSeed(long seed) { this.seed = Math.abs(seed); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound serialize() {
/* 122 */     NBTTagCompound nbt = new NBTTagCompound();
/* 123 */     nbt.func_74772_a("seed", this.seed);
/* 124 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deserialize(NBTTagCompound nbt) {
/* 132 */     if (nbt == null)
/* 133 */       return;  this.seed = nbt.func_74763_f("seed");
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\TheorycraftCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */