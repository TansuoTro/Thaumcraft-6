/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class FXBoreParticles
/*     */   extends Particle
/*     */ {
/*     */   private IBlockState blockInstance;
/*     */   private ItemStack itemInstance;
/*     */   private int side;
/*     */   private Entity target;
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   
/*     */   public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, IBlockState par14Block, int par15) {
/*  34 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  35 */     this.blockInstance = par14Block;
/*     */     try {
/*  37 */       func_187117_a(
/*  38 */           Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178087_a(
/*  39 */             Item.func_150898_a(par14Block.func_177230_c()), par15));
/*  40 */     } catch (Exception e) {
/*  41 */       func_187117_a(
/*  42 */           Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178087_a(
/*  43 */             Item.func_150898_a(Blocks.field_150348_b), 0));
/*  44 */       this.field_70547_e = 0;
/*     */     } 
/*     */     
/*  47 */     this.field_70545_g = (par14Block.func_177230_c()).field_149763_I;
/*  48 */     this.field_70552_h = this.field_70553_i = this.field_70551_j = 0.6F;
/*  49 */     this.field_70544_f = this.field_187136_p.nextFloat() * 0.3F + 0.4F;
/*  50 */     this.side = par15;
/*     */     
/*  52 */     this.targetX = tx;
/*  53 */     this.targetY = ty;
/*  54 */     this.targetZ = tz;
/*  55 */     double dx = tx - this.field_187126_f;
/*  56 */     double dy = ty - this.field_187127_g;
/*  57 */     double dz = tz - this.field_187128_h;
/*  58 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 10.0F);
/*  59 */     if (base < 1) base = 1; 
/*  60 */     this.field_70547_e = base / 2 + this.field_187136_p.nextInt(base);
/*     */     
/*  62 */     float f3 = 0.01F;
/*  63 */     this.field_187129_i = ((float)this.field_187122_b.field_73012_v.nextGaussian() * f3);
/*  64 */     this.field_187130_j = ((float)this.field_187122_b.field_73012_v.nextGaussian() * f3);
/*  65 */     this.field_187131_k = ((float)this.field_187122_b.field_73012_v.nextGaussian() * f3);
/*     */     
/*  67 */     this.field_70545_g = 0.01F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, double sx, double sy, double sz, ItemStack item) {
/*  77 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  78 */     this.itemInstance = item;
/*  79 */     func_187117_a(Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178087_a(item.func_77973_b(), item.func_77952_i()));
/*  80 */     this.field_70545_g = Blocks.field_150431_aC.field_149763_I;
/*  81 */     this.field_70552_h = this.field_70553_i = this.field_70551_j = 0.6F;
/*  82 */     this.field_70544_f = this.field_187136_p.nextFloat() * 0.3F + 0.4F;
/*  83 */     this.side = 0;
/*  84 */     this.targetX = tx;
/*  85 */     this.targetY = ty;
/*  86 */     this.targetZ = tz;
/*  87 */     double dx = tx - this.field_187126_f;
/*  88 */     double dy = ty - this.field_187127_g;
/*  89 */     double dz = tz - this.field_187128_h;
/*  90 */     int base = (int)(MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz) * 10.0F);
/*  91 */     if (base < 1) base = 1; 
/*  92 */     this.field_70547_e = base / 2 + this.field_187136_p.nextInt(base);
/*     */     
/*  94 */     float f3 = 0.01F;
/*  95 */     this.field_187129_i = sx + ((float)this.field_187122_b.field_73012_v.nextGaussian() * f3);
/*  96 */     this.field_187130_j = sy + ((float)this.field_187122_b.field_73012_v.nextGaussian() * f3);
/*  97 */     this.field_187131_k = sz + ((float)this.field_187122_b.field_73012_v.nextGaussian() * f3);
/*     */ 
/*     */     
/* 100 */     this.field_70545_g = 0.01F;
/*     */ 
/*     */     
/* 103 */     Entity renderentity = FMLClientHandler.instance().getClient().func_175606_aa();
/* 104 */     int visibleDistance = 64;
/* 105 */     if (!(FMLClientHandler.instance().getClient()).field_71474_y.field_74347_j) visibleDistance = 32; 
/* 106 */     if (renderentity.func_70011_f(this.field_187126_f, this.field_187127_g, this.field_187128_h) > visibleDistance) this.field_70547_e = 0;
/*     */   
/*     */   }
/*     */   
/* 110 */   public void setTarget(Entity target) { this.target = target; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189213_a() {
/* 116 */     this.field_187123_c = this.field_187126_f;
/* 117 */     this.field_187124_d = this.field_187127_g;
/* 118 */     this.field_187125_e = this.field_187128_h;
/*     */     
/* 120 */     if (this.target != null) {
/* 121 */       this.targetX = this.target.field_70165_t;
/* 122 */       this.targetY = this.target.field_70163_u + this.target.func_70047_e();
/* 123 */       this.targetZ = this.target.field_70161_v;
/*     */     } 
/*     */     
/* 126 */     if (this.field_70546_d++ >= this.field_70547_e || (
/* 127 */       MathHelper.func_76128_c(this.field_187126_f) == MathHelper.func_76128_c(this.targetX) && 
/* 128 */       MathHelper.func_76128_c(this.field_187127_g) == MathHelper.func_76128_c(this.targetY) && 
/* 129 */       MathHelper.func_76128_c(this.field_187128_h) == MathHelper.func_76128_c(this.targetZ))) {
/*     */       
/* 131 */       func_187112_i();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 138 */     func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
/*     */     
/* 140 */     this.field_187129_i *= 0.985D;
/* 141 */     this.field_187130_j *= 0.95D;
/* 142 */     this.field_187131_k *= 0.985D;
/*     */     
/* 144 */     double dx = this.targetX - this.field_187126_f;
/* 145 */     double dy = this.targetY - this.field_187127_g;
/* 146 */     double dz = this.targetZ - this.field_187128_h;
/* 147 */     double d11 = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/* 148 */     double clamp = Math.min(0.25D, d11 / 15.0D);
/*     */     
/* 150 */     if (d11 < 2.0D) {
/* 151 */       this.field_70544_f *= 0.9F;
/*     */     }
/*     */     
/* 154 */     dx /= d11;
/* 155 */     dy /= d11;
/* 156 */     dz /= d11;
/*     */     
/* 158 */     this.field_187129_i += dx * clamp;
/* 159 */     this.field_187130_j += dy * clamp;
/* 160 */     this.field_187131_k += dz * clamp;
/*     */     
/* 162 */     this.field_187129_i = MathHelper.func_151237_a((float)this.field_187129_i, -clamp, clamp);
/* 163 */     this.field_187130_j = MathHelper.func_151237_a((float)this.field_187130_j, -clamp, clamp);
/* 164 */     this.field_187131_k = MathHelper.func_151237_a((float)this.field_187131_k, -clamp, clamp);
/*     */     
/* 166 */     this.field_187129_i += this.field_187136_p.nextGaussian() * 0.005D;
/* 167 */     this.field_187130_j += this.field_187136_p.nextGaussian() * 0.005D;
/* 168 */     this.field_187131_k += this.field_187136_p.nextGaussian() * 0.005D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public int func_70537_b() { return 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FXBoreParticles getObjectColor(BlockPos pos) {
/* 181 */     if (this.blockInstance != null && this.field_187122_b.func_180495_p(pos) == this.blockInstance) {
/* 182 */       if (this.blockInstance == Blocks.field_150349_c && this.side != 1)
/*     */       {
/* 184 */         return this;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 190 */         int var4 = Minecraft.func_71410_x().func_184125_al().func_186724_a(this.blockInstance, this.field_187122_b, pos, 0);
/* 191 */         this.field_70552_h *= (var4 >> 16 & 0xFF) / 255.0F;
/* 192 */         this.field_70553_i *= (var4 >> 8 & 0xFF) / 255.0F;
/* 193 */         this.field_70551_j *= (var4 & 0xFF) / 255.0F;
/* 194 */       } catch (Exception exception) {}
/* 195 */       return this;
/*     */     } 
/*     */     
/*     */     try {
/* 199 */       int var4 = Minecraft.func_71410_x().getItemColors().func_186728_a(this.itemInstance, 0);
/* 200 */       this.field_70552_h *= (var4 >> 16 & 0xFF) / 255.0F;
/* 201 */       this.field_70553_i *= (var4 >> 8 & 0xFF) / 255.0F;
/* 202 */       this.field_70551_j *= (var4 & 0xFF) / 255.0F;
/* 203 */     } catch (Exception exception) {}
/* 204 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180434_a(BufferBuilder p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 213 */     float f6 = (this.field_94054_b + this.field_70548_b / 4.0F) / 16.0F;
/* 214 */     float f7 = f6 + 0.015609375F;
/* 215 */     float f8 = (this.field_94055_c + this.field_70549_c / 4.0F) / 16.0F;
/* 216 */     float f9 = f8 + 0.015609375F;
/* 217 */     float f10 = 0.1F * this.field_70544_f;
/*     */     
/* 219 */     if (this.field_187119_C != null) {
/*     */       
/* 221 */       f6 = this.field_187119_C.func_94214_a((this.field_70548_b / 4.0F * 16.0F));
/* 222 */       f7 = this.field_187119_C.func_94214_a(((this.field_70548_b + 1.0F) / 4.0F * 16.0F));
/* 223 */       f8 = this.field_187119_C.func_94207_b((this.field_70549_c / 4.0F * 16.0F));
/* 224 */       f9 = this.field_187119_C.func_94207_b(((this.field_70549_c + 1.0F) / 4.0F * 16.0F));
/*     */     } 
/* 226 */     int i = func_189214_a(p_180434_3_);
/* 227 */     int j = i >> 16 & 0xFFFF;
/* 228 */     int k = i & 0xFFFF;
/* 229 */     float f11 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * p_180434_3_ - field_70556_an);
/* 230 */     float f12 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * p_180434_3_ - field_70554_ao);
/* 231 */     float f13 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * p_180434_3_ - field_70555_ap);
/* 232 */     p_180434_1_.func_181662_b((f11 - p_180434_4_ * f10 - p_180434_7_ * f10), (f12 - p_180434_5_ * f10), (f13 - p_180434_6_ * f10 - p_180434_8_ * f10)).func_187315_a(f6, f9)
/* 233 */       .func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_187314_a(j, k).func_181675_d();
/* 234 */     p_180434_1_.func_181662_b((f11 - p_180434_4_ * f10 + p_180434_7_ * f10), (f12 + p_180434_5_ * f10), (f13 - p_180434_6_ * f10 + p_180434_8_ * f10)).func_187315_a(f6, f8)
/* 235 */       .func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_187314_a(j, k).func_181675_d();
/* 236 */     p_180434_1_.func_181662_b((f11 + p_180434_4_ * f10 + p_180434_7_ * f10), (f12 + p_180434_5_ * f10), (f13 + p_180434_6_ * f10 + p_180434_8_ * f10)).func_187315_a(f7, f8)
/* 237 */       .func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_187314_a(j, k).func_181675_d();
/* 238 */     p_180434_1_.func_181662_b((f11 + p_180434_4_ * f10 - p_180434_7_ * f10), (f12 - p_180434_5_ * f10), (f13 + p_180434_6_ * f10 - p_180434_8_ * f10)).func_187315_a(f7, f9)
/* 239 */       .func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F).func_187314_a(j, k).func_181675_d();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXBoreParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */