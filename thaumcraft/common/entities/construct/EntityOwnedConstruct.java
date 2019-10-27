/*     */ package thaumcraft.common.entities.construct;
/*     */ import com.google.common.base.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityOwnedConstruct extends EntityCreature implements IEntityOwnable {
/*     */   protected static final DataParameter<Byte> TAMED = EntityDataManager.func_187226_a(EntityOwnedConstruct.class, DataSerializers.field_187191_a);
/*     */   protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.func_187226_a(EntityOwnedConstruct.class, DataSerializers.field_187203_m);
/*     */   boolean validSpawn;
/*     */   
/*     */   protected void func_70088_a() {
/*     */     super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(TAMED, Byte.valueOf((byte)0));
/*     */     func_184212_Q().func_187214_a(OWNER_UNIQUE_ID, Optional.absent());
/*     */   }
/*     */   
/*     */   public boolean isOwned() { return ((((Byte)func_184212_Q().func_187225_a(TAMED)).byteValue() & 0x4) != 0); }
/*     */   
/*     */   public void setOwned(boolean tamed) {
/*     */     byte b0 = ((Byte)func_184212_Q().func_187225_a(TAMED)).byteValue();
/*     */     if (tamed) {
/*     */       func_184212_Q().func_187227_b(TAMED, Byte.valueOf((byte)(b0 | 0x4)));
/*     */     } else {
/*     */       func_184212_Q().func_187227_b(TAMED, Byte.valueOf((byte)(b0 & 0xFFFFFFFB)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public EntityOwnedConstruct(World worldIn) {
/*  33 */     super(worldIn);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     this.validSpawn = false;
/*     */   } public UUID func_184753_b() { return (UUID)((Optional)func_184212_Q().func_187225_a(OWNER_UNIQUE_ID)).orNull(); }
/*     */   public void setOwnerId(UUID p_184754_1_) { func_184212_Q().func_187227_b(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_)); }
/* 132 */   public void setValidSpawn() { this.validSpawn = true; }
/*     */   protected int func_70682_h(int air) { return air; }
/*     */   public boolean func_70648_aU() { return true; }
/*     */   protected SoundEvent func_184639_G() { return SoundsTC.clack; }
/*     */   protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundsTC.clack; }
/*     */   
/* 138 */   public void func_70014_b(NBTTagCompound tagCompound) { super.func_70014_b(tagCompound);
/*     */     
/* 140 */     tagCompound.func_74757_a("v", this.validSpawn);
/*     */     
/* 142 */     if (func_184753_b() == null) {
/*     */       
/* 144 */       tagCompound.func_74778_a("OwnerUUID", "");
/*     */     }
/*     */     else {
/*     */       
/* 148 */       tagCompound.func_74778_a("OwnerUUID", func_184753_b().toString());
/*     */     }  } protected SoundEvent func_184615_bR() { return SoundsTC.tool; } public int func_70627_aG() { return 240; } protected boolean func_70692_ba() { return false; } public void func_70071_h_() { super.func_70071_h_();
/*     */     if (func_70638_az() != null && func_184191_r(func_70638_az()))
/*     */       func_70624_b(null); 
/*     */     if (!this.field_70170_p.field_72995_K && !this.validSpawn)
/*     */       func_70106_y();  }
/*     */   public void func_70037_a(NBTTagCompound tagCompound) {
/* 155 */     super.func_70037_a(tagCompound);
/*     */     
/* 157 */     this.validSpawn = tagCompound.func_74767_n("v");
/*     */     
/* 159 */     String s = "";
/*     */     
/* 161 */     if (tagCompound.func_150297_b("OwnerUUID", 8)) {
/*     */       
/* 163 */       s = tagCompound.func_74779_i("OwnerUUID");
/*     */     }
/*     */     else {
/*     */       
/* 167 */       String s1 = tagCompound.func_74779_i("Owner");
/* 168 */       s = PreYggdrasilConverter.func_187473_a(func_184102_h(), s1);
/*     */     } 
/*     */     
/* 171 */     if (!s.isEmpty()) {
/*     */       
/*     */       try {
/*     */         
/* 175 */         setOwnerId(UUID.fromString(s));
/* 176 */         setOwned(true);
/*     */       }
/* 178 */       catch (Throwable var4) {
/*     */         
/* 180 */         setOwned(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase getOwnerEntity() {
/*     */     try {
/* 190 */       UUID uuid = func_184753_b();
/* 191 */       return (uuid == null) ? null : this.field_70170_p.func_152378_a(uuid);
/*     */     }
/* 193 */     catch (IllegalArgumentException var2) {
/*     */       
/* 195 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 201 */   public boolean isOwner(EntityLivingBase entityIn) { return (entityIn == getOwnerEntity()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Team func_96124_cp() {
/* 207 */     if (isOwned()) {
/*     */       
/* 209 */       EntityLivingBase entitylivingbase = getOwnerEntity();
/*     */       
/* 211 */       if (entitylivingbase != null)
/*     */       {
/* 213 */         return entitylivingbase.func_96124_cp();
/*     */       }
/*     */     } 
/*     */     
/* 217 */     return super.func_96124_cp();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_184191_r(Entity otherEntity) {
/* 223 */     if (isOwned()) {
/*     */       
/* 225 */       EntityLivingBase entitylivingbase1 = getOwnerEntity();
/*     */       
/* 227 */       if (otherEntity == entitylivingbase1)
/*     */       {
/* 229 */         return true;
/*     */       }
/*     */       
/* 232 */       if (entitylivingbase1 != null)
/*     */       {
/* 234 */         return entitylivingbase1.func_184191_r(otherEntity);
/*     */       }
/*     */     } 
/*     */     
/* 238 */     return super.func_184191_r(otherEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource cause) {
/* 244 */     if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_82736_K().func_82766_b("showDeathMessages") && func_145818_k_() && getOwnerEntity() instanceof EntityPlayerMP) {
/* 245 */       ((EntityPlayerMP)getOwnerEntity()).func_145747_a(func_110142_aN().func_151521_b());
/*     */     }
/*     */     
/* 248 */     super.func_70645_a(cause);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   public Entity func_70902_q() { return getOwnerEntity(); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
/* 259 */     if (this.field_70128_L) return false; 
/* 260 */     if (player.func_70093_af() || (player.func_184614_ca() != null && player.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemNameTag)) return false; 
/* 261 */     if (!this.field_70170_p.field_72995_K && !isOwner(player)) {
/* 262 */       player.func_146105_b(new TextComponentTranslation("§5§o" + I18n.func_74838_a("tc.notowned"), new Object[0]), true);
/* 263 */       return true;
/*     */     } 
/*     */     
/* 266 */     return super.func_184645_a(player, hand);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\construct\EntityOwnedConstruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */