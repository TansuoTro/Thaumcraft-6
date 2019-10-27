/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Vector3f;
/*     */ import org.lwjgl.util.vector.Vector4f;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class Vector3
/*     */   extends Object
/*     */   implements Copyable<Vector3>
/*     */ {
/*  22 */   public static Vector3 zero = new Vector3();
/*  23 */   public static Vector3 one = new Vector3(1.0D, 1.0D, 1.0D);
/*  24 */   public static Vector3 center = new Vector3(0.5D, 0.5D, 0.5D);
/*     */   
/*     */   public double x;
/*     */   
/*     */   public double y;
/*     */   public double z;
/*     */   
/*     */   public Vector3() {}
/*     */   
/*     */   public Vector3(double d, double d1, double d2) {
/*  34 */     this.x = d;
/*  35 */     this.y = d1;
/*  36 */     this.z = d2;
/*     */   }
/*     */   
/*     */   public Vector3(Vector3 vec) {
/*  40 */     this.x = vec.x;
/*  41 */     this.y = vec.y;
/*  42 */     this.z = vec.z;
/*     */   }
/*     */ 
/*     */   
/*  46 */   public Vector3(double[] da) { this(da[0], da[1], da[2]); }
/*     */ 
/*     */   
/*     */   public Vector3(Vec3d vec) {
/*  50 */     this.x = vec.field_72450_a;
/*  51 */     this.y = vec.field_72448_b;
/*  52 */     this.z = vec.field_72449_c;
/*     */   }
/*     */   
/*     */   public Vector3(BlockCoord coord) {
/*  56 */     this.x = coord.x;
/*  57 */     this.y = coord.y;
/*  58 */     this.z = coord.z;
/*     */   }
/*     */   
/*     */   public Vector3(BlockPos pos) {
/*  62 */     this.x = pos.func_177958_n();
/*  63 */     this.y = pos.func_177956_o();
/*  64 */     this.z = pos.func_177952_p();
/*     */   }
/*     */ 
/*     */   
/*  68 */   public Vector3 copy() { return new Vector3(this); }
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static Vector3 fromEntity(Entity e) { return new Vector3(e.field_70165_t, e.field_70163_u, e.field_70161_v); }
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static Vector3 fromEntityCenter(Entity e) { return new Vector3(e.field_70165_t, e.field_70163_u - e.func_70033_W() + (e.field_70131_O / 2.0F), e.field_70161_v); }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static Vector3 fromTile(TileEntity tile) { return new Vector3(tile.func_174877_v()); }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public static Vector3 fromTileCenter(TileEntity tile) { return fromTile(tile).add(0.5D); }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static Vector3 fromAxes(double[] da) { return new Vector3(da[2], da[0], da[1]); }
/*     */ 
/*     */   
/*     */   public Vector3 set(double d, double d1, double d2) {
/*  92 */     this.x = d;
/*  93 */     this.y = d1;
/*  94 */     this.z = d2;
/*  95 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 set(Vector3 vec) {
/*  99 */     this.x = vec.x;
/* 100 */     this.y = vec.y;
/* 101 */     this.z = vec.z;
/* 102 */     return this;
/*     */   }
/*     */   
/*     */   public double getSide(int side) {
/* 106 */     switch (side) {
/*     */       case 0:
/*     */       case 1:
/* 109 */         return this.y;
/*     */       case 2:
/*     */       case 3:
/* 112 */         return this.z;
/*     */       case 4:
/*     */       case 5:
/* 115 */         return this.x;
/*     */     } 
/* 117 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public Vector3 setSide(int s, double v) {
/* 121 */     switch (s) {
/*     */       case 0:
/*     */       case 1:
/* 124 */         this.y = v;
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
/* 137 */         return this;case 2: case 3: this.z = v; return this;case 4: case 5: this.x = v; return this;
/*     */     } 
/*     */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   } public double dotProduct(Vector3 vec) {
/* 141 */     double d = vec.x * this.x + vec.y * this.y + vec.z * this.z;
/*     */     
/* 143 */     if (d > 1.0D && d < 1.00001D) {
/* 144 */       d = 1.0D;
/* 145 */     } else if (d < -1.0D && d > -1.00001D) {
/* 146 */       d = -1.0D;
/* 147 */     }  return d;
/*     */   }
/*     */ 
/*     */   
/* 151 */   public double dotProduct(double d, double d1, double d2) { return d * this.x + d1 * this.y + d2 * this.z; }
/*     */ 
/*     */   
/*     */   public Vector3 crossProduct(Vector3 vec) {
/* 155 */     double d = this.y * vec.z - this.z * vec.y;
/* 156 */     double d1 = this.z * vec.x - this.x * vec.z;
/* 157 */     double d2 = this.x * vec.y - this.y * vec.x;
/* 158 */     this.x = d;
/* 159 */     this.y = d1;
/* 160 */     this.z = d2;
/* 161 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 add(double d, double d1, double d2) {
/* 165 */     this.x += d;
/* 166 */     this.y += d1;
/* 167 */     this.z += d2;
/* 168 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 add(Vector3 vec) {
/* 172 */     this.x += vec.x;
/* 173 */     this.y += vec.y;
/* 174 */     this.z += vec.z;
/* 175 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 179 */   public Vector3 add(double d) { return add(d, d, d); }
/*     */ 
/*     */ 
/*     */   
/* 183 */   public Vector3 sub(Vector3 vec) { return subtract(vec); }
/*     */ 
/*     */   
/*     */   public Vector3 subtract(Vector3 vec) {
/* 187 */     this.x -= vec.x;
/* 188 */     this.y -= vec.y;
/* 189 */     this.z -= vec.z;
/* 190 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 negate(Vector3 vec) {
/* 194 */     this.x = -this.x;
/* 195 */     this.y = -this.y;
/* 196 */     this.z = -this.z;
/* 197 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(double d) {
/* 201 */     this.x *= d;
/* 202 */     this.y *= d;
/* 203 */     this.z *= d;
/* 204 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(Vector3 f) {
/* 208 */     this.x *= f.x;
/* 209 */     this.y *= f.y;
/* 210 */     this.z *= f.z;
/* 211 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 multiply(double fx, double fy, double fz) {
/* 215 */     this.x *= fx;
/* 216 */     this.y *= fy;
/* 217 */     this.z *= fz;
/* 218 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 222 */   public double mag() { return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z); }
/*     */ 
/*     */ 
/*     */   
/* 226 */   public double magSquared() { return this.x * this.x + this.y * this.y + this.z * this.z; }
/*     */ 
/*     */   
/*     */   public Vector3 normalize() {
/* 230 */     double d = mag();
/* 231 */     if (d != 0.0D) {
/* 232 */       multiply(1.0D / d);
/*     */     }
/* 234 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 238 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 239 */     return "Vector3(" + new BigDecimal(this.x, cont) + ", " + new BigDecimal(this.y, cont) + ", " + new BigDecimal(this.z, cont) + ")";
/*     */   }
/*     */   
/*     */   public Vector3 perpendicular() {
/* 243 */     if (this.z == 0.0D)
/* 244 */       return zCrossProduct(); 
/* 245 */     return xCrossProduct();
/*     */   }
/*     */   
/*     */   public Vector3 xCrossProduct() {
/* 249 */     double d = this.z;
/* 250 */     double d1 = -this.y;
/* 251 */     this.x = 0.0D;
/* 252 */     this.y = d;
/* 253 */     this.z = d1;
/* 254 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 zCrossProduct() {
/* 258 */     double d = this.y;
/* 259 */     double d1 = -this.x;
/* 260 */     this.x = d;
/* 261 */     this.y = d1;
/* 262 */     this.z = 0.0D;
/* 263 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 yCrossProduct() {
/* 267 */     double d = -this.z;
/* 268 */     double d1 = this.x;
/* 269 */     this.x = d;
/* 270 */     this.y = 0.0D;
/* 271 */     this.z = d1;
/* 272 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 rotate(double angle, Vector3 axis) {
/* 276 */     Quat.aroundAxis(axis.copy().normalize(), angle).rotate(this);
/* 277 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 rotate(Quat rotator) {
/* 281 */     rotator.rotate(this);
/* 282 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 286 */   public Vec3d vec3() { return new Vec3d(this.x, this.y, this.z); }
/*     */ 
/*     */ 
/*     */   
/* 290 */   public double angle(Vector3 vec) { return Math.acos(copy().normalize().dotProduct(vec.copy().normalize())); }
/*     */ 
/*     */ 
/*     */   
/* 294 */   public boolean isZero() { return (this.x == 0.0D && this.y == 0.0D && this.z == 0.0D); }
/*     */ 
/*     */ 
/*     */   
/* 298 */   public boolean isAxial() { return (this.x == 0.0D) ? ((this.y == 0.0D || this.z == 0.0D)) : ((this.y == 0.0D && this.z == 0.0D)); }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 303 */   public Vector3f vector3f() { return new Vector3f((float)this.x, (float)this.y, (float)this.z); }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 308 */   public Vector4f vector4f() { return new Vector4f((float)this.x, (float)this.y, (float)this.z, 1.0F); }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 313 */   public void glVertex() { GL11.glVertex3d(this.x, this.y, this.z); }
/*     */ 
/*     */   
/*     */   public Vector3 YZintercept(Vector3 end, double px) {
/* 317 */     double dx = end.x - this.x;
/* 318 */     double dy = end.y - this.y;
/* 319 */     double dz = end.z - this.z;
/*     */     
/* 321 */     if (dx == 0.0D) {
/* 322 */       return null;
/*     */     }
/* 324 */     double d = (px - this.x) / dx;
/* 325 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 326 */       return this;
/*     */     }
/* 328 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 329 */       return null;
/*     */     }
/* 331 */     this.x = px;
/* 332 */     this.y += d * dy;
/* 333 */     this.z += d * dz;
/* 334 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 XZintercept(Vector3 end, double py) {
/* 338 */     double dx = end.x - this.x;
/* 339 */     double dy = end.y - this.y;
/* 340 */     double dz = end.z - this.z;
/*     */     
/* 342 */     if (dy == 0.0D) {
/* 343 */       return null;
/*     */     }
/* 345 */     double d = (py - this.y) / dy;
/* 346 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 347 */       return this;
/*     */     }
/* 349 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 350 */       return null;
/*     */     }
/* 352 */     this.x += d * dx;
/* 353 */     this.y = py;
/* 354 */     this.z += d * dz;
/* 355 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 XYintercept(Vector3 end, double pz) {
/* 359 */     double dx = end.x - this.x;
/* 360 */     double dy = end.y - this.y;
/* 361 */     double dz = end.z - this.z;
/*     */     
/* 363 */     if (dz == 0.0D) {
/* 364 */       return null;
/*     */     }
/* 366 */     double d = (pz - this.z) / dz;
/* 367 */     if (MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
/* 368 */       return this;
/*     */     }
/* 370 */     if (!MathHelper.between(0.0D, d, 1.0D)) {
/* 371 */       return null;
/*     */     }
/* 373 */     this.x += d * dx;
/* 374 */     this.y += d * dy;
/* 375 */     this.z = pz;
/* 376 */     return this;
/*     */   }
/*     */   
/*     */   public Vector3 negate() {
/* 380 */     this.x = -this.x;
/* 381 */     this.y = -this.y;
/* 382 */     this.z = -this.z;
/* 383 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 387 */   public Translation translation() { return new Translation(this); }
/*     */ 
/*     */   
/*     */   public double scalarProject(Vector3 b) {
/* 391 */     double l = b.mag();
/* 392 */     return (l == 0.0D) ? 0.0D : (dotProduct(b) / l);
/*     */   }
/*     */   
/*     */   public Vector3 project(Vector3 b) {
/* 396 */     double l = b.magSquared();
/* 397 */     if (l == 0.0D) {
/* 398 */       set(0.0D, 0.0D, 0.0D);
/* 399 */       return this;
/*     */     } 
/* 401 */     double m = dotProduct(b) / l;
/* 402 */     set(b).multiply(m);
/* 403 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 408 */     if (!(o instanceof Vector3))
/* 409 */       return false; 
/* 410 */     Vector3 v = (Vector3)o;
/* 411 */     return (this.x == v.x && this.y == v.y && this.z == v.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equalsT(Vector3 v) {
/* 420 */     return (MathHelper.between(this.x - 1.0E-5D, v.x, this.x + 1.0E-5D) && 
/* 421 */       MathHelper.between(this.y - 1.0E-5D, v.y, this.y + 1.0E-5D) && 
/* 422 */       MathHelper.between(this.z - 1.0E-5D, v.z, this.z + 1.0E-5D));
/*     */   }
/*     */   
/*     */   public Vector3 apply(Transformation t) {
/* 426 */     t.apply(this);
/* 427 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 431 */   public Vector3 $tilde() { return normalize(); }
/*     */ 
/*     */ 
/*     */   
/* 435 */   public Vector3 unary_$tilde() { return normalize(); }
/*     */ 
/*     */ 
/*     */   
/* 439 */   public Vector3 $plus(Vector3 v) { return add(v); }
/*     */ 
/*     */ 
/*     */   
/* 443 */   public Vector3 $minus(Vector3 v) { return subtract(v); }
/*     */ 
/*     */ 
/*     */   
/* 447 */   public Vector3 $times(double d) { return multiply(d); }
/*     */ 
/*     */ 
/*     */   
/* 451 */   public Vector3 $div(double d) { return multiply(1.0D / d); }
/*     */ 
/*     */ 
/*     */   
/* 455 */   public Vector3 $times(Vector3 v) { return crossProduct(v); }
/*     */ 
/*     */ 
/*     */   
/* 459 */   public double $dot$times(Vector3 v) { return dotProduct(v); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\Vector3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */