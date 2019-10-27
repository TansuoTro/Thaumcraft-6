/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileCentrifuge
/*     */   extends TileThaumcraft
/*     */   implements IAspectContainer, IEssentiaTransport, ITickable
/*     */ {
/*  22 */   public Aspect aspectOut = null;
/*  23 */   public Aspect aspectIn = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  29 */     this.aspectIn = Aspect.getAspect(nbttagcompound.func_74779_i("aspectIn"));
/*  30 */     this.aspectOut = Aspect.getAspect(nbttagcompound.func_74779_i("aspectOut"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  36 */     if (this.aspectIn != null) nbttagcompound.func_74778_a("aspectIn", this.aspectIn.getTag()); 
/*  37 */     if (this.aspectOut != null) nbttagcompound.func_74778_a("aspectOut", this.aspectOut.getTag()); 
/*  38 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AspectList getAspects() {
/*  44 */     AspectList al = new AspectList();
/*  45 */     if (this.aspectOut != null) al.add(this.aspectOut, 1); 
/*  46 */     return al;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int addToContainer(Aspect tt, int am) {
/*  52 */     if (am > 0 && this.aspectOut == null) {
/*  53 */       this.aspectOut = tt;
/*  54 */       func_70296_d();
/*  55 */       this.field_145850_b.markAndNotifyBlock(func_174877_v(), this.field_145850_b.func_175726_f(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), 3);
/*  56 */       am--;
/*     */     } 
/*  58 */     return am;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am) {
/*  63 */     if (this.aspectOut != null && tt == this.aspectOut) {
/*  64 */       this.aspectOut = null;
/*  65 */       func_70296_d();
/*  66 */       this.field_145850_b.markAndNotifyBlock(func_174877_v(), this.field_145850_b.func_175726_f(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), 3);
/*  67 */       return true;
/*     */     } 
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt) {
/*  80 */     if (amt == 1 && tag == this.aspectOut) return true; 
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot) {
/*  86 */     for (Aspect tt : ot.getAspects()) {
/*  87 */       if (tt == this.aspectOut) return true; 
/*     */     } 
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public int containerContains(Aspect tag) { return (tag == this.aspectOut) ? 1 : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public boolean doesContainerAccept(Aspect tag) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public boolean isConnectable(EnumFacing face) { return (face == EnumFacing.UP || face == EnumFacing.DOWN); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public boolean canInputFrom(EnumFacing face) { return (face == EnumFacing.DOWN); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public boolean canOutputTo(EnumFacing face) { return (face == EnumFacing.UP); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */ 
/*     */ 
/*     */   
/* 125 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public Aspect getSuctionType(EnumFacing face) { return null; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSuctionAmount(EnumFacing face) {
/* 135 */     return (face == EnumFacing.DOWN) ? (
/* 136 */       gettingPower() ? 0 : ((this.aspectIn == null) ? 128 : 64)) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 141 */   public Aspect getEssentiaType(EnumFacing loc) { return this.aspectOut; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public int getEssentiaAmount(EnumFacing loc) { return (this.aspectOut != null) ? 1 : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) { return (canOutputTo(face) && takeFromContainer(aspect, amount)) ? amount : 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) {
/* 156 */     if (this.aspectIn == null && !aspect.isPrimal()) {
/* 157 */       this.aspectIn = aspect;
/* 158 */       this.process = 39;
/* 159 */       func_70296_d();
/* 160 */       this.field_145850_b.markAndNotifyBlock(func_174877_v(), this.field_145850_b.func_175726_f(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), 3);
/* 161 */       return 1;
/*     */     } 
/* 163 */     return 0;
/*     */   }
/*     */   
/* 166 */   int count = 0;
/* 167 */   int process = 0;
/* 168 */   float rotationSpeed = 0.0F;
/* 169 */   public float rotation = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/* 174 */     if (!this.field_145850_b.field_72995_K) {
/* 175 */       if (!gettingPower()) {
/* 176 */         if (this.aspectOut == null && this.aspectIn == null && ++this.count % 5 == 0) {
/* 177 */           drawEssentia();
/*     */         }
/* 179 */         if (this.process > 0) this.process--; 
/* 180 */         if (this.aspectOut == null && this.aspectIn != null && this.process == 0) {
/* 181 */           processEssentia();
/*     */         }
/*     */       } 
/*     */     } else {
/* 185 */       if (this.aspectIn != null && !gettingPower() && this.rotationSpeed < 20.0F) this.rotationSpeed += 2.0F; 
/* 186 */       if ((this.aspectIn == null || gettingPower()) && this.rotationSpeed > 0.0F) this.rotationSpeed -= 0.5F; 
/* 187 */       int pr = (int)this.rotation;
/* 188 */       this.rotation += this.rotationSpeed;
/* 189 */       if (this.rotation % 180.0F <= 20.0F && pr % 180 >= 160 && this.rotationSpeed > 0.0F) {
/* 190 */         this.field_145850_b.func_184134_a(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o() + 0.5D, func_174877_v().func_177952_p() + 0.5D, SoundsTC.pump, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   void processEssentia() {
/* 196 */     Aspect[] comps = this.aspectIn.getComponents();
/* 197 */     this.aspectOut = comps[this.field_145850_b.field_73012_v.nextInt(2)];
/* 198 */     this.aspectIn = null;
/* 199 */     func_70296_d();
/* 200 */     this.field_145850_b.markAndNotifyBlock(func_174877_v(), this.field_145850_b.func_175726_f(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), 3);
/*     */   }
/*     */   
/*     */   void drawEssentia() {
/* 204 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), EnumFacing.DOWN);
/* 205 */     if (te != null) {
/* 206 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 207 */       if (!ic.canOutputTo(EnumFacing.UP))
/* 208 */         return;  Aspect ta = null;
/* 209 */       if (ic.getEssentiaAmount(EnumFacing.UP) > 0 && 
/* 210 */         ic.getSuctionAmount(EnumFacing.UP) < getSuctionAmount(EnumFacing.DOWN) && 
/* 211 */         getSuctionAmount(EnumFacing.DOWN) >= ic.getMinimumSuction()) {
/* 212 */         ta = ic.getEssentiaType(EnumFacing.UP);
/*     */       }
/*     */ 
/*     */       
/* 216 */       if (ta != null && !ta.isPrimal() && ic.getSuctionAmount(EnumFacing.UP) < getSuctionAmount(EnumFacing.DOWN) && 
/* 217 */         ic.takeEssentia(ta, 1, EnumFacing.UP) == 1) {
/* 218 */         this.aspectIn = ta;
/* 219 */         this.process = 39;
/* 220 */         func_70296_d();
/* 221 */         this.field_145850_b.markAndNotifyBlock(func_174877_v(), this.field_145850_b.func_175726_f(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), 3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAspects(AspectList aspects) {}
/*     */ 
/*     */ 
/*     */   
/* 232 */   public boolean canRenderBreaking() { return true; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileCentrifuge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */