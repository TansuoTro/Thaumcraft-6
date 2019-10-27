/*     */ package thaumcraft.common.entities.monster.cult;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.storage.loot.LootTableList;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityCultist
/*     */   extends EntityMob
/*     */ {
/*     */   public EntityCultist(World p_i1745_1_) {
/*  24 */     super(p_i1745_1_);
/*  25 */     func_70105_a(0.6F, 1.8F);
/*  26 */     this.field_70728_aV = 10;
/*  27 */     ((PathNavigateGround)func_70661_as()).func_179688_b(true);
/*     */     
/*  29 */     func_184642_a(EntityEquipmentSlot.CHEST, 0.05F);
/*  30 */     func_184642_a(EntityEquipmentSlot.FEET, 0.05F);
/*  31 */     func_184642_a(EntityEquipmentSlot.HEAD, 0.05F);
/*  32 */     func_184642_a(EntityEquipmentSlot.LEGS, 0.05F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/*  39 */     super.func_110147_ax();
/*  40 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
/*  41 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*  42 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   protected void func_70088_a() { super.func_70088_a(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public boolean func_98052_bS() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected boolean func_70814_o() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public static final ResourceLocation LOOT = LootTableList.func_186375_a(new ResourceLocation("thaumcraft", "cultist"));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected ResourceLocation func_184647_J() { return LOOT; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setLoot(DifficultyInstance diff) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_180483_b(DifficultyInstance diff) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance diff, IEntityLivingData data) {
/*  98 */     setLoot(diff);
/*  99 */     func_180483_b(diff);
/*     */     
/* 101 */     return super.func_180482_a(diff, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   protected boolean func_70692_ba() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 119 */     super.func_70037_a(nbt);
/* 120 */     if (nbt.func_74764_b("HomeD")) {
/* 121 */       func_175449_a(new BlockPos(nbt.func_74762_e("HomeX"), nbt.func_74762_e("HomeY"), nbt.func_74762_e("HomeZ")), nbt.func_74762_e("HomeD"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 128 */     super.func_70014_b(nbt);
/* 129 */     if (func_180486_cf() != null && func_110174_bM() > 0.0F) {
/* 130 */       nbt.func_74768_a("HomeD", (int)func_110174_bM());
/* 131 */       nbt.func_74768_a("HomeX", func_180486_cf().func_177958_n());
/* 132 */       nbt.func_74768_a("HomeY", func_180486_cf().func_177956_o());
/* 133 */       nbt.func_74768_a("HomeZ", func_180486_cf().func_177952_p());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public boolean func_184191_r(Entity el) { return (el instanceof EntityCultist || el instanceof thaumcraft.common.entities.monster.boss.EntityCultistLeader); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70686_a(Class clazz) {
/* 146 */     if (clazz == EntityCultistCleric.class || clazz == thaumcraft.common.entities.monster.boss.EntityCultistLeader.class || clazz == EntityCultistKnight.class)
/*     */     {
/*     */       
/* 149 */       return false; } 
/* 150 */     return super.func_70686_a(clazz);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70656_aK() {
/* 155 */     if (this.field_70170_p.field_72995_K) {
/* 156 */       for (int i = 0; i < 20; i++) {
/*     */         
/* 158 */         double d0 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 159 */         double d1 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 160 */         double d2 = this.field_70146_Z.nextGaussian() * 0.05D;
/* 161 */         double d3 = 2.0D;
/*     */         
/* 163 */         FXDispatcher.INSTANCE.cultistSpawn(this.field_70165_t + (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N + d0 * d3, this.field_70163_u + (this.field_70146_Z
/* 164 */             .nextFloat() * this.field_70131_O) + d1 * d3, this.field_70161_v + (this.field_70146_Z
/* 165 */             .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N + d2 * d3, d0, d1, d2);
/*     */       } 
/*     */     } else {
/*     */       
/* 169 */       this.field_70170_p.func_72960_a(this, (byte)20);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\cult\EntityCultist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */