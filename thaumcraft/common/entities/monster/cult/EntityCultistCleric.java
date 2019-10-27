/*     */ package thaumcraft.common.entities.monster.cult;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
/*     */ import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
/*     */ import thaumcraft.common.entities.ai.misc.AIAltarFocus;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityCultistCleric
/*     */   extends EntityCultist
/*     */   implements IRangedAttackMob, IEntityAdditionalSpawnData {
/*     */   public int rage;
/*     */   
/*     */   public EntityCultistCleric(World p_i1745_1_) {
/*  49 */     super(p_i1745_1_);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     this.rage = 0;
/*     */   } protected void func_184651_r() { this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this)); this.field_70714_bg.func_75776_a(1, new AIAltarFocus(this)); this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 2.0D, 1.0D, 20, 40, 24.0F)); this.field_70714_bg.func_75776_a(3, new EntityAIAttackMelee(this, 1.0D, false)); this.field_70714_bg.func_75776_a(4, new EntityAIRestrictOpenDoor(this)); this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true)); this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D)); this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D)); this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F)); this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this)); this.field_70715_bh.func_75776_a(1, new AICultistHurtByTarget(this, true));
/*     */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*     */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, thaumcraft.common.entities.monster.EntityEldritchGuardian.class, true));
/*  95 */     this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTarget(this, net.minecraft.entity.monster.AbstractIllager.class, true)); } public void func_82196_d(EntityLivingBase entitylivingbase, float f) { double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
/*  96 */     double d1 = (entitylivingbase.func_174813_aQ()).field_72338_b + (entitylivingbase.field_70131_O / 2.0F) - this.field_70163_u + (this.field_70131_O / 2.0F);
/*  97 */     double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
/*  98 */     func_184609_a(func_184600_cs());
/*  99 */     float rf = this.field_70146_Z.nextFloat();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (rf > 0.66F) {
/* 111 */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, true);
/*     */ 
/*     */       
/* 114 */       Vec3d v = entitylivingbase.func_174791_d().func_72441_c(entitylivingbase.field_70159_w * 10.0D, entitylivingbase.field_70181_x * 10.0D, entitylivingbase.field_70179_y * 10.0D).func_178788_d(func_174791_d()).func_72432_b();
/* 115 */       blast.func_70107_b(blast.field_70165_t + v.field_72450_a, blast.field_70163_u + v.field_72448_b, blast.field_70161_v + v.field_72449_c);
/* 116 */       blast.func_70186_c(v.field_72450_a, v.field_72448_b, v.field_72449_c, 0.66F, 3.0F);
/* 117 */       func_184185_a(SoundsTC.egattack, 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 118 */       this.field_70170_p.func_72838_d(blast);
/*     */     } else {
/*     */       
/* 121 */       float f1 = MathHelper.func_76129_c(f) * 0.5F;
/* 122 */       this.field_70170_p.func_180498_a((EntityPlayer)null, 1009, func_180425_c(), 0);
/*     */       
/* 124 */       for (int i = 0; i < 3; i++) {
/*     */         
/* 126 */         EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.field_70170_p, this, d0 + this.field_70146_Z.nextGaussian() * f1, d1, d2 + this.field_70146_Z.nextGaussian() * f1);
/* 127 */         entitysmallfireball.field_70163_u = this.field_70163_u + (this.field_70131_O / 2.0F) + 0.5D;
/* 128 */         this.field_70170_p.func_72838_d(entitysmallfireball);
/*     */       } 
/*     */     }  } protected void func_110147_ax() { super.func_110147_ax(); func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(24.0D); }
/*     */   protected void setLoot(DifficultyInstance diff) { func_184201_a(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonRobeHelm));
/*     */     func_184201_a(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonRobeChest));
/*     */     func_184201_a(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonRobeLegs));
/*     */     if (this.field_70146_Z.nextFloat() < ((this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) ? 0.3F : 0.1F))
/*     */       func_184201_a(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));  }
/* 136 */   protected boolean func_70692_ba() { return !getIsRitualist(); }
/*     */ 
/*     */   
/* 139 */   private static final DataParameter<Boolean> RITUALIST = EntityDataManager.func_187226_a(EntityCultistCleric.class, DataSerializers.field_187198_h);
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70088_a() {
/* 144 */     super.func_70088_a();
/* 145 */     func_184212_Q().func_187214_a(RITUALIST, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 150 */   public boolean getIsRitualist() { return ((Boolean)func_184212_Q().func_187225_a(RITUALIST)).booleanValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setIsRitualist(boolean par1) { func_184212_Q().func_187227_b(RITUALIST, Boolean.valueOf(par1)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
/* 161 */     if (func_180431_b(p_70097_1_))
/*     */     {
/* 163 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 167 */     setIsRitualist(false);
/* 168 */     return super.func_70097_a(p_70097_1_, p_70097_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 178 */     super.func_70037_a(par1NBTTagCompound);
/* 179 */     setIsRitualist(Boolean.valueOf(par1NBTTagCompound.func_74767_n("ritualist")).booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 185 */     super.func_70014_b(par1NBTTagCompound);
/* 186 */     par1NBTTagCompound.func_74757_a("ritualist", getIsRitualist());
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/* 191 */     data.writeInt(func_180486_cf().func_177958_n());
/* 192 */     data.writeInt(func_180486_cf().func_177956_o());
/* 193 */     data.writeInt(func_180486_cf().func_177952_p());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 198 */   public void readSpawnData(ByteBuf data) { func_175449_a(new BlockPos(data.readInt(), data.readInt(), data.readInt()), 8); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 205 */     super.func_70071_h_();
/* 206 */     if (this.field_70170_p.field_72995_K && getIsRitualist()) {
/* 207 */       double d0 = func_180486_cf().func_177958_n() + 0.5D - this.field_70165_t;
/* 208 */       double d1 = func_180486_cf().func_177956_o() + 1.5D - this.field_70163_u + func_70047_e();
/* 209 */       double d2 = func_180486_cf().func_177952_p() + 0.5D - this.field_70161_v;
/* 210 */       double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 211 */       float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
/* 212 */       float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
/* 213 */       this.field_70125_A = updateRotation(this.field_70125_A, f1, 10.0F);
/* 214 */       this.field_70759_as = updateRotation(this.field_70759_as, f, func_70646_bf());
/*     */     } 
/* 216 */     if (!this.field_70170_p.field_72995_K && getIsRitualist() && 
/* 217 */       this.rage >= 5) setIsRitualist(false);
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_) {
/* 223 */     float f3 = MathHelper.func_76142_g(p_75652_2_ - p_75652_1_);
/*     */     
/* 225 */     if (f3 > p_75652_3_)
/*     */     {
/* 227 */       f3 = p_75652_3_;
/*     */     }
/*     */     
/* 230 */     if (f3 < -p_75652_3_)
/*     */     {
/* 232 */       f3 = -p_75652_3_;
/*     */     }
/*     */     
/* 235 */     return p_75652_1_ + f3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   protected SoundEvent func_184639_G() { return SoundsTC.chant; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public int func_70627_aG() { return 500; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1) {
/* 255 */     if (par1 == 19) {
/*     */       
/* 257 */       for (int i = 0; i < 3; i++)
/*     */       {
/* 259 */         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 260 */         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 261 */         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 262 */         this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + (this.field_70146_Z
/* 263 */             .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, this.field_70163_u + 0.5D + (this.field_70146_Z
/* 264 */             .nextFloat() * this.field_70131_O), this.field_70161_v + (this.field_70146_Z
/* 265 */             .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, d0, d1, d2, new int[0]);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 270 */       super.func_70103_a(par1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_184724_a(boolean swingingArms) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\cult\EntityCultistCleric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */