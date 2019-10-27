/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.devices.BlockCondenserLattice;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileCondenser
/*     */   extends TileThaumcraft
/*     */   implements ITickable, IEssentiaTransport
/*     */ {
/*     */   private int essentia;
/*     */   private int flux;
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  33 */     this.essentia = nbttagcompound.func_74765_d("essentia");
/*  34 */     this.flux = nbttagcompound.func_74765_d("flux");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  40 */     nbttagcompound.func_74777_a("essentia", (short)this.essentia);
/*  41 */     nbttagcompound.func_74777_a("flux", (short)this.flux);
/*  42 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  47 */   private int MAX = 100;
/*  48 */   private int count = 0;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  52 */     if (this.latticeCount < 0.0F) triggerCheck(); 
/*  53 */     this.count++;
/*  54 */     if (BlockStateUtils.isEnabled(func_145832_p()) && this.latticeCount > 0.0F) {
/*  55 */       if (this.field_145850_b.field_72995_K) {
/*  56 */         if (this.essentia > 0 && this.uncloggedList.size() > 0 && this.count % Math.max(3, this.interval / 50) == 0) {
/*  57 */           BlockPos p = BlockPos.func_177969_a(((Long)this.uncloggedList.get(this.field_145850_b.field_73012_v.nextInt(this.uncloggedList.size()))).longValue());
/*  58 */           if (p != null) {
/*  59 */             FXDispatcher.INSTANCE.spark(p
/*  60 */                 .func_177958_n() + 0.5D, p.func_177956_o() + 0.5D, p.func_177952_p() + 0.5D, 4.5F + this.field_145850_b.field_73012_v.nextFloat(), 0.33F + this.field_145850_b.field_73012_v
/*  61 */                 .nextFloat() * 0.66F, 0.33F + this.field_145850_b.field_73012_v.nextFloat() * 0.66F, 0.33F + this.field_145850_b.field_73012_v.nextFloat() * 0.66F, 0.8F);
/*     */           }
/*     */         } 
/*     */       } else {
/*  65 */         if (this.count % 5 == 0 && this.essentia < this.MAX) {
/*  66 */           fill();
/*     */         }
/*  68 */         if (this.interval > 0 && this.essentia >= this.cost && this.flux < this.MAX && this.count % this.interval == 0 && AuraHelper.getFlux(func_145831_w(), func_174877_v()) >= 1.0F) {
/*  69 */           AuraHelper.drainFlux(func_145831_w(), func_174877_v(), 1.0F, false);
/*  70 */           this.essentia -= this.cost;
/*  71 */           this.flux++;
/*  72 */           if (this.field_145850_b.field_73012_v.nextInt(50) == 0)
/*  73 */             makeLatticeDirty(); 
/*  74 */           syncTile(false);
/*  75 */           func_70296_d();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void makeLatticeDirty() {
/*  83 */     if (this.uncloggedList.size() > 0) {
/*  84 */       int q = this.field_145850_b.field_73012_v.nextInt(this.uncloggedList.size());
/*  85 */       if (q == 0) q = this.field_145850_b.field_73012_v.nextInt(this.uncloggedList.size()); 
/*  86 */       BlockPos p = BlockPos.func_177969_a(((Long)this.uncloggedList.get(q)).longValue());
/*  87 */       if (p != null) {
/*  88 */         IBlockState bs = this.field_145850_b.func_180495_p(p);
/*  89 */         if (bs.func_177230_c() == BlocksTC.condenserlattice) {
/*  90 */           this.field_145850_b.func_180501_a(p, BlocksTC.condenserlatticeDirty.func_176223_P(), 3);
/*  91 */           ((BlockCondenserLattice)bs.func_177230_c()).triggerUpdate(this.field_145850_b, p);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fill() {
/*  98 */     for (EnumFacing face : EnumFacing.field_176754_o) {
/*  99 */       TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, face);
/* 100 */       if (te != null) {
/* 101 */         IEssentiaTransport ic = (IEssentiaTransport)te;
/* 102 */         Aspect ta = null;
/* 103 */         if (!ic.canOutputTo(face.func_176734_d()))
/* 104 */           return;  if (ic.getEssentiaAmount(face.func_176734_d()) > 0 && 
/* 105 */           ic.getSuctionAmount(face.func_176734_d()) < getSuctionAmount(face) && 
/* 106 */           getSuctionAmount(face) >= ic.getMinimumSuction()) {
/* 107 */           ta = ic.getEssentiaType(face.func_176734_d());
/*     */         }
/*     */ 
/*     */         
/* 111 */         if (ta != null) {
/* 112 */           if (ta != Aspect.FLUX) {
/* 113 */             this.essentia += ic.takeEssentia(ta, 1, face.func_176734_d());
/*     */           } else {
/* 115 */             makeLatticeDirty();
/* 116 */           }  syncTile(false);
/* 117 */           func_70296_d();
/* 118 */           if (this.essentia >= this.MAX)
/*     */             break; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/* 125 */   private ArrayList<Long> history = new ArrayList();
/* 126 */   private ArrayList<Long> blockList = new ArrayList();
/* 127 */   private ArrayList<Long> uncloggedList = new ArrayList();
/* 128 */   public float latticeCount = -1.0F;
/* 129 */   public int interval = 0;
/* 130 */   public int cost = 0;
/*     */ 
/*     */   
/*     */   public void triggerCheck() {
/* 134 */     this.history.clear();
/* 135 */     this.blockList.clear();
/* 136 */     this.uncloggedList.clear();
/* 137 */     this.latticeCount = 0.0F;
/* 138 */     this.interval = 0;
/* 139 */     performCheck(this.field_174879_c, true, false);
/* 140 */     this.history.clear();
/*     */ 
/*     */     
/* 143 */     if (this.latticeCount <= 0.0F) {
/* 144 */       this.latticeCount = 0.0F;
/*     */     } else {
/* 146 */       if (this.latticeCount > 40.0F) this.latticeCount = 40.0F; 
/* 147 */       this.interval = Math.round(600.0F - this.latticeCount * 15.0F);
/* 148 */       if (this.interval < 5) this.interval = 5; 
/* 149 */       this.cost = (int)(4.0D + Math.sqrt(this.blockList.size()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void performCheck(BlockPos pos, boolean skip, boolean clogged) {
/* 155 */     if (this.latticeCount < 0.0F)
/* 156 */       return;  this.history.add(Long.valueOf(pos.func_177986_g()));
/* 157 */     boolean found = false;
/* 158 */     int sides = 0;
/* 159 */     for (EnumFacing face : EnumFacing.field_82609_l) {
/* 160 */       if (!skip || face == EnumFacing.UP) {
/* 161 */         BlockPos p2 = pos.func_177972_a(face);
/* 162 */         IBlockState bs = this.field_145850_b.func_180495_p(p2);
/* 163 */         boolean lattice = (bs.func_177230_c() == BlocksTC.condenserlattice);
/* 164 */         boolean latticeDirty = (bs.func_177230_c() == BlocksTC.condenserlatticeDirty);
/* 165 */         if (skip && latticeDirty) {
/* 166 */           clogged = true;
/*     */         }
/* 168 */         if (lattice || latticeDirty) sides++; 
/* 169 */         if (!this.history.contains(Long.valueOf(p2.func_177986_g()))) {
/*     */           
/* 171 */           if (face == EnumFacing.DOWN && this.field_145850_b.func_180495_p(p2).func_177230_c() == BlocksTC.condenser) {
/* 172 */             this.latticeCount = -99.0F;
/*     */             
/*     */             return;
/*     */           } 
/* 176 */           if (func_174877_v().func_177956_o() < p2.func_177956_o() && 
/* 177 */             func_174877_v().func_177951_i(p2) <= 74.0D && (
/* 178 */             lattice || latticeDirty))
/* 179 */           { this.blockList.add(Long.valueOf(p2.func_177986_g()));
/* 180 */             if (lattice) this.uncloggedList.add(Long.valueOf(p2.func_177986_g())); 
/* 181 */             found = true;
/* 182 */             performCheck(p2, false, (clogged || latticeDirty));
/* 183 */             if (skip)
/*     */               break;  } 
/*     */         } 
/*     */       } 
/* 187 */     }  if (found && !clogged) {
/* 188 */       float f = 1.0F - 0.15F * sides;
/*     */       
/* 190 */       this.latticeCount += f;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 196 */   public boolean isConnectable(EnumFacing face) { return (face != EnumFacing.UP); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public boolean canInputFrom(EnumFacing face) { return (face != EnumFacing.UP && face != EnumFacing.DOWN); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public boolean canOutputTo(EnumFacing face) { return (face == EnumFacing.DOWN); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */ 
/*     */ 
/*     */   
/* 214 */   public Aspect getSuctionType(EnumFacing face) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public int getSuctionAmount(EnumFacing face) { return (face == EnumFacing.DOWN || this.essentia >= this.MAX) ? 0 : 128; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) {
/* 224 */     int amt = (canOutputTo(face) && (aspect == null || aspect == Aspect.FLUX)) ? Math.min(amount, this.flux) : 0;
/* 225 */     if (amt > 0) {
/* 226 */       this.flux -= amt;
/* 227 */       syncTile(false);
/* 228 */       func_70296_d();
/*     */     } 
/* 230 */     return amt;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) {
/* 235 */     int amt = canInputFrom(face) ? Math.min(amount, this.MAX - this.essentia) : 0;
/* 236 */     if (amt > 0) {
/* 237 */       syncTile(false);
/* 238 */       func_70296_d();
/*     */     } 
/* 240 */     return amt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 245 */   public Aspect getEssentiaType(EnumFacing face) { return Aspect.FLUX; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public int getEssentiaAmount(EnumFacing face) { return this.flux; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public int getMinimumSuction() { return 0; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileCondenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */