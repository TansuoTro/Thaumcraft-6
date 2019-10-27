/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.world.taint.BlockTaint;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class EntityFallingTaint extends Entity implements IEntityAdditionalSpawnData {
/*     */   public IBlockState fallTile;
/*     */   BlockPos oldPos;
/*     */   
/*  28 */   public IBlockState getBlock() { return this.fallTile; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int fallTime;
/*     */ 
/*     */   
/*     */   private int fallHurtMax;
/*     */ 
/*     */   
/*     */   private float fallHurtAmount;
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityFallingTaint(World par1World) {
/*  44 */     super(par1World);
/*  45 */     this.fallTime = 0;
/*  46 */     this.fallHurtMax = 40;
/*  47 */     this.fallHurtAmount = 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFallingTaint(World par1World, double par2, double par4, double par6, IBlockState par8, BlockPos o) {
/*  52 */     super(par1World);
/*  53 */     this.fallTime = 0;
/*  54 */     this.fallHurtMax = 40;
/*  55 */     this.fallHurtAmount = 2.0F;
/*  56 */     this.fallTile = par8;
/*  57 */     this.field_70156_m = true;
/*  58 */     func_70105_a(0.98F, 0.98F);
/*  59 */     func_70107_b(par2, par4, par6);
/*  60 */     this.field_70159_w = 0.0D;
/*  61 */     this.field_70181_x = 0.0D;
/*  62 */     this.field_70179_y = 0.0D;
/*  63 */     this.field_70169_q = par2;
/*  64 */     this.field_70167_r = par4;
/*  65 */     this.field_70166_s = par6;
/*  66 */     this.oldPos = o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   protected boolean func_70041_e_() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  86 */     data.writeInt(Block.func_149682_b(this.fallTile.func_177230_c()));
/*  87 */     data.writeByte(this.fallTile.func_177230_c().func_176201_c(this.fallTile));
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*     */     try {
/*  93 */       this.fallTile = Block.func_149729_e(data.readInt()).func_176203_a(data.readByte());
/*  94 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public boolean func_70067_L() { return !this.field_70128_L; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 113 */     if (this.fallTile == null || this.fallTile == Blocks.field_150350_a) {
/*     */       
/* 115 */       func_70106_y();
/*     */     }
/*     */     else {
/*     */       
/* 119 */       this.field_70169_q = this.field_70165_t;
/* 120 */       this.field_70167_r = this.field_70163_u;
/* 121 */       this.field_70166_s = this.field_70161_v;
/* 122 */       this.fallTime++;
/* 123 */       this.field_70181_x -= 0.03999999910593033D;
/* 124 */       func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 125 */       this.field_70159_w *= 0.9800000190734863D;
/* 126 */       this.field_70181_x *= 0.9800000190734863D;
/* 127 */       this.field_70179_y *= 0.9800000190734863D;
/*     */       
/* 129 */       BlockPos bp = new BlockPos(this);
/*     */       
/* 131 */       if (!this.field_70170_p.field_72995_K) {
/*     */ 
/*     */ 
/*     */         
/* 135 */         if (this.fallTime == 1) {
/*     */ 
/*     */           
/* 138 */           if (this.field_70170_p.func_180495_p(this.oldPos) != this.fallTile) {
/*     */             
/* 140 */             func_70106_y();
/*     */             
/*     */             return;
/*     */           } 
/* 144 */           this.field_70170_p.func_175698_g(this.oldPos);
/*     */         } 
/*     */         
/* 147 */         if (this.field_70122_E || this.field_70170_p.func_180495_p(bp.func_177977_b()) == BlocksTC.fluxGoo)
/*     */         {
/* 149 */           this.field_70159_w *= 0.699999988079071D;
/* 150 */           this.field_70179_y *= 0.699999988079071D;
/* 151 */           this.field_70181_x *= -0.5D;
/*     */           
/* 153 */           if (this.field_70170_p.func_180495_p(bp).func_177230_c() != Blocks.field_150331_J && this.field_70170_p
/* 154 */             .func_180495_p(bp).func_177230_c() != Blocks.field_180384_M && this.field_70170_p
/* 155 */             .func_180495_p(bp).func_177230_c() != Blocks.field_150332_K)
/*     */           {
/* 157 */             func_184185_a(SoundsTC.gore, 0.5F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/* 158 */             func_70106_y();
/*     */             
/* 160 */             if (!canPlace(bp) || BlockTaint.canFallBelow(this.field_70170_p, bp.func_177977_b()) || this.field_70170_p
/* 161 */               .func_175656_a(bp, this.fallTile));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 173 */         else if ((this.fallTime > 100 && !this.field_70170_p.field_72995_K && (bp.func_177956_o() < 1 || bp.func_177956_o() > 256)) || this.fallTime > 600)
/*     */         {
/*     */           
/* 176 */           func_70106_y();
/*     */         }
/*     */       
/* 179 */       } else if (this.field_70122_E || this.fallTime == 1) {
/*     */         
/* 181 */         for (int j = 0; j < 10; j++)
/*     */         {
/* 183 */           FXDispatcher.INSTANCE.taintLandFX(this);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canPlace(BlockPos pos) {
/* 192 */     return (this.field_70170_p.func_180495_p(pos).func_177230_c() == BlocksTC.taintFibre || this.field_70170_p
/* 193 */       .func_180495_p(pos).func_177230_c() == BlocksTC.fluxGoo || this.field_70170_p
/* 194 */       .func_190527_a(this.fallTile.func_177230_c(), pos, true, EnumFacing.UP, (Entity)null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180430_e(float distance, float damageMultiplier) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 208 */     Block block = (this.fallTile != null) ? this.fallTile.func_177230_c() : Blocks.field_150350_a;
/* 209 */     ResourceLocation resourcelocation = (ResourceLocation)Block.field_149771_c.func_177774_c(block);
/* 210 */     par1NBTTagCompound.func_74778_a("Block", (resourcelocation == null) ? "" : resourcelocation.toString());
/* 211 */     par1NBTTagCompound.func_74774_a("Data", (byte)block.func_176201_c(this.fallTile));
/*     */     
/* 213 */     par1NBTTagCompound.func_74774_a("Time", (byte)this.fallTime);
/* 214 */     par1NBTTagCompound.func_74776_a("FallHurtAmount", this.fallHurtAmount);
/* 215 */     par1NBTTagCompound.func_74768_a("FallHurtMax", this.fallHurtMax);
/* 216 */     par1NBTTagCompound.func_74772_a("Old", this.oldPos.func_177986_g());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 225 */     int i = par1NBTTagCompound.func_74771_c("Data") & 0xFF;
/*     */     
/* 227 */     if (par1NBTTagCompound.func_150297_b("Block", 8)) {
/*     */       
/* 229 */       this.fallTile = Block.func_149684_b(par1NBTTagCompound.func_74779_i("Block")).func_176203_a(i);
/*     */     }
/* 231 */     else if (par1NBTTagCompound.func_150297_b("TileID", 99)) {
/*     */       
/* 233 */       this.fallTile = Block.func_149729_e(par1NBTTagCompound.func_74762_e("TileID")).func_176203_a(i);
/*     */     }
/*     */     else {
/*     */       
/* 237 */       this.fallTile = Block.func_149729_e(par1NBTTagCompound.func_74771_c("Tile") & 0xFF).func_176203_a(i);
/*     */     } 
/*     */     
/* 240 */     this.fallTime = par1NBTTagCompound.func_74771_c("Time") & 0xFF;
/* 241 */     this.oldPos = BlockPos.func_177969_a(par1NBTTagCompound.func_74763_f("Old"));
/*     */     
/* 243 */     if (par1NBTTagCompound.func_74764_b("HurtEntities")) {
/*     */       
/* 245 */       this.fallHurtAmount = par1NBTTagCompound.func_74760_g("FallHurtAmount");
/* 246 */       this.fallHurtMax = par1NBTTagCompound.func_74762_e("FallHurtMax");
/*     */     } 
/*     */ 
/*     */     
/* 250 */     if (this.fallTile == null)
/*     */     {
/* 252 */       this.fallTile = Blocks.field_150354_m.func_176223_P();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_85029_a(CrashReportCategory par1CrashReportCategory) {
/* 259 */     super.func_85029_a(par1CrashReportCategory);
/* 260 */     par1CrashReportCategory.func_71507_a("Immitating block ID", Integer.valueOf(Block.func_149682_b(this.fallTile.func_177230_c())));
/* 261 */     par1CrashReportCategory.func_71507_a("Immitating block data", Integer.valueOf(this.fallTile.func_177230_c().func_176201_c(this.fallTile)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public SoundCategory func_184176_by() { return SoundCategory.BLOCKS; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 274 */   public World getWorld() { return this.field_70170_p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 281 */   public boolean func_90999_ad() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\EntityFallingTaint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */