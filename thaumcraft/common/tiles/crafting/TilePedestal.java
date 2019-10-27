/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.devices.BlockInlay;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ public class TilePedestal
/*     */   extends TileThaumcraftInventory {
/*     */   public TilePedestal() {
/*  17 */     super(1);
/*  18 */     this.syncedSlots = new int[] { 0 };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  23 */   public int func_70297_j_() { return 1; }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  29 */     return new AxisAlignedBB(
/*  30 */         func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/*  31 */         func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 2), (func_174877_v().func_177952_p() + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public boolean func_94041_b(int par1, ItemStack stack2) { return (stack2.func_190926_b() || func_70301_a(par1).func_190926_b()); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContentsFromInfusion(int par1, ItemStack stack2) {
/*  41 */     func_70299_a(par1, stack2);
/*  42 */     func_70296_d();
/*  43 */     if (!this.field_145850_b.field_72995_K) {
/*  44 */       syncTile(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos findInstabilityMitigator() {
/*  55 */     if (func_145832_p() > 0) {
/*  56 */       BlockPos pp = seekSourceRecursive(this.field_174879_c, func_145832_p());
/*  57 */       if (pp != null) {
/*  58 */         return pp;
/*     */       }
/*     */     } 
/*  61 */     return null;
/*     */   }
/*     */   
/*     */   private BlockPos seekSourceRecursive(BlockPos pos, int lastCharge) {
/*  65 */     for (EnumFacing face : EnumFacing.field_176754_o) {
/*  66 */       BlockPos pp = pos.func_177972_a(face);
/*  67 */       int ss = BlockInlay.getSourceStrengthAt(this.field_145850_b, pp);
/*  68 */       if (ss >= 5) {
/*  69 */         return pp;
/*     */       }
/*  71 */       IBlockState bs = this.field_145850_b.func_180495_p(pp);
/*  72 */       if (bs.func_177228_b().containsKey(BlockInlay.CHARGE)) {
/*  73 */         int charge = ((Integer)bs.func_177229_b(BlockInlay.CHARGE)).intValue();
/*  74 */         if (charge > lastCharge) {
/*  75 */           BlockPos ob = seekSourceRecursive(pp, charge);
/*  76 */           if (ob != null) return ob; 
/*     */         } 
/*     */       } 
/*     */     } 
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/*  86 */     if (i == 11) {
/*     */       
/*  88 */       if (this.field_145850_b.field_72995_K) {
/*  89 */         FXDispatcher.INSTANCE.drawBamf(this.field_174879_c.func_177984_a(), 0.75F, 0.0F, 0.5F, true, true, null);
/*     */       }
/*  91 */       return true;
/*     */     } 
/*  93 */     if (i == 12) {
/*     */       
/*  95 */       if (this.field_145850_b.field_72995_K) {
/*  96 */         FXDispatcher.INSTANCE.drawBamf(this.field_174879_c.func_177984_a(), true, true, null);
/*     */       }
/*  98 */       return true;
/*     */     } 
/* 100 */     if (i == 5) {
/* 101 */       if (this.field_145850_b.field_72995_K) {
/* 102 */         FXDispatcher.INSTANCE.drawPedestalShield(this.field_174879_c);
/*     */       }
/* 104 */       return true;
/*     */     } 
/* 106 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TilePedestal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */