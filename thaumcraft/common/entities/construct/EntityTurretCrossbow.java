/*     */ package thaumcraft.common.entities.construct;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityTippedArrow;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ 
/*     */ public class EntityTurretCrossbow extends EntityOwnedConstruct implements IRangedAttackMob {
/*     */   int loadProgressInt;
/*     */   boolean isLoadInProgress;
/*     */   float loadProgress;
/*     */   float prevLoadProgress;
/*     */   public float loadProgressForRender;
/*     */   boolean attackedLastTick;
/*     */   int attackCount;
/*     */   
/*     */   protected void func_184651_r() {
/*     */     this.field_70714_bg.func_75776_a(1, new EntityAIAttackRanged(this, 0.0D, 20, 60, 24.0F));
/*     */     this.field_70714_bg.func_75776_a(2, new EntityAIWatchTarget(this));
/*     */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
/*     */     this.field_70715_bh.func_75776_a(2, new EntityAINearestValidTarget(this, EntityLiving.class, 5, true, false, IMob.field_82192_a));
/*     */   }
/*     */   
/*     */   public EntityTurretCrossbow(World worldIn, BlockPos pos) {
/*     */     this(worldIn);
/*     */     func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase target, float range) {
/*     */     if (func_184614_ca() != null && !func_184614_ca().func_190926_b() && func_184614_ca().func_190916_E() > 0) {
/*     */       EntityTippedArrow entityarrow = new EntityTippedArrow(this.field_70170_p, this);
/*     */       entityarrow.func_70239_b(2.25D + (range * 2.0F) + this.field_70146_Z.nextGaussian() * 0.25D);
/*     */       entityarrow.func_184555_a(func_184614_ca());
/*     */       Vec3d vec3d = func_70676_i(1.0F);
/*     */       if (!func_184218_aH()) {
/*     */         entityarrow.field_70165_t -= vec3d.field_72450_a * 0.8999999761581421D;
/*     */         entityarrow.field_70163_u -= vec3d.field_72448_b * 0.8999999761581421D;
/*     */         entityarrow.field_70161_v -= vec3d.field_72449_c * 0.8999999761581421D;
/*     */       } else {
/*     */         entityarrow.field_70165_t += vec3d.field_72450_a * 1.75D;
/*     */         entityarrow.field_70163_u += vec3d.field_72448_b * 1.75D;
/*     */         entityarrow.field_70161_v += vec3d.field_72449_c * 1.75D;
/*     */       } 
/*     */       entityarrow.field_70251_a = EntityArrow.PickupStatus.DISALLOWED;
/*     */       double d0 = target.field_70165_t - this.field_70165_t;
/*     */       double d1 = (target.func_174813_aQ()).field_72338_b + target.func_70047_e() + (range * range * 3.0F) - entityarrow.field_70163_u;
/*     */       double d2 = target.field_70161_v - this.field_70161_v;
/*     */       entityarrow.func_70186_c(d0, d1, d2, 2.0F, 2.0F);
/*     */       this.field_70170_p.func_72838_d(entityarrow);
/*     */       this.field_70170_p.func_72960_a(this, (byte)16);
/*     */       func_184185_a(SoundEvents.field_187737_v, 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/*     */       func_184614_ca().func_190918_g(1);
/*     */       if (func_184614_ca().func_190916_E() <= 0)
/*     */         func_184611_a(EnumHand.MAIN_HAND, ItemStack.field_190927_a); 
/*     */     } 
/*     */   }
/*     */   
/*  58 */   public EntityTurretCrossbow(World worldIn) { super(worldIn);
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
/* 139 */     this.loadProgressInt = 0;
/* 140 */     this.isLoadInProgress = false;
/* 141 */     this.loadProgress = 0.0F;
/* 142 */     this.prevLoadProgress = 0.0F;
/* 143 */     this.loadProgressForRender = 0.0F;
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
/* 253 */     this.attackedLastTick = false;
/* 254 */     this.attackCount = 0; func_70105_a(0.95F, 1.25F); this.field_70138_W = 0.0F; }
/*     */   @SideOnly(Side.CLIENT) public void func_70103_a(byte par1) { if (par1 == 16) { if (!this.field_82175_bq) { this.field_110158_av = -1; this.field_82175_bq = true; }  } else if (par1 == 17) { if (!this.isLoadInProgress) { this.loadProgressInt = -1; this.isLoadInProgress = true; }  } else { super.func_70103_a(par1); }
/*     */      }
/*     */   @SideOnly(Side.CLIENT) public float getLoadProgress(float pt) { float f1 = this.loadProgress - this.prevLoadProgress; if (f1 < 0.0F)
/* 258 */       f1++;  return this.prevLoadProgress + f1 * pt; } public void func_70071_h_() { super.func_70071_h_();
/*     */     
/* 260 */     if (func_70638_az() != null && ((func_70638_az()).field_70128_L || func_184191_r(func_70638_az()))) func_70624_b(null);
/*     */     
/* 262 */     if (!this.field_70170_p.field_72995_K)
/* 263 */     { this.field_70177_z = this.field_70759_as;
/*     */       
/* 265 */       if (this.field_70173_aa % 80 == 0) func_70691_i(1.0F);
/*     */       
/* 267 */       int k = MathHelper.func_76128_c(this.field_70165_t);
/* 268 */       int l = MathHelper.func_76128_c(this.field_70163_u);
/* 269 */       int i1 = MathHelper.func_76128_c(this.field_70161_v);
/*     */       
/* 271 */       if (BlockRailBase.func_176562_d(this.field_70170_p, new BlockPos(k, l - 1, i1)))
/*     */       {
/* 273 */         l--;
/*     */       }
/*     */       
/* 276 */       BlockPos blockpos = new BlockPos(k, l, i1);
/* 277 */       IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
/*     */       
/* 279 */       if (BlockRailBase.func_176563_d(iblockstate))
/*     */       {
/* 281 */         if (iblockstate.func_177230_c() == BlocksTC.activatorRail)
/*     */         {
/* 283 */           boolean ac = ((Boolean)iblockstate.func_177229_b(BlockRailPowered.field_176569_M)).booleanValue();
/* 284 */           func_94061_f(ac);
/*     */         }
/*     */       
/*     */       } }
/*     */     
/*     */     else
/*     */     
/* 291 */     { func_82168_bl(); }  } protected void func_82168_bl() { if (this.field_82175_bq) { this.field_110158_av++; if (this.field_110158_av >= 6) { this.field_110158_av = 0; this.field_82175_bq = false; }  } else { this.field_110158_av = 0; }  this.field_70733_aJ = this.field_110158_av / 6.0F; if (this.isLoadInProgress) { this.loadProgressInt++; if (this.loadProgressInt >= 10) { this.loadProgressInt = 0; this.isLoadInProgress = false; }  } else { this.loadProgressInt = 0; }  this.loadProgress = this.loadProgressInt / 10.0F; } public void func_70030_z() { this.prevLoadProgress = this.loadProgress; if (!this.field_70170_p.field_72995_K && (func_184614_ca() == null || func_184614_ca().func_190926_b() || func_184614_ca().func_190916_E() <= 0)) { BlockPos p = func_180425_c().func_177977_b(); TileEntity t = this.field_70170_p.func_175625_s(p); if (t != null && t instanceof TileEntityDispenser && EnumFacing.func_82600_a(t.func_145832_p() & 0x7) == EnumFacing.UP) { TileEntityDispenser d = (TileEntityDispenser)t; for (int a = 0; a < d.func_70302_i_(); a++) { if (d.func_70301_a(a) != null && !d.func_70301_a(a).func_190926_b() && d.func_70301_a(a).func_77973_b() instanceof net.minecraft.item.ItemArrow) { func_184611_a(EnumHand.MAIN_HAND, d.func_70298_a(a, d.func_70301_a(a).func_190916_E())); func_184185_a(SoundsTC.ticks, 1.0F, 1.0F); this.field_70170_p.func_72960_a(this, (byte)17); break; }  }  }
/*     */        }
/*     */      super.func_70030_z(); } public Team func_96124_cp() { if (isOwned()) { EntityLivingBase entitylivingbase = getOwnerEntity(); if (entitylivingbase != null)
/*     */         return entitylivingbase.func_96124_cp();  }
/*     */      return super.func_96124_cp(); }
/*     */   public float func_70047_e() { return this.field_70131_O * 0.66F; }
/*     */   protected void func_110147_ax() { super.func_110147_ax(); func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D); func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(24.0D); }
/*     */   public int func_70658_aO() { return 2; }
/* 299 */   public boolean func_70104_M() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public boolean func_70067_L() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public void func_70037_a(NBTTagCompound nbt) { super.func_70037_a(nbt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   public void func_70014_b(NBTTagCompound nbt) { super.func_70014_b(nbt); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/* 323 */     this.field_70177_z = (float)(this.field_70177_z + func_70681_au().nextGaussian() * 45.0D);
/* 324 */     this.field_70125_A = (float)(this.field_70125_A + func_70681_au().nextGaussian() * 20.0D);
/* 325 */     return super.func_70097_a(source, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70653_a(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
/* 330 */     super.func_70653_a(p_70653_1_, p_70653_2_, p_70653_3_ / 10.0D, p_70653_5_ / 10.0D);
/* 331 */     if (this.field_70181_x > 0.1D) this.field_70181_x = 0.1D;
/*     */   
/*     */   }
/*     */   
/*     */   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
/* 336 */     if (!this.field_70170_p.field_72995_K && isOwner(player) && !this.field_70128_L) {
/* 337 */       if (player.func_70093_af()) {
/* 338 */         func_184185_a(SoundsTC.zap, 1.0F, 1.0F);
/* 339 */         dropAmmo();
/* 340 */         func_70099_a(new ItemStack(ItemsTC.turretPlacer, 1, 0), 0.5F);
/* 341 */         func_70106_y();
/* 342 */         player.func_184609_a(hand);
/*     */       } else {
/* 344 */         player.openGui(Thaumcraft.instance, 16, this.field_70170_p, func_145782_y(), 0, 0);
/*     */       } 
/* 346 */       return true;
/*     */     } 
/*     */     
/* 349 */     return super.func_184645_a(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 354 */   public void func_70091_d(MoverType mt, double x, double y, double z) { super.func_70091_d(mt, x / 20.0D, y, z / 20.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource cause) {
/* 360 */     super.func_70645_a(cause);
/*     */     
/* 362 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 364 */       dropAmmo();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void dropAmmo() {
/* 369 */     if (func_184614_ca() != null && !func_184614_ca().func_190926_b()) {
/* 370 */       func_70099_a(func_184614_ca(), 0.5F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
/* 376 */     float b = p_70628_2_ * 0.15F;
/* 377 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(ItemsTC.mind), 0.5F); 
/* 378 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(ItemsTC.mechanismSimple), 0.5F); 
/* 379 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plankGreatwood), 0.5F); 
/* 380 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plankGreatwood), 0.5F);
/*     */   
/*     */   }
/*     */   
/*     */   protected RayTraceResult getRayTraceResult() {
/* 385 */     float f = this.field_70127_C + this.field_70125_A - this.field_70127_C;
/* 386 */     float f1 = this.field_70126_B + this.field_70177_z - this.field_70126_B;
/* 387 */     double d0 = this.field_70169_q + this.field_70165_t - this.field_70169_q;
/* 388 */     double d1 = this.field_70167_r + this.field_70163_u - this.field_70167_r + func_70047_e();
/* 389 */     double d2 = this.field_70166_s + this.field_70161_v - this.field_70166_s;
/* 390 */     Vec3d vec3 = new Vec3d(d0, d1, d2);
/* 391 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/* 392 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/* 393 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/* 394 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/* 395 */     float f6 = f3 * f4;
/* 396 */     float f7 = f2 * f4;
/* 397 */     double d3 = 5.0D;
/* 398 */     Vec3d vec31 = vec3.func_72441_c(f6 * d3, f5 * d3, f7 * d3);
/* 399 */     return this.field_70170_p.func_147447_a(vec3, vec31, true, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 405 */   public int func_70646_bf() { return 20; }
/*     */ 
/*     */   
/*     */   protected class EntityAIWatchTarget
/*     */     extends EntityAIBase
/*     */   {
/*     */     protected EntityLiving theWatcher;
/*     */     
/*     */     protected Entity closestEntity;
/*     */     
/*     */     private int lookTime;
/*     */     
/*     */     public EntityAIWatchTarget(EntityLiving p_i1631_1_) {
/* 418 */       this.theWatcher = p_i1631_1_;
/* 419 */       func_75248_a(2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_75250_a() {
/* 425 */       if (this.theWatcher.func_70638_az() != null)
/*     */       {
/* 427 */         this.closestEntity = this.theWatcher.func_70638_az();
/*     */       }
/*     */       
/* 430 */       return (this.closestEntity != null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_75253_b() {
/* 436 */       float d = (float)getTargetDistance();
/* 437 */       return !this.closestEntity.func_70089_S() ? false : ((this.theWatcher.func_70068_e(this.closestEntity) > (d * d)) ? false : ((this.lookTime > 0)));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 443 */     public void func_75249_e() { this.lookTime = 40 + this.theWatcher.func_70681_au().nextInt(40); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 449 */     public void func_75251_c() { this.closestEntity = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void func_75246_d() {
/* 455 */       this.theWatcher.func_70671_ap().func_75650_a(this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + this.closestEntity.func_70047_e(), this.closestEntity.field_70161_v, 10.0F, this.theWatcher.func_70646_bf());
/* 456 */       this.lookTime--;
/*     */     }
/*     */ 
/*     */     
/*     */     protected double getTargetDistance() {
/* 461 */       IAttributeInstance iattributeinstance = this.theWatcher.func_110148_a(SharedMonsterAttributes.field_111265_b);
/* 462 */       return (iattributeinstance == null) ? 16.0D : iattributeinstance.func_111126_e();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class EntityAINearestValidTarget
/*     */     extends EntityAITarget
/*     */   {
/*     */     protected final Class targetClass;
/*     */     private final int targetChance;
/*     */     protected final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
/*     */     protected Predicate targetEntitySelector;
/*     */     protected EntityLivingBase targetEntity;
/*     */     
/* 476 */     public EntityAINearestValidTarget(EntityTurretCrossbow this$0, EntityCreature p_i45878_1_, Class p_i45878_2_, boolean p_i45878_3_) { this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 481 */     public EntityAINearestValidTarget(EntityTurretCrossbow this$0, EntityCreature p_i45879_1_, Class p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_) { this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate)null); }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45880_1_, Class p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate tselector) {
/* 486 */       super(p_i45880_1_, p_i45880_4_, p_i45880_5_);
/* 487 */       this.targetClass = p_i45880_2_;
/* 488 */       this.targetChance = p_i45880_3_;
/* 489 */       this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(p_i45880_1_);
/* 490 */       func_75248_a(1);
/* 491 */       this.targetEntitySelector = new Predicate()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001621";
/*     */           
/*     */           public boolean applySelection(EntityLivingBase entity) {
/* 496 */             if (tselector != null && !tselector.apply(entity))
/*     */             {
/* 498 */               return false;
/*     */             }
/*     */ 
/*     */             
/* 502 */             if (entity instanceof EntityPlayer) {
/*     */               
/* 504 */               double d0 = EntityTurretCrossbow.EntityAINearestValidTarget.this.func_111175_f();
/*     */               
/* 506 */               if (entity.func_70093_af())
/*     */               {
/* 508 */                 d0 *= 0.800000011920929D;
/*     */               }
/*     */               
/* 511 */               if (entity.func_82150_aj()) {
/*     */                 
/* 513 */                 float f = ((EntityPlayer)entity).func_82243_bO();
/*     */                 
/* 515 */                 if (f < 0.1F)
/*     */                 {
/* 517 */                   f = 0.1F;
/*     */                 }
/*     */                 
/* 520 */                 d0 *= (0.7F * f);
/*     */               } 
/*     */               
/* 523 */               if (entity.func_70032_d(EntityTurretCrossbow.EntityAINearestValidTarget.this.field_75299_d) > d0)
/*     */               {
/* 525 */                 return false;
/*     */               }
/*     */             } 
/*     */             
/* 529 */             return EntityTurretCrossbow.EntityAINearestValidTarget.this.func_75296_a(entity, false);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 534 */           public boolean apply(Object p_apply_1_) { return applySelection((EntityLivingBase)p_apply_1_); }
/*     */         };
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean func_75296_a(EntityLivingBase p_75296_1_, boolean p_75296_2_) {
/* 542 */       if (!func_179445_a(this.field_75299_d, p_75296_1_, p_75296_2_, this.field_75297_f))
/*     */       {
/* 544 */         return false;
/*     */       }
/* 546 */       if (!this.field_75299_d.func_180485_d(new BlockPos(p_75296_1_)))
/*     */       {
/* 548 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 552 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_75250_a() {
/* 559 */       if (this.targetChance > 0 && this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0)
/*     */       {
/* 561 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 565 */       double d0 = func_111175_f();
/* 566 */       List list = this.field_75299_d.field_70170_p.func_175647_a(this.targetClass, this.field_75299_d.func_174813_aQ().func_72314_b(d0, 4.0D, d0), Predicates.and(this.targetEntitySelector, EntitySelectors.field_180132_d));
/* 567 */       Collections.sort(list, this.theNearestAttackableTargetSorter);
/*     */       
/* 569 */       if (list.isEmpty())
/*     */       {
/* 571 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 575 */       this.targetEntity = (EntityLivingBase)list.get(0);
/* 576 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void func_75249_e() {
/* 584 */       this.field_75299_d.func_70624_b(this.targetEntity);
/* 585 */       super.func_75249_e();
/*     */     }
/*     */ 
/*     */     
/*     */     public class Sorter
/*     */       implements Comparator
/*     */     {
/*     */       private final Entity theEntity;
/*     */       private static final String __OBFID = "CL_00001622";
/*     */       
/* 595 */       public Sorter(Entity p_i1662_1_) { this.theEntity = p_i1662_1_; }
/*     */ 
/*     */ 
/*     */       
/*     */       public int compare(Entity p_compare_1_, Entity p_compare_2_) {
/* 600 */         double d0 = this.theEntity.func_70068_e(p_compare_1_);
/* 601 */         double d1 = this.theEntity.func_70068_e(p_compare_2_);
/* 602 */         return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 607 */       public int compare(Object p_compare_1_, Object p_compare_2_) { return compare((Entity)p_compare_1_, (Entity)p_compare_2_); }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_184724_a(boolean swingingArms) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\construct\EntityTurretCrossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */