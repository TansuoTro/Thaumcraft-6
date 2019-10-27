/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileLevitator
/*     */   extends TileThaumcraft
/*     */   implements ITickable
/*     */ {
/*  29 */   private int[] ranges = { 4, 8, 16, 32 };
/*  30 */   private int range = 1;
/*  31 */   private int rangeActual = 0;
/*  32 */   private int counter = 0;
/*  33 */   private int vis = 0;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  37 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/*  38 */     if (this.rangeActual > this.ranges[this.range]) {
/*  39 */       this.rangeActual = 0;
/*     */     }
/*  41 */     int p = this.counter % this.ranges[this.range];
/*  42 */     if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177967_a(facing, 1 + p)).func_185914_p()) {
/*  43 */       if (1 + p < this.rangeActual) {
/*  44 */         this.rangeActual = 1 + p;
/*     */       }
/*  46 */       this.counter = -1;
/*  47 */     } else if (1 + p > this.rangeActual) {
/*  48 */       this.rangeActual = 1 + p;
/*     */     } 
/*     */     
/*  51 */     this.counter++;
/*     */     
/*  53 */     if (!this.field_145850_b.field_72995_K && this.vis < 10) {
/*  54 */       this.vis = (int)(this.vis + AuraHelper.drainVis(this.field_145850_b, func_174877_v(), 1.0F, false) * 1200.0F);
/*  55 */       func_70296_d();
/*  56 */       syncTile(false);
/*     */     } 
/*     */     
/*  59 */     if (this.rangeActual > 0 && this.vis > 0 && BlockStateUtils.isEnabled(func_145832_p())) {
/*     */       
/*  61 */       List<Entity> targets = this.field_145850_b.func_72872_a(Entity.class, new AxisAlignedBB((this.field_174879_c
/*     */             
/*  63 */             .func_177958_n() - ((facing.func_82601_c() < 0) ? this.rangeActual : 0)), (this.field_174879_c
/*  64 */             .func_177956_o() - ((facing.func_96559_d() < 0) ? this.rangeActual : 0)), (this.field_174879_c
/*  65 */             .func_177952_p() - ((facing.func_82599_e() < 0) ? this.rangeActual : 0)), (this.field_174879_c
/*  66 */             .func_177958_n() + 1 + ((facing.func_82601_c() > 0) ? this.rangeActual : 0)), (this.field_174879_c
/*  67 */             .func_177956_o() + 1 + ((facing.func_96559_d() > 0) ? this.rangeActual : 0)), (this.field_174879_c
/*  68 */             .func_177952_p() + 1 + ((facing.func_82599_e() > 0) ? this.rangeActual : 0))));
/*  69 */       boolean lifted = false;
/*  70 */       if (targets.size() > 0)
/*  71 */         for (Entity e : targets) {
/*  72 */           if (!(e instanceof net.minecraft.entity.item.EntityItem) && !e.func_70104_M() && !(e instanceof net.minecraft.entity.passive.EntityHorse))
/*  73 */             continue;  lifted = true;
/*  74 */           drawFXAt(e);
/*  75 */           drawFX(facing, 0.6D);
/*  76 */           if (e.func_70093_af() && facing == EnumFacing.UP) {
/*  77 */             if (e.field_70181_x < 0.0D) e.field_70181_x *= 0.8999999761581421D; 
/*     */           } else {
/*  79 */             e.field_70159_w += (0.1F * facing.func_82601_c());
/*  80 */             e.field_70181_x += (0.1F * facing.func_96559_d());
/*  81 */             e.field_70179_y += (0.1F * facing.func_82599_e());
/*  82 */             if (facing.func_176740_k() != EnumFacing.Axis.Y && !e.field_70122_E) {
/*  83 */               if (e.field_70181_x < 0.0D) e.field_70181_x *= 0.8999999761581421D; 
/*  84 */               e.field_70181_x += 0.07999999821186066D;
/*     */             } 
/*  86 */             if (e.field_70159_w > 0.3499999940395355D) e.field_70159_w = 0.3499999940395355D; 
/*  87 */             if (e.field_70181_x > 0.3499999940395355D) e.field_70181_x = 0.3499999940395355D; 
/*  88 */             if (e.field_70179_y > 0.3499999940395355D) e.field_70179_y = 0.3499999940395355D; 
/*  89 */             if (e.field_70159_w < -0.3499999940395355D) e.field_70159_w = -0.3499999940395355D; 
/*  90 */             if (e.field_70181_x < -0.3499999940395355D) e.field_70181_x = -0.3499999940395355D; 
/*  91 */             if (e.field_70179_y < -0.3499999940395355D) e.field_70179_y = -0.3499999940395355D; 
/*     */           } 
/*  93 */           e.field_70143_R = 0.0F;
/*  94 */           this.vis -= getCost();
/*  95 */           if (this.vis <= 0)
/*     */             break; 
/*     */         }  
/*  98 */       drawFX(facing, 0.1D);
/*     */       
/* 100 */       if (lifted && !this.field_145850_b.field_72995_K && this.counter % 20 == 0) {
/* 101 */         func_70296_d();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawFX(EnumFacing facing, double c) {
/* 107 */     if (this.field_145850_b.field_72995_K && this.field_145850_b.field_73012_v.nextFloat() < c) {
/* 108 */       float x = this.field_174879_c.func_177958_n() + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F;
/* 109 */       float y = this.field_174879_c.func_177956_o() + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F;
/* 110 */       float z = this.field_174879_c.func_177952_p() + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F;
/* 111 */       FXDispatcher.INSTANCE.drawLevitatorParticles(x, y, z, facing
/* 112 */           .func_82601_c() / 50.0D, facing.func_96559_d() / 50.0D, facing.func_82599_e() / 50.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawFXAt(Entity e) {
/* 117 */     if (this.field_145850_b.field_72995_K && this.field_145850_b.field_73012_v.nextFloat() < 0.1F) {
/* 118 */       float x = (float)(e.field_70165_t + ((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * e.field_70130_N));
/* 119 */       float y = (float)(e.field_70163_u + (this.field_145850_b.field_73012_v.nextFloat() * e.field_70131_O));
/* 120 */       float z = (float)(e.field_70161_v + ((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * e.field_70130_N));
/* 121 */       FXDispatcher.INSTANCE.drawLevitatorParticles(x, y, z, (this.field_145850_b.field_73012_v
/* 122 */           .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01D, (this.field_145850_b.field_73012_v
/* 123 */           .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01D, (this.field_145850_b.field_73012_v
/* 124 */           .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbt) {
/* 131 */     this.range = nbt.func_74771_c("range");
/* 132 */     this.vis = nbt.func_74762_e("vis");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
/* 138 */     nbt.func_74774_a("range", (byte)this.range);
/* 139 */     nbt.func_74768_a("vis", this.vis);
/* 140 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/* 144 */   public int getCost() { return this.ranges[this.range] * 2; }
/*     */ 
/*     */   
/*     */   public void increaseRange(EntityPlayer playerIn) {
/* 148 */     this.rangeActual = 0;
/* 149 */     if (!this.field_145850_b.field_72995_K) {
/* 150 */       this.range++;
/* 151 */       if (this.range >= this.ranges.length) this.range = 0; 
/* 152 */       func_70296_d();
/* 153 */       syncTile(false);
/* 154 */       playerIn.func_145747_a(new TextComponentString(String.format(
/* 155 */               I18n.func_74838_a("tc.levitator"), new Object[] {
/* 156 */                 Integer.valueOf(this.ranges[this.range]), 
/* 157 */                 Integer.valueOf(getCost())
/*     */               })));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 164 */   public RayTraceResult rayTrace(World world, Vec3d vec3d, Vec3d vec3d1, RayTraceResult fullblock) { return fullblock; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids) {
/* 170 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/* 171 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(0), getCuboidByFacing(facing).add(new Vector3(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p()))));
/*     */   }
/*     */   
/*     */   public Cuboid6 getCuboidByFacing(EnumFacing facing) {
/* 175 */     switch (facing) { default:
/* 176 */         return new Cuboid6(0.375D, 0.0625D, 0.375D, 0.625D, 0.125D, 0.625D);
/* 177 */       case DOWN: return new Cuboid6(0.375D, 0.875D, 0.375D, 0.625D, 0.9375D, 0.625D);
/* 178 */       case EAST: return new Cuboid6(0.0625D, 0.375D, 0.375D, 0.125D, 0.625D, 0.625D);
/* 179 */       case WEST: return new Cuboid6(0.875D, 0.375D, 0.375D, 0.9375D, 0.625D, 0.625D);
/* 180 */       case SOUTH: return new Cuboid6(0.375D, 0.375D, 0.0625D, 0.625D, 0.625D, 0.125D);
/* 181 */       case NORTH: break; }  return new Cuboid6(0.375D, 0.375D, 0.875D, 0.625D, 0.625D, 0.9375D);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileLevitator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */