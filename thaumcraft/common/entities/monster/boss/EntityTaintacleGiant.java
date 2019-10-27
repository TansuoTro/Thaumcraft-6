/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.BossInfo;
/*     */ import net.minecraft.world.BossInfoServer;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintacle;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityTaintacleGiant
/*     */   extends EntityTaintacle
/*     */   implements ITaintedMob, IEldritchMob
/*     */ {
/*  32 */   protected final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(
/*  33 */       func_145748_c_(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).func_186741_a(true);
/*     */ 
/*     */   
/*     */   public EntityTaintacleGiant(World par1World) {
/*  37 */     super(par1World);
/*  38 */     func_70105_a(1.1F, 6.0F);
/*  39 */     this.field_70728_aV = 20;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/*  44 */     super.func_110147_ax();
/*  45 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(175.0D);
/*  46 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(9.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) {
/*  53 */     EntityUtils.makeChampion(this, true);
/*  54 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  60 */     super.func_70071_h_();
/*     */     
/*  62 */     if (getAnger() > 0) setAnger(getAnger() - 1); 
/*  63 */     if (this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(15) == 0 && getAnger() > 0) {
/*  64 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  65 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  66 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/*  67 */       this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + (this.field_70146_Z
/*  68 */           .nextFloat() * this.field_70130_N) - this.field_70130_N / 2.0D, 
/*  69 */           (func_174813_aQ()).field_72338_b + this.field_70131_O + this.field_70146_Z.nextFloat() * 0.5D, this.field_70161_v + (this.field_70146_Z
/*  70 */           .nextFloat() * this.field_70130_N) - this.field_70130_N / 2.0D, d0, d1, d2, new int[0]);
/*     */     } 
/*     */ 
/*     */     
/*  74 */     if (!this.field_70170_p.field_72995_K && 
/*  75 */       this.field_70173_aa % 30 == 0)
/*     */     {
/*  77 */       func_70691_i(1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  83 */   private static final DataParameter<Integer> AGGRO = EntityDataManager.func_187226_a(EntityTaintacleGiant.class, DataSerializers.field_187192_b);
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  88 */     super.func_70088_a();
/*  89 */     func_184212_Q().func_187214_a(AGGRO, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public int getAnger() { return ((Integer)func_184212_Q().func_187225_a(AGGRO)).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setAnger(int par1) { func_184212_Q().func_187227_b(AGGRO, Integer.valueOf(par1)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public boolean func_70601_bi() { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i) {
/* 110 */     List<EntityTaintacleGiant> ents = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityTaintacleGiant.class, 48.0D);
/* 111 */     if (ents == null || ents.size() <= 0) {
/* 112 */       EntityUtils.entityDropSpecialItem(this, new ItemStack(ItemsTC.primordialPearl), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 118 */   protected boolean func_70692_ba() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public boolean func_70648_aU() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   protected int func_70682_h(int air) { return air; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage) {
/* 136 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 138 */       if (damage > 35.0F) {
/* 139 */         if (getAnger() == 0) {
/*     */           try {
/* 141 */             func_70690_d(new PotionEffect(MobEffects.field_76428_l, 200, (int)(damage / 15.0F)));
/* 142 */             func_70690_d(new PotionEffect(MobEffects.field_76420_g, 200, (int)(damage / 10.0F)));
/* 143 */             func_70690_d(new PotionEffect(MobEffects.field_76422_e, 200, (int)(damage / 40.0F)));
/* 144 */             setAnger(200);
/* 145 */           } catch (Exception exception) {}
/* 146 */           if (source.func_76346_g() != null && source.func_76346_g() instanceof EntityPlayer) {
/* 147 */             ((EntityPlayer)source.func_76346_g()).func_146105_b(new TextComponentTranslation(func_70005_c_() + " " + I18n.func_74838_a("tc.boss.enrage"), new Object[0]), true);
/*     */           }
/*     */         } 
/* 150 */         damage = 35.0F;
/*     */       } 
/*     */     }
/*     */     
/* 154 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70619_bc() {
/* 159 */     super.func_70619_bc();
/* 160 */     this.bossInfo.func_186735_a(func_110143_aJ() / func_110138_aP());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184203_c(EntityPlayerMP player) {
/* 166 */     super.func_184203_c(player);
/* 167 */     this.bossInfo.func_186761_b(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184178_b(EntityPlayerMP player) {
/* 173 */     super.func_184178_b(player);
/* 174 */     this.bossInfo.func_186760_a(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public boolean func_184222_aU() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\boss\EntityTaintacleGiant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */