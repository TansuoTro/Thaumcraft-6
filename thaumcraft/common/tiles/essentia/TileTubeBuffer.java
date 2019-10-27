/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.devices.TileBellows;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileTubeBuffer
/*     */   extends TileTube
/*     */   implements IAspectContainer
/*     */ {
/*  31 */   public AspectList aspects = new AspectList();
/*  32 */   public final int MAXAMOUNT = 10;
/*  33 */   public byte[] chokedSides = { 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  40 */     this.aspects.readFromNBT(nbttagcompound);
/*  41 */     byte[] sides = nbttagcompound.func_74770_j("open");
/*  42 */     if (sides != null && sides.length == 6)
/*  43 */       for (int a = 0; a < 6; a++) {
/*  44 */         this.openSides[a] = (sides[a] == 1);
/*     */       } 
/*  46 */     this.chokedSides = nbttagcompound.func_74770_j("choke");
/*  47 */     if (this.chokedSides == null || this.chokedSides.length < 6) {
/*  48 */       this.chokedSides = new byte[] { 0, 0, 0, 0, 0, 0 };
/*     */     }
/*  50 */     this.facing = EnumFacing.field_82609_l[nbttagcompound.func_74762_e("side")];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  56 */     this.aspects.writeToNBT(nbttagcompound);
/*  57 */     byte[] sides = new byte[6];
/*  58 */     for (int a = 0; a < 6; a++) {
/*  59 */       sides[a] = this.openSides[a] ? 1 : 0;
/*     */     }
/*  61 */     nbttagcompound.func_74773_a("open", sides);
/*  62 */     nbttagcompound.func_74773_a("choke", this.chokedSides);
/*  63 */     nbttagcompound.func_74768_a("side", this.facing.ordinal());
/*  64 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public AspectList getAspects() { return this.aspects; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAspects(AspectList aspects) {}
/*     */ 
/*     */   
/*     */   public int addToContainer(Aspect tt, int am) {
/*  78 */     if (am != 1) return am; 
/*  79 */     if (this.aspects.visSize() < 10) {
/*  80 */       this.aspects.add(tt, am);
/*  81 */       func_70296_d();
/*  82 */       syncTile(false);
/*  83 */       return 0;
/*     */     } 
/*  85 */     return am;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am) {
/*  90 */     if (this.aspects.getAmount(tt) >= am) {
/*  91 */       this.aspects.remove(tt, am);
/*  92 */       func_70296_d();
/*  93 */       syncTile(false);
/*  94 */       return true;
/*     */     } 
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public boolean doesContainerContainAmount(Aspect tag, int amt) { return (this.aspects.getAmount(tag) >= amt); }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public boolean doesContainerContain(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public int containerContains(Aspect tag) { return this.aspects.getAmount(tag); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public boolean doesContainerAccept(Aspect tag) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public boolean isConnectable(EnumFacing face) { return this.openSides[face.ordinal()]; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public boolean canInputFrom(EnumFacing face) { return this.openSides[face.ordinal()]; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public boolean canOutputTo(EnumFacing face) { return this.openSides[face.ordinal()]; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */ 
/*     */ 
/*     */   
/* 146 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public Aspect getSuctionType(EnumFacing loc) { return null; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSuctionAmount(EnumFacing loc) {
/* 156 */     return (this.chokedSides[loc.ordinal()] == 2) ? 0 : ((this.bellows <= 0 || this.chokedSides[loc
/* 157 */         .ordinal()] == 1) ? 1 : (this.bellows * 32));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 162 */   public Aspect getEssentiaType(EnumFacing loc) { return (this.aspects.size() > 0) ? this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.getAspects().length)] : null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public int getEssentiaAmount(EnumFacing loc) { return this.aspects.visSize(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) {
/* 172 */     if (!canOutputTo(face)) return 0; 
/* 173 */     TileEntity te = null;
/* 174 */     IEssentiaTransport ic = null;
/* 175 */     int suction = 0;
/* 176 */     te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, face);
/* 177 */     if (te != null) {
/* 178 */       ic = (IEssentiaTransport)te;
/* 179 */       suction = ic.getSuctionAmount(face.func_176734_d());
/*     */     } 
/* 181 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 182 */       if (canOutputTo(dir) && dir != face) {
/* 183 */         te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, dir);
/* 184 */         if (te != null) {
/* 185 */           ic = (IEssentiaTransport)te;
/* 186 */           int sa = ic.getSuctionAmount(dir.func_176734_d());
/* 187 */           Aspect su = ic.getSuctionType(dir.func_176734_d());
/* 188 */           if ((su == aspect || su == null) && suction < sa && getSuctionAmount(dir) < sa) return 0; 
/*     */         } 
/*     */       } 
/* 191 */     }  if (amount > this.aspects.getAmount(aspect)) amount = this.aspects.getAmount(aspect); 
/* 192 */     return takeFromContainer(aspect, amount) ? amount : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 197 */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) { return canInputFrom(face) ? (amount - addToContainer(aspect, amount)) : 0; }
/*     */ 
/*     */   
/* 200 */   int count = 0;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/* 204 */     this.count++;
/* 205 */     if (this.bellows < 0 || this.count % 20 == 0) {
/* 206 */       getBellows();
/*     */     }
/* 208 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 210 */       getClass(); if (this.count % 5 == 0 && this.aspects.visSize() < 10) {
/* 211 */         fillBuffer();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void fillBuffer() {
/* 218 */     TileEntity te = null;
/* 219 */     IEssentiaTransport ic = null;
/* 220 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 221 */       if (canInputFrom(dir)) {
/* 222 */         te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, dir);
/* 223 */         if (te != null) {
/* 224 */           ic = (IEssentiaTransport)te;
/*     */           
/* 226 */           if (ic.getEssentiaAmount(dir.func_176734_d()) > 0 && ic
/* 227 */             .getSuctionAmount(dir.func_176734_d()) < getSuctionAmount(dir) && 
/* 228 */             getSuctionAmount(dir) >= ic.getMinimumSuction()) {
/*     */             
/* 230 */             Aspect ta = ic.getEssentiaType(dir.func_176734_d());
/* 231 */             addToContainer(ta, ic.takeEssentia(ta, 1, dir.func_176734_d()));
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 241 */   int bellows = -1;
/*     */ 
/*     */   
/* 244 */   public void getBellows() { this.bellows = TileBellows.getBellows(this.field_145850_b, this.field_174879_c, EnumFacing.field_82609_l); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCasterRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos bp, EnumFacing side, EnumHand hand) {
/* 252 */     RayTraceResult hit = RayTracer.retraceBlock(world, player, this.field_174879_c);
/* 253 */     if (hit == null) return false;
/*     */     
/* 255 */     if (hit.subHit >= 0 && hit.subHit < 6) {
/*     */       
/* 257 */       player.func_184609_a(hand);
/* 258 */       if (player.func_70093_af()) {
/* 259 */         player.field_70170_p.func_184134_a(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, SoundsTC.squeek, SoundCategory.BLOCKS, 0.6F, 2.0F + world.field_73012_v.nextFloat() * 0.2F, false);
/* 260 */         if (!world.field_72995_K) {
/* 261 */           this.chokedSides[hit.subHit] = (byte)(this.chokedSides[hit.subHit] + 1);
/* 262 */           if (this.chokedSides[hit.subHit] > 2) this.chokedSides[hit.subHit] = 0; 
/* 263 */           func_70296_d();
/* 264 */           syncTile(true);
/*     */         } 
/*     */       } else {
/* 267 */         player.field_70170_p.func_184134_a(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, SoundsTC.tool, SoundCategory.BLOCKS, 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*     */         
/* 269 */         this.openSides[hit.subHit] = !this.openSides[hit.subHit];
/* 270 */         EnumFacing dir = EnumFacing.field_82609_l[hit.subHit];
/* 271 */         TileEntity tile = world.func_175625_s(this.field_174879_c.func_177972_a(dir));
/* 272 */         if (tile != null && tile instanceof TileTube) {
/* 273 */           ((TileTube)tile).openSides[dir.func_176734_d().ordinal()] = this.openSides[hit.subHit];
/* 274 */           ((TileTube)tile).syncTile(true);
/* 275 */           tile.func_70296_d();
/*     */         } 
/* 277 */         func_70296_d();
/* 278 */         syncTile(true);
/*     */       } 
/* 280 */       return true;
/*     */     } 
/*     */     
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canConnectSide(EnumFacing side) {
/* 295 */     TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(side));
/* 296 */     return (tile != null && tile instanceof IEssentiaTransport);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids) {
/* 301 */     float min = 0.375F;
/* 302 */     float max = 0.625F;
/* 303 */     if (canConnectSide(EnumFacing.DOWN)) cuboids.add(new IndexedCuboid6(Integer.valueOf(0), new Cuboid6((this.field_174879_c.func_177958_n() + min), this.field_174879_c.func_177956_o(), (this.field_174879_c.func_177952_p() + min), (this.field_174879_c.func_177958_n() + max), this.field_174879_c.func_177956_o() + 0.5D, (this.field_174879_c.func_177952_p() + max)))); 
/* 304 */     if (canConnectSide(EnumFacing.UP)) cuboids.add(new IndexedCuboid6(Integer.valueOf(1), new Cuboid6((this.field_174879_c.func_177958_n() + min), this.field_174879_c.func_177956_o() + 0.5D, (this.field_174879_c.func_177952_p() + min), (this.field_174879_c.func_177958_n() + max), (this.field_174879_c.func_177956_o() + 1), (this.field_174879_c.func_177952_p() + max)))); 
/* 305 */     if (canConnectSide(EnumFacing.NORTH)) cuboids.add(new IndexedCuboid6(Integer.valueOf(2), new Cuboid6((this.field_174879_c.func_177958_n() + min), (this.field_174879_c.func_177956_o() + min), this.field_174879_c.func_177952_p(), (this.field_174879_c.func_177958_n() + max), (this.field_174879_c.func_177956_o() + max), this.field_174879_c.func_177952_p() + 0.5D))); 
/* 306 */     if (canConnectSide(EnumFacing.SOUTH)) cuboids.add(new IndexedCuboid6(Integer.valueOf(3), new Cuboid6((this.field_174879_c.func_177958_n() + min), (this.field_174879_c.func_177956_o() + min), this.field_174879_c.func_177952_p() + 0.5D, (this.field_174879_c.func_177958_n() + max), (this.field_174879_c.func_177956_o() + max), (this.field_174879_c.func_177952_p() + 1)))); 
/* 307 */     if (canConnectSide(EnumFacing.WEST)) cuboids.add(new IndexedCuboid6(Integer.valueOf(4), new Cuboid6(this.field_174879_c.func_177958_n(), (this.field_174879_c.func_177956_o() + min), (this.field_174879_c.func_177952_p() + min), this.field_174879_c.func_177958_n() + 0.5D, (this.field_174879_c.func_177956_o() + max), (this.field_174879_c.func_177952_p() + max)))); 
/* 308 */     if (canConnectSide(EnumFacing.EAST)) cuboids.add(new IndexedCuboid6(Integer.valueOf(5), new Cuboid6(this.field_174879_c.func_177958_n() + 0.5D, (this.field_174879_c.func_177956_o() + min), (this.field_174879_c.func_177952_p() + min), (this.field_174879_c.func_177958_n() + 1), (this.field_174879_c.func_177956_o() + max), (this.field_174879_c.func_177952_p() + max))));
/*     */     
/* 310 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6((this.field_174879_c.func_177958_n() + 0.25F), (this.field_174879_c.func_177956_o() + 0.25F), (this.field_174879_c.func_177952_p() + 0.25F), (this.field_174879_c.func_177958_n() + 0.75F), (this.field_174879_c.func_177956_o() + 0.75F), (this.field_174879_c.func_177952_p() + 0.75F))));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileTubeBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */