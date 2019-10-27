/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.casters.IInteractWithCaster;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ public class TileTube
/*     */   extends TileThaumcraft
/*     */   implements IEssentiaTransport, IInteractWithCaster, ITickable
/*     */ {
/*     */   public static final int freq = 5;
/*  41 */   public EnumFacing facing = EnumFacing.NORTH;
/*     */   public boolean[] openSides = { true, true, true, true, true, true };
/*  43 */   Aspect essentiaType = null;
/*  44 */   int essentiaAmount = 0;
/*  45 */   Aspect suctionType = null;
/*  46 */   int suction = 0;
/*  47 */   int venting = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  52 */     this.essentiaType = Aspect.getAspect(nbttagcompound.func_74779_i("type"));
/*  53 */     this.essentiaAmount = nbttagcompound.func_74762_e("amount");
/*     */     
/*  55 */     this.facing = EnumFacing.field_82609_l[nbttagcompound.func_74762_e("side")];
/*  56 */     byte[] sides = nbttagcompound.func_74770_j("open");
/*  57 */     if (sides != null && sides.length == 6) {
/*  58 */       for (int a = 0; a < 6; a++) {
/*  59 */         this.openSides[a] = (sides[a] == 1);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  66 */     if (this.essentiaType != null) nbttagcompound.func_74778_a("type", this.essentiaType.getTag()); 
/*  67 */     nbttagcompound.func_74768_a("amount", this.essentiaAmount);
/*     */     
/*  69 */     byte[] sides = new byte[6];
/*  70 */     for (int a = 0; a < 6; a++) {
/*  71 */       sides[a] = this.openSides[a] ? 1 : 0;
/*     */     }
/*  73 */     nbttagcompound.func_74768_a("side", this.facing.ordinal());
/*  74 */     nbttagcompound.func_74773_a("open", sides);
/*  75 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/*  80 */     super.func_145839_a(nbttagcompound);
/*  81 */     this.suctionType = Aspect.getAspect(nbttagcompound.func_74779_i("stype"));
/*  82 */     this.suction = nbttagcompound.func_74762_e("samount");
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbttagcompound) {
/*  87 */     super.func_189515_b(nbttagcompound);
/*  88 */     if (this.suctionType != null) nbttagcompound.func_74778_a("stype", this.suctionType.getTag()); 
/*  89 */     nbttagcompound.func_74768_a("samount", this.suction);
/*  90 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*  94 */   int count = 0;
/*     */   
/*  96 */   int ventColor = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/* 101 */     if (this.venting > 0) this.venting--; 
/* 102 */     if (this.count == 0) this.count = this.field_145850_b.field_73012_v.nextInt(10); 
/* 103 */     if (!this.field_145850_b.field_72995_K) {
/* 104 */       if (this.venting <= 0) {
/* 105 */         if (++this.count % 2 == 0) {
/* 106 */           calculateSuction(null, false, false);
/* 107 */           checkVenting();
/* 108 */           if (this.essentiaType != null && this.essentiaAmount == 0) this.essentiaType = null; 
/*     */         } 
/* 110 */         if (this.count % 5 == 0 && this.suction > 0) {
/* 111 */           equalizeWithNeighbours(false);
/*     */         }
/*     */       }
/*     */     
/* 115 */     } else if (this.venting > 0) {
/* 116 */       Random r = new Random((hashCode() * 4));
/* 117 */       float rp = r.nextFloat() * 360.0F;
/* 118 */       float ry = r.nextFloat() * 360.0F;
/* 119 */       double fx = (-MathHelper.func_76126_a(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F));
/* 120 */       double fz = (MathHelper.func_76134_b(ry / 180.0F * 3.1415927F) * MathHelper.func_76134_b(rp / 180.0F * 3.1415927F));
/* 121 */       double fy = -MathHelper.func_76126_a(rp / 180.0F * 3.1415927F);
/*     */       
/* 123 */       FXDispatcher.INSTANCE.drawVentParticles(this.field_174879_c
/* 124 */           .func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, fx / 5.0D, fy / 5.0D, fz / 5.0D, this.ventColor);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void calculateSuction(Aspect filter, boolean restrict, boolean directional) {
/* 131 */     this.suction = 0;
/* 132 */     this.suctionType = null;
/* 133 */     EnumFacing loc = null;
/* 134 */     for (int dir = 0; dir < 6; dir++) {
/*     */       try {
/* 136 */         loc = EnumFacing.field_82609_l[dir];
/* 137 */         if (!directional || this.facing == loc.func_176734_d())
/*     */         {
/* 139 */           if (isConnectable(loc)) {
/* 140 */             TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, loc);
/* 141 */             if (te != null) {
/* 142 */               IEssentiaTransport ic = (IEssentiaTransport)te;
/* 143 */               if (filter == null || ic.getSuctionType(loc.func_176734_d()) == null || ic.getSuctionType(loc.func_176734_d()) == filter)
/*     */               {
/* 145 */                 if (filter != null || getEssentiaAmount(loc) <= 0 || ic
/* 146 */                   .getSuctionType(loc.func_176734_d()) == null || 
/* 147 */                   getEssentiaType(loc) == ic.getSuctionType(loc.func_176734_d()))
/*     */                 {
/* 149 */                   if (filter == null || getEssentiaAmount(loc) <= 0 || getEssentiaType(loc) == null || ic
/* 150 */                     .getSuctionType(loc.func_176734_d()) == null || 
/* 151 */                     getEssentiaType(loc) == ic.getSuctionType(loc.func_176734_d()))
/*     */                   
/* 153 */                   { int suck = ic.getSuctionAmount(loc.func_176734_d());
/* 154 */                     if (suck > 0 && suck > this.suction + 1)
/* 155 */                     { Aspect st = ic.getSuctionType(loc.func_176734_d());
/* 156 */                       if (st == null) st = filter; 
/* 157 */                       setSuction(st, restrict ? (suck / 2) : (suck - 1)); }  }  }  } 
/*     */             } 
/*     */           }  } 
/* 160 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   void checkVenting() {
/* 165 */     EnumFacing loc = null;
/* 166 */     for (int dir = 0; dir < 6; dir++) {
/*     */       try {
/* 168 */         loc = EnumFacing.field_82609_l[dir];
/* 169 */         if (isConnectable(loc)) {
/* 170 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, loc);
/* 171 */           if (te != null) {
/* 172 */             IEssentiaTransport ic = (IEssentiaTransport)te;
/* 173 */             int suck = ic.getSuctionAmount(loc.func_176734_d());
/* 174 */             if (this.suction > 0 && (suck == this.suction || suck == this.suction - 1) && this.suctionType != ic.getSuctionType(loc.func_176734_d()) && !(te instanceof TileTubeFilter))
/*     */             
/* 176 */             { int c = -1;
/* 177 */               if (this.suctionType != null) {
/* 178 */                 c = ModConfig.aspectOrder.indexOf(this.suctionType);
/*     */               }
/* 180 */               this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.tube, 1, c);
/* 181 */               this.venting = 40; } 
/*     */           } 
/*     */         } 
/* 184 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   void equalizeWithNeighbours(boolean directional) {
/* 189 */     EnumFacing loc = null;
/*     */     
/* 191 */     if (this.essentiaAmount > 0)
/*     */       return; 
/* 193 */     for (int dir = 0; dir < 6; dir++) {
/*     */       try {
/* 195 */         loc = EnumFacing.field_82609_l[dir];
/* 196 */         if ((!directional || this.facing != loc.func_176734_d()) && 
/* 197 */           isConnectable(loc)) {
/*     */           
/* 199 */           TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c, loc);
/* 200 */           if (te != null) {
/* 201 */             IEssentiaTransport ic = (IEssentiaTransport)te;
/* 202 */             if (ic.canOutputTo(loc.func_176734_d()))
/*     */             {
/* 204 */               if ((getSuctionType(null) == null || getSuctionType(null) == ic.getEssentiaType(loc.func_176734_d()) || ic
/* 205 */                 .getEssentiaType(loc.func_176734_d()) == null) && 
/* 206 */                 getSuctionAmount(null) > ic.getSuctionAmount(loc.func_176734_d()) && 
/* 207 */                 getSuctionAmount(null) >= ic.getMinimumSuction()) {
/*     */                 
/* 209 */                 Aspect a = getSuctionType(null);
/* 210 */                 if (a == null) {
/* 211 */                   a = ic.getEssentiaType(loc.func_176734_d());
/* 212 */                   if (a == null) a = ic.getEssentiaType(null);
/*     */                 
/*     */                 } 
/* 215 */                 int am = addEssentia(a, ic.takeEssentia(a, 1, loc.func_176734_d()), loc);
/*     */                 
/* 217 */                 if (am > 0) {
/* 218 */                   if (this.field_145850_b.field_73012_v.nextInt(100) == 0)
/* 219 */                     this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.tube, 0, 0);  return;
/*     */                 } 
/*     */               }  } 
/*     */           } 
/*     */         } 
/* 224 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConnectable(EnumFacing face) {
/* 230 */     if (face == null) return false; 
/* 231 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInputFrom(EnumFacing face) {
/* 236 */     if (face == null) return false; 
/* 237 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canOutputTo(EnumFacing face) {
/* 242 */     if (face == null) return false; 
/* 243 */     return this.openSides[face.ordinal()];
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {
/* 248 */     this.suctionType = aspect;
/* 249 */     this.suction = amount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 254 */   public Aspect getSuctionType(EnumFacing loc) { return this.suctionType; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public int getSuctionAmount(EnumFacing loc) { return this.suction; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public Aspect getEssentiaType(EnumFacing loc) { return this.essentiaType; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 269 */   public int getEssentiaAmount(EnumFacing loc) { return this.essentiaAmount; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) {
/* 274 */     if (canOutputTo(face) && this.essentiaType == aspect && this.essentiaAmount > 0 && amount > 0) {
/* 275 */       this.essentiaAmount--;
/* 276 */       if (this.essentiaAmount <= 0) this.essentiaType = null; 
/* 277 */       func_70296_d();
/* 278 */       return 1;
/*     */     } 
/* 280 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) {
/* 285 */     if (canInputFrom(face) && this.essentiaAmount == 0 && amount > 0) {
/* 286 */       this.essentiaType = aspect;
/* 287 */       this.essentiaAmount++;
/* 288 */       func_70296_d();
/* 289 */       return 1;
/*     */     } 
/* 291 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 296 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 303 */     if (i == 0) {
/*     */       
/* 305 */       if (this.field_145850_b.field_72995_K) {
/* 306 */         this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, SoundsTC.creak, SoundCategory.AMBIENT, 1.0F, 1.3F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/* 308 */       return true;
/*     */     } 
/*     */     
/* 311 */     if (i == 1) {
/*     */       
/* 313 */       if (this.field_145850_b.field_72995_K) {
/* 314 */         if (this.venting <= 0)
/* 315 */           this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, SoundEvents.field_187659_cY, SoundCategory.BLOCKS, 0.1F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, false); 
/* 316 */         this.venting = 50;
/* 317 */         if (j == -1 || j >= ModConfig.aspectOrder.size()) {
/* 318 */           this.ventColor = 11184810;
/*     */         } else {
/* 320 */           this.ventColor = ((Aspect)ModConfig.aspectOrder.get(j)).getColor();
/*     */         } 
/*     */       } 
/* 323 */       return true;
/*     */     } 
/*     */     
/* 326 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCasterRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos bp, EnumFacing side, EnumHand hand) {
/* 336 */     RayTraceResult hit = RayTracer.retraceBlock(world, player, this.field_174879_c);
/* 337 */     if (hit == null) return false;
/*     */     
/* 339 */     if (hit.subHit >= 0 && hit.subHit < 6) {
/*     */       
/* 341 */       player.field_70170_p.func_184134_a(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, SoundsTC.tool, SoundCategory.BLOCKS, 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/* 342 */       player.func_184609_a(hand);
/* 343 */       func_70296_d();
/* 344 */       syncTile(true);
/* 345 */       this.openSides[hit.subHit] = !this.openSides[hit.subHit];
/* 346 */       EnumFacing dir = EnumFacing.field_82609_l[hit.subHit];
/* 347 */       TileEntity tile = world.func_175625_s(this.field_174879_c.func_177972_a(dir));
/* 348 */       if (tile != null && tile instanceof TileTube) {
/* 349 */         ((TileTube)tile).openSides[dir.func_176734_d().ordinal()] = this.openSides[hit.subHit];
/* 350 */         ((TileTube)tile).syncTile(true);
/* 351 */         tile.func_70296_d();
/*     */       } 
/*     */       
/* 354 */       return true;
/*     */     } 
/*     */     
/* 357 */     if (hit.subHit == 6) {
/*     */       
/* 359 */       player.field_70170_p.func_184134_a(bp.func_177958_n() + 0.5D, bp.func_177956_o() + 0.5D, bp.func_177952_p() + 0.5D, SoundsTC.tool, SoundCategory.BLOCKS, 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/* 360 */       player.func_184609_a(hand);
/* 361 */       int a = this.facing.ordinal();
/* 362 */       func_70296_d();
/* 363 */       while (++a < 20) {
/* 364 */         if (canConnectSide(EnumFacing.field_82609_l[a % 6].func_176734_d()) && 
/* 365 */           isConnectable(EnumFacing.field_82609_l[a % 6].func_176734_d())) {
/* 366 */           a %= 6;
/* 367 */           this.facing = EnumFacing.field_82609_l[a];
/* 368 */           syncTile(true);
/* 369 */           func_70296_d();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 374 */       return true;
/*     */     } 
/*     */     
/* 377 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/* 384 */     return new AxisAlignedBB(
/* 385 */         func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/* 386 */         func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 1), (func_174877_v().func_177952_p() + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 391 */   public RayTraceResult rayTrace(World world, Vec3d vec3d, Vec3d vec3d1, RayTraceResult fullblock) { return fullblock; }
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
/* 402 */     TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(side));
/* 403 */     return (tile != null && tile instanceof IEssentiaTransport);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids) {
/* 408 */     float min = 0.375F;
/* 409 */     float max = 0.625F;
/* 410 */     if (canConnectSide(EnumFacing.DOWN)) cuboids.add(new IndexedCuboid6(Integer.valueOf(0), new Cuboid6((this.field_174879_c.func_177958_n() + min), this.field_174879_c.func_177956_o(), (this.field_174879_c.func_177952_p() + min), (this.field_174879_c.func_177958_n() + max), this.field_174879_c.func_177956_o() + 0.375D, (this.field_174879_c.func_177952_p() + max)))); 
/* 411 */     if (canConnectSide(EnumFacing.UP)) cuboids.add(new IndexedCuboid6(Integer.valueOf(1), new Cuboid6((this.field_174879_c.func_177958_n() + min), this.field_174879_c.func_177956_o() + 0.625D, (this.field_174879_c.func_177952_p() + min), (this.field_174879_c.func_177958_n() + max), (this.field_174879_c.func_177956_o() + 1), (this.field_174879_c.func_177952_p() + max)))); 
/* 412 */     if (canConnectSide(EnumFacing.NORTH)) cuboids.add(new IndexedCuboid6(Integer.valueOf(2), new Cuboid6((this.field_174879_c.func_177958_n() + min), (this.field_174879_c.func_177956_o() + min), this.field_174879_c.func_177952_p(), (this.field_174879_c.func_177958_n() + max), (this.field_174879_c.func_177956_o() + max), this.field_174879_c.func_177952_p() + 0.375D))); 
/* 413 */     if (canConnectSide(EnumFacing.SOUTH)) cuboids.add(new IndexedCuboid6(Integer.valueOf(3), new Cuboid6((this.field_174879_c.func_177958_n() + min), (this.field_174879_c.func_177956_o() + min), this.field_174879_c.func_177952_p() + 0.625D, (this.field_174879_c.func_177958_n() + max), (this.field_174879_c.func_177956_o() + max), (this.field_174879_c.func_177952_p() + 1)))); 
/* 414 */     if (canConnectSide(EnumFacing.WEST)) cuboids.add(new IndexedCuboid6(Integer.valueOf(4), new Cuboid6(this.field_174879_c.func_177958_n(), (this.field_174879_c.func_177956_o() + min), (this.field_174879_c.func_177952_p() + min), this.field_174879_c.func_177958_n() + 0.375D, (this.field_174879_c.func_177956_o() + max), (this.field_174879_c.func_177952_p() + max)))); 
/* 415 */     if (canConnectSide(EnumFacing.EAST)) cuboids.add(new IndexedCuboid6(Integer.valueOf(5), new Cuboid6(this.field_174879_c.func_177958_n() + 0.625D, (this.field_174879_c.func_177956_o() + min), (this.field_174879_c.func_177952_p() + min), (this.field_174879_c.func_177958_n() + 1), (this.field_174879_c.func_177956_o() + max), (this.field_174879_c.func_177952_p() + max))));
/*     */     
/* 417 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6(this.field_174879_c.func_177958_n() + 0.375D, this.field_174879_c.func_177956_o() + 0.375D, this.field_174879_c.func_177952_p() + 0.375D, this.field_174879_c.func_177958_n() + 0.625D, this.field_174879_c.func_177956_o() + 0.625D, this.field_174879_c.func_177952_p() + 0.625D)));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileTube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */