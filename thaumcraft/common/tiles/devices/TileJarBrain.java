/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.essentia.TileJar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileJarBrain
/*     */   extends TileJar
/*     */ {
/*     */   public float field_40063_b;
/*     */   public float field_40061_d;
/*     */   public float field_40059_f;
/*     */   public float field_40066_q;
/*     */   public float rota;
/*     */   public float rotb;
/*  27 */   public int xp = 0;
/*  28 */   public int xpMax = 2000;
/*  29 */   public int eatDelay = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public void readSyncNBT(NBTTagCompound nbttagcompound) { this.xp = nbttagcompound.func_74762_e("XP"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  40 */     nbttagcompound.func_74768_a("XP", this.xp);
/*  41 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*  45 */   long lastsigh = System.currentTimeMillis() + 1500L;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  49 */     Entity entity = null;
/*  50 */     if (this.xp > this.xpMax) this.xp = this.xpMax; 
/*  51 */     if (this.xp < this.xpMax) {
/*  52 */       entity = getClosestXPOrb();
/*     */       
/*  54 */       if (entity != null && this.eatDelay == 0) {
/*  55 */         double var3 = (this.field_174879_c.func_177958_n() + 0.5D - entity.field_70165_t) / 25.0D;
/*  56 */         double var5 = (this.field_174879_c.func_177956_o() + 0.5D - entity.field_70163_u) / 25.0D;
/*  57 */         double var7 = (this.field_174879_c.func_177952_p() + 0.5D - entity.field_70161_v) / 25.0D;
/*  58 */         double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
/*  59 */         double var11 = 1.0D - var9;
/*     */         
/*  61 */         if (var11 > 0.0D) {
/*     */           
/*  63 */           var11 *= var11;
/*  64 */           entity.field_70159_w += var3 / var9 * var11 * 0.3D;
/*  65 */           entity.field_70181_x += var5 / var9 * var11 * 0.5D;
/*  66 */           entity.field_70179_y += var7 / var9 * var11 * 0.3D;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  71 */     if (this.field_145850_b.field_72995_K) {
/*     */       EntityPlayer entityPlayer;
/*  73 */       this.rotb = this.rota;
/*  74 */       if (entity == null) {
/*  75 */         entityPlayer = this.field_145850_b.func_184137_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, 6.0D, false);
/*  76 */         if (entityPlayer != null && this.lastsigh < System.currentTimeMillis()) {
/*  77 */           this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, SoundsTC.brain, SoundCategory.AMBIENT, 0.15F, 0.8F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F, false);
/*  78 */           this.lastsigh = System.currentTimeMillis() + 5000L + this.field_145850_b.field_73012_v.nextInt(25000);
/*     */         } 
/*     */       } 
/*     */       
/*  82 */       if (entityPlayer != null) {
/*     */         
/*  84 */         double d = entityPlayer.field_70165_t - (this.field_174879_c.func_177958_n() + 0.5F);
/*  85 */         double d1 = entityPlayer.field_70161_v - (this.field_174879_c.func_177952_p() + 0.5F);
/*  86 */         this.field_40066_q = (float)Math.atan2(d1, d);
/*  87 */         this.field_40059_f += 0.1F;
/*  88 */         if (this.field_40059_f < 0.5F || rand.nextInt(40) == 0)
/*     */         {
/*  90 */           float f3 = this.field_40061_d;
/*     */           
/*     */           do {
/*  93 */             this.field_40061_d += (rand.nextInt(4) - rand.nextInt(4));
/*     */           }
/*  95 */           while (f3 == this.field_40061_d);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 100 */         this.field_40066_q += 0.01F;
/*     */       } 
/* 102 */       for (; this.rota >= 3.141593F; this.rota -= 6.283185F);
/* 103 */       for (; this.rota < -3.141593F; this.rota += 6.283185F);
/* 104 */       for (; this.field_40066_q >= 3.141593F; this.field_40066_q -= 6.283185F);
/* 105 */       for (; this.field_40066_q < -3.141593F; this.field_40066_q += 6.283185F);
/*     */       float f;
/* 107 */       for (f = this.field_40066_q - this.rota; f >= 3.141593F; f -= 6.283185F);
/* 108 */       for (; f < -3.141593F; f += 6.283185F);
/* 109 */       this.rota += f * 0.04F;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if (this.eatDelay > 0) {
/* 114 */       this.eatDelay--;
/* 115 */     } else if (this.xp < this.xpMax) {
/* 116 */       List ents = this.field_145850_b.func_72872_a(EntityXPOrb.class, new AxisAlignedBB(this.field_174879_c
/*     */             
/* 118 */             .func_177958_n() - 0.1D, this.field_174879_c.func_177956_o() - 0.1D, this.field_174879_c.func_177952_p() - 0.1D, this.field_174879_c
/* 119 */             .func_177958_n() + 1.1D, this.field_174879_c
/* 120 */             .func_177956_o() + 1.1D, this.field_174879_c
/* 121 */             .func_177952_p() + 1.1D));
/* 122 */       if (ents.size() > 0) {
/* 123 */         for (Object ent : ents) {
/* 124 */           EntityXPOrb eo = (EntityXPOrb)ent;
/* 125 */           this.xp += eo.func_70526_d();
/* 126 */           eo.func_184185_a(SoundEvents.field_187537_bA, 0.1F, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/* 127 */           eo.func_70106_y();
/*     */         } 
/* 129 */         syncTile(false);
/* 130 */         func_70296_d();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getClosestXPOrb() {
/* 138 */     double cdist = Double.MAX_VALUE;
/* 139 */     EntityXPOrb entityXPOrb = null;
/*     */     
/* 141 */     List ents = this.field_145850_b.func_72872_a(EntityXPOrb.class, (new AxisAlignedBB(this.field_174879_c
/*     */           
/* 143 */           .func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), (this.field_174879_c
/* 144 */           .func_177958_n() + 1), (this.field_174879_c
/* 145 */           .func_177956_o() + 1), (this.field_174879_c
/* 146 */           .func_177952_p() + 1))).func_72314_b(8.0D, 8.0D, 8.0D));
/* 147 */     if (ents.size() > 0) {
/* 148 */       for (Object ent : ents) {
/* 149 */         EntityXPOrb eo = (EntityXPOrb)ent;
/* 150 */         double d = func_145835_a(eo.field_70165_t, eo.field_70163_u, eo.field_70161_v);
/* 151 */         if (d < cdist) {
/* 152 */           entityXPOrb = eo;
/* 153 */           cdist = d;
/*     */         } 
/*     */       } 
/*     */     }
/* 157 */     return entityXPOrb;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileJarBrain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */