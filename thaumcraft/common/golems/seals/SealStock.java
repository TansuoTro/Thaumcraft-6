/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealStock
/*     */   extends SealFiltered
/*     */   implements ISealConfigToggles
/*     */ {
/*  25 */   public String getKey() { return "thaumcraft:stock"; }
/*     */ 
/*     */   
/*  28 */   int delay = (new Random(System.nanoTime())).nextInt(50);
/*     */ 
/*     */ 
/*     */   
/*  32 */   public int getFilterSize() { return 9; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  37 */     if (this.delay++ % 20 != 0)
/*  38 */       return;  IItemHandler inv = ThaumcraftInvHelper.getItemHandlerAt(world, (seal.getSealPos()).pos, (seal.getSealPos()).face);
/*  39 */     if (inv != null) {
/*  40 */       for (int a = 0; a < 9; a++) {
/*  41 */         int amt = ThaumcraftInvHelper.countTotalItemsIn(inv, getFilterSlot(a), new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value));
/*  42 */         if (amt < getFilterSlotSize(a)) {
/*  43 */           ItemStack fs = getFilterSlot(a).func_77946_l();
/*  44 */           fs.func_190920_e(Math.min(fs.func_77976_d(), getFilterSlotSize(a) - amt));
/*  45 */           fs = ThaumcraftInvHelper.hasRoomFor(world, (seal.getSealPos()).pos, (seal.getSealPos()).face, fs);
/*  46 */           if (!fs.func_190926_b()) {
/*  47 */             GolemHelper.requestProvisioning(world, (seal.getSealPos()).pos, (seal.getSealPos()).face, fs);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
/*     */ 
/*     */   
/*  59 */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) { return true; }
/*     */ 
/*     */   
/*  62 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta"), new ISealConfigToggles.SealToggle(true, "pnbt", "golem.prop.nbt"), new ISealConfigToggles.SealToggle(false, "pore", "golem.prop.ore"), new ISealConfigToggles.SealToggle(false, "pmod", "golem.prop.mod") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) {
/*  76 */     IItemHandler inv = ThaumcraftInvHelper.getItemHandlerAt(world, pos, side);
/*  77 */     if (inv != null) {
/*  78 */       return true;
/*     */     }
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/*  88 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_stock");
/*     */ 
/*     */ 
/*     */   
/*  92 */   public int[] getGuiCategories() { return new int[] { 1, 3, 0, 4 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public EnumGolemTrait[] getRequiredTags() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public EnumGolemTrait[] getForbiddenTags() { return new EnumGolemTrait[] { EnumGolemTrait.CLUMSY }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskSuspension(World world, Task task) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public boolean hasStacksizeLimiters() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public boolean isBlacklist() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealStock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */