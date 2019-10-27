/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.BossInfo;
/*     */ import net.minecraft.world.BossInfoServer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.tiles.misc.TileBanner;
/*     */ 
/*     */ public class EntityCultistPortalGreater extends EntityMob {
/*     */   protected final BossInfoServer bossInfo;
/*     */   int stage;
/*     */   int stagecounter;
/*     */   public int pulse;
/*     */   
/*  43 */   public EntityCultistPortalGreater(World par1World) { super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     this
/*  50 */       .bossInfo = (BossInfoServer)(new BossInfoServer(func_145748_c_(), BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_6)).func_186741_a(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.stage = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     this.stagecounter = 200;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 319 */     this.pulse = 0; this.field_70178_ae = true; this.field_70728_aV = 30; func_70105_a(1.5F, 3.0F); } public int func_70658_aO() { return 5; } protected void func_70088_a() { super.func_70088_a(); } public void func_70014_b(NBTTagCompound nbt) { super.func_70014_b(nbt); nbt.func_74768_a("stage", this.stage); } public void func_70037_a(NBTTagCompound nbt) { super.func_70037_a(nbt); this.stage = nbt.func_74762_e("stage"); } protected void func_110147_ax() { super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D); } public boolean func_70067_L() { return true; } public boolean func_70104_M() { return false; } public void func_70091_d(MoverType mt, double par1, double par3, double par5) {} public void func_70636_d() {} public boolean func_70112_a(double par1) { return (par1 < 4096.0D); }
/*     */   @SideOnly(Side.CLIENT) public int func_70070_b() { return 15728880; }
/*     */   public float func_70013_c() { return 1.0F; }
/* 325 */   @SideOnly(Side.CLIENT) public void func_70103_a(byte msg) { if (msg == 16)
/*     */     
/* 327 */     { this.pulse = 10; }
/*     */     
/*     */     else
/*     */     
/* 331 */     { super.func_70103_a(msg); }  }
/*     */   public void func_70071_h_() { super.func_70071_h_(); if (!this.field_70170_p.field_72995_K) { if (this.stagecounter > 0) { this.stagecounter--; if (this.stagecounter == 160 && this.stage == 0) { this.field_70170_p.func_72960_a(this, (byte)16); for (EnumFacing dir : EnumFacing.field_176754_o) { BlockPos bp = new BlockPos((int)this.field_70165_t - dir.func_82601_c() * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.func_82599_e() * 6); this.field_70170_p.func_180501_a(bp, BlocksTC.bannerCrimsonCult.func_176223_P(), 3); TileEntity te = this.field_70170_p.func_175625_s(new BlockPos((int)this.field_70165_t - dir.func_82601_c() * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.func_82599_e() * 6)); if (te != null && te instanceof TileBanner) { int face = 0; switch (dir.ordinal()) { case 2: face = 8; break;
/*     */                 case 3: face = 0; break;
/*     */                 case 4: face = 12; break;
/*     */                 case 5: face = 4; break; }  ((TileBanner)te).setBannerFacing((byte)face); PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(new BlockPos((int)this.field_70165_t - dir.func_82601_c() * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.func_82599_e() * 6), this, 0.5F + this.field_70146_Z.nextFloat() * 0.2F, 0.0F, 0.0F), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.getDimension(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D)); func_184185_a(SoundsTC.wandfail, 1.0F, 1.0F); }  }  }  if (this.stagecounter > 20 && this.stagecounter < 150 && this.stage == 0 && this.stagecounter % 13 == 0) { int a = (int)this.field_70165_t + this.field_70146_Z.nextInt(5) - this.field_70146_Z.nextInt(5); int b = (int)this.field_70161_v + this.field_70146_Z.nextInt(5) - this.field_70146_Z.nextInt(5); BlockPos bp = new BlockPos(a, (int)this.field_70163_u, b); if (a != (int)this.field_70165_t && b != (int)this.field_70161_v && this.field_70170_p.func_175623_d(bp)) { this.field_70170_p.func_72960_a(this, (byte)16); float rr = this.field_70170_p.field_73012_v.nextFloat(); int md = (rr < 0.05F) ? 2 : ((rr < 0.2F) ? 1 : 0); Block bb = BlocksTC.lootCrateCommon; switch (md) { case 1: bb = BlocksTC.lootCrateUncommon; break;
/*     */               case 2: bb = BlocksTC.lootCrateRare; break; }  this.field_70170_p.func_175656_a(bp, bb.func_176223_P()); PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(new BlockPos(a, (int)this.field_70163_u, b), this, 0.5F + this.field_70146_Z.nextFloat() * 0.2F, 0.0F, 0.0F), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.getDimension(), this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D)); func_184185_a(SoundsTC.wandfail, 1.0F, 1.0F); }  }  } else if (this.field_70170_p.func_72890_a(this, 48.0D) != null) { int t; this.field_70170_p.func_72960_a(this, (byte)16); switch (this.stage) { case 0: case 1: case 2: case 3: case 4: this.stagecounter = 15 + this.field_70146_Z.nextInt(10 - this.stage) - this.stage; spawnMinions(); break;
/*     */           case 12: this.stagecounter = 50 + getTiming() * 2 + this.field_70146_Z.nextInt(50); spawnBoss(); break;
/*     */           default:
/*     */             t = getTiming(); this.stagecounter = t + this.field_70146_Z.nextInt(5 + t / 3); spawnMinions(); break; }  this.stage++; } else { this.stagecounter = 30 + this.field_70146_Z.nextInt(30); }  if (this.stage < 12)
/*     */         func_70691_i(1.0F);  }  if (this.pulse > 0)
/*     */       this.pulse--;  }
/*     */   int getTiming() { List<EntityCultist> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityCultist.class, 32.0D); return l.size() * 20; }
/*     */   void spawnMinions() { EntityCultistCleric entityCultistCleric = null; if (this.field_70146_Z.nextFloat() > 0.33D) { EntityCultistKnight entityCultistKnight = new EntityCultistKnight(this.field_70170_p); } else { entityCultistCleric = new EntityCultistCleric(this.field_70170_p); }  entityCultistCleric.func_70107_b(this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25D, this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()); entityCultistCleric.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(entityCultistCleric.func_180425_c())), (IEntityLivingData)null); entityCultistCleric.func_175449_a(func_180425_c(), 32); this.field_70170_p.func_72838_d(entityCultistCleric); entityCultistCleric.func_70656_aK(); entityCultistCleric.func_184185_a(SoundsTC.wandfail, 1.0F, 1.0F); if (this.stage > 12)
/* 344 */       func_70097_a(DamageSource.field_76380_i, (5 + this.field_70146_Z.nextInt(5)));  } public void func_70645_a(DamageSource p_70645_1_) { if (!this.field_70170_p.field_72995_K) {
/* 345 */       this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0F, false, false);
/*     */     }
/*     */     
/* 348 */     super.func_70645_a(p_70645_1_); }
/*     */   void spawnBoss() { EntityCultistLeader cultist = new EntityCultistLeader(this.field_70170_p); cultist.func_70107_b(this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25D, this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()); cultist.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos(cultist.func_180425_c())), (IEntityLivingData)null);
/*     */     cultist.func_175449_a(func_180425_c(), 32);
/*     */     this.field_70170_p.func_72838_d(cultist);
/*     */     cultist.func_70656_aK();
/*     */     cultist.func_184185_a(SoundsTC.wandfail, 1.0F, 1.0F); }
/*     */   public void func_70100_b_(EntityPlayer p) { if (func_70068_e(p) < 3.0D && p.func_70097_a(DamageSource.func_76354_b(this, this), 8.0F))
/* 355 */       func_184185_a(SoundsTC.zap, 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F + 1.0F);  } protected float func_70599_aP() { return 0.75F; } public int func_70627_aG() { return 540; } protected SoundEvent func_184639_G() { return SoundsTC.monolith; } protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) { return SoundsTC.zap; } protected SoundEvent func_184615_bR() { return SoundsTC.shock; } protected Item func_146068_u() { return Item.func_150899_d(0); } protected void func_70628_a(boolean flag, int fortune) { EntityUtils.entityDropSpecialItem(this, new ItemStack(ItemsTC.primordialPearl), this.field_70131_O / 2.0F); } public void func_70690_d(PotionEffect p_70690_1_) {} public void func_180430_e(float distance, float damageMultiplier) {} public void func_184203_c(EntityPlayerMP player) { super.func_184203_c(player);
/* 356 */     this.bossInfo.func_186761_b(player); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184178_b(EntityPlayerMP player) {
/* 362 */     super.func_184178_b(player);
/* 363 */     this.bossInfo.func_186760_a(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public boolean func_184222_aU() { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70619_bc() {
/* 374 */     super.func_70619_bc();
/* 375 */     this.bossInfo.func_186735_a(func_110143_aJ() / func_110138_aP());
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\boss\EntityCultistPortalGreater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */