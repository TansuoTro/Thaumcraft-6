/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class BlockCoord
/*     */   extends Object implements Comparable<BlockCoord>, Copyable<BlockCoord> {
/*  10 */   public static final BlockCoord[] sideOffsets = { new BlockCoord(0, -1, 0), new BlockCoord(0, 1, 0), new BlockCoord(0, 0, -1), new BlockCoord(0, 0, 1), new BlockCoord(-1, 0, 0), new BlockCoord(1, 0, 0) };
/*     */ 
/*     */   
/*     */   public int x;
/*     */ 
/*     */   
/*     */   public int y;
/*     */ 
/*     */   
/*     */   public int z;
/*     */ 
/*     */   
/*     */   public BlockCoord(int x, int y, int z) {
/*  23 */     this.x = x;
/*  24 */     this.y = y;
/*  25 */     this.z = z;
/*     */   }
/*     */ 
/*     */   
/*  29 */   public BlockCoord(BlockPos pos) { this(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()); }
/*     */ 
/*     */ 
/*     */   
/*  33 */   public BlockCoord(Vector3 v) { this(MathHelper.func_76128_c(v.x), MathHelper.func_76128_c(v.y), MathHelper.func_76128_c(v.z)); }
/*     */ 
/*     */ 
/*     */   
/*  37 */   public BlockCoord(TileEntity tile) { this(tile.func_174877_v()); }
/*     */ 
/*     */ 
/*     */   
/*  41 */   public BlockCoord(int[] ia) { this(ia[0], ia[1], ia[2]); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockCoord() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static BlockCoord fromAxes(int[] ia) { return new BlockCoord(ia[2], ia[0], ia[1]); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  56 */     if (!(obj instanceof BlockCoord))
/*  57 */       return false; 
/*  58 */     BlockCoord o2 = (BlockCoord)obj;
/*  59 */     return (this.x == o2.x && this.y == o2.y && this.z == o2.z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public int hashCode() { return (this.x ^ this.z) * 31 + this.y; }
/*     */ 
/*     */   
/*     */   public int compareTo(BlockCoord o) {
/*  68 */     if (this.x != o.x) return (this.x < o.x) ? 1 : -1; 
/*  69 */     if (this.y != o.y) return (this.y < o.y) ? 1 : -1; 
/*  70 */     if (this.z != o.z) return (this.z < o.z) ? 1 : -1; 
/*  71 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*  75 */   public Vector3 toVector3Centered() { return new Vector3(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D); }
/*     */ 
/*     */   
/*     */   public BlockCoord multiply(int i) {
/*  79 */     this.x *= i;
/*  80 */     this.y *= i;
/*  81 */     this.z *= i;
/*  82 */     return this;
/*     */   }
/*     */ 
/*     */   
/*  86 */   public double mag() { return Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z)); }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public int mag2() { return this.x * this.x + this.y * this.y + this.z * this.z; }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public boolean isZero() { return (this.x == 0 && this.y == 0 && this.z == 0); }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public boolean isAxial() { return (this.x == 0) ? ((this.y == 0 || this.z == 0)) : ((this.y == 0 && this.z == 0)); }
/*     */ 
/*     */   
/*     */   public BlockCoord add(BlockCoord coord2) {
/* 102 */     this.x += coord2.x;
/* 103 */     this.y += coord2.y;
/* 104 */     this.z += coord2.z;
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord add(int i, int j, int k) {
/* 109 */     this.x += i;
/* 110 */     this.y += j;
/* 111 */     this.z += k;
/* 112 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord sub(BlockCoord coord2) {
/* 116 */     this.x -= coord2.x;
/* 117 */     this.y -= coord2.y;
/* 118 */     this.z -= coord2.z;
/* 119 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord sub(int i, int j, int k) {
/* 123 */     this.x -= i;
/* 124 */     this.y -= j;
/* 125 */     this.z -= k;
/* 126 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 130 */   public BlockCoord offset(int side) { return offset(side, 1); }
/*     */ 
/*     */   
/*     */   public BlockCoord offset(int side, int amount) {
/* 134 */     BlockCoord offset = sideOffsets[side];
/* 135 */     this.x += offset.x * amount;
/* 136 */     this.y += offset.y * amount;
/* 137 */     this.z += offset.z * amount;
/* 138 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 142 */   public BlockCoord inset(int side) { return inset(side, 1); }
/*     */ 
/*     */ 
/*     */   
/* 146 */   public BlockCoord inset(int side, int amount) { return offset(side, -amount); }
/*     */ 
/*     */   
/*     */   public int getSide(int side) {
/* 150 */     switch (side) {
/*     */       case 0:
/*     */       case 1:
/* 153 */         return this.y;
/*     */       case 2:
/*     */       case 3:
/* 156 */         return this.z;
/*     */       case 4:
/*     */       case 5:
/* 159 */         return this.x;
/*     */     } 
/* 161 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public BlockCoord setSide(int s, int v) {
/* 165 */     switch (s) {
/*     */       case 0:
/*     */       case 1:
/* 168 */         this.y = v;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 181 */         return this;case 2: case 3: this.z = v; return this;case 4: case 5: this.x = v; return this;
/*     */     } 
/*     */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/* 185 */   public int[] intArray() { return new int[] { this.x, this.y, this.z }; }
/*     */ 
/*     */ 
/*     */   
/* 189 */   public BlockPos pos() { return new BlockPos(this.x, this.y, this.z); }
/*     */ 
/*     */ 
/*     */   
/* 193 */   public BlockCoord copy() { return new BlockCoord(this.x, this.y, this.z); }
/*     */ 
/*     */   
/*     */   public BlockCoord set(int i, int j, int k) {
/* 197 */     this.x = i;
/* 198 */     this.y = j;
/* 199 */     this.z = k;
/* 200 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 204 */   public BlockCoord set(BlockCoord coord) { return set(coord.x, coord.y, coord.z); }
/*     */ 
/*     */ 
/*     */   
/* 208 */   public BlockCoord set(BlockPos pos) { return set(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()); }
/*     */ 
/*     */ 
/*     */   
/* 212 */   public BlockCoord set(int[] ia) { return set(ia[0], ia[1], ia[2]); }
/*     */ 
/*     */ 
/*     */   
/* 216 */   public BlockCoord set(TileEntity tile) { return set(tile.func_174877_v()); }
/*     */ 
/*     */   
/*     */   public int toSide() {
/* 220 */     if (!isAxial()) return -1; 
/* 221 */     if (this.y < 0) return 0; 
/* 222 */     if (this.y > 0) return 1; 
/* 223 */     if (this.z < 0) return 2; 
/* 224 */     if (this.z > 0) return 3; 
/* 225 */     if (this.x < 0) return 4; 
/* 226 */     if (this.x > 0) return 5;
/*     */     
/* 228 */     return -1;
/*     */   }
/*     */ 
/*     */   
/* 232 */   public int absSum() { return ((this.x < 0) ? -this.x : this.x) + ((this.y < 0) ? -this.y : this.y) + ((this.z < 0) ? -this.z : this.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 238 */   public String toString() { return "(" + this.x + ", " + this.y + ", " + this.z + ")"; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\BlockCoord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */