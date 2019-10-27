/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaintFibre;
/*     */ 
/*     */ public class EntityTaintCrawler extends EntityMob implements ITaintedMob {
/*     */   BlockPos lastPos;
/*     */   
/*     */   protected void func_184651_r() {
/*     */     this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
/*     */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackMelee(this, 1.0D, false));
/*     */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
/*     */     this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 8.0F));
/*     */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*     */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
/*     */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, true));
/*     */   }
/*     */   
/*  43 */   public EntityTaintCrawler(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     this.lastPos = new BlockPos(0, 0, 0); func_70105_a(0.5F, 0.4F); this.field_70728_aV = 3; }
/*     */   public boolean func_70686_a(Class clazz) { return !ITaintedMob.class.isAssignableFrom(clazz); } public boolean func_184191_r(Entity otherEntity) { return (otherEntity instanceof ITaintedMob || super.func_184191_r(otherEntity)); } public float func_70047_e() { return 0.1F; } protected void func_110147_ax() { super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.275D);
/* 127 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D); } public void func_70071_h_() { if (!this.field_70170_p.field_72995_K && func_70089_S() && this.field_70173_aa % 40 == 0 && this.lastPos != func_180425_c()) {
/* 128 */       this.lastPos = func_180425_c();
/* 129 */       IBlockState bs = this.field_70170_p.func_180495_p(func_180425_c());
/* 130 */       Material bm = bs.func_185904_a();
/*     */       
/* 132 */       if (!bs.func_177230_c().isLeaves(bs, this.field_70170_p, func_180425_c()) && !bm.func_76224_d() && bm != ThaumcraftMaterials.MATERIAL_TAINT && (this.field_70170_p
/*     */         
/* 134 */         .func_175623_d(func_180425_c()) || bs.func_177230_c().func_176200_f(this.field_70170_p, func_180425_c()) || bs
/* 135 */         .func_177230_c() instanceof net.minecraft.block.BlockFlower || bs.func_177230_c() instanceof net.minecraftforge.common.IPlantable) && 
/* 136 */         BlockUtils.isAdjacentToSolidBlock(this.field_70170_p, func_180425_c()) && 
/* 137 */         !BlockTaintFibre.isOnlyAdjacentToTaint(this.field_70170_p, func_180425_c()))
/*     */       {
/* 139 */         this.field_70170_p.func_175656_a(func_180425_c(), BlocksTC.taintFibre.func_176223_P());
/*     */       }
/*     */     } 
/*     */     
/* 143 */     super.func_70071_h_(); }
/*     */    protected float func_70647_i() { return 0.7F; }
/*     */   protected SoundEvent func_184639_G() { return SoundEvents.field_187793_eY; }
/*     */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundEvents.field_187850_fa; }
/*     */   protected SoundEvent func_184615_bR() { return SoundEvents.field_187795_eZ; }
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) { func_184185_a(SoundEvents.field_187852_fb, 0.15F, 1.0F); }
/*     */   protected boolean func_70041_e_() { return false; }
/* 150 */   protected boolean func_70814_o() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public EnumCreatureAttribute func_70668_bt() { return EnumCreatureAttribute.ARTHROPOD; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i) {
/* 167 */     if (this.field_70170_p.field_73012_v.nextInt(8) == 0) {
/* 168 */       func_70099_a(ConfigItems.FLUX_CRYSTAL.func_77946_l(), this.field_70131_O / 2.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 174 */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) { return p_180482_2_; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70652_k(Entity victim) {
/* 180 */     if (super.func_70652_k(victim)) {
/*     */       
/* 182 */       if (victim instanceof EntityLivingBase) {
/*     */         
/* 184 */         byte b0 = 0;
/*     */         
/* 186 */         if (this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL) {
/*     */           
/* 188 */           b0 = 3;
/*     */         }
/* 190 */         else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) {
/*     */           
/* 192 */           b0 = 6;
/*     */         } 
/*     */         
/* 195 */         if (b0 > 0 && this.field_70146_Z.nextInt(b0 + 1) > 2)
/*     */         {
/* 197 */           ((EntityLivingBase)victim).func_70690_d(new PotionEffect(PotionFluxTaint.instance, b0 * 20, 0));
/*     */         }
/*     */       } 
/*     */       
/* 201 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 205 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\tainted\EntityTaintCrawler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */