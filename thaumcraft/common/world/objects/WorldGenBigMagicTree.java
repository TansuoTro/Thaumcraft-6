/*     */ package thaumcraft.common.world.objects;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLeaves;
/*     */ import net.minecraft.block.BlockLog;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ 
/*     */ 
/*     */ public class WorldGenBigMagicTree
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private Random field_175949_k;
/*     */   private World world;
/*     */   private BlockPos basePos;
/*     */   int heightLimit;
/*     */   int height;
/*     */   double heightAttenuation;
/*     */   double branchSlope;
/*     */   double scaleWidth;
/*     */   double leafDensity;
/*     */   int trunkSize;
/*     */   int heightLimitLimit;
/*     */   int leafDistanceLimit;
/*     */   List field_175948_j;
/*     */   private static final String __OBFID = "CL_00000400";
/*     */   
/*     */   public WorldGenBigMagicTree(boolean p_i2008_1_) {
/*  39 */     super(p_i2008_1_);
/*  40 */     this.basePos = BlockPos.field_177992_a;
/*  41 */     this.heightAttenuation = 0.6618D;
/*  42 */     this.branchSlope = 0.381D;
/*  43 */     this.scaleWidth = 1.25D;
/*  44 */     this.leafDensity = 0.9D;
/*  45 */     this.trunkSize = 1;
/*  46 */     this.heightLimitLimit = 11;
/*  47 */     this.leafDistanceLimit = 4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNodeList() {
/*  53 */     this.height = (int)(this.heightLimit * this.heightAttenuation);
/*     */     
/*  55 */     if (this.height >= this.heightLimit)
/*     */     {
/*  57 */       this.height = this.heightLimit - 1;
/*     */     }
/*     */     
/*  60 */     int i = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  62 */     if (i < 1)
/*     */     {
/*  64 */       i = 1;
/*     */     }
/*     */     
/*  67 */     int j = this.basePos.func_177956_o() + this.height;
/*  68 */     int k = this.heightLimit - this.leafDistanceLimit;
/*  69 */     this.field_175948_j = Lists.newArrayList();
/*  70 */     this.field_175948_j.add(new FoliageCoordinates(this.basePos.func_177981_b(k), j));
/*     */     
/*  72 */     for (; k >= 0; k--) {
/*     */       
/*  74 */       float f = layerSize(k);
/*     */       
/*  76 */       if (f >= 0.0F)
/*     */       {
/*  78 */         for (int l = 0; l < i; l++) {
/*     */           
/*  80 */           double d0 = this.scaleWidth * f * (this.field_175949_k.nextFloat() + 0.328D);
/*  81 */           double d1 = (this.field_175949_k.nextFloat() * 2.0F) * Math.PI;
/*  82 */           double d2 = d0 * Math.sin(d1) + 0.5D;
/*  83 */           double d3 = d0 * Math.cos(d1) + 0.5D;
/*  84 */           BlockPos blockpos = this.basePos.func_177963_a(d2, (k - 1), d3);
/*  85 */           BlockPos blockpos1 = blockpos.func_177981_b(this.leafDistanceLimit);
/*     */           
/*  87 */           if (func_175936_a(blockpos, blockpos1) == -1) {
/*     */             
/*  89 */             int i1 = this.basePos.func_177958_n() - blockpos.func_177958_n();
/*  90 */             int j1 = this.basePos.func_177952_p() - blockpos.func_177952_p();
/*  91 */             double d4 = blockpos.func_177956_o() - Math.sqrt((i1 * i1 + j1 * j1)) * this.branchSlope;
/*  92 */             int k1 = (d4 > j) ? j : (int)d4;
/*  93 */             BlockPos blockpos2 = new BlockPos(this.basePos.func_177958_n(), k1, this.basePos.func_177952_p());
/*     */             
/*  95 */             if (func_175936_a(blockpos2, blockpos) == -1)
/*     */             {
/*  97 */               this.field_175948_j.add(new FoliageCoordinates(blockpos, blockpos2.func_177956_o()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void func_181631_a(BlockPos p_181631_1_, float p_181631_2_, IBlockState p_181631_3_) {
/* 107 */     int i = (int)(p_181631_2_ + 0.618D);
/*     */     
/* 109 */     for (int j = -i; j <= i; j++) {
/*     */       
/* 111 */       for (int k = -i; k <= i; k++) {
/*     */         
/* 113 */         if (Math.pow(Math.abs(j) + 0.5D, 2.0D) + Math.pow(Math.abs(k) + 0.5D, 2.0D) <= (p_181631_2_ * p_181631_2_)) {
/*     */           
/* 115 */           BlockPos blockpos = p_181631_1_.func_177982_a(j, 0, k);
/* 116 */           IBlockState state = this.world.func_180495_p(blockpos);
/*     */           
/* 118 */           if (state.func_177230_c().isAir(state, this.world, blockpos) || state.func_177230_c().isLeaves(state, this.world, blockpos))
/*     */           {
/* 120 */             func_175903_a(this.world, blockpos, p_181631_3_);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   float layerSize(int p_76490_1_) {
/* 129 */     if (p_76490_1_ < this.heightLimit * 0.3F)
/*     */     {
/* 131 */       return -1.0F;
/*     */     }
/*     */ 
/*     */     
/* 135 */     float f = this.heightLimit / 2.0F;
/* 136 */     float f1 = f - p_76490_1_;
/* 137 */     float f2 = MathHelper.func_76129_c(f * f - f1 * f1);
/*     */     
/* 139 */     if (f1 == 0.0F) {
/*     */       
/* 141 */       f2 = f;
/*     */     }
/* 143 */     else if (Math.abs(f1) >= f) {
/*     */       
/* 145 */       return 0.0F;
/*     */     } 
/*     */     
/* 148 */     return f2 * 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   float leafSize(int p_76495_1_) { return (p_76495_1_ >= 0 && p_76495_1_ < this.leafDistanceLimit) ? ((p_76495_1_ != 0 && p_76495_1_ != this.leafDistanceLimit - 1) ? 3.0F : 2.0F) : -1.0F; }
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNode(BlockPos pos) {
/* 159 */     for (int i = 0; i < this.leafDistanceLimit; i++)
/*     */     {
/* 161 */       func_181631_a(pos.func_177981_b(i), leafSize(i), Blocks.field_150362_t.func_176223_P().func_177226_a(BlockLeaves.field_176236_b, Boolean.valueOf(false)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void func_175937_a(BlockPos p_175937_1_, BlockPos p_175937_2_, Block p_175937_3_) {
/* 167 */     BlockPos blockpos2 = p_175937_2_.func_177982_a(-p_175937_1_.func_177958_n(), -p_175937_1_.func_177956_o(), -p_175937_1_.func_177952_p());
/* 168 */     int i = func_175935_b(blockpos2);
/* 169 */     float f = blockpos2.func_177958_n() / i;
/* 170 */     float f1 = blockpos2.func_177956_o() / i;
/* 171 */     float f2 = blockpos2.func_177952_p() / i;
/*     */     
/* 173 */     for (int j = 0; j <= i; j++) {
/*     */       
/* 175 */       BlockPos blockpos3 = p_175937_1_.func_177963_a((0.5F + j * f), (0.5F + j * f1), (0.5F + j * f2));
/* 176 */       BlockLog.EnumAxis enumaxis = func_175938_b(p_175937_1_, blockpos3);
/* 177 */       func_175903_a(this.world, blockpos3, p_175937_3_.func_176223_P().func_177226_a(BlockLog.field_176299_a, enumaxis));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_175935_b(BlockPos p_175935_1_) {
/* 183 */     int i = MathHelper.func_76130_a(p_175935_1_.func_177958_n());
/* 184 */     int j = MathHelper.func_76130_a(p_175935_1_.func_177956_o());
/* 185 */     int k = MathHelper.func_76130_a(p_175935_1_.func_177952_p());
/* 186 */     return (k > i && k > j) ? k : ((j > i) ? j : i);
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockLog.EnumAxis func_175938_b(BlockPos p_175938_1_, BlockPos p_175938_2_) {
/* 191 */     BlockLog.EnumAxis enumaxis = BlockLog.EnumAxis.Y;
/* 192 */     int i = Math.abs(p_175938_2_.func_177958_n() - p_175938_1_.func_177958_n());
/* 193 */     int j = Math.abs(p_175938_2_.func_177952_p() - p_175938_1_.func_177952_p());
/* 194 */     int k = Math.max(i, j);
/*     */     
/* 196 */     if (k > 0)
/*     */     {
/* 198 */       if (i == k) {
/*     */         
/* 200 */         enumaxis = BlockLog.EnumAxis.X;
/*     */       }
/* 202 */       else if (j == k) {
/*     */         
/* 204 */         enumaxis = BlockLog.EnumAxis.Z;
/*     */       } 
/*     */     }
/*     */     
/* 208 */     return enumaxis;
/*     */   }
/*     */ 
/*     */   
/*     */   void func_175941_b() {
/* 213 */     Iterator iterator = this.field_175948_j.iterator();
/*     */     
/* 215 */     while (iterator.hasNext()) {
/*     */       
/* 217 */       FoliageCoordinates foliagecoordinates = (FoliageCoordinates)iterator.next();
/* 218 */       generateLeafNode(foliagecoordinates);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 224 */   boolean leafNodeNeedsBase(int p_76493_1_) { return (p_76493_1_ >= this.heightLimit * 0.2D); }
/*     */ 
/*     */ 
/*     */   
/*     */   void func_175942_c() {
/* 229 */     BlockPos blockpos = this.basePos;
/* 230 */     BlockPos blockpos1 = this.basePos.func_177981_b(this.height);
/* 231 */     Block block = Blocks.field_150364_r;
/* 232 */     func_175937_a(blockpos, blockpos1, block);
/*     */     
/* 234 */     if (this.trunkSize == 2) {
/*     */       
/* 236 */       func_175937_a(blockpos.func_177974_f(), blockpos1.func_177974_f(), block);
/* 237 */       func_175937_a(blockpos.func_177974_f().func_177968_d(), blockpos1.func_177974_f().func_177968_d(), block);
/* 238 */       func_175937_a(blockpos.func_177968_d(), blockpos1.func_177968_d(), block);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void func_175939_d() {
/* 244 */     Iterator iterator = this.field_175948_j.iterator();
/*     */     
/* 246 */     while (iterator.hasNext()) {
/*     */       
/* 248 */       FoliageCoordinates foliagecoordinates = (FoliageCoordinates)iterator.next();
/* 249 */       int i = foliagecoordinates.func_177999_q();
/* 250 */       BlockPos blockpos = new BlockPos(this.basePos.func_177958_n(), i, this.basePos.func_177952_p());
/*     */       
/* 252 */       if (leafNodeNeedsBase(i - this.basePos.func_177956_o()))
/*     */       {
/* 254 */         func_175937_a(blockpos, foliagecoordinates, Blocks.field_150364_r);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   int func_175936_a(BlockPos p_175936_1_, BlockPos p_175936_2_) {
/* 261 */     BlockPos blockpos2 = p_175936_2_.func_177982_a(-p_175936_1_.func_177958_n(), -p_175936_1_.func_177956_o(), -p_175936_1_.func_177952_p());
/* 262 */     int i = func_175935_b(blockpos2);
/* 263 */     float f = blockpos2.func_177958_n() / i;
/* 264 */     float f1 = blockpos2.func_177956_o() / i;
/* 265 */     float f2 = blockpos2.func_177952_p() / i;
/*     */     
/* 267 */     if (i == 0)
/*     */     {
/* 269 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 273 */     for (int j = 0; j <= i; j++) {
/*     */       
/* 275 */       BlockPos blockpos3 = p_175936_1_.func_177963_a((0.5F + j * f), (0.5F + j * f1), (0.5F + j * f2));
/*     */       
/* 277 */       if (!isReplaceable(this.world, blockpos3))
/*     */       {
/* 279 */         return j;
/*     */       }
/*     */     } 
/*     */     
/* 283 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public void func_175904_e() { this.leafDistanceLimit = 4; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180709_b(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 294 */     this.world = worldIn;
/* 295 */     this.basePos = p_180709_3_;
/* 296 */     this.field_175949_k = new Random(p_180709_2_.nextLong());
/*     */     
/* 298 */     if (this.heightLimit == 0)
/*     */     {
/* 300 */       this.heightLimit = 11 + this.field_175949_k.nextInt(this.heightLimitLimit);
/*     */     }
/*     */     
/* 303 */     if (!validTreeLocation()) {
/*     */       
/* 305 */       this.world = null;
/* 306 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 310 */     generateLeafNodeList();
/* 311 */     func_175941_b();
/* 312 */     func_175942_c();
/* 313 */     func_175939_d();
/* 314 */     this.world = null;
/* 315 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validTreeLocation() {
/* 321 */     BlockPos down = this.basePos.func_177977_b();
/* 322 */     IBlockState state = this.world.func_180495_p(down);
/* 323 */     boolean isSoil = state.func_177230_c().canSustainPlant(state, this.world, down, EnumFacing.UP, (BlockSapling)Blocks.field_150345_g);
/*     */     
/* 325 */     if (!isSoil)
/*     */     {
/* 327 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 331 */     int i = func_175936_a(this.basePos, this.basePos.func_177981_b(this.heightLimit - 1));
/*     */     
/* 333 */     if (i == -1)
/*     */     {
/* 335 */       return true;
/*     */     }
/* 337 */     if (i < 6)
/*     */     {
/* 339 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 343 */     this.heightLimit = i;
/* 344 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   static class FoliageCoordinates
/*     */     extends BlockPos
/*     */   {
/*     */     private final int field_178000_b;
/*     */     
/*     */     private static final String __OBFID = "CL_00002001";
/*     */     
/*     */     public FoliageCoordinates(BlockPos p_i45635_1_, int p_i45635_2_) {
/* 356 */       super(p_i45635_1_.func_177958_n(), p_i45635_1_.func_177956_o(), p_i45635_1_.func_177952_p());
/* 357 */       this.field_178000_b = p_i45635_2_;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 362 */     public int func_177999_q() { return this.field_178000_b; }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\objects\WorldGenBigMagicTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */