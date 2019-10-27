/*     */ package thaumcraft.common.entities.monster.cult;
/*     */ 
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
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
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityCultistKnight
/*     */   extends EntityCultist
/*     */ {
/*  32 */   public EntityCultistKnight(World p_i1745_1_) { super(p_i1745_1_); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  40 */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/*  41 */     this.field_70714_bg.func_75776_a(3, new EntityAIAttackMelee(this, 1.0D, false));
/*  42 */     this.field_70714_bg.func_75776_a(4, new EntityAIRestrictOpenDoor(this));
/*  43 */     this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true));
/*  44 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.8D));
/*  45 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/*  46 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 8.0F));
/*  47 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*  48 */     this.field_70715_bh.func_75776_a(1, new AICultistHurtByTarget(this, true));
/*  49 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, true));
/*  50 */     this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, thaumcraft.common.entities.monster.EntityEldritchGuardian.class, true));
/*  51 */     this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTarget(this, net.minecraft.entity.monster.AbstractIllager.class, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/*  58 */     super.func_110147_ax();
/*  59 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setLoot(DifficultyInstance diff) {
/*  69 */     func_184201_a(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonPlateHelm));
/*  70 */     func_184201_a(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonPlateChest));
/*  71 */     func_184201_a(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonPlateLegs));
/*  72 */     func_184201_a(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
/*     */     
/*  74 */     if (this.field_70146_Z.nextFloat() < ((this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) ? 0.05F : 0.01F)) {
/*     */       
/*  76 */       int i = this.field_70146_Z.nextInt(5);
/*  77 */       if (i == 0) {
/*     */         
/*  79 */         func_184611_a(func_184600_cs(), new ItemStack(ItemsTC.voidSword));
/*  80 */         func_184201_a(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonRobeHelm));
/*     */       }
/*     */       else {
/*     */         
/*  84 */         func_184611_a(func_184600_cs(), new ItemStack(ItemsTC.thaumiumSword));
/*  85 */         if (this.field_70146_Z.nextBoolean()) {
/*  86 */           func_184201_a(EntityEquipmentSlot.HEAD, ItemStack.field_190927_a);
/*     */         }
/*     */       } 
/*     */     } else {
/*  90 */       func_184611_a(func_184600_cs(), new ItemStack(Items.field_151040_l));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_180483_b(DifficultyInstance diff) {
/*  97 */     float f = diff.func_180170_c();
/*  98 */     if (func_184614_ca() != null && !func_184614_ca().func_190926_b() && this.field_70146_Z.nextFloat() < 0.25F * f)
/*     */     {
/* 100 */       EnchantmentHelper.func_77504_a(this.field_70146_Z, func_184614_ca(), (int)(5.0F + f * this.field_70146_Z.nextInt(18)), false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\cult\EntityCultistKnight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */