/*     */ package thaumcraft.common.entities.construct;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackRanged;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAITarget;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityTurretCrossbowAdvanced
/*     */   extends EntityTurretCrossbow
/*     */ {
/*     */   public EntityTurretCrossbowAdvanced(World worldIn) {
/*  50 */     super(worldIn);
/*  51 */     func_70105_a(0.95F, 1.5F);
/*     */     
/*  53 */     this.field_70138_W = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  59 */     this.field_70714_bg.field_75782_a.clear();
/*  60 */     this.field_70715_bh.field_75782_a.clear();
/*     */     
/*  62 */     this.field_70714_bg.func_75776_a(1, new EntityAIAttackRanged(this, 0.0D, 20, 40, 24.0F));
/*  63 */     this.field_70714_bg.func_75776_a(2, new EntityAIWatchTarget(this));
/*  64 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
/*  65 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestValidTarget(this, EntityLivingBase.class, 5, true, false, (Predicate)null));
/*  66 */     setTargetMob(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public float func_70047_e() { return 1.0F; }
/*     */ 
/*     */   
/*     */   public EntityTurretCrossbowAdvanced(World worldIn, BlockPos pos) {
/*  76 */     this(worldIn);
/*  77 */     func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/*     */   }
/*     */   
/*  80 */   private static final DataParameter<Byte> FLAGS = EntityDataManager.func_187226_a(EntityTurretCrossbowAdvanced.class, DataSerializers.field_187191_a);
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  85 */     super.func_70088_a();
/*  86 */     func_184212_Q().func_187214_a(FLAGS, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70686_a(Class clazz) {
/*  92 */     if (net.minecraft.entity.passive.IAnimals.class.isAssignableFrom(clazz) && !net.minecraft.entity.monster.IMob.class.isAssignableFrom(clazz) && getTargetAnimal()) return true; 
/*  93 */     if (net.minecraft.entity.monster.IMob.class.isAssignableFrom(clazz) && getTargetMob()) return true; 
/*  94 */     if (EntityPlayer.class.isAssignableFrom(clazz) && getTargetPlayer()) {
/*  95 */       if (!this.field_70170_p.field_72995_K && !FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W() && !getTargetFriendly()) {
/*  96 */         setTargetPlayer(false);
/*  97 */         return false;
/*     */       } 
/*  99 */       return true;
/*     */     } 
/*     */     
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public boolean getTargetAnimal() { return Utils.getBit(((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue(), 0); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetAnimal(boolean par1) {
/* 112 */     byte var2 = ((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue();
/* 113 */     if (par1) { func_184212_Q().func_187227_b(FLAGS, Byte.valueOf((byte)Utils.setBit(var2, 0))); }
/* 114 */     else { func_184212_Q().func_187227_b(FLAGS, Byte.valueOf((byte)Utils.clearBit(var2, 0))); }
/* 115 */      func_70624_b(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public boolean getTargetMob() { return Utils.getBit(((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue(), 1); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetMob(boolean par1) {
/* 125 */     byte var2 = ((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue();
/* 126 */     if (par1) { func_184212_Q().func_187227_b(FLAGS, Byte.valueOf((byte)Utils.setBit(var2, 1))); }
/* 127 */     else { func_184212_Q().func_187227_b(FLAGS, Byte.valueOf((byte)Utils.clearBit(var2, 1))); }
/* 128 */      func_70624_b(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public boolean getTargetPlayer() { return Utils.getBit(((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue(), 2); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetPlayer(boolean par1) {
/* 138 */     byte var2 = ((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue();
/* 139 */     if (par1) { func_184212_Q().func_187227_b(FLAGS, Byte.valueOf((byte)Utils.setBit(var2, 2))); }
/* 140 */     else { func_184212_Q().func_187227_b(FLAGS, Byte.valueOf((byte)Utils.clearBit(var2, 2))); }
/* 141 */      func_70624_b(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 146 */   public boolean getTargetFriendly() { return Utils.getBit(((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue(), 3); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetFriendly(boolean par1) {
/* 151 */     byte var2 = ((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue();
/* 152 */     if (par1) { func_184212_Q().func_187227_b(FLAGS, Byte.valueOf((byte)Utils.setBit(var2, 3))); }
/* 153 */     else { func_184212_Q().func_187227_b(FLAGS, Byte.valueOf((byte)Utils.clearBit(var2, 3))); }
/* 154 */      func_70624_b(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Team func_96124_cp() {
/* 161 */     if (isOwned()) {
/*     */       
/* 163 */       EntityLivingBase entitylivingbase = getOwnerEntity();
/*     */       
/* 165 */       if (entitylivingbase != null)
/*     */       {
/* 167 */         return entitylivingbase.func_96124_cp();
/*     */       }
/*     */     } 
/*     */     
/* 171 */     return super.func_96124_cp();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/* 178 */     super.func_110147_ax();
/* 179 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 184 */   public int func_70658_aO() { return 8; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 191 */     super.func_70071_h_();
/* 192 */     if (!this.field_70170_p.field_72995_K && !FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W() && 
/* 193 */       func_70638_az() != null && func_70638_az() instanceof EntityPlayer && 
/* 194 */       func_70638_az() != getOwnerEntity()) func_70624_b(null);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 202 */     super.func_70037_a(nbt);
/* 203 */     func_184212_Q().func_187227_b(FLAGS, Byte.valueOf(nbt.func_74771_c("targets")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 209 */     super.func_70014_b(nbt);
/* 210 */     nbt.func_74774_a("targets", ((Byte)func_184212_Q().func_187225_a(FLAGS)).byteValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public void func_70653_a(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) { super.func_70653_a(p_70653_1_, p_70653_2_, p_70653_3_ / 10.0D, p_70653_5_ / 10.0D); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
/* 221 */     if (!this.field_70170_p.field_72995_K && isOwner(player) && !this.field_70128_L) {
/* 222 */       if (player.func_70093_af()) {
/* 223 */         func_184185_a(SoundsTC.zap, 1.0F, 1.0F);
/* 224 */         dropAmmo();
/* 225 */         func_70099_a(new ItemStack(ItemsTC.turretPlacer, 1, 1), 0.5F);
/* 226 */         func_70106_y();
/* 227 */         player.func_184609_a(hand);
/*     */       } else {
/* 229 */         player.openGui(Thaumcraft.instance, 17, this.field_70170_p, func_145782_y(), 0, 0);
/*     */       } 
/* 231 */       return true;
/*     */     } 
/*     */     
/* 234 */     return super.func_184645_a(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 239 */   public void func_70091_d(MoverType t, double x, double y, double z) { super.func_70091_d(t, x / 15.0D, y, z / 15.0D); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
/* 244 */     float b = p_70628_2_ * 0.15F;
/* 245 */     if (this.field_70146_Z.nextFloat() < 0.2F + b) func_70099_a(new ItemStack(ItemsTC.mind, 1, 1), 0.5F); 
/* 246 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(ItemsTC.mechanismSimple), 0.5F); 
/* 247 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plankGreatwood), 0.5F); 
/* 248 */     if (this.field_70146_Z.nextFloat() < 0.5F + b) func_70099_a(new ItemStack(BlocksTC.plankGreatwood), 0.5F); 
/* 249 */     if (this.field_70146_Z.nextFloat() < 0.3F + b) func_70099_a(new ItemStack(ItemsTC.plate, 1, 0), 0.5F); 
/* 250 */     if (this.field_70146_Z.nextFloat() < 0.4F + b) func_70099_a(new ItemStack(ItemsTC.plate, 1, 1), 0.5F); 
/* 251 */     if (this.field_70146_Z.nextFloat() < 0.4F + b) func_70099_a(new ItemStack(ItemsTC.plate, 1, 1), 0.5F);
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   protected class EntityAIWatchTarget
/*     */     extends EntityAIBase
/*     */   {
/*     */     protected EntityLiving theWatcher;
/*     */     
/*     */     protected Entity closestEntity;
/*     */     private int lookTime;
/*     */     
/*     */     public EntityAIWatchTarget(EntityLiving p_i1631_1_) {
/* 265 */       this.theWatcher = p_i1631_1_;
/* 266 */       func_75248_a(2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_75250_a() {
/* 272 */       if (this.theWatcher.func_70638_az() != null)
/*     */       {
/* 274 */         this.closestEntity = this.theWatcher.func_70638_az();
/*     */       }
/*     */       
/* 277 */       return (this.closestEntity != null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_75253_b() {
/* 283 */       float d = (float)getTargetDistance();
/* 284 */       return !this.closestEntity.func_70089_S() ? false : ((this.theWatcher.func_70068_e(this.closestEntity) > (d * d)) ? false : ((this.lookTime > 0)));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     public void func_75249_e() { this.lookTime = 40 + this.theWatcher.func_70681_au().nextInt(40); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     public void func_75251_c() { this.closestEntity = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void func_75246_d() {
/* 302 */       this.theWatcher.func_70671_ap().func_75650_a(this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + this.closestEntity.func_70047_e(), this.closestEntity.field_70161_v, 10.0F, this.theWatcher.func_70646_bf());
/* 303 */       this.lookTime--;
/*     */     }
/*     */ 
/*     */     
/*     */     protected double getTargetDistance() {
/* 308 */       IAttributeInstance iattributeinstance = this.theWatcher.func_110148_a(SharedMonsterAttributes.field_111265_b);
/* 309 */       return (iattributeinstance == null) ? 16.0D : iattributeinstance.func_111126_e();
/*     */     }
/*     */   }
/*     */   
/*     */   protected class EntityAINearestValidTarget
/*     */     extends EntityAITarget
/*     */   {
/*     */     protected final Class targetClass;
/*     */     private final int targetChance;
/*     */     protected final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
/*     */     protected Predicate targetEntitySelector;
/*     */     protected EntityLivingBase targetEntity;
/*     */     private int targetUnseenTicks;
/*     */     
/* 323 */     public EntityAINearestValidTarget(EntityTurretCrossbowAdvanced this$0, EntityCreature p_i45878_1_, Class p_i45878_2_, boolean p_i45878_3_) { this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 328 */     public EntityAINearestValidTarget(EntityTurretCrossbowAdvanced this$0, EntityCreature p_i45879_1_, Class p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_) { this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate)null); }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityAINearestValidTarget(EntityCreature p_i45880_1_, Class p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate tselector) {
/* 333 */       super(p_i45880_1_, p_i45880_4_, p_i45880_5_);
/* 334 */       this.targetClass = p_i45880_2_;
/* 335 */       this.targetChance = p_i45880_3_;
/* 336 */       this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(p_i45880_1_);
/* 337 */       func_75248_a(1);
/* 338 */       this.targetEntitySelector = new Predicate()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001621";
/*     */           
/*     */           public boolean applySelection(EntityLivingBase entity) {
/* 343 */             if (tselector != null && !tselector.apply(entity))
/*     */             {
/* 345 */               return false;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 350 */             if (entity instanceof EntityPlayer) {
/*     */               
/* 352 */               double d0 = EntityTurretCrossbowAdvanced.EntityAINearestValidTarget.this.func_111175_f();
/*     */               
/* 354 */               if (entity.func_70093_af())
/*     */               {
/* 356 */                 d0 *= 0.800000011920929D;
/*     */               }
/*     */               
/* 359 */               if (entity.func_82150_aj()) {
/*     */                 
/* 361 */                 float f = ((EntityPlayer)entity).func_82243_bO();
/*     */                 
/* 363 */                 if (f < 0.1F)
/*     */                 {
/* 365 */                   f = 0.1F;
/*     */                 }
/*     */                 
/* 368 */                 d0 *= (0.7F * f);
/*     */               } 
/*     */               
/* 371 */               if (entity.func_70032_d(EntityTurretCrossbowAdvanced.EntityAINearestValidTarget.this.field_75299_d) > d0)
/*     */               {
/* 373 */                 return false;
/*     */               }
/*     */             } 
/*     */             
/* 377 */             return EntityTurretCrossbowAdvanced.EntityAINearestValidTarget.this.func_75296_a(entity, false);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 382 */           public boolean apply(Object p_apply_1_) { return applySelection((EntityLivingBase)p_apply_1_); }
/*     */         };
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_75253_b() {
/* 390 */       EntityLivingBase entitylivingbase = this.field_75299_d.func_70638_az();
/* 391 */       if (entitylivingbase == null)
/*     */       {
/* 393 */         return false;
/*     */       }
/* 395 */       if (!entitylivingbase.func_70089_S())
/*     */       {
/* 397 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 401 */       Team team = this.field_75299_d.func_96124_cp();
/* 402 */       Team team1 = entitylivingbase.func_96124_cp();
/*     */       
/* 404 */       if (team != null && team1 == team && !((EntityTurretCrossbowAdvanced)this.field_75299_d).getTargetFriendly())
/*     */       {
/* 406 */         return false;
/*     */       }
/*     */       
/* 409 */       if (team != null && team1 != team && ((EntityTurretCrossbowAdvanced)this.field_75299_d).getTargetFriendly())
/*     */       {
/* 411 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 415 */       double d0 = func_111175_f();
/*     */       
/* 417 */       if (this.field_75299_d.func_70068_e(entitylivingbase) > d0 * d0)
/*     */       {
/* 419 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 423 */       if (this.field_75297_f)
/*     */       {
/* 425 */         if (this.field_75299_d.func_70635_at().func_75522_a(entitylivingbase)) {
/*     */           
/* 427 */           this.targetUnseenTicks = 0;
/*     */         }
/* 429 */         else if (++this.targetUnseenTicks > 60) {
/*     */           
/* 431 */           return false;
/*     */         } 
/*     */       }
/*     */       
/* 435 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean func_75296_a(EntityLivingBase p_75296_1_, boolean p_75296_2_) {
/* 444 */       if (!isGoodTarget(this.field_75299_d, p_75296_1_, p_75296_2_, this.field_75297_f))
/*     */       {
/* 446 */         return false;
/*     */       }
/* 448 */       if (!this.field_75299_d.func_180485_d(new BlockPos(p_75296_1_)))
/*     */       {
/* 450 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 454 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean isGoodTarget(EntityLiving attacker, EntityLivingBase posTar, boolean p_179445_2_, boolean checkSight) {
/* 461 */       if (posTar == null)
/*     */       {
/* 463 */         return false;
/*     */       }
/* 465 */       if (posTar == attacker)
/*     */       {
/* 467 */         return false;
/*     */       }
/* 469 */       if (!posTar.func_70089_S())
/*     */       {
/* 471 */         return false;
/*     */       }
/* 473 */       if (!attacker.func_70686_a(posTar.getClass()))
/*     */       {
/* 475 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 479 */       Team team = attacker.func_96124_cp();
/* 480 */       Team team1 = posTar.func_96124_cp();
/* 481 */       if (team != null && team1 == team && !((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly())
/*     */       {
/* 483 */         return false;
/*     */       }
/*     */       
/* 486 */       if (team != null && team1 != team && ((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly())
/*     */       {
/* 488 */         return false;
/*     */       }
/*     */       
/* 491 */       if (attacker instanceof IEntityOwnable && StringUtils.isNotEmpty(((IEntityOwnable)attacker).func_184753_b().toString())) {
/*     */         
/* 493 */         if (posTar instanceof IEntityOwnable && ((IEntityOwnable)attacker)
/* 494 */           .func_184753_b().equals(((IEntityOwnable)posTar).func_184753_b()) && 
/* 495 */           !((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly())
/*     */         {
/* 497 */           return false;
/*     */         }
/*     */         
/* 500 */         if (!(posTar instanceof IEntityOwnable) && !(posTar instanceof EntityPlayer) && ((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly()) {
/* 501 */           return false;
/*     */         }
/*     */         
/* 504 */         if (posTar instanceof IEntityOwnable && 
/* 505 */           !((IEntityOwnable)attacker).func_184753_b().equals(((IEntityOwnable)posTar).func_184753_b()) && ((EntityTurretCrossbowAdvanced)attacker)
/* 506 */           .getTargetFriendly())
/*     */         {
/* 508 */           return false;
/*     */         }
/*     */         
/* 511 */         if (posTar == ((IEntityOwnable)attacker).func_70902_q() && !((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly())
/*     */         {
/* 513 */           return false;
/*     */         
/*     */         }
/*     */       }
/* 517 */       else if (posTar instanceof EntityPlayer && !p_179445_2_ && ((EntityPlayer)posTar).field_71075_bZ.field_75102_a && 
/* 518 */         !((EntityTurretCrossbowAdvanced)attacker).getTargetFriendly()) {
/*     */         
/* 520 */         return false;
/*     */       } 
/*     */       
/* 523 */       return (!checkSight || attacker.func_70635_at().func_75522_a(posTar));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_75250_a() {
/* 530 */       if (this.targetChance > 0 && this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0)
/*     */       {
/* 532 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 536 */       double d0 = func_111175_f();
/* 537 */       List list = this.field_75299_d.field_70170_p.func_175647_a(this.targetClass, this.field_75299_d.func_174813_aQ().func_72314_b(d0, 4.0D, d0), Predicates.and(this.targetEntitySelector, EntitySelectors.field_180132_d));
/* 538 */       Collections.sort(list, this.theNearestAttackableTargetSorter);
/*     */       
/* 540 */       if (list.isEmpty())
/*     */       {
/* 542 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 546 */       this.targetEntity = (EntityLivingBase)list.get(0);
/* 547 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void func_75249_e() {
/* 557 */       this.field_75299_d.func_70624_b(this.targetEntity);
/* 558 */       this.targetUnseenTicks = 0;
/* 559 */       super.func_75249_e();
/*     */     }
/*     */ 
/*     */     
/*     */     public class Sorter
/*     */       implements Comparator
/*     */     {
/*     */       private final Entity theEntity;
/*     */       private static final String __OBFID = "CL_00001622";
/*     */       
/* 569 */       public Sorter(Entity p_i1662_1_) { this.theEntity = p_i1662_1_; }
/*     */ 
/*     */ 
/*     */       
/*     */       public int compare(Entity p_compare_1_, Entity p_compare_2_) {
/* 574 */         double d0 = this.theEntity.func_70068_e(p_compare_1_);
/* 575 */         double d1 = this.theEntity.func_70068_e(p_compare_2_);
/* 576 */         return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 581 */       public int compare(Object p_compare_1_, Object p_compare_2_) { return compare((Entity)p_compare_1_, (Entity)p_compare_2_); }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\construct\EntityTurretCrossbowAdvanced.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */