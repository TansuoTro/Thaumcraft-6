/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.world.biomes.BiomeHandler;
/*     */ 
/*     */ public class EntityTaintacle
/*     */   extends EntityMob
/*     */   implements ITaintedMob
/*     */ {
/*  34 */   public float flailIntensity = 1.0F;
/*     */ 
/*     */   
/*     */   public EntityTaintacle(World par1World) {
/*  38 */     super(par1World);
/*  39 */     func_70105_a(0.8F, 3.0F);
/*  40 */     this.field_70728_aV = 8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  46 */     this.field_70714_bg.func_75776_a(1, new EntityAIAttackMelee(this, 1.0D, false));
/*  47 */     this.field_70714_bg.func_75776_a(2, new EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 6.0F));
/*  48 */     this.field_70714_bg.func_75776_a(3, new EntityAILookIdle(this));
/*  49 */     this.field_70715_bh.func_75776_a(0, new EntityAIHurtByTarget(this, false, new Class[0]));
/*  50 */     this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public boolean func_70686_a(Class clazz) { return !ITaintedMob.class.isAssignableFrom(clazz); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public boolean func_184191_r(Entity otherEntity) { return (otherEntity instanceof ITaintedMob || super.func_184191_r(otherEntity)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/*  70 */     boolean onTaint = (this.field_70170_p.func_180495_p(func_180425_c()).func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT || this.field_70170_p.func_180495_p(func_180425_c().func_177977_b()).func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT);
/*     */     
/*  72 */     return (onTaint && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/*  79 */     super.func_110147_ax();
/*  80 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*  81 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*  82 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70091_d(MoverType mt, double par1, double par3, double par5) {
/*  88 */     par1 = 0.0D;
/*  89 */     par5 = 0.0D;
/*  90 */     if (par3 > 0.0D) par3 = 0.0D; 
/*  91 */     super.func_70091_d(mt, par1, par3, par5);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  96 */     super.func_70071_h_();
/*     */     
/*  98 */     if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
/*     */ 
/*     */       
/* 101 */       boolean onTaint = (this.field_70170_p.func_180495_p(func_180425_c()).func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT || this.field_70170_p.func_180495_p(func_180425_c().func_177977_b()).func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT);
/* 102 */       if (!onTaint) func_70665_d(DamageSource.field_76366_f, 1.0F);
/*     */       
/* 104 */       if (!(this instanceof EntityTaintacleSmall) && this.field_70173_aa % 40 == 0 && 
/* 105 */         func_70638_az() != null && 
/* 106 */         func_70068_e(func_70638_az()) > 16.0D && 
/* 107 */         func_70068_e(func_70638_az()) < 256.0D && 
/* 108 */         func_70635_at().func_75522_a(func_70638_az()))
/*     */       {
/* 110 */         spawnTentacles(func_70638_az());
/*     */       }
/*     */     } 
/*     */     
/* 114 */     if (this.field_70170_p.field_72995_K) {
/* 115 */       if (this.flailIntensity > 1.0F) this.flailIntensity -= 0.01F;
/*     */       
/* 117 */       if (this.field_70173_aa < this.field_70131_O * 10.0F && this.field_70122_E) {
/* 118 */         FXDispatcher.INSTANCE.tentacleAriseFX(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void spawnTentacles(Entity entity) {
/* 124 */     if (this.field_70170_p.func_180494_b(entity.func_180425_c()) == BiomeHandler.ELDRITCH || this.field_70170_p
/* 125 */       .func_180495_p(entity.func_180425_c()).func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT || this.field_70170_p
/* 126 */       .func_180495_p(entity.func_180425_c().func_177977_b()).func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT) {
/* 127 */       EntityTaintacleSmall taintlet = new EntityTaintacleSmall(this.field_70170_p);
/* 128 */       taintlet.func_70012_b(entity.field_70165_t + this.field_70170_p.field_73012_v
/* 129 */           .nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), entity.field_70163_u, entity.field_70161_v + this.field_70170_p.field_73012_v
/*     */           
/* 131 */           .nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), 0.0F, 0.0F);
/* 132 */       this.field_70170_p.func_72838_d(taintlet);
/* 133 */       func_184185_a(SoundsTC.tentacle, func_70599_aP(), func_70647_i());
/* 134 */       if (this.field_70170_p.func_180494_b(entity.func_180425_c()) == BiomeHandler.ELDRITCH && this.field_70170_p
/* 135 */         .func_175623_d(entity.func_180425_c()) && BlockUtils.isAdjacentToSolidBlock(this.field_70170_p, entity.func_180425_c())) {
/* 136 */         this.field_70170_p.func_175656_a(entity.func_180425_c(), BlocksTC.taintFibre.func_176223_P());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void faceEntity(Entity par1Entity, float par2) {
/* 144 */     double d0 = par1Entity.field_70165_t - this.field_70165_t;
/* 145 */     double d1 = par1Entity.field_70161_v - this.field_70161_v;
/*     */ 
/*     */     
/* 148 */     float f2 = (float)(Math.atan2(d1, d0) * 180.0D / Math.PI) - 90.0F;
/* 149 */     this.field_70177_z = func_70663_b(this.field_70177_z, f2, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70663_b(float par1, float par2, float par3) {
/* 154 */     float f3 = MathHelper.func_76142_g(par2 - par1);
/*     */     
/* 156 */     if (f3 > par3)
/*     */     {
/* 158 */       f3 = par3;
/*     */     }
/*     */     
/* 161 */     if (f3 < -par3)
/*     */     {
/* 163 */       f3 = -par3;
/*     */     }
/*     */     
/* 166 */     return par1 + f3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public int func_70627_aG() { return 200; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   protected SoundEvent func_184639_G() { return SoundEvents.field_187540_ab; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   protected float func_70647_i() { return 1.3F - this.field_70131_O / 10.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   protected float func_70599_aP() { return this.field_70131_O / 8.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundsTC.tentacle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   protected SoundEvent func_184615_bR() { return SoundsTC.tentacle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   protected void func_70628_a(boolean flag, int i) { func_70099_a(ConfigItems.FLUX_CRYSTAL.func_77946_l(), this.field_70131_O / 2.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1) {
/* 224 */     if (par1 == 16) {
/*     */       
/* 226 */       this.flailIntensity = 3.0F;
/*     */     }
/*     */     else {
/*     */       
/* 230 */       super.func_70103_a(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70652_k(Entity p_70652_1_) {
/* 237 */     this.field_70170_p.func_72960_a(this, (byte)16);
/* 238 */     func_184185_a(SoundsTC.tentacle, func_70599_aP(), func_70647_i());
/* 239 */     return super.func_70652_k(p_70652_1_);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\tainted\EntityTaintacle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */