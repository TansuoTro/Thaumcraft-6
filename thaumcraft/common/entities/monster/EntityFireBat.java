/*     */ package thaumcraft.common.entities.monster;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ public class EntityFireBat extends EntityMob {
/*     */   private BlockPos currentFlightTarget;
/*     */   
/*     */   public void func_70088_a() {
/*     */     super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(HANGING, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   private static final DataParameter<Boolean> HANGING = EntityDataManager.func_187226_a(EntityFireBat.class, DataSerializers.field_187198_h);
/*     */   public int damBonus;
/*     */   private int attackTime;
/*  32 */   public EntityLivingBase owner = null;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b() { return 15728880; }
/*     */   public EntityFireBat(World par1World) {
/*  36 */     super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     this.damBonus = 0;
/*     */     func_70105_a(0.5F, 0.9F);
/*     */     setIsBatHanging(true);
/*     */     this.field_70178_ae = true;
/*     */   }
/*     */   public float func_70013_c() { return 1.0F; }
/*     */   protected void func_110147_ax() {
/* 128 */     super.func_110147_ax();
/* 129 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5.0D);
/* 130 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
/*     */   }
/*     */   protected float func_70599_aP() { return 0.1F; }
/*     */   protected float func_70647_i() { return super.func_70647_i() * 0.95F; }
/*     */   protected SoundEvent func_184639_G() { return (getIsBatHanging() && this.field_70146_Z.nextInt(4) != 0) ? null : SoundEvents.field_187740_w; }
/*     */   
/* 136 */   public boolean getIsBatHanging() { return ((Boolean)func_184212_Q().func_187225_a(HANGING)).booleanValue(); }
/*     */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundEvents.field_187743_y; }
/*     */   protected SoundEvent func_184615_bR() { return SoundEvents.field_187742_x; }
/*     */   public boolean func_70104_M() { return false; }
/*     */   
/* 141 */   public void setIsBatHanging(boolean par1) { func_184212_Q().func_187227_b(HANGING, Boolean.valueOf(par1)); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 146 */     if (func_70026_G())
/*     */     {
/* 148 */       func_70097_a(DamageSource.field_76369_e, 1.0F);
/*     */     }
/*     */     
/* 151 */     super.func_70636_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 160 */     super.func_70071_h_();
/*     */ 
/*     */ 
/*     */     
/* 164 */     this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0D;
/* 165 */     this.field_70163_u = MathHelper.func_76128_c(this.field_70163_u) + 1.0D - this.field_70131_O;
/*     */ 
/*     */ 
/*     */     
/* 169 */     this.field_70181_x *= 0.6000000238418579D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70619_bc() {
/* 179 */     super.func_70619_bc();
/*     */     
/* 181 */     if (this.attackTime > 0) this.attackTime--;
/*     */     
/* 183 */     BlockPos blockpos = new BlockPos(this);
/* 184 */     BlockPos blockpos1 = blockpos.func_177984_a();
/*     */     
/* 186 */     if (getIsBatHanging()) {
/*     */       
/* 188 */       if (!this.field_70170_p.func_180495_p(blockpos1).func_185915_l())
/*     */       {
/* 190 */         setIsBatHanging(false);
/* 191 */         this.field_70170_p.func_180498_a((EntityPlayer)null, 1025, blockpos, 0);
/*     */       }
/*     */       else
/*     */       {
/* 195 */         if (this.field_70146_Z.nextInt(200) == 0)
/*     */         {
/* 197 */           this.field_70759_as = this.field_70146_Z.nextInt(360);
/*     */         }
/*     */         
/* 200 */         if (this.field_70170_p.func_72890_a(this, 4.0D) != null)
/*     */         {
/* 202 */           setIsBatHanging(false);
/* 203 */           this.field_70170_p.func_180498_a((EntityPlayer)null, 1025, blockpos, 0);
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 209 */     else if (func_70638_az() == null) {
/*     */       
/* 211 */       if (this.currentFlightTarget != null && (
/* 212 */         !this.field_70170_p.func_175623_d(this.currentFlightTarget) || this.currentFlightTarget
/* 213 */         .func_177956_o() < 1))
/*     */       {
/* 215 */         this.currentFlightTarget = null;
/*     */       }
/*     */       
/* 218 */       if (this.currentFlightTarget == null || this.field_70146_Z.nextInt(30) == 0 || 
/* 219 */         func_174831_c(this.currentFlightTarget) < 4.0D)
/*     */       {
/* 221 */         this
/*     */ 
/*     */           
/* 224 */           .currentFlightTarget = new BlockPos((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */       }
/*     */       
/* 227 */       double var1 = this.currentFlightTarget.func_177958_n() + 0.5D - this.field_70165_t;
/* 228 */       double var3 = this.currentFlightTarget.func_177956_o() + 0.1D - this.field_70163_u;
/* 229 */       double var5 = this.currentFlightTarget.func_177952_p() + 0.5D - this.field_70161_v;
/* 230 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 231 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 232 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 233 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
/* 234 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 235 */       this.field_191988_bg = 0.5F;
/* 236 */       this.field_70177_z += var8;
/*     */       
/* 238 */       if (this.field_70146_Z.nextInt(100) == 0 && this.field_70170_p.func_180495_p(blockpos1).func_185915_l())
/*     */       {
/* 240 */         setIsBatHanging(true);
/*     */       }
/*     */     } else {
/* 243 */       double var1 = (func_70638_az()).field_70165_t - this.field_70165_t;
/* 244 */       double var3 = (func_70638_az()).field_70163_u + (func_70638_az().func_70047_e() * 0.66F) - this.field_70163_u;
/* 245 */       double var5 = (func_70638_az()).field_70161_v - this.field_70161_v;
/* 246 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 247 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 248 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 249 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
/* 250 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 251 */       this.field_191988_bg = 0.5F;
/* 252 */       this.field_70177_z += var8;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 257 */     if (func_70638_az() == null) {
/*     */       
/* 259 */       func_70624_b(findPlayerToAttack());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 267 */     else if (func_70638_az().func_70089_S()) {
/*     */       
/* 269 */       float f = func_70638_az().func_70032_d(this);
/*     */       
/* 271 */       if (func_70089_S() && func_70685_l(func_70638_az()))
/*     */       {
/* 273 */         attackEntity(func_70638_az(), f);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 278 */       func_70624_b(null);
/*     */     } 
/*     */     
/* 281 */     if (func_70638_az() instanceof EntityPlayer && ((EntityPlayer)func_70638_az()).field_71075_bZ.field_75102_a)
/*     */     {
/* 283 */       func_70624_b(null);
/*     */     }
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
/* 295 */   protected boolean func_70041_e_() { return false; }
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
/* 319 */   public boolean func_145773_az() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/* 328 */     if (func_180431_b(par1DamageSource) || par1DamageSource
/* 329 */       .func_76347_k() || par1DamageSource.func_94541_c())
/*     */     {
/* 331 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 335 */     if (!this.field_70170_p.field_72995_K && getIsBatHanging())
/*     */     {
/* 337 */       setIsBatHanging(false);
/*     */     }
/*     */     
/* 340 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity entity, float par2) {
/* 347 */     if (this.attackTime <= 0 && par2 < Math.max(2.5F, entity.field_70130_N * 1.1F) && 
/* 348 */       (entity.func_174813_aQ()).field_72337_e > (func_174813_aQ()).field_72338_b && 
/* 349 */       (entity.func_174813_aQ()).field_72338_b < (func_174813_aQ()).field_72337_e) {
/*     */       
/* 351 */       this.attackTime = 20 + this.field_70170_p.field_73012_v.nextInt(20);
/* 352 */       if (this.field_70170_p.field_73012_v.nextInt(10) == 0 && !this.field_70170_p.field_72995_K) {
/* 353 */         entity.field_70172_ad = 0;
/* 354 */         this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5F, false, false);
/* 355 */         func_70106_y();
/*     */       } 
/* 357 */       func_184185_a(SoundEvents.field_187743_y, 0.5F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/* 358 */       func_70652_k(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EntityLivingBase findPlayerToAttack() {
/* 365 */     double var1 = 12.0D;
/* 366 */     return this.field_70170_p.func_72890_a(this, var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 375 */     super.func_70037_a(nbt);
/* 376 */     setIsBatHanging(nbt.func_74767_n("hang"));
/* 377 */     this.damBonus = nbt.func_74771_c("damBonus");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 386 */     super.func_70014_b(nbt);
/* 387 */     nbt.func_74757_a("hang", getIsBatHanging());
/* 388 */     nbt.func_74774_a("damBonus", (byte)this.damBonus);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 397 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 398 */     int j = MathHelper.func_76128_c((func_174813_aQ()).field_72338_b);
/* 399 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/* 400 */     BlockPos blockpos = new BlockPos(i, j, k);
/* 401 */     int var4 = this.field_70170_p.func_175699_k(blockpos);
/* 402 */     byte var5 = 7;
/* 403 */     return (var4 > this.field_70146_Z.nextInt(var5)) ? false : super.func_70601_bi();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   protected Item func_146068_u() { return Items.field_151016_H; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 417 */   protected boolean func_70814_o() { return true; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityFireBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */