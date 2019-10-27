/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ public class TileAlembic
/*     */   extends TileThaumcraft
/*     */   implements IAspectContainer, IEssentiaTransport
/*     */ {
/*     */   public Aspect aspect;
/*     */   
/*  23 */   public AspectList getAspects() { return (this.aspect != null) ? (new AspectList()).add(this.aspect, this.amount) : new AspectList(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAspects(AspectList aspects) {}
/*     */ 
/*     */   
/*  30 */   public Aspect aspectFilter = null;
/*  31 */   public int amount = 0;
/*  32 */   public int maxAmount = 128;
/*  33 */   public int facing = EnumFacing.DOWN.ordinal();
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  38 */     return new AxisAlignedBB(
/*  39 */         func_174877_v().func_177958_n() - 0.1D, func_174877_v().func_177956_o() - 0.1D, func_174877_v().func_177952_p() - 0.1D, 
/*  40 */         func_174877_v().func_177958_n() + 1.1D, func_174877_v().func_177956_o() + 1.1D, func_174877_v().func_177952_p() + 1.1D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  46 */     this.facing = nbttagcompound.func_74771_c("facing");
/*  47 */     this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
/*  48 */     String tag = nbttagcompound.func_74779_i("aspect");
/*  49 */     if (tag != null) this.aspect = Aspect.getAspect(tag); 
/*  50 */     this.amount = nbttagcompound.func_74765_d("amount");
/*     */     
/*  52 */     this.fd = EnumFacing.field_82609_l[this.facing];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  58 */     if (this.aspect != null) nbttagcompound.func_74778_a("aspect", this.aspect.getTag()); 
/*  59 */     if (this.aspectFilter != null) nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag()); 
/*  60 */     nbttagcompound.func_74777_a("amount", (short)this.amount);
/*  61 */     nbttagcompound.func_74774_a("facing", (byte)this.facing);
/*  62 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public boolean aboveFurnace = false;
/*  66 */   EnumFacing fd = null;
/*     */ 
/*     */   
/*     */   public int addToContainer(Aspect tt, int am) {
/*  70 */     if (this.aspectFilter != null && tt != this.aspectFilter) return am; 
/*  71 */     if ((this.amount < this.maxAmount && tt == this.aspect) || this.amount == 0) {
/*  72 */       this.aspect = tt;
/*  73 */       int added = Math.min(am, this.maxAmount - this.amount);
/*  74 */       this.amount += added;
/*  75 */       am -= added;
/*     */     } 
/*  77 */     func_70296_d();
/*  78 */     syncTile(false);
/*     */     
/*  80 */     return am;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am) {
/*  85 */     if (this.amount == 0 || this.aspect == null) {
/*  86 */       this.aspect = null;
/*  87 */       this.amount = 0;
/*     */     } 
/*  89 */     if (this.aspect != null && this.amount >= am && tt == this.aspect) {
/*  90 */       this.amount -= am;
/*  91 */       if (this.amount <= 0) {
/*  92 */         this.aspect = null;
/*  93 */         this.amount = 0;
/*     */       } 
/*  95 */       func_70296_d();
/*  96 */       syncTile(false);
/*  97 */       return true;
/*     */     } 
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot) {
/* 104 */     if (this.amount > 0 && this.aspect != null && 
/* 105 */       ot.getAmount(this.aspect) > 0) return true;
/*     */     
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tt, int am) {
/* 112 */     if (this.amount >= am && tt == this.aspect) return true; 
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 118 */   public int containerContains(Aspect tt) { return (tt == this.aspect) ? this.amount : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public boolean doesContainerAccept(Aspect tag) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public boolean isConnectable(EnumFacing face) { return (face != EnumFacing.field_82609_l[this.facing] && face != EnumFacing.DOWN); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public boolean canInputFrom(EnumFacing face) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public boolean canOutputTo(EnumFacing face) { return (face != EnumFacing.field_82609_l[this.facing] && face != EnumFacing.DOWN); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public Aspect getSuctionType(EnumFacing loc) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public int getSuctionAmount(EnumFacing loc) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public Aspect getEssentiaType(EnumFacing loc) { return this.aspect; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public int getEssentiaAmount(EnumFacing loc) { return this.amount; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) { return (canOutputTo(face) && takeFromContainer(aspect, amount)) ? amount : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public int addEssentia(Aspect aspect, int amount, EnumFacing loc) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean processAlembics(World world, BlockPos pos, Aspect aspect) {
/* 195 */     int deep = 1;
/*     */     while (true) {
/* 197 */       TileEntity te = world.func_175625_s(pos.func_177981_b(deep));
/* 198 */       if (te != null && te instanceof TileAlembic) {
/* 199 */         TileAlembic alembic = (TileAlembic)te;
/* 200 */         if (alembic.amount > 0 && alembic.aspect == aspect && 
/* 201 */           alembic.addToContainer(aspect, 1) == 0) return true;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 206 */         deep++; continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 210 */     deep = 1;
/*     */     while (true) {
/* 212 */       TileEntity te = world.func_175625_s(pos.func_177981_b(deep));
/* 213 */       if (te != null && te instanceof TileAlembic) {
/* 214 */         TileAlembic alembic = (TileAlembic)te;
/* 215 */         if ((alembic.aspectFilter == null || alembic.aspectFilter == aspect) && 
/* 216 */           alembic.addToContainer(aspect, 1) == 0) return true;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 221 */         deep++; continue;
/*     */       }  break;
/*     */     } 
/* 224 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileAlembic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */