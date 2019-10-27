/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileMirrorRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  35 */   FloatBuffer fBuffer = GLAllocation.func_74529_h(16);
/*     */   
/*  37 */   private static final ResourceLocation t1 = new ResourceLocation("thaumcraft", "textures/misc/tunnel.png");
/*  38 */   private static final ResourceLocation t2 = new ResourceLocation("thaumcraft", "textures/misc/particlefield.png");
/*     */ 
/*     */   
/*     */   public void drawPlaneYPos(TileEntity tileentityendportal, double x, double y, double z, float f) {
/*  42 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/*  43 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/*  44 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/*  45 */     GL11.glDisable(2896);
/*  46 */     Random random = new Random(31100L);
/*  47 */     float offset = 0.99F;
/*  48 */     float p = 0.1875F;
/*  49 */     for (int i = 0; i < 16; i++) {
/*     */       
/*  51 */       GL11.glPushMatrix();
/*  52 */       float f5 = (16 - i);
/*  53 */       float f6 = 0.0625F;
/*  54 */       float f7 = 1.0F / (f5 + 1.0F);
/*  55 */       if (i == 0) {
/*     */         
/*  57 */         func_147499_a(t1);
/*  58 */         f7 = 0.1F;
/*  59 */         f5 = 65.0F;
/*  60 */         f6 = 0.125F;
/*  61 */         GL11.glEnable(3042);
/*  62 */         GL11.glBlendFunc(770, 771);
/*     */       } 
/*  64 */       if (i == 1) {
/*     */         
/*  66 */         func_147499_a(t2);
/*  67 */         GL11.glEnable(3042);
/*  68 */         GL11.glBlendFunc(1, 1);
/*  69 */         f6 = 0.5F;
/*     */       } 
/*  71 */       float f8 = (float)(y + offset);
/*  72 */       float f9 = (float)(f8 - (ActiveRenderInfo.getCameraPosition()).field_72448_b);
/*  73 */       float f10 = (float)((f8 + f5) - (ActiveRenderInfo.getCameraPosition()).field_72448_b);
/*  74 */       float f11 = f9 / f10;
/*  75 */       f11 = (float)(y + offset) + f11;
/*  76 */       GL11.glTranslatef(px, f11, pz);
/*  77 */       GL11.glTexGeni(8192, 9472, 9217);
/*  78 */       GL11.glTexGeni(8193, 9472, 9217);
/*  79 */       GL11.glTexGeni(8194, 9472, 9217);
/*  80 */       GL11.glTexGeni(8195, 9472, 9216);
/*  81 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/*  82 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/*  83 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*  84 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/*  85 */       GL11.glEnable(3168);
/*  86 */       GL11.glEnable(3169);
/*  87 */       GL11.glEnable(3170);
/*  88 */       GL11.glEnable(3171);
/*  89 */       GL11.glPopMatrix();
/*  90 */       GL11.glMatrixMode(5890);
/*  91 */       GL11.glPushMatrix();
/*  92 */       GL11.glLoadIdentity();
/*  93 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/*  94 */       GL11.glScalef(f6, f6, f6);
/*  95 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/*  96 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/*  97 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/*  98 */       GL11.glTranslatef(-px, -pz, -py);
/*     */       
/* 100 */       GL11.glTranslated((ActiveRenderInfo.getCameraPosition()).field_72450_a * f5 / f9, (ActiveRenderInfo.getCameraPosition()).field_72449_c * f5 / f9, -py);
/*     */       
/* 102 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 103 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 104 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 105 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 106 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 107 */       if (i == 0)
/*     */       {
/* 109 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 112 */       tessellator.func_178180_c().func_181662_b(x + p, y + offset, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 113 */       tessellator.func_178180_c().func_181662_b(x + p, y + offset, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 114 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + offset, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 115 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + offset, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 117 */       tessellator.func_78381_a();
/* 118 */       GL11.glPopMatrix();
/* 119 */       GL11.glMatrixMode(5888);
/*     */     } 
/*     */     
/* 122 */     GL11.glDisable(3042);
/* 123 */     GL11.glDisable(3168);
/* 124 */     GL11.glDisable(3169);
/* 125 */     GL11.glDisable(3170);
/* 126 */     GL11.glDisable(3171);
/* 127 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawPlaneYNeg(TileEntity tileentityendportal, double x, double y, double z, float f) {
/* 132 */     float f1 = (float)TileEntityRendererDispatcher.field_147554_b;
/* 133 */     float f2 = (float)TileEntityRendererDispatcher.field_147555_c;
/* 134 */     float f3 = (float)TileEntityRendererDispatcher.field_147552_d;
/* 135 */     GL11.glDisable(2896);
/* 136 */     Random random = new Random(31100L);
/* 137 */     float offset = 0.01F;
/* 138 */     float p = 0.1875F;
/* 139 */     for (int i = 0; i < 16; i++) {
/*     */       
/* 141 */       GL11.glPushMatrix();
/* 142 */       float f5 = (16 - i);
/* 143 */       float f6 = 0.0625F;
/* 144 */       float f7 = 1.0F / (f5 + 1.0F);
/* 145 */       if (i == 0) {
/*     */         
/* 147 */         func_147499_a(t1);
/* 148 */         f7 = 0.1F;
/* 149 */         f5 = 65.0F;
/* 150 */         f6 = 0.125F;
/* 151 */         GL11.glEnable(3042);
/* 152 */         GL11.glBlendFunc(770, 771);
/*     */       } 
/* 154 */       if (i == 1) {
/*     */         
/* 156 */         func_147499_a(t2);
/* 157 */         GL11.glEnable(3042);
/* 158 */         GL11.glBlendFunc(1, 1);
/* 159 */         f6 = 0.5F;
/*     */       } 
/* 161 */       float f8 = (float)-(y + offset);
/* 162 */       float f9 = (float)(f8 + (ActiveRenderInfo.getCameraPosition()).field_72448_b);
/* 163 */       float f10 = (float)((f8 + f5) + (ActiveRenderInfo.getCameraPosition()).field_72448_b);
/* 164 */       float f11 = f9 / f10;
/* 165 */       f11 = (float)(y + offset) + f11;
/* 166 */       GL11.glTranslatef(f1, f11, f3);
/* 167 */       GL11.glTexGeni(8192, 9472, 9217);
/* 168 */       GL11.glTexGeni(8193, 9472, 9217);
/* 169 */       GL11.glTexGeni(8194, 9472, 9217);
/* 170 */       GL11.glTexGeni(8195, 9472, 9216);
/* 171 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 172 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 173 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 174 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 175 */       GL11.glEnable(3168);
/* 176 */       GL11.glEnable(3169);
/* 177 */       GL11.glEnable(3170);
/* 178 */       GL11.glEnable(3171);
/* 179 */       GL11.glPopMatrix();
/* 180 */       GL11.glMatrixMode(5890);
/* 181 */       GL11.glPushMatrix();
/* 182 */       GL11.glLoadIdentity();
/* 183 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 184 */       GL11.glScalef(f6, f6, f6);
/* 185 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 186 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 187 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 188 */       GL11.glTranslatef(-f1, -f3, -f2);
/*     */ 
/*     */       
/* 191 */       GL11.glTranslated((ActiveRenderInfo.getCameraPosition()).field_72450_a * f5 / f9, (ActiveRenderInfo.getCameraPosition()).field_72449_c * f5 / f9, -f2);
/* 192 */       Tessellator tessellator = Tessellator.func_178181_a();
/*     */       
/* 194 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 195 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 196 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 197 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 198 */       if (i == 0)
/*     */       {
/* 200 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 203 */       tessellator.func_178180_c().func_181662_b(x + p, y + offset, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 204 */       tessellator.func_178180_c().func_181662_b(x + p, y + offset, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 205 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + offset, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 206 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + offset, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 208 */       tessellator.func_78381_a();
/* 209 */       GL11.glPopMatrix();
/* 210 */       GL11.glMatrixMode(5888);
/*     */     } 
/*     */     
/* 213 */     GL11.glDisable(3042);
/* 214 */     GL11.glDisable(3168);
/* 215 */     GL11.glDisable(3169);
/* 216 */     GL11.glDisable(3170);
/* 217 */     GL11.glDisable(3171);
/* 218 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawPlaneZNeg(TileEntity tileentityendportal, double x, double y, double z, float f) {
/* 223 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 224 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 225 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 226 */     GL11.glDisable(2896);
/* 227 */     Random random = new Random(31100L);
/* 228 */     float offset = 0.01F;
/* 229 */     float p = 0.1875F;
/* 230 */     for (int i = 0; i < 16; i++) {
/*     */       
/* 232 */       GL11.glPushMatrix();
/* 233 */       float f5 = (16 - i);
/* 234 */       float f6 = 0.0625F;
/* 235 */       float f7 = 1.0F / (f5 + 1.0F);
/* 236 */       if (i == 0) {
/*     */         
/* 238 */         func_147499_a(t1);
/* 239 */         f7 = 0.1F;
/* 240 */         f5 = 65.0F;
/* 241 */         f6 = 0.125F;
/* 242 */         GL11.glEnable(3042);
/* 243 */         GL11.glBlendFunc(770, 771);
/*     */       } 
/* 245 */       if (i == 1) {
/*     */         
/* 247 */         func_147499_a(t2);
/* 248 */         GL11.glEnable(3042);
/* 249 */         GL11.glBlendFunc(1, 1);
/* 250 */         f6 = 0.5F;
/*     */       } 
/* 252 */       float f8 = (float)-(z + offset);
/* 253 */       float f9 = (float)(f8 + (ActiveRenderInfo.getCameraPosition()).field_72449_c);
/* 254 */       float f10 = (float)((f8 + f5) + (ActiveRenderInfo.getCameraPosition()).field_72449_c);
/* 255 */       float f11 = f9 / f10;
/* 256 */       f11 = (float)(z + offset) + f11;
/* 257 */       GL11.glTranslatef(px, py, f11);
/* 258 */       GL11.glTexGeni(8192, 9472, 9217);
/* 259 */       GL11.glTexGeni(8193, 9472, 9217);
/* 260 */       GL11.glTexGeni(8194, 9472, 9217);
/* 261 */       GL11.glTexGeni(8195, 9472, 9216);
/* 262 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 263 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 264 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */       
/* 266 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 267 */       GL11.glEnable(3168);
/* 268 */       GL11.glEnable(3169);
/* 269 */       GL11.glEnable(3170);
/* 270 */       GL11.glEnable(3171);
/* 271 */       GL11.glPopMatrix();
/* 272 */       GL11.glMatrixMode(5890);
/* 273 */       GL11.glPushMatrix();
/* 274 */       GL11.glLoadIdentity();
/* 275 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 276 */       GL11.glScalef(f6, f6, f6);
/* 277 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 278 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 279 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 280 */       GL11.glTranslatef(-px, -py, -pz);
/*     */ 
/*     */       
/* 283 */       GL11.glTranslated((ActiveRenderInfo.getCameraPosition()).field_72450_a * f5 / f9, (ActiveRenderInfo.getCameraPosition()).field_72448_b * f5 / f9, -pz);
/*     */ 
/*     */       
/* 286 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 287 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 288 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 289 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 290 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 291 */       if (i == 0)
/*     */       {
/* 293 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 296 */       tessellator.func_178180_c().func_181662_b(x + p, y + 1.0D - p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 297 */       tessellator.func_178180_c().func_181662_b(x + p, y + p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 298 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 299 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + 1.0D - p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 301 */       tessellator.func_78381_a();
/* 302 */       GL11.glPopMatrix();
/* 303 */       GL11.glMatrixMode(5888);
/*     */     } 
/*     */     
/* 306 */     GL11.glDisable(3042);
/* 307 */     GL11.glDisable(3168);
/* 308 */     GL11.glDisable(3169);
/* 309 */     GL11.glDisable(3170);
/* 310 */     GL11.glDisable(3171);
/* 311 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawPlaneZPos(TileEntity tileentityendportal, double x, double y, double z, float f) {
/* 316 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 317 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 318 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 319 */     GL11.glDisable(2896);
/* 320 */     Random random = new Random(31100L);
/* 321 */     float offset = 0.99F;
/* 322 */     float p = 0.1875F;
/* 323 */     for (int i = 0; i < 16; i++) {
/*     */       
/* 325 */       GL11.glPushMatrix();
/* 326 */       float f5 = (16 - i);
/* 327 */       float f6 = 0.0625F;
/* 328 */       float f7 = 1.0F / (f5 + 1.0F);
/* 329 */       if (i == 0) {
/*     */         
/* 331 */         func_147499_a(t1);
/* 332 */         f7 = 0.1F;
/* 333 */         f5 = 65.0F;
/* 334 */         f6 = 0.125F;
/* 335 */         GL11.glEnable(3042);
/* 336 */         GL11.glBlendFunc(770, 771);
/*     */       } 
/* 338 */       if (i == 1) {
/*     */         
/* 340 */         func_147499_a(t2);
/* 341 */         GL11.glEnable(3042);
/* 342 */         GL11.glBlendFunc(1, 1);
/* 343 */         f6 = 0.5F;
/*     */       } 
/* 345 */       float f8 = (float)(z + offset);
/* 346 */       float f9 = (float)(f8 - (ActiveRenderInfo.getCameraPosition()).field_72449_c);
/* 347 */       float f10 = (float)((f8 + f5) - (ActiveRenderInfo.getCameraPosition()).field_72449_c);
/* 348 */       float f11 = f9 / f10;
/* 349 */       f11 = (float)(z + offset) + f11;
/* 350 */       GL11.glTranslatef(px, py, f11);
/* 351 */       GL11.glTexGeni(8192, 9472, 9217);
/* 352 */       GL11.glTexGeni(8193, 9472, 9217);
/* 353 */       GL11.glTexGeni(8194, 9472, 9217);
/* 354 */       GL11.glTexGeni(8195, 9472, 9216);
/* 355 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 356 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 357 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/*     */       
/* 359 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 360 */       GL11.glEnable(3168);
/* 361 */       GL11.glEnable(3169);
/* 362 */       GL11.glEnable(3170);
/* 363 */       GL11.glEnable(3171);
/* 364 */       GL11.glPopMatrix();
/* 365 */       GL11.glMatrixMode(5890);
/* 366 */       GL11.glPushMatrix();
/* 367 */       GL11.glLoadIdentity();
/* 368 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 369 */       GL11.glScalef(f6, f6, f6);
/* 370 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 371 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 372 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 373 */       GL11.glTranslatef(-px, -py, -pz);
/*     */ 
/*     */       
/* 376 */       GL11.glTranslated((ActiveRenderInfo.getCameraPosition()).field_72450_a * f5 / f9, (ActiveRenderInfo.getCameraPosition()).field_72448_b * f5 / f9, -pz);
/*     */       
/* 378 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 379 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 380 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 381 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 382 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 383 */       if (i == 0)
/*     */       {
/* 385 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 388 */       tessellator.func_178180_c().func_181662_b(x + p, y + p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 389 */       tessellator.func_178180_c().func_181662_b(x + p, y + 1.0D - p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 390 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + 1.0D - p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 391 */       tessellator.func_178180_c().func_181662_b(x + 1.0D - p, y + p, z + offset).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 393 */       tessellator.func_78381_a();
/* 394 */       GL11.glPopMatrix();
/* 395 */       GL11.glMatrixMode(5888);
/*     */     } 
/*     */     
/* 398 */     GL11.glDisable(3042);
/* 399 */     GL11.glDisable(3168);
/* 400 */     GL11.glDisable(3169);
/* 401 */     GL11.glDisable(3170);
/* 402 */     GL11.glDisable(3171);
/* 403 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawPlaneXNeg(TileEntity tileentityendportal, double x, double y, double z, float f) {
/* 408 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 409 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 410 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 411 */     GL11.glDisable(2896);
/* 412 */     Random random = new Random(31100L);
/* 413 */     float offset = 0.01F;
/* 414 */     float p = 0.1875F;
/* 415 */     for (int i = 0; i < 16; i++) {
/*     */       
/* 417 */       GL11.glPushMatrix();
/* 418 */       float f5 = (16 - i);
/* 419 */       float f6 = 0.0625F;
/* 420 */       float f7 = 1.0F / (f5 + 1.0F);
/* 421 */       if (i == 0) {
/*     */         
/* 423 */         func_147499_a(t1);
/* 424 */         f7 = 0.1F;
/* 425 */         f5 = 65.0F;
/* 426 */         f6 = 0.125F;
/* 427 */         GL11.glEnable(3042);
/* 428 */         GL11.glBlendFunc(770, 771);
/*     */       } 
/* 430 */       if (i == 1) {
/*     */         
/* 432 */         func_147499_a(t2);
/* 433 */         GL11.glEnable(3042);
/* 434 */         GL11.glBlendFunc(1, 1);
/* 435 */         f6 = 0.5F;
/*     */       } 
/* 437 */       float f8 = (float)-(x + offset);
/* 438 */       float f9 = (float)(f8 + (ActiveRenderInfo.getCameraPosition()).field_72450_a);
/* 439 */       float f10 = (float)((f8 + f5) + (ActiveRenderInfo.getCameraPosition()).field_72450_a);
/* 440 */       float f11 = f9 / f10;
/* 441 */       f11 = (float)(x + offset) + f11;
/* 442 */       GL11.glTranslatef(f11, py, pz);
/* 443 */       GL11.glTexGeni(8192, 9472, 9217);
/* 444 */       GL11.glTexGeni(8193, 9472, 9217);
/* 445 */       GL11.glTexGeni(8194, 9472, 9217);
/* 446 */       GL11.glTexGeni(8195, 9472, 9216);
/*     */ 
/*     */       
/* 449 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 450 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 451 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 452 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 453 */       GL11.glEnable(3168);
/* 454 */       GL11.glEnable(3169);
/* 455 */       GL11.glEnable(3170);
/* 456 */       GL11.glEnable(3171);
/* 457 */       GL11.glPopMatrix();
/* 458 */       GL11.glMatrixMode(5890);
/* 459 */       GL11.glPushMatrix();
/* 460 */       GL11.glLoadIdentity();
/* 461 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 462 */       GL11.glScalef(f6, f6, f6);
/* 463 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 464 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 465 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 466 */       GL11.glTranslatef(-pz, -py, -px);
/*     */ 
/*     */       
/* 469 */       GL11.glTranslated((ActiveRenderInfo.getCameraPosition()).field_72449_c * f5 / f9, (ActiveRenderInfo.getCameraPosition()).field_72448_b * f5 / f9, -px);
/*     */ 
/*     */       
/* 472 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 473 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 474 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 475 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 476 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 477 */       if (i == 0)
/*     */       {
/* 479 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 482 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 1.0D - p, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 483 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 1.0D - p, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 484 */       tessellator.func_178180_c().func_181662_b(x + offset, y + p, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 485 */       tessellator.func_178180_c().func_181662_b(x + offset, y + p, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 487 */       tessellator.func_78381_a();
/* 488 */       GL11.glPopMatrix();
/* 489 */       GL11.glMatrixMode(5888);
/*     */     } 
/*     */     
/* 492 */     GL11.glDisable(3042);
/* 493 */     GL11.glDisable(3168);
/* 494 */     GL11.glDisable(3169);
/* 495 */     GL11.glDisable(3170);
/* 496 */     GL11.glDisable(3171);
/* 497 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawPlaneXPos(TileEntity tileentityendportal, double x, double y, double z, float f) {
/* 502 */     float px = (float)TileEntityRendererDispatcher.field_147554_b;
/* 503 */     float py = (float)TileEntityRendererDispatcher.field_147555_c;
/* 504 */     float pz = (float)TileEntityRendererDispatcher.field_147552_d;
/* 505 */     GL11.glDisable(2896);
/* 506 */     Random random = new Random(31100L);
/* 507 */     float offset = 0.99F;
/* 508 */     float p = 0.1875F;
/* 509 */     for (int i = 0; i < 16; i++) {
/*     */       
/* 511 */       GL11.glPushMatrix();
/* 512 */       float f5 = (16 - i);
/* 513 */       float f6 = 0.0625F;
/* 514 */       float f7 = 1.0F / (f5 + 1.0F);
/* 515 */       if (i == 0) {
/*     */         
/* 517 */         func_147499_a(t1);
/* 518 */         f7 = 0.1F;
/* 519 */         f5 = 65.0F;
/* 520 */         f6 = 0.125F;
/* 521 */         GL11.glEnable(3042);
/* 522 */         GL11.glBlendFunc(770, 771);
/*     */       } 
/* 524 */       if (i == 1) {
/*     */         
/* 526 */         func_147499_a(t2);
/* 527 */         GL11.glEnable(3042);
/* 528 */         GL11.glBlendFunc(1, 1);
/* 529 */         f6 = 0.5F;
/*     */       } 
/* 531 */       float f8 = (float)(x + offset);
/* 532 */       float f9 = (float)(f8 - (ActiveRenderInfo.getCameraPosition()).field_72450_a);
/* 533 */       float f10 = (float)((f8 + f5) - (ActiveRenderInfo.getCameraPosition()).field_72450_a);
/* 534 */       float f11 = f9 / f10;
/* 535 */       f11 = (float)(x + offset) + f11;
/* 536 */       GL11.glTranslatef(f11, py, pz);
/* 537 */       GL11.glTexGeni(8192, 9472, 9217);
/* 538 */       GL11.glTexGeni(8193, 9472, 9217);
/* 539 */       GL11.glTexGeni(8194, 9472, 9217);
/* 540 */       GL11.glTexGeni(8195, 9472, 9216);
/*     */ 
/*     */       
/* 543 */       GL11.glTexGen(8193, 9473, calcFloatBuffer(0.0F, 1.0F, 0.0F, 0.0F));
/* 544 */       GL11.glTexGen(8192, 9473, calcFloatBuffer(0.0F, 0.0F, 1.0F, 0.0F));
/* 545 */       GL11.glTexGen(8194, 9473, calcFloatBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 546 */       GL11.glTexGen(8195, 9474, calcFloatBuffer(1.0F, 0.0F, 0.0F, 0.0F));
/* 547 */       GL11.glEnable(3168);
/* 548 */       GL11.glEnable(3169);
/* 549 */       GL11.glEnable(3170);
/* 550 */       GL11.glEnable(3171);
/* 551 */       GL11.glPopMatrix();
/* 552 */       GL11.glMatrixMode(5890);
/* 553 */       GL11.glPushMatrix();
/* 554 */       GL11.glLoadIdentity();
/* 555 */       GL11.glTranslatef(0.0F, (float)(System.currentTimeMillis() % 700000L) / 250000.0F, 0.0F);
/* 556 */       GL11.glScalef(f6, f6, f6);
/* 557 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/* 558 */       GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/* 559 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 560 */       GL11.glTranslatef(-pz, -py, -px);
/*     */ 
/*     */       
/* 563 */       GL11.glTranslated((ActiveRenderInfo.getCameraPosition()).field_72449_c * f5 / f9, (ActiveRenderInfo.getCameraPosition()).field_72448_b * f5 / f9, -px);
/*     */ 
/*     */       
/* 566 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 567 */       tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 568 */       f11 = random.nextFloat() * 0.5F + 0.1F;
/* 569 */       float f12 = random.nextFloat() * 0.5F + 0.4F;
/* 570 */       float f13 = random.nextFloat() * 0.5F + 0.5F;
/* 571 */       if (i == 0)
/*     */       {
/* 573 */         f11 = f12 = f13 = 1.0F;
/*     */       }
/*     */       
/* 576 */       tessellator.func_178180_c().func_181662_b(x + offset, y + p, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 577 */       tessellator.func_178180_c().func_181662_b(x + offset, y + p, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 578 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 1.0D - p, z + 1.0D - p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/* 579 */       tessellator.func_178180_c().func_181662_b(x + offset, y + 1.0D - p, z + p).func_181666_a(f11 * f7, f12 * f7, f13 * f7, 1.0F).func_181675_d();
/*     */       
/* 581 */       tessellator.func_78381_a();
/* 582 */       GL11.glPopMatrix();
/* 583 */       GL11.glMatrixMode(5888);
/*     */     } 
/*     */     
/* 586 */     GL11.glDisable(3042);
/* 587 */     GL11.glDisable(3168);
/* 588 */     GL11.glDisable(3169);
/* 589 */     GL11.glDisable(3170);
/* 590 */     GL11.glDisable(3171);
/* 591 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   
/*     */   private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3) {
/* 596 */     this.fBuffer.clear();
/* 597 */     this.fBuffer.put(f).put(f1).put(f2).put(f3);
/* 598 */     this.fBuffer.flip();
/* 599 */     return this.fBuffer;
/*     */   }
/*     */   
/* 602 */   private static ResourceLocation mp = new ResourceLocation("thaumcraft", "textures/blocks/mirrorpane.png");
/* 603 */   private static ResourceLocation mpt = new ResourceLocation("thaumcraft", "textures/blocks/mirrorpanetrans.png");
/*     */ 
/*     */   
/*     */   public void func_192841_a(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
/* 607 */     super.func_192841_a(te, x, y, z, partialTicks, destroyStage, alpha);
/* 608 */     EnumFacing dir = BlockStateUtils.getFacing(te.func_145832_p());
/* 609 */     boolean linked = false;
/* 610 */     if (te instanceof TileMirror) {
/* 611 */       linked = ((TileMirror)te).linked;
/*     */     }
/* 613 */     if (te instanceof TileMirrorEssentia) linked = ((TileMirrorEssentia)te).linked; 
/* 614 */     int b = te.func_145838_q().func_185484_c(te.func_145831_w().func_180495_p(te.func_174877_v()), te.func_145831_w(), te.func_174877_v());
/*     */     
/* 616 */     GL11.glPushMatrix();
/* 617 */     GL11.glEnable(3042);
/* 618 */     GL11.glBlendFunc(770, 771);
/* 619 */     translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.01F);
/* 620 */     UtilsFX.renderItemIn2D((te.func_145838_q() == BlocksTC.mirror) ? "thaumcraft:blocks/mirrorframe" : "thaumcraft:blocks/mirrorframe2", 0.0625F);
/* 621 */     GL11.glDisable(3042);
/* 622 */     GL11.glPopMatrix();
/*     */     
/* 624 */     if (linked && (FMLClientHandler.instance().getClient()).field_71439_g.func_174831_c(te.func_174877_v()) < 1024.0D) {
/* 625 */       GL11.glPushMatrix();
/* 626 */       switch (dir) { case DOWN:
/* 627 */           drawPlaneYPos(te, x, y, z, partialTicks); break;
/* 628 */         case UP: drawPlaneYNeg(te, x, y, z, partialTicks); break;
/* 629 */         case WEST: drawPlaneXPos(te, x, y, z, partialTicks); break;
/* 630 */         case EAST: drawPlaneXNeg(te, x, y, z, partialTicks); break;
/* 631 */         case NORTH: drawPlaneZPos(te, x, y, z, partialTicks); break;
/* 632 */         case SOUTH: drawPlaneZNeg(te, x, y, z, partialTicks); break; }
/*     */       
/* 634 */       GL11.glPopMatrix();
/* 635 */       GL11.glPushMatrix();
/* 636 */       GL11.glEnable(3042);
/* 637 */       GL11.glBlendFunc(770, 771);
/* 638 */       translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.02F);
/* 639 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 640 */       GL11.glTranslated(0.5D, -0.5D, 0.0D);
/* 641 */       UtilsFX.renderQuadCentered(mpt, 1.0F, 1.0F, 1.0F, 1.0F, b, 771, 1.0F);
/* 642 */       GL11.glDisable(3042);
/* 643 */       GL11.glPopMatrix();
/*     */     } else {
/* 645 */       GL11.glPushMatrix();
/* 646 */       GL11.glEnable(3042);
/* 647 */       GL11.glBlendFunc(770, 771);
/* 648 */       translateFromOrientation((float)x, (float)y, (float)z, dir.ordinal(), 0.02F);
/* 649 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 650 */       GL11.glTranslated(0.5D, -0.5D, 0.0D);
/* 651 */       UtilsFX.renderQuadCentered(mp, 1.0F, 1.0F, 1.0F, 1.0F, b, 771, 1.0F);
/* 652 */       GL11.glDisable(3042);
/* 653 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void translateFromOrientation(float x, float y, float z, int orientation, float off) {
/* 661 */     if (orientation == 0) {
/*     */       
/* 663 */       GL11.glTranslatef(x, y + 1.0F, z + 1.0F);
/* 664 */       GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*     */     }
/* 666 */     else if (orientation == 1) {
/*     */       
/* 668 */       GL11.glTranslatef(x, y, z);
/* 669 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 670 */     } else if (orientation == 2) {
/*     */       
/* 672 */       GL11.glTranslatef(x, y, z + 1.0F);
/* 673 */     } else if (orientation == 3) {
/*     */       
/* 675 */       GL11.glTranslatef(x + 1.0F, y, z);
/* 676 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 677 */     } else if (orientation == 4) {
/*     */       
/* 679 */       GL11.glTranslatef(x + 1.0F, y, z + 1.0F);
/* 680 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 681 */     } else if (orientation == 5) {
/*     */       
/* 683 */       GL11.glTranslatef(x, y, z);
/* 684 */       GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */     } 
/* 686 */     GL11.glTranslatef(0.0F, 0.0F, -off);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\tile\TileMirrorRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */