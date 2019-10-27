/*     */ package thaumcraft.codechicken.lib.math;
/*     */ 
/*     */ 
/*     */ public class MathHelper
/*     */ {
/*     */   public static final double phi = 1.618033988749894D;
/*     */   public static final double pi = 3.141592653589793D;
/*     */   public static final double todeg = 57.29577951308232D;
/*     */   public static final double torad = 0.017453292519943D;
/*     */   public static final double sqrt2 = 1.414213562373095D;
/*  11 */   public static double[] SIN_TABLE = new double[65536];
/*     */   
/*     */   static  {
/*  14 */     for (i = 0; i < 65536; i++) {
/*  15 */       SIN_TABLE[i] = Math.sin(i / 65536.0D * 2.0D * Math.PI);
/*     */     }
/*  17 */     SIN_TABLE[0] = 0.0D;
/*  18 */     SIN_TABLE[16384] = 1.0D;
/*  19 */     SIN_TABLE[32768] = 0.0D;
/*  20 */     SIN_TABLE[49152] = 1.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  25 */   public static double sin(double d) { return SIN_TABLE[(int)((float)d * 10430.378F) & 0xFFFF]; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   public static double cos(double d) { return SIN_TABLE[(int)((float)d * 10430.378F + 16384.0F) & 0xFFFF]; }
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
/*  41 */   public static float approachLinear(float a, float b, float max) { return (a > b) ? ((a - b < max) ? b : (a - max)) : ((b - a < max) ? b : (a + max)); }
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
/*  54 */   public static double approachLinear(double a, double b, double max) { return (a > b) ? ((a - b < max) ? b : (a - max)) : ((b - a < max) ? b : (a + max)); }
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
/*  67 */   public static float interpolate(float a, float b, float d) { return a + (b - a) * d; }
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
/*  78 */   public static double interpolate(double a, double b, double d) { return a + (b - a) * d; }
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
/*  89 */   public static double approachExp(double a, double b, double ratio) { return a + (b - a) * ratio; }
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
/*     */   public static double approachExp(double a, double b, double ratio, double cap) {
/* 101 */     double d = (b - a) * ratio;
/* 102 */     if (Math.abs(d) > cap)
/* 103 */       d = Math.signum(d) * cap; 
/* 104 */     return a + d;
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
/*     */   public static double retreatExp(double a, double b, double c, double ratio, double kick) {
/* 117 */     double d = (Math.abs(c - a) + kick) * ratio;
/* 118 */     if (d > Math.abs(b - a))
/* 119 */       return b; 
/* 120 */     return a + Math.signum(b - a) * d;
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
/*     */   public static double clip(double value, double min, double max) {
/* 132 */     if (value > max)
/* 133 */       value = max; 
/* 134 */     if (value < min)
/* 135 */       value = min; 
/* 136 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static boolean between(double a, double x, double b) { return (a <= x && x <= b); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int approachExpI(int a, int b, double ratio) {
/* 149 */     int r = (int)Math.round(approachExp(a, b, ratio));
/* 150 */     return (r == a) ? b : r;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int retreatExpI(int a, int b, int c, double ratio, int kick) {
/* 155 */     int r = (int)Math.round(retreatExp(a, b, c, ratio, kick));
/* 156 */     return (r == a) ? b : r;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 161 */   public static int roundAway(double d) { return (int)((d < 0.0D) ? Math.floor(d) : Math.ceil(d)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public static int compare(int a, int b) { return (a == b) ? 0 : ((a < b) ? -1 : 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public static int compare(double a, double b) { return (a == b) ? 0 : ((a < b) ? -1 : 1); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\math\MathHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */