/*     */ package thaumcraft.common.tiles.misc;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.items.casters.foci.FocusEffectRift;
/*     */ 
/*     */ public class TileHole
/*     */   extends TileMemory
/*     */   implements ITickable
/*     */ {
/*  15 */   public short countdown = 0;
/*  16 */   public short countdownmax = 120;
/*  17 */   public byte count = 0;
/*  18 */   public EnumFacing direction = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public TileHole() {}
/*     */ 
/*     */   
/*     */   public TileHole(IBlockState bi, short max, byte count, EnumFacing direction) {
/*  26 */     super(bi);
/*  27 */     this.count = count;
/*  28 */     this.countdownmax = max;
/*  29 */     this.direction = direction;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  34 */   public TileHole(byte count) { this.count = count; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  41 */     if (this.field_145850_b.field_72995_K) {
/*     */       
/*  43 */       for (int a = 0; a < 2; a++) {
/*  44 */         surroundwithsparkles();
/*     */       }
/*     */     } else {
/*  47 */       if (this.countdown == 0 && this.count > 1 && this.direction != null) {
/*  48 */         int a; int a; int a; switch (this.direction.func_176740_k()) {
/*     */           case Y:
/*  50 */             for (a = 0; a < 9; ) { if (a / 3 != 1 || a % 3 != 1)
/*  51 */                 FocusEffectRift.createHole(this.field_145850_b, 
/*  52 */                     func_174877_v().func_177982_a(-1 + a / 3, 0, -1 + a % 3), null, (byte)1, this.countdownmax);  a++; }
/*     */              break;
/*     */           case Z:
/*  55 */             for (a = 0; a < 9; ) { if (a / 3 != 1 || a % 3 != 1)
/*  56 */                 FocusEffectRift.createHole(this.field_145850_b, 
/*  57 */                     func_174877_v().func_177982_a(-1 + a / 3, -1 + a % 3, 0), null, (byte)1, this.countdownmax);  a++; }
/*     */              break;
/*     */           case X:
/*  60 */             for (a = 0; a < 9; ) { if (a / 3 != 1 || a % 3 != 1)
/*  61 */                 FocusEffectRift.createHole(this.field_145850_b, 
/*  62 */                     func_174877_v().func_177982_a(0, -1 + a / 3, -1 + a % 3), null, (byte)1, this.countdownmax);  a++; }
/*     */             
/*     */             break;
/*     */         } 
/*  66 */         if (!FocusEffectRift.createHole(this.field_145850_b, func_174877_v().func_177972_a(this.direction.func_176734_d()), this.direction, (byte)(this.count - 1), this.countdownmax))
/*     */         {
/*     */           
/*  69 */           this.count = 0;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  74 */       this.countdown = (short)(this.countdown + 1);
/*     */       
/*  76 */       if (this.countdown % 20 == 0) func_70296_d();
/*     */       
/*  78 */       if (this.countdown >= this.countdownmax) {
/*  79 */         this.field_145850_b.func_180501_a(func_174877_v(), this.oldblock, 3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void surroundwithsparkles() {
/*  86 */     for (EnumFacing d1 : EnumFacing.values()) {
/*  87 */       IBlockState b1 = this.field_145850_b.func_180495_p(func_174877_v().func_177972_a(d1));
/*  88 */       if (b1.func_177230_c() != BlocksTC.hole && !b1.func_185914_p()) {
/*  89 */         for (EnumFacing d2 : EnumFacing.values()) {
/*  90 */           if (d1.func_176740_k() != d2.func_176740_k() && (
/*  91 */             this.field_145850_b.func_180495_p(func_174877_v().func_177972_a(d2)).func_185914_p() || this.field_145850_b
/*  92 */             .func_180495_p(func_174877_v().func_177972_a(d1).func_177972_a(d2)).func_185914_p())) {
/*     */             
/*  94 */             float sx = 0.5F * d1.func_82601_c();
/*  95 */             float sy = 0.5F * d1.func_96559_d();
/*  96 */             float sz = 0.5F * d1.func_82599_e();
/*     */             
/*  98 */             if (sx == 0.0F) sx = 0.5F * d2.func_82601_c(); 
/*  99 */             if (sy == 0.0F) sy = 0.5F * d2.func_96559_d(); 
/* 100 */             if (sz == 0.0F) sz = 0.5F * d2.func_82599_e();
/*     */             
/* 102 */             if (sx == 0.0F) { sx = this.field_145850_b.field_73012_v.nextFloat(); } else { sx += 0.5F; }
/* 103 */              if (sy == 0.0F) { sy = this.field_145850_b.field_73012_v.nextFloat(); } else { sy += 0.5F; }
/* 104 */              if (sz == 0.0F) { sz = this.field_145850_b.field_73012_v.nextFloat(); } else { sz += 0.5F; }
/*     */             
/* 106 */             FXDispatcher.INSTANCE.sparkle(
/* 107 */                 func_174877_v().func_177958_n() + sx, func_174877_v().func_177956_o() + sy, func_174877_v().func_177952_p() + sz, 0.25F, 0.25F, 1.0F);
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
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 120 */     super.func_145839_a(nbttagcompound);
/* 121 */     this.countdown = nbttagcompound.func_74765_d("countdown");
/* 122 */     this.countdownmax = nbttagcompound.func_74765_d("countdownmax");
/* 123 */     this.count = nbttagcompound.func_74771_c("count");
/* 124 */     byte db = nbttagcompound.func_74771_c("direction");
/* 125 */     this.direction = (db >= 0) ? EnumFacing.values()[db] : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbttagcompound) {
/* 131 */     super.func_189515_b(nbttagcompound);
/* 132 */     nbttagcompound.func_74777_a("countdown", this.countdown);
/* 133 */     nbttagcompound.func_74777_a("countdownmax", this.countdownmax);
/* 134 */     nbttagcompound.func_74774_a("count", this.count);
/* 135 */     nbttagcompound.func_74774_a("direction", (this.direction == null) ? -1 : (byte)this.direction.ordinal());
/* 136 */     return nbttagcompound;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\misc\TileHole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */