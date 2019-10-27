/*     */ package thaumcraft.common.golems;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIAttackRanged;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityMoveHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagLong;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.network.play.server.SPacketAnimation;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathNavigateClimber;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.config.ConfigAspects;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.entities.construct.EntityOwnedConstruct;
/*     */ import thaumcraft.common.golems.ai.AIFollowOwner;
/*     */ import thaumcraft.common.golems.ai.AIGotoBlock;
/*     */ import thaumcraft.common.golems.ai.AIGotoEntity;
/*     */ import thaumcraft.common.golems.ai.AIGotoHome;
/*     */ import thaumcraft.common.golems.ai.AIOwnerHurtByTarget;
/*     */ import thaumcraft.common.golems.ai.AIOwnerHurtTarget;
/*     */ import thaumcraft.common.golems.ai.PathNavigateGolemAir;
/*     */ import thaumcraft.common.golems.ai.PathNavigateGolemGround;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityThaumcraftGolem
/*     */   extends EntityOwnedConstruct
/*     */   implements IGolemAPI, IRangedAttackMob
/*     */ {
/*     */   int rankXp;
/*     */   
/*     */   public EntityThaumcraftGolem(World worldIn) {
/*  80 */     super(worldIn);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     this.rankXp = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     this.redrawParts = false;
/* 281 */     this.firstRun = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 702 */     this.task = null;
/* 703 */     this.taskID = Integer.MAX_VALUE; func_70105_a(0.4F, 0.9F); this.field_70728_aV = 5;
/*     */   } protected void func_184651_r() { this.field_70715_bh.field_75782_a.clear(); this.field_70714_bg.func_75776_a(2, new AIGotoEntity(this)); this.field_70714_bg.func_75776_a(3, new AIGotoBlock(this)); this.field_70714_bg.func_75776_a(4, new AIGotoHome(this)); this.field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F)); this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this)); } private static final DataParameter<Integer> PROPS1 = EntityDataManager.func_187226_a(EntityThaumcraftGolem.class, DataSerializers.field_187192_b); private static final DataParameter<Integer> PROPS2 = EntityDataManager.func_187226_a(EntityThaumcraftGolem.class, DataSerializers.field_187192_b); private static final DataParameter<Integer> PROPS3 = EntityDataManager.func_187226_a(EntityThaumcraftGolem.class, DataSerializers.field_187192_b); private static final DataParameter<Byte> CLIMBING = EntityDataManager.func_187226_a(EntityThaumcraftGolem.class, DataSerializers.field_187191_a); public boolean redrawParts; private boolean firstRun; protected Task task; protected int taskID; public static final int XPM = 1000; protected void func_70088_a() { super.func_70088_a(); func_184212_Q().func_187214_a(PROPS1, Integer.valueOf(0)); func_184212_Q().func_187214_a(PROPS2, Integer.valueOf(0)); func_184212_Q().func_187214_a(PROPS3, Integer.valueOf(0)); func_184212_Q().func_187214_a(CLIMBING, Byte.valueOf((byte)0)); } public IGolemProperties getProperties() { ByteBuffer bb = ByteBuffer.allocate(8); bb.putInt(((Integer)func_184212_Q().func_187225_a(PROPS1)).intValue()); bb.putInt(((Integer)func_184212_Q().func_187225_a(PROPS2)).intValue()); return GolemProperties.fromLong(bb.getLong(0)); } public void setProperties(IGolemProperties prop) { ByteBuffer bb = ByteBuffer.allocate(8); bb.putLong(prop.toLong()); bb.rewind(); func_184212_Q().func_187227_b(PROPS1, Integer.valueOf(bb.getInt())); func_184212_Q().func_187227_b(PROPS2, Integer.valueOf(bb.getInt())); } public byte getGolemColor() { byte[] ba = Utils.intToByteArray(((Integer)func_184212_Q().func_187225_a(PROPS3)).intValue()); return ba[0]; } public void setGolemColor(byte b) { byte[] ba = Utils.intToByteArray(((Integer)func_184212_Q().func_187225_a(PROPS3)).intValue()); ba[0] = b; func_184212_Q().func_187227_b(PROPS3, Integer.valueOf(Utils.byteArraytoInt(ba))); } public byte getFlags() { byte[] ba = Utils.intToByteArray(((Integer)func_184212_Q().func_187225_a(PROPS3)).intValue()); return ba[1]; } public void setFlags(byte b) { byte[] ba = Utils.intToByteArray(((Integer)func_184212_Q().func_187225_a(PROPS3)).intValue()); ba[1] = b; func_184212_Q().func_187227_b(PROPS3, Integer.valueOf(Utils.byteArraytoInt(ba))); } public float func_70047_e() { return 0.7F; } protected void func_110147_ax() { super.func_110147_ax(); func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D); func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D); func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e); func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D); } private void updateEntityAttributes() { int mh = 10 + (getProperties().getMaterial()).healthMod; if (getProperties().hasTrait(EnumGolemTrait.FRAGILE)) mh = (int)(mh * 0.75D);  mh += getProperties().getRank(); func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(mh); this.field_70138_W = getProperties().hasTrait(EnumGolemTrait.WHEELED) ? 0.5F : 0.6F; func_175449_a((func_180486_cf() == BlockPos.field_177992_a) ? func_180425_c() : func_180486_cf(), getProperties().hasTrait(EnumGolemTrait.SCOUT) ? 48 : 32); func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(getProperties().hasTrait(EnumGolemTrait.SCOUT) ? 56.0D : 40.0D); this.field_70699_by = getGolemNavigator(); if (getProperties().hasTrait(EnumGolemTrait.FLYER)) this.field_70765_h = new FlyingMoveControl(this);  if (getProperties().hasTrait(EnumGolemTrait.FIGHTER)) { double da = (getProperties().getMaterial()).damage; if (getProperties().hasTrait(EnumGolemTrait.BRUTAL)) da = Math.max(da * 1.5D, da + 1.0D);  da += getProperties().getRank() * 0.25D; func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(da); } else { func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D); }  createAI(); } private void createAI() { this.field_70714_bg.field_75782_a.clear(); this.field_70715_bh.field_75782_a.clear(); if (isFollowingOwner()) { this.field_70714_bg.func_75776_a(4, new AIFollowOwner(this, 1.0D, 10.0F, 2.0F)); } else { this.field_70714_bg.func_75776_a(3, new AIGotoEntity(this)); this.field_70714_bg.func_75776_a(4, new AIGotoBlock(this)); this.field_70714_bg.func_75776_a(5, new AIGotoHome(this)); }  this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F)); this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this)); if (getProperties().hasTrait(EnumGolemTrait.FIGHTER)) { if (func_70661_as() instanceof net.minecraft.pathfinding.PathNavigateGround) this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));  if (getProperties().hasTrait(EnumGolemTrait.RANGED)) { EntityAIAttackRanged aa = null; if ((getProperties().getArms()).function != null) aa = (getProperties().getArms()).function.getRangedAttackAI(this);  if (aa != null) this.field_70714_bg.func_75776_a(1, aa);  }  this.field_70714_bg.func_75776_a(2, new EntityAIAttackMelee(this, 1.15D, false)); if (isFollowingOwner()) { this.field_70715_bh.func_75776_a(1, new AIOwnerHurtByTarget(this)); this.field_70715_bh.func_75776_a(2, new AIOwnerHurtTarget(this)); }  this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, false, new Class[0])); }  } public boolean func_70617_f_() { return isBesideClimbableBlock(); }
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData ld) { func_175449_a(func_180425_c(), 32); updateEntityAttributes(); return ld; }
/* 706 */   public Task getTask() { if (this.task == null && this.taskID != Integer.MAX_VALUE) {
/* 707 */       this.task = TaskHandler.getTask(this.field_70170_p.field_73011_w.getDimension(), this.taskID);
/* 708 */       this.taskID = Integer.MAX_VALUE;
/*     */     } 
/* 710 */     return this.task; }
/*     */   public int func_70658_aO() { int armor = (getProperties().getMaterial()).armor; if (getProperties().hasTrait(EnumGolemTrait.ARMORED)) armor = (int)Math.max(armor * 1.5D, (armor + 1));  if (getProperties().hasTrait(EnumGolemTrait.FRAGILE)) armor = (int)(armor * 0.75D);  return armor; }
/*     */   public void func_70636_d() { func_82168_bl(); super.func_70636_d(); }
/*     */   public void func_70071_h_() { super.func_70071_h_(); if (getProperties().hasTrait(EnumGolemTrait.FLYER)) func_189654_d(true);  if (!this.field_70170_p.field_72995_K) { if (this.firstRun) { this.firstRun = false; if (func_110175_bO() && !func_180425_c().equals(func_180486_cf())) goHome();  }  if (this.task != null && this.task.isSuspended()) this.task = null;  if (func_70638_az() != null && (func_70638_az()).field_70128_L) func_70624_b(null);  if (func_70638_az() != null && getProperties().hasTrait(EnumGolemTrait.RANGED) && func_70068_e(func_70638_az()) > 1024.0D) func_70624_b(null);  if (!FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W() && func_70638_az() != null && func_70638_az() instanceof EntityPlayer) func_70624_b(null);  if (this.field_70173_aa % (getProperties().hasTrait(EnumGolemTrait.REPAIR) ? 40 : 100) == 0) func_70691_i(1.0F);  if (getProperties().hasTrait(EnumGolemTrait.CLIMBER)) setBesideClimbableBlock(this.field_70123_F);  } else if (this.field_70173_aa < 20 || this.field_70173_aa % 20 == 0) { this.redrawParts = true; }  if ((getProperties().getHead()).function != null) (getProperties().getHead()).function.onUpdateTick(this);  if ((getProperties().getArms()).function != null) (getProperties().getArms()).function.onUpdateTick(this);  if ((getProperties().getLegs()).function != null) (getProperties().getLegs()).function.onUpdateTick(this);  if ((getProperties().getAddon()).function != null) (getProperties().getAddon()).function.onUpdateTick(this);  }
/* 714 */   @SideOnly(Side.CLIENT) public void func_70103_a(byte par1) { if (par1 == 5) { FXDispatcher.INSTANCE.drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O + 0.1D, this.field_70161_v, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.5F, false, 'ˀ' + (this.field_70146_Z.nextBoolean() ? Character.MIN_VALUE : 3), 3, 1, 6, 0, 2.0F, 0.0F, 1); } else if (par1 == 6) { FXDispatcher.INSTANCE.drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O + 0.1D, this.field_70161_v, 0.0D, 0.025D, 0.0D, 0.1F, 1.0F, 1.0F, 0.5F, false, 15, 1, 1, 10, 0, 2.0F, 0.0F, 1); } else if (par1 == 7) { FXDispatcher.INSTANCE.drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O + 0.1D, this.field_70161_v, 0.0D, 0.05D, 0.0D, 1.0F, 1.0F, 1.0F, 0.5F, false, 640, 10, 1, 10, 0, 2.0F, 0.0F, 1); } else if (par1 == 8) { FXDispatcher.INSTANCE.drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O + 0.1D, this.field_70161_v, 0.0D, 0.01D, 0.0D, 1.0F, 1.0F, 0.1F, 0.5F, false, 14, 1, 1, 20, 0, 2.0F, 0.0F, 1); } else if (par1 == 9) { for (int a = 0; a < 5; a++) FXDispatcher.INSTANCE.drawGenericParticles(this.field_70165_t, this.field_70163_u + this.field_70131_O, this.field_70161_v, this.field_70146_Z.nextGaussian() * 0.009999999776482582D, this.field_70146_Z.nextFloat() * 0.02D, this.field_70146_Z.nextGaussian() * 0.009999999776482582D, 1.0F, 1.0F, 1.0F, 0.5F, false, 13, 1, 1, 20 + this.field_70146_Z.nextInt(20), 0, 0.3F + this.field_70146_Z.nextFloat() * 0.4F, 0.0F, 1);  } else { super.func_70103_a(par1); }  } public float getGolemMoveSpeed() { return 1.0F + getProperties().getRank() * 0.025F + (getProperties().hasTrait(EnumGolemTrait.LIGHT) ? 0.2F : 0.0F) + (getProperties().hasTrait(EnumGolemTrait.HEAVY) ? -0.175F : 0.0F) + (getProperties().hasTrait(EnumGolemTrait.FLYER) ? -0.33F : 0.0F) + (getProperties().hasTrait(EnumGolemTrait.WHEELED) ? 0.25F : 0.0F); } public PathNavigate getGolemNavigator() { return getProperties().hasTrait(EnumGolemTrait.FLYER) ? new PathNavigateGolemAir(this, this.field_70170_p) : (getProperties().hasTrait(EnumGolemTrait.CLIMBER) ? new PathNavigateClimber(this, this.field_70170_p) : new PathNavigateGolemGround(this, this.field_70170_p)); } public void setTask(Task task) { this.task = task; }
/*     */   protected boolean func_70041_e_() { return (getProperties().hasTrait(EnumGolemTrait.HEAVY) && !getProperties().hasTrait(EnumGolemTrait.FLYER)); }
/*     */   public void func_180430_e(float distance, float damageMultiplier) { if (!getProperties().hasTrait(EnumGolemTrait.FLYER) && !getProperties().hasTrait(EnumGolemTrait.CLIMBER)) super.func_180430_e(distance, damageMultiplier);  }
/*     */   private void goHome() { double d0 = this.field_70165_t; double d1 = this.field_70163_u; double d2 = this.field_70161_v; this.field_70165_t = func_180486_cf().func_177958_n() + 0.5D; this.field_70163_u = func_180486_cf().func_177956_o(); this.field_70161_v = func_180486_cf().func_177952_p() + 0.5D; boolean flag = false; BlockPos blockpos = new BlockPos(this); boolean flag1 = false; while (!flag1 && blockpos.func_177956_o() < this.field_70170_p.func_72940_L()) { BlockPos blockpos1 = blockpos.func_177984_a(); IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos1); if (iblockstate.func_185904_a().func_76230_c()) { flag1 = true; continue; }  this.field_70163_u++; blockpos = blockpos1; }  if (flag1) { func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v); if (this.field_70170_p.func_184144_a(this, func_174813_aQ()).isEmpty()) flag = true;  }  if (!flag) { func_70634_a(d0, d1, d2); } else if (this instanceof net.minecraft.entity.EntityCreature) { func_70661_as().func_75499_g(); }  }
/*     */   public void func_70037_a(NBTTagCompound nbt) { super.func_70037_a(nbt); setProperties(GolemProperties.fromLong(nbt.func_74763_f("props"))); func_175449_a(BlockPos.func_177969_a(nbt.func_74763_f("homepos")), 32); setFlags(Byte.valueOf(nbt.func_74771_c("gflags")).byteValue()); this.rankXp = nbt.func_74762_e("rankXP"); setGolemColor(nbt.func_74771_c("color")); updateEntityAttributes(); }
/*     */   public void func_70014_b(NBTTagCompound nbt) { super.func_70014_b(nbt); nbt.func_74772_a("props", getProperties().toLong()); nbt.func_74772_a("homepos", func_180486_cf().func_177986_g()); nbt.func_74774_a("gflags", getFlags()); nbt.func_74768_a("rankXP", this.rankXp); nbt.func_74774_a("color", getGolemColor()); }
/*     */   protected void func_70665_d(DamageSource ds, float damage) { if (ds.func_76347_k() && getProperties().hasTrait(EnumGolemTrait.FIREPROOF)) return;  if (ds.func_94541_c() && getProperties().hasTrait(EnumGolemTrait.BLASTPROOF)) damage = Math.min(func_110138_aP() / 2.0F, damage * 0.3F);  if (ds == DamageSource.field_76367_g) return;  if (func_110175_bO() && (ds == DamageSource.field_76368_d || ds == DamageSource.field_76380_i)) goHome();  super.func_70665_d(ds, damage); }
/*     */   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) { if (this.field_70128_L) return false;  if (player.func_184586_b(hand).func_77973_b() instanceof net.minecraft.item.ItemNameTag) return false;  if (!this.field_70170_p.field_72995_K && isOwner(player) && !this.field_70128_L) { if (player.func_70093_af()) { func_184185_a(SoundsTC.zap, 1.0F, 1.0F); if (this.task != null) this.task.setReserved(false);  dropCarried(); ItemStack placer = new ItemStack(ItemsTC.golemPlacer); placer.func_77983_a("props", new NBTTagLong(getProperties().toLong())); placer.func_77983_a("xp", new NBTTagInt(this.rankXp)); func_70099_a(placer, 0.5F); func_70106_y(); player.func_184609_a(hand); } else if (player.func_184586_b(hand).func_77973_b() instanceof ItemGolemBell && ThaumcraftCapabilities.getKnowledge(player).isResearchKnown("GOLEMDIRECT")) { if (this.task != null) this.task.setReserved(false);  func_184185_a(SoundsTC.scan, 1.0F, 1.0F); setFollowingOwner(!isFollowingOwner()); if (isFollowingOwner()) { player.func_146105_b(new TextComponentTranslation("golem.follow", new Object[] { "" }), true); if (ModConfig.CONFIG_GRAPHICS.showGolemEmotes) this.field_70170_p.func_72960_a(this, (byte)5);  func_110177_bN(); } else { player.func_146105_b(new TextComponentTranslation("golem.stay", new Object[] { "" }), true); if (ModConfig.CONFIG_GRAPHICS.showGolemEmotes) this.field_70170_p.func_72960_a(this, (byte)8);  func_175449_a(func_180425_c(), getProperties().hasTrait(EnumGolemTrait.SCOUT) ? 48 : 32); }  updateEntityAttributes(); player.func_184609_a(hand); } else if (!player.func_184586_b(hand).func_190926_b()) { int[] ids = OreDictionary.getOreIDs(player.func_184586_b(hand)); if (ids != null && ids.length > 0) for (int id : ids) { String s = OreDictionary.getOreName(id); if (s.startsWith("dye")) for (int a = 0; a < ConfigAspects.dyes.length; a++) { if (s.equals(ConfigAspects.dyes[a])) { func_184185_a(SoundsTC.zap, 1.0F, 1.0F); setGolemColor((byte)(16 - a)); player.func_184586_b(hand).func_190918_g(1); player.func_184609_a(hand); return true; }  }   }   }  return true; }  return super.func_184645_a(player, hand); }
/*     */   public void func_70645_a(DamageSource cause) { if (this.task != null) this.task.setReserved(false);  super.func_70645_a(cause); if (!this.field_70170_p.field_72995_K) dropCarried();  }
/* 723 */   protected void dropCarried() { for (ItemStack s : getCarrying()) { if (s != null && !s.func_190926_b()) func_70099_a(s, 0.25F);  }  } protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) { float b = p_70628_2_ * 0.15F; for (ItemStack stack : getProperties().generateComponents()) { ItemStack s = stack.func_77946_l(); if (this.field_70146_Z.nextFloat() < 0.3F + b) { if (s.func_190916_E() > 0) s.func_190918_g(this.field_70146_Z.nextInt(s.func_190916_E()));  func_70099_a(s, 0.25F); }  }  } public boolean isBesideClimbableBlock() { return ((((Byte)this.field_70180_af.func_187225_a(CLIMBING)).byteValue() & true) != 0); } public void setBesideClimbableBlock(boolean climbing) { byte b0 = ((Byte)this.field_70180_af.func_187225_a(CLIMBING)).byteValue(); if (climbing) { b0 = (byte)(b0 | true); } else { b0 = (byte)(b0 & 0xFFFFFFFE); }  this.field_70180_af.func_187227_b(CLIMBING, Byte.valueOf(b0)); } public void addRankXp(int xp) { if (!getProperties().hasTrait(EnumGolemTrait.SMART) || this.field_70170_p.field_72995_K)
/* 724 */       return;  int rank = getProperties().getRank();
/* 725 */     if (rank < 10)
/* 726 */     { this.rankXp += xp;
/* 727 */       int xn = (rank + 1) * (rank + 1) * 1000;
/* 728 */       if (this.rankXp >= xn)
/* 729 */       { this.rankXp -= xn;
/* 730 */         IGolemProperties props = getProperties();
/* 731 */         props.setRank(rank + 1);
/* 732 */         setProperties(props);
/* 733 */         if (ModConfig.CONFIG_GRAPHICS.showGolemEmotes)
/* 734 */         { this.field_70170_p.func_72960_a(this, (byte)9);
/* 735 */           func_184185_a(SoundEvents.field_187802_ec, 0.25F, 1.0F); }  }  }  } public boolean isFollowingOwner() { return Utils.getBit(getFlags(), 1); } public void setFollowingOwner(boolean par1) { byte var2 = getFlags(); if (par1) { setFlags(Byte.valueOf((byte)Utils.setBit(var2, 1)).byteValue()); } else { setFlags(Byte.valueOf((byte)Utils.clearBit(var2, 1)).byteValue()); }  } public void func_70624_b(EntityLivingBase entitylivingbaseIn) { super.func_70624_b(entitylivingbaseIn); setInCombat((func_70638_az() != null)); }
/*     */   public boolean isInCombat() { return Utils.getBit(getFlags(), 3); }
/*     */   public void setInCombat(boolean par1) { byte var2 = getFlags(); if (par1) { setFlags(Byte.valueOf((byte)Utils.setBit(var2, 3)).byteValue()); } else { setFlags(Byte.valueOf((byte)Utils.clearBit(var2, 3)).byteValue()); }  }
/*     */   public boolean func_70652_k(Entity ent) { float dmg = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e(); int kb = 0; if (ent instanceof EntityLivingBase) { dmg += EnchantmentHelper.func_152377_a(func_184614_ca(), ((EntityLivingBase)ent).func_70668_bt()); kb += EnchantmentHelper.func_77501_a(this); }  boolean flag = ent.func_70097_a(DamageSource.func_76358_a(this), dmg); if (flag) { if (ent instanceof EntityLivingBase && getProperties().hasTrait(EnumGolemTrait.DEFT))
/*     */         ((EntityLivingBase)ent).field_70718_bc = 100;  if (kb > 0) { ent.func_70024_g((-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * kb * 0.5F), 0.1D, (MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * kb * 0.5F)); this.field_70159_w *= 0.6D; this.field_70179_y *= 0.6D; }  int j = EnchantmentHelper.func_90036_a(this); if (j > 0)
/*     */         ent.func_70015_d(j * 4);  func_174815_a(this, ent); if ((getProperties().getArms()).function != null)
/*     */         (getProperties().getArms()).function.onMeleeAttack(this, ent);  if (ent instanceof EntityLiving && !((EntityLiving)ent).func_70089_S())
/*     */         addRankXp(8);  }  return flag; }
/* 743 */   public ItemStack holdItem(ItemStack stack) { if (stack == null || stack.func_190926_b() || stack.func_190916_E() <= 0) return stack;
/*     */     
/* 745 */     for (int a = 0; a < (getProperties().hasTrait(EnumGolemTrait.HAULER) ? 2 : 1); a++) {
/* 746 */       if (func_184582_a(EntityEquipmentSlot.values()[a]) == null || func_184582_a(EntityEquipmentSlot.values()[a]).func_190926_b()) {
/* 747 */         func_184201_a(EntityEquipmentSlot.values()[a], stack);
/* 748 */         return ItemStack.field_190927_a;
/*     */       } 
/* 750 */       if (func_184582_a(EntityEquipmentSlot.values()[a]).func_190916_E() < func_184582_a(EntityEquipmentSlot.values()[a]).func_77976_d() && 
/* 751 */         ItemStack.func_179545_c(func_184582_a(EntityEquipmentSlot.values()[a]), stack) && 
/* 752 */         ItemStack.func_77970_a(func_184582_a(EntityEquipmentSlot.values()[a]), stack)) {
/* 753 */         int d = Math.min(stack.func_190916_E(), func_184582_a(EntityEquipmentSlot.values()[a]).func_77976_d() - func_184582_a(EntityEquipmentSlot.values()[a]).func_190916_E());
/* 754 */         stack.func_190918_g(d);
/* 755 */         func_184582_a(EntityEquipmentSlot.values()[a]).func_190917_f(d);
/* 756 */         if (stack.func_190916_E() <= 0) stack = ItemStack.field_190927_a;
/*     */       
/*     */       } 
/*     */     } 
/* 760 */     return stack; }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack dropItem(ItemStack stack) {
/* 765 */     ItemStack out = ItemStack.field_190927_a;
/* 766 */     for (int a = 0; a < (getProperties().hasTrait(EnumGolemTrait.HAULER) ? 2 : 1); a++) {
/* 767 */       if (func_184582_a(EntityEquipmentSlot.values()[a]) != null && !func_184582_a(EntityEquipmentSlot.values()[a]).func_190926_b())
/* 768 */       { if (stack == null || stack.func_190926_b()) {
/* 769 */           out = func_184582_a(EntityEquipmentSlot.values()[a]).func_77946_l();
/*     */           
/* 771 */           func_184201_a(EntityEquipmentSlot.values()[a], ItemStack.field_190927_a);
/*     */         }
/* 773 */         else if (ItemStack.func_179545_c(func_184582_a(EntityEquipmentSlot.values()[a]), stack) && 
/* 774 */           ItemStack.func_77970_a(func_184582_a(EntityEquipmentSlot.values()[a]), stack)) {
/* 775 */           out = func_184582_a(EntityEquipmentSlot.values()[a]).func_77946_l();
/* 776 */           out.func_190920_e(Math.min(stack.func_190916_E(), out.func_190916_E()));
/* 777 */           func_184582_a(EntityEquipmentSlot.values()[a]).func_190918_g(stack.func_190916_E());
/* 778 */           if (func_184582_a(EntityEquipmentSlot.values()[a]).func_190916_E() <= 0) {
/* 779 */             func_184201_a(EntityEquipmentSlot.values()[a], ItemStack.field_190927_a);
/*     */           }
/*     */         } 
/* 782 */         if (out != null && !out.func_190926_b())
/*     */           break;  } 
/* 784 */     }  if (getProperties().hasTrait(EnumGolemTrait.HAULER) && (
/* 785 */       func_184582_a(EntityEquipmentSlot.values()[false]) == null || func_184582_a(EntityEquipmentSlot.values()[0]).func_190926_b()) && 
/* 786 */       func_184582_a(EntityEquipmentSlot.values()[true]) != null && !func_184582_a(EntityEquipmentSlot.values()[1]).func_190926_b()) {
/* 787 */       func_184201_a(EntityEquipmentSlot.values()[0], func_184582_a(EntityEquipmentSlot.values()[1]).func_77946_l());
/* 788 */       func_184201_a(EntityEquipmentSlot.values()[1], ItemStack.field_190927_a);
/*     */     } 
/*     */     
/* 791 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public int canCarryAmount(ItemStack stack) {
/* 796 */     int ss = 0;
/* 797 */     for (int a = 0; a < (getProperties().hasTrait(EnumGolemTrait.HAULER) ? 2 : 1); a++) {
/* 798 */       if (func_184582_a(EntityEquipmentSlot.values()[a]) == null || func_184582_a(EntityEquipmentSlot.values()[a]).func_190926_b())
/* 799 */         ss += func_184582_a(EntityEquipmentSlot.values()[a]).func_77976_d(); 
/* 800 */       if (ItemStack.func_179545_c(func_184582_a(EntityEquipmentSlot.values()[a]), stack) && 
/* 801 */         ItemStack.func_77970_a(func_184582_a(EntityEquipmentSlot.values()[a]), stack)) {
/* 802 */         ss += func_184582_a(EntityEquipmentSlot.values()[a]).func_77976_d() - func_184582_a(EntityEquipmentSlot.values()[a]).func_190916_E();
/*     */       }
/*     */     } 
/* 805 */     return ss;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCarry(ItemStack stack, boolean partial) {
/* 810 */     int ca = canCarryAmount(stack);
/* 811 */     return (ca > 0 && (partial || ca >= stack.func_190916_E()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCarrying(ItemStack stack) {
/* 816 */     if (stack == null || stack.func_190926_b()) return false; 
/* 817 */     for (int a = 0; a < (getProperties().hasTrait(EnumGolemTrait.HAULER) ? 2 : 1); a++) {
/* 818 */       if (func_184582_a(EntityEquipmentSlot.values()[a]) != null && 
/* 819 */         !func_184582_a(EntityEquipmentSlot.values()[a]).func_190926_b() && 
/* 820 */         func_184582_a(EntityEquipmentSlot.values()[a]).func_190916_E() > 0 && 
/* 821 */         ItemStack.func_179545_c(func_184582_a(EntityEquipmentSlot.values()[a]), stack) && 
/* 822 */         ItemStack.func_77970_a(func_184582_a(EntityEquipmentSlot.values()[a]), stack))
/* 823 */         return true; 
/*     */     } 
/* 825 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public NonNullList<ItemStack> getCarrying() {
/* 830 */     if (getProperties().hasTrait(EnumGolemTrait.HAULER)) {
/* 831 */       NonNullList<ItemStack> stacks = NonNullList.func_191197_a(2, ItemStack.field_190927_a);
/* 832 */       stacks.set(0, func_184582_a(EntityEquipmentSlot.values()[0]));
/* 833 */       stacks.set(1, func_184582_a(EntityEquipmentSlot.values()[1]));
/* 834 */       return stacks;
/*     */     } 
/* 836 */     return NonNullList.func_191197_a(1, func_184582_a(EntityEquipmentSlot.values()[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 841 */   public EntityLivingBase getGolemEntity() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 846 */   public World getGolemWorld() { return func_130014_f_(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void swingArm() {
/* 852 */     if (!this.field_82175_bq || this.field_110158_av >= 3 || this.field_110158_av < 0) {
/*     */       
/* 854 */       this.field_110158_av = -1;
/* 855 */       this.field_82175_bq = true;
/*     */       
/* 857 */       if (this.field_70170_p instanceof WorldServer)
/*     */       {
/* 859 */         ((WorldServer)this.field_70170_p).func_73039_n().func_151248_b(this, new SPacketAnimation(this, 0));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82196_d(EntityLivingBase target, float range) {
/* 867 */     if ((getProperties().getArms()).function != null) {
/* 868 */       (getProperties().getArms()).function.onRangedAttack(this, target, range);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184724_a(boolean swingingArms) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class FlyingMoveControl
/*     */     extends EntityMoveHelper
/*     */   {
/* 884 */     public FlyingMoveControl(EntityThaumcraftGolem vex) { super(vex); }
/*     */ 
/*     */ 
/*     */     
/*     */     public void func_75641_c() {
/* 889 */       if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
/*     */         
/* 891 */         double d0 = this.field_75646_b - EntityThaumcraftGolem.this.field_70165_t;
/* 892 */         double d1 = this.field_75647_c - EntityThaumcraftGolem.this.field_70163_u;
/* 893 */         double d2 = this.field_75644_d - EntityThaumcraftGolem.this.field_70161_v;
/* 894 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 895 */         d3 = MathHelper.func_76133_a(d3);
/*     */         
/* 897 */         if (d3 < EntityThaumcraftGolem.this.func_174813_aQ().func_72320_b()) {
/*     */           
/* 899 */           this.field_188491_h = EntityMoveHelper.Action.WAIT;
/* 900 */           EntityThaumcraftGolem.this.field_70159_w *= 0.5D;
/* 901 */           EntityThaumcraftGolem.this.field_70181_x *= 0.5D;
/* 902 */           EntityThaumcraftGolem.this.field_70179_y *= 0.5D;
/*     */         }
/*     */         else {
/*     */           
/* 906 */           EntityThaumcraftGolem.this.field_70159_w += d0 / d3 * 0.033D * this.field_75645_e;
/* 907 */           EntityThaumcraftGolem.this.field_70181_x += d1 / d3 * 0.0125D * this.field_75645_e;
/* 908 */           EntityThaumcraftGolem.this.field_70179_y += d2 / d3 * 0.033D * this.field_75645_e;
/*     */           
/* 910 */           if (EntityThaumcraftGolem.this.func_70638_az() == null) {
/*     */             
/* 912 */             EntityThaumcraftGolem.this.field_70177_z = -((float)MathHelper.func_181159_b(EntityThaumcraftGolem.this.field_70159_w, EntityThaumcraftGolem.this.field_70179_y)) * 57.295776F;
/* 913 */             EntityThaumcraftGolem.this.field_70761_aq = EntityThaumcraftGolem.this.field_70177_z;
/*     */           }
/*     */           else {
/*     */             
/* 917 */             double d4 = (this.this$0.func_70638_az()).field_70165_t - EntityThaumcraftGolem.this.field_70165_t;
/* 918 */             double d5 = (this.this$0.func_70638_az()).field_70161_v - EntityThaumcraftGolem.this.field_70161_v;
/* 919 */             EntityThaumcraftGolem.this.field_70177_z = -((float)MathHelper.func_181159_b(d4, d5)) * 57.295776F;
/* 920 */             EntityThaumcraftGolem.this.field_70761_aq = EntityThaumcraftGolem.this.field_70177_z;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\EntityThaumcraftGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */