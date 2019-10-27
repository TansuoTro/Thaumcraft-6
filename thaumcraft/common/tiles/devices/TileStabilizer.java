/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.entities.EntityFluxRift;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileStabilizer
/*     */   extends TileThaumcraft
/*     */   implements ITickable
/*     */ {
/*  22 */   private int ticks = 0;
/*  23 */   private int delay = 0;
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  28 */     return new AxisAlignedBB(
/*  29 */         func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/*  30 */         func_174877_v().func_177958_n() + 1), func_174877_v().func_177956_o() + 1.5D, (func_174877_v().func_177952_p() + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  37 */     if (!this.field_145850_b.field_72995_K) {
/*  38 */       this.ticks++;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  54 */       getClass(); if (this.energy < 15 && this.ticks % 20 == 0) {
/*  55 */         this.energy++;
/*  56 */         AuraHelper.polluteAura(func_145831_w(), func_174877_v(), 0.25F, true);
/*  57 */         func_70296_d();
/*  58 */         syncTile(false);
/*  59 */         this.field_145850_b.func_175685_c(func_174877_v(), this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c(), false);
/*     */       } 
/*     */       
/*  62 */       if (this.energy > 0 && this.delay <= 0 && this.ticks % 5 == 0) {
/*  63 */         int q = this.energy;
/*  64 */         tryAddStability();
/*  65 */         if (q != this.energy) {
/*  66 */           func_70296_d();
/*  67 */           syncTile(false);
/*     */         } 
/*     */       } 
/*     */       
/*  71 */       if (this.delay > 0) this.delay--; 
/*     */     } 
/*  73 */     if (this.field_145850_b.field_72995_K && this.energy != this.lastEnergy) {
/*  74 */       this.field_145850_b.func_175704_b(func_174877_v(), func_174877_v());
/*  75 */       this.lastEnergy = this.energy;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  80 */   int lastEnergy = 0;
/*     */   
/*     */   private void tryAddStability() {
/*  83 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/*  84 */     List<EntityFluxRift> targets = this.field_145850_b.func_72872_a(EntityFluxRift.class, (new AxisAlignedBB(this.field_174879_c
/*  85 */           .func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), (this.field_174879_c.func_177958_n() + 1), (this.field_174879_c.func_177956_o() + 1), (this.field_174879_c.func_177952_p() + 1))).func_186662_g(8.0D));
/*  86 */     if (targets.size() > 0) {
/*  87 */       for (EntityFluxRift e : targets) {
/*  88 */         if (e.field_70128_L)
/*     */           continue; 
/*  90 */         if (e.getStability() != EntityFluxRift.EnumStability.VERY_STABLE && 
/*  91 */           mitigate(1)) {
/*  92 */           e.addStability();
/*  93 */           this.delay += 5;
/*     */ 
/*     */ 
/*     */           
/*  97 */           if (this.energy <= 0) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void readSyncNBT(NBTTagCompound nbt) { this.energy = Math.min(nbt.func_74762_e("energy"), 15); }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
/* 131 */     nbt.func_74768_a("energy", this.energy);
/* 132 */     return nbt;
/*     */   }
/*     */   
/* 135 */   protected int energy = 0;
/* 136 */   protected final int capacity = 15;
/*     */ 
/*     */   
/* 139 */   public int getEnergy() { return this.energy; }
/*     */ 
/*     */   
/*     */   public boolean mitigate(int e) {
/* 143 */     if (this.energy >= e) {
/* 144 */       this.energy -= e;
/* 145 */       this.field_145850_b.func_175685_c(func_174877_v(), this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c(), false);
/* 146 */       return true;
/*     */     } 
/* 148 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileStabilizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */