/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class CuboidCoord
/*     */   extends Object
/*     */   implements Iterable<BlockCoord>, Copyable<CuboidCoord>
/*     */ {
/*     */   public BlockCoord min;
/*     */   public BlockCoord max;
/*     */   
/*     */   public CuboidCoord() {
/*  15 */     this.min = new BlockCoord();
/*  16 */     this.max = new BlockCoord();
/*     */   }
/*     */ 
/*     */   
/*     */   public CuboidCoord(BlockCoord min, BlockCoord max) {
/*  21 */     this.min = min;
/*  22 */     this.max = max;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  27 */   public CuboidCoord(BlockCoord coord) { this(coord, coord.copy()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   public CuboidCoord(int[] ia) { this(ia[0], ia[1], ia[2], ia[3], ia[4], ia[5]); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public CuboidCoord(int x1, int y1, int z1, int x2, int y2, int z2) { this(new BlockCoord(x1, y1, z1), new BlockCoord(x2, y2, z2)); }
/*     */ 
/*     */ 
/*     */   
/*  41 */   public CuboidCoord expand(int amount) { return expand(amount, amount, amount); }
/*     */ 
/*     */   
/*     */   public CuboidCoord expand(int x, int y, int z) {
/*  45 */     this.max.add(x, y, z);
/*  46 */     this.min.sub(x, y, z);
/*  47 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CuboidCoord expand(int side, int amount) {
/*  52 */     if (side % 2 == 0) {
/*  53 */       this.min = this.min.offset(side, amount);
/*     */     } else {
/*  55 */       this.max = this.max.offset(side, amount);
/*  56 */     }  return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size(int s) {
/*  61 */     switch (s) {
/*     */       
/*     */       case 0:
/*     */       case 1:
/*  65 */         return this.max.y - this.min.y + 1;
/*     */       case 2:
/*     */       case 3:
/*  68 */         return this.max.z - this.min.z + 1;
/*     */       case 4:
/*     */       case 5:
/*  71 */         return this.max.x - this.min.x + 1;
/*     */     } 
/*  73 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSide(int s) {
/*  79 */     switch (s) {
/*     */       case 0:
/*  81 */         return this.min.y;
/*  82 */       case 1: return this.max.y;
/*  83 */       case 2: return this.min.z;
/*  84 */       case 3: return this.max.z;
/*  85 */       case 4: return this.min.x;
/*  86 */       case 5: return this.max.x;
/*     */     } 
/*  88 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */ 
/*     */   
/*     */   public CuboidCoord setSide(int s, int v) {
/*  93 */     switch (s) {
/*     */       case 0:
/*  95 */         this.min.y = v;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 103 */         return this;case 1: this.max.y = v; return this;case 2: this.min.z = v; return this;case 3: this.max.z = v; return this;case 4: this.min.x = v; return this;case 5: this.max.x = v; return this;
/*     */     } 
/*     */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/* 108 */   public int getVolume() { return (this.max.x - this.min.x + 1) * (this.max.y - this.min.y + 1) * (this.max.z - this.min.z + 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public Vector3 getCenterVec() { return new Vector3(this.min.x + (this.max.x - this.min.x + 1) / 2.0D, this.min.y + (this.max.y - this.min.y + 1) / 2.0D, this.min.z + (this.max.z - this.min.z + 1) / 2.0D); }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockCoord getCenter(BlockCoord store) {
/* 118 */     store.set(this.min.x + (this.max.x - this.min.x) / 2, this.min.y + (this.max.y - this.min.y) / 2, this.min.z + (this.max.z - this.min.z) / 2);
/* 119 */     return store;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 124 */   public boolean contains(BlockCoord coord) { return contains(coord.x, coord.y, coord.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public boolean contains(int x, int y, int z) { return (x >= this.min.x && x <= this.max.x && y >= this.min.y && y <= this.max.y && z >= this.min.z && z <= this.max.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public int[] intArray() { return new int[] { this.min.x, this.min.y, this.min.z, this.max.x, this.max.y, this.max.z }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public CuboidCoord copy() { return new CuboidCoord(this.min.copy(), this.max.copy()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public Cuboid6 bounds() { return new Cuboid6(this.min.x, this.min.y, this.min.z, (this.max.x + 1), (this.max.y + 1), (this.max.z + 1)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public AxisAlignedBB toAABB() { return bounds().aabb(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(BlockCoord min, BlockCoord max) {
/* 156 */     this.min.set(min);
/* 157 */     this.max.set(max);
/*     */   }
/*     */ 
/*     */   
/*     */   public CuboidCoord set(int x1, int y1, int z1, int x2, int y2, int z2) {
/* 162 */     this.min.set(x1, y1, z1);
/* 163 */     this.max.set(x2, y2, z2);
/* 164 */     return this;
/*     */   }
/*     */   
/*     */   public CuboidCoord set(BlockCoord coord) {
/* 168 */     this.min.set(coord);
/* 169 */     this.max.set(coord);
/* 170 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 175 */   public CuboidCoord set(int[] ia) { return set(ia[0], ia[1], ia[2], ia[3], ia[4], ia[5]); }
/*     */ 
/*     */ 
/*     */   
/* 179 */   public CuboidCoord include(BlockCoord coord) { return include(coord.x, coord.y, coord.z); }
/*     */ 
/*     */   
/*     */   public CuboidCoord include(int x, int y, int z) {
/* 183 */     if (x < this.min.x) { this.min.x = x; }
/* 184 */     else if (x > this.max.x) { this.max.x = x; }
/* 185 */      if (y < this.min.y) { this.min.y = y; }
/* 186 */     else if (y > this.max.y) { this.max.y = y; }
/* 187 */      if (z < this.min.z) { this.min.z = z; }
/* 188 */     else if (z > this.max.z) { this.max.z = z; }
/* 189 */      return this;
/*     */   }
/*     */   
/*     */   public Iterator<BlockCoord> iterator() {
/* 193 */     return new Iterator<BlockCoord>() {
/* 194 */         BlockCoord b = null;
/*     */ 
/*     */         
/* 197 */         public boolean hasNext() { return (this.b == null || !this.b.equals(CuboidCoord.this.max)); }
/*     */ 
/*     */         
/*     */         public BlockCoord next() {
/* 201 */           if (this.b == null) {
/* 202 */             this.b = CuboidCoord.this.min.copy();
/*     */           }
/* 204 */           else if (this.b.z != this.this$0.max.z) {
/* 205 */             this.b.z++;
/*     */           } else {
/* 207 */             this.b.z = this.this$0.min.z;
/* 208 */             if (this.b.y != this.this$0.max.y) {
/* 209 */               this.b.y++;
/*     */             } else {
/* 211 */               this.b.y = this.this$0.min.y;
/* 212 */               this.b.x++;
/*     */             } 
/*     */           } 
/*     */           
/* 216 */           return this.b.copy();
/*     */         }
/*     */ 
/*     */         
/* 220 */         public void remove() { throw new UnsupportedOperationException(); }
/*     */       };
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\CuboidCoord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */