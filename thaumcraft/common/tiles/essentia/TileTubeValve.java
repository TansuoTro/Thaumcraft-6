/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ 
/*     */ public class TileTubeValve
/*     */   extends TileTube
/*     */ {
/*     */   public boolean allowFlow = true;
/*     */   boolean wasPoweredLastTick = false;
/*  23 */   public float rotation = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  29 */     if (!this.field_145850_b.field_72995_K && this.count % 5 == 0) {
/*  30 */       boolean gettingPower = gettingPower();
/*  31 */       if (this.wasPoweredLastTick && !gettingPower && 
/*  32 */         this.allowFlow != true) {
/*  33 */         this.allowFlow = true;
/*  34 */         this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundsTC.squeek, SoundCategory.BLOCKS, 0.7F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F);
/*  35 */         syncTile(true);
/*  36 */         func_70296_d();
/*     */       } 
/*     */ 
/*     */       
/*  40 */       if (!this.wasPoweredLastTick && gettingPower && 
/*  41 */         this.allowFlow) {
/*  42 */         this.allowFlow = false;
/*  43 */         this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundsTC.squeek, SoundCategory.BLOCKS, 0.7F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F);
/*  44 */         syncTile(true);
/*  45 */         func_70296_d();
/*     */       } 
/*     */ 
/*     */       
/*  49 */       this.wasPoweredLastTick = gettingPower;
/*     */     } 
/*     */     
/*  52 */     if (this.field_145850_b.field_72995_K) {
/*  53 */       if (!this.allowFlow && this.rotation < 360.0F) {
/*  54 */         this.rotation += 20.0F;
/*     */       }
/*  56 */       else if (this.allowFlow && this.rotation > 0.0F) {
/*  57 */         this.rotation -= 20.0F;
/*     */       } 
/*     */     }
/*     */     
/*  61 */     super.func_73660_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCasterRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos bp, EnumFacing side, EnumHand hand) {
/*  68 */     RayTraceResult hit = RayTracer.retraceBlock(world, player, this.field_174879_c);
/*  69 */     if (hit == null) return false;
/*     */     
/*  71 */     if (hit.subHit >= 0 && hit.subHit < 6) {
/*     */       
/*  73 */       player.field_70170_p.func_184134_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), SoundsTC.tool, SoundCategory.BLOCKS, 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*  74 */       player.func_184609_a(hand);
/*  75 */       func_70296_d();
/*  76 */       syncTile(true);
/*  77 */       this.openSides[hit.subHit] = !this.openSides[hit.subHit];
/*  78 */       EnumFacing dir = EnumFacing.field_82609_l[hit.subHit];
/*  79 */       TileEntity tile = world.func_175625_s(this.field_174879_c.func_177972_a(dir));
/*  80 */       if (tile != null && tile instanceof TileTube) {
/*  81 */         ((TileTube)tile).openSides[dir.func_176734_d().ordinal()] = this.openSides[hit.subHit];
/*  82 */         syncTile(true);
/*  83 */         tile.func_70296_d();
/*     */       } 
/*  85 */       return true;
/*     */     } 
/*     */     
/*  88 */     if (hit.subHit == 6) {
/*     */       
/*  90 */       player.field_70170_p.func_184134_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), SoundsTC.tool, SoundCategory.BLOCKS, 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*  91 */       player.func_184609_a(hand);
/*  92 */       int a = this.facing.ordinal();
/*  93 */       func_70296_d();
/*  94 */       while (++a < 20) {
/*  95 */         if (!canConnectSide(EnumFacing.field_82609_l[a % 6])) {
/*  96 */           a %= 6;
/*  97 */           this.facing = EnumFacing.field_82609_l[a];
/*  98 */           syncTile(true);
/*  99 */           func_70296_d();
/*     */           break;
/*     */         } 
/*     */       } 
/* 103 */       return true;
/*     */     } 
/*     */     
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/* 112 */     super.readSyncNBT(nbttagcompound);
/* 113 */     this.allowFlow = nbttagcompound.func_74767_n("flow");
/* 114 */     this.wasPoweredLastTick = nbttagcompound.func_74767_n("hadpower");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/* 120 */     nbttagcompound = super.writeSyncNBT(nbttagcompound);
/* 121 */     nbttagcompound.func_74757_a("flow", this.allowFlow);
/* 122 */     nbttagcompound.func_74757_a("hadpower", this.wasPoweredLastTick);
/* 123 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public boolean isConnectable(EnumFacing face) { return (face != this.facing && super.isConnectable(face)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public void setSuction(Aspect aspect, int amount) { if (this.allowFlow) super.setSuction(aspect, amount);
/*     */      }
/*     */ 
/*     */   
/* 137 */   public boolean gettingPower() { return (this.field_145850_b.func_175687_A(this.field_174879_c) > 0); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileTubeValve.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */