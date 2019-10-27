/*     */ package thaumcraft.common.golems.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathNodeType;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.construct.EntityOwnedConstruct;
/*     */ 
/*     */ 
/*     */ public class AIFollowOwner
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityOwnedConstruct thePet;
/*     */   private EntityLivingBase theOwner;
/*     */   World theWorld;
/*     */   private double field_75336_f;
/*     */   private PathNavigate petPathfinder;
/*     */   private int field_75343_h;
/*     */   float maxDist;
/*     */   float minDist;
/*     */   private float field_75344_i;
/*     */   
/*     */   public AIFollowOwner(EntityOwnedConstruct p_i1625_1_, double p_i1625_2_, float p_i1625_4_, float p_i1625_5_) {
/*  30 */     this.thePet = p_i1625_1_;
/*  31 */     this.theWorld = p_i1625_1_.field_70170_p;
/*  32 */     this.field_75336_f = p_i1625_2_;
/*  33 */     this.petPathfinder = p_i1625_1_.func_70661_as();
/*  34 */     this.minDist = p_i1625_4_;
/*  35 */     this.maxDist = p_i1625_5_;
/*  36 */     func_75248_a(3);
/*     */     
/*  38 */     if (!(p_i1625_1_.func_70661_as() instanceof net.minecraft.pathfinding.PathNavigateGround) && !(p_i1625_1_.func_70661_as() instanceof PathNavigateGolemAir))
/*     */     {
/*  40 */       throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  46 */     EntityLivingBase entitylivingbase = this.thePet.getOwnerEntity();
/*     */     
/*  48 */     if (entitylivingbase == null)
/*     */     {
/*  50 */       return false;
/*     */     }
/*  52 */     if (this.thePet.func_70068_e(entitylivingbase) < (this.minDist * this.minDist))
/*     */     {
/*  54 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  58 */     this.theOwner = entitylivingbase;
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public boolean func_75253_b() { return (!this.petPathfinder.func_75500_f() && this.thePet.func_70068_e(this.theOwner) > (this.maxDist * this.maxDist)); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  70 */     this.field_75343_h = 0;
/*  71 */     this.field_75344_i = this.thePet.func_184643_a(PathNodeType.WATER);
/*  72 */     this.thePet.func_184644_a(PathNodeType.WATER, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/*  77 */     this.theOwner = null;
/*  78 */     this.petPathfinder.func_75499_g();
/*  79 */     this.thePet.func_184644_a(PathNodeType.WATER, this.field_75344_i);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_181065_a(BlockPos p_181065_1_) {
/*  84 */     IBlockState iblockstate = this.theWorld.func_180495_p(p_181065_1_);
/*  85 */     Block block = iblockstate.func_177230_c();
/*  86 */     return (block == Blocks.field_150350_a) ? true : (!iblockstate.func_185917_h());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  91 */     this.thePet.func_70671_ap().func_75651_a(this.theOwner, 10.0F, this.thePet.func_70646_bf());
/*     */     
/*  93 */     if (--this.field_75343_h <= 0) {
/*     */       
/*  95 */       this.field_75343_h = 10;
/*     */       
/*  97 */       if (!this.petPathfinder.func_75497_a(this.theOwner, this.field_75336_f))
/*     */       {
/*  99 */         if (!this.thePet.func_110167_bD())
/*     */         {
/* 101 */           if (this.thePet.func_70068_e(this.theOwner) >= 144.0D) {
/*     */             
/* 103 */             int i = MathHelper.func_76128_c(this.theOwner.field_70165_t) - 2;
/* 104 */             int j = MathHelper.func_76128_c(this.theOwner.field_70161_v) - 2;
/* 105 */             int k = MathHelper.func_76128_c((this.theOwner.func_174813_aQ()).field_72338_b);
/*     */             
/* 107 */             for (int l = 0; l <= 4; l++) {
/*     */               
/* 109 */               for (int i1 = 0; i1 <= 4; i1++) {
/*     */                 
/* 111 */                 if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.theWorld.func_180495_p(new BlockPos(i + l, k - 1, j + i1)).func_185917_h() && 
/* 112 */                   func_181065_a(new BlockPos(i + l, k, j + i1)) && func_181065_a(new BlockPos(i + l, k + 1, j + i1))) {
/*     */                   
/* 114 */                   this.thePet.func_70012_b(((i + l) + 0.5F), k, ((j + i1) + 0.5F), this.thePet.field_70177_z, this.thePet.field_70125_A);
/*     */                   
/* 116 */                   this.petPathfinder.func_75499_g();
/*     */                   return;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\AIFollowOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */