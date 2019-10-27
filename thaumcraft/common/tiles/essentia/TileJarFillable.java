/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileJarFillable
/*     */   extends TileJar
/*     */   implements IAspectSource, IEssentiaTransport
/*     */ {
/*     */   public static final int CAPACITY = 250;
/*  19 */   public Aspect aspect = null;
/*  20 */   public Aspect aspectFilter = null;
/*  21 */   public int amount = 0;
/*  22 */   public int facing = 2;
/*     */   
/*     */   public boolean blocked = false;
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  28 */     this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
/*  29 */     this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
/*  30 */     this.amount = nbttagcompound.func_74765_d("Amount");
/*  31 */     this.facing = nbttagcompound.func_74771_c("facing");
/*  32 */     this.blocked = nbttagcompound.func_74767_n("blocked");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  38 */     if (this.aspect != null) nbttagcompound.func_74778_a("Aspect", this.aspect.getTag()); 
/*  39 */     if (this.aspectFilter != null) nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag()); 
/*  40 */     nbttagcompound.func_74777_a("Amount", (short)this.amount);
/*  41 */     nbttagcompound.func_74774_a("facing", (byte)this.facing);
/*  42 */     nbttagcompound.func_74757_a("blocked", this.blocked);
/*  43 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public AspectList getAspects() {
/*  48 */     AspectList al = new AspectList();
/*  49 */     if (this.aspect != null && this.amount > 0) al.add(this.aspect, this.amount); 
/*  50 */     return al;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAspects(AspectList aspects) {
/*  55 */     if (aspects != null && aspects.size() > 0) {
/*  56 */       this.aspect = aspects.getAspectsSortedByAmount()[0];
/*  57 */       this.amount = aspects.getAmount(aspects.getAspectsSortedByAmount()[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int addToContainer(Aspect tt, int am) {
/*  63 */     if (am == 0) return am; 
/*  64 */     if ((this.amount < 250 && tt == this.aspect) || this.amount == 0) {
/*     */       
/*  66 */       this.aspect = tt;
/*  67 */       int added = Math.min(am, 250 - this.amount);
/*  68 */       this.amount += added;
/*  69 */       am -= added;
/*     */     } 
/*     */     
/*  72 */     syncTile(false);
/*  73 */     func_70296_d();
/*  74 */     return am;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am) {
/*  79 */     if (this.amount >= am && tt == this.aspect) {
/*  80 */       this.amount -= am;
/*  81 */       if (this.amount <= 0) {
/*  82 */         this.aspect = null;
/*  83 */         this.amount = 0;
/*     */       } 
/*  85 */       syncTile(false);
/*  86 */       func_70296_d();
/*  87 */       return true;
/*     */     } 
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt) {
/* 100 */     if (this.amount >= amt && tag == this.aspect) return true; 
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot) {
/* 106 */     for (Aspect tt : ot.getAspects()) {
/* 107 */       if (this.amount > 0 && tt == this.aspect) return true; 
/*     */     } 
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int containerContains(Aspect tag) {
/* 114 */     if (tag == this.aspect) return this.amount; 
/* 115 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public boolean doesContainerAccept(Aspect tag) { return (this.aspectFilter != null) ? tag.equals(this.aspectFilter) : 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public boolean isConnectable(EnumFacing face) { return (face == EnumFacing.UP); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public boolean canInputFrom(EnumFacing face) { return (face == EnumFacing.UP); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public boolean canOutputTo(EnumFacing face) { return (face == EnumFacing.UP); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */ 
/*     */ 
/*     */   
/* 147 */   public int getMinimumSuction() { return (this.aspectFilter != null) ? 64 : 32; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public Aspect getSuctionType(EnumFacing loc) { return (this.aspectFilter != null) ? this.aspectFilter : this.aspect; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc) {
/* 157 */     if (this.amount < 250) {
/* 158 */       if (this.aspectFilter != null) {
/* 159 */         return 64;
/*     */       }
/* 161 */       return 32;
/*     */     } 
/*     */     
/* 164 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public Aspect getEssentiaType(EnumFacing loc) { return this.aspect; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public int getEssentiaAmount(EnumFacing loc) { return this.amount; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) { return (canOutputTo(face) && takeFromContainer(aspect, amount)) ? amount : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) { return canInputFrom(face) ? (amount - addToContainer(aspect, amount)) : 0; }
/*     */ 
/*     */   
/* 188 */   int count = 0;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/* 192 */     this; if (!this.field_145850_b.field_72995_K && ++this.count % 5 == 0 && this.amount < 250) {
/* 193 */       fillJar();
/*     */     }
/*     */   }
/*     */   
/*     */   void fillJar() {
/* 198 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, EnumFacing.UP);
/* 199 */     if (te != null) {
/* 200 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 201 */       if (!ic.canOutputTo(EnumFacing.DOWN))
/*     */         return; 
/* 203 */       Aspect ta = null;
/* 204 */       if (this.aspectFilter != null) {
/* 205 */         ta = this.aspectFilter;
/*     */       }
/* 207 */       else if (this.aspect != null && this.amount > 0) {
/* 208 */         ta = this.aspect;
/*     */       }
/* 210 */       else if (ic.getEssentiaAmount(EnumFacing.DOWN) > 0 && 
/* 211 */         ic.getSuctionAmount(EnumFacing.DOWN) < getSuctionAmount(EnumFacing.UP) && 
/* 212 */         getSuctionAmount(EnumFacing.UP) >= ic.getMinimumSuction()) {
/* 213 */         ta = ic.getEssentiaType(EnumFacing.DOWN);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 219 */       if (ta != null && ic.getSuctionAmount(EnumFacing.DOWN) < getSuctionAmount(EnumFacing.UP)) {
/* 220 */         addToContainer(ta, ic.takeEssentia(ta, 1, EnumFacing.DOWN));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public boolean isBlocked() { return this.blocked; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileJarFillable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */