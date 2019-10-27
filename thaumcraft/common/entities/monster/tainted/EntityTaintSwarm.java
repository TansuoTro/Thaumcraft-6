/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.world.taint.TaintHelper;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityTaintSwarm
/*     */   extends EntityMob implements ITaintedMob {
/*     */   private BlockPos currentFlightTarget;
/*     */   
/*     */   public boolean func_70686_a(Class clazz) { return !ITaintedMob.class.isAssignableFrom(clazz); }
/*     */   
/*     */   public boolean func_184191_r(Entity otherEntity) { return (otherEntity instanceof ITaintedMob || super.func_184191_r(otherEntity)); }
/*     */   
/*     */   private static final DataParameter<Boolean> SUMMONED = EntityDataManager.func_187226_a(EntityTaintSwarm.class, DataSerializers.field_187198_h);
/*     */   
/*     */   public EntityTaintSwarm(World par1World) {
/*  41 */     super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     this.damBonus = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     this.swarm = new ArrayList();
/*     */     func_70105_a(2.0F, 2.0F); }
/*     */   public int damBonus; public ArrayList swarm; private int attackTime; protected void func_70088_a() { super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(SUMMONED, Boolean.valueOf(false)); } @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b() { return 15728880; } protected boolean func_70692_ba() { return true; } public void func_70071_h_() {
/* 163 */     super.func_70071_h_();
/*     */ 
/*     */     
/* 166 */     this.field_70181_x *= 0.6000000238418579D;
/*     */     
/* 168 */     if (this.field_70170_p.field_72995_K) {
/* 169 */       for (int a = 0; a < this.swarm.size(); a++) {
/* 170 */         if (this.swarm.get(a) == null || !((Particle)this.swarm.get(a)).func_187113_k()) {
/* 171 */           this.swarm.remove(a);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 176 */       if (this.swarm.size() < 30)
/* 177 */         this.swarm.add(FXDispatcher.INSTANCE.swarmParticleFX(this, 0.22F, 15.0F, 0.08F)); 
/*     */     } 
/*     */   } public float func_70013_c() { return 1.0F; }
/*     */   protected float func_70599_aP() { return 0.1F; }
/*     */   protected SoundEvent func_184639_G() { return SoundsTC.swarm; }
/*     */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundsTC.swarmattack; }
/*     */   public void func_70636_d() {
/* 184 */     super.func_70636_d();
/*     */     
/* 186 */     if (this.attackTime > 0) this.attackTime--;
/*     */     
/* 188 */     if (func_70638_az() == null) {
/*     */       
/* 190 */       if (getIsSummoned()) {
/* 191 */         func_70097_a(DamageSource.field_76377_j, 5.0F);
/*     */       }
/* 193 */       if (this.currentFlightTarget != null && (
/* 194 */         !this.field_70170_p.func_175623_d(this.currentFlightTarget) || this.currentFlightTarget.func_177956_o() < 1 || this.currentFlightTarget
/* 195 */         .func_177956_o() > this.field_70170_p.func_175725_q(this.currentFlightTarget).func_177981_b(2).func_177956_o() || 
/* 196 */         !TaintHelper.isNearTaintSeed(this.field_70170_p, this.currentFlightTarget)))
/*     */       {
/* 198 */         this.currentFlightTarget = null;
/*     */       }
/*     */       
/* 201 */       if (this.currentFlightTarget == null || this.field_70146_Z.nextInt(30) == 0 || 
/* 202 */         func_174831_c(this.currentFlightTarget) < 4.0D)
/*     */       {
/* 204 */         this
/*     */ 
/*     */           
/* 207 */           .currentFlightTarget = new BlockPos((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */       }
/*     */       
/* 210 */       double var1 = this.currentFlightTarget.func_177958_n() + 0.5D - this.field_70165_t;
/* 211 */       double var3 = this.currentFlightTarget.func_177956_o() + 0.1D - this.field_70163_u;
/* 212 */       double var5 = this.currentFlightTarget.func_177952_p() + 0.5D - this.field_70161_v;
/* 213 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.015000000014901161D;
/* 214 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 215 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.015000000014901161D;
/* 216 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
/* 217 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 218 */       this.field_191988_bg = 0.1F;
/* 219 */       this.field_70177_z += var8;
/*     */     }
/* 221 */     else if (func_70638_az() != null) {
/* 222 */       double var1 = (func_70638_az()).field_70165_t - this.field_70165_t;
/* 223 */       double var3 = (func_70638_az()).field_70163_u + func_70638_az().func_70047_e() - this.field_70163_u;
/* 224 */       double var5 = (func_70638_az()).field_70161_v - this.field_70161_v;
/* 225 */       this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.025000000149011613D;
/* 226 */       this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 227 */       this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.02500000001490116D;
/* 228 */       float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
/* 229 */       float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 230 */       this.field_191988_bg = 0.1F;
/* 231 */       this.field_70177_z += var8;
/*     */     } 
/*     */     
/* 234 */     if (func_70638_az() == null) {
/*     */       
/* 236 */       func_70624_b((EntityLivingBase)findPlayerToAttack());
/*     */     }
/* 238 */     else if (func_70089_S() && func_70638_az().func_70089_S()) {
/*     */       
/* 240 */       float f = func_70638_az().func_70032_d(this);
/*     */       
/* 242 */       if (func_70685_l(func_70638_az()))
/*     */       {
/* 244 */         attackEntity(func_70638_az(), f);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 249 */       func_70624_b(null);
/*     */     } 
/*     */     
/* 252 */     if (func_70638_az() instanceof EntityPlayer && ((EntityPlayer)func_70638_az()).field_71075_bZ.field_75102_a)
/*     */     {
/* 254 */       func_70624_b(null); } 
/*     */   } protected SoundEvent func_184615_bR() { return SoundsTC.swarmattack; }
/*     */   public boolean func_70104_M() { return false; }
/*     */   protected void func_110147_ax() {
/*     */     super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a((2 + this.damBonus));
/*     */   }
/*     */   public boolean getIsSummoned() { return ((Boolean)func_184212_Q().func_187225_a(SUMMONED)).booleanValue(); }
/*     */   public void setIsSummoned(boolean par1) { func_184212_Q().func_187227_b(SUMMONED, Boolean.valueOf(par1)); }
/* 264 */   protected void func_70619_bc() { super.func_70619_bc(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   protected boolean func_70041_e_() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public boolean func_145773_az() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/* 296 */     if (func_180431_b(par1DamageSource))
/*     */     {
/* 298 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 302 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity par1Entity, float par2) {
/* 310 */     if (this.attackTime <= 0 && par2 < 3.0F && (par1Entity.func_174813_aQ()).field_72337_e > (func_174813_aQ()).field_72338_b && (par1Entity.func_174813_aQ()).field_72338_b < (func_174813_aQ()).field_72337_e) {
/*     */       
/* 312 */       if (getIsSummoned()) {
/* 313 */         ((EntityLivingBase)par1Entity).field_70718_bc = 100;
/*     */       }
/*     */       
/* 316 */       this.attackTime = 15 + this.field_70146_Z.nextInt(10);
/*     */       
/* 318 */       double mx = par1Entity.field_70159_w;
/* 319 */       double my = par1Entity.field_70181_x;
/* 320 */       double mz = par1Entity.field_70179_y;
/* 321 */       if (func_70652_k(par1Entity) && 
/* 322 */         !this.field_70170_p.field_72995_K && par1Entity instanceof EntityLivingBase) {
/* 323 */         ((EntityLivingBase)par1Entity).func_70690_d(new PotionEffect(MobEffects.field_76437_t, 100, 0));
/*     */       }
/*     */       
/* 326 */       par1Entity.field_70160_al = false;
/* 327 */       par1Entity.field_70159_w = mx;
/* 328 */       par1Entity.field_70181_x = my;
/* 329 */       par1Entity.field_70179_y = mz;
/*     */       
/* 331 */       func_184185_a(SoundsTC.swarmattack, 0.3F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Entity findPlayerToAttack() {
/* 339 */     double var1 = 8.0D;
/* 340 */     return getIsSummoned() ? null : this.field_70170_p.func_72890_a(this, var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 349 */     super.func_70037_a(par1NBTTagCompound);
/* 350 */     setIsSummoned(par1NBTTagCompound.func_74767_n("summoned"));
/* 351 */     this.damBonus = par1NBTTagCompound.func_74771_c("damBonus");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 360 */     super.func_70014_b(par1NBTTagCompound);
/* 361 */     par1NBTTagCompound.func_74757_a("summoned", getIsSummoned());
/* 362 */     par1NBTTagCompound.func_74774_a("damBonus", (byte)this.damBonus);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 371 */     int var1 = MathHelper.func_76128_c((func_174813_aQ()).field_72338_b);
/*     */     
/* 373 */     int var2 = MathHelper.func_76128_c(this.field_70165_t);
/* 374 */     int var3 = MathHelper.func_76128_c(this.field_70161_v);
/* 375 */     int var4 = this.field_70170_p.func_175699_k(new BlockPos(var2, var1, var3));
/* 376 */     byte var5 = 7;
/*     */     
/* 378 */     return (var4 > this.field_70146_Z.nextInt(var5)) ? false : super.func_70601_bi();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 384 */   protected boolean func_70814_o() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i) {
/* 395 */     if (this.field_70170_p.field_73012_v.nextBoolean())
/* 396 */       func_70099_a(ConfigItems.FLUX_CRYSTAL.func_77946_l(), this.field_70131_O / 2.0F); 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\tainted\EntityTaintSwarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */