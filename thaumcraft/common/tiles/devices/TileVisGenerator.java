/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.energy.CapabilityEnergy;
/*     */ import net.minecraftforge.energy.IEnergyStorage;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileVisGenerator
/*     */   extends TileThaumcraft
/*     */   implements ITickable, IEnergyStorage
/*     */ {
/*     */   protected int energy;
/*     */   
/*     */   public void func_73660_a() {
/*  29 */     if (!this.field_145850_b.field_72995_K && BlockStateUtils.isEnabled(func_145832_p())) {
/*  30 */       recharge();
/*  31 */       EnumFacing face = BlockStateUtils.getFacing(func_145832_p());
/*  32 */       IBlockState state = func_145831_w().func_180495_p(func_174877_v().func_177972_a(face));
/*  33 */       Block block = state.func_177230_c();
/*  34 */       if (block.hasTileEntity(state)) {
/*     */         
/*  36 */         TileEntity tileentity = func_145831_w().func_175625_s(func_174877_v().func_177972_a(face));
/*  37 */         if (tileentity != null)
/*     */         {
/*  39 */           if (tileentity.hasCapability(CapabilityEnergy.ENERGY, face.func_176734_d())) {
/*     */             
/*  41 */             IEnergyStorage capability = (IEnergyStorage)tileentity.getCapability(CapabilityEnergy.ENERGY, face.func_176734_d());
/*  42 */             if (capability.canReceive()) {
/*  43 */               int energyExtracted = Math.min(this.energy, 20);
/*  44 */               energyExtracted = capability.receiveEnergy(energyExtracted, false);
/*  45 */               if (energyExtracted > 0) {
/*  46 */                 this.energy -= energyExtracted;
/*  47 */                 func_70296_d();
/*  48 */                 if (this.energy == 0) syncTile(false); 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void recharge() {
/*  58 */     if (this.energy == 0) {
/*  59 */       float vis = AuraHandler.drainVis(func_145831_w(), func_174877_v(), 1.0F, false);
/*  60 */       this.energy = (int)(vis * 1000.0F);
/*  61 */       func_70296_d();
/*  62 */       syncTile(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public void readSyncNBT(NBTTagCompound nbt) { this.energy = nbt.func_74762_e("energy"); }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
/*  74 */     nbt.func_74768_a("energy", this.energy);
/*  75 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
/*  83 */     EnumFacing face = BlockStateUtils.getFacing(func_145832_p());
/*  84 */     return ((face == facing && capability == CapabilityEnergy.ENERGY) || super.hasCapability(capability, facing));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
/*  92 */     EnumFacing face = BlockStateUtils.getFacing(func_145832_p());
/*  93 */     if (face == facing && capability == CapabilityEnergy.ENERGY) return (T)this; 
/*  94 */     return (T)super.getCapability(capability, facing);
/*     */   }
/*     */ 
/*     */   
/*  98 */   protected final int capacity = 1000;
/*  99 */   protected final int maxExtract = 20;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public int receiveEnergy(int maxReceive, boolean simulate) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public int extractEnergy(int maxExtract, boolean simulate) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public int getEnergyStored() { return this.energy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public int getMaxEnergyStored() { return 1000; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public boolean canExtract() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public boolean canReceive() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileVisGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */