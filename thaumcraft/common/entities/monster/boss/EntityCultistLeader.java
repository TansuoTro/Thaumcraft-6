/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.projectile.EntityGolemOrb;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityCultistLeader extends EntityThaumcraftBoss implements IRangedAttackMob {
/*     */   protected void func_184651_r() {
/*     */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*     */     this.field_70714_bg.func_75776_a(2, new AILongRangeAttack(this, 16.0D, 1.0D, 30, 40, 24.0F));
/*     */     this.field_70714_bg.func_75776_a(3, new EntityAIAttackMelee(this, 1.1D, false));
/*     */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*     */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*     */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 8.0F));
/*     */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*     */     this.field_70715_bh.func_75776_a(1, new AICultistHurtByTarget(this, true));
/*     */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, true));
/*     */   }
/*     */   
/*  49 */   public EntityCultistLeader(World p_i1745_1_) { super(p_i1745_1_);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     this.titles = new String[] { "Alberic", "Anselm", "Bastian", "Beturian", "Chabier", "Chorache", "Chuse", "Dodorol", "Ebardo", "Ferrando", "Fertus", "Guillen", "Larpe", "Obano", "Zelipe" };
/*     */     func_70105_a(0.75F, 2.25F);
/*     */     this.field_70728_aV = 40; }
/*     */   protected void func_110147_ax() {
/*     */     super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(150.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/* 127 */   } private static final DataParameter<Byte> NAME = EntityDataManager.func_187226_a(EntityCultistLeader.class, DataSerializers.field_187191_a); protected void func_180481_a(DifficultyInstance difficulty) { func_184201_a(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonPraetorHelm));
/* 128 */     func_184201_a(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonPraetorChest));
/* 129 */     func_184201_a(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonPraetorLegs));
/* 130 */     func_184201_a(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
/* 131 */     if (this.field_70170_p.func_175659_aa() == EnumDifficulty.EASY) {
/* 132 */       func_184611_a(func_184600_cs(), new ItemStack(ItemsTC.voidSword));
/*     */     } else {
/* 134 */       func_184611_a(func_184600_cs(), new ItemStack(ItemsTC.crimsonBlade));
/*     */     }  }
/*     */   String[] titles;
/*     */   protected void func_70088_a() { super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(NAME, Byte.valueOf((byte)0)); } public void generateName() { int t = (int)func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e();
/*     */     if (t >= 0)
/*     */       func_96094_a(String.format(I18n.func_74838_a("entity.Thaumcraft.CultistLeader.name.custom"), new Object[] { getTitle(), ChampionModifier.mods[t].getModNameLocalized() }));  } private String getTitle() { return this.titles[((Byte)func_184212_Q().func_187225_a(NAME)).byteValue()]; } protected void func_180483_b(DifficultyInstance diff) {
/* 141 */     float f = diff.func_180170_c();
/* 142 */     if (func_184614_ca() != null && this.field_70146_Z.nextFloat() < 0.5F * f)
/*     */     {
/* 144 */       EnchantmentHelper.func_77504_a(this.field_70146_Z, func_184614_ca(), (int)(7.0F + f * this.field_70146_Z.nextInt(22)), false); }  } private void setTitle(int title) { func_184212_Q().func_187227_b(NAME, Byte.valueOf((byte)title)); } public void func_70014_b(NBTTagCompound nbt) {
/*     */     super.func_70014_b(nbt);
/*     */     nbt.func_74774_a("title", ((Byte)func_184212_Q().func_187225_a(NAME)).byteValue());
/*     */   } public void func_70037_a(NBTTagCompound nbt) {
/*     */     super.func_70037_a(nbt);
/*     */     setTitle(nbt.func_74771_c("title"));
/* 150 */   } public boolean func_184191_r(Entity el) { return (el instanceof EntityCultist || el instanceof EntityCultistLeader); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70686_a(Class clazz) {
/* 156 */     if (clazz == thaumcraft.common.entities.monster.cult.EntityCultistCleric.class || clazz == EntityCultistLeader.class || clazz == thaumcraft.common.entities.monster.cult.EntityCultistKnight.class)
/*     */     {
/*     */       
/* 159 */       return false; } 
/* 160 */     return super.func_70686_a(clazz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   protected void func_70628_a(boolean flag, int i) { func_70099_a(new ItemStack(ItemsTC.lootBag, 1, 2), 1.5F); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) {
/* 177 */     func_180481_a(diff);
/* 178 */     func_180483_b(diff);
/* 179 */     setTitle(this.field_70146_Z.nextInt(this.titles.length));
/* 180 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70619_bc() {
/* 185 */     super.func_70619_bc();
/* 186 */     List<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityCultist.class, 8.0D);
/* 187 */     for (Entity e : list) {
/*     */       try {
/* 189 */         if (e instanceof EntityCultist && !((EntityCultist)e).func_70644_a(MobEffects.field_76428_l)) {
/* 190 */           ((EntityCultist)e).func_70690_d(new PotionEffect(MobEffects.field_76428_l, 60, 1));
/*     */         }
/* 192 */       } catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
/* 198 */     if (func_70685_l(entitylivingbase)) {
/* 199 */       func_184609_a(func_184600_cs());
/* 200 */       func_70671_ap().func_75650_a(entitylivingbase.field_70165_t, 
/*     */           
/* 202 */           (entitylivingbase.func_174813_aQ()).field_72338_b + (entitylivingbase.field_70131_O / 2.0F), entitylivingbase.field_70161_v, 30.0F, 30.0F);
/*     */ 
/*     */       
/* 205 */       EntityGolemOrb blast = new EntityGolemOrb(this.field_70170_p, this, entitylivingbase, true);
/* 206 */       blast.field_70165_t += blast.field_70159_w / 2.0D;
/* 207 */       blast.field_70161_v += blast.field_70179_y / 2.0D;
/* 208 */       blast.func_70107_b(blast.field_70165_t, blast.field_70163_u, blast.field_70161_v);
/*     */       
/* 210 */       double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
/* 211 */       double d1 = (entitylivingbase.func_174813_aQ()).field_72338_b + (entitylivingbase.field_70131_O / 2.0F) - this.field_70163_u + (this.field_70131_O / 2.0F);
/* 212 */       double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
/*     */       
/* 214 */       blast.func_70186_c(d0, d1 + 2.0D, d2, 0.66F, 3.0F);
/*     */       
/* 216 */       func_184185_a(SoundsTC.egattack, 1.0F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 217 */       this.field_70170_p.func_72838_d(blast);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70656_aK() {
/* 224 */     if (this.field_70170_p.field_72995_K) {
/* 225 */       for (int i = 0; i < 20; i++) {
/*     */         
/* 227 */         double d0 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 228 */         double d1 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 229 */         double d2 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 230 */         double d3 = 2.0D;
/*     */         
/* 232 */         FXDispatcher.INSTANCE.cultistSpawn(this.field_70165_t + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N + d0 * d3, this.field_70163_u + (this.field_70146_Z
/* 233 */             .nextFloat() * this.field_70131_O) + d1 * d3, this.field_70161_v + (this.field_70146_Z
/* 234 */             .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N + d2 * d3, d0, d1, d2);
/*     */       } 
/*     */     } else {
/*     */       
/* 238 */       this.field_70170_p.func_72960_a(this, (byte)20);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_184724_a(boolean swingingArms) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\boss\EntityCultistLeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */