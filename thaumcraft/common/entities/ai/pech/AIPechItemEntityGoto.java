/*     */ package thaumcraft.common.entities.ai.pech;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ 
/*     */ public class AIPechItemEntityGoto
/*     */   extends EntityAIBase {
/*     */   private EntityPech pech;
/*     */   private Entity targetEntity;
/*     */   
/*     */   public AIPechItemEntityGoto(EntityPech par1EntityCreature) {
/*  21 */     this.maxTargetDistance = 16.0F;
/*     */ 
/*     */ 
/*     */     
/*  25 */     this.pech = par1EntityCreature;
/*  26 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */   float maxTargetDistance;
/*     */   private int count;
/*     */   private int failedPathFindingPenalty;
/*     */   
/*     */   public boolean func_75250_a() {
/*  34 */     if (--this.count > 0) return false;
/*     */     
/*  36 */     double range = Double.MAX_VALUE;
/*     */ 
/*     */     
/*  39 */     List<Entity> targets = this.pech.field_70170_p.func_72839_b(this.pech, this.pech
/*  40 */         .func_174813_aQ().func_72314_b(this.maxTargetDistance, this.maxTargetDistance, this.maxTargetDistance));
/*     */     
/*  42 */     if (targets.size() == 0) return false; 
/*  43 */     for (Entity e : targets) {
/*  44 */       if (e instanceof EntityItem && this.pech.canPickup(((EntityItem)e).func_92059_d())) {
/*     */         
/*  46 */         NBTTagCompound itemData = ((EntityItem)e).getEntityData();
/*  47 */         String username = ((EntityItem)e).func_145800_j();
/*  48 */         if (username != null && username.equals("PechDrop"))
/*     */           continue; 
/*  50 */         double distance = e.func_70092_e(this.pech.field_70165_t, this.pech.field_70163_u, this.pech.field_70161_v);
/*  51 */         if (distance < range && distance <= (this.maxTargetDistance * this.maxTargetDistance)) {
/*  52 */           range = distance;
/*  53 */           this.targetEntity = e;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  58 */     if (this.targetEntity == null)
/*     */     {
/*  60 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  75 */     return (this.targetEntity == null) ? false : (!this.targetEntity.func_70089_S() ? false : ((
/*  76 */       !this.pech.func_70661_as().func_75500_f() && this.targetEntity
/*  77 */       .func_70068_e(this.pech) < (this.maxTargetDistance * this.maxTargetDistance))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void func_75251_c() { this.targetEntity = null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  96 */     this.pech.func_70661_as().func_75484_a(this.pech.func_70661_as().func_75494_a(this.targetEntity), this.pech
/*  97 */         .func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 1.5D);
/*  98 */     this.count = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/* 103 */     this.pech.func_70671_ap().func_75651_a(this.targetEntity, 30.0F, 30.0F);
/*     */     
/* 105 */     if (this.pech.func_70635_at().func_75522_a(this.targetEntity) && --this.count <= 0) {
/*     */       
/* 107 */       this.count = this.failedPathFindingPenalty + 4 + this.pech.func_70681_au().nextInt(4);
/* 108 */       this.pech.func_70661_as().func_75497_a(this.targetEntity, this.pech.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 1.5D);
/* 109 */       if (this.pech.func_70661_as().func_75505_d() != null) {
/*     */         
/* 111 */         PathPoint finalPathPoint = this.pech.func_70661_as().func_75505_d().func_75870_c();
/* 112 */         if (finalPathPoint != null && this.targetEntity.func_70092_e(finalPathPoint.field_75839_a, finalPathPoint.field_75837_b, finalPathPoint.field_75838_c) < 1.0D)
/*     */         {
/* 114 */           this.failedPathFindingPenalty = 0;
/*     */         }
/*     */         else
/*     */         {
/* 118 */           this.failedPathFindingPenalty += 10;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 123 */         this.failedPathFindingPenalty += 10;
/*     */       } 
/*     */     } 
/* 126 */     double distance = this.pech.func_70092_e(this.targetEntity.field_70165_t, 
/*     */         
/* 128 */         (this.targetEntity.func_174813_aQ()).field_72338_b, this.targetEntity.field_70161_v);
/*     */     
/* 130 */     if (distance <= 1.5D) {
/*     */       
/* 132 */       this.count = 0;
/*     */       
/* 134 */       int am = ((EntityItem)this.targetEntity).func_92059_d().func_190916_E();
/* 135 */       ItemStack is = this.pech.pickupItem(((EntityItem)this.targetEntity).func_92059_d());
/* 136 */       if (is != null && !is.func_190926_b() && is.func_190916_E() > 0) {
/* 137 */         ((EntityItem)this.targetEntity).func_92058_a(is);
/*     */       } else {
/* 139 */         this.targetEntity.func_70106_y();
/*     */       } 
/* 141 */       if (is == null || is.func_190926_b() || is.func_190916_E() != am)
/* 142 */         this.targetEntity.field_70170_p.func_184148_a(null, this.targetEntity.field_70165_t, this.targetEntity.field_70163_u, this.targetEntity.field_70161_v, SoundEvents.field_187638_cR, SoundCategory.NEUTRAL, 0.2F, ((this.targetEntity.field_70170_p.field_73012_v
/* 143 */             .nextFloat() - this.targetEntity.field_70170_p.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 2.0F); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\ai\pech\AIPechItemEntityGoto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */