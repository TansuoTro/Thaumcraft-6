/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityEldritchCrab extends EntityMob implements IEldritchMob {
/*     */   protected void func_184651_r() {
/*     */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*     */     this.field_70714_bg.func_75776_a(2, new EntityAILeapAtTarget(this, 0.63F));
/*     */     this.field_70714_bg.func_75776_a(3, new EntityAIAttackMelee(this, 1.0D, false));
/*     */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*     */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*     */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
/*     */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, true));
/*     */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, thaumcraft.common.entities.monster.cult.EntityCultist.class, true));
/*     */   }
/*     */   
/*     */   public double func_70033_W() { return func_184218_aH() ? 0.5D : 0.0D; }
/*     */   
/*     */   protected void func_110147_ax() {
/*     */     super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(hasHelm() ? 0.275D : 0.3D);
/*     */   }
/*     */   
/*     */   private static final DataParameter<Boolean> HELM = EntityDataManager.func_187226_a(EntityEldritchCrab.class, DataSerializers.field_187198_h);
/*     */   
/*  46 */   public EntityEldritchCrab(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     this.attackTime = 0; func_70105_a(0.8F, 0.6F);
/*     */     this.field_70728_aV = 6;
/*     */     ((PathNavigateGround)func_70661_as()).func_179688_b(true); } private static final DataParameter<Integer> RIDING = EntityDataManager.func_187226_a(EntityEldritchCrab.class, DataSerializers.field_187192_b); private int attackTime; protected void func_70088_a() { super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(HELM, Boolean.valueOf(false));
/*     */     func_184212_Q().func_187214_a(RIDING, Integer.valueOf(-1)); }
/* 161 */   public void func_70071_h_() { super.func_70071_h_();
/*     */     
/* 163 */     this.attackTime--;
/*     */     
/* 165 */     if (this.field_70173_aa < 20) this.field_70143_R = 0.0F; 
/* 166 */     if (!this.field_70170_p.field_72995_K) {
/* 167 */       if (func_184187_bx() == null && func_70638_az() != null && 
/* 168 */         !func_70638_az().func_184207_aI() && !this.field_70122_E && 
/* 169 */         !hasHelm() && !(func_70638_az()).field_70128_L && this.field_70163_u - 
/* 170 */         (func_70638_az()).field_70163_u >= ((func_70638_az()).field_70131_O / 2.0F) && 
/* 171 */         func_70068_e(func_70638_az()) < 4.0D) {
/* 172 */         func_184220_m(func_70638_az());
/* 173 */         setRiding(func_70638_az().func_145782_y());
/*     */       } 
/* 175 */       if (func_184187_bx() != null && !this.field_70128_L && 
/* 176 */         this.attackTime <= 0) {
/* 177 */         this.attackTime = 10 + this.field_70146_Z.nextInt(10);
/* 178 */         func_70652_k(func_184187_bx());
/* 179 */         if (this.field_70146_Z.nextFloat() < 0.2D) {
/* 180 */           func_184210_p();
/* 181 */           setRiding(-1);
/*     */         } 
/*     */       } 
/*     */       
/* 185 */       if (func_184187_bx() == null && getRiding() != -1) {
/* 186 */         setRiding(-1);
/*     */       }
/*     */     }
/* 189 */     else if (func_184187_bx() == null && getRiding() != -1) {
/* 190 */       Entity e = this.field_70170_p.func_73045_a(getRiding());
/* 191 */       if (e != null) func_184220_m(e);
/*     */     
/* 193 */     } else if (func_184187_bx() != null && getRiding() == -1) {
/* 194 */       func_184210_p();
/*     */     }  }
/*     */   
/*     */   public int getRiding() { return ((Integer)func_184212_Q().func_187225_a(RIDING)).intValue(); }
/*     */   public void setRiding(int s) { func_184212_Q().func_187227_b(RIDING, Integer.valueOf(s)); }
/*     */   
/*     */   public boolean func_98052_bS() { return false; }
/*     */   
/*     */   public int func_70658_aO() { return hasHelm() ? 5 : 0; }
/*     */   
/* 204 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) { EntitySpider.GroupData groupData; if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) { setHelm(true); } else { setHelm((this.field_70146_Z.nextFloat() < 0.33F)); }
/*     */      if (data == null) { groupData = new EntitySpider.GroupData(); if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD && this.field_70170_p.field_73012_v.nextFloat() < 0.1F * diff.func_180170_c())
/*     */         ((EntitySpider.GroupData)groupData).func_111104_a(this.field_70170_p.field_73012_v);  }
/*     */      if (groupData instanceof EntitySpider.GroupData) { Potion potion = ((EntitySpider.GroupData)groupData).field_188478_a; if (potion != null)
/*     */         func_70690_d(new PotionEffect(potion, 2147483647));  }
/* 210 */      return super.func_180482_a(diff, groupData); } protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) { super.func_70628_a(p_70628_1_, p_70628_2_);
/*     */     
/* 212 */     if (p_70628_1_ && (this.field_70146_Z.nextInt(3) == 0 || this.field_70146_Z.nextInt(1 + p_70628_2_) > 0))
/*     */     {
/* 214 */       func_145779_a(Items.field_151079_bi, 1); }  }
/*     */   
/*     */   public boolean hasHelm() { return ((Boolean)func_184212_Q().func_187225_a(HELM)).booleanValue(); }
/*     */   public void setHelm(boolean par1) { func_184212_Q().func_187227_b(HELM, Boolean.valueOf(par1)); }
/*     */   
/*     */   public boolean func_70652_k(Entity p_70652_1_) {
/* 220 */     if (super.func_70652_k(p_70652_1_)) {
/* 221 */       func_184185_a(SoundsTC.crabclaw, 1.0F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/* 222 */       return true;
/* 223 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage) {
/* 229 */     boolean b = super.func_70097_a(source, damage);
/*     */     
/* 231 */     if (hasHelm() && func_110143_aJ() / func_110138_aP() <= 0.5F) {
/* 232 */       setHelm(false);
/* 233 */       func_70669_a(new ItemStack(ItemsTC.crimsonPlateChest));
/* 234 */       func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */     } 
/*     */     
/* 237 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 244 */     super.func_70037_a(par1NBTTagCompound);
/* 245 */     setHelm(par1NBTTagCompound.func_74767_n("helm"));
/* 246 */     if (!hasHelm()) {
/* 247 */       func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 257 */     super.func_70014_b(par1NBTTagCompound);
/* 258 */     par1NBTTagCompound.func_74757_a("helm", hasHelm());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public int func_70627_aG() { return 160; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   protected SoundEvent func_184639_G() { return SoundsTC.crabtalk; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundEvents.field_187543_bD; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   protected SoundEvent func_184615_bR() { return SoundsTC.crabdeath; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) { func_184185_a(SoundEvents.field_187823_fN, 0.15F, 1.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 294 */   public EnumCreatureAttribute func_70668_bt() { return EnumCreatureAttribute.ARTHROPOD; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public boolean func_70687_e(PotionEffect p_70687_1_) { return p_70687_1_.func_188419_a().equals(MobEffects.field_76436_u) ? false : super.func_70687_e(p_70687_1_); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public boolean func_184191_r(Entity el) { return el instanceof EntityEldritchCrab; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityEldritchCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */