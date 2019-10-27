/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityInhabitedZombie
/*     */   extends EntityZombie
/*     */   implements IEldritchMob
/*     */ {
/*     */   protected void func_110147_ax() {
/*  34 */     super.func_110147_ax();
/*  35 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*  36 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*  37 */     func_110148_a(field_110186_bp).func_111128_a(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityInhabitedZombie(World world) {
/*  42 */     super(world);
/*  43 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
/*  44 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, thaumcraft.common.entities.monster.cult.EntityCultist.class, true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70074_a(EntityLivingBase par1EntityLivingBase) {}
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) {
/*  53 */     float d = (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) ? 0.9F : 0.6F;
/*  54 */     func_184201_a(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonPlateHelm));
/*  55 */     if (this.field_70146_Z.nextFloat() <= d) func_184201_a(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonPlateChest)); 
/*  56 */     if (this.field_70146_Z.nextFloat() <= d) func_184201_a(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonPlateLegs)); 
/*  57 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70609_aI() {
/*  72 */     if (!this.field_70170_p.field_72995_K) {
/*  73 */       EntityEldritchCrab crab = new EntityEldritchCrab(this.field_70170_p);
/*  74 */       crab.func_70080_a(this.field_70165_t, this.field_70163_u + func_70047_e(), this.field_70161_v, this.field_70177_z, this.field_70125_A);
/*  75 */       crab.setHelm(true);
/*  76 */       this.field_70170_p.func_72838_d(crab);
/*     */       
/*  78 */       if ((this.field_70718_bc > 0 || func_70684_aJ()) && func_146066_aG() && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot")) {
/*     */         
/*  80 */         int i = func_70693_a(this.field_70717_bb);
/*     */         
/*  82 */         while (i > 0) {
/*     */           
/*  84 */           int j = EntityXPOrb.func_70527_a(i);
/*  85 */           i -= j;
/*  86 */           this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  91 */     for (int i = 0; i < 20; i++) {
/*     */       
/*  93 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  94 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  95 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  96 */       this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, this.field_70165_t + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, this.field_70163_u + (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, d2, d0, d1, new int[0]);
/*     */     } 
/*     */     
/*  99 */     func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource p_70645_1_) {}
/*     */ 
/*     */   
/* 107 */   protected SoundEvent func_184639_G() { return SoundsTC.crabtalk; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundEvents.field_187543_bD; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 119 */     List ents = this.field_70170_p.func_72872_a(EntityInhabitedZombie.class, (new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D))
/*     */ 
/*     */ 
/*     */         
/* 123 */         .func_72314_b(32.0D, 16.0D, 32.0D));
/*     */     
/* 125 */     return (ents.size() > 0) ? false : super.func_70601_bi();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityInhabitedZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */