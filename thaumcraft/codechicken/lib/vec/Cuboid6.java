/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class Cuboid6
/*     */   extends Object
/*     */   implements Copyable<Cuboid6> {
/*  12 */   public static Cuboid6 full = new Cuboid6(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   
/*     */   public Vector3 min;
/*     */   
/*     */   public Vector3 max;
/*     */   
/*  18 */   public Cuboid6() { this(new Vector3(), new Vector3()); }
/*     */ 
/*     */   
/*     */   public Cuboid6(Vector3 min, Vector3 max) {
/*  22 */     this.min = min;
/*  23 */     this.max = max;
/*     */   }
/*     */   
/*     */   public Cuboid6(AxisAlignedBB aabb) {
/*  27 */     this.min = new Vector3(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c);
/*  28 */     this.max = new Vector3(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f);
/*     */   }
/*     */   
/*     */   public Cuboid6(Cuboid6 cuboid) {
/*  32 */     this.min = cuboid.min.copy();
/*  33 */     this.max = cuboid.max.copy();
/*     */   }
/*     */   
/*     */   public Cuboid6(double minx, double miny, double minz, double maxx, double maxy, double maxz) {
/*  37 */     this.min = new Vector3(minx, miny, minz);
/*  38 */     this.max = new Vector3(maxx, maxy, maxz);
/*     */   }
/*     */ 
/*     */   
/*  42 */   public AxisAlignedBB aabb() { return new AxisAlignedBB(this.min.x, this.min.y, this.min.z, this.max.x, this.max.y, this.max.z); }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public Cuboid6 copy() { return new Cuboid6(this); }
/*     */ 
/*     */ 
/*     */   
/*  50 */   public Cuboid6 set(Cuboid6 c) { return set(c.min, c.max); }
/*     */ 
/*     */   
/*     */   public Cuboid6 set(Vector3 min, Vector3 max) {
/*  54 */     this.min.set(min);
/*  55 */     this.max.set(max);
/*  56 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 set(double minx, double miny, double minz, double maxx, double maxy, double maxz) {
/*  60 */     this.min.set(minx, miny, minz);
/*  61 */     this.max.set(maxx, maxy, maxz);
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 add(Vector3 vec) {
/*  66 */     this.min.add(vec);
/*  67 */     this.max.add(vec);
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 sub(Vector3 vec) {
/*  72 */     this.min.subtract(vec);
/*  73 */     this.max.subtract(vec);
/*  74 */     return this;
/*     */   }
/*     */ 
/*     */   
/*  78 */   public Cuboid6 expand(double d) { return expand(new Vector3(d, d, d)); }
/*     */ 
/*     */   
/*     */   public Cuboid6 expand(Vector3 vec) {
/*  82 */     this.min.sub(vec);
/*  83 */     this.max.add(vec);
/*  84 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public boolean intersects(Cuboid6 b) { return (this.max.x - 1.0E-5D > b.min.x && b.max.x - 1.0E-5D > this.min.x && this.max.y - 1.0E-5D > b.min.y && b.max.y - 1.0E-5D > this.min.y && this.max.z - 1.0E-5D > b.min.z && b.max.z - 1.0E-5D > this.min.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cuboid6 offset(Cuboid6 o) {
/* 101 */     this.min.add(o.min);
/* 102 */     this.max.add(o.max);
/* 103 */     return this;
/*     */   }
/*     */ 
/*     */   
/* 107 */   public Vector3 center() { return this.min.copy().add(this.max).multiply(0.5D); }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static boolean intersects(Cuboid6 a, Cuboid6 b) { return (a != null && b != null && a.intersects(b)); }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 116 */     return "Cuboid: (" + new BigDecimal(this.min.x, cont) + ", " + new BigDecimal(this.min.y, cont) + ", " + new BigDecimal(this.min.z, cont) + ") -> (" + new BigDecimal(this.max.x, cont) + ", " + new BigDecimal(this.max.y, cont) + ", " + new BigDecimal(this.max.z, cont) + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public Cuboid6 enclose(Vector3 vec) {
/* 121 */     if (this.min.x > vec.x) this.min.x = vec.x; 
/* 122 */     if (this.min.y > vec.y) this.min.y = vec.y; 
/* 123 */     if (this.min.z > vec.z) this.min.z = vec.z; 
/* 124 */     if (this.max.x < vec.x) this.max.x = vec.x; 
/* 125 */     if (this.max.y < vec.y) this.max.y = vec.y; 
/* 126 */     if (this.max.z < vec.z) this.max.z = vec.z; 
/* 127 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 enclose(Cuboid6 c) {
/* 131 */     if (this.min.x > c.min.x) this.min.x = c.min.x; 
/* 132 */     if (this.min.y > c.min.y) this.min.y = c.min.y; 
/* 133 */     if (this.min.z > c.min.z) this.min.z = c.min.z; 
/* 134 */     if (this.max.x < c.max.x) this.max.x = c.max.x; 
/* 135 */     if (this.max.y < c.max.y) this.max.y = c.max.y; 
/* 136 */     if (this.max.z < c.max.z) this.max.z = c.max.z; 
/* 137 */     return this;
/*     */   }
/*     */   
/*     */   public Cuboid6 apply(Transformation t) {
/* 141 */     t.apply(this.min);
/* 142 */     t.apply(this.max);
/*     */     
/* 144 */     if (this.min.x > this.max.x) { double temp = this.min.x; this.min.x = this.max.x; this.max.x = temp; }
/* 145 */      if (this.min.y > this.max.y) { double temp = this.min.y; this.min.y = this.max.y; this.max.y = temp; }
/* 146 */      if (this.min.z > this.max.z) { double temp = this.min.z; this.min.z = this.max.z; this.max.z = temp; }
/* 147 */      return this;
/*     */   }
/*     */   
/*     */   public double getSide(int s) {
/* 151 */     switch (s) { case 0:
/* 152 */         return this.min.y;
/* 153 */       case 1: return this.max.y;
/* 154 */       case 2: return this.min.z;
/* 155 */       case 3: return this.max.z;
/* 156 */       case 4: return this.min.x;
/* 157 */       case 5: return this.max.x; }
/*     */     
/* 159 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public Cuboid6 setSide(int s, double d) {
/* 163 */     switch (s) { case 0:
/* 164 */         this.min.y = d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 172 */         return this;case 1: this.max.y = d; return this;case 2: this.min.z = d; return this;case 3: this.max.z = d; return this;case 4: this.min.x = d; return this;case 5: this.max.x = d; return this; }
/*     */     
/*     */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\Cuboid6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */