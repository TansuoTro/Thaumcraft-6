/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityFlying;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXWispZap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityWisp
/*     */   extends EntityFlying
/*     */   implements IMob
/*     */ {
/*     */   public int courseChangeCooldown;
/*     */   public double waypointX;
/*     */   public double waypointY;
/*     */   public double waypointZ;
/*     */   private int aggroCooldown;
/*     */   public int prevAttackCounter;
/*     */   public int attackCounter;
/*     */   private BlockPos currentFlightTarget;
/*     */   
/*     */   public EntityWisp(World world) {
/*  49 */     super(world);
/*  50 */     this.courseChangeCooldown = 0;
/*  51 */     this.aggroCooldown = 0;
/*  52 */     this.prevAttackCounter = 0;
/*  53 */     this.attackCounter = 0;
/*  54 */     func_70105_a(0.9F, 0.9F);
/*  55 */     this.field_70728_aV = 5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/*  61 */     super.func_110147_ax();
/*  62 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(22.0D);
/*  63 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/*  64 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  69 */   protected boolean func_70041_e_() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public int func_70682_h(int par1) { return par1; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource damagesource, float i) {
/*  81 */     if (damagesource.func_76346_g() instanceof EntityLivingBase) {
/*  82 */       func_70624_b((EntityLivingBase)damagesource.func_76346_g());
/*  83 */       this.aggroCooldown = 200;
/*     */     } 
/*  85 */     return super.func_70097_a(damagesource, i);
/*     */   }
/*     */ 
/*     */   
/*  89 */   private static final DataParameter<String> TYPE = EntityDataManager.func_187226_a(EntityWisp.class, DataSerializers.field_187194_d);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  95 */     super.func_70088_a();
/*  96 */     func_184212_Q().func_187214_a(TYPE, String.valueOf(""));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource par1DamageSource) {
/* 102 */     super.func_70645_a(par1DamageSource);
/* 103 */     if (this.field_70170_p.field_72995_K) {
/* 104 */       FXDispatcher.INSTANCE.burst(this.field_70165_t, this.field_70163_u + 0.44999998807907104D, this.field_70161_v, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 111 */     super.func_70071_h_();
/* 112 */     if (this.field_70170_p.field_72995_K && 
/* 113 */       this.field_70173_aa <= 1) {
/* 114 */       FXDispatcher.INSTANCE.burst(this.field_70165_t, this.field_70163_u, this.field_70161_v, 10.0F);
/*     */     }
/*     */     
/* 117 */     if (this.field_70170_p.field_72995_K && this.field_70170_p.field_73012_v.nextBoolean() && Aspect.getAspect(getType()) != null) {
/* 118 */       FXDispatcher.INSTANCE.drawWispParticles(this.field_70165_t + ((this.field_70170_p.field_73012_v
/* 119 */           .nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F), this.field_70163_u + ((this.field_70170_p.field_73012_v
/* 120 */           .nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F), this.field_70161_v + ((this.field_70170_p.field_73012_v
/* 121 */           .nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7F), 0.0D, 0.0D, 0.0D, 
/* 122 */           Aspect.getAspect(getType()).getColor(), 0);
/*     */     }
/*     */     
/* 125 */     this.field_70181_x *= 0.6000000238418579D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 130 */   public String getType() { return (String)func_184212_Q().func_187225_a(TYPE); }
/*     */ 
/*     */ 
/*     */   
/* 134 */   public void setType(String t) { func_184212_Q().func_187227_b(TYPE, String.valueOf(t)); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 139 */     super.func_70636_d();
/*     */     
/* 141 */     if (func_70613_aW()) {
/*     */       
/* 143 */       if (!this.field_70170_p.field_72995_K && Aspect.getAspect(getType()) == null) {
/* 144 */         if (this.field_70170_p.field_73012_v.nextInt(10) != 0) {
/* 145 */           ArrayList<Aspect> as = Aspect.getPrimalAspects();
/* 146 */           setType(((Aspect)as.get(this.field_70170_p.field_73012_v.nextInt(as.size()))).getTag());
/*     */         } else {
/* 148 */           ArrayList<Aspect> as = Aspect.getCompoundAspects();
/* 149 */           setType(((Aspect)as.get(this.field_70170_p.field_73012_v.nextInt(as.size()))).getTag());
/*     */         } 
/*     */       }
/*     */       
/* 153 */       if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL)
/*     */       {
/* 155 */         func_70106_y();
/*     */       }
/*     */       
/* 158 */       this.prevAttackCounter = this.attackCounter;
/*     */       
/* 160 */       double attackrange = 16.0D;
/*     */ 
/*     */       
/* 163 */       if (func_70638_az() == null || !func_70685_l(func_70638_az())) {
/*     */         
/* 165 */         if (this.currentFlightTarget != null && (!this.field_70170_p.func_175623_d(this.currentFlightTarget) || this.currentFlightTarget
/* 166 */           .func_177956_o() < 1 || this.currentFlightTarget
/* 167 */           .func_177956_o() > this.field_70170_p.func_175725_q(this.currentFlightTarget).func_177981_b(8).func_177956_o()))
/*     */         {
/* 169 */           this.currentFlightTarget = null;
/*     */         }
/*     */         
/* 172 */         if (this.currentFlightTarget == null || this.field_70146_Z.nextInt(30) == 0 || 
/* 173 */           func_174831_c(this.currentFlightTarget) < 4.0D)
/*     */         {
/* 175 */           this
/*     */ 
/*     */             
/* 178 */             .currentFlightTarget = new BlockPos((int)this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int)this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int)this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
/*     */         }
/*     */         
/* 181 */         double var1 = this.currentFlightTarget.func_177958_n() + 0.5D - this.field_70165_t;
/* 182 */         double var3 = this.currentFlightTarget.func_177956_o() + 0.1D - this.field_70163_u;
/* 183 */         double var5 = this.currentFlightTarget.func_177952_p() + 0.5D - this.field_70161_v;
/* 184 */         this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 185 */         this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 186 */         this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 187 */         float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
/* 188 */         float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 189 */         this.field_191988_bg = 0.15F;
/* 190 */         this.field_70177_z += var8;
/*     */       }
/* 192 */       else if (func_70068_e(func_70638_az()) > attackrange * attackrange / 2.0D && 
/* 193 */         func_70685_l(func_70638_az())) {
/*     */         
/* 195 */         double var1 = (func_70638_az()).field_70165_t - this.field_70165_t;
/* 196 */         double var3 = (func_70638_az()).field_70163_u + (func_70638_az().func_70047_e() * 0.66F) - this.field_70163_u;
/* 197 */         double var5 = (func_70638_az()).field_70161_v - this.field_70161_v;
/* 198 */         this.field_70159_w += (Math.signum(var1) * 0.5D - this.field_70159_w) * 0.10000000149011612D;
/* 199 */         this.field_70181_x += (Math.signum(var3) * 0.699999988079071D - this.field_70181_x) * 0.10000000149011612D;
/* 200 */         this.field_70179_y += (Math.signum(var5) * 0.5D - this.field_70179_y) * 0.10000000149011612D;
/* 201 */         float var7 = (float)(Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / Math.PI) - 90.0F;
/* 202 */         float var8 = MathHelper.func_76142_g(var7 - this.field_70177_z);
/* 203 */         this.field_191988_bg = 0.5F;
/* 204 */         this.field_70177_z += var8;
/*     */       } 
/*     */       
/* 207 */       if (func_70638_az() instanceof EntityPlayer && ((EntityPlayer)func_70638_az()).field_71075_bZ.field_75102_a)
/*     */       {
/* 209 */         func_70624_b(null);
/*     */       }
/*     */       
/* 212 */       if (func_70638_az() != null && (func_70638_az()).field_70128_L)
/*     */       {
/* 214 */         func_70624_b(null);
/*     */       }
/*     */       
/* 217 */       this.aggroCooldown--;
/*     */ 
/*     */       
/* 220 */       if (this.field_70170_p.field_73012_v.nextInt(1000) == 0 && (func_70638_az() == null || this.aggroCooldown-- <= 0)) {
/*     */         
/* 222 */         func_70624_b(this.field_70170_p.func_72890_a(this, 16.0D));
/* 223 */         if (func_70638_az() != null)
/*     */         {
/* 225 */           this.aggroCooldown = 50;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 230 */       if (func_70089_S() && func_70638_az() != null && func_70638_az().func_70068_e(this) < attackrange * attackrange) {
/*     */         
/* 232 */         double d5 = (func_70638_az()).field_70165_t - this.field_70165_t;
/* 233 */         double d6 = (func_70638_az().func_174813_aQ()).field_72338_b + ((func_70638_az()).field_70131_O / 2.0F) - this.field_70163_u + (this.field_70131_O / 2.0F);
/* 234 */         double d7 = (func_70638_az()).field_70161_v - this.field_70161_v;
/* 235 */         this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(d5, d7)) * 180.0F / 3.141593F;
/* 236 */         if (func_70685_l(func_70638_az())) {
/*     */           
/* 238 */           this.attackCounter++;
/* 239 */           if (this.attackCounter == 20)
/*     */           {
/* 241 */             func_184185_a(SoundsTC.zap, 1.0F, 1.1F);
/*     */             
/* 243 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXWispZap(
/* 244 */                   func_145782_y(), func_70638_az().func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w
/* 245 */                   .getDimension(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */             
/* 247 */             float damage = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*     */             
/* 249 */             if (Math.abs((func_70638_az()).field_70159_w) > 0.10000000149011612D || 
/* 250 */               Math.abs((func_70638_az()).field_70181_x) > 0.10000000149011612D || 
/* 251 */               Math.abs((func_70638_az()).field_70179_y) > 0.10000000149011612D) {
/* 252 */               if (this.field_70170_p.field_73012_v.nextFloat() < 0.4F) {
/* 253 */                 func_70638_az().func_70097_a(DamageSource.func_76358_a(this), damage);
/*     */               }
/*     */             }
/* 256 */             else if (this.field_70170_p.field_73012_v.nextFloat() < 0.66F) {
/* 257 */               func_70638_az().func_70097_a(DamageSource.func_76358_a(this), damage + 1.0F);
/*     */             } 
/* 259 */             this.attackCounter = -20 + this.field_70170_p.field_73012_v.nextInt(20);
/*     */           }
/*     */         
/* 262 */         } else if (this.attackCounter > 0) {
/*     */           
/* 264 */           this.attackCounter--;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 275 */   protected SoundEvent func_184639_G() { return SoundsTC.wisplive; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundEvents.field_187659_cY; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   protected SoundEvent func_184615_bR() { return SoundsTC.wispdead; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i) {
/* 299 */     if (Aspect.getAspect(getType()) != null) {
/* 300 */       func_70099_a(ThaumcraftApiHelper.makeCrystal(Aspect.getAspect(getType())), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   protected float func_70599_aP() { return 0.25F; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   protected boolean func_70692_ba() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 319 */     int count = 0;
/*     */     try {
/* 321 */       List l = this.field_70170_p.func_72872_a(EntityWisp.class, 
/* 322 */           func_174813_aQ().func_72314_b(16.0D, 16.0D, 16.0D));
/* 323 */       if (l != null) count = l.size(); 
/* 324 */     } catch (Exception exception) {}
/*     */     
/* 326 */     return (count < 8 && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && 
/* 327 */       isValidLightLevel() && super.func_70601_bi());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 333 */     BlockPos blockpos = new BlockPos(this.field_70165_t, (func_174813_aQ()).field_72338_b, this.field_70161_v);
/*     */     
/* 335 */     if (this.field_70170_p.func_175642_b(EnumSkyBlock.SKY, blockpos) > this.field_70146_Z.nextInt(32))
/*     */     {
/* 337 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 341 */     int i = this.field_70170_p.func_175671_l(blockpos);
/*     */     
/* 343 */     if (this.field_70170_p.func_72911_I()) {
/*     */       
/* 345 */       int j = this.field_70170_p.func_175657_ab();
/* 346 */       this.field_70170_p.func_175692_b(10);
/* 347 */       i = this.field_70170_p.func_175671_l(blockpos);
/* 348 */       this.field_70170_p.func_175692_b(j);
/*     */     } 
/*     */     
/* 351 */     return (i <= this.field_70146_Z.nextInt(8));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbttagcompound) {
/* 358 */     super.func_70014_b(nbttagcompound);
/* 359 */     nbttagcompound.func_74778_a("Type", getType());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbttagcompound) {
/* 365 */     super.func_70037_a(nbttagcompound);
/* 366 */     setType(nbttagcompound.func_74779_i("Type"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 372 */   public int func_70641_bl() { return 2; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityWisp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */