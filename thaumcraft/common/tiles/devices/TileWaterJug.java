/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockCauldron;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import net.minecraftforge.fluids.capability.IFluidTankProperties;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ import vazkii.botania.api.item.IPetalApothecary;
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
/*     */ public class TileWaterJug
/*     */   extends TileThaumcraft
/*     */   implements ITickable, IFluidHandler
/*     */ {
/*  41 */   int zone = 0;
/*  42 */   int counter = 0;
/*  43 */   ArrayList<Integer> handlers = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*  47 */   public void readSyncNBT(NBTTagCompound nbttagcompound) { this.tank.readFromNBT(nbttagcompound); }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  52 */     this.tank.writeToNBT(nbttagcompound);
/*  53 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  58 */     super.func_145839_a(nbt);
/*     */     
/*  60 */     NBTTagList nbttaglist = nbt.func_150295_c("handlers", 3);
/*  61 */     this.handlers = new ArrayList();
/*  62 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/*  64 */       NBTTagInt tag = (NBTTagInt)nbttaglist.func_179238_g(i);
/*  65 */       this.handlers.add(Integer.valueOf(tag.func_150287_d()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
/*  71 */     super.func_189515_b(nbt);
/*     */     
/*  73 */     NBTTagList nbttaglist = new NBTTagList();
/*  74 */     for (int i = 0; i < this.handlers.size(); i++)
/*     */     {
/*  76 */       NBTTagInt nBTTagInt = new NBTTagInt(((Integer)this.handlers.get(i)).intValue());
/*     */     }
/*  78 */     nbt.func_74782_a("handlers", nbttaglist);
/*  79 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  85 */     this.counter++;
/*  86 */     if (this.field_145850_b.field_72995_K) {
/*  87 */       if (this.tcount > 0) {
/*  88 */         if (this.tcount % 5 == 0) {
/*  89 */           int x = this.zc / 5 % 5;
/*  90 */           int y = this.zc / 5 / 5 % 3;
/*  91 */           int z = this.zc % 5;
/*  92 */           FXDispatcher.INSTANCE.waterTrailFx(func_174877_v(), func_174877_v().func_177982_a(x - 2, y - 1, z - 2), this.counter, 2650102, 0.1F);
/*     */         } 
/*  94 */         this.tcount--;
/*     */       }
/*     */     
/*  97 */     } else if (this.counter % 5 == 0) {
/*  98 */       this.zone++;
/*  99 */       int x = this.zone / 5 % 5;
/* 100 */       int y = this.zone / 5 / 5 % 3;
/* 101 */       int z = this.zone % 5;
/* 102 */       IBlockState bs = this.field_145850_b.func_180495_p(func_174877_v().func_177982_a(x - 2, y - 1, z - 2));
/* 103 */       TileEntity te = this.field_145850_b.func_175625_s(func_174877_v().func_177982_a(x - 2, y - 1, z - 2));
/* 104 */       if (((te != null && (te instanceof IFluidHandler || te instanceof IPetalApothecary || te
/* 105 */         .hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP))) || bs.func_177230_c() == Blocks.field_150383_bp) && 
/* 106 */         !this.handlers.contains(Integer.valueOf(this.zone))) {
/* 107 */         this.handlers.add(Integer.valueOf(this.zone));
/* 108 */         func_70296_d();
/*     */       } 
/*     */ 
/*     */       
/* 112 */       int i = 0;
/* 113 */       while (i < this.handlers.size() && this.tank.getFluidAmount() >= 25) {
/* 114 */         int zz = ((Integer)this.handlers.get(i)).intValue();
/* 115 */         x = zz / 5 % 5;
/* 116 */         y = zz / 5 / 5 % 3;
/* 117 */         z = zz % 5;
/* 118 */         IBlockState bs2 = this.field_145850_b.func_180495_p(func_174877_v().func_177982_a(x - 2, y - 1, z - 2));
/* 119 */         TileEntity tile = this.field_145850_b.func_175625_s(func_174877_v().func_177982_a(x - 2, y - 1, z - 2));
/* 120 */         if (tile != null && (tile instanceof IFluidHandler || tile
/* 121 */           .hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP))) {
/* 122 */           IFluidHandler fh = null;
/* 123 */           if (tile instanceof IFluidHandler) {
/* 124 */             fh = (IFluidHandler)tile;
/*     */           } else {
/* 126 */             fh = (IFluidHandler)tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
/*     */           } 
/* 128 */           if (fh != null) {
/* 129 */             int q = fh.fill(new FluidStack(FluidRegistry.WATER, 25), true);
/* 130 */             if (q > 0) {
/* 131 */               drain(new FluidStack(FluidRegistry.WATER, q), true);
/* 132 */               func_70296_d();
/* 133 */               this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 1, zz);
/*     */               break;
/*     */             } 
/*     */           } 
/* 137 */         } else if (tile != null && tile instanceof IPetalApothecary && this.tank.getFluidAmount() >= 1000) {
/* 138 */           IPetalApothecary pa = (IPetalApothecary)tile;
/* 139 */           if (!pa.hasWater()) {
/* 140 */             pa.setWater(true);
/* 141 */             this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 1, zz);
/* 142 */             drain(new FluidStack(FluidRegistry.WATER, 1000), true);
/*     */           } 
/* 144 */         } else if (bs2.func_177230_c() == Blocks.field_150383_bp && this.tank.getFluidAmount() >= 333) {
/* 145 */           if (((Integer)bs2.func_177229_b(BlockCauldron.field_176591_a)).intValue() < 3) {
/*     */             
/* 147 */             BlockPos pp = func_174877_v().func_177982_a(x - 2, y - 1, z - 2);
/* 148 */             this.field_145850_b.func_180501_a(pp, bs2.func_177231_a(BlockCauldron.field_176591_a), 2);
/* 149 */             this.field_145850_b.func_175666_e(pp, bs2.func_177230_c());
/* 150 */             this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 1, zz);
/* 151 */             drain(new FluidStack(FluidRegistry.WATER, 333), true);
/*     */           } 
/*     */         } else {
/* 154 */           this.handlers.remove(i);
/* 155 */           func_70296_d();
/*     */           continue;
/*     */         } 
/* 158 */         i++;
/*     */       } 
/*     */       
/* 161 */       if (this.tank.getFluidAmount() < 1000) {
/* 162 */         float da = (1000 - this.tank.getFluidAmount()) / 1000.0F;
/* 163 */         if (da > 0.1F) da = 0.1F; 
/* 164 */         float dv = AuraHelper.drainVis(func_145831_w(), func_174877_v(), da, false);
/* 165 */         int wa = (int)(1000.0F * dv);
/* 166 */         if (wa > 0) {
/*     */           
/* 168 */           this.tank.fill(new FluidStack(FluidRegistry.WATER, wa), true);
/* 169 */           func_70296_d();
/* 170 */           if (this.tank.getFluidAmount() >= this.tank.getCapacity()) syncTile(false);
/*     */         
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 178 */   int zc = 0;
/* 179 */   int tcount = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 184 */     if (i == 1) {
/*     */       
/* 186 */       if (this.field_145850_b.field_72995_K) {
/* 187 */         this.zc = j;
/* 188 */         this.tcount = 5;
/*     */       } 
/* 190 */       return true;
/*     */     } 
/* 192 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public FluidTank tank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 1000);
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
/* 203 */     return ((facing == EnumFacing.UP && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) || super
/* 204 */       .hasCapability(capability, facing));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
/* 212 */     if (facing == EnumFacing.UP && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
/* 213 */       return (T)this.tank; 
/* 214 */     return (T)super.getCapability(capability, facing);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 219 */   public IFluidTankProperties[] getTankProperties() { return this.tank.getTankProperties(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   public int fill(FluidStack resource, boolean doFill) { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidStack drain(FluidStack resource, boolean doDrain) {
/* 229 */     boolean f = (this.tank.getFluidAmount() >= this.tank.getCapacity());
/* 230 */     FluidStack fs = this.tank.drain(resource, doDrain);
/* 231 */     func_70296_d();
/* 232 */     if (f && this.tank.getFluidAmount() < this.tank.getCapacity()) syncTile(false); 
/* 233 */     return fs;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack drain(int maxDrain, boolean doDrain) {
/* 238 */     boolean f = (this.tank.getFluidAmount() >= this.tank.getCapacity());
/* 239 */     FluidStack fs = this.tank.drain(maxDrain, doDrain);
/* 240 */     func_70296_d();
/* 241 */     if (f && this.tank.getFluidAmount() < this.tank.getCapacity()) syncTile(false); 
/* 242 */     return fs;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileWaterJug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */