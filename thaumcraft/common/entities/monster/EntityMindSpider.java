/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ 
/*     */ public class EntityMindSpider
/*     */   extends EntitySpider
/*     */   implements IEldritchMob {
/*     */   private int lifeSpan;
/*     */   
/*     */   public EntityMindSpider(World par1World) {
/*  25 */     super(par1World);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.lifeSpan = Integer.MAX_VALUE;
/*     */     func_70105_a(0.7F, 0.5F);
/*     */     this.field_70728_aV = 1;
/*     */   }
/*  46 */   protected int func_70693_a(EntityPlayer p_70693_1_) { return isHarmless() ? 0 : super.func_70693_a(p_70693_1_); }
/*     */   
/*     */   @Nullable
/*     */   protected ResourceLocation func_184647_J() { return null; }
/*     */   
/*     */   protected void func_110147_ax() {
/*  52 */     super.func_110147_ax();
/*  53 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
/*  54 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
/*     */   }
/*     */   public float func_70047_e() { return 0.45F; }
/*  57 */   private static final DataParameter<Boolean> HARMLESS = EntityDataManager.func_187226_a(EntityMindSpider.class, DataSerializers.field_187198_h);
/*  58 */   private static final DataParameter<String> VIEWER = EntityDataManager.func_187226_a(EntityMindSpider.class, DataSerializers.field_187194_d);
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  63 */     super.func_70088_a();
/*  64 */     func_184212_Q().func_187214_a(HARMLESS, Boolean.valueOf(false));
/*  65 */     func_184212_Q().func_187214_a(VIEWER, String.valueOf(""));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public String getViewer() { return (String)func_184212_Q().func_187225_a(VIEWER); }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public void setViewer(String player) { func_184212_Q().func_187227_b(VIEWER, String.valueOf(player)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public boolean isHarmless() { return ((Boolean)func_184212_Q().func_187225_a(HARMLESS)).booleanValue(); }
/*     */ 
/*     */   
/*     */   public void setHarmless(boolean h) {
/*  83 */     if (h) this.lifeSpan = 1200; 
/*  84 */     func_184212_Q().func_187227_b(HARMLESS, Boolean.valueOf(h));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  89 */   protected float func_70647_i() { return 0.7F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  96 */     super.func_70071_h_();
/*  97 */     if (!this.field_70170_p.field_72995_K && this.field_70173_aa > this.lifeSpan) func_70106_y();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   protected Item func_146068_u() { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public boolean func_145773_az() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   protected boolean func_70041_e_() { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70652_k(Entity p_70652_1_) {
/* 127 */     if (isHarmless()) {
/* 128 */       return false;
/*     */     }
/* 130 */     return super.func_70652_k(p_70652_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 136 */     super.func_70037_a(nbt);
/* 137 */     setHarmless(nbt.func_74767_n("harmless"));
/* 138 */     setViewer(nbt.func_74779_i("viewer"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 144 */     super.func_70014_b(nbt);
/* 145 */     nbt.func_74757_a("harmless", isHarmless());
/* 146 */     nbt.func_74778_a("viewer", getViewer());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) { return p_180482_2_; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityMindSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */