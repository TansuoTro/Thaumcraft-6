/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Rotation;
/*     */ import thaumcraft.codechicken.lib.vec.Transformation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileRedstoneRelay
/*     */   extends TileThaumcraft
/*     */ {
/*  22 */   private int in = 1;
/*  23 */   private int out = 15;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbt) {
/*  29 */     setIn(nbt.func_74771_c("in"));
/*  30 */     setOut(nbt.func_74771_c("out"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
/*  36 */     nbt.func_74774_a("in", (byte)getIn());
/*  37 */     nbt.func_74774_a("out", (byte)getOut());
/*  38 */     return nbt;
/*     */   }
/*     */   
/*     */   public void increaseIn() {
/*  42 */     if (!this.field_145850_b.field_72995_K) {
/*  43 */       setIn(getIn() + 1);
/*  44 */       if (getIn() > 15) setIn(1); 
/*  45 */       func_70296_d();
/*  46 */       syncTile(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void increaseOut() {
/*  51 */     if (!this.field_145850_b.field_72995_K) {
/*  52 */       setOut(getOut() + 1);
/*  53 */       if (getOut() > 15) setOut(1); 
/*  54 */       func_70296_d();
/*  55 */       syncTile(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public RayTraceResult rayTrace(World world, Vec3d vec3d, Vec3d vec3d1, RayTraceResult fullblock) { return fullblock; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTraceableCuboids(List<IndexedCuboid6> cuboids) {
/*  67 */     EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/*     */     
/*  69 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(0), getCuboid0(facing)));
/*  70 */     cuboids.add(new IndexedCuboid6(Integer.valueOf(1), getCuboid1(facing)));
/*     */   }
/*     */   
/*     */   public Cuboid6 getCuboid0(EnumFacing facing) {
/*  74 */     Transformation rot = Rotation.quarterRotations[0];
/*  75 */     switch (facing) { case WEST:
/*  76 */         rot = Rotation.quarterRotations[1]; break;
/*  77 */       case NORTH: rot = Rotation.quarterRotations[2]; break;
/*  78 */       case EAST: rot = Rotation.quarterRotations[3]; break; }
/*     */     
/*  80 */     return (new Cuboid6(-0.375D, 0.0625D, -0.375D, -0.125D, 0.25D, -0.125D))
/*  81 */       .apply(rot).add(new Vector3(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o(), func_174877_v().func_177952_p() + 0.5D));
/*     */   }
/*     */   
/*     */   public Cuboid6 getCuboid1(EnumFacing facing) {
/*  85 */     Transformation rot = Rotation.quarterRotations[0];
/*  86 */     switch (facing) { case WEST:
/*  87 */         rot = Rotation.quarterRotations[1]; break;
/*  88 */       case NORTH: rot = Rotation.quarterRotations[2]; break;
/*  89 */       case EAST: rot = Rotation.quarterRotations[3]; break; }
/*     */     
/*  91 */     return (new Cuboid6(-0.125D, 0.0625D, 0.125D, 0.125D, 0.25D, 0.375D))
/*  92 */       .apply(rot).add(new Vector3(func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o(), func_174877_v().func_177952_p() + 0.5D));
/*     */   }
/*     */ 
/*     */   
/*  96 */   public int getOut() { return this.out; }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public void setOut(int out) { this.out = out; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public int getIn() { return this.in; }
/*     */ 
/*     */ 
/*     */   
/* 108 */   public void setIn(int in) { this.in = in; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileRedstoneRelay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */