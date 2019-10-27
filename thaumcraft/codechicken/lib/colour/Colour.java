/*     */ package thaumcraft.codechicken.lib.colour;
/*     */ 
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ 
/*     */ public abstract class Colour
/*     */   extends Object
/*     */   implements Copyable<Colour>
/*     */ {
/*     */   public byte r;
/*     */   public byte g;
/*     */   public byte b;
/*     */   public byte a;
/*     */   
/*     */   public Colour(int r, int g, int b, int a) {
/*  20 */     this.r = (byte)r;
/*  21 */     this.g = (byte)g;
/*  22 */     this.b = (byte)b;
/*  23 */     this.a = (byte)a;
/*     */   }
/*     */ 
/*     */   
/*     */   public Colour(Colour colour) {
/*  28 */     this.r = colour.r;
/*  29 */     this.g = colour.g;
/*  30 */     this.b = colour.b;
/*  31 */     this.a = colour.a;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  37 */   public void glColour() { GL11.glColor4ub(this.r, this.g, this.b, this.a); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  43 */   public void glColour(int a) { GL11.glColor4ub(this.r, this.g, this.b, (byte)a); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public String toString() { return getClass().getSimpleName() + "[0x" + Integer.toHexString(pack()).toUpperCase() + "]"; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Colour add(Colour colour2) {
/*  56 */     this.a = (byte)(this.a + colour2.a);
/*  57 */     this.r = (byte)(this.r + colour2.r);
/*  58 */     this.g = (byte)(this.g + colour2.g);
/*  59 */     this.b = (byte)(this.b + colour2.b);
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Colour sub(Colour colour2) {
/*  65 */     int ia = (this.a & 0xFF) - (colour2.a & 0xFF);
/*  66 */     int ir = (this.r & 0xFF) - (colour2.r & 0xFF);
/*  67 */     int ig = (this.g & 0xFF) - (colour2.g & 0xFF);
/*  68 */     int ib = (this.b & 0xFF) - (colour2.b & 0xFF);
/*  69 */     this.a = (byte)((ia < 0) ? 0 : ia);
/*  70 */     this.r = (byte)((ir < 0) ? 0 : ir);
/*  71 */     this.g = (byte)((ig < 0) ? 0 : ig);
/*  72 */     this.b = (byte)((ib < 0) ? 0 : ib);
/*  73 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Colour invert() {
/*  78 */     this.a = (byte)('ÿ' - (this.a & 0xFF));
/*  79 */     this.r = (byte)('ÿ' - (this.r & 0xFF));
/*  80 */     this.g = (byte)('ÿ' - (this.g & 0xFF));
/*  81 */     this.b = (byte)('ÿ' - (this.b & 0xFF));
/*  82 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Colour multiply(Colour colour2) {
/*  87 */     this.a = (byte)(int)((this.a & 0xFF) * (colour2.a & 0xFF) / 255.0D);
/*  88 */     this.r = (byte)(int)((this.r & 0xFF) * (colour2.r & 0xFF) / 255.0D);
/*  89 */     this.g = (byte)(int)((this.g & 0xFF) * (colour2.g & 0xFF) / 255.0D);
/*  90 */     this.b = (byte)(int)((this.b & 0xFF) * (colour2.b & 0xFF) / 255.0D);
/*  91 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Colour scale(double d) {
/*  96 */     this.a = (byte)(int)((this.a & 0xFF) * d);
/*  97 */     this.r = (byte)(int)((this.r & 0xFF) * d);
/*  98 */     this.g = (byte)(int)((this.g & 0xFF) * d);
/*  99 */     this.b = (byte)(int)((this.b & 0xFF) * d);
/* 100 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public Colour interpolate(Colour colour2, double d) { return add(colour2.copy().sub(this).scale(d)); }
/*     */ 
/*     */ 
/*     */   
/*     */   public Colour multiplyC(double d) {
/* 110 */     this.r = (byte)(int)MathHelper.clip((this.r & 0xFF) * d, 0.0D, 255.0D);
/* 111 */     this.g = (byte)(int)MathHelper.clip((this.g & 0xFF) * d, 0.0D, 255.0D);
/* 112 */     this.b = (byte)(int)MathHelper.clip((this.b & 0xFF) * d, 0.0D, 255.0D);
/*     */     
/* 114 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public int rgb() { return (this.r & 0xFF) << 16 | (this.g & 0xFF) << 8 | this.b & 0xFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public int argb() { return (this.a & 0xFF) << 24 | (this.r & 0xFF) << 16 | (this.g & 0xFF) << 8 | this.b & 0xFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public int rgba() { return (this.r & 0xFF) << 24 | (this.g & 0xFF) << 16 | (this.b & 0xFF) << 8 | this.a & 0xFF; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Colour set(Colour colour) {
/* 136 */     this.r = colour.r;
/* 137 */     this.g = colour.g;
/* 138 */     this.b = colour.b;
/* 139 */     this.a = colour.a;
/* 140 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 145 */   public boolean equals(Colour colour) { return (colour != null && rgba() == colour.rgba()); }
/*     */   
/*     */   public abstract int pack();
/*     */   
/*     */   public abstract Colour copy();
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\colour\Colour.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */