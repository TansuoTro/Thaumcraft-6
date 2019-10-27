/*     */ package thaumcraft.common.entities.monster.cult;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityCultistPortalLesser extends EntityMob {
/*     */   public int func_70658_aO() { return 4; }
/*     */   
/*     */   private static final DataParameter<Boolean> ACTIVE = EntityDataManager.func_187226_a(EntityCultistPortalLesser.class, DataSerializers.field_187198_h);
/*     */   int stagecounter;
/*     */   public int activeCounter;
/*     */   public int pulse;
/*     */   
/*     */   protected void func_70088_a() {
/*     */     super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(ACTIVE, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean isActive() { return ((Boolean)func_184212_Q().func_187225_a(ACTIVE)).booleanValue(); }
/*     */   
/*     */   public void setActive(boolean active) { func_184212_Q().func_187227_b(ACTIVE, Boolean.valueOf(active)); }
/*     */   
/*  32 */   public EntityCultistPortalLesser(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     this.stagecounter = 100;
/* 143 */     this.activeCounter = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 257 */     this.pulse = 0; this.field_70178_ae = true; this.field_70728_aV = 10; func_70105_a(1.5F, 3.0F); }
/*     */   protected void func_110147_ax() { super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D); }
/*     */   protected boolean func_70692_ba() { return false; } public void func_70014_b(NBTTagCompound nbt) { super.func_70014_b(nbt);
/* 263 */     nbt.func_74757_a("active", isActive()); } @SideOnly(Side.CLIENT) public void func_70103_a(byte msg) { if (msg == 16) {
/*     */       
/* 265 */       this.pulse = 10;
/*     */     }
/*     */     else {
/*     */       
/* 269 */       super.func_70103_a(msg);
/*     */     }  }
/*     */    public void func_70037_a(NBTTagCompound nbt) {
/*     */     super.func_70037_a(nbt);
/*     */     setActive(nbt.func_74767_n("active"));
/*     */   } public boolean func_70067_L() { return true; } public boolean func_70104_M() { return false; }
/*     */   public void func_70091_d(MoverType mt, double par1, double par3, double par5) {}
/*     */   public void func_70636_d() {}
/*     */   public boolean func_70112_a(double par1) { return (par1 < 4096.0D); }
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b() { return 15728880; }
/*     */   public float func_70013_c() { return 1.0F; }
/*     */   public void func_70645_a(DamageSource p_70645_1_) {
/* 282 */     if (!this.field_70170_p.field_72995_K) {
/* 283 */       this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5F, false, false);
/*     */     }
/*     */     
/* 286 */     super.func_70645_a(p_70645_1_);
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/*     */     super.func_70071_h_();
/*     */     if (isActive())
/*     */       this.activeCounter++; 
/*     */     if (!this.field_70170_p.field_72995_K)
/*     */       if (!isActive()) {
/*     */         if (this.field_70173_aa % 10 == 0) {
/*     */           EntityPlayer p = this.field_70170_p.func_72890_a(this, 32.0D);
/*     */           if (p != null) {
/*     */             setActive(true);
/*     */             func_184185_a(SoundsTC.craftstart, 1.0F, 1.0F);
/*     */           } 
/*     */         } 
/*     */       } else if (this.stagecounter-- <= 0) {
/*     */         EntityPlayer p = this.field_70170_p.func_72890_a(this, 32.0D);
/*     */         if (p != null && func_70685_l(p)) {
/*     */           int count = (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) ? 6 : ((this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL) ? 4 : 2);
/*     */           try {
/*     */             List l = this.field_70170_p.func_72872_a(EntityCultist.class, func_174813_aQ().func_72314_b(32.0D, 32.0D, 32.0D));
/*     */             if (l != null)
/*     */               count -= l.size(); 
/*     */           } catch (Exception exception) {}
/*     */           if (count > 0) {
/*     */             this.field_70170_p.func_72960_a(this, (byte)16);
/*     */             spawnMinions();
/*     */           } 
/*     */         } 
/*     */         this.stagecounter = 50 + this.field_70146_Z.nextInt(50);
/*     */       }  
/*     */     if (this.pulse > 0)
/*     */       this.pulse--; 
/*     */   }
/*     */   
/*     */   int getTiming() {
/*     */     List<Entity> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityCultist.class, 32.0D);
/*     */     return l.size() * 20;
/*     */   }
/*     */   
/*     */   void spawnMinions() {
/*     */     EntityCultist cultist = null;
/*     */     if (this.field_70146_Z.nextFloat() > 0.33D) {
/*     */       cultist = new EntityCultistKnight(this.field_70170_p);
/*     */     } else {
/*     */       cultist = new EntityCultistCleric(this.field_70170_p);
/*     */     } 
/*     */     cultist.func_70107_b(this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25D, this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat());
/*     */     cultist.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(cultist.func_180425_c())), (IEntityLivingData)null);
/*     */     this.field_70170_p.func_72838_d(cultist);
/*     */     cultist.func_70656_aK();
/*     */     cultist.func_184185_a(SoundsTC.wandfail, 1.0F, 1.0F);
/*     */     func_70097_a(DamageSource.field_76380_i, (5 + this.field_70146_Z.nextInt(5)));
/*     */   }
/*     */   
/*     */   protected boolean func_70814_o() { return true; }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer p) {
/*     */     if (func_70068_e(p) < 3.0D && p.func_70097_a(DamageSource.func_76354_b(this, this), 4.0F))
/*     */       func_184185_a(SoundsTC.zap, 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F + 1.0F); 
/*     */   }
/*     */   
/*     */   protected float func_70599_aP() { return 0.75F; }
/*     */   
/*     */   public int func_70627_aG() { return 540; }
/*     */   
/*     */   protected SoundEvent func_184639_G() { return SoundsTC.monolith; }
/*     */   
/*     */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundsTC.zap; }
/*     */   
/*     */   protected SoundEvent func_184615_bR() { return SoundsTC.shock; }
/*     */   
/*     */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */   
/*     */   protected void func_70628_a(boolean flag, int fortune) {}
/*     */   
/*     */   public void func_70690_d(PotionEffect p_70690_1_) {}
/*     */   
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\cult\EntityCultistPortalLesser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */