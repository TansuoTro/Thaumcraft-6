/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.monster.EntitySlime;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.ITaintedMob;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityThaumicSlime extends EntitySlime implements ITaintedMob {
/*     */   int launched;
/*     */   int spitCounter;
/*     */   
/*     */   public EntityThaumicSlime(World par1World) {
/*  25 */     super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  30 */     this.launched = 10;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     this.spitCounter = 100; int i = 1 << 1 + this.field_70146_Z.nextInt(3); func_70799_a(i, true); } public void shoot(double par1, double par3, double par5, float par7, float par8) { float var9 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5); par1 /= var9; par3 /= var9; par5 /= var9; par1 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8; par3 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8; par5 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8; par1 *= par7; par3 *= par7; par5 *= par7; this.field_70159_w = par1; this.field_70181_x = par3; this.field_70179_y = par5; float var10 = MathHelper.func_76133_a(par1 * par1 + par5 * par5); this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI); this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, var10) * 180.0D / Math.PI); } public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) { int i = this.field_70146_Z.nextInt(3); if (i < 2 && this.field_70146_Z.nextFloat() < 0.5F * p_180482_1_.func_180170_c()) i++;  int j = 1 << i; func_70799_a(j, true); return super.func_180482_a(p_180482_1_, p_180482_2_); } public EntityThaumicSlime(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) { super(par1World); this.launched = 10; this.spitCounter = 100; func_70799_a(1, true); this.field_70163_u = ((par2EntityLiving.func_174813_aQ()).field_72338_b + (par2EntityLiving.func_174813_aQ()).field_72337_e) / 2.0D; double var6 = par3EntityLiving.field_70165_t - par2EntityLiving.field_70165_t; double var8 = (par3EntityLiving.func_174813_aQ()).field_72338_b + (par3EntityLiving.field_70131_O / 3.0F) - this.field_70163_u; double var10 = par3EntityLiving.field_70161_v - par2EntityLiving.field_70161_v; double var12 = MathHelper.func_76133_a(var6 * var6 + var10 * var10); if (var12 >= 1.0E-7D) {
/*     */       float var14 = (float)(Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F; float var15 = (float)-(Math.atan2(var8, var12) * 180.0D / Math.PI); double var16 = var6 / var12; double var18 = var10 / var12; func_70012_b(par2EntityLiving.field_70165_t + var16, this.field_70163_u, par2EntityLiving.field_70161_v + var18, var14, var15);
/*     */       float var20 = (float)var12 * 0.2F;
/*     */       shoot(var6, var8 + var20, var10, 1.5F, 1.0F);
/*     */     }  }
/*     */   public void func_70799_a(int par1, boolean t) { super.func_70799_a(par1, t);
/* 186 */     this.field_70728_aV = par1 + 2; } protected EntityThaumicSlime createInstance() { return new EntityThaumicSlime(this.field_70170_p); } public void func_70014_b(NBTTagCompound par1NBTTagCompound) { super.func_70014_b(par1NBTTagCompound); } public void func_70037_a(NBTTagCompound par1NBTTagCompound) { super.func_70037_a(par1NBTTagCompound); } public void func_70071_h_() { int i = func_70809_q(); if (this.field_70122_E && !this.field_175452_bi) { this.field_175452_bi = true; float sa = this.field_70813_a; super.func_70071_h_(); this.field_70813_a = sa; if (this.field_70170_p.field_72995_K)
/*     */         for (int j = 0; j < i * 2; j++)
/*     */           FXDispatcher.INSTANCE.slimeJumpFX(this, i);   if (func_70807_r())
/*     */         func_184185_a(func_184710_cZ(), func_70599_aP(), ((func_70681_au().nextFloat() - func_70681_au().nextFloat()) * 0.2F + 1.0F) * 0.8F);  this.field_70813_a = -0.5F; this.field_175452_bi = this.field_70122_E; func_70808_l(); } else { super.func_70071_h_(); }  if (this.field_70170_p.field_72995_K) { if (this.launched > 0) { this.launched--; for (int j = 0; j < i * (this.launched + 1); j++)
/*     */           FXDispatcher.INSTANCE.slimeJumpFX(this, i);  }  float ff = func_70809_q(); func_70105_a(0.6F * ff, 0.6F * ff); func_70105_a(0.51000005F * ff, 0.51000005F * ff); } else if (!this.field_70128_L) { EntityPlayer entityplayer = this.field_70170_p.func_72890_a(this, 16.0D); if (entityplayer != null) { if (this.spitCounter > 0)
/*     */           this.spitCounter--;  func_70625_a(entityplayer, 10.0F, 20.0F); if (func_70032_d(entityplayer) > 4.0F && this.spitCounter <= 0 && func_70809_q() > 2) { this.spitCounter = 101; if (!this.field_70170_p.field_72995_K) { EntityThaumicSlime flyslime = new EntityThaumicSlime(this.field_70170_p, this, entityplayer); this.field_70170_p.func_72838_d(flyslime); }  func_184185_a(SoundsTC.gore, 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F); func_70799_a(func_70809_q() - 1, true); }
/*     */          }
/*     */        }
/*     */      }
/* 195 */   public void func_70106_y() { int i = func_70809_q();
/*     */     
/* 197 */     if (!this.field_70170_p.field_72995_K && i > 1 && func_110143_aJ() <= 0.0F)
/*     */     {
/* 199 */       for (int k = 0; k < i; k++) {
/*     */         
/* 201 */         float f = ((k % 2) - 0.5F) * i / 4.0F;
/* 202 */         float f1 = ((k / 2) - 0.5F) * i / 4.0F;
/* 203 */         EntityThaumicSlime entityslime = createInstance();
/* 204 */         entityslime.func_70799_a(1, true);
/* 205 */         entityslime.func_70012_b(this.field_70165_t + f, this.field_70163_u + 0.5D, this.field_70161_v + f1, this.field_70146_Z.nextFloat() * 360.0F, 0.0F);
/* 206 */         this.field_70170_p.func_72838_d(entityslime);
/*     */       } 
/*     */     }
/*     */     
/* 210 */     this.field_70128_L = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public boolean func_70601_bi() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   protected int func_70805_n() { return func_70809_q() + 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   protected boolean func_70800_m() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_175451_e(EntityLivingBase p_175451_1_) {
/* 235 */     int i = func_70809_q();
/* 236 */     if (this.launched > 0) i += 2; 
/* 237 */     if (func_70089_S() && func_70685_l(p_175451_1_) && func_70068_e(p_175451_1_) < 0.6D * i * 0.6D * i && p_175451_1_.func_70097_a(DamageSource.func_76358_a(this), func_70805_n())) {
/*     */       
/* 239 */       func_184185_a(SoundEvents.field_187870_fk, 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
/* 240 */       func_174815_a(this, p_175451_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   protected Item func_146068_u() { return (func_70809_q() > 1) ? ItemsTC.crystalEssence : Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean flag, int i) {
/* 252 */     if (func_70809_q() > 1)
/* 253 */       func_70099_a(ConfigItems.FLUX_CRYSTAL.func_77946_l(), this.field_70131_O / 2.0F); 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityThaumicSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */