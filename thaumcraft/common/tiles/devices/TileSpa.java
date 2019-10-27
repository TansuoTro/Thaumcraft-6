/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import net.minecraftforge.fluids.capability.IFluidTankProperties;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ public class TileSpa extends TileThaumcraftInventory implements IFluidHandler {
/*     */   private boolean mix;
/*     */   private int counter;
/*     */   public FluidTank tank;
/*     */   
/*     */   public TileSpa() {
/*  31 */     super(1);
/*     */ 
/*     */     
/*  34 */     this.mix = true;
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
/*  98 */     this.counter = 0;
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
/* 192 */     this.tank = new FluidTank(5000);
/*     */   } public void toggleMix() { this.mix = !this.mix; syncTile(false); func_70296_d(); } public boolean getMix() { return this.mix; } public void readSyncNBT(NBTTagCompound nbttagcompound) { this.mix = nbttagcompound.func_74767_n("mix"); this.tank.readFromNBT(nbttagcompound); } public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) { nbttagcompound.func_74757_a("mix", this.mix);
/*     */     this.tank.writeToNBT(nbttagcompound);
/*     */     return nbttagcompound; } public boolean func_94041_b(int par1, ItemStack stack) { return stack.func_77973_b() instanceof thaumcraft.common.items.consumables.ItemBathSalts; } public String func_70005_c_() { return "thaumcraft.spa"; } public boolean func_145818_k_() { return false; }
/*     */   public ITextComponent func_145748_c_() { return null; }
/* 197 */   public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) { return (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing)); }
/*     */   
/*     */   public int[] func_180463_a(EnumFacing side) { new int[1][0] = 0;
/*     */     return (side != EnumFacing.UP) ? new int[1] : new int[0]; }
/*     */   
/*     */   public boolean func_180462_a(int index, ItemStack itemStackIn, EnumFacing side) { return (side != EnumFacing.UP); }
/*     */   
/*     */   @Nullable
/* 205 */   public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) { if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
/* 206 */       return (T)this.tank; 
/* 207 */     return (T)super.getCapability(capability, facing); }
/*     */   public boolean func_180461_b(int index, ItemStack stack, EnumFacing side) { return (side != EnumFacing.UP); }
/*     */   public void func_73660_a() { super.func_73660_a(); if (!this.field_145850_b.field_72995_K && this.counter++ % 40 == 0 && !this.field_145850_b.func_175640_z(this.field_174879_c) && hasIngredients()) { Block b = this.field_145850_b.func_180495_p(this.field_174879_c.func_177984_a()).func_177230_c(); int m = b.func_176201_c(this.field_145850_b.func_180495_p(this.field_174879_c.func_177984_a())); Block tb = null; if (this.mix) { tb = BlocksTC.purifyingFluid; } else { tb = this.tank.getFluid().getFluid().getBlock(); }  if (b == tb && m == 0) { for (int xx = -2; xx <= 2; xx++) { for (int zz = -2; zz <= 2; zz++) { BlockPos p = func_174877_v().func_177982_a(xx, 1, zz); if (isValidLocation(p, true, tb)) { consumeIngredients(); this.field_145850_b.func_175656_a(p, tb.func_176223_P()); checkQuanta(p); // Byte code: goto -> 267 }  }  }  } else if (isValidLocation(this.field_174879_c.func_177984_a(), false, tb)) { consumeIngredients(); this.field_145850_b.func_175656_a(this.field_174879_c.func_177984_a(), tb.func_176223_P()); checkQuanta(this.field_174879_c.func_177984_a()); }  }  }
/*     */   private void checkQuanta(BlockPos pos) { Block b = this.field_145850_b.func_180495_p(pos).func_177230_c(); if (b instanceof BlockFluidBase) { float p = ((BlockFluidBase)b).getQuantaPercentage(this.field_145850_b, pos); if (p < 1.0F) { int md = (int)(1.0F / p) - 1; if (md >= 0 && md < 16) this.field_145850_b.func_175656_a(pos, b.func_176203_a(md));  }  }  } private boolean hasIngredients() { if (this.mix) { if ((this.tank.getInfo()).fluid == null || !(this.tank.getInfo()).fluid.containsFluid(new FluidStack(FluidRegistry.WATER, 1000)))
/*     */         return false;  if (!(func_70301_a(0).func_77973_b() instanceof thaumcraft.common.items.consumables.ItemBathSalts))
/* 212 */         return false;  } else if ((this.tank.getInfo()).fluid == null || !this.tank.getFluid().getFluid().canBePlacedInWorld() || this.tank.getFluidAmount() < 1000) { return false; }  return true; } public IFluidTankProperties[] getTankProperties() { return this.tank.getTankProperties(); } private void consumeIngredients() { if (this.mix)
/*     */       func_70298_a(0, 1);  drain(1000, true); }
/*     */   private boolean isValidLocation(BlockPos pos, boolean mustBeAdjacent, Block target) { if ((target == Blocks.field_150355_j || target == Blocks.field_150358_i) && this.field_145850_b.field_73011_w.func_177500_n())
/*     */       return false;  Block b = this.field_145850_b.func_180495_p(pos).func_177230_c(); IBlockState bb = this.field_145850_b.func_180495_p(pos.func_177977_b()); int m = b.func_176201_c(this.field_145850_b.func_180495_p(pos)); if (bb.isSideSolid(this.field_145850_b, pos.func_177977_b(), EnumFacing.UP) && b.func_176200_f(this.field_145850_b, pos) && (b != target || m != 0)) { if (!mustBeAdjacent)
/*     */         return true;  return BlockUtils.isBlockTouching(this.field_145850_b, pos, target.func_176203_a(0)); }  return false; }
/* 217 */   public int fill(FluidStack resource, boolean doFill) { func_70296_d();
/* 218 */     syncTile(false);
/* 219 */     return this.tank.fill(resource, doFill); }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidStack drain(FluidStack resource, boolean doDrain) {
/* 224 */     FluidStack fs = this.tank.drain(resource, doDrain);
/* 225 */     func_70296_d();
/* 226 */     syncTile(false);
/* 227 */     return fs;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack drain(int maxDrain, boolean doDrain) {
/* 232 */     FluidStack fs = this.tank.drain(maxDrain, doDrain);
/* 233 */     func_70296_d();
/* 234 */     syncTile(false);
/* 235 */     return fs;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileSpa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */