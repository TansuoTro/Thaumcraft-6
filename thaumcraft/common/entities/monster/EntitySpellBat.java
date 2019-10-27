/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.casters.FocusEffect;
/*     */ import thaumcraft.api.casters.FocusEngine;
/*     */ import thaumcraft.api.casters.FocusPackage;
/*     */ import thaumcraft.api.casters.Trajectory;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntitySpellBat
/*     */   extends EntityMob
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   private BlockPos currentFlightTarget;
/*  47 */   public EntityLivingBase owner = null;
/*     */   FocusPackage focusPackage;
/*     */   private UUID ownerUniqueId;
/*     */   
/*     */   public EntitySpellBat(World world)
/*     */   {
/*  53 */     super(world);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     this.damBonus = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     this.effects = null;
/* 225 */     this.color = 16777215; func_70105_a(0.5F, 0.9F); } public void func_70088_a() { super.func_70088_a(); func_184212_Q().func_187214_a(FRIENDLY, Boolean.valueOf(false)); } private static final DataParameter<Boolean> FRIENDLY = EntityDataManager.func_187226_a(EntitySpellBat.class, DataSerializers.field_187198_h); public int damBonus; private int attackTime; FocusEffect[] effects; public int color; public EntitySpellBat(FocusPackage pac, boolean friendly) { super(pac.world); this.damBonus = 0; this.effects = null; this.color = 16777215; func_70105_a(0.5F, 0.9F); this.focusPackage = pac; setOwner(pac.getCaster()); setIsFriendly(friendly); }
/*     */   public boolean getIsFriendly() { return ((Boolean)func_184212_Q().func_187225_a(FRIENDLY)).booleanValue(); }
/*     */   public void setIsFriendly(boolean par1) { func_184212_Q().func_187227_b(FRIENDLY, Boolean.valueOf(par1)); } public void writeSpawnData(ByteBuf data) { Utils.writeNBTTagCompoundToBuffer(data, this.focusPackage.serialize()); } public void readSpawnData(ByteBuf data) { try { this.focusPackage = new FocusPackage(); this.focusPackage.deserialize(Utils.readNBTTagCompoundFromBuffer(data)); }
/*     */     catch (Exception e)
/*     */     { e.printStackTrace(); }
/* 230 */      } public void setOwner(@Nullable EntityLivingBase ownerIn) { this.owner = ownerIn; this.ownerUniqueId = (ownerIn == null) ? null : ownerIn.func_110124_au(); } public void func_70071_h_() { super.func_70071_h_();
/*     */     
/* 232 */     if (!this.field_70170_p.field_72995_K && (this.field_70173_aa > 600 || getOwner() == null))
/*     */     {
/* 234 */       func_70106_y();
/*     */     }
/*     */     
/* 237 */     this.field_70181_x *= 0.6000000238418579D;
/*     */     
/* 239 */     if (func_70089_S() && this.field_70170_p.field_72995_K) {
/*     */       
/* 241 */       if (this.effects == null) {
/* 242 */         this.effects = this.focusPackage.getFocusEffects();
/* 243 */         int r = 0;
/* 244 */         int g = 0;
/* 245 */         int b = 0;
/* 246 */         for (FocusEffect ef : this.effects) {
/* 247 */           Color c = new Color(FocusEngine.getElementColor(ef.getKey()));
/* 248 */           r += c.getRed();
/* 249 */           g += c.getGreen();
/* 250 */           b += c.getBlue();
/*     */         } 
/* 252 */         r /= this.effects.length;
/* 253 */         g /= this.effects.length;
/* 254 */         b /= this.effects.length;
/* 255 */         Color c = new Color(r, g, b);
/* 256 */         this.color = c.getRGB();
/*     */       } 
/* 258 */       if (this.effects != null && this.effects.length > 0) {
/* 259 */         FocusEffect eff = this.effects[this.field_70146_Z.nextInt(this.effects.length)];
/* 260 */         eff.renderParticleFX(this.field_70170_p, this.field_70165_t + this.field_70170_p.field_73012_v
/* 261 */             .nextGaussian() * 0.125D, this.field_70163_u + (this.field_70131_O / 2.0F) + this.field_70170_p.field_73012_v
/* 262 */             .nextGaussian() * 0.125D, this.field_70161_v + this.field_70170_p.field_73012_v
/* 263 */             .nextGaussian() * 0.125D, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     }  } @Nullable
/*     */   public EntityLivingBase getOwner() { if (this.owner == null && this.ownerUniqueId != null && this.field_70170_p instanceof WorldServer) {
/*     */       Entity entity = ((WorldServer)this.field_70170_p).func_175733_a(this.ownerUniqueId);
/*     */       if (entity instanceof EntityLivingBase)
/*     */         this.owner = (EntityLivingBase)entity; 
/*     */     } 
/*     */     return this.owner; } @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b() { return 15728880; } public float func_70013_c() { return 1.0F; } protected float func_70599_aP() { return 0.1F; } protected float func_70647_i() { return super.func_70647_i() * 0.95F; }
/*     */   protected SoundEvent func_184639_G() { return SoundEvents.field_187740_w; }
/* 274 */   protected void func_70619_bc() { super.func_70619_bc();
/*     */     
/* 276 */     if (this.attackTime > 0) this.attackTime--;
/*     */     
/* 278 */     BlockPos blockpos = new BlockPos(this);
/* 279 */     BlockPos blockpos1 = blockpos.func_177984_a();
/*     */     
/* 281 */     if (func_70638_az() == null) {
/*     */       
/* 283 */       if (this.currentFlightTarget != null && (
/* 284 */         !this.field_70170_p.func_175623_d(this.currentFlightTarget) || this.currentFlightTarget
/* 285 */         .func_177956_o() < 1))
/*     */       {
/* 287 */         this.currentFlightTarget = null;
/*     */       }
/*     */       
/* 290 */       if (this.currentFlightTarget == null || this.field_70146_Z.nextInt(30) == 0 || 
/* 291 */         func_174831_c(this.currentFlightTarget) < 4.0D)
/*     */       {
/* 293 */         this
/*     */ 
/*     */           
/* 296 */           .currentFlightTarget = new BlockPos((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */       }
/*     */       
/* 299 */       double var1 = this.currentFlightTarget.func_177958_n() + 0.5D - this.field_70165_t;
/* 300 */       double var3 = this.currentFlightTarget.func_177956_o() + 0.1D - this.field_70163_u;
/* 301 */       double var5 = this.currentFlightTarget.func_177952_p() + 0.5D - this.field_70161_v;
/* 302 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 303 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 304 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 305 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
/* 306 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 307 */       this.field_191988_bg = 0.5F;
/* 308 */       this.field_70177_z += var8;
/*     */     } else {
/* 310 */       double var1 = (func_70638_az()).field_70165_t - this.field_70165_t;
/* 311 */       double var3 = (func_70638_az()).field_70163_u + (func_70638_az().func_70047_e() * 0.66F) - this.field_70163_u;
/* 312 */       double var5 = (func_70638_az()).field_70161_v - this.field_70161_v;
/* 313 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 314 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 315 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 316 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
/* 317 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 318 */       this.field_191988_bg = 0.5F;
/* 319 */       this.field_70177_z += var8;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 324 */     if (func_70638_az() == null) {
/*     */       
/* 326 */       func_70624_b(findTargetToAttack());
/*     */     }
/* 328 */     else if (func_70638_az().func_70089_S()) {
/*     */       
/* 330 */       float f = func_70638_az().func_70032_d(this);
/*     */       
/* 332 */       if (func_70089_S() && func_70685_l(func_70638_az()))
/*     */       {
/* 334 */         attackEntity(func_70638_az(), f);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 339 */       func_70624_b(null);
/*     */     } 
/*     */     
/* 342 */     if (!getIsFriendly() && func_70638_az() instanceof EntityPlayer && ((EntityPlayer)func_70638_az()).field_71075_bZ.field_75102_a)
/*     */     {
/* 344 */       func_70624_b(null); }  } protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundEvents.field_187743_y; } protected SoundEvent func_184615_bR() { return SoundEvents.field_187742_x; } public boolean func_70104_M() { return false; } protected void func_110147_ax() { super.func_110147_ax(); func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D); }
/*     */   public Team func_96124_cp() { EntityLivingBase entitylivingbase = getOwner();
/*     */     if (entitylivingbase != null)
/*     */       return entitylivingbase.func_96124_cp(); 
/*     */     return super.func_96124_cp(); }
/*     */   public boolean func_184191_r(Entity otherEntity) { EntityLivingBase owner = getOwner();
/*     */     if (otherEntity == owner)
/*     */       return true; 
/*     */     if (owner != null)
/*     */       return (owner.func_184191_r(otherEntity) || otherEntity.func_184191_r(owner)); 
/*     */     return super.func_184191_r(otherEntity); }
/* 356 */   protected boolean func_70041_e_() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180430_e(float par1, float damageMultiplier) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_184231_a(double p_180433_1_, boolean p_180433_3_, IBlockState state, BlockPos pos) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public boolean func_145773_az() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 389 */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) { return super.func_70097_a(par1DamageSource, par2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity target, float par2) {
/* 395 */     if (this.attackTime <= 0 && par2 < Math.max(2.5F, target.field_70130_N * 1.1F) && 
/* 396 */       (target.func_174813_aQ()).field_72337_e > (func_174813_aQ()).field_72338_b && 
/* 397 */       (target.func_174813_aQ()).field_72338_b < (func_174813_aQ()).field_72337_e) {
/*     */       
/* 399 */       this.attackTime = 40;
/* 400 */       if (!this.field_70170_p.field_72995_K) {
/* 401 */         RayTraceResult ray = new RayTraceResult(target);
/* 402 */         ray.field_72307_f = target.func_174791_d().func_72441_c(0.0D, (target.field_70131_O / 2.0F), 0.0D);
/* 403 */         Trajectory tra = new Trajectory(func_174791_d(), func_174791_d().func_72444_a(ray.field_72307_f));
/* 404 */         FocusEngine.runFocusPackage(this.focusPackage.copy(getOwner()), new Trajectory[] { tra }, new RayTraceResult[] { ray });
/* 405 */         func_70606_j(func_110143_aJ() - 1.0F);
/*     */       } 
/*     */       
/* 408 */       func_184185_a(SoundEvents.field_187743_y, 0.5F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82167_n(Entity entityIn) {
/* 418 */     if (getIsFriendly())
/* 419 */       return;  super.func_82167_n(entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityLivingBase findTargetToAttack() {
/* 424 */     double var1 = 12.0D;
/*     */     
/* 426 */     List<EntityLivingBase> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityLivingBase.class, var1);
/* 427 */     double d = Double.MAX_VALUE;
/* 428 */     EntityLivingBase ret = null;
/* 429 */     for (EntityLivingBase e : list) {
/* 430 */       if (e.field_70128_L || (
/* 431 */         getIsFriendly() ? 
/* 432 */         !EntityUtils.isFriendly(getOwner(), e) : (
/*     */ 
/*     */ 
/*     */         
/* 436 */         EntityUtils.isFriendly(getOwner(), e) || func_184191_r(e)))) {
/*     */         continue;
/*     */       }
/* 439 */       double ed = func_70068_e(e);
/* 440 */       if (ed < d) {
/* 441 */         d = ed;
/* 442 */         ret = e;
/*     */       } 
/*     */     } 
/* 445 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 454 */     super.func_70037_a(nbt);
/* 455 */     this.ownerUniqueId = nbt.func_186857_a("OwnerUUID");
/* 456 */     setIsFriendly(nbt.func_74767_n("friendly"));
/*     */     try {
/* 458 */       this.focusPackage = new FocusPackage();
/* 459 */       this.focusPackage.deserialize(nbt.func_74775_l("pack"));
/* 460 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 470 */     super.func_70014_b(nbt);
/* 471 */     if (this.ownerUniqueId != null)
/*     */     {
/* 473 */       nbt.func_186854_a("OwnerUUID", this.ownerUniqueId);
/*     */     }
/* 475 */     nbt.func_74782_a("pack", this.focusPackage.serialize());
/* 476 */     nbt.func_74757_a("friendly", getIsFriendly());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 485 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 486 */     int j = MathHelper.func_76128_c((func_174813_aQ()).field_72338_b);
/* 487 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/* 488 */     BlockPos blockpos = new BlockPos(i, j, k);
/* 489 */     int var4 = this.field_70170_p.func_175699_k(blockpos);
/* 490 */     byte var5 = 7;
/* 491 */     return (var4 > this.field_70146_Z.nextInt(var5)) ? false : super.func_70601_bi();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 497 */   protected boolean func_146066_aG() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 502 */   protected boolean func_70814_o() { return true; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntitySpellBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */