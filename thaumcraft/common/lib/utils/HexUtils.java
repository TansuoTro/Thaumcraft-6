/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class HexUtils
/*     */ {
/*   9 */   static final int[][] NEIGHBOURS = { { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 } };
/*     */   
/*     */   public static class Hex { public int q;
/*     */     public int r;
/*     */     
/*     */     public Hex(int q, int r) {
/*  15 */       this.q = 0;
/*  16 */       this.r = 0;
/*     */ 
/*     */ 
/*     */       
/*  20 */       this.q = q;
/*  21 */       this.r = r;
/*     */     }
/*     */ 
/*     */     
/*  25 */     public HexUtils.CubicHex toCubicHex() { return new HexUtils.CubicHex(this.q, this.r, -this.q - this.r); }
/*     */ 
/*     */ 
/*     */     
/*  29 */     public HexUtils.Pixel toPixel(int size) { return new HexUtils.Pixel(size * 1.5D * this.q, size * Math.sqrt(3.0D) * (this.r + this.q / 2.0D)); }
/*     */ 
/*     */     
/*     */     public Hex getNeighbour(int direction) {
/*  33 */       int[] d = HexUtils.NEIGHBOURS[direction];
/*  34 */       return new Hex(this.q + d[0], this.r + d[1]);
/*     */     }
/*     */ 
/*     */     
/*  38 */     public boolean equals(Hex h) { return (h.q == this.q && h.r == this.r); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     public String toString() { return this.q + ":" + this.r; }
/*     */ 
/*     */     
/*     */     public static Hex fromString(String hs) {
/*  47 */       String[] ss = hs.split(":");
/*  48 */       if (ss.length == 2)
/*     */         
/*  50 */         try { int q = Integer.parseInt(ss[0]);
/*  51 */           int r = Integer.parseInt(ss[1]);
/*  52 */           return new Hex(q, r); }
/*  53 */         catch (Exception e) { e.printStackTrace(); }
/*     */          
/*  55 */       return null;
/*     */     } }
/*     */   public static class CubicHex { public int x;
/*     */     
/*     */     public CubicHex(int x, int y, int z) {
/*  60 */       this.x = 0;
/*  61 */       this.y = 0;
/*  62 */       this.z = 0;
/*     */ 
/*     */ 
/*     */       
/*  66 */       this.x = x;
/*  67 */       this.y = y;
/*  68 */       this.z = z;
/*     */     }
/*     */     public int y; public int z;
/*     */     
/*  72 */     public HexUtils.Hex toHex() { return new HexUtils.Hex(this.x, this.z); } }
/*     */   public static class Pixel { public double x;
/*     */     public double y;
/*     */     
/*     */     public Pixel(double x, double y) {
/*  77 */       this.x = 0.0D;
/*  78 */       this.y = 0.0D;
/*     */ 
/*     */ 
/*     */       
/*  82 */       this.x = x;
/*  83 */       this.y = y;
/*     */     }
/*     */     
/*     */     public HexUtils.Hex toHex(int size) {
/*  87 */       double qq = 0.6666666666666666D * this.x / size;
/*  88 */       double rr = (0.3333333333333333D * Math.sqrt(3.0D) * -this.y - 0.3333333333333333D * this.x) / size;
/*  89 */       return HexUtils.getRoundedHex(qq, rr);
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static int getDistance(Hex a1, Hex a2) { return (Math.abs(a1.q - a2.q) + Math.abs(a1.r - a2.r) + Math.abs(a1.q + a1.r - a2.q - a2.r)) / 2; }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public static Hex getRoundedHex(double qq, double rr) { return getRoundedCubicHex(qq, rr, -qq - rr).toHex(); }
/*     */ 
/*     */   
/*     */   public static CubicHex getRoundedCubicHex(double xx, double yy, double zz) {
/* 104 */     int rx = (int)Math.round(xx);
/* 105 */     int ry = (int)Math.round(yy);
/* 106 */     int rz = (int)Math.round(zz);
/*     */     
/* 108 */     double x_diff = Math.abs(rx - xx);
/* 109 */     double y_diff = Math.abs(ry - yy);
/* 110 */     double z_diff = Math.abs(rz - zz);
/*     */     
/* 112 */     if (x_diff > y_diff && x_diff > z_diff) {
/* 113 */       rx = -ry - rz;
/*     */     }
/* 115 */     else if (y_diff > z_diff) {
/* 116 */       ry = -rx - rz;
/*     */     } else {
/* 118 */       rz = -rx - ry;
/*     */     } 
/* 120 */     return new CubicHex(rx, ry, rz);
/*     */   }
/*     */   
/*     */   public static ArrayList<Hex> getRing(int radius) {
/* 124 */     Hex h = new Hex(0, 0);
/* 125 */     for (int k = 0; k < radius; k++) {
/* 126 */       h = h.getNeighbour(4);
/*     */     }
/*     */     
/* 129 */     ArrayList<Hex> ring = new ArrayList<Hex>();
/* 130 */     for (int i = 0; i < 6; i++) {
/* 131 */       for (int j = 0; j < radius; j++) {
/* 132 */         ring.add(h);
/* 133 */         h = h.getNeighbour(i);
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     return ring;
/*     */   }
/*     */   
/*     */   public static ArrayList<Hex> distributeRingRandomly(int radius, int entries, Random random) {
/* 141 */     ArrayList<Hex> ring = getRing(radius);
/* 142 */     ArrayList<Hex> results = new ArrayList<Hex>();
/* 143 */     float spacing = ring.size() / entries;
/*     */     
/* 145 */     float pos = random.nextInt(ring.size());
/* 146 */     for (int i = 0; i < entries; i++) {
/* 147 */       results.add(ring.get(Math.round(pos)));
/* 148 */       pos += spacing;
/* 149 */       if (pos >= ring.size()) {
/* 150 */         pos -= ring.size();
/*     */       }
/*     */     } 
/*     */     
/* 154 */     return results;
/*     */   }
/*     */   
/*     */   public static HashMap<String, Hex> generateHexes(int radius) {
/* 158 */     HashMap<String, Hex> results = new HashMap<String, Hex>();
/* 159 */     Hex h = new Hex(0, 0);
/* 160 */     results.put(h.toString(), h);
/* 161 */     for (int k = 0; k < radius; k++) {
/* 162 */       h = h.getNeighbour(4);
/* 163 */       Hex hd = new Hex(h.q, h.r);
/* 164 */       for (int i = 0; i < 6; i++) {
/* 165 */         for (int j = 0; j <= k; j++) {
/* 166 */           results.put(hd.toString(), hd);
/* 167 */           hd = hd.getNeighbour(i);
/*     */         } 
/*     */       } 
/*     */     } 
/* 171 */     return results;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\HexUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */