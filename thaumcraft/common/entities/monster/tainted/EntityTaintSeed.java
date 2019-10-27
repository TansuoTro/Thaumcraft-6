/*     */ package thaumcraft.common.entities.monster.tainted;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.world.taint.TaintHelper;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ import thaumcraft.common.world.biomes.BiomeHandler;
/*     */ 
/*     */ 
/*     */ public class EntityTaintSeed
/*     */   extends EntityMob
/*     */   implements ITaintedMob
/*     */ {
/*  43 */   public int boost = 0;
/*     */   boolean firstRun;
/*     */   public float attackAnim;
/*     */   
/*  47 */   public EntityTaintSeed(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     this.firstRun = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     this.attackAnim = 0.0F; func_70105_a(1.5F, 1.25F); this.field_70728_aV = 8; } protected int getArea() { return 1; } protected void func_184651_r() { this.field_70714_bg.func_75776_a(1, new EntityAIAttackMelee(this, 1.0D, false)); this.field_70715_bh.func_75776_a(0, new EntityAIHurtByTarget(this, false, new Class[0])); this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, net.minecraft.entity.player.EntityPlayer.class, true)); } public void func_70037_a(NBTTagCompound nbt) { super.func_70037_a(nbt); this.boost = nbt.func_74762_e("boost"); } public void func_70014_b(NBTTagCompound nbt) { super.func_70014_b(nbt); nbt.func_74768_a("boost", this.boost); }
/*     */   public boolean func_70652_k(Entity p_70652_1_) { this.field_70170_p.func_72960_a(this, (byte)16); func_184185_a(SoundsTC.tentacle, func_70599_aP(), func_70647_i()); return super.func_70652_k(p_70652_1_); }
/*     */   @SideOnly(Side.CLIENT) public void func_70103_a(byte par1) { if (par1 == 16) { this.attackAnim = 0.5F; } else { super.func_70103_a(par1); }  }
/* 212 */   protected void spawnTentacles(Entity entity) { if (this.field_70170_p.func_180494_b(entity.func_180425_c()) == BiomeHandler.ELDRITCH || this.field_70170_p
/* 213 */       .func_180495_p(entity.func_180425_c()).func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT || this.field_70170_p
/* 214 */       .func_180495_p(entity.func_180425_c().func_177977_b()).func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT) {
/* 215 */       EntityTaintacleSmall taintlet = new EntityTaintacleSmall(this.field_70170_p);
/* 216 */       taintlet.func_70012_b(entity.field_70165_t + this.field_70170_p.field_73012_v
/* 217 */           .nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), entity.field_70163_u, entity.field_70161_v + this.field_70170_p.field_73012_v
/*     */           
/* 219 */           .nextFloat() - this.field_70170_p.field_73012_v.nextFloat(), 0.0F, 0.0F);
/* 220 */       this.field_70170_p.func_72838_d(taintlet);
/* 221 */       func_184185_a(SoundsTC.tentacle, func_70599_aP(), func_70647_i());
/* 222 */       if (this.field_70170_p.func_180494_b(entity.func_180425_c()) == BiomeHandler.ELDRITCH && this.field_70170_p
/* 223 */         .func_175623_d(entity.func_180425_c()) && BlockUtils.isAdjacentToSolidBlock(this.field_70170_p, entity.func_180425_c()))
/* 224 */         this.field_70170_p.func_175656_a(entity.func_180425_c(), BlocksTC.taintFibre.func_176223_P()); 
/*     */     }  }
/*     */   
/*     */   public boolean func_70686_a(Class clazz) { return !ITaintedMob.class.isAssignableFrom(clazz); }
/*     */   public boolean func_184191_r(Entity otherEntity) { return (otherEntity instanceof ITaintedMob || super.func_184191_r(otherEntity)); }
/*     */   public boolean func_70601_bi() {
/*     */     return (this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && func_70058_J() && EntityUtils.getEntitiesInRange(func_130014_f_(), func_180425_c(), null, EntityTaintSeed.class, ModConfig.CONFIG_WORLD.taintSpreadArea * 0.8D).size() <= 0);
/*     */   }
/* 232 */   public int func_70627_aG() { return 200; } public boolean func_70058_J() { return (!this.field_70170_p.func_72953_d(func_174813_aQ()) && this.field_70170_p.func_72917_a(func_174813_aQ(), this)); }
/*     */   protected void func_110147_ax() { super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(75.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D); }
/* 237 */   protected SoundEvent func_184639_G() { return SoundEvents.field_187540_ab; }
/*     */   public void func_70645_a(DamageSource cause) {
/*     */     TaintHelper.removeTaintSeed(func_130014_f_(), func_180425_c());
/*     */     super.func_70645_a(cause);
/*     */   }
/* 242 */   protected float func_70647_i() { return 1.3F - this.field_70131_O / 10.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   protected float func_70599_aP() { return this.field_70131_O / 8.0F; }
/*     */   public void func_70071_h_() { super.func_70071_h_(); if (!this.field_70170_p.field_72995_K) { if (!this.firstRun || this.field_70173_aa % 1200 == 0) { TaintHelper.removeTaintSeed(func_130014_f_(), func_180425_c()); TaintHelper.addTaintSeed(func_130014_f_(), func_180425_c()); this.firstRun = true; }  if (func_70089_S()) { boolean tickFlag = (this.field_70173_aa % 20 == 0); if (this.boost > 0 || tickFlag) { float mod = (this.boost > 0) ? 1.0F : AuraHandler.getFluxSaturation(this.field_70170_p, func_180425_c()); if (this.boost > 0) this.boost--;  if (mod <= 0.0F) { func_70097_a(DamageSource.field_76366_f, 0.5F); AuraHelper.polluteAura(func_130014_f_(), func_180425_c(), 0.1F, false); } else { TaintHelper.spreadFibres(this.field_70170_p, func_180425_c().func_177982_a(MathHelper.func_76136_a(func_70681_au(), -getArea() * 3, getArea() * 3), MathHelper.func_76136_a(func_70681_au(), -getArea(), getArea()), MathHelper.func_76136_a(func_70681_au(), -getArea() * 3, getArea() * 3)), true); }  }  if (tickFlag) { if (func_70638_az() != null && func_70068_e(func_70638_az()) < (getArea() * 256) && func_70635_at().func_75522_a(func_70638_az()))
/*     */             spawnTentacles(func_70638_az());  List<EntityLivingBase> list = EntityUtils.getEntitiesInRange(func_130014_f_(), func_180425_c(), this, EntityLivingBase.class, (getArea() * 4)); for (EntityLivingBase elb : list)
/*     */             elb.func_70690_d(new PotionEffect(PotionFluxTaint.instance, 100, getArea() - 1, false, true));  }  }  } else { if (this.attackAnim > 0.0F)
/*     */         this.attackAnim *= 0.75F;  if (this.attackAnim < 0.001D)
/* 252 */         this.attackAnim = 0.0F;  float xx = 1.0F * MathHelper.func_76126_a(this.field_70173_aa * 0.05F - 0.5F) / 5.0F; float zz = 1.0F * MathHelper.func_76126_a(this.field_70173_aa * 0.06F - 0.5F) / 5.0F + this.field_70737_aN / 200.0F + this.attackAnim; if (this.field_70146_Z.nextFloat() < 0.033D) { FXDispatcher.INSTANCE.drawLightningFlash(((float)this.field_70165_t + xx), ((float)this.field_70163_u + this.field_70131_O + 0.25F), ((float)this.field_70161_v + zz), 0.7F, 0.1F, 0.9F, 0.5F, 1.5F + this.field_70146_Z.nextFloat()); } else { FXDispatcher.INSTANCE.drawTaintParticles((float)this.field_70165_t + xx, (float)this.field_70163_u + this.field_70131_O + 0.25F, (float)this.field_70161_v + zz, (float)this.field_70146_Z.nextGaussian() * 0.05F, 0.1F + 0.01F * this.field_70146_Z.nextFloat(), (float)this.field_70146_Z.nextGaussian() * 0.05F, 2.0F); }  }  } protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundsTC.tentacle; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   protected SoundEvent func_184615_bR() { return SoundsTC.tentacle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   protected void func_70628_a(boolean flag, int i) { func_70099_a(ConfigItems.FLUX_CRYSTAL.func_77946_l(), this.field_70131_O / 2.0F); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   public boolean func_70104_M() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public boolean func_70067_L() { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_191958_b(float strafe, float forward, float friction, float g) {}
/*     */ 
/*     */   
/*     */   public void func_70091_d(MoverType mt, double par1, double par3, double par5) {
/* 288 */     par1 = 0.0D;
/* 289 */     par5 = 0.0D;
/* 290 */     if (par3 > 0.0D) par3 = 0.0D; 
/* 291 */     super.func_70091_d(mt, par1, par3, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   protected int func_70682_h(int air) { return air; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public boolean func_70648_aU() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   protected boolean func_70692_ba() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\tainted\EntityTaintSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */