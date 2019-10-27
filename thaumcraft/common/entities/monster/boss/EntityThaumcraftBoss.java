/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EntityThaumcraftBoss extends EntityMob {
/*     */   protected final BossInfoServer bossInfo;
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/*     */     super.func_70037_a(nbt);
/*     */     if (nbt.func_74764_b("HomeD"))
/*     */       func_175449_a(new BlockPos(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ")), nbt.func_74762_e("HomeD")); 
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/*     */     super.func_70014_b(nbt);
/*     */     if (func_180486_cf() != null && func_110174_bM() > 0.0F) {
/*     */       nbt.func_74768_a("HomeD", (int)func_110174_bM());
/*     */       nbt.func_74768_a("HomeX", func_180486_cf().func_177958_n());
/*     */       nbt.func_74768_a("HomeY", func_180486_cf().func_177956_o());
/*     */       nbt.func_74768_a("HomeZ", func_180486_cf().func_177952_p());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_110147_ax() {
/*     */     super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.95D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
/*     */   }
/*     */   
/*     */   private static final DataParameter<Integer> AGGRO = EntityDataManager.func_187226_a(EntityThaumcraftBoss.class, DataSerializers.field_187192_b);
/*     */   
/*  37 */   public EntityThaumcraftBoss(World world) { super(world);
/*     */ 
/*     */ 
/*     */     
/*  41 */     this
/*  42 */       .bossInfo = (BossInfoServer)(new BossInfoServer(func_145748_c_(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).func_186741_a(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     this.aggro = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     this.spawnTimer = 0;
/*     */     this.field_70728_aV = 50; }
/*     */   HashMap<Integer, Integer> aggro; int spawnTimer; protected void func_70088_a() { super.func_70088_a();
/* 136 */     func_184212_Q().func_187214_a(AGGRO, Integer.valueOf(0)); } public int getSpawnTimer() { return this.spawnTimer; } protected void func_70619_bc() { if (getSpawnTimer() == 0)
/*     */       super.func_70619_bc();  if (func_70638_az() != null && (func_70638_az()).field_70128_L)
/*     */       func_70624_b(null);  this.bossInfo.func_186735_a(func_110143_aJ() / func_110138_aP()); }
/*     */   public void func_184203_c(EntityPlayerMP player) { super.func_184203_c(player); this.bossInfo.func_186761_b(player); }
/*     */   public void func_184178_b(EntityPlayerMP player) { super.func_184178_b(player); this.bossInfo.func_186760_a(player); }
/* 141 */   public void func_70071_h_() { super.func_70071_h_();
/* 142 */     if (getSpawnTimer() > 0) this.spawnTimer--;
/*     */     
/* 144 */     if (getAnger() > 0) setAnger(getAnger() - 1); 
/* 145 */     if (this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(15) == 0 && getAnger() > 0) {
/* 146 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 147 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 148 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 149 */       this.field_70170_p.func_175688_a(EnumParticleTypes.VILLAGER_ANGRY, this.field_70165_t + (this.field_70146_Z
/* 150 */           .nextFloat() * this.field_70130_N) - this.field_70130_N / 2.0D, 
/* 151 */           (func_174813_aQ()).field_72338_b + this.field_70131_O + this.field_70146_Z.nextFloat() * 0.5D, this.field_70161_v + (this.field_70146_Z
/* 152 */           .nextFloat() * this.field_70130_N) - this.field_70130_N / 2.0D, d0, d1, d2, new int[0]);
/*     */     } 
/*     */ 
/*     */     
/* 156 */     if (!this.field_70170_p.field_72995_K) {
/* 157 */       if (this.field_70173_aa % 30 == 0)
/*     */       {
/* 159 */         func_70691_i(1.0F);
/*     */       }
/*     */       
/* 162 */       if (func_70638_az() != null && this.field_70173_aa % 20 == 0) {
/* 163 */         ArrayList<Integer> dl = new ArrayList<Integer>();
/* 164 */         int players = 0;
/* 165 */         int hei = func_70638_az().func_145782_y();
/* 166 */         int ad = this.aggro.containsKey(Integer.valueOf(hei)) ? ((Integer)this.aggro.get(Integer.valueOf(hei))).intValue() : 0;
/* 167 */         int ld = ad;
/* 168 */         Entity newTarget = null;
/* 169 */         for (Integer ei : this.aggro.keySet()) {
/* 170 */           int ca = ((Integer)this.aggro.get(ei)).intValue();
/* 171 */           if (ca > ad + 25 && ca > ad * 1.1D && ca > ld) {
/* 172 */             newTarget = this.field_70170_p.func_73045_a(hei);
/* 173 */             if (newTarget == null || newTarget.field_70128_L || func_70068_e(newTarget) > 16384.0D) {
/* 174 */               dl.add(ei); continue;
/*     */             } 
/* 176 */             hei = ei.intValue();
/* 177 */             ld = ei.intValue();
/* 178 */             if (newTarget instanceof EntityPlayer) {
/* 179 */               players++;
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 185 */         for (Integer ei : dl) this.aggro.remove(ei);
/*     */         
/* 187 */         if (newTarget != null && hei != func_70638_az().func_145782_y()) {
/* 188 */           func_70624_b((EntityLivingBase)newTarget);
/*     */         }
/*     */         
/* 191 */         float om = func_110138_aP();
/*     */         
/* 193 */         IAttributeInstance iattributeinstance = func_110148_a(SharedMonsterAttributes.field_111267_a);
/* 194 */         IAttributeInstance iattributeinstance2 = func_110148_a(SharedMonsterAttributes.field_111264_e);
/*     */         
/* 196 */         for (int a = 0; a < 5; a++) {
/* 197 */           iattributeinstance2.func_111124_b(EntityUtils.DMGBUFF[a]);
/* 198 */           iattributeinstance.func_111124_b(EntityUtils.HPBUFF[a]);
/*     */         } 
/*     */         
/* 201 */         for (int a = 0; a < Math.min(5, players - 1); a++) {
/* 202 */           iattributeinstance.func_111121_a(EntityUtils.HPBUFF[a]);
/* 203 */           iattributeinstance2.func_111121_a(EntityUtils.DMGBUFF[a]);
/*     */         } 
/*     */         
/* 206 */         double mm = (func_110138_aP() / om);
/* 207 */         func_70606_j((float)(func_110143_aJ() * mm));
/*     */       } 
/*     */     }  }
/*     */    public boolean func_184222_aU() { return false; } public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) {
/*     */     func_175449_a(func_180425_c(), 24);
/*     */     generateName();
/*     */     this.bossInfo.func_186739_a(func_145748_c_());
/*     */     return data;
/*     */   } public int getAnger() { return ((Integer)func_184212_Q().func_187225_a(AGGRO)).intValue(); } public void setAnger(int par1) { func_184212_Q().func_187227_b(AGGRO, Integer.valueOf(par1)); }
/* 216 */   public boolean func_180431_b(DamageSource ds) { return (super.func_180431_b(ds) || getSpawnTimer() > 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public boolean func_70648_aU() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public boolean func_70104_M() { return (super.func_70104_M() && !func_180431_b(DamageSource.field_76366_f)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   protected int func_70682_h(int air) { return air; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70110_aj() {}
/*     */ 
/*     */ 
/*     */   
/* 240 */   public boolean func_98052_bS() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_180483_b(DifficultyInstance diff) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   protected boolean func_70692_ba() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public boolean func_184191_r(Entity el) { return el instanceof thaumcraft.api.entities.IEldritchMob; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean flag, int fortune) {
/* 262 */     EntityUtils.entityDropSpecialItem(this, new ItemStack(ItemsTC.primordialPearl), this.field_70131_O / 2.0F);
/* 263 */     func_70099_a(new ItemStack(ItemsTC.lootBag, 1, 2), 1.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float damage) {
/* 270 */     if (!this.field_70170_p.field_72995_K) {
/* 271 */       if (source.func_76346_g() != null && source.func_76346_g() instanceof EntityLivingBase) {
/* 272 */         int target = source.func_76346_g().func_145782_y();
/* 273 */         int ad = (int)damage;
/* 274 */         if (this.aggro.containsKey(Integer.valueOf(target))) {
/* 275 */           ad += ((Integer)this.aggro.get(Integer.valueOf(target))).intValue();
/*     */         }
/* 277 */         this.aggro.put(Integer.valueOf(target), Integer.valueOf(ad));
/*     */       } 
/*     */       
/* 280 */       if (damage > 35.0F) {
/* 281 */         if (getAnger() == 0) {
/*     */           try {
/*     */             try {
/* 284 */               func_70690_d(new PotionEffect(MobEffects.field_76428_l, 200, (int)(damage / 15.0F)));
/* 285 */               func_70690_d(new PotionEffect(MobEffects.field_76420_g, 200, (int)(damage / 10.0F)));
/* 286 */               func_70690_d(new PotionEffect(MobEffects.field_76422_e, 200, (int)(damage / 40.0F)));
/* 287 */             } catch (Exception exception) {}
/*     */             
/* 289 */             setAnger(200);
/* 290 */           } catch (Exception exception) {}
/* 291 */           if (source.func_76346_g() != null && source.func_76346_g() instanceof EntityPlayer) {
/* 292 */             ((EntityPlayer)source.func_76346_g()).func_146105_b(new TextComponentTranslation(func_70005_c_() + " " + I18n.func_74838_a("tc.boss.enrage"), new Object[0]), true);
/*     */           }
/*     */         } 
/* 295 */         damage = 35.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 299 */     return super.func_70097_a(source, damage);
/*     */   }
/*     */   
/*     */   public void generateName() {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\boss\EntityThaumcraftBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */