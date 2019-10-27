/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIAttackRanged;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityTippedArrow;
/*     */ import net.minecraft.init.Enchantments;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.inventory.ItemStackHelper;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.casters.FocusMediumRoot;
/*     */ import thaumcraft.api.casters.FocusPackage;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.items.casters.foci.FocusEffectAir;
/*     */ import thaumcraft.common.items.casters.foci.FocusMediumProjectile;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.world.biomes.BiomeHandler;
/*     */ 
/*     */ public class EntityPech extends EntityMob implements IRangedAttackMob {
/*     */   public NonNullList<ItemStack> loot;
/*     */   public boolean trading;
/*     */   private final EntityAIAttackRanged aiArrowAttack;
/*     */   private final EntityAIAttackRanged aiBlastAttack;
/*     */   private final EntityAIAttackMelee aiMeleeAttack;
/*     */   private final EntityAIAvoidEntity aiAvoidPlayer;
/*     */   
/*     */   public String func_70005_c_() {
/*     */     if (func_145818_k_())
/*     */       return func_95999_t(); 
/*     */     switch (getPechType()) {
/*     */       case 0:
/*     */         return I18n.func_74838_a("entity.Pech.name");
/*     */       case 1:
/*     */         return I18n.func_74838_a("entity.Pech.1.name");
/*     */       case 2:
/*     */         return I18n.func_74838_a("entity.Pech.2.name");
/*     */     } 
/*     */     return I18n.func_74838_a("entity.Pech.name");
/*     */   }
/*     */   
/*     */   protected void func_184651_r() {
/*     */     ((PathNavigateGround)func_70661_as()).func_179693_d(false);
/*     */     func_184642_a(EntityEquipmentSlot.MAINHAND, 0.2F);
/*     */     func_184642_a(EntityEquipmentSlot.OFFHAND, 0.2F);
/*     */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*     */     this.field_70714_bg.func_75776_a(1, new AIPechTradePlayer(this));
/*     */     this.field_70714_bg.func_75776_a(3, new AIPechItemEntityGoto(this));
/*     */     this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true));
/*     */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.5D));
/*     */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
/*     */     this.field_70714_bg.func_75776_a(9, new EntityAIWander(this, 0.6D));
/*     */     this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
/*     */     this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, net.minecraft.entity.EntityLiving.class, 8.0F));
/*     */     this.field_70714_bg.func_75776_a(11, new EntityAILookIdle(this));
/*     */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
/*     */     this.field_70715_bh.func_75776_a(2, new AINearestAttackableTargetPech(this, EntityPlayer.class, true));
/*     */   }
/*     */   
/*  93 */   public EntityPech(World world) { super(world);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     this.loot = NonNullList.func_191197_a(9, ItemStack.field_190927_a);
/*     */     
/* 117 */     this.trading = false;
/*     */     
/* 119 */     this.aiArrowAttack = new EntityAIAttackRanged(this, 0.6D, 20, 50, 15.0F);
/* 120 */     this.aiBlastAttack = new EntityAIAttackRanged(this, 0.6D, 20, 50, 15.0F);
/* 121 */     this.aiMeleeAttack = new EntityAIAttackMelee(this, 0.6D, false);
/* 122 */     this.aiAvoidPlayer = new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.5D, 0.6D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 509 */     this.mumble = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 631 */     this.chargecount = 0; func_70105_a(0.6F, 1.8F); this.field_70728_aV = 8; }
/*     */   public void setCombatTask() { if (this.field_70170_p != null && !this.field_70170_p.field_72995_K) { this.field_70714_bg.func_85156_a(this.aiMeleeAttack); this.field_70714_bg.func_85156_a(this.aiArrowAttack); this.field_70714_bg.func_85156_a(this.aiBlastAttack); ItemStack itemstack = func_184614_ca(); if (itemstack.func_77973_b() == Items.field_151031_f) { this.field_70714_bg.func_75776_a(2, this.aiArrowAttack); } else if (itemstack.func_77973_b() == ItemsTC.pechWand) { this.field_70714_bg.func_75776_a(2, this.aiBlastAttack); } else { this.field_70714_bg.func_75776_a(2, this.aiMeleeAttack); }  if (isTamed()) { this.field_70714_bg.func_85156_a(this.aiAvoidPlayer); } else { this.field_70714_bg.func_75776_a(4, this.aiAvoidPlayer); }  }  }
/*     */   public void func_82196_d(EntityLivingBase target, float f) { if (getPechType() == 2) { EntityTippedArrow entityarrow = new EntityTippedArrow(this.field_70170_p, this); if (this.field_70170_p.field_73012_v.nextFloat() < 0.2D) { ItemStack itemstack = new ItemStack(Items.field_185167_i); PotionUtils.func_185184_a(itemstack, Collections.singletonList(new PotionEffect(MobEffects.field_76436_u, 40))); entityarrow.func_184555_a(itemstack); }  double d0 = target.field_70165_t - this.field_70165_t; double d1 = (target.func_174813_aQ()).field_72338_b + (target.field_70131_O / 3.0F) - entityarrow.field_70163_u; double d2 = target.field_70161_v - this.field_70161_v; double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2); entityarrow.func_70186_c(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - this.field_70170_p.func_175659_aa().func_151525_a() * 4)); int i = EnchantmentHelper.func_185284_a(Enchantments.field_185309_u, this); int j = EnchantmentHelper.func_185284_a(Enchantments.field_185310_v, this); entityarrow.func_70239_b((f * 2.0F) + this.field_70146_Z.nextGaussian() * 0.25D + (this.field_70170_p.func_175659_aa().func_151525_a() * 0.11F)); if (i > 0) entityarrow.func_70239_b(entityarrow.func_70242_d() + i * 0.5D + 0.5D);  if (j > 0) entityarrow.func_70240_a(j);  if (EnchantmentHelper.func_185284_a(Enchantments.field_185311_w, this) > 0) entityarrow.func_70015_d(100);  func_184185_a(SoundEvents.field_187737_v, 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F)); this.field_70170_p.func_72838_d(entityarrow); } else if (getPechType() == 1) { FocusPackage p = new FocusPackage(this); FocusMediumRoot root = new FocusMediumRoot(); double off = (func_70032_d(target) / 6.0F); root.setupFromCasterToTarget(this, target, off); p.addNode(root); FocusMediumProjectile fp = new FocusMediumProjectile(); fp.initialize(); fp.getSetting("speed").setValue(2); p.addNode(fp); p.addNode(this.field_70146_Z.nextBoolean() ? new FocusEffectCurse() : (this.field_70146_Z.nextBoolean() ? new FocusEffectFlux() : (this.field_70146_Z.nextBoolean() ? new FocusEffectEarth() : (this.field_70146_Z.nextBoolean() ? new FocusEffectAir() : new FocusEffectFire())))); FocusEngine.castFocusPackage(this, p, true); func_184609_a(func_184600_cs()); }  }
/*     */   public void func_184201_a(EntityEquipmentSlot slotIn, @Nullable ItemStack stack) { super.func_184201_a(slotIn, stack); if (!this.field_70170_p.field_72995_K && slotIn == EntityEquipmentSlot.MAINHAND) setCombatTask();  }
/* 635 */   protected void func_180481_a(DifficultyInstance difficulty) { super.func_180481_a(difficulty); switch (this.field_70146_Z.nextInt(20)) { case 0: case 12: func_184611_a(func_184600_cs(), new ItemStack(ItemsTC.pechWand)); break;case 1: func_184611_a(func_184600_cs(), new ItemStack(Items.field_151052_q)); break;case 3: func_184611_a(func_184600_cs(), new ItemStack(Items.field_151049_t)); break;case 5: func_184611_a(func_184600_cs(), new ItemStack(Items.field_151040_l)); break;case 6: func_184611_a(func_184600_cs(), new ItemStack(Items.field_151036_c)); break;case 7: func_184611_a(func_184600_cs(), new ItemStack(Items.field_151112_aM)); break;case 8: func_184611_a(func_184600_cs(), new ItemStack(Items.field_151050_s)); break;case 9: func_184611_a(func_184600_cs(), new ItemStack(Items.field_151035_b)); break;case 2: case 4: case 10: case 11: case 13: func_184611_a(func_184600_cs(), new ItemStack(Items.field_151031_f)); break; }  } public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) { func_180481_a(diff); ItemStack itemstack = func_184586_b(func_184600_cs()); if (itemstack.func_77973_b() == ItemsTC.pechWand) { setPechType(1); func_184642_a((func_184600_cs() == EnumHand.MAIN_HAND) ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, 0.1F); } else if (!itemstack.func_190926_b()) { if (itemstack.func_77973_b() == Items.field_151031_f) setPechType(2);  func_180483_b(diff); }  float f = diff.func_180170_c(); func_98053_h((this.field_70146_Z.nextFloat() < 0.75F * f)); setCombatTask(); return super.func_180482_a(diff, data); } public boolean func_70601_bi() { Biome biome = this.field_70170_p.func_180494_b(new BlockPos(this)); boolean magicBiome = false; if (biome != null) magicBiome = BiomeDictionary.hasType(biome, BiomeDictionary.Type.MAGICAL);  int count = 0; try { List l = this.field_70170_p.func_72872_a(EntityPech.class, func_174813_aQ().func_72314_b(16.0D, 16.0D, 16.0D)); if (l != null) count = l.size();  } catch (Exception exception) {} if (this.field_70170_p.field_73011_w.getDimension() != ModConfig.CONFIG_WORLD.overworldDim && biome != BiomeHandler.MAGICAL_FOREST && biome != BiomeHandler.EERIE) magicBiome = false;  return (count < 4 && magicBiome && super.func_70601_bi()); } public float func_70047_e() { return this.field_70131_O * 0.66F; } public void func_70636_d() { super.func_70636_d(); } private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityPech.class, DataSerializers.field_187191_a); private static final DataParameter<Integer> ANGER = EntityDataManager.func_187226_a(EntityPech.class, DataSerializers.field_187192_b); private static final DataParameter<Boolean> TAMED = EntityDataManager.func_187226_a(EntityPech.class, DataSerializers.field_187198_h); protected void func_70088_a() { super.func_70088_a(); func_184212_Q().func_187214_a(TYPE, Byte.valueOf((byte)0)); func_184212_Q().func_187214_a(ANGER, Integer.valueOf(0)); func_184212_Q().func_187214_a(TAMED, Boolean.valueOf(false)); } public int getPechType() { return ((Byte)func_184212_Q().func_187225_a(TYPE)).byteValue(); } public int getAnger() { return ((Integer)func_184212_Q().func_187225_a(ANGER)).intValue(); } public boolean isTamed() { return ((Boolean)func_184212_Q().func_187225_a(TAMED)).booleanValue(); } public void setPechType(int par1) { func_184212_Q().func_187227_b(TYPE, Byte.valueOf((byte)par1)); } public void setAnger(int par1) { func_184212_Q().func_187227_b(ANGER, Integer.valueOf(par1)); } public void setTamed(boolean par1) { func_184212_Q().func_187227_b(TAMED, Boolean.valueOf(par1)); } public void func_70071_h_() { if (this.mumble > 0.0F) this.mumble *= 0.75F; 
/* 636 */     if (getAnger() > 0) setAnger(getAnger() - 1);
/*     */     
/* 638 */     if (getAnger() > 0 && func_70638_az() != null) {
/* 639 */       if (this.chargecount > 0) this.chargecount--; 
/* 640 */       if (this.chargecount == 0) {
/* 641 */         this.chargecount = 100;
/* 642 */         func_184185_a(SoundsTC.pech_charge, func_70599_aP(), func_70647_i());
/*     */       } 
/* 644 */       this.field_70170_p.func_72960_a(this, (byte)17);
/*     */     } 
/*     */     
/* 647 */     if (this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(15) == 0 && getAnger() > 0) {
/* 648 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 649 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 650 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 651 */       this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + (this.field_70146_Z
/* 652 */           .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, this.field_70163_u + 0.5D + (this.field_70146_Z
/* 653 */           .nextFloat() * this.field_70131_O), this.field_70161_v + (this.field_70146_Z
/* 654 */           .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, d0, d1, d2, new int[0]);
/*     */     } 
/*     */     
/* 657 */     if (this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(25) == 0 && isTamed()) {
/* 658 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 659 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 660 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 661 */       this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_HAPPY, this.field_70165_t + (this.field_70146_Z
/* 662 */           .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, this.field_70163_u + 0.5D + (this.field_70146_Z
/* 663 */           .nextFloat() * this.field_70131_O), this.field_70161_v + (this.field_70146_Z
/* 664 */           .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, d0, d1, d2, new int[0]);
/*     */     } 
/*     */     
/* 667 */     super.func_70071_h_(); } protected void func_110147_ax() { super.func_110147_ax(); func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D); func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D); func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D); } public void func_70014_b(NBTTagCompound nbt) { super.func_70014_b(nbt); nbt.func_74774_a("PechType", (byte)getPechType()); nbt.func_74777_a("Anger", (short)getAnger()); nbt.func_74757_a("Tamed", isTamed()); ItemStackHelper.func_191282_a(nbt, this.loot); } public void func_70037_a(NBTTagCompound nbt) { super.func_70037_a(nbt); if (nbt.func_74764_b("PechType")) { byte b0 = nbt.func_74771_c("PechType"); setPechType(b0); }  setAnger(nbt.func_74765_d("Anger")); setTamed(nbt.func_74767_n("Tamed")); this.loot = NonNullList.func_191197_a(9, ItemStack.field_190927_a); ItemStackHelper.func_191283_b(nbt, this.loot); setCombatTask(); } protected boolean func_70692_ba() { try { if (this.loot == null) return true;  int q = 0; for (ItemStack is : this.loot) { if (is != null && is.func_190916_E() > 0) q++;  }  return (q < 5); } catch (Exception e) { return true; }  } public boolean func_184652_a(EntityPlayer player) { return false; } public static final ResourceLocation LOOT = LootTableList.func_186375_a(new ResourceLocation("thaumcraft", "pech")); public float mumble; int chargecount; protected ResourceLocation func_184647_J() { return LOOT; } protected void func_184610_a(boolean wasRecentlyHit, int lootingModifier, DamageSource source) { for (int a = 0; a < this.loot.size(); a++) { if (!((ItemStack)this.loot.get(a)).func_190926_b() && this.field_70170_p.field_73012_v.nextFloat() < 0.33F) func_70099_a(((ItemStack)this.loot.get(a)).func_77946_l(), 1.5F);  }  super.func_184610_a(wasRecentlyHit, lootingModifier, source); } @SideOnly(Side.CLIENT) public void func_70103_a(byte par1) { if (par1 == 16) { this.mumble = 3.1415927F; } else if (par1 == 17) { this.mumble = 6.2831855F; } else if (par1 == 18) { for (int i = 0; i < 5; i++) { double d0 = this.field_70146_Z.nextGaussian() * 0.02D; double d1 = this.field_70146_Z.nextGaussian() * 0.02D; double d2 = this.field_70146_Z.nextGaussian() * 0.02D; this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_HAPPY, this.field_70165_t + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, this.field_70163_u + 0.5D + (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, d0, d1, d2, new int[0]); }  }  if (par1 == 19) { for (int i = 0; i < 5; i++) { double d0 = this.field_70146_Z.nextGaussian() * 0.02D; double d1 = this.field_70146_Z.nextGaussian() * 0.02D; double d2 = this.field_70146_Z.nextGaussian() * 0.02D; this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, this.field_70163_u + 0.5D + (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N, d0, d1, d2, new int[0]); }  this.mumble = 6.2831855F; } else { super.func_70103_a(par1); }  } public void func_70642_aH() { if (!this.field_70170_p.field_72995_K) { if (this.field_70146_Z.nextInt(3) == 0) { List list = this.field_70170_p.func_72839_b(this, func_174813_aQ().func_72314_b(4.0D, 2.0D, 4.0D)); for (int i = 0; i < list.size(); i++) { Entity entity1 = (Entity)list.get(i); if (entity1 instanceof EntityPech) { this.field_70170_p.func_72960_a(this, (byte)17); func_184185_a(SoundsTC.pech_trade, func_70599_aP(), func_70647_i()); return; }  }  }  this.field_70170_p.func_72960_a(this, (byte)16); }  super.func_70642_aH(); } public int func_70627_aG() { return 120; } protected float func_70599_aP() { return 0.4F; } protected SoundEvent func_184639_G() { return SoundsTC.pech_idle; }
/*     */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundsTC.pech_hit; }
/*     */   protected SoundEvent func_184615_bR() { return SoundsTC.pech_death; }
/*     */   private void becomeAngryAt(Entity entity) { if (entity instanceof EntityPlayer && ((EntityPlayer)entity).func_184812_l_()) return;  if (getAnger() <= 0) { this.field_70170_p.func_72960_a(this, (byte)19); func_184185_a(SoundsTC.pech_charge, func_70599_aP(), func_70647_i()); }  func_70624_b((EntityLivingBase)entity); setAnger(400 + this.field_70146_Z.nextInt(400)); setTamed(false); setCombatTask(); }
/*     */   public int func_70658_aO() { int i = super.func_70658_aO() + 2; if (i > 20) i = 20;  return i; }
/*     */   public boolean func_70097_a(DamageSource damSource, float par2) { if (func_180431_b(damSource)) return false;  Entity entity = damSource.func_76346_g(); if (entity instanceof EntityPlayer) { List list = this.field_70170_p.func_72839_b(this, func_174813_aQ().func_72314_b(32.0D, 16.0D, 32.0D)); for (int i = 0; i < list.size(); i++) { Entity entity1 = (Entity)list.get(i); if (entity1 instanceof EntityPech) { EntityPech entitypech = (EntityPech)entity1; entitypech.becomeAngryAt(entity); }  }  becomeAngryAt(entity); }  return super.func_70097_a(damSource, par2); }
/* 673 */   public void func_70619_bc() { super.func_70619_bc();
/* 674 */     if (this.field_70173_aa % 40 == 0)
/*     */     {
/* 676 */       func_70691_i(1.0F);
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPickup(ItemStack entityItem) {
/* 683 */     if (entityItem == null) return false; 
/* 684 */     if (!isTamed() && valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(entityItem.func_77973_b())))) {
/* 685 */       return true;
/*     */     }
/* 687 */     for (int a = 0; a < this.loot.size(); a++) {
/* 688 */       if (!((ItemStack)this.loot.get(a)).func_190926_b() && ((ItemStack)this.loot.get(a)).func_190916_E() <= 0) this.loot.set(a, ItemStack.field_190927_a); 
/* 689 */       if (((ItemStack)this.loot.get(a)).func_190926_b()) return true; 
/* 690 */       if (InventoryUtils.areItemStacksEqualStrict(entityItem, (ItemStack)this.loot.get(a)) && entityItem
/* 691 */         .func_190916_E() + ((ItemStack)this.loot.get(a)).func_190916_E() <= ((ItemStack)this.loot.get(a)).func_77976_d()) return true; 
/*     */     } 
/* 693 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack pickupItem(ItemStack entityItem) {
/* 697 */     if (entityItem == null || entityItem.func_190926_b()) return ItemStack.field_190927_a;
/*     */     
/* 699 */     if (!isTamed() && isValued(entityItem)) {
/*     */       
/* 701 */       if (this.field_70146_Z.nextInt(10) < getValue(entityItem)) {
/* 702 */         setTamed(true);
/* 703 */         setCombatTask();
/* 704 */         this.field_70170_p.func_72960_a(this, (byte)18);
/*     */       } 
/*     */       
/* 707 */       entityItem.func_190918_g(1);
/*     */       
/* 709 */       if (entityItem.func_190916_E() <= 0) {
/* 710 */         return ItemStack.field_190927_a;
/*     */       }
/* 712 */       return entityItem;
/*     */     } 
/*     */ 
/*     */     
/* 716 */     for (int a = 0; a < this.loot.size(); a++) {
/* 717 */       if (this.loot.get(a) != null && ((ItemStack)this.loot.get(a)).func_190916_E() <= 0) this.loot.set(a, ItemStack.field_190927_a); 
/* 718 */       if (entityItem != null && !entityItem.func_190926_b() && entityItem.func_190916_E() > 0 && 
/* 719 */         !((ItemStack)this.loot.get(a)).func_190926_b() && ((ItemStack)this.loot.get(a)).func_190916_E() < ((ItemStack)this.loot.get(a)).func_77976_d() && 
/* 720 */         InventoryUtils.areItemStacksEqualStrict(entityItem, (ItemStack)this.loot.get(a))) {
/*     */         
/* 722 */         if (entityItem.func_190916_E() + ((ItemStack)this.loot.get(a)).func_190916_E() <= ((ItemStack)this.loot.get(a)).func_77976_d()) {
/* 723 */           ((ItemStack)this.loot.get(a)).func_190917_f(entityItem.func_190916_E());
/* 724 */           return ItemStack.field_190927_a;
/*     */         } 
/* 726 */         int sz = Math.min(entityItem.func_190916_E(), ((ItemStack)this.loot.get(a)).func_77976_d() - ((ItemStack)this.loot.get(a)).func_190916_E());
/* 727 */         ((ItemStack)this.loot.get(a)).func_190917_f(sz);
/* 728 */         entityItem.func_190918_g(sz);
/*     */       } 
/*     */       
/* 731 */       if (entityItem != null && !entityItem.func_190926_b() && entityItem.func_190916_E() <= 0) entityItem = ItemStack.field_190927_a;
/*     */     
/*     */     } 
/*     */     
/* 735 */     for (int a = 0; a < this.loot.size(); a++) {
/* 736 */       if (!((ItemStack)this.loot.get(a)).func_190926_b() && ((ItemStack)this.loot.get(a)).func_190916_E() <= 0) this.loot.set(a, ItemStack.field_190927_a); 
/* 737 */       if (entityItem != null && entityItem.func_190916_E() > 0 && ((ItemStack)this.loot.get(a)).func_190926_b()) {
/* 738 */         this.loot.set(a, entityItem.func_77946_l());
/* 739 */         return null;
/*     */       } 
/*     */     } 
/* 742 */     if (entityItem != null && !entityItem.func_190926_b() && entityItem.func_190916_E() <= 0) entityItem = ItemStack.field_190927_a; 
/* 743 */     return entityItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
/* 753 */     if (player.func_70093_af() || (player
/* 754 */       .func_184586_b(hand) != null && player.func_184586_b(hand).func_77973_b() instanceof net.minecraft.item.ItemNameTag)) return false; 
/* 755 */     if (isTamed()) {
/* 756 */       if (!this.field_70170_p.field_72995_K) {
/* 757 */         player.openGui(Thaumcraft.instance, 1, this.field_70170_p, func_145782_y(), 0, 0);
/*     */       }
/* 759 */       return true;
/*     */     } 
/* 761 */     return super.func_184645_a(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValued(ItemStack item) {
/* 766 */     if (item == null || item.func_190926_b()) return false; 
/* 767 */     boolean value = valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(item.func_77973_b())));
/* 768 */     if (!value) {
/* 769 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(item);
/* 770 */       if (al.getAmount(Aspect.DESIRE) > 1)
/* 771 */         value = true; 
/*     */     } 
/* 773 */     return value;
/*     */   }
/*     */   
/*     */   public int getValue(ItemStack item) {
/* 777 */     if (item == null || item.func_190926_b()) return 0; 
/* 778 */     int value = valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(item.func_77973_b()))) ? ((Integer)valuedItems.get(Integer.valueOf(Item.func_150891_b(item.func_77973_b())))).intValue() : 0;
/* 779 */     if (value == 0) {
/* 780 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(item);
/* 781 */       value = Math.min(32, al.getAmount(Aspect.DESIRE) / 2);
/*     */     } 
/* 783 */     return value;
/*     */   }
/*     */   
/* 786 */   public static HashMap<Integer, Integer> valuedItems = new HashMap();
/* 787 */   public static HashMap<Integer, ArrayList<List>> tradeInventory = new HashMap();
/*     */   
/*     */   public void func_184724_a(boolean swingingArms) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityPech.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */