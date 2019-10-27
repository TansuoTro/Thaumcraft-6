/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityEldritchGolem extends EntityThaumcraftBoss implements IEldritchMob, IRangedAttackMob {
/*     */   protected void func_184651_r() {
/*     */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*     */     this.field_70714_bg.func_75776_a(3, new EntityAIAttackMelee(this, 1.1D, false));
/*     */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*     */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*     */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 8.0F));
/*     */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*     */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
/*     */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, true));
/*     */   }
/*     */   
/*     */   public void generateName() {
/*     */     int t = (int)func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e();
/*     */     if (t >= 0)
/*     */       func_96094_a(String.format(I18n.func_74838_a("entity.Thaumcraft.EldritchGolem.name.custom"), new Object[] { ChampionModifier.mods[t].getModNameLocalized() })); 
/*     */   }
/*     */   
/*     */   private static final DataParameter<Boolean> HEADLESS = EntityDataManager.func_187226_a(EntityEldritchGolem.class, DataSerializers.field_187198_h);
/*     */   int beamCharge;
/*     */   boolean chargingBeam;
/*     */   int arcing;
/*     */   int ax;
/*     */   
/*  49 */   public EntityEldritchGolem(World p_i1745_1_) { super(p_i1745_1_);
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
/* 224 */     this.beamCharge = 0;
/* 225 */     this.chargingBeam = false;
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
/* 323 */     this.arcing = 0;
/* 324 */     this.ax = 0;
/* 325 */     this.ay = 0;
/* 326 */     this.az = 0;
/*     */     func_70105_a(1.75F, 3.5F);
/*     */     this.field_70178_ae = true; }
/*     */   
/* 330 */   int ay; int az; private int attackTimer; public void func_70071_h_() { if (getSpawnTimer() == 150) this.field_70170_p.func_72960_a(this, (byte)18); 
/* 331 */     if (getSpawnTimer() > 0) {
/* 332 */       func_70691_i(2.0F);
/*     */     }
/* 334 */     super.func_70071_h_();
/* 335 */     if (this.field_70170_p.field_72995_K) {
/* 336 */       if (isHeadless()) {
/* 337 */         this.field_70125_A = 0.0F;
/* 338 */         float f1 = MathHelper.func_76134_b(-this.field_70761_aq * 0.017453292F - 3.1415927F);
/* 339 */         float f2 = MathHelper.func_76126_a(-this.field_70761_aq * 0.017453292F - 3.1415927F);
/* 340 */         float f3 = -MathHelper.func_76134_b(-this.field_70125_A * 0.017453292F);
/* 341 */         float f4 = MathHelper.func_76126_a(-this.field_70125_A * 0.017453292F);
/* 342 */         Vec3d v = new Vec3d((f2 * f3), f4, (f1 * f3));
/*     */         
/* 344 */         if (this.field_70146_Z.nextInt(20) == 0) {
/* 345 */           float a = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) / 3.0F;
/* 346 */           float b = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) / 3.0F;
/*     */           
/* 348 */           FXDispatcher.INSTANCE.spark((float)(this.field_70165_t + v.field_72450_a + a), ((float)this.field_70163_u + func_70047_e() - 0.75F), (float)(this.field_70161_v + v.field_72449_c + b), 3.0F, 0.65F + this.field_70146_Z
/* 349 */               .nextFloat() * 0.1F, 1.0F, 1.0F, 0.8F);
/*     */         } 
/*     */         
/* 352 */         FXDispatcher.INSTANCE.drawVentParticles((float)this.field_70165_t + v.field_72450_a * 0.4D, ((float)this.field_70163_u + 
/* 353 */             func_70047_e() - 1.25F), (float)this.field_70161_v + v.field_72449_c * 0.4D, 0.0D, 0.001D, 0.0D, 5592405, 4.0F);
/*     */ 
/*     */         
/* 356 */         if (this.arcing > 0) {
/* 357 */           FXDispatcher.INSTANCE.arcLightning(this.field_70165_t, this.field_70163_u + (this.field_70131_O / 2.0F), this.field_70161_v, this.ax + 0.5D, (this.ay + 1), this.az + 0.5D, 0.65F + this.field_70146_Z
/* 358 */               .nextFloat() * 0.1F, 1.0F, 1.0F, 1.0F - this.arcing / 10.0F);
/* 359 */           this.arcing--;
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 364 */       if (isHeadless() && this.beamCharge <= 0) {
/* 365 */         this.chargingBeam = true;
/*     */       }
/* 367 */       if (isHeadless() && this.chargingBeam) {
/* 368 */         this.beamCharge++;
/* 369 */         this.field_70170_p.func_72960_a(this, (byte)19);
/* 370 */         if (this.beamCharge == 150) this.chargingBeam = false; 
/*     */       } 
/*     */     }  }
/*     */   protected void func_70088_a() { super.func_70088_a(); func_184212_Q().func_187214_a(HEADLESS, Boolean.valueOf(false)); }
/*     */   public boolean isHeadless() { return ((Boolean)func_184212_Q().func_187225_a(HEADLESS)).booleanValue(); }
/*     */   public void setHeadless(boolean par1) { func_184212_Q().func_187227_b(HEADLESS, Boolean.valueOf(par1)); } public void func_70014_b(NBTTagCompound nbt) { super.func_70014_b(nbt); nbt.func_74757_a("headless", isHeadless()); } public void func_70037_a(NBTTagCompound nbt) { super.func_70037_a(nbt);
/*     */     setHeadless(nbt.func_74767_n("headless"));
/*     */     if (isHeadless())
/* 378 */       makeHeadless();  } public float func_70047_e() { return isHeadless() ? 3.33F : 3.0F; } public int func_70658_aO() { return super.func_70658_aO() + 6; } @SideOnly(Side.CLIENT) public int getAttackTimer() { return this.attackTimer; }
/*     */   
/*     */   protected void func_110147_ax() {
/*     */     super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
/*     */   }
/*     */   
/*     */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundEvents.field_187602_cF; }
/*     */   
/*     */   protected SoundEvent func_184615_bR() { return SoundEvents.field_187599_cE; }
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) { func_184185_a(SoundEvents.field_187605_cG, 1.0F, 1.0F); }
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) {
/*     */     this.spawnTimer = 100;
/*     */     return super.func_180482_a(diff, data);
/*     */   }
/*     */   
/*     */   public void func_70636_d() {
/*     */     super.func_70636_d();
/*     */     if (this.attackTimer > 0)
/*     */       this.attackTimer--; 
/*     */     if (this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7D && this.field_70146_Z.nextInt(5) == 0) {
/*     */       IBlockState bs = this.field_70170_p.func_180495_p(func_180425_c());
/*     */       if (bs.func_185904_a() != Material.field_151579_a)
/*     */         this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, (func_174813_aQ()).field_72338_b + 0.1D, this.field_70161_v + (this.field_70146_Z.nextFloat() - 0.5D) * this.field_70130_N, 4.0D * (this.field_70146_Z.nextFloat() - 0.5D), 0.5D, (this.field_70146_Z.nextFloat() - 0.5D) * 4.0D, new int[] { Block.func_176210_f(bs) }); 
/*     */       if (!this.field_70170_p.field_72995_K && bs.func_177230_c() instanceof thaumcraft.common.blocks.world.BlockLoot)
/*     */         this.field_70170_p.func_175655_b(func_180425_c(), true); 
/*     */     } 
/*     */     if (!this.field_70170_p.field_72995_K) {
/*     */       IBlockState bs = this.field_70170_p.func_180495_p(func_180425_c());
/*     */       float h = bs.func_185887_b(this.field_70170_p, func_180425_c());
/*     */       if (h >= 0.0F && h <= 0.15F)
/*     */         this.field_70170_p.func_175655_b(func_180425_c(), true); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage) {
/*     */     if (!this.field_70170_p.field_72995_K && damage > func_110143_aJ() && !isHeadless()) {
/*     */       setHeadless(true);
/*     */       this.spawnTimer = 100;
/*     */       double xx = (MathHelper.func_76134_b(this.field_70177_z % 360.0F / 180.0F * 3.1415927F) * 0.75F);
/*     */       double zz = (MathHelper.func_76126_a(this.field_70177_z % 360.0F / 180.0F * 3.1415927F) * 0.75F);
/*     */       this.field_70170_p.func_72876_a(this, this.field_70165_t + xx, this.field_70163_u + func_70047_e(), this.field_70161_v + zz, 2.0F, false);
/*     */       makeHeadless();
/*     */       return false;
/*     */     } 
/*     */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */   void makeHeadless() { this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 3.0D, 1.0D, 5, 5, 24.0F)); }
/*     */   
/*     */   public boolean func_70652_k(Entity target) {
/*     */     if (this.attackTimer > 0)
/*     */       return false; 
/*     */     this.attackTimer = 10;
/*     */     this.field_70170_p.func_72960_a(this, (byte)4);
/*     */     boolean flag = target.func_70097_a(DamageSource.func_76358_a(this), (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.75F);
/*     */     if (flag) {
/*     */       target.field_70181_x += 0.2000000059604645D;
/*     */       if (isHeadless())
/*     */         target.func_70024_g((-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F), 0.1D, (MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * 1.5F)); 
/*     */     } 
/*     */     return flag;
/*     */   }
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
/*     */     if (func_70685_l(entitylivingbase) && !this.chargingBeam && this.beamCharge > 0) {
/*     */       this.beamCharge -= 15 + this.field_70146_Z.nextInt(5);
/*     */       func_70671_ap().func_75650_a(entitylivingbase.field_70165_t, (entitylivingbase.func_174813_aQ()).field_72338_b + (entitylivingbase.field_70131_O / 2.0F), entitylivingbase.field_70161_v, 30.0F, 30.0F);
/*     */       Vec3d v = func_70676_i(1.0F);
/*     */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, false);
/*     */       blast.field_70165_t += v.field_72450_a;
/*     */       blast.field_70161_v += v.field_72449_c;
/*     */       blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
/*     */       double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
/*     */       double d1 = entitylivingbase.field_70163_u - this.field_70163_u - (entitylivingbase.field_70131_O / 2.0F);
/*     */       double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
/*     */       blast.func_70186_c(d0, d1, d2, 0.66F, 5.0F);
/*     */       func_184185_a(SoundsTC.egattack, 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/*     */       this.field_70170_p.func_72838_d(blast);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte p_70103_1_) {
/*     */     if (p_70103_1_ == 4) {
/*     */       this.attackTimer = 10;
/*     */       func_184185_a(SoundEvents.field_187596_cD, 1.0F, 1.0F);
/*     */     } else if (p_70103_1_ == 18) {
/*     */       this.spawnTimer = 150;
/*     */     } else if (p_70103_1_ == 19) {
/*     */       if (this.arcing == 0) {
/*     */         float radius = 2.0F + this.field_70146_Z.nextFloat() * 2.0F;
/*     */         double radians = Math.toRadians(this.field_70146_Z.nextInt(360));
/*     */         double deltaX = radius * Math.cos(radians);
/*     */         double deltaZ = radius * Math.sin(radians);
/*     */         int bx = MathHelper.func_76128_c(this.field_70165_t + deltaX);
/*     */         int by = MathHelper.func_76128_c(this.field_70163_u);
/*     */         int bz = MathHelper.func_76128_c(this.field_70161_v + deltaZ);
/*     */         BlockPos bp = new BlockPos(bx, by, bz);
/*     */         int c = 0;
/*     */         while (c < 5 && this.field_70170_p.func_175623_d(bp)) {
/*     */           c++;
/*     */           by--;
/*     */         } 
/*     */         if (this.field_70170_p.func_175623_d(bp.func_177984_a()) && !this.field_70170_p.func_175623_d(bp)) {
/*     */           this.ax = bx;
/*     */           this.ay = by;
/*     */           this.az = bz;
/*     */           this.arcing = 8 + this.field_70146_Z.nextInt(5);
/*     */           func_184185_a(SoundsTC.jacobs, 0.8F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.05F);
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       super.func_70103_a(p_70103_1_);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_184724_a(boolean swingingArms) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\boss\EntityEldritchGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */